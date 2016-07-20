 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
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
 * CommodityBrandPageInputDto
 * @author li.j1
 * @Date 创建时间：2016-03-23 18:39:34
 */
public class CommodityBrandPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * brandName
	 */
	private String brandName;
	/**
	 * speelingName
	 */
	private String speelingName;
	/**
	 * englishName
	 */
	private String englishName;
	/**
	 * url
	 */
	private String url;
	/**
	 * brandDesc
	 */
	private String brandDesc;
	/**
	 * 品牌小图的url
	 */
	private String logoSmallUrl;
	/**
	 * 品牌大图url
	 */
	private String logoNameUrl;
	/**
	 * keywords
	 */
	private String keywords;
	/**
	 * isRecommend
	 */
	private Integer isRecommend;
	/**
	 * isShow
	 */
	private Integer isShow;
	/**
	 * metaTitle
	 */
	private String metaTitle;
	/**
	 * 牌品系列id
	 */
	private String seriesId;
	/**
	 * metaKeywords
	 */
	private String metaKeywords;
	/**
	 * metaDescription
	 */
	private String metaDescription;
	/**
	 * updatePerson
	 */
	private String updatePerson;
	/**
	 * updateTime
	 */
	private Date updateTime;
	/**
	 * deleteflag
	 */
	private Integer deleteflag;
	/**
	 * brandNo
	 */
	private String brandNo;
	/**
	 * isEnabled
	 */
	private java.lang.Boolean isEnabled;
	/**
	 * defaultImg
	 */
	private String defaultImg;
	/**
	 * updateTimestamp
	 */
	private java.lang.Long updateTimestamp;
	/**
	 * 栏目ID（cms）
	 */
	private String channelId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * shopTemplateFlag
	 */
	private Integer shopTemplateFlag;
	/**
	 * sortNo
	 */
	private Integer sortNo;
	/**
	 * logoLeastUrl
	 */
	private String logoLeastUrl;
	/**
	 * logoMiddleUrl
	 */
	private String logoMiddleUrl;
	/**
	 * advertImage
	 */
	private String advertImage;
	/**
	 * stationImage
	 */
	private String stationImage;
	/**
	 * 舰旗中图
	 */
	private String shipMiddleImage;
	/**
	 * 旗舰小图
	 */
	private String shipSmallImage;
	/**
	 * seoKeyword
	 */
	private String seoKeyword;
	/**
	 * seoTitle
	 */
	private String seoTitle;
	/**
	 * seoDescription
	 */
	private String seoDescription;
	/**
	 * 手机客户端图片
	 */
	private String mobilePic;
	/**
	 * 品牌类型（1是自营品牌，2是招商品牌）
	 */
	private Integer typeFlag;
	/**
	 * 负责人
	 */
	private String manager;
	/**
	 * 是否热门 1：是，2：否
	 */
	private Integer hotFlag;
	/**
	 * 热门排序
	 */
	private Integer hotSn;
	/**
	 * 1=启用，0=停用 
	 */
	private Integer status;
	
	/**
	 * 是否自营品牌在用 1在用 2不在用
	 */
	private Integer useFlag;
	
	public CommodityBrandPageInputDto(){
	}

	public CommodityBrandPageInputDto(
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
	public void setBrandName(String value) {
		this.brandName = value;
	}
	
	public String getBrandName() {
		return this.brandName == null ? null : this.brandName.trim();
	}
	public void setSpeelingName(String value) {
		this.speelingName = value;
	}
	
	public String getSpeelingName() {
		return this.speelingName == null ? null : this.speelingName.trim();
	}
	public void setEnglishName(String value) {
		this.englishName = value;
	}
	
	public String getEnglishName() {
		return this.englishName == null ? null : this.englishName.trim();
	}
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url == null ? null : this.url.trim();
	}
	public void setBrandDesc(String value) {
		this.brandDesc = value;
	}
	
	public String getBrandDesc() {
		return this.brandDesc == null ? null : this.brandDesc.trim();
	}
	public void setLogoSmallUrl(String value) {
		this.logoSmallUrl = value;
	}
	
	public String getLogoSmallUrl() {
		return this.logoSmallUrl == null ? null : this.logoSmallUrl.trim();
	}
	public void setLogoNameUrl(String value) {
		this.logoNameUrl = value;
	}
	
	public String getLogoNameUrl() {
		return this.logoNameUrl == null ? null : this.logoNameUrl.trim();
	}
	public void setKeywords(String value) {
		this.keywords = value;
	}
	
	public String getKeywords() {
		return this.keywords == null ? null : this.keywords.trim();
	}
	public void setIsRecommend(Integer value) {
		value = value == null ? 0 : value;
		this.isRecommend = value;
	}
	
	public Integer getIsRecommend() {
		return this.isRecommend == null ? 0 : this.isRecommend;
	}
	public void setIsShow(Integer value) {
		value = value == null ? 0 : value;
		this.isShow = value;
	}
	
	public Integer getIsShow() {
		return this.isShow == null ? 0 : this.isShow;
	}
	public void setMetaTitle(String value) {
		this.metaTitle = value;
	}
	
	public String getMetaTitle() {
		return this.metaTitle == null ? null : this.metaTitle.trim();
	}
	public void setSeriesId(String value) {
		this.seriesId = value;
	}
	
	public String getSeriesId() {
		return this.seriesId == null ? null : this.seriesId.trim();
	}
	public void setMetaKeywords(String value) {
		this.metaKeywords = value;
	}
	
	public String getMetaKeywords() {
		return this.metaKeywords == null ? null : this.metaKeywords.trim();
	}
	public void setMetaDescription(String value) {
		this.metaDescription = value;
	}
	
	public String getMetaDescription() {
		return this.metaDescription == null ? null : this.metaDescription.trim();
	}
	public void setUpdatePerson(String value) {
		this.updatePerson = value;
	}
	
	public String getUpdatePerson() {
		return this.updatePerson == null ? null : this.updatePerson.trim();
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public String getStringUpdateTime() {
		if(this.updateTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.updateTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setDeleteflag(Integer value) {
		value = value == null ? 0 : value;
		this.deleteflag = value;
	}
	
	public Integer getDeleteflag() {
		return this.deleteflag == null ? 0 : this.deleteflag;
	}
	public void setBrandNo(String value) {
		this.brandNo = value;
	}
	
	public String getBrandNo() {
		return this.brandNo == null ? null : this.brandNo.trim();
	}
	public void setIsEnabled(java.lang.Boolean value) {
		this.isEnabled = value;
	}
	
	public java.lang.Boolean getIsEnabled() {
		return this.isEnabled;
	}
	public void setDefaultImg(String value) {
		this.defaultImg = value;
	}
	
	public String getDefaultImg() {
		return this.defaultImg == null ? null : this.defaultImg.trim();
	}
	public void setUpdateTimestamp(java.lang.Long value) {
		this.updateTimestamp = value;
	}
	
	public java.lang.Long getUpdateTimestamp() {
		return this.updateTimestamp;
	}
	public void setChannelId(String value) {
		this.channelId = value;
	}
	
	public String getChannelId() {
		return this.channelId == null ? null : this.channelId.trim();
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public String getStringCreateTime() {
		if(this.createTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setShopTemplateFlag(Integer value) {
		value = value == null ? 0 : value;
		this.shopTemplateFlag = value;
	}
	
	public Integer getShopTemplateFlag() {
		return this.shopTemplateFlag == null ? 0 : this.shopTemplateFlag;
	}
	public void setSortNo(Integer value) {
		value = value == null ? 0 : value;
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo == null ? 0 : this.sortNo;
	}
	public void setLogoLeastUrl(String value) {
		this.logoLeastUrl = value;
	}
	
	public String getLogoLeastUrl() {
		return this.logoLeastUrl == null ? null : this.logoLeastUrl.trim();
	}
	public void setLogoMiddleUrl(String value) {
		this.logoMiddleUrl = value;
	}
	
	public String getLogoMiddleUrl() {
		return this.logoMiddleUrl == null ? null : this.logoMiddleUrl.trim();
	}
	public void setAdvertImage(String value) {
		this.advertImage = value;
	}
	
	public String getAdvertImage() {
		return this.advertImage == null ? null : this.advertImage.trim();
	}
	public void setStationImage(String value) {
		this.stationImage = value;
	}
	
	public String getStationImage() {
		return this.stationImage == null ? null : this.stationImage.trim();
	}
	public void setShipMiddleImage(String value) {
		this.shipMiddleImage = value;
	}
	
	public String getShipMiddleImage() {
		return this.shipMiddleImage == null ? null : this.shipMiddleImage.trim();
	}
	public void setShipSmallImage(String value) {
		this.shipSmallImage = value;
	}
	
	public String getShipSmallImage() {
		return this.shipSmallImage == null ? null : this.shipSmallImage.trim();
	}
	public void setSeoKeyword(String value) {
		this.seoKeyword = value;
	}
	
	public String getSeoKeyword() {
		return this.seoKeyword == null ? null : this.seoKeyword.trim();
	}
	public void setSeoTitle(String value) {
		this.seoTitle = value;
	}
	
	public String getSeoTitle() {
		return this.seoTitle == null ? null : this.seoTitle.trim();
	}
	public void setSeoDescription(String value) {
		this.seoDescription = value;
	}
	
	public String getSeoDescription() {
		return this.seoDescription == null ? null : this.seoDescription.trim();
	}
	public void setMobilePic(String value) {
		this.mobilePic = value;
	}
	
	public String getMobilePic() {
		return this.mobilePic == null ? null : this.mobilePic.trim();
	}
	public void setTypeFlag(Integer value) {
		value = value == null ? 0 : value;
		this.typeFlag = value;
	}
	
	public Integer getTypeFlag() {
		return this.typeFlag == null ? 0 : this.typeFlag;
	}
	public void setManager(String value) {
		this.manager = value;
	}
	
	public String getManager() {
		return this.manager == null ? null : this.manager.trim();
	}
	public void setHotFlag(Integer value) {
		value = value == null ? 0 : value;
		this.hotFlag = value;
	}
	
	public Integer getHotFlag() {
		return this.hotFlag == null ? 0 : this.hotFlag;
	}
	public void setHotSn(Integer value) {
		value = value == null ? 0 : value;
		this.hotSn = value;
	}
	
	public Integer getHotSn() {
		return this.hotSn == null ? 0 : this.hotSn;
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
	}
	
	public Integer getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(Integer useFlag) {
		this.useFlag = useFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
