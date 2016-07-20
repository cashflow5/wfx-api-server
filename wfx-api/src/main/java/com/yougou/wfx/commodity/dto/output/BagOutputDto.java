package com.yougou.wfx.commodity.dto.output;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

public class BagOutputDto extends OutputDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String bagName;
	private String bagSmallPic;
	private String bagBigPic;
	private Integer sortNo;
	private Integer status;
	private Integer commodityNum;
	private String comments;
	private Integer isDelete;
	private Date createTime;
	private String updateUser;
	private Date updateTime;

	public BagOutputDto() {
	}

	public BagOutputDto(String id) {
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id;
	}

	public void setBagName(String value) {
		this.bagName = value;
	}

	public String getBagName() {
		return this.bagName;
	}

	public void setBagSmallPic(String value) {
		this.bagSmallPic = value;
	}

	public String getBagSmallPic() {
		return this.bagSmallPic;
	}

	public void setBagBigPic(String value) {
		this.bagBigPic = value;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

	public String getBagBigPic() {
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

	public void setComments(String value) {
		this.comments = value;
	}

	public String getComments() {
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

	public void setUpdateUser(String value) {
		this.updateUser = value;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}