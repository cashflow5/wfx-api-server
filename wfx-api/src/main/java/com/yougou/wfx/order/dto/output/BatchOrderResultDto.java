package com.yougou.wfx.order.dto.output;


import java.util.List;

import com.yougou.wfx.dto.base.OutputDto;

public class BatchOrderResultDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 创建订单结果代码
	 * 1：成功
	 * 2.商品已下架
	 * 3：商品已售完
	 * 4.库存不足
	 * 5.传和参数错误
	 * 6.发生异常
	 */
	private Integer code;
	
	private String shopId;
	
	private String shopName;
	
	private String prodId;
	
	private String prodName;
	
	/**
	 * 创建订单结果信息
	 */
	private String message;
	
	private List<OrderResultDto> orderReslutList;
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
	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
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

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<OrderResultDto> getOrderReslutList() {
		return orderReslutList;
	}

	public void setOrderReslutList(List<OrderResultDto> orderReslutList) {
		this.orderReslutList = orderReslutList;
	}
}
