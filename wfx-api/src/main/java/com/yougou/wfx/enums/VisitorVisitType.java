package com.yougou.wfx.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 访客访问类型枚举
 * @author zhang.f1
 *
 */
public enum VisitorVisitType {
	SHOP_VISIT(1,"店铺访问"),
	COMMODITY_VISIT(2,"单品页访问");
	
	Integer type;
	String desc;
	
	VisitorVisitType(Integer type,String desc){
		this.type=type;
		this.desc=desc;
	}
	
	/**
	 * 传入 访客访问类型key值，获取对应访客访问类型描述
	 * @param type
	 * @return
	 */
	public static String getTypeDesc(Integer type){
		
		if(null != type){
			for(VisitorVisitType st : VisitorVisitType.values()){
				Integer key = st.getType();
				if(key==type){
					return st.getDesc();
				}
			}
		}
		return null;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
	
}
