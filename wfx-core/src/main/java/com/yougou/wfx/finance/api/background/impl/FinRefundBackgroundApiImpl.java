 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.wfx.finance.api.background.IFinRefundBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinReturnDebtInputDto;
import com.yougou.wfx.finance.dto.output.FinRefundSynRes;
import com.yougou.wfx.finance.dto.output.FinReturnDebtOutputDto;
import com.yougou.wfx.finance.model.FinReturnDebtEntity;
import com.yougou.wfx.finance.service.IFinReturnDebtService;
import com.yougou.wfx.framework.bean.BeanUtil;

/**
 * IFinRefundBackgroundApi实现
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
@Service
public class FinRefundBackgroundApiImpl implements IFinRefundBackgroundApi{
	private static final Logger LOG = LoggerFactory.getLogger(FinRefundBackgroundApiImpl.class);
	
	@Resource
	private IFinReturnDebtService finReturnDebtService;
	
	private FinReturnDebtEntity dtoToEntity(Object obj){
		return (FinReturnDebtEntity) BeanUtil.convertBean(obj,FinReturnDebtEntity.class);
	}
	
	private FinReturnDebtOutputDto entityToDto(Object obj){
		return (FinReturnDebtOutputDto) BeanUtil.convertBean(obj,FinReturnDebtOutputDto.class);
	}

	/**
	 * 售后同意退款，财务创建或修改退款单
	 */
	@Override
	public FinRefundSynRes applyAndModifyRefund(FinReturnDebtInputDto finReturnDebt) {
		LOG.info("【售后同意退款，财务创建或修改退款单Dubbo】，接受数据：" + finReturnDebt + "，退款单号["+finReturnDebt.getBackNo()+"]");
		return this.finReturnDebtService.applyAndModifyRefund(this.dtoToEntity(finReturnDebt));
	}

	/**
	 * 通过退款单号查询退款信息
	 */
	@Override
	public FinReturnDebtOutputDto getRefundInfo(String backNo) {
		LOG.info("【财务查询退款单信息Dubbo】，退款单号：" + backNo);
		FinReturnDebtEntity refundEntity = this.finReturnDebtService.getReturnDebtByBackNo(backNo);
		return this.entityToDto(refundEntity);
	}
	
}
