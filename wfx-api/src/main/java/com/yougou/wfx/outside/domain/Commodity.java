package com.yougou.wfx.outside.domain;

import java.util.Date;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 商品信息
 * @author li.lq
 * @Date 2016年4月19日
 */
public class Commodity extends OutputDto {

	private static final long serialVersionUID = 1L;
	/*** 商品编号 */
	private String no;
	/*** 商品ID*/
	private String id;
	/*** 商品名称 */
	private String commodityName;
	/*** 微分销平台上下架状态 */
	private Integer isOnsale;
	/*** 品牌编码 */
	private String brandNo;
	/*** 供应商款色编码*/
	private String supplierCode;
	/*** 品牌名称 */
	private String brandName;
	/*** 分类编码 */
	private String catNo;
	/*** 分类名称 */
	private String catName;
	/*** 微分销价 */
	private Double wfxPrice;
	/*** 市场价 */
	private Double publicPrice;
	/*** 创建时间 */
	private Date createDate;
	/*** 修改时间 */
	private Date updateDate;
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public Integer getIsOnsale() {
		return isOnsale;
	}
	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	public Double getWfxPrice() {
		return wfxPrice;
	}
	public void setWfxPrice(Double wfxPrice) {
		this.wfxPrice = wfxPrice;
	}
	public Double getPublicPrice() {
		return publicPrice;
	}
	public void setPublicPrice(Double publicPrice) {
		this.publicPrice = publicPrice;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
