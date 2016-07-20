package com.yougou.wfx.framework.annotation.log;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.RpcContext;
import com.alibaba.fastjson.JSON;

/**
 * 使用AOP对方法进行日志记录
 */
@Aspect
@Component
public class LoggerProfilerIntercepter {

	private static final Logger logger = LoggerFactory.getLogger("ApiLoggerProfiler");

	@Pointcut("@annotation(com.yougou.wfx.framework.annotation.log.LoggerProfile)")
	public void loggerProfilerPointcut() {}

	@Around(value="loggerProfilerPointcut()") 
	public Object doLoggerProfiler(final ProceedingJoinPoint joinPoint)
			throws Throwable {
		long startTime = System.currentTimeMillis();
		Map<String, Object> logMap = new HashMap<String, Object>();
		try {
			if (logger.isInfoEnabled()) {
				Method method = this.getMethod(joinPoint);
				LoggerProfile loggerProfile = method.getAnnotation(LoggerProfile.class);
				if (loggerProfile.methodNote() != null && loggerProfile.methodNote().length() > 0) {
					logMap.put("note", loggerProfile.methodNote());
				}
				String methodName = joinPoint.getSignature().getName();
				String className = joinPoint.getTarget().getClass().getName();
				logMap.put("class", className);
				logMap.put("method", methodName);

				Object[] args = joinPoint.getArgs();
				if (args != null && args.length > 0) {
					for (int i = 0; i < args.length; i++) {
						logMap.put("arg-" + i, args[i]);
					}
				}
				try {
					RpcContext rpcContext = RpcContext.getContext();
					if (rpcContext != null) {
						logMap.put("remoteAddress",rpcContext.getRemoteAddressString());
						URL url = rpcContext.getUrl();
						if (url != null) {
							logMap.put("application",url.getParameter("application"));
						}
					}
				} catch (Exception e) {
					logger.error("RpcContext.getContext() Exception ", e);
				}
			}
		} catch (Exception e) {
			logger.error("LoggerProfile Exception ", e);
		}
		Object obj = null;
		Exception error = null;
		try {
			obj = joinPoint.proceed();
		} catch (Exception ex) {
			error = ex;
		}
		try{
			if (logger.isInfoEnabled()) {
				if (error != null) {
					logMap.put("exception", error.getMessage());
					logMap.put("result", null);
				} else {
					logMap.put("result", obj == null?"null":obj);
				}
				long endTime = System.currentTimeMillis();
				logMap.put("time", String.valueOf((endTime - startTime)) + "ms");
				logger.info(JSON.toJSONString(logMap));
			}
		}catch(Exception ex){
			logger.error("LoggerProfile Exception ", ex);
		}
		if (error != null) {
			throw error;
		}
		return obj;
	}

	/**
	 * @param joinPoint
	 * @return
	 * @throws NoSuchMethodException
	 */
	protected Method getMethod(final JoinPoint joinPoint)
			throws NoSuchMethodException {
		final Signature sig = joinPoint.getSignature();
		if (!(sig instanceof MethodSignature)) {
			throw new NoSuchMethodException(
					"This annotation is only valid on a method.");
		}
		final MethodSignature msig = (MethodSignature) sig;
		final Object target = joinPoint.getTarget();
		return target.getClass().getMethod(msig.getName(),
				msig.getParameterTypes());
	}
}