 /*
 * 版本信息
 
 * 日期 2016-04-08 09:36:01
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.InputDto;

/**
 * CommodityBagRelaInputDto
 * @author zheng.qq
 * @Date 创建时间：2016-04-08 09:36:16
 */
public class CommodityBagRelaInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	private String id;
	/**
	 * 分销包id
	 */
	private String bagId;
	/**
	 * 商品id
	 */
	private String commodityId;
	/**
	 * 创建时间
	 */
	private Date createTime;

	public CommodityBagRelaInputDto(){
	}

	public CommodityBagRelaInputDto(
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
	public void setBagId(String value) {
		this.bagId = value;
	}
	
	public String getBagId() {
		return this.bagId == null ? null : this.bagId.trim();
	}
	public void setCommodityId(String value) {
		this.commodityId = value;
	}
	
	public String getCommodityId() {
		return this.commodityId == null ? null : this.commodityId.trim();
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

