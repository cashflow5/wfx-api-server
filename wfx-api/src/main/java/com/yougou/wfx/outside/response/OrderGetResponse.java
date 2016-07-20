package com.yougou.wfx.outside.response;

import com.yougou.wfx.outside.domain.Order;

/**
 * <p>Title: OrderGetResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderGetResponse extends BaseResponse{

	private static final long serialVersionUID = -1558660252045099700L;

	//订单列表
	private Order order;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
