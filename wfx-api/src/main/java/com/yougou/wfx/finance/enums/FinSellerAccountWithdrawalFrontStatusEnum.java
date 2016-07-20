package com.yougou.wfx.finance.enums;

import org.apache.commons.lang3.StringUtils;

public enum FinSellerAccountWithdrawalFrontStatusEnum {
	ACCOUNT_WITHDRAWAL_UNAUDIT("1", "提现中"),
	ACCOUNT_WITHDRAWAL_AUDITED("2", "提现中"),
	ACCOUNT_WITHDRAWAL_SUCCESS("3", "已提现"),
	ACCOUNT_WITHDRAWAL_FAILURE("4", "提现失败");
	
	private final String code;
	private final String desc;
	
	private FinSellerAccountWithdrawalFrontStatusEnum(String code, String desc) {
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
		for (FinSellerAccountWithdrawalStatusEnum statusEnum : FinSellerAccountWithdrawalStatusEnum.values()) {
			if (StringUtils.equals(statusEnum.getCode(), code)) {
				return statusEnum.getDesc();
			}
		}
		return "";
	}
	
}
