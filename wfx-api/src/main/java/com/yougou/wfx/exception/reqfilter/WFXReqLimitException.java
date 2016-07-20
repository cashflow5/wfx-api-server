package com.yougou.wfx.exception.reqfilter;

import java.io.Serializable;

import com.yougou.wfx.exception.IErrorCode;
import com.yougou.wfx.exception.WFXAppException;

/**
 * 请求超过限制异常
 */
public class WFXReqLimitException extends WFXAppException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public WFXReqLimitException(IErrorCode errorCode) {
		super(errorCode);
	}
	
	public WFXReqLimitException(String message,IErrorCode errorCode) {
		super(message,errorCode);
	}
	
	public WFXReqLimitException(String message, Throwable cause,IErrorCode errorCode) {
		super(message,cause,errorCode);
	}
}