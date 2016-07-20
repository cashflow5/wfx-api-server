 /*
 * 版本信息
 
 * 日期 2016-03-24 18:30:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;
import com.yougou.wfx.member.dto.output.MemberProfileOutputDto;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;
import com.yougou.wfx.shop.dto.output.ShopOutputDto;

/**
 * MemberAccountEntity
 * @author luoq
 * @Date 创建时间：2016-03-24 18:30:55
 */
public class MemberAccountEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * 登录账号
	 */
	private String loginName;
	/**
	 * 登录密码
	 */
	private String loginPassword;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 登录次数统计
	 */
	private Integer loginCount;
	/**
	 * 锁定状态:0=锁定，1=有效
	 */
	private Integer state;
	/**
	 * 最后一次登录的ip地址
	 */
	private String lastLoginIp;
	/**
	 * tbl_member_level.ID
	 */
	private String levelId;
	/**
	 * 注册、找回密码等操作的临时码
	 */
	private String validateCode;
	/**
	 * 会员名称
	 */
	private String memberName;
	/**
	 * platformUsername
	 */
	private String platformUsername;
	/**
	 * 是否老用户字段,1-新用户，2-老用户
	 */
	private Integer hasCompletedOrder;
	/**
	 * 注册平台
	 */
	private String platform;
	/**
	 * 注册类型
	 */
	private String regType;
	/**
	 * 推广渠道
	 */
	private String extensionChannel;
	/**
	 * 最后登录身份(平台),1=普通会员登录,2=分销商登录
	 */
	private String lastLoginSite;
	/**
	 * 会员身份，1:普通会员，2：分销商
	 */
	private Integer memberType;
	/**
	 * 用户绑定的手机号码
	 */
	private String registerCheckMobile;
	/**
	 * 用户绑定手机时间
	 */
	private Date registerCheckMobileTime;
	/**
	 * 注册ip
	 */
	private String registerIp;
	/**
	 * 注册时间
	 */
	private Date registerDate;
	/**
	 * 已支付的 订单总数
	 */
	private Integer orderCount;
	/**
	 * 已支付的订单总金额
	 */
	private Double orderAmount;
	
	/**
	 * 会员生日[年-月-日]
	 */
	private Date birthday;
	
	/**
	 * 会员性别,1=男，0=女
	 */
	private Integer memberSex;
	
	/**
	 * 注册时间起始
	 */
	private Date registerDateStart;
	
	/**
	 * 注册时间截止
	 */
	private Date registerDateEnd;
	
	/**
	 * 最后登录时间起始
	 */
	private Date lastLoginTimeStart;
	
	/**
	 * 最后登录时间截止
	 */
	private Date lastLoginTimeEnd;
	
