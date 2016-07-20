package com.yougou.wfx.commodity.enums;

public enum BrandImgTypeEnum {

	LOGO_LEAST_URL(5,"微图"),
	LOGO_MIDDLE_URL(2,"中图"),
	LOGO_NAME_URL(1,"大图"),
	LOGO_SMALL_URL(3,"小图"),
	MOBILE_PIC(4,"手机图");
	
	
	private Integer type;
	
	private String name;

	BrandImgTypeEnum(Integer type, String name){
		this.type = type;
		this.name = name;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
