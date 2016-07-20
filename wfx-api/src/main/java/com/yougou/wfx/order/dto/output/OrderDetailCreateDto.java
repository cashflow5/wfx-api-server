package com.yougou.wfx.order.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 顾客下订单的子订单信息
 * @author wang.zf
 *
 */
public class OrderDetailCreateDto extends OutputDto{
	private static final long serialVersionUID = 1L;
	/**
	 * 商品id
	 */
	private String commodityId;
	/**
	 * 商品最小库存单位id
	 */
	private String prodId;
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

	public OrderDetailCreateDto(){
	}

	public void setCommodityId(String value) {
		this.commodityId = value;
	}
	
	public String getCommodityId() {
		return this.commodityId == null ? null : this.commodityId.trim();
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
	public void setProdSpec(String value) {
		this.prodSpec = value;
	}
	
	public String getProdSpec() {
		return this.prodSpec == null ? null : this.prodSpec.trim();
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
	public void setPrice(Double value) {
		this.price = value;
	}
	
	public Double getPrice() {
		return this.price;
	}
	public void setDiscountFee(Double value) {
		this.discountFee = value;
	}
	
	public Double getDiscountFee() {
		return this.discountFee;
	}
	public void setTotalFee(Double value) {
		this.totalFee = value;
	}
	
	public Double getTotalFee() {
		return this.totalFee;
	}
	public void setProDiscountFee(Double value) {
		this.proDiscountFee = value;
	}
	
	public Double getProDiscountFee() {
		return this.proDiscountFee;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
