package com.yougou.wfx.finance.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 分销商账户提现状态枚举
 * @author he.xx
 * @Date 2016-04-25
 */
public enum FinSellerAccountWithdrawalStatusEnum {
	
	ACCOUNT_WITHDRAWAL_UNAUDIT("1", "待审核"),
	ACCOUNT_WITHDRAWAL_AUDITED("2", "已审核"),
	ACCOUNT_WITHDRAWAL_SUCCESS("3", "已提现"),
	ACCOUNT_WITHDRAWAL_REFUSED("4", "审核不通过"),
	ACCOUNT_WITHDRAWAL_CONFIRM("5", "已确认");
	
	private final String code;
	private final String desc;
	
	private FinSellerAccountWithdrawalStatusEnum(String code, String desc) {
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
		if (StringUtils.isNotBlank(code)) {
			for (FinSellerAccountWithdrawalStatusEnum statusEnum : FinSellerAccountWithdrawalStatusEnum.values()) {
				if (StringUtils.equals(statusEnum.getCode(), code)) {
					return statusEnum.getDesc();
				}
			}
		}
		return "";
	}
	
}
