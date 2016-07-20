 /*
 * 版本信息
 
 * 日期 2016-06-02 09:59:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * DiscoverArticleEntity
 * @author wang.zf
 * @Date 创建时间：2016-06-02 09:59:26
 */
public class ArticleInfoEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 文章编号
	 */
	private Integer no;
	/**
	 * 文章标题
	 */
	private String title;
	/**
	 * 作者类型（1、优购，2、分销商）
	 */
	private Integer authorType;
	/**
	 * 作者登录账号
	 */
	private String authorAccount;
	/**
	 * 频道id，频道表id
	 */
	private String channelId;
	/**
	 * 频道名称
	 */
	private String channelName;
	/**
	 * 文章发布状态（1、未发布，2、已发布）
	 */
	private Integer publishStatus;
	/**
	 * 推荐状态（1、不推荐，2、推荐）
	 */
	private Integer recommendFlag;
	/**
	 * 删除状态（1、未删除，2、删除，如果状态为2，则可以在回收站查看）
	 */
	private Integer deleteFlag;
	/**
	 * 文章内容
	 */
	private String content;
	/**
	 * 文章封面
	 */
	private String picCover;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后操作人
	 */
	private String operUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 起始修改时间
	 */
	private Date updateTimeStart;
	/**
	 * 结束修改时间
	 */
	private Date updateTimeEnd;
//columns END

	public ArticleInfoEntity(){
	}

	public ArticleInfoEntity(
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
	public void setNo(Integer value) {
		value = value == null ? 0 : value;
		this.no = value;
	}
	
	public Integer getNo() {
		return this.no == null ? 0 : this.no;
	}
	public void setTitle(String value) {
		this.title = value;
	}
	
	public String getTitle() {
		return this.title == null ? null : this.title.trim();
	}
	public void setAuthorType(Integer value) {
		value = value == null ? 0 : value;
		this.authorType = value;
	}
	
	public Integer getAuthorType() {
		return this.authorType == null ? 0 : this.authorType;
	}
	public void setAuthorAccount(String value) {
		this.authorAccount = value;
	}
	
	public String getAuthorAccount() {
		return this.authorAccount == null ? null : this.authorAccount.trim();
	}
	public void setChannelId(String value) {
		this.channelId = value;
	}
	
	public String getChannelId() {
		return this.channelId == null ? null : this.channelId.trim();
	}
	public void setPublishStatus(Integer value) {
		value = value == null ? 0 : value;
		this.publishStatus = value;
	}
	
	public Integer getPublishStatus() {
		return this.publishStatus == null ? 0 : this.publishStatus;
	}
	public void setRecommendFlag(Integer value) {
		value = value == null ? 0 : value;
		this.recommendFlag = value;
	}
	
	public Integer getRecommendFlag() {
		return this.recommendFlag == null ? 0 : this.recommendFlag;
	}
	public void setDeleteFlag(Integer value) {
		value = value == null ? 0 : value;
		this.deleteFlag = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag == null ? 0 : this.deleteFlag;
	}
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content == null ? null : this.content.trim();
	}
	public void setPicCover(String value) {
		this.picCover = value;
	}
	
	public String getPicCover() {
		return this.picCover == null ? null : this.picCover.trim();
	}
	public void setSortNo(Integer value) {
		value = value == null ? 0 : value;
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo == null ? 0 : this.sortNo;
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
	public void setOperUser(String value) {
		this.operUser = value;
	}
	
	public String getOperUser() {
		return this.operUser == null ? null : this.operUser.trim();
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

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Date getUpdateTimeStart() {
		return updateTimeStart;
	}

	public void setUpdateTimeStart(Date updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	public Date getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	public void setUpdateTimeEnd(Date updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

