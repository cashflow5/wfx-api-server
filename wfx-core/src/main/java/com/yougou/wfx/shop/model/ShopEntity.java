 /*
 * 版本信息
 
 * 日期 2016-03-31 10:45:08
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.shop.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * ShopEntity
 * @author wuyang
 * @Date 创建时间：2016-03-31 10:45:08
 */
public class ShopEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 分销商ID
	 */
	private String sellerId;
	/**
	 * 登录账号
	 */
	private String loginName;
	/**
	 * 店铺名称
	 */
	private String name;
	/**
	 * 店铺公告
	 */
	private String notice;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 联系电话
	 */
	private String mobile;
	/**
	 * 店铺状态：1开启，2关闭
	 */
	private Integer status;
	/**
	 * 店铺URL
	 */
	private String shopUrl;
	/**
	 * 店铺Logo图片URL
	 */
	private String logoUrl;
	/**
	 * 店招图片URL
	 */
	private String signUrl;
	/**
	 * 二维码图片URL
	 */
	private String qrCodeUrl;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 最后更新人
	 */
	private String updateUser;
	/**
	 * 店铺跟踪码
	 */
	private String validateCode;
	/**
	 * 审核序号
	 */
	private Integer verifySeries;
	
	/**
	 * 创建时间起始
	 */
	private Date createTimeStart;
	/**
	 * 创建时间截止
	 */
	private Date createTimeEnd;
	/**
	 * 店铺编码
	 */
	private String shopCode;
	
	public String getShopCode() {
		return shopCode;
	}

	public void setShopCode(String shopCode) {
		this.shopCode = shopCode;
	}

	public Date getCreateTimeStart() {
		return createTimeStart;
	}

	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}

	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}

	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}

	public ShopEntity(){
	}

	public ShopEntity(
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
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName == null ? null : this.loginName.trim();
	}
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name == null ? null : this.name.trim();
	}
	public void setNotice(String value) {
		this.notice = value;
	}
	
	public String getNotice() {
		return this.notice == null ? null : this.notice.trim();
	}
	public void setContact(String value) {
		this.contact = value;
	}
	
	public String getContact() {
		return this.contact == null ? null : this.contact.trim();
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	
	public String getMobile() {
		return this.mobile == null ? null : this.mobile.trim();
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
	}
	public void setShopUrl(String value) {
		this.shopUrl = value;
	}
	
	public String getShopUrl() {
		return this.shopUrl == null ? null : this.shopUrl.trim();
	}
	public void setLogoUrl(String value) {
		this.logoUrl = value;
	}
	
	public String getLogoUrl() {
		return this.logoUrl == null ? null : this.logoUrl.trim();
	}
	public void setSignUrl(String value) {
		this.signUrl = value;
	}
	
	public String getSignUrl() {
		return this.signUrl == null ? null : this.signUrl.trim();
	}
	public void setQrCodeUrl(String value) {
		this.qrCodeUrl = value;
	}
	
	public String getQrCodeUrl() {
		return this.qrCodeUrl == null ? null : this.qrCodeUrl.trim();
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
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public String getStringUpdateTime() {
		if(this.updateTime == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.updateTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser == null ? null : this.updateUser.trim();
	}
	public void setValidateCode(String value) {
		this.validateCode = value;
	}
	
	public String getValidateCode() {
		return this.validateCode == null ? null : this.validateCode.trim();
	}
	public void setVerifySeries(Integer value) {
		value = value == null ? 0 : value;
		this.verifySeries = value;
	}
	
	public Integer getVerifySeries() {
		return this.verifySeries == null ? 0 : this.verifySeries;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

