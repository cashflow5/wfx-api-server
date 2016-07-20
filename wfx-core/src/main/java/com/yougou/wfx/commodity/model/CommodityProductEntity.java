 /*
 * 版本信息
 
 * 日期 2016-04-14 13:34:05
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * CommodityProductEntity
 * @author wfx
 * @Date 创建时间：2016-04-14 13:34:06
 */
public class CommodityProductEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * id
	 */
	private String id;
	/**
	 * 商品ID
	 */
	private String commodityId;
	/**
	 * 货品规格
	 */
	private String prodModel;
	/**
	 * 市场价 
	 */
	private Double costPrice;
	/**
	 * 百丽价
	 */
	private Double salePrice;
	/**
	 * 删除标记
	 */
	private Integer deleteFlag;
	/**
	 * 货品编号
	 */
	private String productNo;
	/**
	 * 第三方条码
	 */
	private String thirdPartyCode;
	/**
	 * 百丽条码
	 */
	private String insideCode;
	/**
	 * 长
	 */
	private Double length;
	/**
	 * 宽
	 */
	private Double width;
	/**
	 * 高
	 */
	private Double height;
	/**
	 * 重量
	 */
	private Double weight;
	/**
	 * 货品规格
	 */
	private String specKey;
	/**
	 * 品牌编码
	 */
	private String brandNo;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 商品类别编码 
	 */
	private String catNo;
	/**
	 * 商品类别名称 
	 */
	private String catName;
	/**
	 * 商品类别结构码
	 */
	private String catStructName;
	/**
	 * 尺码编号
	 */
	private String sizeNo;
	/**
	 * 尺码名称
	 */
	private String sizeName;
	/**
	 * updateDate
	 */
	private Date updateDate;
	/**
	 * secondCode
	 */
	private String secondCode;
	/**
	 * 销售状态，0是停售、1是销售中
	 */
	private Integer sellStatus;
	/**
	 *  0是不需要抽样，1是需要抽样
	 */
	private Integer sampleSet;
	/**
	 * 记录操作时间戳
	 */
	private Date opTimestamp;
	/**
	 * 可售库存
	 */
	private Integer inventoryNum;
	/**
	 * 预占库存数量
	 */
	private Integer prestoreInventoryNum;
//columns END

	public CommodityProductEntity(){
	}

	public CommodityProductEntity(
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
	public void setCommodityId(String value) {
		this.commodityId = value;
	}
	
	public String getCommodityId() {
		return this.commodityId == null ? null : this.commodityId.trim();
	}
	public void setProdModel(String value) {
		this.prodModel = value;
	}
	
	public String getProdModel() {
		return this.prodModel == null ? null : this.prodModel.trim();
	}
	public void setCostPrice(Double value) {
		this.costPrice = value;
	}
	
	public Double getCostPrice() {
		return this.costPrice;
	}
	public void setSalePrice(Double value) {
		this.salePrice = value;
	}
	
	public Double getSalePrice() {
		return this.salePrice;
	}
	public void setDeleteFlag(Integer value) {
		value = value == null ? 0 : value;
		this.deleteFlag = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag == null ? 0 : this.deleteFlag;
	}
	public void setProductNo(String value) {
		this.productNo = value;
	}
	
	public String getProductNo() {
		return this.productNo == null ? null : this.productNo.trim();
	}
	public void setThirdPartyCode(String value) {
		this.thirdPartyCode = value;
	}
	
	public String getThirdPartyCode() {
		return this.thirdPartyCode == null ? null : this.thirdPartyCode.trim();
	}
	public void setInsideCode(String value) {
		this.insideCode = value;
	}
	
	public String getInsideCode() {
		return this.insideCode == null ? null : this.insideCode.trim();
	}
	public void setLength(Double value) {
		this.length = value;
	}
	
	public Double getLength() {
		return this.length;
	}
	public void setWidth(Double value) {
		this.width = value;
	}
	
	public Double getWidth() {
		return this.width;
	}
	public void setHeight(Double value) {
		this.height = value;
	}
	
	public Double getHeight() {
		return this.height;
	}
	public void setWeight(Double value) {
		this.weight = value;
	}
	
	public Double getWeight() {
		return this.weight;
	}
	public void setSpecKey(String value) {
		this.specKey = value;
	}
	
	public String getSpecKey() {
		return this.specKey == null ? null : this.specKey.trim();
	}
	public void setBrandNo(String value) {
		this.brandNo = value;
	}
	
	public String getBrandNo() {
		return this.brandNo == null ? null : this.brandNo.trim();
	}
	public void setBrandName(String value) {
		this.brandName = value;
	}
	
	public String getBrandName() {
		return this.brandName == null ? null : this.brandName.trim();
	}
	public void setCatNo(String value) {
		this.catNo = value;
	}
	
	public String getCatNo() {
		return this.catNo == null ? null : this.catNo.trim();
	}
	public void setCatName(String value) {
		this.catName = value;
	}
	
	public String getCatName() {
		return this.catName == null ? null : this.catName.trim();
	}
	public void setCatStructName(String value) {
		this.catStructName = value;
	}
	
	public String getCatStructName() {
		return this.catStructName == null ? null : this.catStructName.trim();
	}
	public void setSizeNo(String value) {
		this.sizeNo = value;
	}
	
	public String getSizeNo() {
		return this.sizeNo == null ? null : this.sizeNo.trim();
	}
	public void setSizeName(String value) {
		this.sizeName = value;
	}
	
	public String getSizeName() {
		return this.sizeName == null ? null : this.sizeName.trim();
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
	public void setSecondCode(String value) {
		this.secondCode = value;
	}
	
	public String getSecondCode() {
		return this.secondCode == null ? null : this.secondCode.trim();
	}
	public void setSellStatus(Integer value) {
		value = value == null ? 0 : value;
		this.sellStatus = value;
	}
	
	public Integer getSellStatus() {
		return this.sellStatus == null ? 0 : this.sellStatus;
	}
	public void setSampleSet(Integer value) {
		value = value == null ? 0 : value;
		this.sampleSet = value;
	}
	
	public Integer getSampleSet() {
		return this.sampleSet == null ? 0 : this.sampleSet;
	}
	public void setOpTimestamp(Date value) {
		this.opTimestamp = value;
	}
	
	public Date getOpTimestamp() {
		return this.opTimestamp;
	}
	
	public String getStringOpTimestamp() {
		if(this.opTimestamp == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.opTimestamp, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setInventoryNum(Integer value) {
		value = value == null ? 0 : value;
		this.inventoryNum = value;
	}
	
	public Integer getInventoryNum() {
		return this.inventoryNum == null ? 0 : this.inventoryNum;
	}
	public void setPrestoreInventoryNum(Integer value) {
		value = value == null ? 0 : value;
		this.prestoreInventoryNum = value;
	}
	
	public Integer getPrestoreInventoryNum() {
		return this.prestoreInventoryNum == null ? 0 : this.prestoreInventoryNum;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

