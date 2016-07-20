 /*
 * 版本信息
 
 * 日期 2016-06-23 14:26:46
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.visitor.dto.input;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.InputDto;

/**
 * VisitorPageInputDto
 * @author zhang.f
 * @Date 创建时间：2016-06-23 14:26:48
 */
public class VisitorPageInputDto extends InputDto{

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
	 * 商品编码,访问商品页时记录
	 */
	private String commodityNo;
	
	public VisitorPageInputDto(){
	}

	public VisitorPageInputDto(
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
}
	
