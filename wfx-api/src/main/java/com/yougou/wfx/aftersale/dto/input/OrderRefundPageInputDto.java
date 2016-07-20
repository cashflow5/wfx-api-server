 /*
 * 版本信息
 
 * 日期 2016-04-01 11:08:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.aftersale.dto.input;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

/**
 * OrderRefundPageInputDto
 * @author luoq
 * @Date 创建时间：2016-04-01 11:08:55
 */
public class OrderRefundPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 退款单号
	 */
	private String refundNo;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 子订单号
	 */
	private String orderDetailId;
	/**
	 * 商品编号
	 */
	private String prodId;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 买家的注册账号
	 */
	private String buyerLoginName;
	/**
	 * 分销商id
	 */
	private String sellerId;
	/**
	 * 店铺ID
	 */
	private String shopId;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 交易总金额。
	 */
	private Double totalFee;
	/**
	 * 退还金额(退还给买家的金额)。
	 */
	private Double refundFee;
	/**
	 * 支付给卖家的金额(交易总金额-退还给买家的金额)。
	 */
	private Double payment;
	/**
	 * 退款状态
	 */
	private String status;
	/**
	 * 货物状态
	 */
	private String goodStatus;
	/**
	 * 物流公司名称
	 */
	private String companyName;
	/**
	 * 退货运单号
	 */
	private String sid;
	/**
	 * 退款原因
	 */
	private String reason;
	/**
	 * 退款说明
	 */
	private String description;
	/**
	 * 买家是否需要退货。
	 */
	private String hasGoodReturn;
	/**
	 * 退款对应的订单交易状态
	 */
	private String orderStatus;
	/**
	 * 退款单同步状态
	 */
	private String syncStatus;
	/**
	 * 同步描述’,用于保存返回退款单保存失败原因
	 */
	private String syncRemark;
	/**
	 * 纪录创建时间
	 */
	private Date createTime;
	/**
	 * 纪录修改时间
	 */
	private Date updateTime;
	/**
	 * 是否存在超时可选值:true(是)’,false(否)
	 */
	private String existTimeout;
	/**
	 * 超时时间
	 */
	private Date timeout;
	/**
	 * 退款阶段(onsale,aftersale)
	 */
	private String refundPhase;
	/**
	 * 退款版本号
	 */
	private String refundVersion;
	/**
	 * 退款扩展属性
	 */
	private String attribute;
	/**
	 * 退款类型
	 */
	private String refundType;
	/**
	 * 退货数量
	 */
	private Integer proNum;
	/**
	 * 订单对应的发货供货商名称
	 */
	private String supplierName;
	/**
	 * 退款单退款的时间
	 */
	private Date payTime;
	/**
	 * 售中/售后 
	 */
	private String isAfterReceived;
	
	/**
	 * 申请时间起始
	 */
	private String createTimeStart;
	
	/**
	 * 申请时间截止
	 */
	private String createTimeEnd;
	
	/**
	 * 退款单退款的时间起始
	 */
	private String payTimeStart;
	
	/**
	 * 退款单退款的时间截止
	 */
	private String payTimeEnd;
	
	/**
	 * 微分销订单编码
	 */
	private String wfxOrderNo;
	/**
	 * 拒绝退款的原因
	 */
	private String denyReason;
	
	public String getDenyReason() {
		return denyReason;
	}

	public void setDenyReason(String denyReason) {
		this.denyReason = denyReason;
	}

	public OrderRefundPageInputDto(){
	}

	public OrderRefundPageInputDto(
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
	public void setRefundNo(String value) {
		this.refundNo = value;
	}
	
	public String getRefundNo() {
		return this.refundNo == null ? null : this.refundNo.trim();
	}
	public void setOrderId(String value) {
		this.orderId = value;
	}
	
	public String getOrderId() {
		return this.orderId == null ? null : this.orderId.trim();
	}
	public void setOrderDetailId(String value) {
		this.orderDetailId = value;
	}
	
	public String getOrderDetailId() {
		return this.orderDetailId == null ? null : this.orderDetailId.trim();
	}
	public void setProdId(String value) {
		this.prodId = value;
	}
	
	public String getProdId() {
		return this.prodId == null ? null : this.prodId.trim();
	}
	public void setProdName(String value) {
		this.prodName = value;
	}
	
	public String getProdName() {
		return this.prodName == null ? null : this.prodName.trim();
	}
	public void setBuyerLoginName(String value) {
		this.buyerLoginName = value;
	}
	
	public String getBuyerLoginName() {
		return this.buyerLoginName == null ? null : this.buyerLoginName.trim();
	}
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setShopId(String value) {
		this.shopId = value;
	}
	
	public String getShopId() {
		return this.shopId == null ? null : this.shopId.trim();
	}
	public void setShopName(String value) {
		this.shopName = value;
	}
	
	public String getShopName() {
		return this.shopName == null ? null : this.shopName.trim();
	}
	public void setTotalFee(Double value) {
		this.totalFee = value;
	}
	
	public Double getTotalFee() {
		return this.totalFee;
	}
	public void setRefundFee(Double value) {
		this.refundFee = value;
	}
	
	public Double getRefundFee() {
		return this.refundFee;
	}
	public void setPayment(Double value) {
		this.payment = value;
	}
	
	public Double getPayment() {
		return this.payment;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status == null ? null : this.status.trim();
	}
	public void setGoodStatus(String value) {
		this.goodStatus = value;
	}
	
	public String getGoodStatus() {
		return this.goodStatus == null ? null : this.goodStatus.trim();
	}
	public void setCompanyName(String value) {
		this.companyName = value;
	}
	
	public String getCompanyName() {
		return this.companyName == null ? null : this.companyName.trim();
	}
	public void setSid(String value) {
		this.sid = value;
	}
	
	public String getSid() {
		return this.sid == null ? null : this.sid.trim();
	}
	public void setReason(String value) {
		this.reason = value;
	}
	
	public String getReason() {
		return this.reason == null ? null : this.reason.trim();
	}
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description == null ? null : this.description.trim();
	}
	public void setHasGoodReturn(String value) {
		this.hasGoodReturn = value;
	}
	
	public String getHasGoodReturn() {
		return this.hasGoodReturn == null ? null : this.hasGoodReturn.trim();
	}
	public void setOrderStatus(String value) {
		this.orderStatus = value;
	}
	
	public String getOrderStatus() {
		return this.orderStatus == null ? null : this.orderStatus.trim();
	}
	public void setSyncStatus(String value) {
		this.syncStatus = value;
	}
	
	public String getSyncStatus() {
		return this.syncStatus == null ? null : this.syncStatus.trim();
	}
	public void setSyncRemark(String value) {
		this.syncRemark = value;
	}
	
	public String getSyncRemark() {
		return this.syncRemark == null ? null : this.syncRemark.trim();
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
	public void setExistTimeout(String value) {
		this.existTimeout = value;
	}
	
	public String getExistTimeout() {
		return this.existTimeout == null ? null : this.existTimeout.trim();
	}
	public void setTimeout(Date value) {
		this.timeout = value;
	}
	
	public Date getTimeout() {
		return this.timeout;
	}
	
	public String getStringTimeout() {
		if(this.timeout == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.timeout, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setRefundPhase(String value) {
		this.refundPhase = value;
	}
	
	public String getRefundPhase() {
		return this.refundPhase == null ? null : this.refundPhase.trim();
	}
	public void setRefundVersion(String value) {
		this.refundVersion = value;
	}
	
	public String getRefundVersion() {
		return this.refundVersion == null ? null : this.refundVersion.trim();
	}
	public void setAttribute(String value) {
		this.attribute = value;
	}
	
	public String getAttribute() {
		return this.attribute == null ? null : this.attribute.trim();
	}
	public void setRefundType(String value) {
		this.refundType = value;
	}
	
	public String getRefundType() {
		return this.refundType == null ? null : this.refundType.trim();
	}
	public void setProNum(Integer value) {
		value = value == null ? 0 : value;
		this.proNum = value;
	}
	
	public Integer getProNum() {
		return this.proNum == null ? 0 : this.proNum;
	}
	public void setSupplierName(String value) {
		this.supplierName = value;
	}
	
	public String getSupplierName() {
		return this.supplierName == null ? null : this.supplierName.trim();
	}
	public void setPayTime(Date value) {
		this.payTime = value;
	}
	
	public Date getPayTime() {
		return this.payTime;
	}
	
	public String getStringPayTime() {
		if(this.payTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.payTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setIsAfterReceived(String value) {
		this.isAfterReceived = value;
	}
	
	public String getIsAfterReceived() {
		return this.isAfterReceived == null ? null : this.isAfterReceived.trim();
	}
	
	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public String getPayTimeStart() {
		return payTimeStart;
	}

	public void setPayTimeStart(String payTimeStart) {
		this.payTimeStart = payTimeStart;
	}

	public String getPayTimeEnd() {
		return payTimeEnd;
	}

	public void setPayTimeEnd(String payTimeEnd) {
		this.payTimeEnd = payTimeEnd;
	}

	public String getWfxOrderNo() {
		return wfxOrderNo;
	}

	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
