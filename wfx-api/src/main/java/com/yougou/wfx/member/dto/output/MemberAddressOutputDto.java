 /*
 * 版本信息
 
 * 日期 2016-03-25 17:36:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.member.dto.output;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * MemberAddressOutputDto
 * @author wfx
 * @Date 创建时间：2016-03-25 17:36:56
 */
public class MemberAddressOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * tbl_member_loginaccount.id
	 */
	private String loginacccountId;
	/**
	 * receivingName
	 */
	private String receivingName;
	/**
	 * 省份
	 */
	private String receivingProvince;
	/**
	 * 城市
	 */
	private String receivingCity;
	/**
	 * 地区
	 */
	private String receivingDistrict;
	/**
	 * 详细地址
	 */
	private String receivingAddress;
	/**
	 * 邮政编码
	 */
	private String receivingZipCode;
	/**
	 * 座机号
	 */
	private String receivingTelephone;
	/**
	 * 手机号
	 */
	private String receivingMobilePhone;
	/**
	 * 是否设置为默认收货地址,1=默认
	 */
	private Integer isDefaultAddress;
	/**
	 * distributionType
	 */
	private Integer distributionType;
	/**
	 * remarks
	 */
	private String remarks;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date lastUpdateTime;

	public MemberAddressOutputDto(){
	}

	public MemberAddressOutputDto(
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
	public void setLoginacccountId(String value) {
		this.loginacccountId = value;
	}
	
	public String getLoginacccountId() {
		return this.loginacccountId == null ? null : this.loginacccountId.trim();
	}
	public void setReceivingName(String value) {
		this.receivingName = value;
	}
	
	public String getReceivingName() {
		return this.receivingName == null ? null : this.receivingName.trim();
	}
	public void setReceivingProvince(String value) {
		this.receivingProvince = value;
	}
	
	public String getReceivingProvince() {
		return this.receivingProvince == null ? null : this.receivingProvince.trim();
	}
	public void setReceivingCity(String value) {
		this.receivingCity = value;
	}
	
	public String getReceivingCity() {
		return this.receivingCity == null ? null : this.receivingCity.trim();
	}
	public void setReceivingDistrict(String value) {
		this.receivingDistrict = value;
	}
	
	public String getReceivingDistrict() {
		return this.receivingDistrict == null ? null : this.receivingDistrict.trim();
	}
	public void setReceivingAddress(String value) {
		this.receivingAddress = value;
	}
	
	public String getReceivingAddress() {
		return this.receivingAddress == null ? null : this.receivingAddress.trim();
	}
	public void setReceivingZipCode(String value) {
		this.receivingZipCode = value;
	}
	
	public String getReceivingZipCode() {
		return this.receivingZipCode == null ? null : this.receivingZipCode.trim();
	}
	public void setReceivingTelephone(String value) {
		this.receivingTelephone = value;
	}
	
	public String getReceivingTelephone() {
		return this.receivingTelephone == null ? null : this.receivingTelephone.trim();
	}
	public void setReceivingMobilePhone(String value) {
		this.receivingMobilePhone = value;
	}
	
	public String getReceivingMobilePhone() {
		return this.receivingMobilePhone == null ? null : this.receivingMobilePhone.trim();
	}
	public void setIsDefaultAddress(Integer value) {
		value = value == null ? 0 : value;
		this.isDefaultAddress = value;
	}
	
	public Integer getIsDefaultAddress() {
		return this.isDefaultAddress == null ? 0 : this.isDefaultAddress;
	}
	public void setDistributionType(Integer value) {
		value = value == null ? 0 : value;
		this.distributionType = value;
	}
	
	public Integer getDistributionType() {
		return this.distributionType == null ? 0 : this.distributionType;
	}
	public void setRemarks(String value) {
		this.remarks = value;
	}
	
	public String getRemarks() {
		return this.remarks == null ? null : this.remarks.trim();
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
	public void setLastUpdateTime(Date value) {
		this.lastUpdateTime = value;
	}
	
	public Date getLastUpdateTime() {
		return this.lastUpdateTime;
	}
	
	public String getStringLastUpdateTime() {
		if(this.lastUpdateTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.lastUpdateTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

