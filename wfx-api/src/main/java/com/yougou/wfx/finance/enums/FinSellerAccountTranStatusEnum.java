package com.yougou.wfx.finance.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 分销商账户收支明细状态枚举
 * @author he.xx
 * @Date 2016-04-22
 */
public enum FinSellerAccountTranStatusEnum {
	
	ACCOUNT_TRAN_SUCCESS("1", "交易成功"),
	ACCOUNT_TRAN_PROCESSING("2", "处理中"),
	ACCOUNT_TRAN_CLOSED("3", "交易关闭"),
	ACCOUNT_TRAN_FAILURE("4", "交易失败");
	
	private final String code;
	private final String desc;
	
	private FinSellerAccountTranStatusEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	public static String getName(String code) {
		for (FinSellerAccountTranStatusEnum statusEnum : FinSellerAccountTranStatusEnum.values()) {
			if (StringUtils.equals(statusEnum.getCode(), code)) {
				return statusEnum.getDesc();
			}
		}
		return "";
	}
	
}
