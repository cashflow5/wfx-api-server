package com.yougou.wfx.outside.response;


/**
 * <p>Title: OrderUpdateResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderUpdateResponse extends BaseResponse{

	private static final long serialVersionUID = -1558660252045099700L;

	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	
}
