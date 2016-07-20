package com.yougou.wfx.exception;


public class WFXException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2322478954247153563L;
	
	protected IErrorCode errorCode;

	public WFXException() {
		super();
	}

	public WFXException(String message, Throwable cause) {
		super(message, cause);
	}

	public WFXException(String message) {
		super(message);
	}

	public WFXException(Throwable cause) {
		super(cause);
	}

	public IErrorCode getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(IErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
}
