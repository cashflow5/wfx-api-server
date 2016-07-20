 /*
 * 版本信息
 
 * 日期 2016-06-02 20:18:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dto.output;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * DiscoverLogOutputDto
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 20:18:26
 */
public class DiscoverLogOutputDto extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private String id;
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

	public DiscoverLogOutputDto(){
	}

	public DiscoverLogOutputDto(
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
	public void setOperateType(String value) {
		this.operateType = value;
	}
	
	public String getOperateType() {
		return this.operateType == null ? null : this.operateType.trim();
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

