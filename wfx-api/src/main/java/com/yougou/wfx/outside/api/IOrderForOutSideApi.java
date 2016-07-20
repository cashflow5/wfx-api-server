package com.yougou.wfx.outside.api;

import com.yougou.wfx.outside.request.OrderGetRequest;
import com.yougou.wfx.outside.request.OrderSearchRequest;
import com.yougou.wfx.outside.request.OrderUpdateRequest;
import com.yougou.wfx.outside.response.OrderGetResponse;
import com.yougou.wfx.outside.response.OrderSearchResponse;
import com.yougou.wfx.outside.response.OrderUpdateResponse;

/**
 * <p>Title: IOrderForOutSideApi</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public interface IOrderForOutSideApi {

	/**
	 * 查询订单
	 * @param request
	 * @return
	 */
	public OrderSearchResponse queryOrderList(OrderSearchRequest request) throws Exception ;
	/**
	 * 获取订单
	 * @param outerOrderNo
	 * @return
	 */
	public OrderGetResponse getOrder(OrderGetRequest request) throws Exception ;
	
	/**
	 * 修改订单
	 * @param outerOrderNo
	 * @return
	 */
	public OrderUpdateResponse updateOrder(OrderUpdateRequest request) throws Exception ;
	
}
