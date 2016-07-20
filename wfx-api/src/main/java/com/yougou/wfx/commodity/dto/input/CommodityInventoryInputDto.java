package com.yougou.wfx.commodity.dto.input;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

public class CommodityInventoryInputDto extends InputDto {

	private static final long serialVersionUID = 1L;
	
	private String commodityId;
	
	private String productId;

	private String commodityName;

	private String wfxCommodityNo;

	private String supplierCode;

	private String productNo;

	private String thirdPartyCode;

	private Integer inventoryNumStart;

	private Integer inventoryNumEnd;

	private Integer isOnsale;
	
	private String commodityNo;
	
	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getWfxCommodityNo() {
		return wfxCommodityNo;
	}

	public void setWfxCommodityNo(String wfxCommodityNo) {
		this.wfxCommodityNo = wfxCommodityNo;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getThirdPartyCode() {
		return thirdPartyCode;
	}

	public void setThirdPartyCode(String thirdPartyCode) {
		this.thirdPartyCode = thirdPartyCode;
	}

	public Integer getInventoryNumStart() {
		return inventoryNumStart;
	}

	public void setInventoryNumStart(Integer inventoryNumStart) {
		this.inventoryNumStart = inventoryNumStart;
	}

	public Integer getInventoryNumEnd() {
		return inventoryNumEnd;
	}

	public void setInventoryNumEnd(Integer inventoryNumEnd) {
		this.inventoryNumEnd = inventoryNumEnd;
	}

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
