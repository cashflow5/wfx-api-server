package com.yougou.wfx.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 访客来源类型枚举
 * @author zhang.f1
 *
 */
public enum VisitorSourceType {
	WEIXIN("weixin","微信访客"),
	OTHER("other","其他访客");
	
	String type;
	String desc;
	
	VisitorSourceType(String type,String desc){
		this.type=type;
		this.desc=desc;
	}
	
	/**
	 * 传入访客来源key值，获取对应访客来源描述
	 * @param type
	 * @return
	 */
	public static String getTypeDesc(String type){
		
		if(StringUtils.isNotBlank(type)){
			for(VisitorSourceType st : VisitorSourceType.values()){
				String key = st.getType();
				if(key.equals(type)){
					return st.getDesc();
				}
			}
		}
		return null;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
	
	
}
