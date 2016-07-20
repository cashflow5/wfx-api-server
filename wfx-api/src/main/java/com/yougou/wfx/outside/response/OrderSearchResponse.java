package com.yougou.wfx.outside.response;

import java.util.List;

import com.yougou.wfx.outside.domain.Order;

/**
 * <p>Title: OrderSearchResponse</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderSearchResponse extends BaseResponse{

	private static final long serialVersionUID = -1558660252045099700L;

	//订单列表
	private List<Order> orderList;
	//总数
    private int totalResults;

	public List<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Order> orderList) {
		this.orderList = orderList;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	

}
