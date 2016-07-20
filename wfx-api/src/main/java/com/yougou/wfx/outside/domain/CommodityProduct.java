package com.yougou.wfx.outside.domain;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 商品货品信息
 * 
 * @author li.lq
 * @Date 2016年4月19日
 */
public class CommodityProduct extends OutputDto {

	private static final long serialVersionUID = 1L;
	/*** 货品编码  */
	private String productNo;
	/*** 第三方条码 */
	private String thirdPartyCode;
	/*** 可售库存 */
	private Integer inventoryNum;
	/*** 尺码编号 */
	private String sizeNo;
	public String getThirdPartyCode() {
		return thirdPartyCode;
	}
	public void setThirdPartyCode(String thirdPartyCode) {
		this.thirdPartyCode = thirdPartyCode;
	}
	public Integer getInventoryNum() {
		return inventoryNum;
	}
	public void setInventoryNum(Integer inventoryNum) {
		this.inventoryNum = inventoryNum;
	}
	public String getSizeNo() {
		return sizeNo;
	}
	public void setSizeNo(String sizeNo) {
		this.sizeNo = sizeNo;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
}
