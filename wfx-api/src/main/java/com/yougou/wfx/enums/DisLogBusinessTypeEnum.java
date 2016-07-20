package com.yougou.wfx.enums;


/**
 * 发现功能业务类型枚举类
 * @author wang.zf
 *
 */
public enum DisLogBusinessTypeEnum {
	//业务类型:1频道，2文章,3轮播图,4回收站
	DIS_LOG_BIZ_CHANNEL(1,"频道"),
	DIS_LOG_BIZ_ARTICLE(2,"文章"),
	DIS_LOG_BIZ_CARFIG(3,"轮播图"),
	DIS_LOG_BIZ_RECYCLE(4,"回收站");
	
	private Integer key;
	private String desc;
	
	DisLogBusinessTypeEnum(Integer key,String desc){
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
			for(DisLogBusinessTypeEnum st : DisLogBusinessTypeEnum.values()){
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
