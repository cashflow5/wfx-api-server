 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.background;

import java.util.List;

import com.yougou.wfx.aftersale.dto.input.OrderApplyInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderApplyOutputDto;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.order.dto.input.OrderInputDto;
import com.yougou.wfx.order.dto.input.OrderPageInputDto;
import com.yougou.wfx.order.dto.output.OrderConsignOutputDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;

/**
 * IOrderBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public interface IOrderBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderInputDto orderDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<OrderOutputDto> findPage(OrderPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(OrderInputDto orderDto);
	
	/**
	 * 通过id查询数据
	 */
	public OrderOutputDto getById(String id);
	
	public PageModel<OrderApplyOutputDto> queryApplyList(OrderApplyInputDto inputDto,PageModel pageModel);
	
	public int queryApplyCount(OrderApplyInputDto inputDto);
	/**
	 * 查询订单详情
	 * @param id
	 * @return
	 */
	public OrderApplyOutputDto  queryOrderDetails(String id);
	
	public void generateCommission();
	
	/**
	 * 定时作废未付款订单
	 */
	public String closeOrders();
	/**
	 * 超时自动确认订单收货
	 * @return
	 */
	public String autoChangeOrderStatusToDeliverd();
	
	

	/**
	 * 订单详情页获取订单物流信息
	 * @param orderId
	 * @return
	 */
	List<OrderConsignOutputDto> getOrderLogisDetail(String orderId);
}

