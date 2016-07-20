 /*
 * 版本信息
 
 * 日期 2016-03-28 17:16:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.messenger.api.front;

import com.yougou.wfx.dto.base.UserContext;
import com.yougou.wfx.messenger.enums.SmsSecurityCheckState;
import com.yougou.wfx.messenger.enums.SmsSecuritySendState;

/**
 * 消息类api接口(短信、邮件等)
 * @author wuyang
 * @Date 创建时间：2016-03-28 17:16:57
 */
public interface IMessengerFrontApi {
	
	/**
	 * 发送短信验证码
	 * @param phone 手机号码
	 * @param context 用户上下文
	 * @return
	 */
	SmsSecuritySendState sendPhoneCode(String phone,UserContext context);
	
	/**
	 * 验证短信验证码
	 * @param phone 手机号
	 * @param code 短信验证码
	 * @param context 用户上下文(clientIP、sessionId必填)
	 * @return
	 */
	SmsSecurityCheckState checkPhoneCode(String phone,String code,UserContext context);
	
	/**
	 * 发送短信
	 * @param phones 手机号码数组
	 * @param content 短信内容
	 */
	void sendSms(String[] phones,String content);
	
	/**
	 * 发送邮件
	 * @param email 邮箱地址
	 * @param title 邮件标题
	 * @param content 正文
	 */
	void sendEmail(String email,String title,String content);
}
