package com.yougou.wfx.finance.mq;

import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yougou.tools.common.utils.CalculateUtils;
import com.yougou.wfx.finance.dto.input.FinAlreadyIncomeInputDto;
import com.yougou.wfx.finance.model.FinAlreadyIncomeEntity;
import com.yougou.wfx.finance.service.IFinAlreadyIncomeService;
import com.yougou.wfx.framework.bean.BeanUtil;

/**
 * 财务收款MQ（订单支付成功后，需回调财务接口，异步生成收款信息）
 * @author he.xx
 * @Date 2016-04-18
 */
@Component
public class AlreadyIncomeNormalMQHandler {
	private static Logger LOG = LoggerFactory.getLogger(AlreadyIncomeNormalMQHandler.class);
	
	@Resource
	private IFinAlreadyIncomeService finAlreadyIncomeService;
	
	private FinAlreadyIncomeEntity dtoToEntity(Object obj){
		return (FinAlreadyIncomeEntity) BeanUtil.convertBean(obj,FinAlreadyIncomeEntity.class);
	}
	
	public void handleMessage(FinAlreadyIncomeInputDto alreadyIncome) {
		LOG.info("【财务收款消息MQ】，从队列中接受数据：" + alreadyIncome + "，收款订单号["+ alreadyIncome.getOrderNo() +"]");
		try {
			Assert.hasText(alreadyIncome.getOrderNo(), "订单号不能为空，订单来源："+alreadyIncome.getStoreName());
			Assert.hasText(alreadyIncome.getBankTradeNo(), "支付交易号流水号不能为空，订单号："+alreadyIncome.getOrderNo());
			
			FinAlreadyIncomeEntity queryVo = new FinAlreadyIncomeEntity();
			queryVo.setBankTradeNo(alreadyIncome.getBankTradeNo());
			queryVo.setOutTradeNo(alreadyIncome.getOutTradeNo());
			
			FinAlreadyIncomeEntity finAlreadyIncomeEntity = this.finAlreadyIncomeService.queryAlreadyIncome(queryVo);
			if (finAlreadyIncomeEntity == null) {
				if (alreadyIncome.getIncomeType() != null && alreadyIncome.getIncomeType() == 1) {
					alreadyIncome.setIncomeTypeDesc("正常收款");
				}
				if (CalculateUtils.compareTo(alreadyIncome.getTradeAmount(), alreadyIncome.getIncomedAmount()) != 0) {
					alreadyIncome.setIncomeType(3);
					if (CalculateUtils.gt(alreadyIncome.getTradeAmount(), alreadyIncome.getIncomedAmount())) {
						alreadyIncome.setIncomeTypeDesc("多收顾客"+String.valueOf(CalculateUtils.sub(alreadyIncome.getTradeAmount(), alreadyIncome.getIncomedAmount())));
					} else if (CalculateUtils.lt(alreadyIncome.getTradeAmount(), alreadyIncome.getIncomedAmount())) {
						alreadyIncome.setIncomeTypeDesc("少收顾客"+String.valueOf(CalculateUtils.sub(alreadyIncome.getTradeAmount(), alreadyIncome.getIncomedAmount())));
					}
				}
				alreadyIncome.setCreateDate(new Date());
				alreadyIncome.setUpdateTime(alreadyIncome.getCreateDate());
				this.finAlreadyIncomeService.insert(this.dtoToEntity(alreadyIncome));
			} else {
				if (finAlreadyIncomeEntity.getIncomeType() == 1) {
					LOG.error("支付交易号流水号和支付号不能重复，交易流水号：" + alreadyIncome.getBankTradeNo() + "，支付号："+alreadyIncome.getOutTradeNo() + "，订单号为：" + alreadyIncome.getOrderNo());
					throw new Exception("支付交易号流水号和支付号不能重复，交易流水号：" + alreadyIncome.getBankTradeNo() + "，支付号：" + alreadyIncome.getOutTradeNo() + "，订单号为：" + alreadyIncome.getOrderNo());
				} else if (finAlreadyIncomeEntity.getIncomeType() == 3 && alreadyIncome.getIncomeType() == 1) {
					FinAlreadyIncomeEntity entity = new FinAlreadyIncomeEntity();
					entity.setIncomeType(alreadyIncome.getIncomeType());
					entity.setIncomeTypeDesc(finAlreadyIncomeEntity.getIncomeTypeDesc()+"#"+"正常收款");
					entity.setId(finAlreadyIncomeEntity.getId());
					entity.setUpdateTime(new Date());
					this.finAlreadyIncomeService.update(finAlreadyIncomeEntity);
				}
			}
		} catch (Exception e) {
			LOG.error("【财务收款消息MQ】，处理数据异常，订单号："+alreadyIncome.getOrderNo(), e);
		}
	}
	
}
