package com.yougou.wfx.aftersale.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

public class OrderAfterSaleDto extends OutputDto{

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 店铺id
	 */
	private String shopId;
	/**
	 * 退款单id
	 */
	private String refundId;
	/**
	 * 退款单编号
	 */
	private String refundNo;
	/**
	 * 主订单id
	 */
	private String orderId;
	/**
	 * 子订单id
	 */
	private String orderDetailId;
	/**
	 * 店铺名称
	 */
	private String shopName;
	
	/**
	 * 店铺编码
	 */
	private String shopCode;
	
	/**
	 * 退款单状态
	 */
	private String status;
	/**
	 * 商品id
	 */
	private String prodId;
	/**
	 * 小图url
	 */
	private String smallPic;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 商品属性（颜色，尺码）
	 */
	private String prodSpec;
	/**
	 * 交易金额
	 */
	private Double tradeFee;
	/**
	 * 退款金额
	 */
	private Double refundFee;
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public String getRefundNo() {
		return refundNo;
	}
	public void setRefundNo(String refundNo) {
		this.refundNo = refundNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdSpec() {
		return prodSpec;
	}
	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}
	public Double getTradeFee() {
		return tradeFee;
	}
	public void setTradeFee(Double tradeFee) {
		this.tradeFee = tradeFee;
	}
	public Double getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(Double refundFee) {
		this.refundFee = refundFee;
	}
	public String getShopCode() {
		return shopCode;
	}
	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}
}
