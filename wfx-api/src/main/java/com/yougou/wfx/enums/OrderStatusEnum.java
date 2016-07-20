/**
 * 
 */
package com.yougou.wfx.enums;


/**
 * 订单状态枚举类
 * @author luo.q1
 *
 */
public enum OrderStatusEnum {

	
	WAIT_PAY("WAIT_PAY","待付款"), 
	WAIT_DELIVER("WAIT_DELIVER","待发货"), 
	PART_DELIVERED("PART_DELIVERED","部分发货"), 
	DELIVERED("DELIVERED","已发货"), 
	TRADE_SUCCESS("TRADE_SUCCESS","交易成功"), 
	TRADE_CLOSED("TRADE_CLOSED","交易关闭");
	
	private String key;
	private String desc;
	
	OrderStatusEnum(String key,String desc){
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
			for(OrderStatusEnum st : OrderStatusEnum.values()){
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
