package com.yougou.wfx.enums;

public enum CarouseFigureTypeEnum {
	APP_SUPPLY_MARKET("APP_SUPPLY_MARKET", "APP货源市场"), 
	H5_INDEX("H5_INDEX", "H5首页");

	private String key;
	private String desc;

	CarouseFigureTypeEnum(String key, String desc) {
		this.key = key;
		this.desc = desc;
	}

	/**
	 * 传入key值，获取对应描述
	 * 
	 * @param state
	 * @return
	 */
	public static String getDescByKey(String keyValue) {

		if (null != keyValue) {
			for (CarouseFigureTypeEnum st : CarouseFigureTypeEnum.values()) {
				String key = st.getKey();
				if (keyValue.equals(key)) {
					return st.getDesc();
				}
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
