 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.cms.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * CommoditySaleCatOutputDto
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
public class HotSaleCatOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 销售分类编码
	 */
	private String catNo;
	/**
	 * 图片的URL
	 */
	private String picUrl;
	/**
	 * 热门分类排序
	 */
	private Integer hotSn;
	

	public HotSaleCatOutputDto(){
	}	

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name == null ? null : this.name.trim();
	}
	public void setCatNo(String value) {
		this.catNo = value;
	}
	
	public String getCatNo() {
		return this.catNo == null ? null : this.catNo.trim();
	}
	public void setPicUrl(String value) {
		this.picUrl = value;
	}
	
	public String getPicUrl() {
		return this.picUrl == null ? null : this.picUrl.trim();
	}
	public void setHotSn(Integer value) {
		value = value == null ? 0 : value;
		this.hotSn = value;
	}
	
	public Integer getHotSn() {
		return this.hotSn == null ? 0 : this.hotSn;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

