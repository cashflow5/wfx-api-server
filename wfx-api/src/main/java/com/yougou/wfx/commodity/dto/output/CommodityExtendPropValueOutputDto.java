package com.yougou.wfx.commodity.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

public class CommodityExtendPropValueOutputDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 属性项名称
	 */
	private String propItemName;
	
	/**
	 * 属性项编码
	 */
	private String propItemNo;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropItemName() {
		return propItemName;
	}

	public void setPropItemName(String propItemName) {
		this.propItemName = propItemName;
	}

	public String getPropItemNo() {
		return propItemNo;
	}

	public void setPropItemNo(String propItemNo) {
		this.propItemNo = propItemNo;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
