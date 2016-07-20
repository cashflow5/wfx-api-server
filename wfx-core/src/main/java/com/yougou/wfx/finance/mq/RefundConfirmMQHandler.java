package com.yougou.wfx.finance.mq;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yougou.pay.vo.RefundCallBackStateEnum;
import com.yougou.pay.vo.RefundCallBackVo;
import com.yougou.wfx.aftersale.api.background.IOrderRefundBackgroundApi;
import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.enums.OrderLogBelongTypeEnum;
import com.yougou.wfx.enums.OrderLogOptBelongEnum;
import com.yougou.wfx.enums.OrderLogOptEnum;
import com.yougou.wfx.enums.OrderLogOptResultEnum;
import com.yougou.wfx.enums.OrderLogTypeEnum;
import com.yougou.wfx.enums.RefundStatusEnum;
import com.yougou.wfx.finance.enums.FinRefundStatusEnum;
import com.yougou.wfx.finance.model.FinReturnDebtEntity;
import com.yougou.wfx.finance.service.IFinReturnDebtService;
import com.yougou.wfx.order.api.background.IOrderLogBackgroundApi;
import com.yougou.wfx.order.dto.input.OrderLogInputDto;

/**
 * 退款确认MQ（支付系统异步消息，返回最终退款成功信息）
 * @author he.xx
 * @Date 2016-04-18 
 */
@Component
public class RefundConfirmMQHandler {
	private static Logger LOG = LoggerFactory.getLogger(RefundConfirmMQHandler.class.getName());
	
	@Resource
	private IFinReturnDebtService finReturnDebtService;
	@Resource
	private IOrderRefundBackgroundApi orderRefundBackgroundApi; //售后退款API
	@Resource
	private IOrderLogBackgroundApi iOrderLogBackgroundApi; //订单日志API，售后和财务共用同一张日志表
	
	public void handleMessage(RefundCallBackVo refundVo) {
		LOG.info("【退款确认MQ消息回调】，从队列中接收消息：" + refundVo + "，退款确认退款单号[" + refundVo.getRefundApplyReqNo() + "]");
		RefundCallBackStateEnum statusEnum = refundVo.getRefundCallBackState();
		String backNo = refundVo.getRefundApplyReqNo();
		Date nowDate = new Date();
		
		FinReturnDebtEntity returnDebtEntity = null;
		try {
			returnDebtEntity = this.finReturnDebtService.getReturnDebtByBackNo(backNo);
			if (returnDebtEntity == null) {
				LOG.info("【退款确认MQ消息回调】，不存在此退款单，退款单号：" + backNo);
			} else if (FinRefundStatusEnum.退款成功.getCode() == returnDebtEntity.getRefundStatus()) {
				LOG.info("【退款确认MQ消息回调】，此单已退款，不能重复操作，退款单号：" + backNo);
			} else {
				if (RefundCallBackStateEnum.STATE_SUCCESS == statusEnum) {
					LOG.info("【退款确认MQ消息回调】，退款标识："+statusEnum.getDesc()+"，退款单号："+backNo);
					FinReturnDebtEntity entity = new FinReturnDebtEntity();
					entity.setRefundStatus(FinRefundStatusEnum.退款成功.getCode());
					entity.setApplyStatus(RefundStatusEnum.SUCCESS_REFUND.getKey());
					entity.setRefundDate(nowDate);
					String note = returnDebtEntity.getNote() == null ? "MQ退款成功" : (returnDebtEntity.getNote()+"#MQ"+FinRefundStatusEnum.退款成功.toString());
					entity.setNote(note);
					entity.setId(returnDebtEntity.getId());
					entity.setBackNo(returnDebtEntity.getBackNo());
					entity.setUpdateTime(nowDate);
					int num = this.finReturnDebtService.update(entity);
					if (num > 0) {
						StringBuffer strBuff = new StringBuffer();
						OrderLogInputDto logInputDto = new OrderLogInputDto();
						logInputDto.setOrderNo(returnDebtEntity.getOrderNo());
						logInputDto.setCreateTime(nowDate);
						logInputDto.setRejectedNo(returnDebtEntity.getBackNo());
						logInputDto.setOptUser(returnDebtEntity.getOperator());
						logInputDto.setOptBelong(OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey());
						logInputDto.setType(OrderLogTypeEnum.ORDER_OPT_LOG.getKey());
						logInputDto.setLogType(OrderLogBelongTypeEnum.REFUND_LOG.getKey());
						logInputDto.setShowType(2);
						logInputDto.setOptType(OrderLogOptEnum.OPT_REFUND_SUCCESS.getKey());
						strBuff.append(OrderLogOptEnum.OPT_REFUND_SUCCESS.getDesc());
						strBuff.append("，操作人【").append(returnDebtEntity.getOperator()).append("】");
						strBuff.append("#");
						strBuff.append("退款金额，").append(String.valueOf(returnDebtEntity.getRefundAmount())).append("元");
						logInputDto.setLogInfo(strBuff.toString());
						logInputDto.setOptResult(OrderLogOptResultEnum.OPT_SUCCESS.getKey());
						logInputDto.setUpdateTime(nowDate);
						this.iOrderLogBackgroundApi.insert(logInputDto);
						
						OrderRefundInputDto orderRefund = new OrderRefundInputDto();
						orderRefund.setRefundNo(returnDebtEntity.getBackNo());
						orderRefund.setStatus(RefundStatusEnum.SUCCESS_REFUND.getKey());
						orderRefund.setRefundType(returnDebtEntity.getRefundType());
						orderRefund.setPayTime(nowDate);
						orderRefund.setSupplierName(returnDebtEntity.getOperator());
						this.orderRefundBackgroundApi.updateStatusOfRefund(orderRefund);
					}
				} else {
					LOG.info("【退款确认MQ消息回调】，退款标识：" + statusEnum.getDesc() + "，退款单号：" + backNo);
					StringBuffer strBuff = new StringBuffer();
					OrderLogInputDto logInputDto = new OrderLogInputDto();
					logInputDto.setOrderNo(returnDebtEntity.getOrderNo());
					logInputDto.setCreateTime(nowDate);
					logInputDto.setRejectedNo(returnDebtEntity.getBackNo());
					logInputDto.setOptUser(returnDebtEntity.getOperator());
					logInputDto.setOptBelong(OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey());
					logInputDto.setType(OrderLogTypeEnum.ORDER_OPT_LOG.getKey());
					logInputDto.setLogType(OrderLogBelongTypeEnum.REFUND_LOG.getKey());
					logInputDto.setShowType(2);
					logInputDto.setOptType(OrderLogOptEnum.OPT_REFUND_FAILURE.getKey());
					strBuff.append(OrderLogOptEnum.OPT_REFUND_FAILURE.getDesc());
					strBuff.append("，操作人【").append(returnDebtEntity.getOperator()).append("】");
					strBuff.append("#");
					strBuff.append("退款金额，").append(String.valueOf(returnDebtEntity.getRefundAmount())).append("元");
					logInputDto.setLogInfo(strBuff.toString());
					logInputDto.setOptResult(OrderLogOptResultEnum.OPT_FAILED.getKey());
					logInputDto.setUpdateTime(nowDate);
					this.iOrderLogBackgroundApi.insert(logInputDto);
				}
			}
		} catch (Exception e) {
			LOG.error("【退款确认MQ消息回调】，处理出错...，退款单号：" + backNo, e);
		}
		
	}
	
}
