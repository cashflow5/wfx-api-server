 /*
 * 版本信息
 
 * 日期 2016-05-09 17:09:12
 
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
 * CommodityPicsEntity
 * @author wzf
 * @Date 创建时间：2016-05-09 17:09:13
 */
public class CommodityPicsEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 商品编号
	 */
	private String commodityNo;
	/**
	 * 图片文件名称
	 */
	private String picName;
	/**
	 * 图片类型（l最大图，角度图；b宝贝描述图，描述图;m大图，角度图；s中图，搜索列表图；t小图，角度图;c最小图，颜色图；u、U图,U图；mb手机手机客户端大图，手机图片；ms手机手机客户端大图，手机图片；目前只存放宝贝描述图片）
	 */
	private String picType;
	/**
	 * 删除标志，0是删除、1是为删除
	 */
	private Integer delFlag;
	/**
	 * 最后的修改时间
	 */
	private Date updateTime;
	/**
	 * 最后修改人
	 */
	private String updateUser;
	/**
	 * 图片路径
	 */
	private String picPath;
//columns END

	public CommodityPicsEntity(){
	}

	public CommodityPicsEntity(
		Integer id
	){
		this.id = id;
	}

	public void setId(Integer value) {
		value = value == null ? 0 : value;
		this.id = value;
	}
	
	public Integer getId() {
		return this.id == null ? 0 : this.id;
	}
	public void setCommodityNo(String value) {
		this.commodityNo = value;
	}
	
	public String getCommodityNo() {
		return this.commodityNo == null ? null : this.commodityNo.trim();
	}
	public void setPicName(String value) {
		this.picName = value;
	}
	
	public String getPicName() {
		return this.picName == null ? null : this.picName.trim();
	}
	public void setPicType(String value) {
		this.picType = value;
	}
	
	public String getPicType() {
		return this.picType == null ? null : this.picType.trim();
	}
	public void setDelFlag(Integer value) {
		this.delFlag = value;
	}
	
	public Integer getDelFlag() {
		return this.delFlag;
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
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser == null ? null : this.updateUser.trim();
	}
	public void setPicPath(String value) {
		this.picPath = value;
	}
	
	public String getPicPath() {
		return this.picPath == null ? null : this.picPath.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

