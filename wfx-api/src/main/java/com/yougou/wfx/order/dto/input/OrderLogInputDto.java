 /*
 * 版本信息
 
 * 日期 2016-04-07 10:33:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.InputDto;

/**
 * OrderLogInputDto
 * @author wfx
 * @Date 创建时间：2016-04-07 10:33:53
 */
public class OrderLogInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 订单号
	 */
	private String orderNo;
	/**
	 * 订单明细编号
	 */
	private String orderDetailNo;
	/**
	 * 退款单号
	 */
	private String rejectedNo;
	/**
	 * 操作人
	 */
	private String optUser;
	/**
	 * 操作行为，如：申请退款，修改退款等等 具体参考OrderLogOptEnum
	 */
	private Integer optType;
	/**
	 * 操作人所属方 1：买家 2：卖家
	 */
	private Integer optBelong;
	/**
	 * 日志内容
	 */
	private String logInfo;
	/**
	 * 1:操作日志 2：正常流转日志
	 */
	private Integer type;
	/**
	 * 1:订单日志 2：退款单日志
	 */
	private Integer logType;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 操作结果：1成功，2：失败
	 */
	private Integer optResult;
	/**
	 * 查看展示类型：1：前台，2：后台 3：前后台
	 */
	private Integer showType;

	public OrderLogInputDto(){
	}

	public OrderLogInputDto(
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
	public void setOrderNo(String value) {
		this.orderNo = value;
	}
	
	public String getOrderNo() {
		return this.orderNo == null ? null : this.orderNo.trim();
	}
	public void setOrderDetailNo(String value) {
		this.orderDetailNo = value;
	}
	
	public String getOrderDetailNo() {
		return this.orderDetailNo == null ? null : this.orderDetailNo.trim();
	}
	public void setRejectedNo(String value) {
		this.rejectedNo = value;
	}
	
	public String getRejectedNo() {
		return this.rejectedNo == null ? null : this.rejectedNo.trim();
	}
	public void setOptUser(String value) {
		this.optUser = value;
	}
	
	public String getOptUser() {
		return this.optUser == null ? null : this.optUser.trim();
	}
	public void setOptType(Integer value) {
		this.optType = value;
	}
	
	public Integer getOptType() {
		return this.optType == null ? 0 : this.optType;
	}
	public void setOptBelong(Integer value) {
		value = value == null ? 0 : value;
		this.optBelong = value;
	}
	
	public Integer getOptBelong() {
		return this.optBelong == null ? 0 : this.optBelong;
	}
	public void setLogInfo(String value) {
		this.logInfo = value;
	}
	
	public String getLogInfo() {
		return this.logInfo == null ? null : this.logInfo.trim();
	}
	public void setType(Integer value) {
		value = value == null ? 0 : value;
		this.type = value;
	}
	
	public Integer getType() {
		return this.type == null ? 0 : this.type;
	}
	public void setLogType(Integer value) {
		value = value == null ? 0 : value;
		this.logType = value;
	}
	
	public Integer getLogType() {
		return this.logType == null ? 0 : this.logType;
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
	public void setOptResult(Integer value) {
		value = value == null ? 0 : value;
		this.optResult = value;
	}
	
	public Integer getOptResult() {
		return this.optResult == null ? 0 : this.optResult;
	}
	public void setShowType(Integer value) {
		value = value == null ? 0 : value;
		this.showType = value;
	}
	
	public Integer getShowType() {
		return this.showType == null ? 0 : this.showType;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

