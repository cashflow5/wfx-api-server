 /*
 * 版本信息
 
 * 日期 2016-05-31 15:04:39
 
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
 * OrderRemarkPageInputDto
 * @author luoq
 * @Date 创建时间：2016-05-31 15:04:40
 */
public class OrderRemarkPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 外键 关联订单表id
	 */
	private String orderId;
	/**
	 * 外键 关联会员表id
	 */
	private String memberId;
	/**
	 * 插入数据库时间
	 */
	private Date createTime;
	/**
	 * 1:优购标记 2：买家标记
	 */
	private Integer type;
	/**
	 * 备注
	 */
	private String markNote;
	/**
	 * 操作人
	 */
	private String operator;
	
	public OrderRemarkPageInputDto(){
	}

	public OrderRemarkPageInputDto(
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
	public void setOrderId(String value) {
		this.orderId = value;
	}
	
	public String getOrderId() {
		return this.orderId == null ? null : this.orderId.trim();
	}
	public void setMemberId(String value) {
		this.memberId = value;
	}
	
	public String getMemberId() {
		return this.memberId == null ? null : this.memberId.trim();
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
	public void setType(Integer value) {
		value = value == null ? 0 : value;
		this.type = value;
	}
	
	public Integer getType() {
		return this.type == null ? 0 : this.type;
	}
	public void setMarkNote(String value) {
		this.markNote = value;
	}
	
	public String getMarkNote() {
		return this.markNote == null ? null : this.markNote.trim();
	}
	public void setOperator(String value) {
		this.operator = value;
	}
	
	public String getOperator() {
		return this.operator == null ? null : this.operator.trim();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
