package com.yougou.wfx.enums;

/**
 * 订单日志：操作人所属方枚举
 * @author zhang.f1
 *
 */
public enum OrderLogOptBelongEnum {
	//订单日志操作人所属方枚举start
	OPT_BELONG_BUYER(1,"买家"),
	OPT_BELONG_SELLER(2,"卖家");

	private Integer key;
	private String desc;
	
	OrderLogOptBelongEnum(Integer key,String desc){
		this.key=key;
		this.desc = desc;
	}
	
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(Integer keyValue){
		
		if(null != keyValue){
			for(OrderLogOptEnum st : OrderLogOptEnum.values()){
				Integer key = st.getKey();
				if(key == keyValue){
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
