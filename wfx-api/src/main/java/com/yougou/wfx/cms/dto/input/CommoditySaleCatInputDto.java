 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
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
 * CommoditySaleCatInputDto
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
public class CommoditySaleCatInputDto extends PageModel<CommoditySaleCatInputDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 分类名称
	 */
	private String name;
	/**
	 * 销售分类编码
	 */
	private String catNo;
	/**
	 * 图片的URL
	 */
	private String picUrl;
	/**
	 * 产品数量
	 */
	private Integer productNum;
	/**
	 * 级别
	 */
	private Integer level;
	/**
	 * 是否热门分类 1：是，2：否
	 */
	private Integer hotFlag;
	/**
	 * 热门分类排序
	 */
	private Integer hotSn;
	/**
	 * 父分类ID
	 */
	private String parentId;
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
	 * 删除标志：1:删除，2:有效
	 */
	private Integer deleteFlag;

	public CommoditySaleCatInputDto(){
	}

	public CommoditySaleCatInputDto(
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
	public void setCatNo(String value) {
		this.catNo = value;
	}
	
	public String getCatNo() {
		return this.catNo == null ? null : this.catNo.trim();
	}
	public void setPicUrl(String value) {
		this.picUrl = value;
	}
	
	public String getPicUrl() {
		return this.picUrl == null ? null : this.picUrl.trim();
	}
	public void setProductNum(Integer value) {
		value = value == null ? 0 : value;
		this.productNum = value;
	}
	
	public Integer getProductNum() {
		return this.productNum == null ? 0 : this.productNum;
	}
	public void setLevel(Integer value) {
		value = value == null ? 0 : value;
		this.level = value;
	}
	
	public Integer getLevel() {
		return this.level == null ? 0 : this.level;
	}
	public void setHotFlag(Integer value) {
		value = value == null ? 0 : value;
		this.hotFlag = value;
	}
	
	public Integer getHotFlag() {
		return this.hotFlag == null ? 0 : this.hotFlag;
	}
	public void setHotSn(Integer value) {
		this.hotSn = value;
	}
	
	public Integer getHotSn() {
		return this.hotSn;
	}
	public void setParentId(String value) {
		this.parentId = value;
	}
	
	public String getParentId() {
		return this.parentId == null ? null : this.parentId.trim();
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
}

