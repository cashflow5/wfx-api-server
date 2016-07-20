/**
 * 
 */
package com.yougou.wfx.enums;

/**
 * 操作类型枚举类
 * @author luo.q1
 *
 */
public enum OperateTypeEnum {

	
	ADD("ADD","添加"), 
	EDIT("EDIT","修改"), 
	DELETE("DELETE","删除"),  
	EXPORT("EXPORT","导出");
	
	private String key;
	private String desc;
	
	
	OperateTypeEnum(String key,String desc){
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
			for(OperateTypeEnum st : OperateTypeEnum.values()){
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
