 /*
 * 版本信息
 
 * 日期 2016-03-28 17:15:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dto.input;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

/**
 * FinAlreadyIncomePageInputDto
 * @author he.xx
 * @Date 创建时间：2016-03-28 17:15:58
 */
public class FinAlreadyIncomePageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 买家名称
	 */
	private String customerName;
	/**
	 * 买家账号
	 */
	private String customerNo;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 下单时间
	 */
	private Date orderDate;
	/**
	 * 订单金额
	 */
	private Double orderAmount;
	/**
	 * 店铺名,即订单来源
	 */
	private String storeName;
	/**
	 * 店铺id,即订单来源id
	 */
	private String storeId;
	/**
	 * 支付号
	 */
	private String outTradeNo;
	/**
	 * 收款时间,即交易时间
	 */
	private Date tradeDate;
	/**
	 * 订单支付交易金额
	 */
	private Double tradeAmount;
	/**
	 * 银行支付交易号流水号
	 */
	private String bankTradeNo;
	/**
	 * 支付方式编码
	 */
	private String bankNo;
	/**
	 * 支付方式名称
	 */
	private String bankName;
	/**
	 * 收款类型,1-正常收款,2-重复收款,3-收款异常
	 */
	private Integer incomeType;
	/**
	 * 收款类型描述
	 */
	private String incomeTypeDesc;
	/**
	 * 收款账号
	 */
	private String incomeAccount;
	/**
	 * 收货款金额
	 */
	private Double productAmount;
	/**
	 * 收运费金额
	 */
	private Double fareAmount;
	/**
	 * 收款合计
	 */
	private Double incomedAmount;
	/**
	 * 操作员
	 */
	private String operator;
	/**
	 * 操作类型:1-系统,2-人工
	 */
	private Integer operateType;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	//收款（交易）开始时间
	private String tradeStartTime;
	//收款（交易）截止时间
	private String tradeEndTime;
	
	public FinAlreadyIncomePageInputDto(){
	}

	public FinAlreadyIncomePageInputDto(
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
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public String getStringCreateDate() {
		if(this.createDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setCustomerName(String value) {
		this.customerName = value;
	}
	
	public String getCustomerName() {
		return this.customerName == null ? null : this.customerName.trim();
	}
	public void setCustomerNo(String value) {
		this.customerNo = value;
	}
	
	public String getCustomerNo() {
		return this.customerNo == null ? null : this.customerNo.trim();
	}
	public void setOrderNo(String value) {
		this.orderNo = value;
	}
	
	public String getOrderNo() {
		return this.orderNo == null ? null : this.orderNo.trim();
	}
	public void setOrderDate(Date value) {
		this.orderDate = value;
	}
	
	public Date getOrderDate() {
		return this.orderDate;
	}
	
	public String getStringOrderDate() {
		if(this.orderDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.orderDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setOrderAmount(Double value) {
		this.orderAmount = value;
	}
	
	public Double getOrderAmount() {
		return this.orderAmount;
	}
	public void setStoreName(String value) {
		this.storeName = value;
	}
	
	public String getStoreName() {
		return this.storeName == null ? null : this.storeName.trim();
	}
	public void setStoreId(String value) {
		this.storeId = value;
	}
	
	public String getStoreId() {
		return this.storeId == null ? null : this.storeId.trim();
	}
	public void setOutTradeNo(String value) {
		this.outTradeNo = value;
	}
	
	public String getOutTradeNo() {
		return this.outTradeNo == null ? null : this.outTradeNo.trim();
	}
	public void setTradeDate(Date value) {
		this.tradeDate = value;
	}
	
	public Date getTradeDate() {
		return this.tradeDate;
	}
	
	public String getStringTradeDate() {
		if(this.tradeDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.tradeDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setTradeAmount(Double value) {
		this.tradeAmount = value;
	}
	
	public Double getTradeAmount() {
		return this.tradeAmount;
	}
	public void setBankTradeNo(String value) {
		this.bankTradeNo = value;
	}
	
	public String getBankTradeNo() {
		return this.bankTradeNo == null ? null : this.bankTradeNo.trim();
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
	public void setIncomeType(Integer value) {
		this.incomeType = value;
	}
	
	public Integer getIncomeType() {
		return this.incomeType;
	}
	public void setIncomeTypeDesc(String value) {
		this.incomeTypeDesc = value;
	}
	
	public String getIncomeTypeDesc() {
		return this.incomeTypeDesc == null ? null : this.incomeTypeDesc.trim();
	}
	public void setIncomeAccount(String value) {
		this.incomeAccount = value;
	}
	
	public String getIncomeAccount() {
		return this.incomeAccount == null ? null : this.incomeAccount.trim();
	}
	public void setProductAmount(Double value) {
		this.productAmount = value;
	}
	
	public Double getProductAmount() {
		return this.productAmount;
	}
	public void setFareAmount(Double value) {
		this.fareAmount = value;
	}
	
	public Double getFareAmount() {
		return this.fareAmount;
	}
	public void setIncomedAmount(Double value) {
		this.incomedAmount = value;
	}
	
	public Double getIncomedAmount() {
		return this.incomedAmount;
	}
	public void setOperator(String value) {
		this.operator = value;
	}
	
	public String getOperator() {
		return this.operator == null ? null : this.operator.trim();
	}
	public void setOperateType(Integer value) {
		this.operateType = value;
	}
	
	public Integer getOperateType() {
		return this.operateType;
	}
	public void setNote(String value) {
		this.note = value;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getNote() {
		return this.note == null ? null : this.note.trim();
	}
	
	public String getTradeStartTime() {
		return tradeStartTime;
	}

	public void setTradeStartTime(String tradeStartTime) {
		this.tradeStartTime = tradeStartTime;
	}

	public String getTradeEndTime() {
		return tradeEndTime;
	}

	public void setTradeEndTime(String tradeEndTime) {
		this.tradeEndTime = tradeEndTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
}
	
