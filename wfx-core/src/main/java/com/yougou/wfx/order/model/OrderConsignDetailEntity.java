 /*
 * 版本信息
 
 * 日期 2016-04-18 14:41:51
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * OrderConsignDetailEntity
 * @author wzf
 * @Date 创建时间：2016-04-18 14:41:52
 */
public class OrderConsignDetailEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 外键
	 */
	private String consignId;
	/**
	 * 子订单编号
	 */
	private String orderDetailId;
	/**
	 * 商品编号
	 */
	private String prodId;
	/**
	 * 发货数量
	 */
	private Integer num;
	/**
	 * 记录创建时间
	 */
	private Date createTime;
	/**
	 * 记录修改时间
	 */
	private Date updateTime;
//columns END

	public OrderConsignDetailEntity(){
	}

	public OrderConsignDetailEntity(
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
	public void setConsignId(String value) {
		this.consignId = value;
	}
	
	public String getConsignId() {
		return this.consignId == null ? null : this.consignId.trim();
	}
	public void setOrderDetailId(String value) {
		this.orderDetailId = value;
	}
	
	public String getOrderDetailId() {
		return this.orderDetailId == null ? null : this.orderDetailId.trim();
	}
	public void setProdId(String value) {
		this.prodId = value;
	}
	
	public String getProdId() {
		return this.prodId == null ? null : this.prodId.trim();
	}
	public void setNum(Integer value) {
		value = value == null ? 0 : value;
		this.num = value;
	}
	
	public Integer getNum() {
		return this.num == null ? 0 : this.num;
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

