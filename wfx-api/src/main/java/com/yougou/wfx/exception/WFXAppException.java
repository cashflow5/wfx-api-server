package com.yougou.wfx.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public class WFXAppException extends WFXException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 377652621380495002L;

	public WFXAppException(IErrorCode errorCode) {
		this.errorCode = errorCode;
	}
	
	public WFXAppException(IErrorCode errorCode ,Throwable cause) {
		super(cause);
		this.errorCode = errorCode;
	}
	
	public WFXAppException(String message, IErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public WFXAppException(String message, Throwable cause,
			IErrorCode errorCode) {
		super(message, cause);
		this.errorCode = errorCode;
	}

	@Override
	public String getMessage() {
		String message = super.getMessage();
		Throwable cause = getCause();
		if (cause != null) {
			message = message + ";nested Exception is " + cause;
		}
		return message;
	}
	
	@Override
	 public void printStackTrace(PrintStream s) {
       synchronized (s) {
           printStackTrace(new PrintWriter(s));
       }
   }

	@Override
   public void printStackTrace(PrintWriter s) { 
		StringBuilder stringBuilder = new StringBuilder("\n");
		stringBuilder.append("异常描述：");
		stringBuilder.append(errorCode.getMsg());
		stringBuilder.append("\n");
		stringBuilder.append("异常编码：");
		stringBuilder.append(errorCode.getNumber());
       synchronized (s) {
       	s.println(stringBuilder.toString());
        s.flush();
       }
   }

}
