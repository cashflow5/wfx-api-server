 /*
 * 版本信息
 
 * 日期 2016-04-21 16:43:38
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.ShoppingcartEntity;

/**
 * ShoppingcartMapper
 * @author wfx
 * @Date 创建时间：2016-04-21 16:43:39
 */
public interface ShoppingcartMapper{
	
	int findPageCount(ShoppingcartEntity shoppingcartEntity);

	List<ShoppingcartEntity> findPage(ShoppingcartEntity shoppingcartEntity,RowBounds rowBounds);
	
	int insert(ShoppingcartEntity shoppingcartEntity);
	
	int update(ShoppingcartEntity shoppingcartEntity);
	
	int remove(String id);
	
	ShoppingcartEntity getById(String id);
}
