 /*
 * 版本信息
 
 * 日期 2016-05-31 15:04:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderRemarkEntity;

/**
 * OrderRemarkMapper
 * @author luoq
 * @Date 创建时间：2016-05-31 15:04:40
 */
public interface OrderRemarkMapper{
	
	int findPageCount(OrderRemarkEntity orderRemarkEntity);

	List<OrderRemarkEntity> findPage(OrderRemarkEntity orderRemarkEntity,RowBounds rowBounds);
	
	int insert(OrderRemarkEntity orderRemarkEntity);
	
	int update(OrderRemarkEntity orderRemarkEntity);
	
	int remove(String id);
	
	OrderRemarkEntity getById(String id);
}
