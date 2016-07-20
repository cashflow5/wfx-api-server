 /*
 * 版本信息
 
 * 日期 2016-03-25 14:05:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dto.output;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * SellerInfoOutputDto
 * @author luoq
 * @Date 创建时间：2016-03-25 14:05:40
 */
public class SellerInfoOutputDto extends OutputDto {
	
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
	 * 审核通过时间（注册时间）
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
	 */
	private Double cashedTotalAmount;
	/**
	 * 提现中总金额
	 */
	private Double cashingTotalAmount;
	/**
	 * 可用余额
	 */
	private Double balanceTotalAmount;
	/**
	 * 佣金收入总金额
	 */
	private Double commissionTotalAmount;
	/**
	 * 上级代理sellerinfo.id
	 */
	private String parentId;
	/**
	 * 店铺名称
	 */
	private String shopName;
	
	/**
	 * 店铺编码 added by zhangfeng 2016-6-1
	 */
	private String shopCode;
	
	/**
	 * 真实姓名
	 */
	private String memberName;
	
	/**
	 * 性别
	 */
	private String memberSex;
	
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 为上级带来的佣金收入
	 */
	private Double commissionTotalAmountForParent;
	
	
	/**
	 * 分销商自己下单的总金额
	 */
	private Double orderAmount;
	
	/**
	 * 分销商自己下单数量
	 */
	private Integer orderCount;
	
	/**
	 * 注册普通用户时间
	 */
	private Date registerDate;
	
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	
	/**
	 * 作为下级分销商给上级带来的佣金收入总额
	 */
	//private Double subCommissionTotalAmount;
	
	/**
	 * 直接下级分销商数量
	 */
	private Integer subSellerCount;
	/**
	 * 会员的第三方平台昵称
	 */
	private String platformUsername;
	
	/**
	 * 上级分销商账号
	 */
	private String parentLoginName;
	
	private String parentPlatformUsername;
	
	public String getPlatformUsername() {
		return platformUsername;
	}

	public void setPlatformUsername(String platformUsername) {
		this.platformUsername = platformUsername;
	}

	public SellerInfoOutputDto(){
	}

	public SellerInfoOutputDto(
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
	public void setCashedTotalAmount(Double value) {
		this.cashedTotalAmount = value;
	}
	
	public Double getCashedTotalAmount() {
		return this.cashedTotalAmount;
	}
	public void setCashingTotalAmount(Double value) {
		this.cashingTotalAmount = value;
	}
	
	public Double getCashingTotalAmount() {
		return this.cashingTotalAmount;
	}
	public void setBalanceTotalAmount(Double value) {
		this.balanceTotalAmount = value;
	}
	
	public Double getBalanceTotalAmount() {
		return this.balanceTotalAmount;
	}
	public void setCommissionTotalAmount(Double value) {
		this.commissionTotalAmount = value;
	}
	
	public Double getCommissionTotalAmount() {
		return this.commissionTotalAmount;
	}
	public void setParentId(String value) {
		this.parentId = value;
	}
	
	public String getParentId() {
		return this.parentId == null ? null : this.parentId.trim();
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(String memberSex) {
		this.memberSex = memberSex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Double getCommissionTotalAmountForParent() {
		return commissionTotalAmountForParent;
	}

	public void setCommissionTotalAmountForParent(
			Double commissionTotalAmountForParent) {
		this.commissionTotalAmountForParent = commissionTotalAmountForParent;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}

	/*public Double getSubCommissionTotalAmount() {
		return subCommissionTotalAmount;
	}

	public void setSubCommissionTotalAmount(Double subCommissionTotalAmount) {
		this.subCommissionTotalAmount = subCommissionTotalAmount;
	}*/

	public Integer getSubSellerCount() {
		return subSellerCount;
	}

	public void setSubSellerCount(Integer subSellerCount) {
		this.subSellerCount = subSellerCount;
	}

	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public String getParentLoginName() {
		return parentLoginName;
	}

	public void setParentLoginName(String parentLoginName) {
		this.parentLoginName = parentLoginName;
	}

	public String getParentPlatformUsername() {
		return parentPlatformUsername;
	}

	public void setParentPlatformUsername(String parentPlatformUsername) {
		this.parentPlatformUsername = parentPlatformUsername;
	}
	
}

