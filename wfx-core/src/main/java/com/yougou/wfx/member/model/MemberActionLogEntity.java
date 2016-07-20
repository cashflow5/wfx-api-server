 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * MemberActionLogEntity
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
public class MemberActionLogEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * tbl_member_loginaccount.id
	 */
	private String loginaccountId;
	/**
	 * 冗余字段:登录账号
	 */
	private String loginName;
	/**
	 * 操作ip
	 */
	private String optIp;
	/**
	 * 操作时间(数据创建时间)
	 */
	private Date optTime;
	/**
	 * 操作类型，后台定义枚举，包括登录，修改密码等
	 */
	private String optType;
	/**
	 * 操作平台,0=商城端(C端),1=分销商平台(B端),2=公众号商城
	 */
	private Integer optSite;
	/**
	 * 操作所在店铺id
	 */
	private String optShopId;
	/**
	 * 相关文本信息
	 */
	private String remark;
//columns END

	public MemberActionLogEntity(){
	}

	public MemberActionLogEntity(
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
	public void setLoginaccountId(String value) {
		this.loginaccountId = value;
	}
	
	public String getLoginaccountId() {
		return this.loginaccountId == null ? null : this.loginaccountId.trim();
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName == null ? null : this.loginName.trim();
	}
	public void setOptIp(String value) {
		this.optIp = value;
	}
	
	public String getOptIp() {
		return this.optIp == null ? null : this.optIp.trim();
	}
	public void setOptTime(Date value) {
		this.optTime = value;
	}
	
	public Date getOptTime() {
		return this.optTime;
	}
	
	public String getStringOptTime() {
		if(this.optTime == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.optTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setOptType(String value) {
		this.optType = value;
	}
	
	public String getOptType() {
		return this.optType == null ? null : this.optType.trim();
	}
	public void setOptSite(Integer value) {
		value = value == null ? 0 : value;
		this.optSite = value;
	}
	
	public Integer getOptSite() {
		return this.optSite == null ? 0 : this.optSite;
	}
	public void setOptShopId(String value) {
		this.optShopId = value;
	}
	
	public String getOptShopId() {
		return this.optShopId == null ? null : this.optShopId.trim();
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

