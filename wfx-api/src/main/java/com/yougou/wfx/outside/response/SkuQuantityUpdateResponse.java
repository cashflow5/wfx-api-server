package com.yougou.wfx.outside.response;

import java.util.List;

import com.yougou.wfx.outside.domain.Order;

/**
 * <p>Title: SkuQuantityUpdateResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class SkuQuantityUpdateResponse extends BaseResponse{

	private static final long serialVersionUID = -1558660252045099700L;

	private boolean result;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

}
