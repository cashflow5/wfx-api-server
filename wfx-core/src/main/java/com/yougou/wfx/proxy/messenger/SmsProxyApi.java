/*
 * 版本信息

 * 日期 2016-03-24 17:16:57

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.proxy.messenger;

import com.yougou.component.sms.api.ISmsApi;
import com.yougou.component.sms.model.SmsVo;
import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * 短信发送API代理
 * 
 * @author wuyang
 * @Date 创建时间：2016-03-24 17:16:57
 */
public class SmsProxyApi {
	
	private final static Logger logger = LoggerFactory.getLogger(SmsProxyApi.class);
	
	/**
	 * 较实时发送（适用于短信验证码、注册成功、支付成功等场景）
	 * @param phones 手机号数组
	 * @param content 短信内容(不含短信签名)
	 * @param type 场景标识
	 * @throws Exception
	 */
	public static void sendNow(String[] phones,String content,String type) throws Exception{
		logger.debug(String.format("开始发送短信!phone:%s,content:%s,type:%s",phones, content, type));
		try{
			ISmsApi smsApi = SpringContextHolder.getBean(ISmsApi.class);
			SmsVo smsVo = new SmsVo();
			smsVo.setContent(content+SmsUtil.SMS_SIGN);
			smsVo.setPhone(phones);
			smsVo.setModelType("wfx-"+type);
	        smsApi.sendNow4WFX(smsVo);
		}catch(Exception ex){
			logger.error(String.format("发送短信时发送异常!phones:%s,content:%s,type:%s",phones, content, type), ex);
			throw ex;
		}
		logger.debug(String.format("发送短信成功!phone:%s,content:%s,type:%s",phones, content, type));
	}
	
	
	/**
	 * 延迟发送(9:00 ~ 20：00发送，适用于发货、退款等场景）
	 * @param phones 手机号数组
	 * @param content 短信内容(不含短信签名)
	 * @param type 场景标识
	 * @throws Exception
	 */
	public static void sendDelay(String[] phones,String content,String type) throws Exception{
		logger.debug(String.format("开始发送短信!phone:%s,content:%s,type:%s",phones, content, type));
		try{
			ISmsApi smsApi = SpringContextHolder.getBean(ISmsApi.class);
			SmsVo smsVo = new SmsVo();
			smsVo.setContent(content+SmsUtil.SMS_SIGN);
			smsVo.setPhone(phones);
			smsVo.setModelType("wfx-"+type);
	        smsApi.sendDelay4WFX(smsVo);
		}catch(Exception ex){
			logger.error(String.format("发送短信时发送异常!phones:%s,content:%s,type:%s",phones, content, type), ex);
			throw ex;
		}
		logger.debug(String.format("发送短信成功!phone:%s,content:%s,type:%s",phones, content, type));
	}
}
