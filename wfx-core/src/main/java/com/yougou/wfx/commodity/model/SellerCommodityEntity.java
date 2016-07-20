 /*
 * 版本信息
 
 * 日期 2016-04-13 16:39:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * SellerCommodityEntity
 * @author wfx
 * @Date 创建时间：2016-04-13 16:39:55
 */
public class SellerCommodityEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 分销商id
	 */
	private String sellerId;
	/**
	 * 分销商名称
	 */
	private String sellerName;
	/**
	 * 商品款号
	 */
	private String commodityStyleNo;
	/**
	 * 商品品牌编号
	 */
	private String commodityBrandNo;
	
	/**
	 * 分类编号
	 */
	private String commodityCatNo;
	
	/**
	 * 商品年份
	 */
	private String commodityYears;
	/**
	 * 上下架状态
	 */
	private Integer status;
	/**
	 * 记录创建时间
	 */
	private Date createTime;
	/**
	 * 记录修改时间
	 */
	private Date updateTime;
//columns END

	public SellerCommodityEntity(){
	}

	public SellerCommodityEntity(
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
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setSellerName(String value) {
		this.sellerName = value;
	}
	
	public String getSellerName() {
		return this.sellerName == null ? null : this.sellerName.trim();
	}
	
	public String getCommodityStyleNo() {
		return commodityStyleNo;
	}

	public void setCommodityStyleNo(String commodityStyleNo) {
		this.commodityStyleNo = commodityStyleNo;
	}


	public String getCommodityBrandNo() {
		return commodityBrandNo;
	}

	public void setCommodityBrandNo(String commodityBrandNo) {
		this.commodityBrandNo = commodityBrandNo;
	}

	public String getCommodityCatNo() {
		return commodityCatNo;
	}

	public void setCommodityCatNo(String commodityCatNo) {
		this.commodityCatNo = commodityCatNo;
	}

	public String getCommodityYears() {
		return commodityYears;
	}

	public void setCommodityYears(String commodityYears) {
		this.commodityYears = commodityYears;
	}

	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
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

