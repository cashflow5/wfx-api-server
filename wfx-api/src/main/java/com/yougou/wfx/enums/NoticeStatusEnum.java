package com.yougou.wfx.enums;


public enum NoticeStatusEnum {

	INEFFECTIVE (1,"未生效"),
	ACTIVE (2,"生效中"),
	INVALID(3,"已失效");
	private Integer key;
	private String desc;
	
	NoticeStatusEnum(Integer key,String desc){
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
			for(NoticeStatusEnum st : NoticeStatusEnum.values()){
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
