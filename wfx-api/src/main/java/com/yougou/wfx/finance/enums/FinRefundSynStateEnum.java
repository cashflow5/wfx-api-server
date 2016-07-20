package com.yougou.wfx.finance.enums;

/**
 * 创建退款单，返回状态枚举
 * @author he.xx
 * @Date 2016-04-22
 */
public enum FinRefundSynStateEnum {
	
	/** 处理成功 **/
	STATE_SUCCESS {
		public String getDesc() {
			return "处理成功";
		}

		public String getCode() {
			return "success";
		}
	},
	
	/** 处理失败 **/
	STATE_FAIL {
		public String getDesc() {
			return "处理失败";
		}

		public String getCode() {
			return "fail";
		}
	},
	
	/** 接口出现异常 **/
	STATE_ERROR {
		public String getDesc() {
			return "接口出现异常";
		}

		public String getCode() {
			return "error";
		}
	};
	
	public abstract String getDesc();

	public abstract String getCode();
	
}
