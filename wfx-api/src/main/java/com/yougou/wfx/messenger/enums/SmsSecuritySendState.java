 /*
 * 版本信息
 
 * 日期 2016-03-30 16:45:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.messenger.enums;

/**
 * 验证码发送状态
 * @author wuyang
 * @Date 创建时间：2016-03-30 16:45:57
 */
public enum SmsSecuritySendState {	
	PARAM_NULL(1,"参数存在空值"),
	PHONE_PATTERN_ERROR(2,"手机号码格式有误"),
	LIMIT_ONE_MINUTE(3,"1分钟内不可重复获取验证码"),
	LIMIT_IP(4,"同一IP不可频繁获取验证码"),
	LIMIT_24_HOUR(5,"24小时内不可超过系统限制次数获取验证码"),
	SEND_EXCEPTION(6,"发生短信时发生异常"),
	SEND_SUCCESS(7,"验证码已成功发送");
	
	private final int code;
	private final String msg;

	private SmsSecuritySendState(int code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
