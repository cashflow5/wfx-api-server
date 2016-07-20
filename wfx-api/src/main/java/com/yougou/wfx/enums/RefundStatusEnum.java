/**
 * 
 */
package com.yougou.wfx.enums;


/**
 * 退款状态枚举类
 * @author luo.q1
 *
 */
public enum RefundStatusEnum {

	
	APPLYING("APPLYING","退款申请中"), 
	PENDING_DELIVERD("PENDING_DELIVERD","待卖家确认收货"), 
	REJECT_REFUND("REJECT_REFUND","卖家拒绝退款"), 
	SUCCESS_REFUND("SUCCESS_REFUND","退款成功"), 
	UNDER_REFUND("UNDER_REFUND","退款处理中"), 
	CLOSE_REFUND("CLOSE_REFUND","退款关闭");
	
	private String key;
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
	private String desc;
	
	RefundStatusEnum(String key,String desc){
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
			for(RefundStatusEnum st : RefundStatusEnum.values()){
				String key = st.getKey();
				if(key.equals(keyValue) ){
					return st.getDesc();
				}
			}
		}
		return null;
	}

}
