package com.yougou.wfx.exception.format;


public abstract class ExceptionFormatter {
	private Exception exception;
	
	protected ExceptionFormatter(Exception exception){
		this.exception = exception;
	}
	
	public String Format(){
		StringBuffer msg=new StringBuffer();
//		msg.append(WriteDateTime());
		msg.append(WriteDescription());
		msg.append(WriteException(this.exception));
		return msg.toString();
	}
	
	protected abstract String WriteDescription();
//	protected abstract String WriteDateTime();
	protected abstract String WriteException(Exception exceptionType);
}
