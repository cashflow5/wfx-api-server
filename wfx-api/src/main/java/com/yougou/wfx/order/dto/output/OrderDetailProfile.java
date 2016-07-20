 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dto.output;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.OutputDto;

/**
 * OrderDetailProfile
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public class OrderDetailProfile extends OutputDto {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 订单列表中子订单显示退款的状态类型
	 * 1.退货/退款
	 * 2.退款中
	 * 3.已退款
	 * 4.无（确认收货7天后关闭退款、交易关闭、等待付款）
	 * 
	 */
	private String refudnShowStatus;
	/**
	 * 已退金额
	 */
	private Double refundMoney;
	/**
	 * 已退数量
	 */
	private Integer refundNum;
	/**
	 * 可退金额
	 */
	private Double canRefundMoney;
	/**
	 * 可退数量
	 */
	private Double canRefundNum;
	public String getRefudnShowStatus() {
		return refudnShowStatus;
	}
	public void setRefudnShowStatus(String refudnShowStatus) {
		this.refudnShowStatus = refudnShowStatus;
	}
	public Double getRefundMoney() {
		return refundMoney;
	}
	public void setRefundMoney(Double refundMoney) {
		this.refundMoney = refundMoney;
	}
	public Integer getRefundNum() {
		return refundNum;
	}
	public void setRefundNum(Integer refundNum) {
		this.refundNum = refundNum;
	}
	public Double getCanRefundMoney() {
		return canRefundMoney;
	}
	public void setCanRefundMoney(Double canRefundMoney) {
		this.canRefundMoney = canRefundMoney;
	}
	public Double getCanRefundNum() {
		return canRefundNum;
	}
	public void setCanRefundNum(Double canRefundNum) {
		this.canRefundNum = canRefundNum;
	}
}

