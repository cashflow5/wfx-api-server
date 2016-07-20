 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.aftersale.model.OrderApplyEntity;
import com.yougou.wfx.order.model.CommissionOrderEntity;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.model.OrderEntity;

/**
 * OrderMapper
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public interface OrderMapper{
	
	int findPageCount(OrderEntity orderEntity);

	List<OrderEntity> findPage(OrderEntity orderEntity,RowBounds rowBounds);

	int querySellerListCount(OrderEntity orderEntity);
	
	List<OrderEntity> querySellerList(OrderEntity orderEntity,RowBounds rowBounds);

	List<OrderEntity> findPage(OrderEntity orderEntity);
	
	List<OrderEntity> queryUnCommissionOrders(OrderEntity orderEntity);
	
	int insert(OrderEntity orderEntity);
	
	int update(OrderEntity orderEntity);
	
	int remove(String id);
	
	OrderEntity getById(String id);
	
	List<OrderApplyEntity> queryApplyList(OrderApplyEntity orderApplyEntity,RowBounds rowBounds);
	
	int queryApplyCount(OrderApplyEntity orderApplyEntity);
	
	OrderApplyEntity   queryOrderDetails(String id);

	OrderApplyEntity queryOrderDetailsByNo(String wfxOrderNo);
	
	/**
	 * 根据用户ID，查询用户确认收货订单数量
	 * @param memberId
	 * @return
	 */
	int queryReceivedCount(String memberId);
	
	/**
	 * 根据订单状态查询用户订单数量
	 * @param memberId
	 * @param status
	 * @return
	 */
	int queryMemberOrderCountByStatus(@Param("memberId") String memberId,@Param("status") String status);
	
	/**
	 * 根据用户ID查询其退款/售后中的订单数量
	 * @param memberId
	 * @return
	 */
	int queryMemberRefundOrderCount(String memberId);
	
	OrderEntity getByOrderDetailId(String orderDetailId);
	
	int closeOrders(@Param("seconds")int seconds, @Param("curDate")Date curDate);
	
	List<OrderEntity> queryForCloseOrders(@Param("seconds")int seconds, @Param("curDate")Date curDate);

	/**
	 * 调度使用：获取 超时自动确认收货的订单列表（初步缩小范畴）
	 * @param days
	 * @param curDate
	 */
	List<OrderEntity> queryAutoChangeOrderStatusList();
	
	/**
	 * 调度使用：获取 超时自动确认收货的订单列表（已发货）
	 * @param days
	 * @param curDate
	 */
	List<OrderEntity> queryAutoChangeOrderStatusList2(@Param("days")int days,@Param("curDate") Date curDate);
	
	/**
	 * 根据分销商ID 获取最近7日（前6天+当天）订单数量
	 * @param orderEntity
	 * @return
	 */
	int getSellerSevenDayOrderCount(OrderEntity orderEntity);
	
	/**
	 * 根据订单ID 获取订单物流信息
	 * @param orderId
	 * @return
	 */
	List<OrderConsignEntity> queryOrderConsign(@Param("order_id")String orderId);
	
	Double queryPreCommFee(Map<String, Object> paramMap);
	
	Double queryRefundCommFee(Map<String, Object> paramMap);
	
	List<CommissionOrderEntity> queryPreCommFeeList(String sellerId ,RowBounds rowBounds);

	int queryPreCommFeeCount(String sellerId);

	OrderEntity getByWFXOrderNo(String wfxOrderNo);
}
