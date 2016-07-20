 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.PageModel;

/**
 * DiscoverChannelInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
public class DiscoverChannelInputDto extends PageModel<DiscoverChannelInputDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 频道编码
	 */
	private String channelCode;
	/**
	 * 频道名字
	 */
	private String channelName;
	/**
	 * 频道状态
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 更新时间
	 */
	private Date updateDate;
	/**
	 * 创建用户
	 */
	private String createUser;
	/**
	 * 更新用户
	 */
	private String updateUser;
	/**
	 * 排序号
	 */
	private Integer orderMark;

	
	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setChannelCode(String value) {
		this.channelCode = value;
	}
	
	public String getChannelCode() {
		return this.channelCode == null ? null : this.channelCode.trim();
	}
	public void setChannelName(String value) {
		this.channelName = value;
	}
	
	public String getChannelName() {
		return this.channelName == null ? null : this.channelName.trim();
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
	}
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	

	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	
	public void setCreateUser(String value) {
		this.createUser = value;
	}
	
	public String getCreateUser() {
		return this.createUser == null ? null : this.createUser.trim();
	}
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser == null ? null : this.updateUser.trim();
	}
	public void setOrderMark(Integer value) {
		value = value == null ? 0 : value;
		this.orderMark = value;
	}
	
	public Integer getOrderMark() {
		return this.orderMark == null ? 0 : this.orderMark;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

