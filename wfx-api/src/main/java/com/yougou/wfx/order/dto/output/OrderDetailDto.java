package com.yougou.wfx.order.dto.output;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 子订单详细信息
 * @author wang.zf
 *
 */
public class OrderDetailDto extends OutputDto{

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
	 * 订单列表中子订单显示退款的状态类型
	 * 1.退货/退款
	 * 2.退款中
	 * 3.已退款
	 * 4.无（确认收货7天后关闭退款）
	 */
	private String refundShowStatus;
	/**
	 * 店铺ID
	 */
	private String shopId;
	/**
	 * 店铺名称
	 */
	private String shopName;
	/**
	 * 商品id
	 */
	private String commodityId;
	/**
	 * 商品编号
	 */
	private String commodityNo;
	/**
	 * 商品最小库存单位id
	 */
	private String prodId;
	/**
	 * 供货商商品id
	 */
	private String outerCommodityId;
	/**
	 * 供货商商品最小库存单位id
	 */
	private String outerProdId;
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
	 * 一级佣金
	 */
	private Double commissionLevel1;
	/**
	 * 二级佣金
	 */
	private Double commissionLevel2;
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

	/**
	 * 商品图片
	 */
	private String picSmall;
	/**
	 * 货品条码
	 */
	private String thirdPartyCode;
	public String getPicSmall() {
		return picSmall;
	}

	public void setPicSmall(String picSmall) {
		this.picSmall = picSmall;
	}

	public String getThirdPartyCode() {
		return thirdPartyCode;
	}

	public void setThirdPartyCode(String thirdPartyCode) {
		this.thirdPartyCode = thirdPartyCode;
	}

	
	public OrderDetailDto(){
	}

	public OrderDetailDto(
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
	public void setWfxOrderDetailNo(String value) {
		this.wfxOrderDetailNo = value;
	}
	
	public String getWfxOrderDetailNo() {
		return this.wfxOrderDetailNo == null ? null : this.wfxOrderDetailNo.trim();
	}
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getRefundShowStatus() {
		return refundShowStatus;
	}

	public void setRefundShowStatus(String refundShowStatus) {
		this.refundShowStatus = refundShowStatus;
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
	public void setCommodityId(String value) {
		this.commodityId = value;
	}
	
	public String getCommodityId() {
		return this.commodityId == null ? null : this.commodityId.trim();
	}
	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public void setProdId(String value) {
		this.prodId = value;
	}
	
	public String getProdId() {
		return this.prodId == null ? null : this.prodId.trim();
	}
	public void setOuterCommodityId(String value) {
		this.outerCommodityId = value;
	}
	
	public String getOuterCommodityId() {
		return this.outerCommodityId == null ? null : this.outerCommodityId.trim();
	}
	public void setOuterProdId(String value) {
		this.outerProdId = value;
	}
	
	public String getOuterProdId() {
		return this.outerProdId == null ? null : this.outerProdId.trim();
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
	public void setCommissionLevel1(Double value) {
		this.commissionLevel1 = value;
	}
	
	public Double getCommissionLevel1() {
		return this.commissionLevel1;
	}
	public void setCommissionLevel2(Double value) {
		this.commissionLevel2 = value;
	}
	
	public Double getCommissionLevel2() {
		return this.commissionLevel2;
	}
	public void setRefundStatus(String value) {
		this.refundStatus = value;
	}
	
	public String getRefundStatus() {
		return this.refundStatus == null ? null : this.refundStatus.trim();
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
	public void setCommissionLevel1Percent(Double value) {
		this.commissionLevel1Percent = value;
	}
	
	public Double getCommissionLevel1Percent() {
		return this.commissionLevel1Percent;
	}
	public void setCommissionLevel2Percent(Double value) {
		this.commissionLevel2Percent = value;
	}
	
	public Double getCommissionLevel2Percent() {
		return this.commissionLevel2Percent;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
