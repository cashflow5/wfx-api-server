 /*
 * 版本信息
 
 * 日期 2016-04-16 16:08:23
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dto.output;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * SellerBankOutputDto
 * @author luoq
 * @Date 创建时间：2016-04-16 16:08:24
 */
public class SellerBankOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 分销商ID
	 */
	private String sellerId;
	/**
	 * 真实姓名
	 */
	private String trueName;
	/**
	 * 身份证号码
	 */
	private String idCardNo;
	/**
	 * 银行编码
	 */
	private String bankNo;
	/**
	 * 开户银行名称
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String bankAccount;
	/**
	 * 省
	 */
	private String bankProvince;
	/**
	 * 市
	 */
	private String bankCity;
	/**
	 * 支行名称
	 */
	private String bankSubName;
	/**
	 * 最后使用时间
	 */
	private Date lastUseTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 删除标志 0：未删除 1：已删除
	 */
	private Integer deleteFlag;
	
	/**
	 * 授权信息表主键
	 */
	private String authorizeId;
	
	/**
	 * 授权信息
	 */
	private SellerAuthorizeOutputDto sellerAuthorizeOutputDto;
	
	public SellerBankOutputDto(){
	}

	public String getAuthorizeId() {
		return authorizeId;
	}

	public void setAuthorizeId(String authorizeId) {
		this.authorizeId = authorizeId;
	}

	public SellerAuthorizeOutputDto getSellerAuthorizeOutputDto() {
		return sellerAuthorizeOutputDto;
	}

	public void setSellerAuthorizeOutputDto(
			SellerAuthorizeOutputDto sellerAuthorizeOutputDto) {
		this.sellerAuthorizeOutputDto = sellerAuthorizeOutputDto;
	}

	public SellerBankOutputDto(
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
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setTrueName(String value) {
		this.trueName = value;
	}
	
	public String getTrueName() {
		return this.trueName == null ? null : this.trueName.trim();
	}
	public void setIdCardNo(String value) {
		this.idCardNo = value;
	}
	
	public String getIdCardNo() {
		return this.idCardNo == null ? null : this.idCardNo.trim();
	}
	public void setBankNo(String value) {
		this.bankNo = value;
	}
	
	public String getBankNo() {
		return this.bankNo == null ? null : this.bankNo.trim();
	}
	public void setBankName(String value) {
		this.bankName = value;
	}
	
	public String getBankName() {
		return this.bankName == null ? null : this.bankName.trim();
	}
	public void setBankAccount(String value) {
		this.bankAccount = value;
	}
	
	public String getBankAccount() {
		return this.bankAccount == null ? null : this.bankAccount.trim();
	}
	public void setBankProvince(String value) {
		this.bankProvince = value;
	}
	
	public String getBankProvince() {
		return this.bankProvince == null ? null : this.bankProvince.trim();
	}
	public void setBankCity(String value) {
		this.bankCity = value;
	}
	
	public String getBankCity() {
		return this.bankCity == null ? null : this.bankCity.trim();
	}
	public void setBankSubName(String value) {
		this.bankSubName = value;
	}
	
	public String getBankSubName() {
		return this.bankSubName == null ? null : this.bankSubName.trim();
	}
	public void setLastUseTime(Date value) {
		this.lastUseTime = value;
	}
	
	public Date getLastUseTime() {
		return this.lastUseTime;
	}
	
	public String getStringLastUseTime() {
		if(this.lastUseTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.lastUseTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
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
	public void setDeleteFlag(Integer value) {
		value = value == null ? 0 : value;
		this.deleteFlag = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag == null ? 0 : this.deleteFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

