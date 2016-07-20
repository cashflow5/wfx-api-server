 /*
 * 版本信息
 
 * 日期 2016-06-02 10:14:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dto.output;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.OutputDto;
import com.yougou.wfx.util.DatetimeUtil;

/**
 * DiscoverCarouselFigureOutputDto
 * @author wang.zf
 * @Date 创建时间：2016-06-02 10:14:16
 */
public class DiscoverCarouselFigureOutputDto extends OutputDto {
	
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
	 * 轮播图图片
	 */
	private String picture;
	/**
	 * 文章编号
	 */
	private String articleNo;
	/**
	 * 文章标题及作者说明
	 */
	private String articleMark;
	/**
	 * 文章标题
	 */
	private String articleTitle;
	/**
	 * 文章作者
	 */
	private String articleAuthor;
	/**
	 * 是否显示（1、隐藏，2、显示）
	 */
	private Integer showFlag;
	/**
	 * 排序号
	 */
	private Integer sortNo;
	/**
	 * 轮播图创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新人
	 */
	private String operUser;
	/**
	 * 轮揪图更新时间
	 */
	private Date updateTime;
	/**
	 * 文章
	 */
	private DiscoverArticleOutputDto discoverArticle ;

	public DiscoverArticleOutputDto getDiscoverArticle() {
		return discoverArticle;
	}

	public void setDiscoverArticle(DiscoverArticleOutputDto discoverArticle) {
		this.discoverArticle = discoverArticle;
	}

	public DiscoverCarouselFigureOutputDto(){
	}

	public DiscoverCarouselFigureOutputDto(
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
	public void setPicture(String value) {
		this.picture = value;
	}
	
	public String getPicture() {
		return this.picture == null ? null : this.picture.trim();
	}
	public void setArticleNo(String value) {
		this.articleNo = value;
	}
	
	public String getArticleNo() {
		return this.articleNo == null ? null : this.articleNo.trim();
	}
	public String getArticleMark() {
		return articleMark;
	}

	public void setArticleMark(String articleMark) {
		this.articleMark = articleMark;
	}

	public void setShowFlag(Integer value) {
		value = value == null ? 0 : value;
		this.showFlag = value;
	}
	
	public Integer getShowFlag() {
		return this.showFlag == null ? 0 : this.showFlag;
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
	
	public String getArticleTitle() {
		return articleTitle;
	}

	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}

	public String getArticleAuthor() {
		return articleAuthor;
	}

	public void setArticleAuthor(String articleAuthor) {
		this.articleAuthor = articleAuthor;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

