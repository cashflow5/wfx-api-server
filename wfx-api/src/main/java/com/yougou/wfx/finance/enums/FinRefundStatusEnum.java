package com.yougou.wfx.finance.enums;

/**
 * 财务退款专用状态枚举（对接支付中心）
 * @author he.xx
 * @Date 2016-04-22
 */
public enum FinRefundStatusEnum {
	
	待确认退款(2),
	退款处理中(3),
	退款成功(4),
	退款失败(5),
	拒绝退款(6),
	退款关闭(7);
	
	private final Integer code;

	public Integer getCode() {
		return code;
	}

	private FinRefundStatusEnum(Integer code) {
		this.code = code;
	}
	
	public static String getName(Integer code) {
		if (code != null) {
			for (FinRefundStatusEnum refundStatusEnum : FinRefundStatusEnum.values()) {
				if (refundStatusEnum.getCode() == code) {
					return refundStatusEnum.toString();
				}
			}
		}
		return "";
	}
	
}
