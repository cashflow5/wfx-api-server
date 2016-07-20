 /*
 * 版本信息
 
 * 日期 2016-04-08 13:32:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.user.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.InputDto;

/**
 * SupplierInputDto
 * @author wfx
 * @Date 创建时间：2016-04-08 13:32:03
 */
public class SupplierInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 状态:1=启用,2=关闭
	 */
	private Integer state;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 创建时间起始时间
	 */
	private Date createDateStart;
	/**
	 * 创建时间结束时间
	 */
	private Date createDateEnd;
	/**
	 * 供货商名称
	 */
	private String supplierName;
	/**
	 * 供货商编码
	 */
	private String supplierCode;

	public SupplierInputDto(){
	}

	public SupplierInputDto(
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
	public void setState(Integer value) {
		value = value == null ? 0 : value;
		this.state = value;
	}
	
	public Integer getState() {
		return this.state == null ? 0 : this.state;
	}
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public String getStringCreateDate() {
		if(this.createDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public Date getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(Date createDateStart) {
		this.createDateStart = createDateStart;
	}

	public Date getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(Date createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public void setSupplierName(String value) {
		this.supplierName = value;
	}
	
	public String getSupplierName() {
		return this.supplierName == null ? null : this.supplierName.trim();
	}
	public void setSupplierCode(String value) {
		this.supplierCode = value;
	}
	
	public String getSupplierCode() {
		return this.supplierCode == null ? null : this.supplierCode.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

