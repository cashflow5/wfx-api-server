package com.yougou.wfx.order.dto.output;

import java.util.Date;

import com.yougou.wfx.dto.base.OutputDto;

public class OrderDetailCommissionOutPutDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	/**
	 * 主订单id
	 */
	private String orderId;
	/**
	 * 主订单no
	 */
	private String orderNo;
	/**
	 * 订单创建时间
	 */
	private Date createOrderTime;
	/**
	 * 店铺id
	 */
	private String shopId;
	/**
	 * 店铺名称
	 */
	private String shopName;
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
	 * 佣金
	 */
	private Double commission;
	
	/**
     * 子订单号
     */
    private String wfxOrderDetailNo; 
    /**
     * 店铺级别，1一级,2二级,3三级
     */
    private String shopLevel;
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
	public String getShopLevel() {
        return shopLevel;
    }
    public void setShopLevel(String shopLevel) {
        this.shopLevel = shopLevel;
    }
    public String getWfxOrderDetailNo() {
        return wfxOrderDetailNo;
    }
    public void setWfxOrderDetailNo(String wfxOrderDetailNo) {
        this.wfxOrderDetailNo = wfxOrderDetailNo;
    }
    public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Date getCreateOrderTime() {
		return createOrderTime;
	}
	public void setCreateOrderTime(Date createOrderTime) {
		this.createOrderTime = createOrderTime;
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
	public Double getCommission() {
		return commission;
	}
	public void setCommission(Double commission) {
		this.commission = commission;
	}
}
