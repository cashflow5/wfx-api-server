 /*
 * 版本信息
 
 * 日期 2016-04-18 14:31:47
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderConsignEntity;

/**
 * OrderConsignMapper
 * @author wzf
 * @Date 创建时间：2016-04-18 14:31:48
 */
public interface OrderConsignMapper{
	
	int findPageCount(OrderConsignEntity orderConsignEntity);

	List<OrderConsignEntity> findPage(OrderConsignEntity orderConsignEntity,RowBounds rowBounds);

	List<OrderConsignEntity> queryList(OrderConsignEntity orderConsignEntity);
	
	int insert(OrderConsignEntity orderConsignEntity);
	
	int update(OrderConsignEntity orderConsignEntity);
	
	int remove(String id);
	
	OrderConsignEntity getById(String id);
}
