 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.CalculateUtils;
import com.yougou.wfx.finance.dao.CommissionDetailMapper;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.model.CommissionDetailEntity;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.service.ICommissionDetailService;
import com.yougou.wfx.finance.service.IFinSellerInfoService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommissionDetailService实现
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
@Service
public class CommissionDetailServiceImpl extends BaseService implements ICommissionDetailService {
	
	@Resource
	private CommissionDetailMapper commissionDetailMapper;
	@Resource
    IFinSellerInfoService finSellerInfoService;
	@Override
	public int findPageCount(CommissionDetailEntity commissionDetailEntity){
		return commissionDetailMapper.findPageCount(commissionDetailEntity);
	}
	
	@Override
    public Map<String,Object> findCommissionCollect(CommissionDetailEntity commissionDetailEntity){
        return commissionDetailMapper.findCommissionCollect(commissionDetailEntity);
    }

	@Override
	public List<CommissionDetailEntity> findPage(CommissionDetailEntity commissionDetailEntity,RowBounds rowBounds){
		return commissionDetailMapper.findPage(commissionDetailEntity,rowBounds);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
    public String insert(CommissionDetailEntity commissionDetailEntity){
        String strId = UUIDGenerator.get32LowCaseUUID();
        commissionDetailEntity.setId(strId);
        commissionDetailMapper.insert(commissionDetailEntity);
        return strId;
    }
	
	@Override
	@Transactional
	public int update(CommissionDetailEntity commissionDetailEntity){
		return  commissionDetailMapper.update(commissionDetailEntity);
	}
	
	@Override
	public CommissionDetailEntity getById(String id){
	    CommissionDetailEntity entity= commissionDetailMapper.getById(id);
	    if(null!=entity){
	        entity.setAccountBalance(
	        CalculateUtils.add(finSellerInfoService.getById(entity.getSellerId()).getAccountBalance(),0,2)
	        );//根据分销商ID获取账户余额
	    }
	    return entity;
	} 
	
	/**
     * 佣金明细结算-调度，计入账户余额 ，两个小时调度生成一次
	 * @throws Exception 
     */
	@Override
	@Transactional(rollbackFor=Exception.class)
    public void dispatchCreateAccountBalance(CommissionDetailEntity commissionDetailEntity) throws Exception{
        //1、逐步迭代佣金汇总计入相应分销商账号余额
        FinSellerInfoDetailEntity finSellerInfoDetailEntity=new FinSellerInfoDetailEntity();
        finSellerInfoDetailEntity.setSellerNextId(commissionDetailEntity.getNextSellerId());
        finSellerInfoDetailEntity.setIncomeAmount(commissionDetailEntity.getCommissionAmount());
        finSellerInfoDetailEntity.setSellerId(commissionDetailEntity.getSellerId());
        finSellerInfoDetailEntity.setCommissionType(commissionDetailEntity.getCommissionLevel());
        finSellerInfoDetailEntity.setTransactionType("1");//佣金交易
        finSellerInfoDetailEntity.setCommissionId(commissionDetailEntity.getId());//设置佣金明细id保存，防止重复结算出现
        finSellerInfoDetailEntity.setTransactionOrderNum(commissionDetailEntity.getWfxOrderDetailNo());
        MessageOutputDto  outputDto = finSellerInfoService.applyCommissionIncome(finSellerInfoDetailEntity);
        String code=outputDto.getCode();
        if("100".equals(code)){//处理成功
            commissionDetailEntity.setStatus("1");//已结算
            //2、余额汇入成功
            commissionDetailMapper.dispatchEndUpdate(commissionDetailEntity);
        }else if("400".equals(code)){//余额不足
            commissionDetailEntity.setStatus("2");//异常挂起
            commissionDetailEntity.setRemark("账户余额不足，不能结算！");
            //2、余额汇入失败做出相应处理
            commissionDetailMapper.dispatchEndUpdate(commissionDetailEntity);
        }
    }
    
	@Override
	public List<CommissionDetailEntity> queryList(CommissionDetailEntity commissionDetailEntity) {
		return commissionDetailMapper.queryList(commissionDetailEntity);
	}
    @Override
    public List<CommissionDetailEntity> queryUnSettleCommission() {
        return commissionDetailMapper.queryUnSettleCommission();
    }

    @Override
    public int findPageCountGroup(Map<String, String> map) {
        return commissionDetailMapper.findPageCountGroup(map);
    }
    @Override
    public List<CommissionDetailEntity> findPageGroup(Map<String, String> map, RowBounds rowBounds) {
        return commissionDetailMapper.findPageGroup(map,rowBounds);
    }
}