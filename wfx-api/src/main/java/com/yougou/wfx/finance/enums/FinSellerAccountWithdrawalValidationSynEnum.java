package com.yougou.wfx.finance.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 分销商账户提现验证状态枚举
 * @author he.xx
 * @Date 2016-06-24
 */
public enum FinSellerAccountWithdrawalValidationSynEnum {
	
	STATE_SUCCESS("100", "操作成功"),
	STATE_FAILURE("500", "操作失败"),
	STATE_OVER_AMOUNT("200", "单次提现不能大于500"),
	STATE_OVER_MONTH_AMOUNT("300", "月累计提现不能大于30000"),
	STATE_NO_REMAIN_AMOUNT("400", "账户余额不足"),
	STATE_OVER_NUM("600", "超过提现次数，限一周一次");
	
	private final String code;
	private final String desc;
	
	private FinSellerAccountWithdrawalValidationSynEnum(String code, String desc) {
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
		for (FinSellerAccountWithdrawalValidationSynEnum statusEnum : FinSellerAccountWithdrawalValidationSynEnum.values()) {
			if (StringUtils.equals(statusEnum.getCode(), code)) {
				return statusEnum.getDesc();
			}
		}
		return "";
	}
	
}
