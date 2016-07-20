 /*
 * 版本信息
 
 * 日期 2016-05-31 15:04:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.order.api.front.IOrderRemarkFrontApi;
import com.yougou.wfx.order.dto.input.OrderRemarkInputDto;
import com.yougou.wfx.order.dto.input.OrderRemarkPageInputDto;
import com.yougou.wfx.order.dto.output.OrderRemarkOutputDto;
import com.yougou.wfx.order.model.OrderRemarkEntity;
import com.yougou.wfx.order.service.IOrderRemarkService;

/**
 * IOrderRemarkFrontApi实现
 * @author luoq
 * @Date 创建时间：2016-05-31 15:04:40
 */
@Service
public class OrderRemarkFrontApiImpl implements IOrderRemarkFrontApi{
	
	@Resource
	IOrderRemarkService orderRemarkService;
	
	@Override
	public int removeById(String id) {
		return orderRemarkService.remove(id);
	}

	@Override
	public String insert(OrderRemarkInputDto orderRemarkDto) {
		return orderRemarkService.insert(this.dtoToEntity(orderRemarkDto));
	}

	@Override
	public PageModel<OrderRemarkOutputDto> findPage(OrderRemarkPageInputDto pageInputDto,PageModel<OrderRemarkOutputDto> pageModel) {
		OrderRemarkEntity orderRemarkEntity = (OrderRemarkEntity) BeanUtil.convertBean(pageInputDto,OrderRemarkEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = orderRemarkService.findPageCount(orderRemarkEntity);
		List<OrderRemarkEntity> lstOrderRemarks = orderRemarkService.findPage(orderRemarkEntity, rowBounds);

		return new PageModel<OrderRemarkOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<OrderRemarkOutputDto>) BeanUtil.convertBeanList(lstOrderRemarks,OrderRemarkOutputDto.class));
	}

	@Override
	public int update(OrderRemarkInputDto orderRemarkDto) {
		return orderRemarkService.update(this.dtoToEntity(orderRemarkDto));
	}

	@Override
	public OrderRemarkOutputDto getById(String id) {
		OrderRemarkEntity orderRemarkEntity = orderRemarkService.getById(id);
		return this.entityToDto(orderRemarkEntity);
	}
	
	private OrderRemarkEntity dtoToEntity(Object obj){
		return (OrderRemarkEntity) BeanUtil.convertBean(obj,OrderRemarkEntity.class);
	}
	
	private OrderRemarkOutputDto entityToDto(Object obj){
		return (OrderRemarkOutputDto) BeanUtil.convertBean(obj,OrderRemarkOutputDto.class);
	}
}
