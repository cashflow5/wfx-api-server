package com.yougou.wfx.finance.enums;

public enum FinRefundTypeEnum {
	REFUND_ONLY("仅退款", 1),
	REFUND_RETURNGOODS("退货退款", 2);
	
	private final Integer code;
	private final String desc;
	
	private FinRefundTypeEnum(String desc, Integer code) {
		this.desc = desc;
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}
	public String getDesc() {
		return desc;
	}
	
	public static String getDesc(Integer code) {
		for (FinRefundTypeEnum refundTypeEnum : FinRefundTypeEnum.values()) {
			if (refundTypeEnum.getCode() == code) {
				return refundTypeEnum.getDesc();
			}
		}
		return "";
	}
	
}
