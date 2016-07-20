 /*
 * 版本信息
 
 * 日期 2016-04-13 18:17:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.order.dto.output.OrderDetailCommDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;
import com.yougou.wfx.order.model.OrderDetailEntity;

/**
 * OrderDetailMapper
 * @author wfx
 * @Date 创建时间：2016-04-13 18:17:52
 */
public interface OrderDetailMapper{
	
	int findPageCount(OrderDetailEntity orderDetailEntity);

	List<OrderDetailEntity> findPage(OrderDetailEntity orderDetailEntity,RowBounds rowBounds);
	
	int queryListCount(OrderDetailEntity orderDetailEntity);

	List<OrderDetailEntity> queryList(OrderDetailEntity orderDetailEntity);
	
	int insert(OrderDetailEntity orderDetailEntity);
	
	int update(OrderDetailEntity orderDetailEntity);
	
	int remove(String id);
	
	OrderDetailEntity getById(String id);

	String getByOrderIdAndProId(@Param("orderId")String orderId, @Param("prodId")String prodId);
	
	List<OrderDetailCommDto> queryCommsByOrderId(String orderId);
	
	OrderDetailCommissionOutPutDto getCommByCommId(String id);
	
	List<String> getByOrderId(@Param("orderId")String orderId);
	/** 查某订单的detail记录，包括退款状态*/
	List<OrderDetailEntity> queryDetailListByOrderId( @Param("orderId")String orderId );
	
	Double getRealCommission(Map<String, Object> paramMap);
}
