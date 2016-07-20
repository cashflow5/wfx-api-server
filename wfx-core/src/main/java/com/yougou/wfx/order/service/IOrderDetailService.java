 /*
 * 版本信息
 
 * 日期 2016-04-13 18:17:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.dto.output.OrderDetailCommDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;
import com.yougou.wfx.order.model.OrderDetailEntity;

/**
 * IOrderDetailService接口
 * @author wfx
 * @Date 创建时间：2016-04-13 18:17:52
 */
public interface IOrderDetailService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(OrderDetailEntity orderDetailEntity);

	/**
	 * 获取分页数据
	 */
	public List<OrderDetailEntity> findPage(OrderDetailEntity orderDetailEntity,RowBounds rowBounds);

	/**
	 * 获取查询数量
	 */
	public int queryListCount(OrderDetailEntity orderDetailEntity);
	
	/**
	 * 获取查询数据
	 */
	public List<OrderDetailEntity> queryList(OrderDetailEntity orderDetailEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(OrderDetailEntity orderDetailEntity);
	
	/**
	 * 更新记录
	 */
	public int update(OrderDetailEntity orderDetailEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderDetailEntity getById(String id); 
	
	List<OrderDetailCommDto> queryCommsByOrderId(String orderId);
	/**
	 * 通过order_id查询order_detail_id数据
	 */
	public List<String> getByOrderId(String orderId); 

	OrderDetailCommissionOutPutDto getCommByCommId(String id);

	Double getRealCommission(Map paramMap);

}