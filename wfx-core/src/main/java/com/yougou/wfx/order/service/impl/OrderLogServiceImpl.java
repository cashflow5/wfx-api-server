 /*
 * 版本信息
 
 * 日期 2016-04-07 10:33:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.enums.OrderLogBelongTypeEnum;
import com.yougou.wfx.enums.OrderLogOptEnum;
import com.yougou.wfx.enums.OrderLogTypeEnum;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.order.dao.OrderLogMapper;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderLogService;

/**
 * IOrderLogService实现
 * @author wfx
 * @Date 创建时间：2016-04-07 10:33:53
 */
@Service
public class OrderLogServiceImpl extends BaseService implements IOrderLogService {
	
	@Resource
	private OrderLogMapper orderLogMapper;

	@Override
	public int findPageCount(OrderLogEntity orderLogEntity){
		return orderLogMapper.findPageCount(orderLogEntity);
	}

	@Override
	public List<OrderLogEntity> findPage(OrderLogEntity orderLogEntity,RowBounds rowBounds){
		return orderLogMapper.findPage(orderLogEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(OrderLogEntity orderLogEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		orderLogEntity.setId(strId);
		orderLogMapper.insert(orderLogEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderLogEntity orderLogEntity){
		return  orderLogMapper.update(orderLogEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderLogMapper.remove(id);
	}
	
	@Override
	public OrderLogEntity getById(String id){
		return orderLogMapper.getById(id);
	}

	@Override
	public List<OrderLogEntity> queryList(OrderLogEntity orderLogEntity) {
		// TODO Auto-generated method stub
	    return orderLogMapper.queryList(orderLogEntity);
	}
	
	public void insertOrderLog(String orderNo,String optUser,Integer optType,Integer optBlone,Integer optResult){
		try{
			OrderLogEntity orderLogEntity = new OrderLogEntity();
			String strId = UUIDGenerator.get32LowCaseUUID();
			if(null == optUser){
				optUser = "";
			}
			orderLogEntity.setId(strId);
			orderLogEntity.setOrderNo(orderNo);
			orderLogEntity.setOptUser(optUser);
			orderLogEntity.setOptType(optType);
			orderLogEntity.setOptBelong(optBlone);
			String logInfo = "";
			if(optType == OrderLogOptEnum.OPT_CREATE_ORDER.getKey()){
				logInfo = "生成订单成功";
			}else if(optType == OrderLogOptEnum.OPT_CANCEL_ORDER.getKey()){
				if("system".equals(optUser)){
					logInfo = "订单超时未支付取消订单";
				}else{
					logInfo = "取消订单";
				}
			}else if(optType == OrderLogOptEnum.OPT_RECEIVED_ORDER.getKey()){
				if("system".equals(optUser)){
					logInfo = "超时系统自动确认收货";
				}else{
					logInfo = "订单已确认收货";
				}
			}else if(optType == OrderLogOptEnum.OPT_DELIVERED.getKey()){
				if("system".equals(optUser)){
					logInfo = "退款完成后发现该主单其他子订单全部发货，系统自动设置主订单状态为已发货";
				}else{
					logInfo = "订单发货";
				}
			}else if(optType == OrderLogOptEnum.OPT_CLOSE_ORDER.getKey()){
				if("system".equals(optUser)){
					logInfo = "退款完成后发现该主单其他子订单全部退款，系统自动设置主订单状态为关闭";
				}else{
					logInfo = "订单关闭";
				}
			}
			
			//
			orderLogEntity.setLogInfo(logInfo);
			orderLogEntity.setType(OrderLogTypeEnum.ORDER_TURN_LOG.getKey());
			orderLogEntity.setLogType(OrderLogBelongTypeEnum.ORDER_LOG.getKey());
			orderLogEntity.setOptResult(optResult);
			orderLogEntity.setShowType(3);
			Date date = new Date();
			orderLogEntity.setCreateTime(date);
			orderLogEntity.setUpdateTime(date);
			orderLogMapper.insert(orderLogEntity);
		}catch(Exception e){
			logger.error("保存订单日志失败，订单号：" + orderNo + "，类型：" + optType);
		}
	}
}