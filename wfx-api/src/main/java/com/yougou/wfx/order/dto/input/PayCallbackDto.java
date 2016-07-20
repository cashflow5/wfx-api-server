/*
 * 类名 PayCallbackDto.java
 *
 * 版本信息 
 *
 * 日期 2013-1-21
 *
 * 版权声明Copyright (C) 2013 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.input;

import java.io.Serializable;
//import com.yougou.pay.exception.TradeBizException;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 支付成功回调Vo
 * 
 * @author luoq
 * 
 */
public class PayCallbackDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/**订单号**/
	private String orderNo;
	
	/**支付号 如：订单号_1 、订单号_2 **/
	private String outTradeNo;
	
	/** 银行交易号**/
	private String bankTradeNo;
	
	/**退款交易号 (该退款交易号是基于支付号outTradeNo或银行交易号bankTradeNo二选一)**/
	private String refundTradeNo;

	/** 在线支付时间 **/
	private String tradeTime;

	/** 实际交易金额 **/
	private String tradeAmount;
	
	/** 在线支付内部编码**/
	private String bankNo;

	/** 在线支付名称 **/
	private String bankName;
	
	/**支付宝或微信分配优购的商户编号,方便财务区分收款帐户**/
	private String partnerId;
	
	/** 客户端来源 **/
	private String clientSource;

	/** 客户端ip **/
	private String clientAddress;
	
	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getBankTradeNo() {
		return bankTradeNo;
	}

	public void setBankTradeNo(String bankTradeNo) {
		this.bankTradeNo = bankTradeNo;
	}

	public String getRefundTradeNo() {
		return refundTradeNo;
	}

	public void setRefundTradeNo(String refundTradeNo) {
		this.refundTradeNo = refundTradeNo;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(String tradeAmount) {
		this.tradeAmount = tradeAmount;
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

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
