package com.yougou.wfx.outside.response;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * <p>Title: BaseResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public abstract class BaseResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	private int resultCode;
	private String resultMsg;

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
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
