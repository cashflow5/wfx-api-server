package com.yougou.wfx.enums;

/**
 * <p>Title: ResultCodeEnum</p>
 * <p>Description:返回结果代号枚举类 </p>
 * @author: zheng.qq
 * @date: 2016年4月13日
 */
public enum ResultCodeEnum {
	SUCCESS(200,"成功"), 
	FAILURE(500,"失败"),
	WARN(400,"警告");
	
	private int key;
	private String desc;
	
	ResultCodeEnum(int key,String desc){
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

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
