 /*
 * 版本信息
 
 * 日期 2016-03-31 10:10:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dto.output;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * FinSellerAccountWithdrawOutputDto
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
public class FinSellerAccountWithdrawOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 提现申请单号
	 */
	private String withdrawApplyNo;
	/**
	 * 申请时间
	 */
	private Date applyTime;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 分销商账号
	 */
	private String sellerAccount;
	/**
	 * 账户余额
	 */
	private Double accountBalance;
	/**
	 * 实收费用
	 */
	private Double actualReceivedAmount;
	/**
	 * 服务费
	 */
	private Double serviceAmount;
	/**
	 * 提现金额
	 */
	private Double withdrawAmount;
	/**
	 * 开户名
	 */
	private String accountName;
	/**
	 * 开户行编码（*），即支付方式，详见 BankCompanyEnum
	 */
	private String accountBankNo;
	/**
	 * 开户行简称（例：招商银行，银行编码对应的名称）
	 */
	private String accountBankName;
	/**
	 * 开户行全称（例：某某银行支行）
	 */
	private String accountBankAllName;
	/**
	 * 开户账号
	 */
	private String accountNo;
	/**
	 * 申请原因说明
	 */
	private String applyReason;
	/**
	 * 申请人
	 */
	private String applyer;
	/**
	 * 审核人
	 */
	private String operater;
	/**
	 * 审核时间
	 */
	private Date operaterTime;
	/**
	 * 付款人
	 */
	private String modifier;
	/**
	 * 付款时间，即提现时间
	 */
	private Date modifyTime;
	/**
	 * 提现状态：1-待审核,2-已审核,3-已提现,4-审核不通过
	 * 说明：提现中（待审核和已审核），已提现（已提现），提现失败（审核不通过）
	 */
	private String billStatus;
	/**
	 * 备注说明
	 */
	private String remark;
	
	/**
	 * 分销商ID.
	 */
	private String sellerId;
	
	/**
	 * 对应的交易明细ID
	 */
	private String sellerDetailId;

	public FinSellerAccountWithdrawOutputDto(){
	}

	public FinSellerAccountWithdrawOutputDto(
		String id,
		String withdrawApplyNo
	){
		this.id = id;
		this.withdrawApplyNo = withdrawApplyNo;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setWithdrawApplyNo(String value) {
		this.withdrawApplyNo = value;
	}
	
	public String getWithdrawApplyNo() {
		return this.withdrawApplyNo == null ? null : this.withdrawApplyNo.trim();
	}
	public void setApplyTime(Date value) {
		this.applyTime = value;
	}
	
	public Date getApplyTime() {
		return this.applyTime;
	}
	
	public String getStringApplyTime() {
		if(this.applyTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.applyTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setShopName(String value) {
		this.shopName = value;
	}
	
	public String getShopName() {
		return this.shopName == null ? null : this.shopName.trim();
	}
	public void setSellerAccount(String value) {
		this.sellerAccount = value;
	}
	
	public String getSellerAccount() {
		return this.sellerAccount == null ? null : this.sellerAccount.trim();
	}
	public void setAccountBalance(Double value) {
		this.accountBalance = value;
	}
	
	public Double getAccountBalance() {
		return this.accountBalance;
	}
	public void setActualReceivedAmount(Double value) {
		this.actualReceivedAmount = value;
	}
	
	public Double getActualReceivedAmount() {
		return this.actualReceivedAmount;
	}
	public void setServiceAmount(Double value) {
		this.serviceAmount = value;
	}
	
	public Double getServiceAmount() {
		return this.serviceAmount;
	}
	public void setWithdrawAmount(Double value) {
		this.withdrawAmount = value;
	}
	
	public Double getWithdrawAmount() {
		return this.withdrawAmount;
	}

	public String getAccountBankName() {
		return accountBankName;
	}

	public void setAccountBankName(String accountBankName) {
		this.accountBankName = accountBankName;
	}

	public String getAccountBankAllName() {
		return accountBankAllName;
	}

	public void setAccountBankAllName(String accountBankAllName) {
		this.accountBankAllName = accountBankAllName;
	}

	public void setAccountName(String value) {
		this.accountName = value;
	}
	
	public String getAccountName() {
		return this.accountName == null ? null : this.accountName.trim();
	}
	public void setAccountBankNo(String value) {
		this.accountBankNo = value;
	}
	
	public String getAccountBankNo() {
		return this.accountBankNo == null ? null : this.accountBankNo.trim();
	}
	public void setAccountNo(String value) {
		this.accountNo = value;
	}
	
	public String getAccountNo() {
		return this.accountNo == null ? null : this.accountNo.trim();
	}
	public void setApplyReason(String value) {
		this.applyReason = value;
	}
	
	public String getApplyReason() {
		return this.applyReason == null ? null : this.applyReason.trim();
	}
	public void setApplyer(String value) {
		this.applyer = value;
	}
	
	public String getApplyer() {
		return this.applyer == null ? null : this.applyer.trim();
	}
	public void setOperater(String value) {
		this.operater = value;
	}
	
	public String getOperater() {
		return this.operater == null ? null : this.operater.trim();
	}
	public void setOperaterTime(Date value) {
		this.operaterTime = value;
	}
	
	public Date getOperaterTime() {
		return this.operaterTime;
	}
	
	public String getStringOperaterTime() {
		if(this.operaterTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.operaterTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setModifier(String value) {
		this.modifier = value;
	}
	
	public String getModifier() {
		return this.modifier == null ? null : this.modifier.trim();
	}
	public void setModifyTime(Date value) {
		this.modifyTime = value;
	}
	
	public Date getModifyTime() {
		return this.modifyTime;
	}
	
	public String getStringModifyTime() {
		if(this.modifyTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.modifyTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setBillStatus(String value) {
		this.billStatus = value;
	}
	
	public String getBillStatus() {
		return this.billStatus == null ? null : this.billStatus.trim();
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark == null ? null : this.remark.trim();
	}

	/**
	 * @return the sellerId
	 */
	public String getSellerId() {
		return sellerId;
	}

	/**
	 * @param sellerId the sellerId to set
	 */
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	/**
	 * @return the sellerDetailId
	 */
	public String getSellerDetailId() {
		return sellerDetailId;
	}

	/**
	 * @param sellerDetailId the sellerDetailId to set
	 */
	public void setSellerDetailId(String sellerDetailId) {
		this.sellerDetailId = sellerDetailId;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}

