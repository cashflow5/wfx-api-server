 /*
 * 版本信息
 
 * 日期 2016-04-13 18:17:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.order.dao.OrderDetailMapper;
import com.yougou.wfx.order.dto.output.OrderDetailCommDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.service.IOrderDetailService;

/**
 * IOrderDetailService实现
 * @author wfx
 * @Date 创建时间：2016-04-13 18:17:52
 */
@Service
public class OrderDetailServiceImpl extends BaseService implements IOrderDetailService {
	
	@Resource
	private OrderDetailMapper orderDetailMapper;

	@Override
	public int findPageCount(OrderDetailEntity orderDetailEntity){
		return orderDetailMapper.findPageCount(orderDetailEntity);
	}

	@Override
	public List<OrderDetailEntity> findPage(OrderDetailEntity orderDetailEntity,RowBounds rowBounds){
		return orderDetailMapper.findPage(orderDetailEntity,rowBounds);
	}

	@Override
	public List<OrderDetailEntity> queryList(OrderDetailEntity orderDetailEntity){
		return orderDetailMapper.queryList(orderDetailEntity);
	}
	
	@Override
	@Transactional
	public String insert(OrderDetailEntity orderDetailEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		orderDetailEntity.setId(strId);
		orderDetailMapper.insert(orderDetailEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderDetailEntity orderDetailEntity){
		return  orderDetailMapper.update(orderDetailEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderDetailMapper.remove(id);
	}
	
	@Override
	public OrderDetailEntity getById(String id){
		return orderDetailMapper.getById(id);
	}

	@Override
	public int queryListCount(OrderDetailEntity orderDetailEntity) {
		return orderDetailMapper.queryListCount(orderDetailEntity);
	}

	@Override
	public List<OrderDetailCommDto> queryCommsByOrderId(String orderId) {
		return orderDetailMapper.queryCommsByOrderId(orderId);
	}

	@Override
	public List<String> getByOrderId(String orderId) {
		return orderDetailMapper.getByOrderId(orderId);
	}

	@Override
	public OrderDetailCommissionOutPutDto getCommByCommId(String id) {
		return orderDetailMapper.getCommByCommId(id);
	}

	@Override
	public Double getRealCommission(Map paramMap) {
		return orderDetailMapper.getRealCommission(paramMap);
	}

}