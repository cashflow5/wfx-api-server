package com.yougou.wfx.aftersale.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

public class OrderAfterSaleSearchDto extends OutputDto{

	/**
	 * 序列化版本号
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 退款单状态
	 */
	private String refundStatus;

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
}
