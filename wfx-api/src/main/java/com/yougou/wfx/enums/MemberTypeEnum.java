package com.yougou.wfx.enums;

/**
 * 会员类型枚举：会员身份，1:普通会员，2：分销商'
 * @author zhang.f1
 *
 */
public enum MemberTypeEnum {
	NORMAL_MEMBER(1,"普通会员"),
	SELLER_MEMBER(2,"分销商");
	
	private Integer key;
	private String desc;
	
	MemberTypeEnum(Integer key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(Integer keyValue){
		
		if(null != keyValue){
			for(MemberTypeEnum st : MemberTypeEnum.values()){
				Integer key = st.getKey();
				if(key == keyValue ){
					return st.getDesc();
				}
			}
		}
		return null;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
