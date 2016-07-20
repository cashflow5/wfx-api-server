package com.yougou.wfx.enums;

public enum SellerLevelEnum {
	LEVEL_ONE("1","一级分销商"),
	LEVEL_TWO("2","二级分销商"),
	LEVEL_THREE("3","三级分销商");

	private String key;
	private String desc;
	
	SellerLevelEnum(String key,String desc){
		this.key=key;
		this.desc = desc;
	}
	
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(String keyValue){
		if(null != keyValue){
			for(SellerLevelEnum st : SellerLevelEnum.values()){
				String key = st.getKey();
				if(key.equals(keyValue)){
					return st.getDesc();
				}
			}
		}
		return null;
	}
	
	/**
	 * 传入key值，获取对象
	 * @param keyValue
	 * @return
	 */
	public static SellerLevelEnum getEnumByKey(String keyValue) {
		if (null != keyValue) {
			for (SellerLevelEnum st : SellerLevelEnum.values()) {
				String key = st.getKey();
				if (key.equals(keyValue)) {
					return st;
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
