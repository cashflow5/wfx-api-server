package com.yougou.wfx.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 订单日志操作行为枚举
 * @author zhang.f1
 *
 */
public enum OrderLogOptEnum {
	//订单日志行为枚举start
	OPT_CREATE_ORDER(1,"生成订单"),
	OPT_PAYED_ORDER(2,"订单支付"),
	OPT_PART_DELIVERED(3,"部分发货"),
	OPT_DELIVERED(4,"订单发货"),
	OPT_REFUND_SUCCESS(5,"退款成功"),
	OPT_REFUND_CLOSE(6,"退款关闭"),
	OPT_CANCEL_ORDER(7,"取消订单"),
	OPT_PICKING_ORDER(8,"订单拣货中"),
	OPT_RECEIVED_ORDER(9,"确认收货"),
	OPT_UPDATE_REFUND(10,"修改退款"),
	OPT_CANCEL_REFUND(11,"取消退款"),
	OPT_APPLY_SALE_REFUND(12,"申请售中退款"),
	OPT_APPLY_AFTER_SALE_REFUND(13,"申请售后退款"),	
	OPT_REFUSED_REFUND(14,"拒绝退款"),
	OPT_REFUND_CONFIRM(15,"确认退款"),
	OPT_REFUND_FAILURE(16,"退款失败"),
	OPT_CLOSE_ORDER(17,"关闭订单");
	//订单日志行为枚举end
	
	
	
	private Integer key;
	private String desc;
	
	OrderLogOptEnum(Integer key,String desc){
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
