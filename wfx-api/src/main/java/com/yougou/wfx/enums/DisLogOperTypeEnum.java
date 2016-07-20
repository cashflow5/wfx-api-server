package com.yougou.wfx.enums;


/**
 * 发现功能操作类型枚举类
 * @author wang.zf
 *
 */
public enum DisLogOperTypeEnum {
	//操作类型:1新增，2删除，3修改
	DIS_LOG_OPER_ADD(1,"新增"),
	DIS_LOG_OPER_DEL(2,"删除"),
	DIS_LOG_OPER_UPDATE(3,"修改");
	
	private Integer key;
	private String desc;
	
	DisLogOperTypeEnum(Integer key,String desc){
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
			for(DisLogOperTypeEnum st : DisLogOperTypeEnum.values()){
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
