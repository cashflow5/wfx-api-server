 /*
 * 版本信息
 
 * 日期 2016-04-13 14:20:20
 
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
 * SellerCatEntity
 * @author wfx
 * @Date 创建时间：2016-04-13 14:20:20
 */
public class SellerCatEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 分销商id
	 */
	private String sellerId;
	/**
	 * 分类级别
	 */
	private Integer level;
	/**
	 * 商品数量
	 */
	private Integer num;
	/**
	 * 父分类编号
	 */
	private String parentId;
	/**
	 * 记录创建时间
	 */
	private Date createTime;
	/**
	 * 记录修改时间
	 */
	private Date updateTime;
//columns END

	public SellerCatEntity(){
	}

	public SellerCatEntity(
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
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name == null ? null : this.name.trim();
	}
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setLevel(Integer value) {
		value = value == null ? 0 : value;
		this.level = value;
	}
	
	public Integer getLevel() {
		return this.level == null ? 0 : this.level;
	}
	public void setNum(Integer value) {
		value = value == null ? 0 : value;
		this.num = value;
	}
	
	public Integer getNum() {
		return this.num == null ? 0 : this.num;
	}
	public void setParentId(String value) {
		this.parentId = value;
	}
	
	public String getParentId() {
		return this.parentId == null ? null : this.parentId.trim();
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

