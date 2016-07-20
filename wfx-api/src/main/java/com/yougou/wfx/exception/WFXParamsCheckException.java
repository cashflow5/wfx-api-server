package com.yougou.wfx.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import com.yougou.wfx.exception.format.ExceptionFormatter;
import com.yougou.wfx.exception.format.TextExceptionFormatter;

public class WFXParamsCheckException extends WFXRuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4860747877384304446L;
	
	public WFXParamsCheckException(IErrorCode errorCode) {
		super(errorCode);
	}
	
	public WFXParamsCheckException(IErrorCode errorCode ,Throwable cause) {
		super(errorCode, new Throwable(cause.getMessage()));
	}
	
	public WFXParamsCheckException(String message, Throwable cause) {
		super(message, cause);
	}

	public WFXParamsCheckException(String message) {
		super(message);
	}

	public WFXParamsCheckException(Throwable cause) {
		super(cause);
	}

	@Override
	public void printStackTrace(PrintStream s) {
		synchronized (s) {
			printStackTrace(new PrintWriter(s));
		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		synchronized (s) {
			ExceptionFormatter exceptionFormatter = new TextExceptionFormatter(
					this, errorCode);
			s.println(exceptionFormatter.Format());
			s.flush();
		}
	}

}
