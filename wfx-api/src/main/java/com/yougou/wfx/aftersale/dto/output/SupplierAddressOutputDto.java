 /*
 * 版本信息
 
 * 日期 2016-04-26 09:34:42
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.aftersale.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * SupplierAddressOutputDto
 * @author wfx
 * @Date 创建时间：2016-04-26 09:34:43
 */
public class SupplierAddressOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 供应商编码
	 */
	private String supplierCode;
	/**
	 * 第三方平台返回编码
	 */
	private String outsideNo;
	/**
	 * 省编号
	 */
	private String provinceNo;
	/**
	 * 省
	 */
	private String province;
	/**
	 * 市编号
	 */
	private String cityNo;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 区编号
	 */
	private String areaNo;
	/**
	 * 地区
	 */
	private String area;
	/**
	 * 详细地址
	 */
	private String address;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String phone;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 详细地址
	 */
	private String detailAddress;

	public SupplierAddressOutputDto(){
	}

	public SupplierAddressOutputDto(
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
	public void setSupplierCode(String value) {
		this.supplierCode = value;
	}
	
	public String getSupplierCode() {
		return this.supplierCode == null ? null : this.supplierCode.trim();
	}
	
	public String getOutsideNo() {
		return outsideNo;
	}

	public void setOutsideNo(String outsideNo) {
		this.outsideNo = outsideNo;
	}

	public void setProvinceNo(String value) {
		this.provinceNo = value;
	}
	
	public String getProvinceNo() {
		return this.provinceNo == null ? null : this.provinceNo.trim();
	}
	public void setProvince(String value) {
		this.province = value;
	}
	
	public String getProvince() {
		return this.province == null ? null : this.province.trim();
	}
	public void setCityNo(String value) {
		this.cityNo = value;
	}
	
	public String getCityNo() {
		return this.cityNo == null ? null : this.cityNo.trim();
	}
	public void setCity(String value) {
		this.city = value;
	}
	
	public String getCity() {
		return this.city == null ? null : this.city.trim();
	}
	public void setAreaNo(String value) {
		this.areaNo = value;
	}
	
	public String getAreaNo() {
		return this.areaNo == null ? null : this.areaNo.trim();
	}
	public void setArea(String value) {
		this.area = value;
	}
	
	public String getArea() {
		return this.area == null ? null : this.area.trim();
	}
	public void setAddress(String value) {
		this.address = value;
	}
	
	public String getAddress() {
		return this.address == null ? null : this.address.trim();
	}
	public void setContact(String value) {
		this.contact = value;
	}
	
	public String getContact() {
		return this.contact == null ? null : this.contact.trim();
	}
	public void setPhone(String value) {
		this.phone = value;
	}
	
	public String getPhone() {
		return this.phone == null ? null : this.phone.trim();
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark == null ? null : this.remark.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	
}

