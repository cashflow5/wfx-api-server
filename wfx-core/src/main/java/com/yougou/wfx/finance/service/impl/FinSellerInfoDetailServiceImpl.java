 /*
 * 版本信息
 
 * 日期 2016-03-29 14:08:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.CalculateUtils;
import com.yougou.wfx.finance.dao.FinSellerInfoDetailMapper;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.service.IFinSellerInfoDetailService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IFinSellerInfoDetailService实现
 * @author wfx
 * @Date 创建时间：2016-03-29 14:08:45
 */
@Service
public class FinSellerInfoDetailServiceImpl extends BaseService implements IFinSellerInfoDetailService {
	
	@Resource
	private FinSellerInfoDetailMapper finSellerInfoDetailMapper;

	@Override
	public int findPageCount(FinSellerInfoDetailEntity finSellerInfoDetailEntity){
		return finSellerInfoDetailMapper.findPageCount(finSellerInfoDetailEntity);
	}

	@Override
	public List<FinSellerInfoDetailEntity> findPage(FinSellerInfoDetailEntity finSellerInfoDetailEntity,RowBounds rowBounds){
		return finSellerInfoDetailMapper.findPage(finSellerInfoDetailEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(FinSellerInfoDetailEntity finSellerInfoDetailEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		finSellerInfoDetailEntity.setId(strId);
		finSellerInfoDetailMapper.insert(finSellerInfoDetailEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(FinSellerInfoDetailEntity finSellerInfoDetailEntity){
		return  finSellerInfoDetailMapper.update(finSellerInfoDetailEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return finSellerInfoDetailMapper.remove(id);
	}
	
	@Override
	public FinSellerInfoDetailEntity getById(String id){
		return finSellerInfoDetailMapper.getById(id);
	}

	@Override
	public List<FinSellerInfoDetailEntity> querySellerDetailList(FinSellerInfoDetailEntity finSellerInfoDetailEntity) {
		return this.finSellerInfoDetailMapper.querySellerDetailList(finSellerInfoDetailEntity);
	}

	@Override
	public int querySellerDetailListCount(FinSellerInfoDetailEntity finSellerInfoDetailEntity) {
		return this.finSellerInfoDetailMapper.querySellerDetailListCount(finSellerInfoDetailEntity);
	}

	@Override
	public Double getCommissionTotalAmountById(String sellerId, String sellerNextId) {
		Double totalAmount = 0.0;
		FinSellerInfoDetailEntity finSellerInfoDetailEntity = new FinSellerInfoDetailEntity();
		finSellerInfoDetailEntity.setSellerId(sellerId);
		finSellerInfoDetailEntity.setSellerNextId(sellerNextId);
		List<FinSellerInfoDetailEntity> detailEntityList = this.finSellerInfoDetailMapper.getCommissionTotalAmountById(finSellerInfoDetailEntity);
		if (detailEntityList != null && detailEntityList.size() > 0) {
			for (FinSellerInfoDetailEntity entity : detailEntityList) {
				if (entity.getIncomeAmount() == null) {
					entity.setIncomeAmount(new Double(0));
				}
				if (entity.getExpendAmount() == null) {
					entity.setExpendAmount(new Double(0));
				}
				if (StringUtils.equals("1", entity.getTransactionFlag())) { //收入
					totalAmount = CalculateUtils.add(totalAmount, entity.getIncomeAmount());
				} else if (StringUtils.equals("2", entity.getTransactionFlag())) { // 支出
					totalAmount = CalculateUtils.add(totalAmount, entity.getExpendAmount());
				}
			}
		}
		return totalAmount;
	} 
	
}