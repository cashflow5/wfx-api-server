package com.yougou.wfx.finance.enums;

/**
 * 佣金结算状态枚举
 * @author he.xx
 * @Date 2016-04-22
 */
public enum FinCommissionStatusEnum {
	
	COMMISSON_UNSETTLEMENT(0, "未结算"),
	COMMISSON_SETTLEMENTED(1, "已结算"),
	COMMISSON_EXCEPTION(2, "异常挂起"),
	COMMISSON_CLOSED(3, "已关闭");
	
	private final Integer code;
	private final String desc;

	private FinCommissionStatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

	public static String getName(Integer code) {
		for (FinCommissionStatusEnum statusEnum : FinCommissionStatusEnum.values()) {
			if (statusEnum.getCode() == code) {
				return statusEnum.getDesc();
			}
		}
		return "";
	}
	
}
