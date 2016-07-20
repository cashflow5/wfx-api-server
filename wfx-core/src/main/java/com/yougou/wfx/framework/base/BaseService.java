package com.yougou.wfx.framework.base;

import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;


/**
 * Base Service
 * @author 吴阳
 */
public abstract class BaseService {
	protected final Logger logger = LoggerFactory.getLogger(super.getClass().getName());
}
