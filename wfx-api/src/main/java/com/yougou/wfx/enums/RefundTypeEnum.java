/**
 * 
 */
package com.yougou.wfx.enums;

/**
 * 退款类型枚举类
 * @author luo.q1
 *
 */
public enum RefundTypeEnum {

	
	ONLY_REFUND("ONLY_REFUND","仅退款"), 
	DELIVERD_REFUND("DELIVERD_REFUND","已发货仅退款"), 
	REJECTED_REFUND("REJECTED_REFUND","退货退款");
	
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

	private String key;
	private String desc;
	
	RefundTypeEnum(String key,String desc){
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
			for(RefundTypeEnum st : RefundTypeEnum.values()){
				String key = st.getKey();
				if(key.equals(keyValue) ){
					return st.getDesc();
				}
			}
		}
		return null;
	}
}
