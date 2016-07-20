/*
 * 版本信息

 * 日期 2016-06-20 14:03:02

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.notice.dto.output;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;
import com.yougou.wfx.enums.NoticeStatusEnum;
import com.yougou.wfx.enums.RedirectTypeEnum;
import com.yougou.wfx.util.DatetimeUtil;

/**
 * NoticeOutputDto
 * 
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
public class NoticeOutputDto extends OutputDto {

	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 跳转类型
	 */
	private String redirectType;
	/**
	 * 跳转链接
	 */
	private String redirectUrl;
	/**
	 * 排序
	 */
	private Integer sort;
	/**
	 * 生效时间
	 */
	private Date effectiveTime;
	/**
	 * 失效时间
	 */
	private Date failureTime;
	/**
	 * 最后更新人
	 */
	private String lastOperateUser;
	/**
	 * 最后更新时间
	 */
	private Date lastOperateTime;
	/**
	 * 状态  对应（NoticeStatusEnum）
	 */
	private int status;
	/**
	 * 删除标示（1：删除 0：正常）
	 */
	private int deleteFlag;

	public int getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(int deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public NoticeOutputDto() {
	}

	public NoticeOutputDto(String id) {
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}

	public String getId() {
		return this.id == null ? null : this.id.trim();
	}

	public void setTitle(String value) {
		this.title = value;
	}

	public String getTitle() {
		return this.title == null ? null : this.title.trim();
	}

	public String getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(String redirectType) {
		this.redirectType = redirectType;
	}

	public String getRedirectTypeDesc() {
		if (this.redirectType == null) {
			return "";
		}
		return RedirectTypeEnum.getDescByKey(Integer.parseInt(this.redirectType));
	}

	public void setRedirectUrl(String value) {
		this.redirectUrl = value;
	}

	public String getRedirectUrl() {
		return this.redirectUrl == null ? null : this.redirectUrl.trim();
	}

	public void setSort(Integer value) {
		value = value == null ? 0 : value;
		this.sort = value;
	}

	public Integer getSort() {
		return this.sort == null ? 0 : this.sort;
	}

	public void setEffectiveTime(Date value) {
		this.effectiveTime = value;
	}

	public Date getEffectiveTime() {
		return this.effectiveTime;
	}

	public String getStringEffectiveTime() {
		if (this.effectiveTime == null) {
			return null;
		}
		return DatetimeUtil.DateToString(this.effectiveTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}

	public void setFailureTime(Date value) {
		this.failureTime = value;
	}

	public Date getFailureTime() {
		return this.failureTime;
	}

	public String getStringFailureTime() {
		if (this.failureTime == null) {
			return null;
		}
		return DatetimeUtil.DateToString(this.failureTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}

	public void setLastOperateUser(String value) {
		this.lastOperateUser = value;
	}

	public String getLastOperateUser() {
		return this.lastOperateUser == null ? null : this.lastOperateUser.trim();
	}

	public void setLastOperateTime(Date value) {
		this.lastOperateTime = value;
	}

	public Date getLastOperateTime() {
		return this.lastOperateTime;
	}

	public String getStringLastOperateTime() {
		if (this.lastOperateTime == null) {
			return null;
		}
		return DatetimeUtil.DateToString(this.lastOperateTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}

	public String getValidTime() {
		return getStringEffectiveTime() + " - " + getStringFailureTime();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusDesc(){
		return NoticeStatusEnum.getDescByKey(this.status);
	}
	
}
