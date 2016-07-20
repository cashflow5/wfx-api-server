 /*
 * 版本信息
 
 * 日期 2016-04-18 14:41:51
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.model.OrderConsignDetailEntity;

/**
 * OrderConsignDetailMapper
 * @author wzf
 * @Date 创建时间：2016-04-18 14:41:52
 */
public interface OrderConsignDetailMapper{
	
	int findPageCount(OrderConsignDetailEntity orderConsignDetailEntity);

	List<OrderConsignDetailEntity> findPage(OrderConsignDetailEntity orderConsignDetailEntity,RowBounds rowBounds);

	List<OrderConsignDetailEntity> queryList(OrderConsignDetailEntity orderConsignDetailEntity);
	
	int insert(OrderConsignDetailEntity orderConsignDetailEntity);
	
	int update(OrderConsignDetailEntity orderConsignDetailEntity);
	
	int remove(String id);
	
	OrderConsignDetailEntity getById(String id);
	
	Integer queryQualifiedByOrderDetailId(@Param("orderDetailId")String orderDetailId,@Param("curDate")Date curDate);
	
	Integer queryConsignStatusByWfxOrderNo(@Param("wfxOrderNo")String wfxOrderNo);
}
