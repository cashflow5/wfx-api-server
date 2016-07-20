 /*
 * 版本信息
 
 * 日期 2016-03-28 10:58:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.CalculateUtils;
import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wfx.finance.dao.FinSellerInfoDetailMapper;
import com.yougou.wfx.finance.dao.FinSellerInfoMapper;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.enums.FinSellerAccountTranStatusEnum;
import com.yougou.wfx.finance.enums.FinSellerAccountTranTypeEnum;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.model.FinSellerInfoEntity;
import com.yougou.wfx.finance.service.IFinSellerInfoService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IFinSellerInfoService实现
 * @author wfx
 * @Date 创建时间：2016-03-28 10:58:34
 */
@Service
public class FinSellerInfoServiceImpl extends BaseService implements IFinSellerInfoService {
	
	@Resource
	private FinSellerInfoMapper finSellerInfoMapper;
	
	/**
	 * 分销商交易明细表.
	 */
	@Resource
	private  FinSellerInfoDetailMapper  finSellerInfoDetailMapper;
	

	@Override
	public int findPageCount(FinSellerInfoEntity finSellerInfoEntity){
		return finSellerInfoMapper.findPageCount(finSellerInfoEntity);
	}

	@Override
	public List<FinSellerInfoEntity> findPage(FinSellerInfoEntity finSellerInfoEntity,RowBounds rowBounds){
		return finSellerInfoMapper.findPage(finSellerInfoEntity,rowBounds);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public MessageOutputDto createSeller(FinSellerInfoEntity finSellerInfoEntity){
		MessageOutputDto outPut = new MessageOutputDto();
        if (StringUtils.isNotEmpty(finSellerInfoEntity.getId())) {
        	try {
        		if (finSellerInfoEntity.getAccountBalance() == null) {
        			finSellerInfoEntity.setAccountBalance(new Double(0));
        		}
        		if (finSellerInfoEntity.getRegisterTime() == null) {
        			finSellerInfoEntity.setRegisterTime(new Date());
        		}
        		finSellerInfoMapper.insert(finSellerInfoEntity);
        	} catch (Exception error) {
        		outPut.setCode("500");
            	outPut.setMessage("当前分销商账户信息同步到财务资料库中失败！");
            	logger.info("当前分销商账户信息同步到财务资料库中失败！分销商ID："+finSellerInfoEntity.getId()+"，错误："+error.getMessage());
            	return outPut;
        	}
        } else {
        	this.logger.info("分销商账户信息同步到财务资料库中失败，分销商ID或分销商账户为空");
        	outPut.setCode("500");
        	outPut.setMessage("当前分销商账户ID为空或者直销商账号为空！");
        }
        outPut.setCode("100");
    	outPut.setMessage("当前分销商账户信息在财务库中创建成功！");
		return outPut;
	}
	
	@Override
	@Transactional
	public int update(FinSellerInfoEntity finSellerInfoEntity){
		return  finSellerInfoMapper.update(finSellerInfoEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return finSellerInfoMapper.remove(id);
	}
	
	@Override
	public FinSellerInfoEntity getById(String id){
		return finSellerInfoMapper.getById(id);
	}

	/**
	 * 分销商账户佣金结算入口
	 * @throws IOException 
	 * @see com.yougou.wfx.finance.service.IFinSellerInfoService#applyCommissionIncome(com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public MessageOutputDto applyCommissionIncome(final FinSellerInfoDetailEntity obj) throws Exception {
		this.logger.info("分销商账户结算，结算数据："+obj);
		MessageOutputDto output = new MessageOutputDto();
		if (StringUtils.isEmpty(obj.getSellerId())) {
			this.logger.info("佣金结算调度失败，分销商id为空，结算订单号：" + obj.getTransactionOrderNum());
			output.setCode("500");
			output.setMessage("分销商ID不能为空");
			return output;
		}
		//通过分销商ID获得当前的分销商信息.
		FinSellerInfoEntity entity = this.getById(obj.getSellerId());
		if (entity == null) {
			this.logger.info("佣金结算调度失败，分销商账户表无对应的分销商，结算分销商id：" + obj.getSellerId() + "，结算订单号："+ obj.getTransactionOrderNum());
			output.setCode("500");
			output.setMessage("分销商账户表无对应的分销商");
			return output;
		}
		//获取当前的佣金收益
		Double incomeCommission = obj.getIncomeAmount();
		//获取当前分销商的账户可用余额
		Double accountAmount = entity.getAccountBalance();
		//佣金收益与账户可用余额的和不得小于零
		if (CalculateUtils.lt(CalculateUtils.add(accountAmount, incomeCommission), new Double(0))){
			this.logger.info("佣金结算调度失败，佣金收益超出账户余额的可用范围，结算分销商id：" + obj.getSellerId() + "，结算订单号："+ obj.getTransactionOrderNum());
			output.setCode("400");
			output.setMessage("当前佣金收益超出账户余额的可用范围!");
			return output;
		}
		//操作主表账户余额做累加，佣金收益做累加.
		FinSellerInfoEntity newSellerEntity = new FinSellerInfoEntity();
		newSellerEntity.setLockVersion(entity.getLockVersion());
		newSellerEntity.setAccountBalance(CalculateUtils.add(accountAmount, incomeCommission));
		if (StringUtils.equals("1", obj.getCommissionType())) {
			newSellerEntity.setCommissionTotalAmount(CalculateUtils.add(incomeCommission, entity.getCommissionTotalAmount()));
		} else {
			newSellerEntity.setCommissionNextTotalAmount(CalculateUtils.add(incomeCommission, entity.getCommissionNextTotalAmount()));
		}
		newSellerEntity.setCommissionAllTotalAmount(CalculateUtils.add(incomeCommission, entity.getCommissionAllTotalAmount()));
		newSellerEntity.setLatelyTransactionTime(new Date());
		newSellerEntity.setId(entity.getId());
		int updateCount = 0 ;
		//更新分销商账户佣金中收益,账户可用余额出错(接口调用处需做一下异常抓取操作)
		updateCount = finSellerInfoMapper.changeSellerAmount(newSellerEntity);
		if (updateCount == 0) {
			output.setCode("500");
			output.setMessage("更新分销商账户佣金中收益，账户可用余额出错(系统发生同步修改出错)!");
			this.logger.info("佣金结算调度失败，更新分销商账户佣金中收益，账户可用余额出错(系统发生同步修改出错)，结算分销商id：" + obj.getSellerId() + "，结算订单号："+ obj.getTransactionOrderNum());
			return output;
		}
		
		//向交易明细表中插入明细交易记录.
		FinSellerInfoDetailEntity  detail = new FinSellerInfoDetailEntity();
		String detailId = UUIDGenerator.get32LowCaseUUID();
	    detail.setSellerId(entity.getId());
	    detail.setSellerNextId(obj.getSellerNextId());
	    detail.setTransactionOrderNum(obj.getTransactionOrderNum());
		detail.setOperater(StringUtils.isBlank(obj.getOperater()) ? "system" : obj.getOperater());
		detail.setTransactionType(FinSellerAccountTranTypeEnum.ACCOUNT_TRAN_INCOME.getCode());
		detail.setCommissionType(obj.getCommissionType());
		if (CalculateUtils.ge(incomeCommission, new Double(0))) {
			detail.setExpendAmount(new Double(0));
			detail.setIncomeAmount(incomeCommission);
			detail.setTransactionFlag("1");
			detail.setOperateNote("佣金收入，收入金额为"+incomeCommission);
		} else {
			detail.setExpendAmount(incomeCommission);
			detail.setIncomeAmount(new Double(0));
			detail.setTransactionFlag("2");
			detail.setOperateNote("退货产生负佣金，金额为"+incomeCommission);
		}
		detail.setAccountBalance(CalculateUtils.add(accountAmount, incomeCommission));
		detail.setRemark(obj.getTransactionOrderNum()); //备注订单号
		detail.setBillState(FinSellerAccountTranStatusEnum.ACCOUNT_TRAN_SUCCESS.getCode());
		detail.setTransactionTime(new Date());
		detail.setTransactionNumber(FinSellerAccountWithdrawServiceUtils.generateSequenceNumber("IC"));
		detail.setCreateTime(new Date());
		detail.setId(detailId);
		detail.setCommissionId(obj.getCommissionId());
		//向交易明细表中插入一笔收入明细,当前佣金收益信息插入到账户明细中出错(接口调用处需做一次异常抓取操作)
		finSellerInfoDetailMapper.insert(detail);
		output.setCode("100");
		output.setMessage("操作成功，产生一条佣金收益记录!");
		return output;
	}

	/**
	 * @see com.yougou.wfx.finance.service.IFinSellerInfoService#querySubSellers(java.util.List)
	 */
	@Override
	public List<FinSellerInfoEntity> querySubSellers(final List<String> subSellers) {
		return finSellerInfoMapper.querySubSellers(subSellers);
	}

	@Override
	public Double getTotalCommissionAmount(String sellerId) {
		FinSellerInfoEntity sellerEntity = this.getById(sellerId);
		if (sellerEntity == null) {
			this.logger.error("B端获取分销商累计收益，不存在该分销商ID：" + sellerId);
			return null;
		}
		return sellerEntity.getCommissionAllTotalAmount() == null ? new Double(0) : sellerEntity.getCommissionAllTotalAmount();
	}

	@Override
	public Double getTodayCommissionAmount(String sellerId) {
		Double todayCommissionAmount = 0.0;
		FinSellerInfoDetailEntity sellerDetailEntity = new FinSellerInfoDetailEntity();
		sellerDetailEntity.setSellerId(sellerId);
		sellerDetailEntity.setTransactionType(FinSellerAccountTranTypeEnum.ACCOUNT_TRAN_INCOME.getCode());
		sellerDetailEntity.setCreateStartTime(DateUtil.format1(new Date())+" 00:00:00");
		sellerDetailEntity.setCreateEndTime(DateUtil.format1(new Date())+" 23:59:59");
		List<FinSellerInfoDetailEntity> sellerDetailEntityList = this.finSellerInfoDetailMapper.querySellerDetailList(sellerDetailEntity);
		if (sellerDetailEntityList != null && sellerDetailEntityList.size() > 0) {
			for (FinSellerInfoDetailEntity entity : sellerDetailEntityList) {
				if (entity.getIncomeAmount() == null) {
					entity.setIncomeAmount(new Double(0));
				}
				if (entity.getExpendAmount() == null) {
					entity.setExpendAmount(new Double(0));
				}
				if (StringUtils.equals("1", entity.getTransactionFlag())) { //收入
					todayCommissionAmount = CalculateUtils.add(todayCommissionAmount, entity.getIncomeAmount());
				} else if (StringUtils.equals("2", entity.getTransactionFlag())) { // 支出
					todayCommissionAmount = CalculateUtils.add(todayCommissionAmount, entity.getExpendAmount());
				}
			}
		}
		return todayCommissionAmount;
	}

	/** 更新分销商基本信息 */
	@Override
	@Transactional
	public int updateSeller(FinSellerInfoEntity finSellerInfoEntity) {
		return this.finSellerInfoMapper.updateSeller(finSellerInfoEntity);
	}

	@Override
	public Map<String, Object> queryAccountSummary(FinSellerInfoEntity finSellerInfoEntity) {
		Map<String, Object> resultMap = finSellerInfoMapper.queryAccountSummary(finSellerInfoEntity);
		if (resultMap == null) {
			resultMap = new HashMap<String, Object>();
			resultMap.put("accountBalance", new Double(0));
			resultMap.put("cashedTotalAmount", new Double(0));
			resultMap.put("cashingTotalAmount", new Double(0));
			resultMap.put("commissionAllTotalAmount", new Double(0));
		}
		return resultMap;
	}
	
}