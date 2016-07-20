 /*
 * 版本信息
 
 * 日期 2016-03-24 16:06:23
 
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
 * CommodityCatb2cPageInputDto
 * @author wzf
 * @Date 创建时间：2016-03-24 16:06:23
 */
public class CommodityCatb2cPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	private String id;
	/**
	 * 类别名称
	 */
	private String catName;
	/**
	 * 分类词ID,tbl_commodity_catb2c_words.id
	 */
	private String catWordsId;
	/**
	 * 结构名称
	 */
	private String structName;
	/**
	 * 名称分类树
	 */
	private String structCatName;
	/**
	 * 是否推荐
	 */
	private Integer isRecommend;
	/**
	 * 是否显示
	 */
	private Integer isShow;
	/**
	 * 排序
	 */
	private Integer sortNo;
	/**
	 * 更新人
	 */
	private String updatePerson;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 分类别名
	 */
	private String catAlias;
	/**
	 * 关键字
	 */
	private String keywords;
	/**
	 * 分类描述
	 */
	private String catb2cDesc;
	/**
	 * 是否启用
	 */
	private Integer isEnabled;
	/**
	 * 是否删除
	 */
	private Integer deleteFlag;
	/**
	 * 分类编码
	 */
	private String no;
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * 时间戳
	 */
	private Long updateTimestamp;
	/**
	 * 栏目ID（cms）
	 */
	private String channelId;
	/**
	 * 属性类型。0：基础属性；1：销售属性
	 */
	private Integer propType;
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
	 * 淘宝图片规则
	 */
	private String taobaoImgOrder;
	/**
	 * 分类商品毛利率规则
	 */
	private Double interestedRateOrder;
	/**
	 * 父分类id
	 */
	private String parentId;
	/**
	 * 1=启用，0=停用 
	 */
	private Integer status;
	
	public CommodityCatb2cPageInputDto(){
	}

	public CommodityCatb2cPageInputDto(
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
	public void setCatName(String value) {
		this.catName = value;
	}
	
	public String getCatName() {
		return this.catName == null ? null : this.catName.trim();
	}
	public void setCatWordsId(String value) {
		this.catWordsId = value;
	}
	
	public String getCatWordsId() {
		return this.catWordsId == null ? null : this.catWordsId.trim();
	}
	public void setStructName(String value) {
		this.structName = value;
	}
	
	public String getStructName() {
		return this.structName == null ? null : this.structName.trim();
	}
	public void setStructCatName(String value) {
		this.structCatName = value;
	}
	
	public String getStructCatName() {
		return this.structCatName == null ? null : this.structCatName.trim();
	}
	public void setIsRecommend(Integer value) {
		this.isRecommend = value;
	}
	
	public Integer getIsRecommend() {
		return this.isRecommend;
	}
	public void setIsShow(Integer value) {
		this.isShow = value;
	}
	
	public Integer getIsShow() {
		return this.isShow;
	}
	public void setSortNo(Integer value) {
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo;
	}
	public void setUpdatePerson(String value) {
		this.updatePerson = value;
	}
	
	public String getUpdatePerson() {
		return this.updatePerson == null ? null : this.updatePerson.trim();
	}
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public String getStringCreateDate() {
		if(this.createDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	public String getStringUpdateDate() {
		if(this.updateDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.updateDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setCatAlias(String value) {
		this.catAlias = value;
	}
	
	public String getCatAlias() {
		return this.catAlias == null ? null : this.catAlias.trim();
	}
	public void setKeywords(String value) {
		this.keywords = value;
	}
	
	public String getKeywords() {
		return this.keywords == null ? null : this.keywords.trim();
	}
	public void setCatb2cDesc(String value) {
		this.catb2cDesc = value;
	}
	
	public String getCatb2cDesc() {
		return this.catb2cDesc == null ? null : this.catb2cDesc.trim();
	}
	public void setIsEnabled(Integer value) {
		this.isEnabled = value;
	}
	
	public Integer getIsEnabled() {
		return this.isEnabled;
	}
	public void setDeleteFlag(Integer value) {
		this.deleteFlag = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag;
	}
	public void setNo(String value) {
		this.no = value;
	}
	
	public String getNo() {
		return this.no == null ? null : this.no.trim();
	}
	public void setLevel(Integer value) {
		this.level = value;
	}
	
	public Integer getLevel() {
		return this.level;
	}
	public void setUpdateTimestamp(Long value) {
		this.updateTimestamp = value;
	}
	
	public Long getUpdateTimestamp() {
		return this.updateTimestamp;
	}
	public void setChannelId(String value) {
		this.channelId = value;
	}
	
	public String getChannelId() {
		return this.channelId == null ? null : this.channelId.trim();
	}
	public void setPropType(Integer value) {
		this.propType = value;
	}
	
	public Integer getPropType() {
		return this.propType;
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
	public void setTaobaoImgOrder(String value) {
		this.taobaoImgOrder = value;
	}
	
	public String getTaobaoImgOrder() {
		return this.taobaoImgOrder == null ? null : this.taobaoImgOrder.trim();
	}
	public void setInterestedRateOrder(Double value) {
		this.interestedRateOrder = value;
	}
	
	public Double getInterestedRateOrder() {
		return this.interestedRateOrder;
	}
	public void setParentId(String value) {
		this.parentId = value;
	}
	
	public String getParentId() {
		return this.parentId == null ? null : this.parentId.trim();
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
