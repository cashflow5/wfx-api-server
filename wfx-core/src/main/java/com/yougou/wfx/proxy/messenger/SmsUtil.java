 /*
 * 版本信息
 
 * 日期 2016-03-28 17:16:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.proxy.messenger;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.dto.base.UserContext;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.verify.PhoneVerifyUtil;
import com.yougou.wfx.messenger.enums.SmsCodeDeleteState;
import com.yougou.wfx.messenger.enums.SmsSecurityCheckState;
import com.yougou.wfx.messenger.enums.SmsSecuritySendState;

/**
 * 短信验证码发送、验证工具类
 * @author wuyang
 * @Date 创建时间：2016-03-28 17:16:57
 */
public class SmsUtil{
	
	private final static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
	
	// 一分钟不可重复获取验证码
	private final static String LIMIT_ONE_MINUTE_KEY = "LIMIT_ONE_MINUTE_KEY:";
	// 同ip24小时内不得超过500次
	private final static String LIMIT_IP_KEY = "LIMIT_IP_KEY:";
	// 同手机号码24小时内不得超过5次
	private final static String LIMIT_24_HOUR_KEY = "LIMIT_24_HOUR_KEY:";
	// 验证码
	private final static String CAPTCHA_CODE_KEY = "CAPTCHA_CODE_KEY:";
	// 验证码校验错误次数
	private final static String CHECK_INVALID_COUNT = "CHECK_INVALID_COUNT:";
	// 短信签名(【优购微分销】、【优购微店】、【优分销】、【优购微零售】、【百丽微分销】、【百丽微零售】)
	public final static String SMS_SIGN = "【优购微零售】";
	// 验证码短信模板
	private final static String SMS_CODE_TEMPLATE = "亲爱的优购家人，您的验证码为：%s，有效时间为%s分钟，请确认该申请是由您本人操作，感谢您的光临。";
	//不做限制的请求IP(内网IP)
	private final static String[] NOT_LIMIT_IP_START = {"10.0.20","10.0.30","192.168.211","0:0:0","10.0.60","127.0.0.1"};
	//系统配置项24小时同一个手机号验证码限制次数
	private final static String LIMIT_24_HOUR_SYS_KEY = "wfx.member.phone.valicode.limit";
	//系统默认24小时同一个手机号验证码限制次数
	private final static Integer LIMIT_24_HOUR_COUNT = 10;
	//系统配置项短信验证码有效时间
	private final static String SMS_CODE_VALID_TIME_SYS_KEY = "wfx.member.phone.valicode.time";
	//系统默认短信验证码有效时间15分钟
	private final static Integer SMS_CODE_VALID_TIME = 15;
	
