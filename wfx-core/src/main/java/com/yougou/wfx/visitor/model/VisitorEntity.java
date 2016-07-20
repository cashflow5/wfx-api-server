package com.yougou.wfx.visitor.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.util.DatetimeUtil;

public class VisitorEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private String id;
	/**
	 * 店铺id
	 */
	private String shopId;
	/**
	 * 访客id
	 */
	private String visitorId;
	/**
	 * 访客ip地址
	 */
	private String visitorIp;
	/**
	 * 访客来源,微信：weixin,其他:other
	 */
	private String sourceType;
	/**
	 * 访问日期
	 */
	private Date visitTime;
	/**
	 * 访问类型,1、店铺首页,2、商品页
	 */
	private Integer visitType;
	/**
	 * 款色ID,访问商品页时记录
	 */
	private String commodityNo;
	
	/**
	 * 访客头像
	 */
	private String headShowImg;
	
	/**
	 * 访客名称(昵称)
	 */
	private String visitorName;

	public VisitorEntity(){}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setShopId(String value) {
		this.shopId = value;
	}
	
	public String getShopId() {
		return this.shopId == null ? null : this.shopId.trim();
	}
	public void setVisitorId(String value) {
		this.visitorId = value;
	}
	
	public String getVisitorId() {
		return this.visitorId == null ? null : this.visitorId.trim();
	}
	public void setVisitorIp(String value) {
		this.visitorIp = value;
	}
	
	public String getVisitorIp() {
		return this.visitorIp == null ? null : this.visitorIp.trim();
	}
	public void setSourceType(String value) {
		this.sourceType = value;
	}
	
	public String getSourceType() {
		return this.sourceType == null ? null : this.sourceType.trim();
	}
	public void setVisitTime(Date value) {
		this.visitTime = value;
	}
	
	public Date getVisitTime() {
		return this.visitTime;
	}
	
	public String getStringVisitTime() {
		if(this.visitTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.visitTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setVisitType(Integer value) {
		value = value == null ? 0 : value;
		this.visitType = value;
	}
	
	public Integer getVisitType() {
		return this.visitType == null ? 0 : this.visitType;
	}
	public void setCommodityNo(String value) {
		this.commodityNo = value;
	}
	
	public String getCommodityNo() {
		return this.commodityNo == null ? null : this.commodityNo.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public String getHeadShowImg() {
		return headShowImg;
	}

	public void setHeadShowImg(String headShowImg) {
		this.headShowImg = headShowImg;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}
}
