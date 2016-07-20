package com.yougou.wfx.outside.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: OrderDetail</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderDetail implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 子订单流水号
	 */
	private String wfxOrderDetailNo;
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
	 * 主订单ID
	 */
	private String orderId;
	/**
	 * 商品id
	 */
	private String commodityId;
	/**
	 * 商品最小库存单位id
	 */
	private String prodId;
	/**
	 * 商品编码
	 */
	private String commodityNo;
	/**
	 * 货品编码
	 */
	private String productNo;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 商品规格(颜色，尺码等）
	 */
	private String prodSpec;
	/**
	 * 子订单实付金额。
	 */
	private Double payment;
	/**
	 * 购买数量。
	 */
	private Integer num;
	/**
	 * 商品价格
	 */
	private Double price;
	/**
	 * 订单优惠金额
	 */
	private Double discountFee;
	/**
	 * 应付金额（商品价格 * 商品数量 + 手工调整金额 - 订单优惠金额）。
	 */
	private Double totalFee;
	/**
	 * 单品级优惠
	 */
	private Double proDiscountFee;
	
	/**
	 * 佣金总和
	 */
	private Double commissionTotal;
	/**
	 * 退款状态。
	 */
	private String refundStatus;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 退货地址编码
	 */
	private String returnAddressNo;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getWfxOrderDetailNo() {
		return wfxOrderDetailNo;
	}
	public void setWfxOrderDetailNo(String wfxOrderDetailNo) {
		this.wfxOrderDetailNo = wfxOrderDetailNo;
	}
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCommodityId() {
		return commodityId;
	}
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
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
	public Double getPayment() {
		return payment;
	}
	public void setPayment(Double payment) {
		this.payment = payment;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getDiscountFee() {
		return discountFee;
	}
	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	public Double getProDiscountFee() {
		return proDiscountFee;
	}
	public void setProDiscountFee(Double proDiscountFee) {
		this.proDiscountFee = proDiscountFee;
	}
	public Double getCommissionTotal() {
		return commissionTotal;
	}
	public void setCommissionTotal(Double commissionTotal) {
		this.commissionTotal = commissionTotal;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getReturnAddressNo() {
		return returnAddressNo;
	}
	public void setReturnAddressNo(String returnAddressNo) {
		this.returnAddressNo = returnAddressNo;
	}
	
}
