 /*
 * 版本信息
 
 * 日期 2016-03-25 14:05:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dto.input;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

/**
 * SellerInfoPageInputDto
 * @author luoq
 * @Date 创建时间：2016-03-25 14:05:40
 */
public class SellerInfoPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 创建（申请）时间
	 */
	private Date createTime;
	/**
	 * tbl_member_loginaccount.id
	 */
	private String loginaccountId;
	/**
	 * 冗余字段:登录账号
	 */
	private String loginName;
	/**
	 * 分销商名称
	 */
	private String sellerName;
	/**
	 * 枚举：1待审核，2审核不通过，3合作中，4取消合作
	 */
	private String state;
	/**
	 * 审核通过时间
	 */
	private Date passDate;
	/**
	 * 操作审核通过的系统用户id
	 */
	private String passSysuser;
	/**
	 * 最后开启时间(最近分销商开启合作时间)
	 */
	private Date lastActiveDate;
	/**
	 * 最后开启系统用户id(最近分销商开启合作系统用户id)
	 */
	private String lastActiveOptuser;
	/**
	 * 最后停止合作时间(最近分销商停止合作时间)
	 */
	private Date lastUnactiveDate;
	/**
	 * 最后停止系统用户id(最近分销商停止合作系统用户id)
	 */
	private String lastUnactiveOptuser;
	/**
	 * 审核拒绝原因
	 */
	private String refuseRemark;
	/**
	 * 已提现总金额
	 *//*
	private Double cashedTotalAmount;
	*//**
	 * 提现中总金额
	 *//*
	private Double cashingTotalAmount;
	*//**
	 * 可用余额
	 *//*
	private Double balanceTotalAmount;
	*//**
	 * 佣金收入总金额
	 *//*
	private Double commissionTotalAmount;*/
	/**
	 * 上级代理sellerinfo.id
	 */
	private String parentId;
	
	/**
	 * 分销商作为普通用户自己下单的订单总数起始值
	 */
	private Integer orderCountStart;
	
	/**
	 * 分销商作为普通用户自己下单的订单总数结束值
	 */
	private Integer orderCountEnd;
	
	/**
	 * 申请成为分销商时间起始值
	 */
	private String createTimeStart;
	
	/**
	 * 申请成为分销商时间结束值
	 */
	private String createTimeEnd;
	
	/**
	 * 分销商作为普通用户自己下单的订单总金额起始值
	 */
	private Double orderAmountStart;
	
	/**
	 * 申分销商作为普通用户自己下单的订单总金额结束值
	 */
	private Double orderAmountEnd;
	
	/**
	 * 注册时间起始值
	 */
	private String registerDateStart;
	
	/**
	 * 注册结束值
	 */
	private String registerDateEnd;
	/**
	 * 会员的第三方平台昵称
	 */
	private String platformUsername;
	
	public String getPlatformUsername() {
		return platformUsername;
	}

	public void setPlatformUsername(String platformUsername) {
		this.platformUsername = platformUsername;
	}

	
	public Integer getOrderCountStart() {
		return orderCountStart;
	}

	public void setOrderCountStart(Integer orderCountStart) {
		this.orderCountStart = orderCountStart;
	}

	public Integer getOrderCountEnd() {
		return orderCountEnd;
	}

	public void setOrderCountEnd(Integer orderCountEnd) {
		this.orderCountEnd = orderCountEnd;
	}

	public String getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(String createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public String getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(String createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public Double getOrderAmountStart() {
		return orderAmountStart;
	}

	public void setOrderAmountStart(Double orderAmountStart) {
		this.orderAmountStart = orderAmountStart;
	}

	public Double getOrderAmountEnd() {
		return orderAmountEnd;
	}

	public void setOrderAmountEnd(Double orderAmountEnd) {
		this.orderAmountEnd = orderAmountEnd;
	}

	public SellerInfoPageInputDto(){
	}

	public SellerInfoPageInputDto(
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
	public void setSellerName(String value) {
		this.sellerName = value;
	}
	
	public String getSellerName() {
		return this.sellerName == null ? null : this.sellerName.trim();
	}
	public void setState(String value) {
		this.state = value;
	}
	
	public String getState() {
		return this.state == null ? null : this.state.trim();
	}
	public void setPassDate(Date value) {
		this.passDate = value;
	}
	
	public Date getPassDate() {
		return this.passDate;
	}
	
	public String getStringPassDate() {
		if(this.passDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.passDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setPassSysuser(String value) {
		this.passSysuser = value;
	}
	
	public String getPassSysuser() {
		return this.passSysuser == null ? null : this.passSysuser.trim();
	}
	public void setLastActiveDate(Date value) {
		this.lastActiveDate = value;
	}
	
	public Date getLastActiveDate() {
		return this.lastActiveDate;
	}
	
	public String getStringLastActiveDate() {
		if(this.lastActiveDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.lastActiveDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setLastActiveOptuser(String value) {
		this.lastActiveOptuser = value;
	}
	
	public String getLastActiveOptuser() {
		return this.lastActiveOptuser == null ? null : this.lastActiveOptuser.trim();
	}
	public void setLastUnactiveDate(Date value) {
		this.lastUnactiveDate = value;
	}
	
	public Date getLastUnactiveDate() {
		return this.lastUnactiveDate;
	}
	
	public String getStringLastUnactiveDate() {
		if(this.lastUnactiveDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.lastUnactiveDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setLastUnactiveOptuser(String value) {
		this.lastUnactiveOptuser = value;
	}
	
	public String getLastUnactiveOptuser() {
		return this.lastUnactiveOptuser == null ? null : this.lastUnactiveOptuser.trim();
	}
	public void setRefuseRemark(String value) {
		this.refuseRemark = value;
	}
	
	public String getRefuseRemark() {
		return this.refuseRemark == null ? null : this.refuseRemark.trim();
	}

	public void setParentId(String value) {
		this.parentId = value;
	}
	
	public String getParentId() {
		return this.parentId == null ? null : this.parentId.trim();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getRegisterDateStart() {
		return registerDateStart;
	}

	public void setRegisterDateStart(String registerDateStart) {
		this.registerDateStart = registerDateStart;
	}

	public String getRegisterDateEnd() {
		return registerDateEnd;
	}

	public void setRegisterDateEnd(String registerDateEnd) {
		this.registerDateEnd = registerDateEnd;
	}
}
	
