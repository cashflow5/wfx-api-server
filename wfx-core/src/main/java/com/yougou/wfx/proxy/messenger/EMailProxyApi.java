/*
 * 版本信息

 * 日期 2016-03-24 17:16:57

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.proxy.messenger;

import com.yougou.component.email.api.IEmailApi;
import com.yougou.component.email.model.MailSenderInfo;
import com.yougou.component.email.model.ModelType;
import com.yougou.component.email.model.SubjectIdType;
import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * 邮件发送API代理
 * 
 * @author wuyang
 * @Date 创建时间：2016-03-24 17:16:57
 */
public class EMailProxyApi {
	
	private final static Logger logger = LoggerFactory.getLogger(EMailProxyApi.class);
	
	public static void sendEmail(String email,String title,String content,String type) throws Exception{
		logger.debug(String.format("开始发送邮件!email:%s,content:%s,type:%s",email, content, type));
		try{
			IEmailApi emailApi = SpringContextHolder.getBean(IEmailApi.class);
			MailSenderInfo ms = new MailSenderInfo();
            ms.setContent(content);
            ms.setToAddress(email);
            ms.setTitle(title);
            ms.setSubject(SubjectIdType.MODEL_TYPE_PROMOTION_GRANT_COUPON);
            ms.setModelType(ModelType.MODEL_TYPE_PROMOTION_GRANT_COUPON);
            emailApi.sendNow(ms);
		}catch(Exception ex){
			logger.error(String.format("发送邮件时发送异常!email:%s,content:%s,type:%s",email, content, type), ex);
			throw ex;
		}
		logger.debug(String.format("发送邮件成功!email:%s,content:%s,type:%s",email, content, type));
	}
}
