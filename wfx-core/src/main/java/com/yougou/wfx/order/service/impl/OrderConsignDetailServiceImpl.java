 /*
 * 版本信息
 
 * 日期 2016-04-18 14:41:51
 
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
import com.yougou.wfx.order.dao.OrderConsignDetailMapper;
import com.yougou.wfx.order.model.OrderConsignDetailEntity;
import com.yougou.wfx.order.service.IOrderConsignDetailService;

/**
 * IOrderConsignDetailService实现
 * @author wzf
 * @Date 创建时间：2016-04-18 14:41:52
 */
@Service
public class OrderConsignDetailServiceImpl extends BaseService implements IOrderConsignDetailService {
	
	@Resource
	private OrderConsignDetailMapper orderConsignDetailMapper;

	@Override
	public int findPageCount(OrderConsignDetailEntity orderConsignDetailEntity){
		return orderConsignDetailMapper.findPageCount(orderConsignDetailEntity);
	}

	@Override
	public List<OrderConsignDetailEntity> findPage(OrderConsignDetailEntity orderConsignDetailEntity,RowBounds rowBounds){
		return orderConsignDetailMapper.findPage(orderConsignDetailEntity,rowBounds);
	}

	@Override
	public List<OrderConsignDetailEntity> queryList(OrderConsignDetailEntity orderConsignDetailEntity){
		return orderConsignDetailMapper.queryList(orderConsignDetailEntity);
	}
	
	@Override
	@Transactional
	public String insert(OrderConsignDetailEntity orderConsignDetailEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		orderConsignDetailEntity.setId(strId);
		orderConsignDetailMapper.insert(orderConsignDetailEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderConsignDetailEntity orderConsignDetailEntity){
		return  orderConsignDetailMapper.update(orderConsignDetailEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderConsignDetailMapper.remove(id);
	}
	
	@Override
	public OrderConsignDetailEntity getById(String id){
		return orderConsignDetailMapper.getById(id);
	}

	@Override
	public int queryConsignStatusByWfxOrderNo(String wfxOrderNo) {
		// TODO Auto-generated method stub
		return orderConsignDetailMapper.queryConsignStatusByWfxOrderNo(wfxOrderNo);
	} 
}