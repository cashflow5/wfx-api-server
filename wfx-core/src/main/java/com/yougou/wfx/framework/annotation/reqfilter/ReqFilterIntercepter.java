package com.yougou.wfx.framework.annotation.reqfilter;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.rpc.RpcContext;
import com.yougou.tools.common.utils.JSonUtil;
import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.exception.ErrorCode;
import com.yougou.wfx.exception.WFXParamsCheckException;
import com.yougou.wfx.exception.WFXRuntimeException;
import com.yougou.wfx.framework.jmx.ConfigMBean;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.DESEncrypt;

/**
 * 请求控制切面
 * @author wuyang
 * @Date 2016年4月26日
 */
@Aspect  
@Component
public class ReqFilterIntercepter{
	
	protected final Logger logger = LoggerFactory.getLogger("FilterLogger");
	
	/** 
     * 定义一个切入点,在方法含@ReqFilter注解位置切入 
     */  
    @Pointcut("within(com.yougou..*) && @annotation(com.yougou.wfx.framework.annotation.reqfilter.ReqFilter)")  
    public void intercepter(){  
    }  
    
    /** 
     * @param pJointPoint 
     * @return 
     */  
    @Around(value="intercepter()") 
    public Object doFilters(final ProceedingJoinPoint pJointPoint) throws Throwable {  
    	long startime = System.currentTimeMillis();
    	boolean filterFlag = ConfigMBean.getInstance().getConfigBoolean("com.yougou.wfx.api.intercepter", true);
    	if(!filterFlag){
    		return pJointPoint.proceed(); 
    	}
    	boolean isFilter = false;
    	String apiName = null;
    	String signatureName = null;
    	String spelValue = null;
    	Method method = null;
    	Object[] paramValues;
    	String className;
    	ReqFilter iFilter;
    	StringBuffer message = new StringBuffer(128);
		StringBuffer clientKey = new StringBuffer(128);
    	// 方法拦截前处理
    	try {
    		paramValues = pJointPoint.getArgs();
            signatureName = pJointPoint.getSignature().getName();  //获取方法名
            method = getMethod(pJointPoint);
            className = method.getDeclaringClass().getName();
    		apiName = Class.forName(className).getInterfaces()[0].getName();
            iFilter = method.getAnnotation(ReqFilter.class);
            if(StringUtils.isBlank(iFilter.keySpel())){
            	throw new WFXParamsCheckException(ErrorCode.PARAM_IS_NULL, new Throwable("参数'keySpel'为空"));
            }
            if(paramValues == null || paramValues.length == 0){
            	throw new WFXParamsCheckException(ErrorCode.PARAM_IS_NULL, new Throwable("方法为{"+ apiName + "." + signatureName + getParams(method,pJointPoint.getArgs()) + "}参数为空"));
            }
            String keySpel = iFilter.keySpel();
            if(StringUtils.isBlank(keySpel)) {
            	return pJointPoint.proceed(); 	// Spel 表达式为空。不过滤
            }
            long times = iFilter.time();
            long limits = iFilter.limit();
            
            // clientKey=接口全路径.方法名称
            clientKey.append(apiName).append(".").append(signatureName).append("_");
            //解释SPEL,组装拦截主键
    		try {
    			spelValue = parseSpel(keySpel, method,pJointPoint.getArgs());
    		} catch (Exception e) {
    			logger.error("ReqFilter对应参数值异常:keySpel="+keySpel, e);
    		}
    		if(StringUtils.isBlank(spelValue)){
    			logger.error("ReqFilter对应参数值[:keySpel="+keySpel+"]为空");
    			return pJointPoint.proceed();
    		}
			try {
				spelValue = DESEncrypt.encrypt(String.valueOf(spelValue));
			} catch (Exception e) {
				logger.error("ReqFilter对key DES加密异常->spelValue="+spelValue, e);
				return pJointPoint.proceed(); 
			}
        	clientKey.append(spelValue);
            
    		isFilter = doFilter(clientKey.toString(), limits, times, TimeUnit.MILLISECONDS);
    		if(isFilter){
    			RpcContext rpcContext = RpcContext.getContext();
    			String remoteAddress = rpcContext.getRemoteHost() +":"+rpcContext.getRemotePort();
    			message.append("ReqFilter[" + remoteAddress + "]调用频率过高!超出预设"+times+"ms内"
    				+ limits + "次的限制,接口将被禁用" + times + "ms,接口:"
    				+ apiName + "." + signatureName + getParams(method ,pJointPoint.getArgs()));
    			logger.error(message.toString());
            }
		} catch (Exception e) {
			logger.error("ReqFilter接口调用频率拦截前处理异常", e);
		}    
    	
    	try {
			logger.debug("ReqFilter接口调用频率拦截前置处理耗时：" + (System.currentTimeMillis() - startime) + " ms" + " ,clientKey=" + clientKey +" ,原始clientKey=" + apiName + "." + signatureName + "_" + DESEncrypt.decrypt(String.valueOf(spelValue)));
		} catch (Exception e) {
		}
    	// 超出限制频率，抛出异常
        if(isFilter){
			throw new WFXRuntimeException(ErrorCode.REQ_RATE_TOO_HIGH_ERROR, new Throwable(message.toString()));
        }
        return pJointPoint.proceed(); 
    }  

//    @AfterThrowing(value="intercepter()",throwing="ex")
//    public void afterThrowing(final JoinPoint joinPoint,Throwable ex) throws Throwable{    	
//		throw new MemberException(ex);
//    }
    
