 /*
 * 版本信息
 
 * 日期 2016-05-19 09:43:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * AppVersionEntity
 * @author wfx
 * @Date 创建时间：2016-05-19 09:43:27
 */
public class AppVersionEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 版本名称
	 */
	private String versionName;
	/**
	 * 版本编号
	 */
	private String versionCode;
	/**
	 * 版本APK存放地址
	 */
	private String versionUrl;
	/**
	 * 版本描述
	 */
	private String versionContent;
	/**
	 * 是否强制更新，1:是 2否
	 */
	private Integer forceUpdate;
//columns END

	public AppVersionEntity(){
	}

	public AppVersionEntity(
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
	public void setVersionName(String value) {
		this.versionName = value;
	}
	
	public String getVersionName() {
		return this.versionName == null ? null : this.versionName.trim();
	}
	public void setVersionCode(String value) {
		this.versionCode = value;
	}
	
	public String getVersionCode() {
		return this.versionCode == null ? null : this.versionCode.trim();
	}
	public void setVersionUrl(String value) {
		this.versionUrl = value;
	}
	
	public String getVersionUrl() {
		return this.versionUrl == null ? null : this.versionUrl.trim();
	}
	public void setVersionContent(String value) {
		this.versionContent = value;
	}
	
	public String getVersionContent() {
		return this.versionContent == null ? null : this.versionContent.trim();
	}
	public void setForceUpdate(Integer value) {
		value = value == null ? 0 : value;
		this.forceUpdate = value;
	}
	
	public Integer getForceUpdate() {
		return this.forceUpdate == null ? 0 : this.forceUpdate;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

