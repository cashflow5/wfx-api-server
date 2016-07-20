 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.finance.dao.FinAlreadyIncomeMapper;
import com.yougou.wfx.finance.dao.FinReturnDebtMapper;
import com.yougou.wfx.finance.dto.output.FinRefundSynRes;
import com.yougou.wfx.finance.enums.FinRefundStatusEnum;
import com.yougou.wfx.finance.enums.FinRefundSynStateEnum;
import com.yougou.wfx.finance.model.FinAlreadyIncomeEntity;
import com.yougou.wfx.finance.model.FinReturnDebtEntity;
import com.yougou.wfx.finance.service.IFinReturnDebtLogService;
import com.yougou.wfx.finance.service.IFinReturnDebtService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IFinReturnDebtService实现
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
@Service
public class FinReturnDebtServiceImpl extends BaseService implements IFinReturnDebtService {
	private static final Logger LOG = LoggerFactory.getLogger(FinReturnDebtServiceImpl.class);
	
	@Resource
	private FinReturnDebtMapper finReturnDebtMapper;
	
	@Resource
	private FinAlreadyIncomeMapper finAlreadyIncomeMapper;
	
	@Resource
	private IFinReturnDebtLogService finReturnDebtLogService;

	@Override
	public int findPageCount(FinReturnDebtEntity finReturnDebtEntity){
		return finReturnDebtMapper.findPageCount(finReturnDebtEntity);
	}

