/**
 * Copyright (c) 2005-2009 springside.org.cn
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * $Id: Log4jMBean.java 1113 2010-07-10 08:28:33Z calvinxiu $
 */
package com.yougou.wfx.framework.jmx;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于JMX动态配置Logger日志等级, 获取Logger Appender的MBean.
 * 
 * @author calvin
 */
public class Log4jMBean {

	/**
	 * Log4jMbean的注册名称.
	 */
	public static final String LOG4J_MBEAN_NAME = "SpringSide:name=log4j,type=Log4j";

	private static org.slf4j.Logger mbeanLogger = LoggerFactory.getLogger(Log4jMBean.class);
	
	public Log4jMBean() {
		
	}

	/**
	 * 获取Root Logger的日志级别.
	 */
	public String getRootLoggerLevel() {
		Logger root = Logger.getRootLogger();
		return root.getLevel().toString();
	}

	/**
	 * 设置Root Logger的日志级别.
	 */
	public void setRootLoggerLevel(String newLevel) {
		Logger root = Logger.getRootLogger();
		Level level = Level.toLevel(newLevel);
		root.setLevel(level);
		mbeanLogger.info("设置Root Logger级别到{}", newLevel);
	}

	/**
	 * 获取Logger的日志级别.
	 */
	public String getLoggerLevel(String loggerName) {
		Logger logger = Logger.getLogger(loggerName);
		return logger.getEffectiveLevel().toString();
	}

	/**
	 * 设置Logger的日志级别.
	 */
	public void setLoggerLevel(String loggerName, String newLevel) {
		Logger logger = Logger.getLogger(loggerName);
		Level level = Level.toLevel(newLevel);
		logger.setLevel(level);
		mbeanLogger.info("设置{}级别到{}", loggerName, newLevel);
	}

	/**
	 * 获取Root Logger的所有Appender的名称.
	 */
	public List<String> getRootLoggerAppenders() {
		Logger root = Logger.getRootLogger();
		return getLoggerAppenders(root);
	}

	/**
	 * 获取Logger的所有Appender的名称.
	 * 继承而来的Appender名称后将有(parent)标识.
	 */
	public List<String> getLoggerAppenders(String loggerName) {
		Logger logger = Logger.getLogger(loggerName);
		return getLoggerAppenders(logger);

	}

	/**
	 * 获取Logger的所有Appender的名称.
	 */
	@SuppressWarnings({"rawtypes" })
	private List<String> getLoggerAppenders(Logger logger) {
		List<String> appenderNameList = new ArrayList<String>();
		//循环加载logger及其parent的appenders
		for (Category c = logger; c != null; c = c.getParent()) {
			Enumeration e = c.getAllAppenders();
			while (e.hasMoreElements()) {
				Appender appender = (Appender) e.nextElement();
				String appenderName = appender.getName();
				if (c != logger) {//NOSONAR
					appenderName += "(parent)";
				}
				appenderNameList.add(appenderName);
			}

			//如果logger不继承parent的appender,则停止向上循环.
			if (!c.getAdditivity()) {
				break;
			}
		}

		return appenderNameList;
	}
}
