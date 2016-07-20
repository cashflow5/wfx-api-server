package com.yougou.wfx.exception;


public enum ErrorCode implements IErrorCode {
	
	VALUE_REQUIRED(1001,"字段必须有值"),
	INVALID_FORMAT(1002,"验证格式异常"),
	PARAM_IS_NULL(1003,"参数为空"),
	VALUE_TOO_LONGS(1004,"值长度过长"),
	INVALID_USER(1005,"非法用户"),
	MULTI_SUBMIT(1006,"重复提交操作"),
	TOKEN_ERROR(1007,"非法访问,token异常!"),
	IP_LIMIT_ERROR(1008,"非法访问,ip访问超过限定次数!"),
	REQ_RATE_TOO_HIGH_ERROR(1009,"非法访问,请求频率过高!"),
	REQ_METHOD_ERROR(1010,"非法访问,请求方法错误!"),
	REFERER_ERROR(1011,"非法访问,来路异常!"),
	CARDS_NUMBER_ERROR(1012,"非法访问,身份证格式异常!");
	
	private final int number;
	private final String errorMsg;

	private ErrorCode(int number,String errorMsg) {
		this.number = number;
		this.errorMsg = errorMsg;
	}
	
	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public String getMsg() {
		return errorMsg;
	}

}
