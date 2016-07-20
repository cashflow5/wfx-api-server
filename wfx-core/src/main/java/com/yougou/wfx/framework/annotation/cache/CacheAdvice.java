package com.yougou.wfx.framework.annotation.cache;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.yougou.wfx.framework.cache.local.LocalCacheUtil;
import com.yougou.wfx.framework.jmx.ConfigMBean;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

@Aspect
@Component
public class CacheAdvice {
	
	protected final Logger logger = LoggerFactory.getLogger(CacheAdvice.class);
	
	@Pointcut("@annotation(com.yougou.wfx.framework.annotation.cache.CacheCable)")
	public void redisCachePointcut() {}

	@Around(value="redisCachePointcut()") 
	public Object get(final ProceedingJoinPoint pJointPoint) throws Throwable {
		// 读取配置项，是否开启缓存
    	boolean filterFlag = ConfigMBean.getInstance().getConfigBoolean("com.yougou.wfx.framework.cache", true);
    	
    	if(!filterFlag){
    		return pJointPoint.proceed(); 
    	}
		
		CacheCable redisCache = getAnnotation(pJointPoint);
		if(redisCache == null){
			return pJointPoint.proceed();
		}
		
		//校验参数
		boolean check = checkParams(redisCache);
		if(!check){
			return pJointPoint.proceed();
		}
    	
		Method method = null;
		try {
			method = getMethod(pJointPoint); //获取方法名
		} catch (Exception e) {
			logger.error("缓存Aop:获取的是方法对象异常", e);
			return pJointPoint.proceed();
		}
		
		String spelValue = null;
		try {
			spelValue = parseSpel(redisCache.key(), method,
					pJointPoint.getArgs()); //获取组合key
		} catch (Exception e) {
			logger.error("获取缓存的key对应参数值异常:key="+redisCache.key(), e);
			return pJointPoint.proceed();
		}
		
		logger.debug("获取缓存的key对应参数Spel值:spelValue="+spelValue +" ,nameSpace="+redisCache.value()+" ,expiration="+redisCache.expiration());
		
		if(StringUtils.isBlank(spelValue)){
			logger.error("获取缓存的key对应参数值[:key="+redisCache.key()+"]为空");
			return pJointPoint.proceed();
		}
    	
		String[] nameSpace = redisCache.value(); //缓存值
		long expiration = redisCache.expiration(); //redis缓存过期时间
		long localExpiration = redisCache.localExpiration(); //本地缓存过期时间
		
		/***********先本地缓存，次redis缓存，再方法取************/
		Object value = null;
		//从本地缓存获取
    	boolean localCacheSwitch = ConfigMBean.getInstance().getConfigBoolean("com.yougou.wfx.framework.cache.local.switch", false);//判断本地缓存是否开启（默认）
		if(localCacheSwitch && localExpiration>0){
			try {
				value = LocalCacheUtil.getInstance().getLocalCache(spelValue);
			} catch (Exception e) {
				logger.error("本地缓存读取数据时异常 key="+spelValue, e);
				return pJointPoint.proceed();
			}
			if(null!=value){
				logger.debug("命中本地缓存， key="+spelValue);
				return value;
			}else{
				logger.debug("本地缓存查询数据为空，准备查询redis缓存， key="+spelValue);
			}
		}
		
		//从redis缓存获取
		try {
			value = RedisCacheUtil.getInstance().get(spelValue ,nameSpace);
		} catch (Exception e) {
			logger.error("redis缓存读取数据时异常 key="+spelValue, e);
			return pJointPoint.proceed();
		}
		
		//如果redis中有缓存，则把redis中的缓存根据需要（是否需要本地缓存）保存至本地缓存
		if(null!=value){ 
			logger.debug("命中redis缓存 key="+spelValue);
			if(localCacheSwitch && localExpiration>0){
				try {
					LocalCacheUtil.getInstance().putLocalCache(spelValue, value, new Date(),localExpiration);
				} catch (Exception e) {
					logger.error("本地缓存存入数据异常， key="+spelValue, e);
				}
			}
			return value;
			
		//如果redis中没有缓存，则从方法中取出值保存至redis缓存和本地缓存（根据需要是否保存本地缓存）
		}else{
			logger.debug("缓存注解CacheCable查询数据为空，准备执行目标方法，"+method.getName()+"获取缓存:spelValue="+spelValue +" ,nameSpace="+redisCache.value()+" ,expiration="+redisCache.expiration());
			value = pJointPoint.proceed();
			if(value != null){
				try{
					RedisCacheUtil.getInstance().put(spelValue ,value ,nameSpace ,expiration);
				}catch(Exception e){
					logger.error("缓存注解CacheCable处理[结果存入缓存]异常， key="+spelValue, e);
				}
				if(localCacheSwitch && localExpiration>0){
					try {
						LocalCacheUtil.getInstance().putLocalCache(spelValue, value, new Date(),localExpiration);
					} catch (Exception e) {
						logger.error("本地缓存存入数据异常， key="+spelValue, e);
					}
				}
			}
		}

		return value;
	}
	
	/**
	 * 检验注解参数
	 * @param redisCache
	 * @return  true:参数合法；false:参数不合法
	 */
	private boolean checkParams(CacheCable redisCache) {
		String key = redisCache.key();
		String[] nameSpace = redisCache.value();
		if(StringUtils.isBlank(key) || 
				nameSpace.length == 0){
			return false;
		}
		boolean allParamsNull = true;	//注解参数中至少包含一个非空字符串
		for (String value : nameSpace) {
			if(StringUtils.isNotBlank(value)){
				allParamsNull = false;
				break;
			}
		}
		if(allParamsNull){
			return false;
		}
		return true;
	}

	private CacheCable getAnnotation(final ProceedingJoinPoint pJointPoint) throws Throwable{
		final Signature sig = pJointPoint.getSignature();
		if (!(sig instanceof MethodSignature)) {
			throw new NoSuchMethodException("This annotation is only valid on a method.");
		}
		final MethodSignature msig = (MethodSignature) sig;
		final Object target = pJointPoint.getTarget();
		Method method = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
		return method.getAnnotation(CacheCable.class);
	}

	/**
	 * 获取的是方法对象
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 */
	@SuppressWarnings("rawtypes")
	public Method getMethod(ProceedingJoinPoint pjp) throws SecurityException, NoSuchMethodException {
		Method method = null;
		Object[] args = pjp.getArgs();
		Class[] argTypes = new Class[pjp.getArgs().length];	// 获取参数的类型
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		method = pjp.getTarget().getClass()
				.getMethod(pjp.getSignature().getName(), argTypes);
			
		return method;
	}
	
	/**
	 * 获取缓存的key对应参数值 (SPEL表达式) 
	 * @param key
	 * @param method
	 * @param args
	 * @return
	 */
	private String parseSpel(String key, Method method, Object[] args) throws Exception{
		ExpressionParser parser = new SpelExpressionParser();
		LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = parameterNameDiscoverer.getParameterNames(method);
		// 无入参情况直接返回原始key
		if(paraNameArr == null || paraNameArr.length == 0){
			return key;
		}
		StandardEvaluationContext context = new StandardEvaluationContext();
		for (int i = 0; i < paraNameArr.length; i++) {
			// 判断目标方法参数
			if(args[i] == null){
				throw new Exception("缓存目标方法对应参数值:"+paraNameArr[i]+"=null");
			}
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}

}
