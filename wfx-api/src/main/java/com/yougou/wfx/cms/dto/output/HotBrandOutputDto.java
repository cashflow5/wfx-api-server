/*
 * 版本信息

 * 日期 2016-03-23 18:39:33

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.cms.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * CommodityBrandOutputDto
 * 
 * @author li.j1
 * @Date 创建时间：2016-03-23 18:39:34
 */
public class HotBrandOutputDto extends OutputDto {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 品牌英文名称
	 */
	private String englishName;
	/**
	 * 品牌小图的url
	 */
	private String logoSmallUrl;
	/**
	 * 品牌大图url
	 */
	private String logoNameUrl;
	/**
	 * 品牌编号
	 */
	private String brandNo;
	/**
	 * 小图
	 */
	private String logoLeastUrl;
	/**
	 * 中图
	 */
	private String logoMiddleUrl;
	/**
	 * 手机客户端图片
	 */
	private String mobilePic;
	/**
	 * 热门排序
	 */
	private Integer hotSn;

	public HotBrandOutputDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getLogoSmallUrl() {
		return logoSmallUrl;
	}

	public void setLogoSmallUrl(String logoSmallUrl) {
		this.logoSmallUrl = logoSmallUrl;
	}

	public String getLogoNameUrl() {
		return logoNameUrl;
	}

	public void setLogoNameUrl(String logoNameUrl) {
		this.logoNameUrl = logoNameUrl;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getLogoLeastUrl() {
		return logoLeastUrl;
	}

	public void setLogoLeastUrl(String logoLeastUrl) {
		this.logoLeastUrl = logoLeastUrl;
	}

	public String getLogoMiddleUrl() {
		return logoMiddleUrl;
	}

	public void setLogoMiddleUrl(String logoMiddleUrl) {
		this.logoMiddleUrl = logoMiddleUrl;
	}

	public String getMobilePic() {
		return mobilePic;
	}

	public void setMobilePic(String mobilePic) {
		this.mobilePic = mobilePic;
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
