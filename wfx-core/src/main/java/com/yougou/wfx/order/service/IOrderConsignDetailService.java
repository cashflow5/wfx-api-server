 /*
 * 版本信息
 
 * 日期 2016-04-18 14:41:51
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderConsignDetailEntity;

/**
 * IOrderConsignDetailService接口
 * @author wzf
 * @Date 创建时间：2016-04-18 14:41:52
 */
public interface IOrderConsignDetailService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(OrderConsignDetailEntity orderConsignDetailEntity);

	/**
	 * 获取分页数据
	 */
	public List<OrderConsignDetailEntity> findPage(OrderConsignDetailEntity orderConsignDetailEntity,RowBounds rowBounds);
	
	/**
	 * 获取查询数据
	 */
	public List<OrderConsignDetailEntity> queryList(OrderConsignDetailEntity orderConsignDetailEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(OrderConsignDetailEntity orderConsignDetailEntity);
	
	/**
	 * 更新记录
	 */
	public int update(OrderConsignDetailEntity orderConsignDetailEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderConsignDetailEntity getById(String id); 
	
	/**
	 * 根据微分销订单号获取订单得发货状态(全部发货/部分发货/都没发货) 【排除已经退款的订单】
	 */
	public int queryConsignStatusByWfxOrderNo(String wfxOrderNo); 
}