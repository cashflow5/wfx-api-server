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
 * 验证码验证状态
 * @author wuyang
 * @Date 创建时间：2016-03-30 16:45:57
 */
public enum SmsSecurityCheckState {	
	PARAM_NULL(1,"参数存在空值"),
	EXPIRED(2,"验证码已过期"),
	LIMIT_CHECK(3,"多次尝试验证不正确，验证码已失效"),
	CAPTCHA_ERROR(4,"验证码不正确"),
	CHECK_SUCCESS(5,"验证码校验成功");
	
	private final int code;
	private final String msg;

	private SmsSecurityCheckState(int code,String msg) {
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