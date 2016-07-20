 /*
 * 版本信息
 
 * 日期 2016-03-25 14:21:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.cms.dto.input;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.PageModel;

/**
 * CarouselFigureInputDto
 * @author lijun
 * @Date 创建时间：2016-03-25 14:21:13
 */
public class CarouselFigureInputDto extends PageModel<CarouselFigureInputDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 轮播图名称
	 */
	private String name;
	/**
	 * 链接地址
	 */
	private String linkUrl;
	/**
	 * 图片的URL
	 */
	private String picUrl;
	/**
	 * 排序序号
	 */
	private Integer sortNo;
	/**
	 * 状态：0 停用，1 启用
	 */
	private Integer status;
	/**
	 * 最后更新人
	 */
	private String updateUser;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 删除标志：1删除，0，有效
	 */
	private Integer deleteFlag;
	/**
	 * 类型:CarouseFigureTypeEnum
	 */
	private String type;
	/**
	 * 跳转类型
	 */
	private String redirectType;


	public CarouselFigureInputDto(){
	}

	public CarouselFigureInputDto(
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
	public void setLinkUrl(String value) {
		this.linkUrl = value;
	}
	
	public String getLinkUrl() {
		return this.linkUrl == null ? null : this.linkUrl.trim();
	}
	public void setPicUrl(String value) {
		this.picUrl = value;
	}
	
	public String getPicUrl() {
		return this.picUrl == null ? null : this.picUrl.trim();
	}
	public void setSortNo(Integer value) {
		value = value == null ? 0 : value;
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo == null ? 0 : this.sortNo;
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
	}
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser == null ? null : this.updateUser.trim();
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
	public void setDeleteFlag(Integer value) {
		value = value == null ? 0 : value;
		this.deleteFlag = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag == null ? 0 : this.deleteFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}
}

