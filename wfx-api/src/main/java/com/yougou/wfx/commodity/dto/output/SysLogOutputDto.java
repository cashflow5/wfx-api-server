 /*
 * 版本信息
 
 * 日期 2016-03-30 10:51:22
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dto.output;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;
import com.yougou.wfx.enums.BusinessTypeEnum;
import com.yougou.wfx.enums.OperateTypeEnum;

/**
 * SysLogOutputDto
 * @author luoq
 * @Date 创建时间：2016-03-30 10:51:23
 */
public class SysLogOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
	/**
	 * 模块
	 */
	private String module;
	/**
	 * 操作人
	 */
	private String operateUser;
	/**
	 * 操作人账号
	 */
	private String operateAccount;
	/**
	 * 业务类型
	 */
	private String businessType;
	/**
	 * 操作类型
	 */
	private String operateType;
	/**
	 * 操作内容
	 */
	private String operateContent;
	/**
	 * 操作时间
	 */
	private Date operateDate;
	/**
	 * 操作人ip
	 */
	private String operatorIp;
	/**
	 * 备注
	 */
	private String remark;
	
	/** 商品编码 */
	private String commodityNo;
	
	/** 款色编码*/
	private String supplierCode;
	
	/** 商品款号*/
	private String styleNo;

	public SysLogOutputDto(){
	}

	public SysLogOutputDto(
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
	public void setModule(String value) {
		this.module = value;
	}
	
	public String getModule() {
		return this.module == null ? null : this.module.trim();
	}
	public void setOperateUser(String value) {
		this.operateUser = value;
	}
	
	public String getOperateUser() {
		return this.operateUser == null ? null : this.operateUser.trim();
	}
	public void setOperateAccount(String value) {
		this.operateAccount = value;
	}
	
	public String getOperateAccount() {
		return this.operateAccount == null ? null : this.operateAccount.trim();
	}
	public void setBusinessType(String value) {
		this.businessType = value;
	}
	
	public String getBusinessType() {
		return this.businessType == null ? null : this.businessType.trim();
	}
	
	public String getStringBusinessType() {
		if(this.businessType == null){
			return null;
		}
		businessType = businessType.trim();
		return BusinessTypeEnum.getDescByKey(businessType);
	}
	
	public void setOperateType(String value) {
		this.operateType = value;
	}
	
	public String getOperateType() {
		return this.operateType == null ? null : this.operateType.trim();
	}
	
	public String getStringOperateType() {
		if(this.operateType == null){
			return null;
		}
		operateType = operateType.trim();
		return OperateTypeEnum.getDescByKey(operateType);
	}
	
	public void setOperateContent(String value) {
		this.operateContent = value;
	}
	
	public String getOperateContent() {
		return this.operateContent == null ? null : this.operateContent.trim();
	}
	public void setOperateDate(Date value) {
		this.operateDate = value;
	}
	
	public Date getOperateDate() {
		return this.operateDate;
	}
	
	public String getStringOperateDate() {
		if(this.operateDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.operateDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setOperatorIp(String value) {
		this.operatorIp = value;
	}
	
	public String getOperatorIp() {
		return this.operatorIp == null ? null : this.operatorIp.trim();
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark == null ? null : this.remark.trim();
	}

	public String getCommodityNo() {
		return commodityNo;
	}

	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

