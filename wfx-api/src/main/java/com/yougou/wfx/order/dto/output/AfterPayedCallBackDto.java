package com.yougou.wfx.order.dto.output;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

public class AfterPayedCallBackDto extends OutputDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订单编号
	 */
	private String orderNo;
	
	/**
	 * 银行编号
	 */
	private String bankNo;
	/**
	 * 主订单id
	 */
	private String orderId;
	/**
	 * 银行名称
	 */
	private String bankName;
	/**
	 * 实付金额
	 */
	private double tradeAmount;
	
	/**
	 * 支付时间
	 */
	private Date tradeTime;
	
	/**
	 * 支付方式 alipay/wechatpay
	 */
	private String payType;
	
	/**
	 * 支付状态 见PayCallBackStateEnum.getCode()
	 */
	private String status;

	/**
	 * 买家升级为优粉之后的新的店铺编码
	 */
	private String shopCode;
	
	/**
	 * 买家升级为优粉之后的新的店铺Id
	 */
	private String shopId;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Date getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Date tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getShopCode() {
		return shopCode;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
