package com.yougou.wfx.framework.logger.jcl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.yougou.wfx.framework.logger.Logger;


/**
 * 适配CommonsLogging，依赖于commons-logging.jar
 * <br/>
 * 有关CommonsLogging详细信息请参阅：<a target="_blank" href="http://www.apache.org/">http://www.apache.org/</a>
 *
 * @author liangfei0201@163.com
 *
 */
public class JclLogger implements Logger, Serializable {

	private static final long serialVersionUID = 1L;

	private final Log logger;

	public JclLogger(Log logger) {
		this.logger = logger;
	}

    public void trace(String msg) {
        logger.trace(msg);
    }

    public void trace(Throwable e) {
        logger.trace(e);
    }

    public void trace(String msg, Throwable e) {
        logger.trace(msg, e);
    }

	public void debug(String msg) {
		logger.debug(msg);
	}

    public void debug(Throwable e) {
        logger.debug(e);
    }

	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
	}

	public void info(String msg) {
		logger.info(msg);
	}

    public void info(Throwable e) {
        logger.info(e);
    }

	public void info(String msg, Throwable e) {
		logger.info(msg, e);
	}

	public void warn(String msg) {
		logger.warn(msg);
	}

    public void warn(Throwable e) {
        logger.warn(e);
    }

	public void warn(String msg, Throwable e) {
		logger.warn(msg, e);
	}

	public void error(String msg) {
		logger.error(msg);
	}

    public void error(Throwable e) {
        logger.error(e);
    }

	public void error(String msg, Throwable e) {
		logger.error(msg, e);
	}

    public boolean isTraceEnabled() {
        return logger.isTraceEnabled();
    }

	public boolean isDebugEnabled() {
		return logger.isDebugEnabled();
	}

	public boolean isInfoEnabled() {
		return logger.isInfoEnabled();
	}

	public boolean isWarnEnabled() {
		return logger.isWarnEnabled();
	}

	public boolean isErrorEnabled() {
		return logger.isErrorEnabled();
	}

	@Override
	public void debug(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format,arg);
		logger.debug(ft.getMessage());
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		FormattingTuple ft = MessageFormatter.format(format,arg1,arg2);
		logger.debug(ft.getMessage());
	}

	@Override
	public void info(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format,arg);
		logger.info(ft.getMessage());
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		FormattingTuple ft = MessageFormatter.format(format,arg1,arg2);
		logger.info(ft.getMessage());
	}

	@Override
	public void warn(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format,arg);
		logger.warn(ft.getMessage());
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		FormattingTuple ft = MessageFormatter.format(format,arg1,arg2);
		logger.warn(ft.getMessage());
	}

	@Override
	public void error(String format, Object arg) {
		FormattingTuple ft = MessageFormatter.format(format,arg);
		logger.error(ft.getMessage());
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		FormattingTuple ft = MessageFormatter.format(format,arg1,arg2);
		logger.error(ft.getMessage());
	}
}
