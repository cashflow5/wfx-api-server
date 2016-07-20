package com.yougou.wfx.exception.format;

import java.util.List;

import com.yougou.wfx.exception.IErrorCode;
import com.yougou.wfx.exception.WFXRuntimeException;

public class TextExceptionFormatter extends ExceptionFormatter{

	private Exception exception;
	private IErrorCode errorCode;
	private List<String> errorstackTraces;
	
	public TextExceptionFormatter(Exception exception) {
		super(exception);
		this.exception = exception;
	}
	
	public TextExceptionFormatter(Exception exception,IErrorCode errorCode) {
		super(exception);
		this.exception = exception;
		this.errorCode = errorCode;
	}

//	@Override
//	protected String WriteDateTime() {
//		StringBuilder stringBuilder = new StringBuilder("\n");
//		stringBuilder.append("异常发生时间");
//		stringBuilder.append(":");
//		stringBuilder.append(DateUtil.formatDate(new Date(), DateUtil.LONG_DATE_TIME_PATTERN));
//		return stringBuilder.toString() ;
//	}

	@Override
	protected String WriteDescription() {
		StringBuilder stringBuilder = new StringBuilder("\n");
		stringBuilder.append("异常简述：");
		stringBuilder.append(errorCode != null ? errorCode.getMsg() : "");
		stringBuilder.append("\n");
		stringBuilder.append("异常编码：");
		stringBuilder.append(errorCode != null ? errorCode.getNumber() : "");
		if(this.exception.getMessage() != null && this.exception.getMessage() != ""){
			stringBuilder.append("\n");
			stringBuilder.append("异常详情：");
			stringBuilder.append(this.exception.getMessage());
		}
		return stringBuilder.toString() ;
	}

	@Override
	protected String WriteException(Exception exceptionType) {
		StringBuilder stringBuilder = new StringBuilder("\n");
		if(exceptionType instanceof WFXRuntimeException){
			WFXRuntimeException myException = (WFXRuntimeException) exceptionType ;
			errorstackTraces = myException.getErrorstackTraces();
		}
		if(errorstackTraces != null && errorstackTraces.size() > 0){
			int errorPointsSize = errorstackTraces.size();
			stringBuilder.append("异常栈：");
			for (int i = 0 ; i < errorPointsSize ; i++) {
				stringBuilder.append(errorstackTraces.get(i));
				stringBuilder.append("\n");
			}
		}
		return stringBuilder.toString();
	}

}
