package com.yougou.wfx.finance.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 分销商账户收支明细类型枚举
 * @author he.xx
 * @Date 2016-04-22
 */
public enum FinSellerAccountTranTypeEnum {
	
	ACCOUNT_TRAN_INCOME("1", "佣金收益"),
	ACCOUNT_TRAN_EXPAND("2", "提现");
	
	private final String code;
	private final String desc;
	
	private FinSellerAccountTranTypeEnum(String code, String desc) {
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
		for (FinSellerAccountTranTypeEnum typeEnum : FinSellerAccountTranTypeEnum.values()) {
			if (StringUtils.equals(typeEnum.getCode(), code)) {
				return typeEnum.getDesc();
			}
		}
		return "";
	}
	
}
