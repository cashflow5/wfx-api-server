 /*
 * 版本信息
 
 * 日期 2016-04-18 14:31:47
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.order.dao.OrderConsignMapper;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.service.IOrderConsignService;

/**
 * IOrderConsignService实现
 * @author wzf
 * @Date 创建时间：2016-04-18 14:31:48
 */
@Service
public class OrderConsignServiceImpl extends BaseService implements IOrderConsignService {
	
	@Resource
	private OrderConsignMapper orderConsignMapper;

	@Override
	public int findPageCount(OrderConsignEntity orderConsignEntity){
		return orderConsignMapper.findPageCount(orderConsignEntity);
	}

	@Override
	public List<OrderConsignEntity> findPage(OrderConsignEntity orderConsignEntity,RowBounds rowBounds){
		return orderConsignMapper.findPage(orderConsignEntity,rowBounds);
	}

	@Override
	public List<OrderConsignEntity> queryList(OrderConsignEntity orderConsignEntity){
		return orderConsignMapper.queryList(orderConsignEntity);
	}
	
	@Override
	@Transactional
	public String insert(OrderConsignEntity orderConsignEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		orderConsignEntity.setId(strId);
		orderConsignMapper.insert(orderConsignEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderConsignEntity orderConsignEntity){
		return  orderConsignMapper.update(orderConsignEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderConsignMapper.remove(id);
	}
	
	@Override
	public OrderConsignEntity getById(String id){
		return orderConsignMapper.getById(id);
	} 
}