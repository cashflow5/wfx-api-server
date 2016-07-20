package com.yougou.wfx.enums;

/**
 * 订单日志操作结果枚举：成功，失败
 * @author zhang.f1
 *
 */
public enum OrderLogOptResultEnum {
	OPT_SUCCESS(1,"成功"),
	OPT_FAILED(2,"失败");

	private Integer key;
	private String desc;
	
	OrderLogOptResultEnum(Integer key,String desc){
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