	/**
	 * 校验是否是需要限制短信验证码发送次数的IP
	 * @param ip
	 * @return true:需要限制 ，false:不需要限制
	 */
	private static boolean checkLimitIp(String ip){
		if(StringUtils.isNotBlank(ip)){
			for(String ipStart : NOT_LIMIT_IP_START){
				if(ip.startsWith(ipStart)){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * 获取系统内24小时内手机号短信验证码限制次数
	 * @return
	 */
	private static int get24HourPhoneLimit(){
		try{
			ISysConfigService sysConfigService = SpringContextHolder.getBean(ISysConfigService.class);
			String limit = sysConfigService.getValueBykey(LIMIT_24_HOUR_SYS_KEY);
		
			int count = Integer.parseInt(limit);
			return count;
		}catch(Exception e){
			logger.error("获取24小时同一个手机号短信验证码系统限制次数，转换数据异常： " , e);
		}
		return LIMIT_24_HOUR_COUNT;
	}
	
	/**
	 * 获取短信验证码有效时长配置项
	 * @return
	 */
	private static int getValicodeTime(){
		try{
			ISysConfigService sysConfigService = SpringContextHolder.getBean(ISysConfigService.class);
			String value = sysConfigService.getValueBykey(SMS_CODE_VALID_TIME_SYS_KEY);
		
			int validTime = Integer.parseInt(value);
			return validTime;
		}catch(Exception e){
			logger.error("获取24小时同一个手机号短信验证码系统限制次数，转换数据异常： " , e);
		}
		return SMS_CODE_VALID_TIME;
	}
	
	/**
	 * 发送手机验证码
	 * @param phone 手机号(必填)
	 * @param content 内容(待定)
	 * @param context 用户上下文(clientIP、sessionId必填)
	 * @return
	 */
	public static SmsSecuritySendState sendPhoneCode(String phone,UserContext context){
		try {
			Assert.hasText(phone, "paramter 'phone' must not be empty");
//			Assert.hasText(content, "paramter 'content' must not be empty");
			Assert.notNull(context, "paramter 'context' must not be null");
			Assert.hasText(context.getClientIp(), "paramter 'ip' must not be empty");
			Assert.hasText(context.getSessionId(), "paramter 'sessionId' must not be empty");
		} catch (Exception e) {
			logger.error("参数存在空值");
			return SmsSecuritySendState.PARAM_NULL;
		}
		phone = phone.trim(); //手机号
        String ip = context.getClientIp();//客户端ip
        String sessionId = context.getSessionId();//会话id
		try { 
			// 校验验证
			SmsSecuritySendState smsSecuritySendState = validLimits(phone,ip,sessionId);
			if (smsSecuritySendState != null) {
				return smsSecuritySendState;
			}
			
			// 生成验证码
			String captchaCodeKey = CAPTCHA_CODE_KEY + phone;
			String code = "";
			String oldCode = WFXRedisUtil.getString(WFXModuleType.SECURITY, captchaCodeKey);
			int validTime = getValicodeTime();		
			if (StringUtils.isNotBlank(oldCode)) {
				code = oldCode;
			} else {
				code = buildNumber(4);
				WFXRedisUtil.set(WFXModuleType.SECURITY, captchaCodeKey,code,validTime,TimeUnit.MINUTES);// 验证码15分钟内有效
			}
			
			// 立即发送短信验证码
			SmsProxyApi.sendNow(new String[] { phone }, String.format(SMS_CODE_TEMPLATE, code,validTime), "captcha");
			
		    // 发送统计
			statSend(phone,ip,sessionId);
		} catch (Exception ex) {
			logger.error("发送手机验证信息异常，用户手机号码: "+ phone +"  第三方返回状态： " , ex);
			return SmsSecuritySendState.SEND_EXCEPTION;
		}
		return SmsSecuritySendState.SEND_SUCCESS;
	}
	
	/**
	 * 验证短信验证码
	 * @param phone 手机号(必填)
	 * @param code 短信验证码(必填)
	 * @param context 用户上下文(clientIP、sessionId必填)
	 * @return
	 */
	public static SmsSecurityCheckState checkPhoneCode(String phone,String code,UserContext context,String deleteState) {
		try {
			Assert.hasText(phone, "paramter 'phone' must not be empty");
			Assert.hasText(code, "paramter 'code' must not be empty");
			Assert.notNull(context, "paramter 'context' must not be null");
			Assert.hasText(context.getClientIp(), "paramter 'ip' must not be empty");
			Assert.hasText(context.getSessionId(), "paramter 'sessionId' must not be empty");
		} catch (Exception e) {
			logger.error("参数存在空值");
			return SmsSecurityCheckState.PARAM_NULL;
		}
		
		String oldCode = WFXRedisUtil.getString(WFXModuleType.SECURITY, CAPTCHA_CODE_KEY + phone);
		// 过期
		if (StringUtils.equals(code,oldCode)) {
			// 校验成功删除验证码和相关记录
			if(SmsCodeDeleteState.DELETE_CODE.getCode().equals(deleteState)){
				WFXRedisUtil.delete(WFXModuleType.SECURITY, CAPTCHA_CODE_KEY + phone);
				WFXRedisUtil.delete(WFXModuleType.SECURITY, CHECK_INVALID_COUNT + phone);
			}
			// 验证码校验成功
			return SmsSecurityCheckState.CHECK_SUCCESS;
		}else if (StringUtils.isEmpty(oldCode)) {
			return SmsSecurityCheckState.EXPIRED;
		} else if (!StringUtils.equals(code, oldCode)) {
			// 历史错误次数
		    int lastErrCount = WFXRedisUtil.getInt(WFXModuleType.SECURITY, CHECK_INVALID_COUNT + phone);
		    lastErrCount ++;
		    // 超过五次验证失败就将验证码失效
		    if(lastErrCount >= 5){
		    	WFXRedisUtil.delete(WFXModuleType.SECURITY, CAPTCHA_CODE_KEY + phone);
		    	return SmsSecurityCheckState.LIMIT_CHECK;
		    }
	        WFXRedisUtil.set(WFXModuleType.SECURITY, CHECK_INVALID_COUNT + phone,lastErrCount,15,TimeUnit.MINUTES);
			// 验证码不一致
			return SmsSecurityCheckState.CAPTCHA_ERROR;
		} else {
			return null;
		}
	}
	
	/**
	 * 发送前校验
	 */
	private static SmsSecuritySendState validLimits(String phone,String ip,String sessionId){
		//非内网IP进行验证
		if(checkLimitIp(ip)){
	        // 验证手机格式
			if (!PhoneVerifyUtil.validMobile(phone)) {
				logger.error(String.format("手机号码格式不正确!phone:%s,ip:%s,sessonId:%s",phone, ip, sessionId));
				return SmsSecuritySendState.PHONE_PATTERN_ERROR;
			}
			
			// 同一手机号1分钟内不可重复获取验证码
			String objTime = WFXRedisUtil.getString(WFXModuleType.SECURITY, LIMIT_ONE_MINUTE_KEY + phone);
			if(StringUtils.isNotBlank(objTime)){
				long minDuplication = 1; // 1分钟内获取次数
				long interval = Calendar.getInstance().getTimeInMillis() - Long.valueOf(objTime);
				if (interval / 60000 < minDuplication) {
					logger.error(String.format("同一手机号1分钟内不可重复获取验证码!phone:%s,ip:%s,sessonId:%s",phone, ip, sessionId));
					return SmsSecuritySendState.LIMIT_ONE_MINUTE;
				}
			}
			// 同一IP不可超过500次验证码
			int curIpCount = WFXRedisUtil.getInt(WFXModuleType.SECURITY, LIMIT_IP_KEY + ip);
			int maxIpCount = 500;
			if (curIpCount >= maxIpCount) {
				logger.error(String.format("同一IP不可超过次%d验证码!phone:%s,ip:%s,sessonId:%s,curCount:%d",maxIpCount,phone, ip, sessionId,curIpCount));
				return SmsSecuritySendState.LIMIT_IP;
			}
			
				
			
			// 同一手机号24小时之内不可超过10次获取验证码
			int hour24Count = WFXRedisUtil.getInt(WFXModuleType.SECURITY,LIMIT_24_HOUR_KEY + phone);
			int hourLimit = get24HourPhoneLimit();
			/*if(hourLimit == 0 ){
				hourLimit = LIMIT_24_HOUR_COUNT; //默认不超过10次
			}*/
			if (hour24Count >= hourLimit) {
				logger.error(String.format("24小时内不可超过%s次获取验证码!phone:%s,ip:%s,sessonId:%s,curCount:%d",hourLimit,phone, ip, sessionId,hour24Count));
				return SmsSecuritySendState.LIMIT_24_HOUR;
			}
		}
		return null;
	}
	
	/**
	 * 将验证的次数放入缓存
	 */
	private static void statSend(String phone,String ip,String sessionId){
		//非内网IP进行限制次数统计
		if(checkLimitIp(ip)){ 
			// 同一手机号1分钟内不可重复获取验证码
			String objTime = WFXRedisUtil.getString(WFXModuleType.SECURITY, LIMIT_ONE_MINUTE_KEY + phone);
			if(StringUtils.isBlank(objTime)){
				String time = String.valueOf(Calendar.getInstance().getTimeInMillis());
				WFXRedisUtil.set(WFXModuleType.SECURITY, LIMIT_ONE_MINUTE_KEY + phone, time, 1, TimeUnit.MINUTES);
			}
			// 同一IP不可超过500次验证码
			int curIpCount = WFXRedisUtil.getInt(WFXModuleType.SECURITY, LIMIT_IP_KEY + ip);
			if (curIpCount == 0) {
				WFXRedisUtil.set(WFXModuleType.SECURITY, LIMIT_IP_KEY + ip, 1, 1, TimeUnit.DAYS);
			}else{
				curIpCount++;
				WFXRedisUtil.set(WFXModuleType.SECURITY, LIMIT_IP_KEY + ip, curIpCount, 1, TimeUnit.DAYS);
			}
			// 同一手机号24小时之内不可超过10次获取验证码
			int hour24Count = WFXRedisUtil.getInt(WFXModuleType.SECURITY,LIMIT_24_HOUR_KEY + phone);
			if (hour24Count == 0) {
				WFXRedisUtil.set(WFXModuleType.SECURITY, LIMIT_24_HOUR_KEY + phone, 1, 1, TimeUnit.DAYS);
			}else{
				hour24Count++;
				WFXRedisUtil.set(WFXModuleType.SECURITY, LIMIT_24_HOUR_KEY + phone, hour24Count, 1, TimeUnit.DAYS);
			}
		}
	}
	
	/** 根据时间生成唯一编号 */
	private static String buildNumber(int length){
    	long time = System.currentTimeMillis();
    	
    	Random random = new Random();
    	StringBuffer buffer = new StringBuffer().append(time);
    	String result = "";
    	if(buffer.length() >= length){
    	    buffer = new StringBuffer();
    	    for (int i = 0; i < length; i++) {
                buffer.append(random.nextInt(9));
            }
    	}else{
    		for (int i = 0; i < (length - buffer.length()); i++) {
    			buffer.append(random.nextInt(9));
			}
    	}
    	result = buffer.toString();
    	return result;
    }
}
