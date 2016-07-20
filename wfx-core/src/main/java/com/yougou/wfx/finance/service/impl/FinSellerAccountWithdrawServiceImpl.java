 /*
 * 版本信息
 
 * 日期 2016-03-31 10:10:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.text.MessageFormat;
import java.util.Date;
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
import com.yougou.wfx.common.constant.MessageConstant;
import com.yougou.wfx.enums.BankCompanyEnum;
import com.yougou.wfx.finance.dao.FinSellerAccountWithdrawMapper;
import com.yougou.wfx.finance.dao.FinSellerInfoDetailMapper;
import com.yougou.wfx.finance.dao.FinSellerInfoMapper;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.enums.FinSellerAccountTranStatusEnum;
import com.yougou.wfx.finance.enums.FinSellerAccountTranTypeEnum;
import com.yougou.wfx.finance.enums.FinSellerAccountWithdrawalStatusEnum;
import com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.model.FinSellerInfoEntity;
import com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.proxy.messenger.SmsProxyApi;
import com.yougou.wfx.utils.BizNoGen;

/**
 * IFinSellerAccountWithdrawService实现
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
@Service
public class FinSellerAccountWithdrawServiceImpl extends BaseService implements IFinSellerAccountWithdrawService {
	
	@Resource
	private FinSellerAccountWithdrawMapper finSellerAccountWithdrawMapper;
	
	/**
	 * 分销商交易总表.
	 */
	@Resource
	private FinSellerInfoMapper  finSellerInfoMapper;
	
	/**
	 * 分销商交易明细表.
	 */
	@Resource
	private  FinSellerInfoDetailMapper  finSellerInfoDetailMapper;

	@Override
	public int findPageCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity){
		return finSellerAccountWithdrawMapper.findPageCount(finSellerAccountWithdrawEntity);
	}

	@Override
	public List<FinSellerAccountWithdrawEntity> findPage(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity,RowBounds rowBounds){
		return finSellerAccountWithdrawMapper.findPage(finSellerAccountWithdrawEntity,rowBounds);
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public MessageOutputDto createApplyBill(FinSellerAccountWithdrawEntity obj){
		MessageOutputDto output= new MessageOutputDto();
		//提现申请单对应的分销商ID为必填项.
		if(StringUtils.isEmpty(obj.getSellerId())){
			this.logger.info("当前提现申请单对应的分销商ID为空，提现分销商账户["+obj.getAccountNo()+"]");
			output.setCode("500");
			output.setMessage("当前提现申请单对应的分销商ID为空！");
			return output;
		}
		//获取当前分销商对应的可用余额.可用余额如果不够提现申请金额直接返回,不能创建提现申请单.
		FinSellerInfoEntity entity = finSellerInfoMapper.getById(obj.getSellerId());
		//判断通过分销商ID能否获得分销商信息,如果为空，则当前的分销商id失效
		if (null == entity) {
			this.logger.info("当前提现申请单分销商ID对应的分销商不存在，提现分销商账户["+obj.getAccountNo()+"]");
			output.setCode("500");
			output.setMessage("当前提现申请单分销商ID对应的分销商不存在！");
			return output;
		}
		//判断提现金额，每次提现不能大于500元
		if (CalculateUtils.gt(obj.getWithdrawAmount(), new Double(500))) {
			this.logger.info("提现金额不能大于500，提现分销商ID["+obj.getSellerId()+"]");
			output.setCode("200");
			output.setMessage("提现金额不能大于500");
			return output;
		}
		//判断提现金额，账户余额是否足够提现
		if (CalculateUtils.gt(obj.getWithdrawAmount(), entity.getAccountBalance())){
			this.logger.info("当前账户余额不足，提现分销商ID["+obj.getSellerId()+"]");
			output.setCode("400");
			output.setMessage("当前账户余额不足");
			return output;
		}
		//判断当月累计提现金额，限制 30000 元
		FinSellerAccountWithdrawEntity withdrawEntity = new FinSellerAccountWithdrawEntity();
		withdrawEntity.setSellerId(obj.getSellerId());
		withdrawEntity.setApplyTimeStart(DateUtil.getFirstDayOfCurMonth() + " 00:00:00");
		withdrawEntity.setApplyTimeEnd(DateUtil.format1(new Date()) + " 23:59:59");
		withdrawEntity.setBillStatus(FinSellerAccountWithdrawalStatusEnum.ACCOUNT_WITHDRAWAL_REFUSED.getCode());
		Map<String, Object> resultMap = this.finSellerAccountWithdrawMapper.getCurrMonthWithdrawAmount(withdrawEntity);
		if (resultMap != null) {
			Double totalWithdrawalAmount = CalculateUtils.add(Double.valueOf(resultMap.get("MONTH_TOTAL_AMOUNT").toString()), obj.getWithdrawAmount());
			if (CalculateUtils.gt(totalWithdrawalAmount, Double.valueOf(30000))) {
				this.logger.info("月累计提现金额不能超过30000，提现分销商ID["+obj.getSellerId()+"]");
				output.setCode("300");
				output.setMessage("月累计提现金额不能超过30000");
				return output;
			}
		}
		//实收费用，暂无手续费，默认等于提现金额
		if (obj.getActualReceivedAmount() == null) {
			obj.setActualReceivedAmount(obj.getWithdrawAmount());
		}
		if (obj.getServiceAmount() == null) {
			obj.setServiceAmount(new Double(0));
		}
		if (StringUtils.isBlank(obj.getAccountBankName()) && StringUtils.isNotBlank(obj.getAccountBankNo())) {
			obj.setAccountBankName(BankCompanyEnum.getDescByKey(Integer.valueOf(obj.getAccountBankNo())));
		}
		String withdrawalApplyNo = BizNoGen.getAccountWithdrawalNo(); //提现申请单号
		
		// 账户生成支出的交易明细，向交易明细表中插入明细交易记录
		FinSellerInfoDetailEntity  detail = new FinSellerInfoDetailEntity();
		String detailId = UUIDGenerator.get32LowCaseUUID();
	    detail.setSellerId(obj.getSellerId());
		detail.setOperater(obj.getApplyer());
		detail.setOperateNote(obj.getApplyer() + "申请提现，支出金额为"+obj.getWithdrawAmount());
		detail.setTransactionType(FinSellerAccountTranTypeEnum.ACCOUNT_TRAN_EXPAND.getCode());
		detail.setTransactionFlag("2");
		detail.setExpendAmount(CalculateUtils.sub(new Double(0), obj.getWithdrawAmount()));
		detail.setIncomeAmount(new Double(0));
		detail.setAccountBalance(CalculateUtils.sub(entity.getAccountBalance(), obj.getWithdrawAmount()));
		detail.setRemark(withdrawalApplyNo); //备注提现申请单号
		detail.setPaymentStyle(obj.getAccountBankNo());
		detail.setPaymentStyleName(obj.getAccountBankName());
		detail.setBillState(FinSellerAccountTranStatusEnum.ACCOUNT_TRAN_PROCESSING.getCode());
		detail.setTransactionTime(new Date());
		detail.setTransactionNumber(FinSellerAccountWithdrawServiceUtils.generateSequenceNumber("TX"));
		detail.setCreateTime(new Date());
		detail.setId(detailId);
		//当前提现申请单信息插入到账户明细中出错(接口调用时,需捕获接口)
		finSellerInfoDetailMapper.insert(detail);
		
		obj.setId(UUIDGenerator.get32LowCaseUUID());
		obj.setSellerDetailId(detailId);
		obj.setWithdrawApplyNo(withdrawalApplyNo);
		obj.setAccountBalance(CalculateUtils.sub(entity.getAccountBalance(), obj.getWithdrawAmount()));
		obj.setCreateTime(new Date());
		//设置提现单状态.
		obj.setBillStatus(FinSellerAccountWithdrawalStatusEnum.ACCOUNT_WITHDRAWAL_UNAUDIT.getCode());
		//系统生成提现申请单出错(接口调用处,需做一次事物抓取操作！)
		finSellerAccountWithdrawMapper.insert(obj);
					
		//修改相应的账户总金额信息(账户余额做扣减，提现中总金额做累加)
		//乐观锁版本号累加1
		FinSellerInfoEntity newEntity = new FinSellerInfoEntity();
		Double accountBalance = CalculateUtils.sub(entity.getAccountBalance(), obj.getWithdrawAmount());
		Double cashingAmount = CalculateUtils.add(entity.getCashingTotalAmount(), obj.getWithdrawAmount());
		newEntity.setLockVersion(entity.getLockVersion());
		newEntity.setId(entity.getId());
		newEntity.setLatelyTransactionTime(new Date());
		newEntity.setAccountBalance(accountBalance);
		newEntity.setCashingTotalAmount(cashingAmount);
		int updateCount = 0 ;
		//当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(接口调用时需捕获接口异常)
		updateCount = finSellerInfoMapper.changeSellerAmount(newEntity);
		 //如果修改的记录数为零，则表明当前的乐观锁已经被同步修改过，需重新提交相应的提现申请单.
		if (updateCount == 0) {
			//如果修改不成功，明细记录回滚
			output.setCode("500");
			output.setMessage("当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(发生同步修改情况)！");
			this.logger.info("当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(发生同步修改情况)，提现分销商ID["+obj.getSellerId()+"]");
			this.finSellerInfoDetailMapper.remove(detail.getId());
			this.finSellerAccountWithdrawMapper.remove(obj.getId());
			return output;
		}
		output.setCode("100");
		output.setMessage("账户余额提现申请成功！");
		return output;
	}
	
	@Override
	@Transactional
	public int update(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity){
		return  finSellerAccountWithdrawMapper.update(finSellerAccountWithdrawEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return finSellerAccountWithdrawMapper.remove(id);
	}
	
	@Override
	public FinSellerAccountWithdrawEntity getById(String id){
		return finSellerAccountWithdrawMapper.getById(id);
	}

	/**
	 * 付款审核拒绝.
	 * @see com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService#refusedApplyBill(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public MessageOutputDto refusedModifyApplyBill(final FinSellerAccountWithdrawEntity dto) {
		MessageOutputDto  outPut = new MessageOutputDto();
		//获取提现申请单的详情.
		FinSellerAccountWithdrawEntity applyBill = this.getById(dto.getId());
		if(StringUtils.isEmpty(applyBill.getSellerId())){
			outPut.setCode("500");
			outPut.setMessage("当前提现申请单对应的直销商ID为空!");
			return outPut;
		}
		if(StringUtils.isEmpty(applyBill.getSellerDetailId())){
			outPut.setCode("500");
			outPut.setMessage("当前提现申请单无对应的交易明细记录!");
			return outPut;
		}
		//获取提现申请单对应的该直销商对应的提现中总金额，可用余额（如果拒绝，则提现中总金额做扣减，可用余额做累加,并生成一笔交易明细为收入）
		FinSellerInfoEntity entity = finSellerInfoMapper.getById(applyBill.getSellerId());
		if(null==entity){
			outPut.setCode("500");
			outPut.setMessage("当前提现申请单对应的分销商为无效分销商!");
			return outPut;
		}
		//明细交易记录做相应的关闭.
		FinSellerInfoDetailEntity detalEntity = finSellerInfoDetailMapper.getById(applyBill.getSellerDetailId());
		if(null==detalEntity){
			outPut.setCode("500");
			outPut.setMessage("当前提现申请单无对应的交易明细记录!");
			return outPut;
		}
		
		FinSellerInfoEntity newEntity = new FinSellerInfoEntity();
		//可用余额做累加.
		Double accountBalance = CalculateUtils.add(entity.getAccountBalance(), applyBill.getWithdrawAmount());
		//提现中总金额做扣减.
		Double cashingTotalAmount = CalculateUtils.sub(entity.getCashingTotalAmount(), applyBill.getWithdrawAmount());
		newEntity.setAccountBalance(accountBalance);
		newEntity.setCashingTotalAmount(cashingTotalAmount);
		newEntity.setLockVersion(entity.getLockVersion());
		newEntity.setId(entity.getId());
		int updateCount = 0;
		//当前提现申请单对应的分销商账户金额修改出错(接口调用需捕获异常)
		updateCount = finSellerInfoMapper.changeSellerAmount(newEntity);
		//如果修改的记录数为零，则表明当前的乐观锁已经被同步修改过，需重新提交相应的提现申请单.
		if(updateCount==0){
			outPut.setCode("500");
			outPut.setMessage("当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(发生同步修改情况)！");
			logger.info("当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(发生同步修改情况)！");
			return outPut;
		 }
		//将当前的交易明细置为交易关闭状态.
		FinSellerInfoDetailEntity stateUpdate = new FinSellerInfoDetailEntity();
		stateUpdate.setId(detalEntity.getId());
		stateUpdate.setBillState(FinSellerAccountTranStatusEnum.ACCOUNT_TRAN_CLOSED.getCode());
		//当前提现申请单修改对应的交易明细记录出错(接口调用需捕获异常)!
		finSellerInfoDetailMapper.update(stateUpdate);
		//生产一笔新的交易记录并为交易失败状态,支出金额作为收入金额.
		FinSellerInfoDetailEntity newDetailEntity = (FinSellerInfoDetailEntity) BeanUtil.convertBean(detalEntity, FinSellerInfoDetailEntity.class);
		newDetailEntity.setId(UUIDGenerator.get32LowCaseUUID());
		newDetailEntity.setTransactionTime(new Date());
		newDetailEntity.setExpendAmount(new Double(0));
		newDetailEntity.setAccountBalance(accountBalance);
		newDetailEntity.setIncomeAmount(applyBill.getWithdrawAmount());
		newDetailEntity.setRemark("申请提现，付款审核拒绝");
		newDetailEntity.setBillState(FinSellerAccountTranStatusEnum.ACCOUNT_TRAN_FAILURE.getCode());
		//当前提现申请单生产新的对应的收入交易明细记录出错(调用接口时需捕获异常)
		finSellerInfoDetailMapper.insert(newDetailEntity);
		//修改当前的提现审核单的状态.
		//修改当前提现申请单状态出错(调用接口时,需捕获异常)
		this.update(dto);
		outPut.setCode("100");
		outPut.setMessage("操作成功，提现单号"+ applyBill.getWithdrawApplyNo() +"审核拒绝!");
		return outPut;
	}

	/**
	 * 审核提现申请单.
	 * @see com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService#auditApplyBill(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public MessageOutputDto auditApplyBill(final FinSellerAccountWithdrawEntity dto) {
		MessageOutputDto output = new MessageOutputDto();
		//获取提现申请单的详情.
		FinSellerAccountWithdrawEntity obj = this.getById(dto.getId());
		//获取当前提现申请单对应的分销商信息.
		FinSellerInfoEntity entity = finSellerInfoMapper.getById(obj.getSellerId());
		if (null == entity) {
			output.setCode("500");
			output.setMessage("当前提现申请单无对应的分销商信息！");
			return output;
		}
		//提现申请单做相应的状态变更.
		//当前提现申请单状态变更出错(接口调用时,需捕获异常)
		int num = this.finSellerAccountWithdrawMapper.update(dto);
		if (num > 0) {
			output.setCode("100");
			output.setMessage("操作成功，提现单号"+ obj.getWithdrawApplyNo() +"审核通过！");
		} else {
			output.setCode("500");
			output.setMessage("操作失败，提现单号"+ obj.getWithdrawApplyNo() +"审核失败！");
		}
		return output;
	}
	
	/**
	 * 审核提现申请单，审核拒绝
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public MessageOutputDto auditRefuseApplyBill(FinSellerAccountWithdrawEntity dtoEntity) {
		MessageOutputDto messageOutPut = new MessageOutputDto();
		//获取提现申请单的详情.
		FinSellerAccountWithdrawEntity applyBill = this.getById(dtoEntity.getId());
		if (StringUtils.isEmpty(applyBill.getSellerId())) {
			messageOutPut.setCode("500");
			messageOutPut.setMessage("当前提现申请单对应的分销商ID为空!");
			return messageOutPut;
		}
		if (StringUtils.isEmpty(applyBill.getSellerDetailId())) {
			messageOutPut.setCode("500");
			messageOutPut.setMessage("当前提现申请单无对应的交易明细记录!");
			return messageOutPut;
		}
		//获取提现申请单对应的该直销商对应的提现中总金额，可用余额（如果拒绝，则提现中总金额做扣减，可用余额做累加,并生成一笔交易明细为收入）
		FinSellerInfoEntity sellerEntity = finSellerInfoMapper.getById(applyBill.getSellerId());
		if (null == sellerEntity) {
			messageOutPut.setCode("500");
			messageOutPut.setMessage("当前提现申请单对应的分销商为无效分销商!");
			return messageOutPut;
		}
		//查询交易明细
		FinSellerInfoDetailEntity sellerDetailEntity = finSellerInfoDetailMapper.getById(applyBill.getSellerDetailId());
		if (null == sellerDetailEntity) {
			messageOutPut.setCode("500");
			messageOutPut.setMessage("当前提现申请单无对应的交易明细记录!");
			return messageOutPut;
		}
		//修改当前的提现审核单的状态(调用接口出错,需捕获异常)
	    int num = this.finSellerAccountWithdrawMapper.update(dtoEntity);
	    if (num > 0) {
	    	FinSellerInfoEntity newSellerEntity = new FinSellerInfoEntity();
	    	//可用余额做累加.
	    	Double accountBalance = CalculateUtils.add(sellerEntity.getAccountBalance(), applyBill.getWithdrawAmount());
	    	//提现中总金额做扣减.
	    	Double cashingTotalAmount = CalculateUtils.sub(sellerEntity.getCashingTotalAmount(), applyBill.getWithdrawAmount());
	    	newSellerEntity.setAccountBalance(accountBalance);
	    	newSellerEntity.setCashingTotalAmount(cashingTotalAmount);
	    	newSellerEntity.setLockVersion(sellerEntity.getLockVersion());
	    	newSellerEntity.setId(sellerEntity.getId());
	    	int updateCount = 0;
	    	//当前提现申请单对应的分销商账户金额修改出错(接口调用需捕获异常)
	    	updateCount = finSellerInfoMapper.changeSellerAmount(newSellerEntity);
	    	//如果修改的记录数为零，则表明当前的乐观锁已经被同步修改过，需重新提交相应的提现申请单.
	    	if (updateCount == 0) {
	    		messageOutPut.setCode("500");
	    		messageOutPut.setMessage("当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(发生同步修改情况)！");
	    		logger.info("当前提现申请单详情修改分销商主表账户余额，提现中总金额出错(发生同步修改情况)！");
	    		return messageOutPut;
	    	} else {
	    		//将当前分销商对应的交易明细置为交易关闭状态.
	    		FinSellerInfoDetailEntity stateUpdate = new FinSellerInfoDetailEntity();
	    		stateUpdate.setId(sellerDetailEntity.getId());
	    		stateUpdate.setBillState(FinSellerAccountTranStatusEnum.ACCOUNT_TRAN_CLOSED.getCode());
	    		//当前提现申请单修改对应的交易明细记录出错(接口调用需捕获异常)!
	    		finSellerInfoDetailMapper.update(stateUpdate);
	    	}
	    	messageOutPut.setCode("100");
	    	messageOutPut.setMessage("操作成功，提现单号"+ applyBill.getWithdrawApplyNo() +"审核拒绝成功!");
	    } else {
	    	messageOutPut.setCode("500");
	    	messageOutPut.setMessage("操作失败，提现单号"+ applyBill.getWithdrawApplyNo() +"审核拒绝失败!");
	    }
		return messageOutPut;
	} 

	/**
	 * 当前提现申请单付款操作.
	 * @see com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService#modifyApplyBill(com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public MessageOutputDto modifyApplyBill(final FinSellerAccountWithdrawEntity dto) {
		MessageOutputDto output = new MessageOutputDto();
		//获取提现申请单的详情.
		FinSellerAccountWithdrawEntity obj = this.getById(dto.getId());
		//获取当前提现申请单对应的分销商信息.
		FinSellerInfoEntity entity = finSellerInfoMapper.getById(obj.getSellerId());
		if (null == entity) {
			output.setCode("500");
			output.setMessage("当前提现申请单无对应的分销商信息！");
			return output;
		}
		//获取相应的明细交易记录状态置为交易成功.
		FinSellerInfoDetailEntity detalEntity = finSellerInfoDetailMapper.getById(obj.getSellerDetailId());
		if (null == detalEntity) {
			output.setCode("500");
			output.setMessage("当前提现申请单无对应的交易明细记录!");
			return output;
		}
		
		//对应分销商提现中总金额做扣减，已提现总金额做累加.
		FinSellerInfoEntity newEntity = new FinSellerInfoEntity();
		Double cashingTotalAmount = CalculateUtils.sub(entity.getCashingTotalAmount(), obj.getWithdrawAmount());
		Double cashedTotalAmount = CalculateUtils.add(entity.getCashedTotalAmount(), obj.getWithdrawAmount());
		newEntity.setLockVersion(entity.getLockVersion());
		newEntity.setId(entity.getId());
		newEntity.setCashingTotalAmount(cashingTotalAmount);
		newEntity.setCashedTotalAmount(cashedTotalAmount);
		int updateCount = 0 ; 
		//当前提现申请单修改对应的分销商提现中总金额、已提现金额失败(接口调用时,需捕获异常)
		updateCount = finSellerInfoMapper.changeSellerAmount(newEntity);
		if (updateCount == 0) {
		    output.setCode("500");
		    output.setMessage("当前提现申请单详情修改分销商主表出错(发生同步修改情况)！");
		    logger.info("当前提现申请单详情修改分销商主表出错(发生同步修改情况)！");
		    return output;
		}
		//交易明细状态置为交易成功
		FinSellerInfoDetailEntity newDetailEntity = new FinSellerInfoDetailEntity();
		newDetailEntity.setId(detalEntity.getId());
		newDetailEntity.setBillState(FinSellerAccountTranStatusEnum.ACCOUNT_TRAN_SUCCESS.getCode());
		//当前提现申请单详情对应的交易明细记录修改出错(接口调用时,需捕获接口异常)
		finSellerInfoDetailMapper.update(newDetailEntity);
		//修改提现申请单状态信息.
		//当前提现申请单修改状态出错(接口调用时,需捕获异常)
		this.update(dto);
		//提现付款成功，立即发送短信通知
		if (StringUtils.isNotBlank(entity.getSellerAccount())) {
			String content = MessageFormat.format(MessageConstant.SELLER_WITHDRAWAL_SUCCESS, obj.getWithdrawApplyNo(), obj.getWithdrawAmount());
			try {
				SmsProxyApi.sendNow(new String[] {entity.getSellerAccount()}, content, "withdrawal");
			} catch (Exception e) {
				this.logger.error("提现成功["+ obj.getWithdrawApplyNo() +"]，发送短信通知，出错：", e.getCause());
			}
		}
		output.setCode("100");
		output.setMessage("操作成功，提现单号"+ obj.getWithdrawApplyNo() +"付款成功！");
		return output;
	}

	/**
	 * 查询提现申请单详情(接口调用)
	 * @see com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService#queryDetailsWithdraw(com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity)
	 */
	@Override
	public FinSellerAccountWithdrawEntity queryDetailsWithdraw(final FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity) {
		return finSellerAccountWithdrawMapper.queryDetailsWithdraw(finSellerAccountWithdrawEntity);
	}

	@Override
	public List<FinSellerAccountWithdrawEntity> queryWithdrawalList(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity) {
		return this.finSellerAccountWithdrawMapper.queryWithdrawalList(finSellerAccountWithdrawEntity);
	}

	@Override
	public int queryWithdrawalListCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity) {
		return this.finSellerAccountWithdrawMapper.queryWithdrawalListCount(finSellerAccountWithdrawEntity);
	}

	@Override
	public int queryPageListCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity) {
		return this.finSellerAccountWithdrawMapper.queryPageListCount(finSellerAccountWithdrawEntity);
	}

	@Override
	public List<FinSellerAccountWithdrawEntity> queryPageList(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity,
			RowBounds rowBounds) {
		return this.finSellerAccountWithdrawMapper.queryPageList(finSellerAccountWithdrawEntity, rowBounds);
	}

	@Override
	public Double queryTotalAmountByStatus(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity) {
		Double totalAmount = 0.0;
		List<FinSellerAccountWithdrawEntity> entityList = this.finSellerAccountWithdrawMapper.queryDataList(finSellerAccountWithdrawEntity);
		if (entityList != null && entityList.size() > 0) {
			for (FinSellerAccountWithdrawEntity entity : entityList) {
				if (entity.getWithdrawAmount() == null) {
					entity.setWithdrawAmount(0.0);
				}
				totalAmount = CalculateUtils.add(totalAmount, entity.getWithdrawAmount());
			}
		}
		return totalAmount;
	}

	/*
	 * 申请提现规则验证
	 * @see com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService#applyBillValidation(com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity)
	 */
	@Override
	public MessageOutputDto applyBillValidation(String sellerId) {
		MessageOutputDto output = new MessageOutputDto();
		//提现申请单对应的分销商ID为必填项
		if (StringUtils.isBlank(sellerId)) {
			this.logger.info("申请提现验证参数错误，对应的分销商sellerId为空");
			output.setCode("500");
			output.setMessage("申请提现验证参数错误，申请单对应的分销商sellerId为空！");
			return output;
		}
		//获取当前分销商对应的可用余额.可用余额如果不够提现申请金额直接返回,不能创建提现申请单.
		FinSellerInfoEntity sellerEntity = this.finSellerInfoMapper.getById(sellerId);
		if (sellerEntity == null) {
			this.logger.info("申请提现验证失败，申请单对应的分销商sellerId信息不存在，提现分销商Id:"+sellerId);
			output.setCode("500");
			output.setMessage("申请提现验证失败，申请单对应的分销商不存在！");
			return output;
		}
		//判断一周是否有提现记录，限一周只能提现一次		
		Date date = new Date();
		int dayOfWeek = DateUtil.getWeek(date); //获取当前日期是自然周内的第几天
		//获取所在自然周的周一的日期
		String startTime = DateUtil.format1(DateUtil.addDaysToDate(date, -dayOfWeek + 1)) + " 00:00:00";
		String endTime = DateUtil.format1(date) + " 23:59:59";
		FinSellerAccountWithdrawEntity tempWithdrawEntity = new FinSellerAccountWithdrawEntity();
		tempWithdrawEntity.setSellerId(sellerId);
		tempWithdrawEntity.setApplyTimeStart(startTime);
		tempWithdrawEntity.setApplyTimeEnd(endTime);
		tempWithdrawEntity.setBillStatus(FinSellerAccountWithdrawalStatusEnum.ACCOUNT_WITHDRAWAL_REFUSED.getCode());
		int num = this.finSellerAccountWithdrawMapper.queryWithdrawNumInWeek(tempWithdrawEntity);
		if (num > 0) {
			output.setCode("600");
			output.setMessage("申请提现验证，提现失败，超出提现次数(注：一周只能提现一次)");
			this.logger.info("申请提现验证，提现失败，超出提现次数（一周只能提现一次），提现分销商ID:"+sellerId);
			return output;
		}
		output.setCode("100");
		output.setMessage("申请提现验证通过");
		return output;
	}

	/**
	 * 确认提现申请单
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public MessageOutputDto auditConfirmApplyBill(FinSellerAccountWithdrawEntity finSellerAccountWithdraw) {
		MessageOutputDto messageDto = new MessageOutputDto();
		//查询提现详情
		FinSellerAccountWithdrawEntity widthdrawEntity = getById(finSellerAccountWithdraw.getId());
		//查询分销商账户信息
		FinSellerInfoEntity sellerEntity = finSellerInfoMapper.getById(widthdrawEntity.getSellerId());
		if (null == sellerEntity) {
			messageDto.setCode("500");
			messageDto.setMessage("当前提现申请单无对应的分销商信息！");
			return messageDto;
		}
		//查询分销商交易明细
		FinSellerInfoDetailEntity sellerDetailEntity = finSellerInfoDetailMapper.getById(widthdrawEntity.getSellerDetailId());
		if (null == sellerDetailEntity) {
			messageDto.setCode("500");
			messageDto.setMessage("当前提现申请单无对应的分销商交易明细信息！");
			return messageDto;
		}
		int num = finSellerAccountWithdrawMapper.updateBaseInfo(finSellerAccountWithdraw);
		if (num > 0) {
			messageDto.setCode("100");
			messageDto.setMessage("操作成功，提现单号"+ widthdrawEntity.getWithdrawApplyNo() +"已确认！");
		} else {
			messageDto.setCode("500");
			messageDto.setMessage("操作失败，提现单号"+ widthdrawEntity.getWithdrawApplyNo() +"确认失败！");
		}
		return messageDto;
	}

}