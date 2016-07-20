package com.yougou.wfx.order.dto.output;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

public class CommissionOrderOutputDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	
	private String orderId;

	private String wfxOrderNo;
	
	private Date createTime;
	
	private Double commission;
	
	private String level;
	// 佣金归属对象
	private String sellerId;
	
	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getWfxOrderNo() {
		return wfxOrderNo;
	}

	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String toString(){
		return ToStringBuilder.reflectionToString(this);
	}
}
