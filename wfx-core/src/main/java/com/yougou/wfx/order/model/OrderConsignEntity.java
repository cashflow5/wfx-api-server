package com.yougou.wfx.order.model;

 /*
 * 版本信息
 
 * 日期 2016-04-12 18:25:31
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * OrderConsignEntity
 * @authorzhang.wj
 * @Date 创建时间：2016-04-12 18:25:33
 */
public class OrderConsignEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 订单号
	 */
	private String orderId;
	/**
	 * 外部订单号
	 */
	private String outOrderId;
	/**
	 * 快递公司编码
	 */
	private String expressCode;
	/**
	 * 快递公司名称
	 */
	private String expressName;
	/**
	 * 快递单号
	 */
	private String expressNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 商品信息
	 */
	private List<OrderConsignCommodityEntity> orderConsignCommodity=new ArrayList<OrderConsignCommodityEntity>();




	public List<OrderConsignCommodityEntity> getOrderConsignCommodity() {
		return orderConsignCommodity;
	}

	public void setOrderConsignCommodity(
			List<OrderConsignCommodityEntity> orderConsignCommodity) {
		this.orderConsignCommodity = orderConsignCommodity;
	}

	public OrderConsignEntity(){
	}

	public OrderConsignEntity(
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
	public void setOrderId(String value) {
		this.orderId = value;
	}
	
	public String getOrderId() {
		return this.orderId == null ? null : this.orderId.trim();
	}
	public void setOutOrderId(String value) {
		this.outOrderId = value;
	}
	
	public String getOutOrderId() {
		return this.outOrderId == null ? null : this.outOrderId.trim();
	}
	public void setExpressCode(String value) {
		this.expressCode = value;
	}
	
	public String getExpressCode() {
		return this.expressCode == null ? null : this.expressCode.trim();
	}
	public void setExpressName(String value) {
		this.expressName = value;
	}
	
	public String getExpressName() {
		return this.expressName == null ? null : this.expressName.trim();
	}
	public void setExpressNo(String value) {
		this.expressNo = value;
	}
	
	public String getExpressNo() {
		return this.expressNo == null ? null : this.expressNo.trim();
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

