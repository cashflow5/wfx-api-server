package com.yougou.wfx.finance.enums;

/**
 * 退款日志枚举
 * @author he.xx
 */
public enum FinRefundOperateEnum {
	REFUND_CREATE("创建退款单", 1),
	REFUND_MODIFY("修改退款单", 2),
	REFUND_CLOSE("关闭退款单", 3),
	REFUND_CONFIRM("同意退款", 4),
	REFUND_REFUSE("拒绝退款", 5);
	
	private final Integer code;
	private final String desc;
	
	private FinRefundOperateEnum(String desc, Integer code) {
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
		for (FinRefundOperateEnum refundTypeEnum : FinRefundOperateEnum.values()) {
			if (refundTypeEnum.getCode() == code) {
				return refundTypeEnum.getDesc();
			}
		}
		return "";
	}
	
}
