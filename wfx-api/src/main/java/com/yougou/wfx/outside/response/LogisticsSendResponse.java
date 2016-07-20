package com.yougou.wfx.outside.response;

import com.yougou.wfx.outside.domain.Order;

/**
 * <p>Title: OrderLogisticsSendResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class LogisticsSendResponse extends BaseResponse{

	private static final long serialVersionUID = -1558660252045099700L;
	
	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}
}
