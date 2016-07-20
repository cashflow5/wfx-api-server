 /*
 * 版本信息
 
 * 日期 2016-04-09 13:01:36
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.basicset.dto.input;

import java.util.Date;
import java.util.Map;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

/**
 * SysConfigPageInputDto
 * @author wfx
 * @Date 创建时间：2016-04-09 13:01:37
 */
public class SysConfigPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 配置项名称
	 */
	private String configName;
	/**
	 * 配置项key
	 */
	private String configKey;
	/**
	 * 配置项value
	 */
	private String configValue;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 状态，1表示开启 2表示关闭
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改用户
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	
	private Map<String, String> syncMap;
	
	public SysConfigPageInputDto(){
	}

	public SysConfigPageInputDto(
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
	public void setConfigName(String value) {
		this.configName = value;
	}
	
	public String getConfigName() {
		return this.configName == null ? null : this.configName.trim();
	}
	public void setConfigKey(String value) {
		this.configKey = value;
	}
	
	public String getConfigKey() {
		return this.configKey == null ? null : this.configKey.trim();
	}
	public void setConfigValue(String value) {
		this.configValue = value;
	}
	
	public String getConfigValue() {
		return this.configValue == null ? null : this.configValue.trim();
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark == null ? null : this.remark.trim();
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
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
	
	public Map<String, String> getSyncMap() {
		return syncMap;
	}

	public void setSyncMap(Map<String, String> syncMap) {
		this.syncMap = syncMap;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
