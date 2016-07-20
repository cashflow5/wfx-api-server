package com.yougou.wfx.messenger.enums;

/**
 * 是否清除短信验证码枚举
 * @author zhang.f1
 *
 */
public enum SmsCodeDeleteState {
	DELETE_CODE("DELETE","清除验证码"),
	NOT_DELETE_CODE("NOT_DELETE","不清楚验证码");
	
	private final String code;
	private final String msg;

	private SmsCodeDeleteState(String code,String msg) {
		this.code = code;
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
