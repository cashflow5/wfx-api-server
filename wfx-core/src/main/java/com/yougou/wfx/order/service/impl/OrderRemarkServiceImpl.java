 /*
 * 版本信息
 
 * 日期 2016-05-31 15:04:39
 
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
import com.yougou.wfx.order.dao.OrderRemarkMapper;
import com.yougou.wfx.order.model.OrderRemarkEntity;
import com.yougou.wfx.order.service.IOrderRemarkService;

/**
 * IOrderRemarkService实现
 * @author luoq
 * @Date 创建时间：2016-05-31 15:04:40
 */
@Service
public class OrderRemarkServiceImpl extends BaseService implements IOrderRemarkService {
	
	@Resource
	private OrderRemarkMapper orderRemarkMapper;

	@Override
	public int findPageCount(OrderRemarkEntity orderRemarkEntity){
		return orderRemarkMapper.findPageCount(orderRemarkEntity);
	}

	@Override
	public List<OrderRemarkEntity> findPage(OrderRemarkEntity orderRemarkEntity,RowBounds rowBounds){
		return orderRemarkMapper.findPage(orderRemarkEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(OrderRemarkEntity orderRemarkEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		orderRemarkEntity.setId(strId);
		orderRemarkMapper.insert(orderRemarkEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderRemarkEntity orderRemarkEntity){
		return  orderRemarkMapper.update(orderRemarkEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderRemarkMapper.remove(id);
	}
	
	@Override
	public OrderRemarkEntity getById(String id){
		return orderRemarkMapper.getById(id);
	} 
}