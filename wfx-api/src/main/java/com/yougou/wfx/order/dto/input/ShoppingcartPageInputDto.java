 /*
 * 版本信息
 
 * 日期 2016-04-21 16:43:38
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.input;

import java.util.Date;
import com.yougou.wfx.util.DatetimeUtil;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.dto.base.InputDto;

/**
 * ShoppingcartPageInputDto
 * @author wfx
 * @Date 创建时间：2016-04-21 16:43:39
 */
public class ShoppingcartPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 用户ID
	 */
	private String loginId;
	/**
	 * 登录账号
	 */
	private String loginName;
	/**
	 * 店铺id
	 */
	private String shopId;
	/**
	 * 商品编码
	 */
	private String commodityNo;
	/**
	 * 货品编码
	 */
	private String productNo;
	/**
	 * 货品数量
	 */
	private Integer count;
	/**
	 * 勾选状态：1勾选，0未勾选
	 */
	private Integer isChecked;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	
	public ShoppingcartPageInputDto(){
	}

	public ShoppingcartPageInputDto(
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
	public void setLoginId(String value) {
		this.loginId = value;
	}
	
	public String getLoginId() {
		return this.loginId == null ? null : this.loginId.trim();
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName == null ? null : this.loginName.trim();
	}
	public void setShopId(String value) {
		this.shopId = value;
	}
	
	public String getShopId() {
		return this.shopId == null ? null : this.shopId.trim();
	}
	public void setCommodityNo(String value) {
		this.commodityNo = value;
	}
	
	public String getCommodityNo() {
		return this.commodityNo == null ? null : this.commodityNo.trim();
	}
	public void setProductNo(String value) {
		this.productNo = value;
	}
	
	public String getProductNo() {
		return this.productNo == null ? null : this.productNo.trim();
	}
	public void setCount(Integer value) {
		value = value == null ? 0 : value;
		this.count = value;
	}
	
	public Integer getCount() {
		return this.count == null ? 0 : this.count;
	}
	public void setIsChecked(Integer value) {
		this.isChecked = value;
	}
	
	public Integer getIsChecked() {
		return this.isChecked;
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
}
	
