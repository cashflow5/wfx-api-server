 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.aftersale.model.OrderApplyEntity;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.order.dto.output.ConsignInfosOutPutDto;
import com.yougou.wfx.order.dto.output.OrderCreateDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;
import com.yougou.wfx.order.dto.output.OrderResultDto;
import com.yougou.wfx.order.model.CommissionOrderEntity;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.model.OrderCountEntity;
import com.yougou.wfx.order.model.OrderEntity;

/**
 * IOrderService接口
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public interface IOrderService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(OrderEntity orderEntity);

	/**
	 * 获取分页数据
	 */
	public List<OrderEntity> findPage(OrderEntity orderEntity,RowBounds rowBounds);
	
	/**
	 * 获取分销商及下级分销商订单的分页数据
	 */
	public List<OrderEntity> querySellerList(OrderEntity orderEntity,RowBounds rowBounds);
	
	public List<OrderEntity> findPage(OrderEntity orderEntity);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderEntity orderEntity);
	
	/**
	 * 更新记录
	 */
	public int update(OrderEntity orderEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderEntity getById(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderEntity getByWFXOrderNo(String wfxOrderNo);
	
	/**
	 * 分页查询订单申请表详情
	 * @param orderApplyEntity
	 * @param rowBounds
	 * @return
	 */
	List<OrderApplyEntity> queryApplyList(OrderApplyEntity orderApplyEntity,RowBounds rowBounds);
	
	/**
	 * 查询订单申请记录数量
	 * @param orderApplyEntity
	 * @return
	 */
	int queryApplyCount(OrderApplyEntity orderApplyEntity);
	
	/**
	 * 查询订单详情
	 * @param id
	 * @return
	 */
	public OrderApplyEntity queryOrderDetails(String id);
	/**
	 * 查询订单详情
	 * @param 微分销订单号
	 * @return
	 */
	OrderApplyEntity queryOrderDetailsByNo(String wfxOrderNo);
	
	/**
	 * 订单分页查询
	 * @param orderEntity
	 * @return
	 */
	PageModel<OrderOutputDto> queryPage(OrderEntity orderEntity,PageModel pageModel);
	
	/**
	 * 订单分页查询
	 * @param orderEntity
	 * @return
	 */
	PageModel<OrderOutputDto> querySellerPage(OrderEntity orderEntity,PageModel pageModel);
	
	/**
	 * 根据用户ID查询用户确认收货的订单数量
	 * @param memberId
	 * @return
	 */
	int queryReceivedCount(String memberId);
	
	List<ConsignInfosOutPutDto> getConsignInfosByOrderId(String orderId);
	
	/**
	 * 创建订单接口
	 * @param info
	 */
	OrderResultDto createOrder(OrderCreateDto orderCreateDto);
	
	/**
	 * C 端 获取用户订单数量：待付款、退款/售后 
	 * @param memberId
	 * @return
	 */
	OrderCountEntity getMemberOrderCount(String memberId);
	
	/**
	 * 查询出确认收货7天后未生成佣金的订单
	 * @param orderEntity
	 * @return
	 */
	List<OrderEntity> queryUnCommissionOrders(OrderEntity orderEntity);
	/**
	 * 定时作废未付款订单
	 */
	String closeOrders();
	/**
	 * 超时自动确认订单收货
	 * @return
	 */
	String autoChangeOrderStatusToDeliverd();
	
	/**
	 * 根据分销商ID获取最近7日（前6天+当天）订单数量
	 * @param OrderEntity orderEntity
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

	int findCommissionOrderPageCount(String sellerId);

	List<CommissionOrderEntity> findCommissionOrderPage(String sellerId,
			RowBounds rowBounds);
	
}