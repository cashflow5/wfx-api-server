 /*
 * 版本信息
 
 * 日期 2016-03-29 14:08:45
 
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
 * FinSellerInfoDetailOutputDto
 * @author wfx
 * @Date 创建时间：2016-03-29 14:08:45
 */
public class FinSellerInfoDetailOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 分销商账户明细表ID
	 */
	private String id;
	/**
	 * 交易流水号
	 */
	private String transactionNumber;
	/**
	 * 交易时间
	 */
	private Date transactionTime;
	/**
	 * 交易类型(1：佣金收益，2：提现)
	 */
	private String transactionType;
	/**
	 * 交易标识
	 * 1：收入，2-支出
	 */
	private String transactionFlag;
	/**
	 * 收入金额
	 */
	private Double incomeAmount;
	/**
	 * 支出金额
	 */
	private Double expendAmount;
	/**
	 * 账户余额
	 */
	private Double accountBalance;
	/**
	 * 支付方式编码，详见 BankCompanyEnum
	 */
	private String paymentStyle;
	/**
	 * 支付方式名称（支付方式编码对应的名称）
	 */
	private String paymentStyleName;
	/**
	 * 交易订单号
	 */
	private String transactionOrderNum;
	/**
	 * 单据状态(1：交易成功，2：处理中，3：交易关闭，4：交易失败)
	 */
	private String billState;
	/**
	 * 操作人
	 */
	private String operater;
	/**
	 * 操作备注
	 */
	private String operateNote;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 分销商ID
	 */
	private String sellerId;
	/**
	 * 下级分销商ID
	 */
	private String sellerNextId;

	public FinSellerInfoDetailOutputDto(){
	}

	public FinSellerInfoDetailOutputDto(
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
	public void setTransactionNumber(String value) {
		this.transactionNumber = value;
	}
	
	public String getTransactionNumber() {
		return this.transactionNumber == null ? null : this.transactionNumber.trim();
	}
	public void setTransactionTime(Date value) {
		this.transactionTime = value;
	}
	
	public Date getTransactionTime() {
		return this.transactionTime;
	}
	
	public String getStringTransactionTime() {
		if(this.transactionTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.transactionTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setTransactionType(String value) {
		this.transactionType = value;
	}
	
	public String getTransactionType() {
		return this.transactionType == null ? null : this.transactionType.trim();
	}
	public void setIncomeAmount(Double value) {
		this.incomeAmount = value;
	}
	
	public Double getIncomeAmount() {
		return this.incomeAmount;
	}
	public void setExpendAmount(Double value) {
		this.expendAmount = value;
	}
	
	public Double getExpendAmount() {
		return this.expendAmount;
	}
	public void setAccountBalance(Double value) {
		this.accountBalance = value;
	}
	
	public Double getAccountBalance() {
		return this.accountBalance;
	}
	public void setPaymentStyle(String value) {
		this.paymentStyle = value;
	}
	
	public String getPaymentStyle() {
		return this.paymentStyle == null ? null : this.paymentStyle.trim();
	}
	
	public String getTransactionFlag() {
		return transactionFlag;
	}

	public void setTransactionFlag(String transactionFlag) {
		this.transactionFlag = transactionFlag;
	}

	public String getPaymentStyleName() {
		return paymentStyleName;
	}

	public void setPaymentStyleName(String paymentStyleName) {
		this.paymentStyleName = paymentStyleName;
	}

	public void setTransactionOrderNum(String value) {
		this.transactionOrderNum = value;
	}
	
	public String getTransactionOrderNum() {
		return this.transactionOrderNum == null ? null : this.transactionOrderNum.trim();
	}
	public void setBillState(String value) {
		this.billState = value;
	}
	
	public String getBillState() {
		return this.billState == null ? null : this.billState.trim();
	}
	public void setOperater(String value) {
		this.operater = value;
	}
	
	public String getOperater() {
		return this.operater == null ? null : this.operater.trim();
	}
	
	public String getOperateNote() {
		return operateNote;
	}

	public void setOperateNote(String operateNote) {
		this.operateNote = operateNote;
	}

	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark == null ? null : this.remark.trim();
	}
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}

	public String getSellerNextId() {
		return sellerNextId;
	}

	public void setSellerNextId(String sellerNextId) {
		this.sellerNextId = sellerNextId;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

