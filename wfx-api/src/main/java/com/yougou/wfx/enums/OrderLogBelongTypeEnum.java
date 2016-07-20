package com.yougou.wfx.enums;

/**
 * 订单日志表中日志归属枚举：订单日志，退款/售后日志
 * @author zhang.f1
 *
 */
public enum OrderLogBelongTypeEnum {
	ORDER_LOG(1,"订单日志"),
	REFUND_LOG(2,"退款单日志");

	private Integer key;
	private String desc;
	
	OrderLogBelongTypeEnum(Integer key,String desc){
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
