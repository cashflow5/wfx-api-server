 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.biz.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.order.biz.IOrderBiz;
import com.yougou.wfx.order.dto.output.BatchOrderResultDto;
import com.yougou.wfx.order.dto.output.OrderCreateDto;
import com.yougou.wfx.order.dto.output.OrderDetailCreateDto;
import com.yougou.wfx.order.dto.output.OrderResultDto;
import com.yougou.wfx.order.service.IOrderService;

/**
 * IOrderService实现
 * @author wzf
 * @Date 创建时间：2016-03-25 10:43:03
 */
@Service
public class OrderBizImpl extends BaseService implements IOrderBiz {
	
	Logger logger = LoggerFactory.getLogger( OrderBizImpl.class );
	
	@Resource
	IOrderService orderService;

	@Override
	@Transactional
	public void batchCreateOrder(List<OrderCreateDto> orderCreateList,BatchOrderResultDto result) {
		try {
			if(null == orderCreateList || orderCreateList.size() <= 0){
				result.setCode(5);
				result.setMessage("传入的参数为空");
				throw new RuntimeException("传入的子订单参数为空");
			}
			List<OrderResultDto> orderResultList = new ArrayList<OrderResultDto>();
			for(OrderCreateDto orderCreate:orderCreateList){
				//检验参数是否齐全
				List<OrderDetailCreateDto> commDtoList = orderCreate.getOrderDetailCreateDto();
				if(null == commDtoList || commDtoList.size() <= 0){
					result.setCode(5);
					result.setMessage("传入的子订单参数为空");
					throw new RuntimeException("传入的子订单参数为空");
				}
				if(StringUtils.isBlank(orderCreate.getSellerId())||
						StringUtils.isBlank(orderCreate.getBuyerId())||
						StringUtils.isBlank(orderCreate.getReceiverName())||
						StringUtils.isBlank(orderCreate.getReceiverMobile())||
						StringUtils.isBlank(orderCreate.getReceiverState())||
						StringUtils.isBlank(orderCreate.getReceiverCity())||
						StringUtils.isBlank(orderCreate.getReceiverDistrict())||
						StringUtils.isBlank(orderCreate.getReceiverAddress()))
				{
					result.setCode(5);
					result.setMessage("订单参数不全");
					result.setShopId(orderCreate.getShopId());
					result.setShopName(orderCreate.getShopName());
					throw new RuntimeException("传入的子订单参数为空");
				}
				
				OrderResultDto orderResult = orderService.createOrder(orderCreate);
				Integer code = orderResult.getCode();
				if(null != code && 1 == code){
					orderResultList.add(orderResult);
				}else{
					result.setCode(orderResult.getCode());
					result.setMessage(orderResult.getMessage());
					result.setShopId(orderResult.getShopId());
					result.setShopName(orderResult.getShopName());
					result.setProdId(orderResult.getProdId());
					result.setProdName(orderResult.getProdName());
					throw new RuntimeException("批量创建订单出现异常");
				}
			}
			result.setCode(1);
			result.setMessage("创建订单成功");
			result.setOrderReslutList(orderResultList);
		} catch (Exception e) {
			result.setCode(6);
			result.setMessage("创建订单出现异常");
			logger.error("批量创建订单出现异常",e);
			throw new RuntimeException("批量创建订单出现异常");
		}
	}
	
}