 /*
 * 版本信息
 
 * 日期 2016-05-31 15:04:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderRemarkEntity;

/**
 * IOrderRemarkService接口
 * @author luoq
 * @Date 创建时间：2016-05-31 15:04:40
 */
public interface IOrderRemarkService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(OrderRemarkEntity orderRemarkEntity);

	/**
	 * 获取分页数据
	 */
	public List<OrderRemarkEntity> findPage(OrderRemarkEntity orderRemarkEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderRemarkEntity orderRemarkEntity);
	
	/**
	 * 更新记录
	 */
	public int update(OrderRemarkEntity orderRemarkEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderRemarkEntity getById(String id); 
}