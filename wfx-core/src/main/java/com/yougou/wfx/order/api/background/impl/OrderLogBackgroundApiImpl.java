 /*
 * 版本信息
 
 * 日期 2016-04-07 10:33:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.order.api.background.IOrderLogBackgroundApi;
import com.yougou.wfx.order.dto.input.OrderLogInputDto;
import com.yougou.wfx.order.dto.input.OrderLogPageInputDto;
import com.yougou.wfx.order.dto.output.OrderLogOutputDto;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderLogService;

/**
 * IOrderLogBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-07 10:33:53
 */
@Service
public class OrderLogBackgroundApiImpl implements IOrderLogBackgroundApi{
	
	@Resource
	IOrderLogService orderLogService;
	
	@Override
	public int removeById(String id) {
		return orderLogService.remove(id);
	}
	
	@LoggerProfile(methodNote = "后台新增订单/退款单 日志接口")
	@Override
	public String insert(OrderLogInputDto orderLogDto) {
		return orderLogService.insert(this.dtoToEntity(orderLogDto));
	}
	
	@LoggerProfile(methodNote = "后台列表查询订单/退款单 日志接口")
	@Override
	public PageModel<OrderLogOutputDto> findPage(OrderLogPageInputDto pageInputDto,PageModel pageModel) {
		OrderLogEntity orderLogEntity = (OrderLogEntity) BeanUtil.convertBean(pageInputDto,OrderLogEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = orderLogService.findPageCount(orderLogEntity);
		List<OrderLogEntity> lstOrderLogs = orderLogService.findPage(orderLogEntity, rowBounds);

		return new PageModel<OrderLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<OrderLogOutputDto>) BeanUtil.convertBeanList(lstOrderLogs,OrderLogOutputDto.class));
	}
	
	@LoggerProfile(methodNote = "后台修改订单/退款单 日志接口")
	@Override
	public int update(OrderLogInputDto orderLogDto) {
		return orderLogService.update(this.dtoToEntity(orderLogDto));
	}
	
	@LoggerProfile(methodNote = "后台根据ID获取 订单/退款单 日志接口")
	@Override
	public OrderLogOutputDto getById(String id) {
		OrderLogEntity orderLogEntity = orderLogService.getById(id);
		return this.entityToDto(orderLogEntity);
	}
	
	private OrderLogEntity dtoToEntity(Object obj){
		return (OrderLogEntity) BeanUtil.convertBean(obj,OrderLogEntity.class);
	}
	
	private OrderLogOutputDto entityToDto(Object obj){
		return (OrderLogOutputDto) BeanUtil.convertBean(obj,OrderLogOutputDto.class);
	}
	
	@LoggerProfile(methodNote = "后台列表查询（不分页）订单/退款单 日志接口")
	@Override
	public List<OrderLogOutputDto> queryList(OrderLogPageInputDto pageInputDto) {
		// TODO Auto-generated method stub
		OrderLogEntity orderLogEntity = (OrderLogEntity) BeanUtil.convertBean(pageInputDto,OrderLogEntity.class);
		List<OrderLogEntity> lstOrderLogs =  orderLogService.queryList(orderLogEntity);
		return (List<OrderLogOutputDto>) BeanUtil.convertBeanList(lstOrderLogs,OrderLogOutputDto.class);
	}
}