	@Override
	public List<FinReturnDebtEntity> findPage(FinReturnDebtEntity finReturnDebtEntity,RowBounds rowBounds){
		return finReturnDebtMapper.findPage(finReturnDebtEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(FinReturnDebtEntity finReturnDebtEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		finReturnDebtEntity.setId(strId);
		finReturnDebtMapper.insert(finReturnDebtEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(FinReturnDebtEntity finReturnDebtEntity){
		return  finReturnDebtMapper.update(finReturnDebtEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return finReturnDebtMapper.remove(id);
	}
	
	@Override
	public FinReturnDebtEntity getById(String id){
		return finReturnDebtMapper.getById(id);
	}

	@Override
	public List<FinReturnDebtEntity> queryDataList(FinReturnDebtEntity finReturnDebtEntity) {
		return this.finReturnDebtMapper.queryDataList(finReturnDebtEntity);
	}

	@Override
	public int queryDataListCount(FinReturnDebtEntity finReturnDebtEntity) {
		return this.finReturnDebtMapper.queryDataListCount(finReturnDebtEntity);
	}
	
	/**
	 * 通过退款单号查询退款信息
	 */
	@Override
	public FinReturnDebtEntity getReturnDebtByBackNo(String backNo) {
		return this.finReturnDebtMapper.getReturnDebtByBackNo(backNo);
	}

	/**
	 * 查询退款单详情
	 */
	@Override
	public FinReturnDebtEntity getReturnDebtByQueryVo(FinReturnDebtEntity finReturnDebtEntity) {
		return this.finReturnDebtMapper.getReturnDebtByQueryVo(finReturnDebtEntity);
	}

	/**
	 * 售后同意退款，财务创建或修改退款单
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public FinRefundSynRes applyAndModifyRefund(FinReturnDebtEntity finReturnDebtEntity) {
		FinRefundSynRes resultVo = new FinRefundSynRes();
		if (StringUtils.isBlank(finReturnDebtEntity.getBackNo())) {
			LOG.error("订单号为"+finReturnDebtEntity.getOrderNo()+"的退款申请单对应的退款单号backNo不能为空");
			resultVo.setStatusMsg("订单号为"+finReturnDebtEntity.getOrderNo()+"的退款申请单对应的退款单号backNo不能为空");
			resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_ERROR);
			return resultVo;
		}
		FinReturnDebtEntity returnDebtEntity = null;
		try {
			returnDebtEntity = this.finReturnDebtMapper.getReturnDebtByBackNo(finReturnDebtEntity.getBackNo());
			if (returnDebtEntity == null) {
				if (StringUtils.isBlank(finReturnDebtEntity.getOrderNo())) {
					LOG.error("订单号为空，不能产生退款记录，退款单号："+finReturnDebtEntity.getBackNo());
					resultVo.setStatusMsg("订单号为空，不能产生退款记录，退款单号："+finReturnDebtEntity.getBackNo());
					resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_ERROR);
					return resultVo;
				} else if (finReturnDebtEntity.getApplyAmount() == null) {
					LOG.error("申请退款金额为空，不能产生退款记录，退款单号："+finReturnDebtEntity.getBackNo());
					resultVo.setStatusMsg("申请退款金额为空，不能产生退款记录，退款单号："+finReturnDebtEntity.getBackNo());
					resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_ERROR);
					return resultVo;
				}
				String orderMainNo = finReturnDebtEntity.getOrderNo();
				if (StringUtils.indexOf(orderMainNo, "_") != -1) {
					orderMainNo = finReturnDebtEntity.getOrderNo().substring(0, StringUtils.indexOf(orderMainNo, "_"));
				}
				FinAlreadyIncomeEntity queryVo = new FinAlreadyIncomeEntity();
				queryVo.setOrderNo(orderMainNo);
				FinAlreadyIncomeEntity alreadyIncome = this.finAlreadyIncomeMapper.queryAlreadyIncome(queryVo);
				if (alreadyIncome == null) {
					resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_ERROR);
					resultVo.setStatusMsg("订单号["+finReturnDebtEntity.getOrderNo()+"]的订单未找到收款记录，不能退款");
					LOG.error("订单号["+finReturnDebtEntity.getOrderNo()+"]的订单未找到收款记录，不能退款");
					return resultVo;
				}
				LOG.info("新增退款申请单["+ finReturnDebtEntity.getBackNo() +"]：" + finReturnDebtEntity);
				finReturnDebtEntity.setCreateDate(new Date());
				finReturnDebtEntity.setBankNo(alreadyIncome.getBankNo());
				finReturnDebtEntity.setBankName(alreadyIncome.getBankName());
				finReturnDebtEntity.setBankTradeNo(alreadyIncome.getBankTradeNo());
				finReturnDebtEntity.setRefundStatus(FinRefundStatusEnum.待确认退款.getCode());
				finReturnDebtEntity.setRefundAmount(finReturnDebtEntity.getApplyAmount());
				finReturnDebtEntity.setUpdateTime(finReturnDebtEntity.getCreateDate());
				finReturnDebtEntity.setOperator("");//忽略售后传过来的操作人
				String returnId = this.insert(finReturnDebtEntity);
				if (StringUtils.isNotBlank(returnId)) {
					resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_SUCCESS);
					resultVo.setStatusMsg("创建退款单成功");
				}
			} else {
				LOG.info("修改退款申请单：" + finReturnDebtEntity);
//				int num = this.finReturnDebtMapper.updateDataByBackNo(finReturnDebtEntity);
//				if (num > 0) {
//					resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_SUCCESS);
//					resultVo.setStatusMsg("修改退款单成功");
//				}
			}
			return resultVo;
		} catch (Exception e) {
			LOG.error("财务创建或修改退款单异常...", e);
			resultVo.setRefundSynState(FinRefundSynStateEnum.STATE_FAIL);
			resultVo.setStatusMsg("新增或修改退款申请单失败");
			return resultVo;
		}
	}

	/*
	 * 通过订单号查询已退款的金额
	 * @see com.yougou.wfx.finance.service.IFinReturnDebtService#queryRefundedAmountByOrderNo(java.lang.String)
	 */
	@Override
	public Double queryRefundedAmountByOrderNo(String orderNo) {
		Double refundedAmount = this.finReturnDebtMapper.queryRefundedAmountByOrderNo(orderNo);
		return refundedAmount == null ? new Double(0) : refundedAmount;
	} 
	
}