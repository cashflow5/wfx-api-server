package com.yougou.wfx.exception;

import java.util.ArrayList;
import java.util.List;



public class WFXRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -461644299236694571L;
	
	protected IErrorCode errorCode;
	protected List<String> errorstackTraces;

	public WFXRuntimeException(IErrorCode errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public WFXRuntimeException(IErrorCode errorCode ,Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
		if(cause.getStackTrace() != null){
			if(errorstackTraces == null){
				errorstackTraces = new ArrayList<String>(20);
			}
			StackTraceElement[] traceElements = cause.getStackTrace();
			for (int i = 0; i < traceElements.length; i++) {
				errorstackTraces.add(traceElements[i].toString());
				if(i > 12){
					break;
				}
			}
//			StackTraceElement[] traceElements_this = this.getStackTrace();
//			for (int i = 0; i < traceElements_this.length; i++) {
//				errorstackTraces.add(traceElements_this[i].toString());
//				if(i > 12){
//					break;
//				}
//			}
		}else{
			errorstackTraces = new ArrayList<String>(20);
		}
	}
	
	public WFXRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public WFXRuntimeException(String message) {
		super(message);
	}

	public WFXRuntimeException(Throwable cause) {
		super(cause);
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(IErrorCode errorCode) {
		this.errorCode = errorCode;
	}

	public List<String> getErrorstackTraces() {
		return errorstackTraces;
	}

	public void setErrorstackTraces(List<String> errorstackTraces) {
		this.errorstackTraces = errorstackTraces;
	}

}
