 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
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
 * FinReturnDebtPageInputDto
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
public class FinReturnDebtPageInputDto extends InputDto{
	
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
	 * 主订单号
	 */
	private String orderNo;
	/**
	 * 子订单号
	 */
	private String orderSubNo;
	/**
	 * 退款单编号
	 */
	private String backNo;
	/**
	 * 申请退款时间
	 */
	private Date applyDate;
	/**
	 * 申请退款状态（售后专用状态）
	 */
	private String applyStatus;
	/**
	 * 申请退款金额
	 */
	private java.lang.Double applyAmount;
	/**
	 * 客户名称
	 */
	private String customerName;
	/**
	 * 客户账号
	 */
	private String customerNo;
	/**
	 * 店铺名
	 */
	private String storeName;
	/**
	 * 店铺id
	 */
	private String storeId;
	
	/**
	 * 银行交易流水号
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
	 * 退款类型
	 */
	private String refundType;
	/**
	 * 退款状态（财务专用，对接支付系统）
	 */
	private Integer refundStatus;
	/**
	 * 退款时间
	 */
	private Date refundDate;
	/**
	 * 实际退款金额
	 */
	private java.lang.Double refundAmount;
	/**
	 * 退款原因
	 */
	private String refundNote;
	/**
	 * 退款说明
	 */
	private String refundDesc;
	/**
	 * 操作员
	 */
	private String operator;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	
	//实际退款开始时间
	private String refundStartTime;
	//实际退款截止时间
	private String refundEndTime;
	//申请退款开始时间
	private String applyStartTime;
	//申请退款截止时间
	private String applyEndTime;
	
	/**
	 * 选项卡查询标识，1-仅退款，2-退货退款，3-全部
	 */
	private Integer tabQueryFlag;
	
	public FinReturnDebtPageInputDto(){
	}

	public FinReturnDebtPageInputDto(
		String id
	){
		this.id = id;
	}

	public String getRefundStartTime() {
		return refundStartTime;
	}
	public void setRefundStartTime(String refundStartTime) {
		this.refundStartTime = refundStartTime;
	}

	public String getRefundEndTime() {
		return refundEndTime;
	}
	public void setRefundEndTime(String refundEndTime) {
		this.refundEndTime = refundEndTime;
	}

	public String getApplyStartTime() {
		return applyStartTime;
	}
	public void setApplyStartTime(String applyStartTime) {
		this.applyStartTime = applyStartTime;
	}

	public String getApplyEndTime() {
		return applyEndTime;
	}
	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
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
	public void setOrderNo(String value) {
		this.orderNo = value;
	}
	
	public String getOrderNo() {
		return this.orderNo == null ? null : this.orderNo.trim();
	}
	
	public String getOrderSubNo() {
		return orderSubNo;
	}

	public void setOrderSubNo(String orderSubNo) {
		this.orderSubNo = orderSubNo;
	}

	public void setBackNo(String value) {
		this.backNo = value;
	}
	
	public String getBackNo() {
		return this.backNo == null ? null : this.backNo.trim();
	}
	public void setApplyDate(Date value) {
		this.applyDate = value;
	}
	
	public Date getApplyDate() {
		return this.applyDate;
	}
	
	public String getStringApplyDate() {
		if(this.applyDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.applyDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	
	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
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
	
	public java.lang.Double getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(java.lang.Double applyAmount) {
		this.applyAmount = applyAmount;
	}
	
	public String getBankTradeNo() {
		return bankTradeNo;
	}

	public void setBankTradeNo(String bankTradeNo) {
		this.bankTradeNo = bankTradeNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public void setRefundType(String value) {
		this.refundType = value;
	}
	public String getRefundType() {
		return this.refundType;
	}
	
	public Integer getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(Integer refundStatus) {
		this.refundStatus = refundStatus;
	}

	public void setRefundDate(Date value) {
		this.refundDate = value;
	}
	public Date getRefundDate() {
		return this.refundDate;
	}
	
	public String getStringRefundDate() {
		if(this.refundDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.refundDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setRefundAmount(java.lang.Double value) {
		this.refundAmount = value;
	}
	
	public java.lang.Double getRefundAmount() {
		return this.refundAmount;
	}
	public void setRefundNote(String value) {
		this.refundNote = value;
	}
	
	public String getRefundNote() {
		return this.refundNote == null ? null : this.refundNote.trim();
	}
	
	public String getRefundDesc() {
		return refundDesc;
	}
	public void setRefundDesc(String refundDesc) {
		this.refundDesc = refundDesc;
	}

	public void setOperator(String value) {
		this.operator = value;
	}
	
	public String getOperator() {
		return this.operator == null ? null : this.operator.trim();
	}
	public void setNote(String value) {
		this.note = value;
	}
	
	public String getNote() {
		return this.note == null ? null : this.note.trim();
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getTabQueryFlag() {
		return tabQueryFlag;
	}

	public void setTabQueryFlag(Integer tabQueryFlag) {
		this.tabQueryFlag = tabQueryFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
