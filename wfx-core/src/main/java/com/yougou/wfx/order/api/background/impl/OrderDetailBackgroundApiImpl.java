 /*
 * 版本信息
 
 * 日期 2016-04-15 11:56:30
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.order.api.background.IOrderDetailBackgroundApi;
import com.yougou.wfx.order.dto.input.OrderDetailInputDto;
import com.yougou.wfx.order.dto.input.OrderDetailPageInputDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;
import com.yougou.wfx.order.dto.output.OrderDetailOutputDto;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.service.IOrderDetailService;

/**
 * IOrderDetailBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-15 11:56:31
 */
@Service
public class OrderDetailBackgroundApiImpl implements IOrderDetailBackgroundApi{
	
	@Resource
	IOrderDetailService orderDetailService;
	
	@Override
	public int removeById(String id) {
		return orderDetailService.remove(id);
	}

	@Override
	public String insert(OrderDetailInputDto orderDetailDto) {
		return orderDetailService.insert(this.dtoToEntity(orderDetailDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询子订单")
	public PageModel<OrderDetailOutputDto> findPage(OrderDetailPageInputDto pageInputDto,PageModel pageModel) {
		OrderDetailEntity orderDetailEntity = (OrderDetailEntity) BeanUtil.convertBean(pageInputDto,OrderDetailEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = orderDetailService.findPageCount(orderDetailEntity);
		List<OrderDetailEntity> lstOrderDetails = orderDetailService.findPage(orderDetailEntity, rowBounds);

		return new PageModel<OrderDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<OrderDetailOutputDto>) BeanUtil.convertBeanList(lstOrderDetails,OrderDetailOutputDto.class));
	}

	@Override
	public int update(OrderDetailInputDto orderDetailDto) {
		return orderDetailService.update(this.dtoToEntity(orderDetailDto));
	}

	@Override
	public OrderDetailOutputDto getById(String id) {
		OrderDetailEntity orderDetailEntity = orderDetailService.getById(id);
		return this.entityToDto(orderDetailEntity);
	}
	
	private OrderDetailEntity dtoToEntity(Object obj){
		return (OrderDetailEntity) BeanUtil.convertBean(obj,OrderDetailEntity.class);
	}
	
	private OrderDetailOutputDto entityToDto(Object obj){
		return (OrderDetailOutputDto) BeanUtil.convertBean(obj,OrderDetailOutputDto.class);
	}

	@Override
	public int queryListCount(OrderDetailPageInputDto pageInputDto) {
		return orderDetailService.queryListCount(dtoToEntity(pageInputDto));
	}

	@Override
	@LoggerProfile(methodNote="查询所有满足条件的子订单")
	public List<OrderDetailOutputDto> queryList(OrderDetailInputDto pageInputDto) {
		List<OrderDetailEntity> lstOrderDetails = orderDetailService.queryList(this.dtoToEntity(pageInputDto));
		List<OrderDetailOutputDto> outPutList = (List<OrderDetailOutputDto>)BeanUtil.convertBeanList(lstOrderDetails, OrderDetailOutputDto.class);
		return outPutList;
	}

	@Override
	@LoggerProfile(methodNote="查询子订单佣金")
	public OrderDetailCommissionOutPutDto getOrderDetailCommission(String commissionDetailId) {
		if(StringUtils.isBlank(commissionDetailId)){
			return null;
		}
		return orderDetailService.getCommByCommId(commissionDetailId);
	}
}
