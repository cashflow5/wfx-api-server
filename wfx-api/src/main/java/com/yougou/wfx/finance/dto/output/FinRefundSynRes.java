package com.yougou.wfx.finance.dto.output;

import java.io.Serializable;

import com.yougou.wfx.finance.enums.FinRefundSynStateEnum;

public class FinRefundSynRes implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 返回状态枚举 */
	private FinRefundSynStateEnum refundSynState;
	
	/**
	 * 返回描述
	 */
	private String statusMsg;

	public FinRefundSynStateEnum getRefundSynState() {
		return refundSynState;
	}

	public void setRefundSynState(FinRefundSynStateEnum refundSynState) {
		this.refundSynState = refundSynState;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "FinRefundSynRes [refundSynState=" + refundSynState
				+ ", statusMsg=" + statusMsg + "]";
	}
	
}
