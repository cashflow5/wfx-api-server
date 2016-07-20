package com.yougou.wfx.dto.base;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class WFXResult<T> extends OutputDto {

	private static final long serialVersionUID = 1L;
	
	private int resultCode;
	
	private String resultMsg;
	
	private T result;

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public T getResult() {
		return result;
	}

	public void setResult(T result) {
		this.result = result;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
