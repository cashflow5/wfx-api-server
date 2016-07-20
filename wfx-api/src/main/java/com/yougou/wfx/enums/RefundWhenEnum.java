/**
 * 
 */
package com.yougou.wfx.enums;

/**
 * 售中/售后 枚举类
 * @author luo.q1
 *
 */
public enum RefundWhenEnum {

	
	BEFORE_DELIVERD_REFUND("BEFORE_DELIVERD_REFUND","售中"), 
	AFTER_DELIVERD_REFUND("AFTER_DELIVERD_REFUND","售后");
	
	private String key;
	private String desc;
	
	RefundWhenEnum(String key,String desc){
		this.key = key;
		this.desc = desc;
	}
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(String keyValue){
		
		if(null != keyValue){
			for(RefundWhenEnum st : RefundWhenEnum.values()){
				String key = st.getKey();
				if(key.equals(keyValue) ){
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
