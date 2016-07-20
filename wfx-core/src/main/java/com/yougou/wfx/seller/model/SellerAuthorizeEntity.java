 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * SellerAuthorizeEntity
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
public class SellerAuthorizeEntity implements java.io.Serializable{
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
	 * 身份证扫描件地址
	 */
	private String idCardPic;
	/**
	 * 身份证反面
	 */
	private String idCardPicBack;
	/**
	 * 授权扫描件地址
	 */
	private String authorizePic;
	/**
	 * 状态 1待审核，2审核通过 3审核不通过
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
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

	public SellerAuthorizeEntity(){
	}

	public SellerAuthorizeEntity(
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
	public void setIdCardPic(String value) {
		this.idCardPic = value;
	}
	
	public String getIdCardPic() {
		return this.idCardPic == null ? null : this.idCardPic.trim();
	}
	public void setAuthorizePic(String value) {
		this.authorizePic = value;
	}
	
	public String getAuthorizePic() {
		return this.authorizePic == null ? null : this.authorizePic.trim();
	}
	public void setStatus(Integer value) {
		value = value == null ? 0 : value;
		this.status = value;
	}
	
	public Integer getStatus() {
		return this.status == null ? 0 : this.status;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getIdCardPicBack() {
		return idCardPicBack;
	}

	public void setIdCardPicBack(String idCardPicBack) {
		this.idCardPicBack = idCardPicBack;
	}
}

