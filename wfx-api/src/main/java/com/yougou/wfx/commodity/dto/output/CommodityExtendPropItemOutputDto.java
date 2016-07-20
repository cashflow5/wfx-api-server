package com.yougou.wfx.commodity.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

public class CommodityExtendPropItemOutputDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键ID
	 */
	private String id;
	
	/**
	 * 属性值
	 */
	private String propValue;
	
	/**
	 * 属性值编码
	 */
	private String propValueNo;
	
	/**
	 * 外键ID
	 */
	private String extendPropItemId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPropValue() {
		return propValue;
	}

	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}

	public String getPropValueNo() {
		return propValueNo;
	}

	public void setPropValueNo(String propValueNo) {
		this.propValueNo = propValueNo;
	}

	public String getExtendPropItemId() {
		return extendPropItemId;
	}

	public void setExtendPropItemId(String extendPropItemId) {
		this.extendPropItemId = extendPropItemId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
