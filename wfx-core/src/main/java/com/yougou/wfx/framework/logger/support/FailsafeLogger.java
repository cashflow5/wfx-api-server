/*
 * Copyright 1999-2011 Alibaba Group.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yougou.wfx.framework.logger.support;

import com.alibaba.dubbo.rpc.RpcContext;
import com.yougou.wfx.framework.logger.Logger;


public class FailsafeLogger implements Logger {

	private Logger logger;

	public FailsafeLogger(Logger logger) {
		this.logger = logger;
	}

	public Logger getLogger() {
		return logger;
	}

	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	private String appendContextMessage(String msg) {
		String strAddress = "";
		try{
	        RpcContext rpcContext = RpcContext.getContext();
	        String strRemoteHost = rpcContext.getRemoteHost();
	        int strRemotePort = rpcContext.getRemotePort();
	        
	        if(strRemotePort != 0){
	        	strAddress = strRemoteHost + ":" + strRemotePort +",";
	        }
		}catch(Exception ex){
		}
		
	    return "[wfx-api]"+ strAddress + msg;
	}

    public void trace(String msg, Throwable e) {
        try {
            logger.trace(appendContextMessage(msg), e);
        } catch (Throwable t) {
        }
    }

    public void trace(Throwable e) {
        try {
            logger.trace(e);
        } catch (Throwable t) {
        }
    }

    public void trace(String msg) {
        try {
            logger.trace(appendContextMessage(msg));
        } catch (Throwable t) {
        }
    }

	public void debug(String msg, Throwable e) {
		try {
			logger.debug(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

    public void debug(Throwable e) {
        try {
            logger.debug(e);
        } catch (Throwable t) {
        }
    }

	public void debug(String msg) {
		try {
			logger.debug(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	public void info(String msg, Throwable e) {
		try {
			logger.info(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}
	
	public void info(String msg) {
		try {
			logger.info(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	public void warn(String msg, Throwable e) {
		try {
			logger.warn(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	public void warn(String msg) {
		try {
			logger.warn(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

	public void error(String msg, Throwable e) {
		try {
			logger.error(appendContextMessage(msg), e);
		} catch (Throwable t) {
		}
	}

	public void error(String msg) {
		try {
			logger.error(appendContextMessage(msg));
		} catch (Throwable t) {
		}
	}

    public void error(Throwable e) {
        try {
            logger.error(e);
        } catch (Throwable t) {
        }
    }

    public void info(Throwable e) {
        try {
            logger.info(e);
        } catch (Throwable t) {
        }
    }

    public void warn(Throwable e) {
        try {
            logger.warn(e);
        } catch (Throwable t) {
        }
    }

    public boolean isTraceEnabled() {
        try {
            return logger.isTraceEnabled();
        } catch (Throwable t) {
            return false;
        }
    }

	public boolean isDebugEnabled() {
		try {
			return logger.isDebugEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean isInfoEnabled() {
		try {
			return logger.isInfoEnabled();
		} catch (Throwable t) {
			return false;
		}
	}

	public boolean isWarnEnabled() {
		try {
			return logger.isWarnEnabled();
		} catch (Throwable t) {
			return false;
		}
	}
	
	public boolean isErrorEnabled() {
	    try {
	        return logger.isErrorEnabled();
	    } catch (Throwable t) {
	        return false;
	    }
	}

	@Override
	public void debug(String format, Object arg) {
		try {
			logger.debug(appendContextMessage(format),arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public void debug(String format, Object arg1, Object arg2) {
		try {
			logger.debug(appendContextMessage(format),arg1,arg2);
		} catch (Throwable t) {
		}
	}

	@Override
	public void info(String format, Object arg) {
		try {
			logger.info(appendContextMessage(format),arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public void info(String format, Object arg1, Object arg2) {
		try {
			logger.info(appendContextMessage(format),arg1,arg2);
		} catch (Throwable t) {
		}
	}

	@Override
	public void warn(String format, Object arg) {
		try {
			logger.warn(appendContextMessage(format),arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public void warn(String format, Object arg1, Object arg2) {
		try {
			logger.warn(appendContextMessage(format),arg1,arg2);
		} catch (Throwable t) {
		}
	}

	@Override
	public void error(String format, Object arg) {
		try {
			logger.error(appendContextMessage(format),arg);
		} catch (Throwable t) {
		}
	}

	@Override
	public void error(String format, Object arg1, Object arg2) {
		try {
			logger.error(appendContextMessage(format),arg1,arg2);
		} catch (Throwable t) {
		}
	}
}