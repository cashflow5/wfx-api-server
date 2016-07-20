package com.yougou.wfx.outside.request;

/**
 * SupplierAddressRequest
 * 
 * @author li.lq
 * @Date 2016年4月28日
 */
public class SupplierAddressRequest extends BaseRequest {
	private static final long serialVersionUID = -1140448559396274185L;

	/**
	 * 供货商编号
	 */
	private String supplierCode;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

}
