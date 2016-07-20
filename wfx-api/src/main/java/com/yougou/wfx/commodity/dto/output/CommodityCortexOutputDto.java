 /*
 * 版本信息
 
 * 日期 2016-06-21 17:57:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dto.output;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;
import com.yougou.wfx.util.DatetimeUtil;

/**
 * CommodityCortexOutputDto
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
public class CommodityCortexOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 皮质名
	 */
	private String name;
	/**
	 * 皮质编码
	 */
	private String no;
	
	/**
	 * 是否有说明，100:有,101:否
	 */
	private Integer isNotDescription;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 最后更新人
	 */
	private String lastOperateUser;
	/**
	 * 最后更新时间
	 */
	private Date lastOperateTime;

	public CommodityCortexOutputDto(){
	}

	public CommodityCortexOutputDto(
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
	public void setIsNotDescription(Integer value) {
		value = value == null ? 0 : value;
		this.isNotDescription = value;
	}
	
	public Integer getIsNotDescription() {
		return this.isNotDescription == null ? 0 : this.isNotDescription;
	}
	public void setDescription(String value) {
		this.description = value;
	}
	
	public String getDescription() {
		return this.description == null ? null : this.description.trim();
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
		if(this.lastOperateTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.lastOperateTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
}

