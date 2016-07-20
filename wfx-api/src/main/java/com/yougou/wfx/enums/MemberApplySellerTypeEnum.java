package com.yougou.wfx.enums;

public enum MemberApplySellerTypeEnum {
	ALREADY_APPLY("1","已申请"),
	NOT_APPLY("0","未申请");

	private String key;
	private String desc;
	
	MemberApplySellerTypeEnum(String key,String desc){
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
			for(OrderLogOptEnum st : OrderLogOptEnum.values()){
				Integer key = st.getKey();
				if(key.equals(keyValue)){
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
