 /*
 * 版本信息
 
 * 日期 2016-04-07 10:33:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderLogEntity;

/**
 * OrderLogMapper
 * @author wfx
 * @Date 创建时间：2016-04-07 10:33:53
 */
public interface OrderLogMapper{
	
	int findPageCount(OrderLogEntity orderLogEntity);

	List<OrderLogEntity> findPage(OrderLogEntity orderLogEntity,RowBounds rowBounds);
	
	List<OrderLogEntity> queryList(OrderLogEntity orderLogEntity);
	
	int insert(OrderLogEntity orderLogEntity);
	
	int update(OrderLogEntity orderLogEntity);
	
	int remove(String id);
	
	OrderLogEntity getById(String id);
}
