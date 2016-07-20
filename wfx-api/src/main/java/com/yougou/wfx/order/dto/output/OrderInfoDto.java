 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.output;

import java.util.Date;
import java.util.List;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * OrderInfoDto
 * 保存主订单及子订单的信息
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public class OrderInfoDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 微分销订单号
	 */
	private String wfxOrderNo;
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
	 * supplierId
	 */
	private String supplierId;
	/**
	 * 支付方式
	 */
	private String payType;
	/**
	 * 支付方式名称
	 */
	private String payTypeName;
	/**
	 * 付款时间
	 */
	private Date payTime;
	/**
	 * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
	 */
	private Double payment;
	/**
	 * 商品购买数量。取值范围：大于零的整数
	 */
	private Integer num;
	/**
	 * 商品金额（优惠前总金额）
	 */
	private Double totalFee;
	/**
	 * 系统优惠金额
	 */
	private Double discountFee;
	/**
	 * 邮费。
	 */
	private Double postFee;
	/**
	 * 订单状态。
	 */
	private String status;
	/**
	 * 买家账号
	 */
	private String buyerId;
	/**
	 * buyerAccount
	 */
	private String buyerAccount;
	/**
	 * 买家留言
	 */
	private String buyerMessage;
	/**
	 * 买家昵称
	 */
	private String buyerNick;
	/**
	 * 收货人的姓名
	 */
	private String receiverName;
	/**
	 * 收货人的手机号码
	 */
	private String receiverMobile;
	/**
	 * 收货人的电话号码
	 */
	private String receiverPhone;
	/**
	 * 身份证号码
	 */
	private String idCardNo;
	/**
	 * 收货人的所在省份
	 */
	private String receiverState;
	/**
	 * 收货人的所在城市
	 */
	private String receiverCity;
	/**
	 * 收货人的所在地区
	 */
	private String receiverDistrict;
	/**
	 * 收货人的详细地址
	 */
	private String receiverAddress;
	/**
	 * 收货人的邮编
	 */
	private String receiverZip;
	/**
	 * 创建交易时的物流方式（交易完成前，物流方式有可能改变，但系统里的这个字段一直不变）。可选值：ems, express, post, free, virtual。
	 */
	private String shippingType;
	/**
	 * 收货时间段
	 */
	private Integer receiveTimeRange;
	/**
	 * 订单创建时间。
	 */
	private Date createdTime;
	/**
	 * 订单修改时间。
	 */
	private Date updateTime;
	/**
	 * 确认收货时间
	 */
	private Date confirmTime;
	/**
	 * 订单关闭时间
	 */
	private Date closeTime;
	/**
	 * 人工退款标志
	 */
	private Integer manualRefundFlag;
	/**
	 * 订单预计佣金一级
	 */
	private Double commissionLevel1;
	/**
	 * 订单预计佣金二级
	 */
	private Double commissionLevel2;
	/**
	 * 订单预计佣金三级
	 */
	private Double commissionLevel3;
	
	/**
	 * 佣金生成状态
	 */
	private Integer commissionStatus;
	
	private List<OrderDetailDto> orderDetailList;
	/**
	 * 店铺编码
	 */
	private String shopCode;
	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
	public OrderInfoDto(){
	}

	public OrderInfoDto(
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
	public void setWfxOrderNo(String value) {
		this.wfxOrderNo = value;
	}
	
	public String getWfxOrderNo() {
		return this.wfxOrderNo == null ? null : this.wfxOrderNo.trim();
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
	public void setSupplierId(String value) {
		this.supplierId = value;
	}
	
	public String getSupplierId() {
		return this.supplierId == null ? null : this.supplierId.trim();
	}
	public void setPayType(String value) {
		this.payType = value;
	}
	
	public String getPayType() {
		return this.payType == null ? null : this.payType.trim();
	}
	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
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
	public void setPayment(Double value) {
		this.payment = value;
	}
	
	public Double getPayment() {
		return this.payment;
	}
	public void setNum(Integer value) {
		value = value == null ? 0 : value;
		this.num = value;
	}
	
	public Integer getNum() {
		return this.num == null ? 0 : this.num;
	}
	public void setTotalFee(Double value) {
		this.totalFee = value;
	}
	
	public Double getTotalFee() {
		return this.totalFee;
	}
	public void setDiscountFee(Double value) {
		this.discountFee = value;
	}
	
	public Double getDiscountFee() {
		return this.discountFee;
	}
	public void setPostFee(Double value) {
		this.postFee = value;
	}
	
	public Double getPostFee() {
		return this.postFee;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status == null ? null : this.status.trim();
	}
	public void setBuyerId(String value) {
		this.buyerId = value;
	}
	
	public String getBuyerId() {
		return this.buyerId == null ? null : this.buyerId.trim();
	}
	public void setBuyerAccount(String value) {
		this.buyerAccount = value;
	}
	
	public String getBuyerAccount() {
		return this.buyerAccount == null ? null : this.buyerAccount.trim();
	}
	public void setBuyerMessage(String value) {
		this.buyerMessage = value;
	}
	
	public String getBuyerMessage() {
		return this.buyerMessage == null ? null : this.buyerMessage.trim();
	}
	public void setBuyerNick(String value) {
		this.buyerNick = value;
	}
	
	public String getBuyerNick() {
		return this.buyerNick == null ? null : this.buyerNick.trim();
	}
	public void setReceiverName(String value) {
		this.receiverName = value;
	}
	
	public String getReceiverName() {
		return this.receiverName == null ? null : this.receiverName.trim();
	}
	public void setReceiverMobile(String value) {
		this.receiverMobile = value;
	}
	
	public String getReceiverMobile() {
		return this.receiverMobile == null ? null : this.receiverMobile.trim();
	}
	public void setReceiverPhone(String value) {
		this.receiverPhone = value;
	}
	
	public String getReceiverPhone() {
		return this.receiverPhone == null ? null : this.receiverPhone.trim();
	}
	public void setIdCardNo(String value) {
		this.idCardNo = value;
	}
	
	public String getIdCardNo() {
		return this.idCardNo == null ? null : this.idCardNo.trim();
	}
	public void setReceiverState(String value) {
		this.receiverState = value;
	}
	
	public String getReceiverState() {
		return this.receiverState == null ? null : this.receiverState.trim();
	}
	public void setReceiverCity(String value) {
		this.receiverCity = value;
	}
	
	public String getReceiverCity() {
		return this.receiverCity == null ? null : this.receiverCity.trim();
	}
	public void setReceiverDistrict(String value) {
		this.receiverDistrict = value;
	}
	
	public String getReceiverDistrict() {
		return this.receiverDistrict == null ? null : this.receiverDistrict.trim();
	}
	public void setReceiverAddress(String value) {
		this.receiverAddress = value;
	}
	
	public String getReceiverAddress() {
		return this.receiverAddress == null ? null : this.receiverAddress.trim();
	}
	public void setReceiverZip(String value) {
		this.receiverZip = value;
	}
	
	public String getReceiverZip() {
		return this.receiverZip == null ? null : this.receiverZip.trim();
	}
	public void setShippingType(String value) {
		this.shippingType = value;
	}
	
	public String getShippingType() {
		return this.shippingType == null ? null : this.shippingType.trim();
	}
	public void setReceiveTimeRange(Integer value) {
		value = value == null ? 0 : value;
		this.receiveTimeRange = value;
	}
	
	public Integer getReceiveTimeRange() {
		return this.receiveTimeRange == null ? 0 : this.receiveTimeRange;
	}
	public void setCreatedTime(Date value) {
		this.createdTime = value;
	}
	
	public Date getCreatedTime() {
		return this.createdTime;
	}
	
	public String getStringCreatedTime() {
		if(this.createdTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createdTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
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
	public void setConfirmTime(Date value) {
		this.confirmTime = value;
	}
	
	public Date getConfirmTime() {
		return this.confirmTime;
	}
	
	public String getStringConfirmTime() {
		if(this.confirmTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.confirmTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setCloseTime(Date value) {
		this.closeTime = value;
	}
	
	public Date getCloseTime() {
		return this.closeTime;
	}
	
	public String getStringCloseTime() {
		if(this.closeTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.closeTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setManualRefundFlag(Integer value) {
		value = value == null ? 0 : value;
		this.manualRefundFlag = value;
	}
	
	public Integer getManualRefundFlag() {
		return this.manualRefundFlag == null ? 0 : this.manualRefundFlag;
	}

	public Double getCommissionLevel1() {
		return commissionLevel1;
	}

	public void setCommissionLevel1(Double commissionLevel1) {
		this.commissionLevel1 = commissionLevel1;
	}

	public Double getCommissionLevel2() {
		return commissionLevel2;
	}

	public void setCommissionLevel2(Double commissionLevel2) {
		this.commissionLevel2 = commissionLevel2;
	}

	public Double getCommissionLevel3() {
		return commissionLevel3;
	}

	public void setCommissionLevel3(Double commissionLevel3) {
		this.commissionLevel3 = commissionLevel3;
	}

	public List<OrderDetailDto> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<OrderDetailDto> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public Integer getCommissionStatus() {
		return commissionStatus;
	}

	public void setCommissionStatus(Integer commissionStatus) {
		this.commissionStatus = commissionStatus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

