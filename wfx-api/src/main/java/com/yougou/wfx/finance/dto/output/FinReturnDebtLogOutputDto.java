 /*
 * 版本信息
 
 * 日期 2016-04-05 19:11:06
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dto.output;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * FinReturnDebtLogOutputDto
 * @author wfx
 * @Date 创建时间：2016-04-05 19:11:08
 */
public class FinReturnDebtLogOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 退款申请单号
	 */
	private String backNo;
	/**
	 * 日志类型;1:买家日志,2:卖家日志
	 */
	private Integer logType;
	/**
	 * 行为描述
	 */
	private String behaviourDesc;
	/**
	 * 操作人
	 */
	private String operater;
	/**
	 * 操作码
	 */
	private String operateCode;
	/**
	 * 操作说明
	 */
	private String operateDesc;
	/**
	 * 操作结果，1：成功，-1：失败
	 */
	private Integer operateResult;
	/**
	 * 操作来源
	 */
	private String operateSource;

	public FinReturnDebtLogOutputDto(){
	}

	public FinReturnDebtLogOutputDto(
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
	public void setBackNo(String value) {
		this.backNo = value;
	}
	
	public String getBackNo() {
		return this.backNo == null ? null : this.backNo.trim();
	}
	public void setLogType(Integer value) {
		this.logType = value;
	}
	
	public Integer getLogType() {
		return this.logType;
	}
	public void setBehaviourDesc(String value) {
		this.behaviourDesc = value;
	}
	
	public String getBehaviourDesc() {
		return this.behaviourDesc == null ? null : this.behaviourDesc.trim();
	}
	public void setOperater(String value) {
		this.operater = value;
	}
	
	public String getOperater() {
		return this.operater == null ? null : this.operater.trim();
	}
	public void setOperateDesc(String value) {
		this.operateDesc = value;
	}
	
	public String getOperateDesc() {
		return this.operateDesc == null ? null : this.operateDesc.trim();
	}
	
	
	public String getOperateCode() {
		return operateCode;
	}

	public void setOperateCode(String operateCode) {
		this.operateCode = operateCode;
	}

	public void setOperateResult(Integer value) {
		this.operateResult = value;
	}
	
	public Integer getOperateResult() {
		return this.operateResult;
	}
	public void setOperateSource(String value) {
		this.operateSource = value;
	}
	
	public String getOperateSource() {
		return this.operateSource == null ? null : this.operateSource.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

