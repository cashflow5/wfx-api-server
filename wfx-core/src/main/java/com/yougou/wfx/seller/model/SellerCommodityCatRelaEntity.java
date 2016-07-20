 /*
 * 版本信息
 
 * 日期 2016-04-14 14:39:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * SellerCommodityCatRelaEntity
 * @author zheng.qq
 * @Date 创建时间：2016-04-14 14:39:16
 */
public class SellerCommodityCatRelaEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 代理商品id
	 */
	private String sellerCommodityId;
	/**
	 * 自定义分类id
	 */
	private String catId;
	/**
	 * 记录创建时间
	 */
	private Date createTime;
//columns END

	public SellerCommodityCatRelaEntity(){
	}

	public SellerCommodityCatRelaEntity(
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
	public void setSellerCommodityId(String value) {
		this.sellerCommodityId = value;
	}
	
	public String getSellerCommodityId() {
		return this.sellerCommodityId == null ? null : this.sellerCommodityId.trim();
	}
	public void setCatId(String value) {
		this.catId = value;
	}
	
	public String getCatId() {
		return this.catId == null ? null : this.catId.trim();
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

