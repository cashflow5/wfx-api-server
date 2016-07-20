package com.yougou.wfx.order.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 子订单详细信息
 * @author wang.zf
 *
 */
public class OrderDetailCommDto extends OutputDto{
	private static final long serialVersionUID = 1L;
	
	/**
	 * 子订单id
	 */
	private String orderDetailId;
	/**
	 * 子订单编号
	 */
	private String wfxOrderDetailNo;
	/**
	 * 货品id
	 */
	private String prodId;
	/**
	 * 商品id
	 */
	private String commodityId;
	/**
	 * 商品图片
	 */
	private String picSmall;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 商品颜色，尺寸
	 */
	private String prodSpec;
	/**
	 * 分销价
	 */
	private Double wfxPrice;
	/**
	 * 购买数量
	 */
	private Integer num;
	/**
	 * 订单列表中子订单显示退款的状态类型
	 * 1.退货/退款
	 * 2.退款中
	 * 3.已退款
	 * 4.无（确认收货7天后关闭退款、交易关闭、等待付款）
	 */
	private String refundShowStatus;
	/**
	 * 一级佣金
	 */
	private Double commissionLevel1;
	/**
	 * 二级佣金
	 */
	private Double commissionLevel2;
	/**
	 * 一级佣金比例
	 */
	private Double commissionLevel1Percent;
	/**
	 * 二级佣金比例
	 */
	private Double commissionLevel2Percent;
	/**
	 * 发货回写退货地址编码
	 */
	private String returnAddressNo;
	
	/**
	 * 三级佣金比例
	 */
	private Double commissionLevel3Percent;
	/**
	 * 三级佣金
	 */
	private Double commissionLevel3;

	public String getReturnAddressNo() {
		return returnAddressNo;
	}

	public void setReturnAddressNo(String returnAddressNo) {
		this.returnAddressNo = returnAddressNo;
	}

	public Double getCommissionLevel3Percent() {
		return commissionLevel3Percent;
	}

	public void setCommissionLevel3Percent(Double commissionLevel3Percent) {
		this.commissionLevel3Percent = commissionLevel3Percent;
	}

	public Double getCommissionLevel3() {
		return commissionLevel3;
	}

	public void setCommissionLevel3(Double commissionLevel3) {
		this.commissionLevel3 = commissionLevel3;
	}

	public OrderDetailCommDto(){
	}

	public String getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getWfxOrderDetailNo() {
		return wfxOrderDetailNo;
	}

	public void setWfxOrderDetailNo(String wfxOrderDetailNo) {
		this.wfxOrderDetailNo = wfxOrderDetailNo;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getPicSmall() {
		return picSmall;
	}

	public void setPicSmall(String picSmall) {
		this.picSmall = picSmall;
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

	public Double getWfxPrice() {
		return wfxPrice;
	}

	public void setWfxPrice(Double wfxPrice) {
		this.wfxPrice = wfxPrice;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
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

	public Double getCommissionLevel1Percent() {
		return commissionLevel1Percent;
	}

	public void setCommissionLevel1Percent(Double commissionLevel1Percent) {
		this.commissionLevel1Percent = commissionLevel1Percent;
	}

	public Double getCommissionLevel2Percent() {
		return commissionLevel2Percent;
	}

	public void setCommissionLevel2Percent(Double commissionLevel2Percent) {
		this.commissionLevel2Percent = commissionLevel2Percent;
	}

	public String getRefundShowStatus() {
		return refundShowStatus;
	}

	public void setRefundShowStatus(String refundShowStatus) {
		this.refundShowStatus = refundShowStatus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