//columns END
	
	/**
	 * 用户基本信息
	 */
	private MemberProfileOutputDto profileInfo;
	
	/**
	 * 用户店铺信息
	 */
	private ShopOutputDto shopInfo;
	
	/**
	 * 用户分销商信息
	 */
	private SellerInfoOutputDto sellerInfo;
	/**
	 * 潜在上级分销商Id
	 */
	private String parentSellerId;
	
	/** 实际是union_id */
	private String openId;
	
	/** h5 openId */
	private String h5OpenId;
	
	private String headShowImg;

	public String getParentSellerId() {
		return parentSellerId;
	}

	public void setParentSellerId(String parentSellerId) {
		this.parentSellerId = parentSellerId;
	}
	public MemberAccountEntity(){
	}

	public MemberAccountEntity(
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
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName == null ? null : this.loginName.trim();
	}
	public void setLoginPassword(String value) {
		this.loginPassword = value;
	}
	
	public String getLoginPassword() {
		return this.loginPassword == null ? null : this.loginPassword.trim();
	}
	public void setLastLoginTime(Date value) {
		this.lastLoginTime = value;
	}
	
	public Date getLastLoginTime() {
		return this.lastLoginTime;
	}
	
	public String getStringLastLoginTime() {
		if(this.lastLoginTime == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.lastLoginTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setLoginCount(Integer value) {
		value = value == null ? 0 : value;
		this.loginCount = value;
	}
	
	public Integer getLoginCount() {
		return this.loginCount == null ? 0 : this.loginCount;
	}
	public void setState(Integer value) {
		value = value == null ? 0 : value;
		this.state = value;
	}
	
	public Integer getState() {
		return this.state == null ? 0 : this.state;
	}
	public void setLastLoginIp(String value) {
		this.lastLoginIp = value;
	}
	
	public String getLastLoginIp() {
		return this.lastLoginIp == null ? null : this.lastLoginIp.trim();
	}
	public void setLevelId(String value) {
		this.levelId = value;
	}
	
	public String getLevelId() {
		return this.levelId == null ? null : this.levelId.trim();
	}
	public void setValidateCode(String value) {
		this.validateCode = value;
	}
	
	public String getValidateCode() {
		return this.validateCode == null ? null : this.validateCode.trim();
	}
	public void setMemberName(String value) {
		this.memberName = value;
	}
	
	public String getMemberName() {
		return this.memberName == null ? null : this.memberName.trim();
	}
	public void setPlatformUsername(String value) {
		this.platformUsername = value;
	}
	
	public String getPlatformUsername() {
		return this.platformUsername == null ? null : this.platformUsername.trim();
	}
	public void setHasCompletedOrder(Integer value) {
		value = value == null ? 0 : value;
		this.hasCompletedOrder = value;
	}
	
	public Integer getHasCompletedOrder() {
		return this.hasCompletedOrder == null ? 0 : this.hasCompletedOrder;
	}
	public void setPlatform(String value) {
		this.platform = value;
	}
	
	public String getPlatform() {
		return this.platform == null ? null : this.platform.trim();
	}
	public void setRegType(String value) {
		this.regType = value;
	}
	
	public String getRegType() {
		return this.regType == null ? null : this.regType.trim();
	}
	public void setExtensionChannel(String value) {
		this.extensionChannel = value;
	}
	
	public String getExtensionChannel() {
		return this.extensionChannel == null ? null : this.extensionChannel.trim();
	}
	public void setLastLoginSite(String value) {
		this.lastLoginSite = value;
	}
	
	public String getLastLoginSite() {
		return this.lastLoginSite == null ? null : this.lastLoginSite.trim();
	}
	public void setMemberType(Integer value) {
		value = value == null ? 0 : value;
		this.memberType = value;
	}
	
	public Integer getMemberType() {
		return this.memberType == null ? 0 : this.memberType;
	}
	public void setRegisterCheckMobile(String value) {
		this.registerCheckMobile = value;
	}
	
	public String getRegisterCheckMobile() {
		return this.registerCheckMobile == null ? null : this.registerCheckMobile.trim();
	}
	public void setRegisterCheckMobileTime(Date value) {
		this.registerCheckMobileTime = value;
	}
	
	public Date getRegisterCheckMobileTime() {
		return this.registerCheckMobileTime;
	}
	
	public String getStringRegisterCheckMobileTime() {
		if(this.registerCheckMobileTime == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.registerCheckMobileTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setRegisterIp(String value) {
		this.registerIp = value;
	}
	
	public String getRegisterIp() {
		return this.registerIp == null ? null : this.registerIp.trim();
	}
	public void setRegisterDate(Date value) {
		this.registerDate = value;
	}
	
	public Date getRegisterDate() {
		return this.registerDate;
	}
	
	public String getStringRegisterDate() {
		if(this.registerDate == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.registerDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public ShopOutputDto getShopInfo() {
		return shopInfo;
	}

	public void setShopInfo(ShopOutputDto shopInfo) {
		this.shopInfo = shopInfo;
	}

	public SellerInfoOutputDto getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(SellerInfoOutputDto sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public Date getRegisterDateStart() {
		return registerDateStart;
	}

	public void setRegisterDateStart(Date registerDateStart) {
		this.registerDateStart = registerDateStart;
	}

	public Date getRegisterDateEnd() {
		return registerDateEnd;
	}

	public void setRegisterDateEnd(Date registerDateEnd) {
		this.registerDateEnd = registerDateEnd;
	}

	public Date getLastLoginTimeStart() {
		return lastLoginTimeStart;
	}

	public void setLastLoginTimeStart(Date lastLoginTimeStart) {
		this.lastLoginTimeStart = lastLoginTimeStart;
	}

	public Date getLastLoginTimeEnd() {
		return lastLoginTimeEnd;
	}

	public void setLastLoginTimeEnd(Date lastLoginTimeEnd) {
		this.lastLoginTimeEnd = lastLoginTimeEnd;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Integer getMemberSex() {
		return memberSex;
	}

	public void setMemberSex(Integer memberSex) {
		this.memberSex = memberSex;
	}

	public MemberProfileOutputDto getProfileInfo() {
		return profileInfo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public void setProfileInfo(MemberProfileOutputDto profileInfo) {
		this.profileInfo = profileInfo;
	}

	public String getH5OpenId() {
		return h5OpenId;
	}

	public void setH5OpenId(String h5OpenId) {
		this.h5OpenId = h5OpenId;
	}

	public String getHeadShowImg() {
		return headShowImg;
	}

	public void setHeadShowImg(String headShowImg) {
		this.headShowImg = headShowImg;
	}
}

