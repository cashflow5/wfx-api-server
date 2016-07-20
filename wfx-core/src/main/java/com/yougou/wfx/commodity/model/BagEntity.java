 /*
 * 版本信息
 
 * 日期 2016-03-20 13:16:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author wuyang
 * @Date 创建时间：2016-03-20 13:16:57
 */
public class BagEntity  implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	//columns START
	private java.lang.String id;
	private java.lang.String bagName;
	private java.lang.String bagSmallPic;
	private java.lang.String bagBigPic;
	private Integer sortNo;
	private Integer status;
	private Integer commodityNum;
	private java.lang.String comments;
	private Integer isDelete;
	private Date createTime;
	private java.lang.String updateUser;
	private Date updateTime;
	//columns END
	// 开始时间
	private String startTime;
	// 结束时间
	private String endTime;


	public BagEntity(){
	}

	public BagEntity(
		java.lang.String id
	){
		this.id = id;
	}

	public void setId(java.lang.String value) {
		this.id = value;
	}
	
	public java.lang.String getId() {
		return this.id;
	}
	public void setBagName(java.lang.String value) {
		this.bagName = value;
	}
	
	public java.lang.String getBagName() {
		return this.bagName;
	}
	public void setBagSmallPic(java.lang.String value) {
		this.bagSmallPic = value;
	}
	
	public java.lang.String getBagSmallPic() {
		return this.bagSmallPic;
	}
	public void setBagBigPic(java.lang.String value) {
		this.bagBigPic = value;
	}
	public Integer getSortNo() {
		return sortNo;
	}
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public java.lang.String getBagBigPic() {
		return this.bagBigPic;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status;
	}
	
	public Integer getCommodityNum() {
		return commodityNum;
	}

	public void setCommodityNum(Integer commodityNum) {
		this.commodityNum = commodityNum;
	}

	public void setComments(java.lang.String value) {
		this.comments = value;
	}
	
	public java.lang.String getComments() {
		return this.comments;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setUpdateUser(java.lang.String value) {
		this.updateUser = value;
	}
	
	public java.lang.String getUpdateUser() {
		return this.updateUser;
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

