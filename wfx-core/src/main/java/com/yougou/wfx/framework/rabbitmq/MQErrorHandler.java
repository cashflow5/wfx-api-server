package com.yougou.wfx.framework.rabbitmq;

import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * MQ异常善后处理者
 */
@Component
public class MQErrorHandler implements ErrorHandler {

	private static Logger logger = LoggerFactory.getLogger(MQErrorHandler.class);
	
	@Override
	public void handleError(Throwable t) {
		logger.error("接收并处理消息时发生异常,exception={}", t); // error
	}

}
