 /*
 * 版本信息
 
 * 日期 2016-04-05 11:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.cms.dto.output;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * CommoditySaleCatRelaOutputDto
 * @author wfx
 * @Date 创建时间：2016-04-05 11:29:34
 */
public class CommoditySaleCatRelaOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String id;
	/**
	 * 销售分类ID
	 */
	private String saleCatId;
	/**
	 * 销售分类ID
	 */
	private String saleCatNo;
	/**
	 * 基础分类ID
	 */
	private String baseCatId;
	/**
	 * 基础分类编码
	 */
	private String baseCatNo;
	/**
	 * 创建人
	 */
	private String updateUser;
	/**
	 * 创建时间
	 */
	private Date updateTime;
	/**
	 * 一级分类
	 */
	private String levelOneName;
	/**
	 * 二级分类
	 */
	private String levelTwoName;
	/**
	 * 三级分类
	 */
	private String levelThreeName;

	public CommoditySaleCatRelaOutputDto(){
	}

	public CommoditySaleCatRelaOutputDto(
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
	public void setSaleCatId(String value) {
		this.saleCatId = value;
	}
	
	public String getSaleCatId() {
		return this.saleCatId == null ? null : this.saleCatId.trim();
	}
	public void setSaleCatNo(String value) {
		this.saleCatNo = value;
	}
	
	public String getSaleCatNo() {
		return this.saleCatNo == null ? null : this.saleCatNo.trim();
	}
	public void setBaseCatId(String value) {
		this.baseCatId = value;
	}
	
	public String getBaseCatId() {
		return this.baseCatId == null ? null : this.baseCatId.trim();
	}
	public void setBaseCatNo(String value) {
		this.baseCatNo = value;
	}
	
	public String getBaseCatNo() {
		return this.baseCatNo == null ? null : this.baseCatNo.trim();
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
	
	public String getLevelOneName() {
		return levelOneName;
	}

	public void setLevelOneName(String levelOneName) {
		this.levelOneName = levelOneName;
	}

	public String getLevelTwoName() {
		return levelTwoName;
	}

	public void setLevelTwoName(String levelTwoName) {
		this.levelTwoName = levelTwoName;
	}

	public String getLevelThreeName() {
		return levelThreeName;
	}

	public void setLevelThreeName(String levelThreeName) {
		this.levelThreeName = levelThreeName;
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

