 /*
 * 版本信息
 
 * 日期 2016-03-31 19:13:58
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dto.input;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.InputDto;

/**
 * CommodityBrandCatb2cPageInputDto
 * @author wfx
 * @Date 创建时间：2016-03-31 19:13:58
 */
public class CommodityBrandCatb2cPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * catb2cId
	 */
	private String catb2cId;
	/**
	 * brandId
	 */
	private String brandId;
	
	public CommodityBrandCatb2cPageInputDto(){
	}

	public CommodityBrandCatb2cPageInputDto(
		String id
	){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setCatb2cId(String value) {
		this.catb2cId = value;
	}
	
	public String getCatb2cId() {
		return this.catb2cId == null ? null : this.catb2cId.trim();
	}
	public void setBrandId(String value) {
		this.brandId = value;
	}
	
	public String getBrandId() {
		return this.brandId == null ? null : this.brandId.trim();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
