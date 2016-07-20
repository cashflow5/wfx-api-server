package com.yougou.wfx.outside.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.outside.domain.Order;
import com.yougou.wfx.outside.request.OrderGetRequest;
import com.yougou.wfx.outside.request.OrderSearchRequest;
import com.yougou.wfx.outside.request.OrderUpdateRequest;

/**
 * <p>Title: OrderForOutSideMapper</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public interface OrderForOutSideMapper {
	
	int queryOrderCount(OrderSearchRequest orderSearchRequest);
	
	List<Order> queryOrderList(OrderSearchRequest orderSearchRequest,RowBounds rowBounds);
	
	List<Order> getOrder(OrderGetRequest orderGetRequest);
	
	int updateOrder(@Param(value = "request") OrderUpdateRequest orderUpdateRequest,
			@Param(value = "updateTime") Date date);
	
	int updateOrderReturnAddressNo(@Param(value = "orderDetailNo") String orderDetailNo, 
			@Param(value = "returnAddressNo") String returnAddressNo, 
			@Param(value = "updateTime") Date updateTime);
}