    /**
     * 获取方法参数字符串
     * @return
     */
    private String getParams(Method method, Object[] args){
    	if(args == null) return null;
    	LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer =
				new LocalVariableTableParameterNameDiscoverer();
    	String[] paramNames = parameterNameDiscoverer.getParameterNames(method);
    	StringBuffer pramBuf = new StringBuffer(128);
    	pramBuf.append("(");
    	for (int i = 0; i < paramNames.length; i++) {
    		pramBuf.append(paramNames[i])
    			   .append("=")
    			   .append(JSonUtil.ObjToJSON(args[i]));
    		if(i < paramNames.length -1){
    			pramBuf.append(",");
    		}
		}
    	pramBuf.append(")");
    	return pramBuf.toString();
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
		method = pjp.getTarget().getClass().getMethod(pjp.getSignature().getName(), argTypes);
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
		ExpressionParser parser = null;
		StandardEvaluationContext context;
		parser = new SpelExpressionParser();
		LocalVariableTableParameterNameDiscoverer parameterNameDiscoverer =
					new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = parameterNameDiscoverer.getParameterNames(method);
		context = new StandardEvaluationContext();
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}
	
	/**
	 * 控制api被调用频率
	 * <br> 如果被调用的次数超过指定次数，则对此调用者禁用指定时间，超过过期时间后，重新计算
	 * @param uniqueKey	唯一主键
	 * @param limits	调用限制次数
	 * @param times		过期时间
	 * @param unit		时间单位  参见:{@link java.util.concurrent.TimeUnit TimeUnit}
	 * @return <code>true: 表示超出限制，不通过；false: 表示未达到限制，通过</code>
	 */
	public boolean doFilter(String uniqueKey ,long limits ,long times ,TimeUnit unit ){
		if(limits == 0 || times == 0){
			return false;
		}
		try {
			RedisTemplate<String, String> redisTemplate =  SpringContextHolder.getBean("redisTemplate");
			Long cacheLimits = redisTemplate.opsForValue().increment(uniqueKey, 1);
			if(cacheLimits == 1){
				redisTemplate.expire(uniqueKey, times, unit);
			}
			if(cacheLimits == null) return true;
			if(cacheLimits.intValue() > limits){
				return true;
			}
			
			// 设置预警机制
//			if(StringUtils.isBlank(valueOperations.get()) && limits > 20 && (count.get()/limits)*100 >= 80){
//				String toAddress = "wu.y1@yougou.com";
//				IEmailSender emailSender = SpringContextHolder.getBean("emailSender");
//				StringBuffer buffer = new StringBuffer();
//				buffer.append("控制api被调用频率预警：当前api被调用总数=")
//					  .append(count)
//					  .append(" ,api调用限制总数=")
//					  .append(limits)
//					  .append(".");
//				MailSenderInfo mailInfo = new MailSenderInfo();
//				mailInfo.setTitle("控制api被调用频率监控");
//				mailInfo.setSubject(SubjectIdType.SUBJECT_ID_EMAIL_INVITATION_COMMENT);
//				mailInfo.setModelType(ModelType.MODEL_TYPE_INVITATION_COMMENT);
//				mailInfo.setContent(buffer.toString());
//				mailInfo.setToAddress(toAddress);
//				emailSender.sendByOperation(mailInfo);
//				
//				valueOperations.setIfAbsent(uniqueKey);
//				Calendar exprCalendar = Calendar.getInstance();
//				exprCalendar.set(Calendar.HOUR_OF_DAY, 23);
//				exprCalendar.set(Calendar.MINUTE, 59);
//				exprCalendar.set(Calendar.SECOND, 59);
//				valueOperations.expireAt(exprCalendar.getTime());
//			}
//			if((count.get() % 1000) == 0){
//				LoggerFactory.getLogger("FilterLogger").info("当前api被调用频率:uniqueKey="+uniqueKey+" ;count="+count);
//			}
		} catch (Exception e) {
			logger.error("控制api被调用频率redis发生异常:uniqueKey="+uniqueKey ,e);
		}
		return false;
	}
}
