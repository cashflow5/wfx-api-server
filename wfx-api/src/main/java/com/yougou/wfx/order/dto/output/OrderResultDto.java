 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.output;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 创建订单返回结果表
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public class OrderResultDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 保存生成订单的详细信息
	 */
	private OrderInfoDto orderInfoDto;
	
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
	public OrderResultDto(){
	}

	public OrderInfoDto getOrderInfoDto() {
		return orderInfoDto;
	}

	public void setOrderInfoDto(OrderInfoDto orderInfoDto) {
		this.orderInfoDto = orderInfoDto;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

