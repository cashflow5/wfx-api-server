 /*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.model;

import com.yougou.wfx.dto.base.PageModel;

/**
 * CommodityStyleOrderInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public class CommodityStyleFilterEntity implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	//品牌编码
	private String brandNo;
	//品牌名称
	private String brandName;
	//分类编码
	private String catNo;
	//分类名称
	private String catName;
	//尺码编码
	private String sizeNo;
	//尺码名称
	private String sizeName;
	//颜色名称
	private String specName;
	//颜色编码
	private String supplierCode;
	//属性项编码:款式，风格，性别 为同一个字段
	private String propValueNo;
	//属性项值：款式，风格，性别 为同一个字段
	private String propValue;
	//最小金额
	private Integer minPrice;
	
	//最小金额
	private Integer maxPrice;
	//分类id
	private String catId;
	//店铺id
	private String shopId;
	//属性项名称
	private String propItemName;
	public String getPropItemName() {
		return propItemName;
	}
	public void setPropItemName(String propItemName) {
		this.propItemName = propItemName;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getCatNo() {
		return catNo;
	}
	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getSizeNo() {
		return sizeNo;
	}
	public void setSizeNo(String sizeNo) {
		this.sizeNo = sizeNo;
	}
	public String getSizeName() {
		return sizeName;
	}
	public void setSizeName(String sizeName) {
		this.sizeName = sizeName;
	}
	public String getSpecName() {
		return specName;
	}
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getPropValueNo() {
		return propValueNo;
	}
	public void setPropValueNo(String propValueNo) {
		this.propValueNo = propValueNo;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	public Integer getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Integer minPrice) {
		this.minPrice = minPrice;
	}
	public Integer getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(Integer maxPrice) {
		this.maxPrice = maxPrice;
	}
}

