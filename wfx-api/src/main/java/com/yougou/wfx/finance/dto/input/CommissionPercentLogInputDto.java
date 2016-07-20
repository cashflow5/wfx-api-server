 /*
 * 版本信息
 
 * 日期 2016-04-29 13:53:43
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.PageModel;

/**
 * CommissionPercentLogInputDto
 * @author langqiwei
 * @Date 创建时间：2016-04-29 13:53:44
 */
public class CommissionPercentLogInputDto extends PageModel<CommissionPercentLogInputDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 基础分类ID
	 */
	private String baseCatId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 日志操作类型:1新增,2修改,3删除
	 */
	private String operateType;
	/**
	 * 备注
	 */
	private String remark;

	public CommissionPercentLogInputDto(){
	}

	public CommissionPercentLogInputDto(
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
	public void setBaseCatId(String value) {
		this.baseCatId = value;
	}
	
	public String getBaseCatId() {
		return this.baseCatId == null ? null : this.baseCatId.trim();
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
	public void setCreateUser(String value) {
		this.createUser = value;
	}
	
	public String getCreateUser() {
		return this.createUser == null ? null : this.createUser.trim();
	}
	public void setOperateType(String value) {
		this.operateType = value;
	}
	
	public String getOperateType() {
		return this.operateType == null ? null : this.operateType.trim();
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

