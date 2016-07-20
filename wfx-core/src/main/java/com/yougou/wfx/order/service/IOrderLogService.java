 /*
 * 版本信息
 
 * 日期 2016-04-07 10:33:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderLogEntity;

/**
 * IOrderLogService接口
 * @author wfx
 * @Date 创建时间：2016-04-07 10:33:53
 */
public interface IOrderLogService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(OrderLogEntity orderLogEntity);

	/**
	 * 获取分页数据
	 */
	public List<OrderLogEntity> findPage(OrderLogEntity orderLogEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderLogEntity orderLogEntity);
	
	/**
	 * 更新记录
	 */
	public int update(OrderLogEntity orderLogEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderLogEntity getById(String id); 
	
	/**
	 * 获取所有订单/退款日志
	 * @param orderLogEntity
	 * @return
	 */
	List<OrderLogEntity> queryList(OrderLogEntity orderLogEntity);
	
	/**
	 * 插入订单日志
	 * @param order
	 * @param optType
	 * @param optBlone
	 * @param optResult
	 */
	public void insertOrderLog(String orderNo,String optUser,Integer optType,Integer optBlone,Integer optResult);
}