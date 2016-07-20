package com.yougou.wfx.outside.api;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.StringUtil;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.outside.dao.OrderForOutSideMapper;
import com.yougou.wfx.outside.domain.Order;
import com.yougou.wfx.outside.request.OrderGetRequest;
import com.yougou.wfx.outside.request.OrderSearchRequest;
import com.yougou.wfx.outside.request.OrderUpdateRequest;
import com.yougou.wfx.outside.response.OrderGetResponse;
import com.yougou.wfx.outside.response.OrderSearchResponse;
import com.yougou.wfx.outside.response.OrderUpdateResponse;

/**
 * <p>Title: OrderForOutSideApiImpl</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
@Service
public class OrderForOutSideApiImpl extends BaseService implements IOrderForOutSideApi{
	@Resource
	private OrderForOutSideMapper orderForOutSideMapper;
	@Resource
	private IOrderService orderService;
	
	@Override
	@LoggerProfile(methodNote="查询订单")
	public OrderSearchResponse queryOrderList(OrderSearchRequest request) throws Exception {
		// TODO Auto-generated method stub
		OrderSearchResponse response = new OrderSearchResponse();
		try{
			logger.info("外部平台下载订单输入参数："+request.toString());
			if(request.getPageNo()>0 && request.getPageSize()>0){
				int total = orderForOutSideMapper.queryOrderCount(request);
				RowBounds rowBounds = new RowBounds((request.getPageNo()-1)*request.getPageSize(), request.getPageSize());
				List<Order> orderList = orderForOutSideMapper.queryOrderList(request, rowBounds);
				response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				response.setResultMsg("执行成功");
				response.setOrderList(orderList);
				response.setTotalResults(total);
			}else{
				logger.error("查询订单:分页条件不符合逻辑！PageNo="+request.getPageNo()+",PageSize="+request.getPageSize());
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("分页条件无法获取数据");
				response.setOrderList(null);
				response.setTotalResults(0);
			}
		}catch(Exception e){
			logger.error("外部平台下载订单，程序执行发生错误:"+e.getMessage());
			throw new Exception("程序执行发生错误:",e);
		}
		
		return response;
	}

	@Override
	@LoggerProfile(methodNote="获取订单")
	public OrderGetResponse getOrder(OrderGetRequest request) throws Exception {
		// TODO Auto-generated method stub
		OrderGetResponse response = new OrderGetResponse();
		try{
			logger.info("外部平台获取订单输入参数："+request.toString());
			if(StringUtil.isStrEmpty(request.getWfxOrderNo())){
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("微分销订单号为空！");
				response.setOrder(null);
			}else{
				List<Order> orderList = orderForOutSideMapper.getOrder(request);
				if(orderList.size()>0 && orderList.size()<2){
					response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
					response.setResultMsg("执行成功");
					response.setOrder(orderList.get(0));
				}else if(orderList.size()>1){
					response.setResultCode(ResultCodeEnum.WARN.getKey());
					response.setResultMsg("该微分销订单号存在多个订单");
					response.setOrder(null);
				}else{
					response.setResultCode(ResultCodeEnum.WARN.getKey());
					response.setResultMsg("不存在该微分销订单号");
					response.setOrder(null);
				}
			}
		}catch(Exception e){
			logger.error("获取订单【"+request.getWfxOrderNo()+"】,程序执行发生错误:"+e.getMessage());
			throw new Exception("程序执行发生错误:",e);
		}
		
		return response;
	}
	

	@Override
	@Transactional
	@LoggerProfile(methodNote="修改订单状态")
	public OrderUpdateResponse updateOrder(OrderUpdateRequest request) throws Exception {
		// TODO Auto-generated method stub
		OrderUpdateResponse response = new OrderUpdateResponse();
		try{
			logger.info("外部平台修改订单状态输入参数："+request.toString());
			if(StringUtil.isStrEmpty(request.getWfxOrderNo())){
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("未指定微分销订单号");
				response.setResult(false);
				return response;
			}
			Date current = new Date();
			int c = orderForOutSideMapper.updateOrder(request, current);
			if(c>0){
				response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				response.setResultMsg("执行成功");
				response.setResult(true);
			}else{
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("修改失败:可能订单不存在");
				response.setResult(false);
			}
		}catch(Exception e){
			logger.error("修改订单【"+request.getWfxOrderNo()+"】状态【"+request.getStatus()+"】,程序执行发生错误:"+e.getMessage());
			throw new RuntimeException("程序执行发生错误:", e);
		}
		return response;
	}

	
	
}
