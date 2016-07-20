 /*
 * 版本信息
 
 * 日期 2016-04-15 11:56:30
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.order.dto.input.OrderDetailInputDto;
import com.yougou.wfx.order.dto.input.OrderDetailPageInputDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;
import com.yougou.wfx.order.dto.output.OrderDetailOutputDto;

/**
 * IOrderDetailBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-15 11:56:31
 */
public interface IOrderDetailBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderDetailInputDto orderDetailDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<OrderDetailOutputDto> findPage(OrderDetailPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 获取查询数量
	 */
	public int queryListCount(OrderDetailPageInputDto pageInputDto);
	
	/**
	 * 获取查询数据
	 */
	public List<OrderDetailOutputDto> queryList(OrderDetailInputDto pageInputDto);
	
	/**
	 * 更新记录
	 */
	public int update(OrderDetailInputDto orderDetailDto);
	
	/**
	 * 通过id查询数据
	 */
	public OrderDetailOutputDto getById(String id);
	
	/**
	 * 通过子订单号查询子订单的佣金详情
	 * @param detailNo
	 */
	public OrderDetailCommissionOutPutDto getOrderDetailCommission(String commissionDetailId);
}

