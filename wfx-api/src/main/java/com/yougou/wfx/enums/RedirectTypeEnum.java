package com.yougou.wfx.enums;


public enum RedirectTypeEnum {

	NOTHING(0,"无"),
	H5_PAGE(1,"H5页面"),
	COMMODITY_DETAIL(2,"商品详情"),
	DISCOVER_ARTICLE(3,"发现文章");
	
	
	private Integer key;
	private String desc;
	
	RedirectTypeEnum(Integer key,String desc){
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
			for(RedirectTypeEnum st : RedirectTypeEnum.values()){
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
