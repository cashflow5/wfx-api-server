 /*
 * 版本信息
 
 * 日期 2016-03-28 17:15:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.finance.dao.FinAlreadyIncomeMapper;
import com.yougou.wfx.finance.model.FinAlreadyIncomeEntity;
import com.yougou.wfx.finance.service.IFinAlreadyIncomeService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IFinAlreadyIncomeService实现
 * @author he.xx
 * @Date 创建时间：2016-03-28 17:15:58
 */
@Service
public class FinAlreadyIncomeServiceImpl extends BaseService implements IFinAlreadyIncomeService {
	
	@Resource
	private FinAlreadyIncomeMapper finAlreadyIncomeMapper;

	@Override
	public int findPageCount(FinAlreadyIncomeEntity finAlreadyIncomeEntity){
		return finAlreadyIncomeMapper.findPageCount(finAlreadyIncomeEntity);
	}

	@Override
	public List<FinAlreadyIncomeEntity> findPage(FinAlreadyIncomeEntity finAlreadyIncomeEntity,RowBounds rowBounds){
		return finAlreadyIncomeMapper.findPage(finAlreadyIncomeEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(FinAlreadyIncomeEntity finAlreadyIncomeEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		finAlreadyIncomeEntity.setId(strId);
		finAlreadyIncomeMapper.insert(finAlreadyIncomeEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(FinAlreadyIncomeEntity finAlreadyIncomeEntity){
		return  finAlreadyIncomeMapper.update(finAlreadyIncomeEntity);
	}
	
	@Override
	public FinAlreadyIncomeEntity getById(String id){
		return finAlreadyIncomeMapper.getById(id);
	}

	@Override
	public List<FinAlreadyIncomeEntity> queryDataList(FinAlreadyIncomeEntity finAlreadyIncomeEntity) {
		return this.finAlreadyIncomeMapper.queryDataList(finAlreadyIncomeEntity);
	}

	@Override
	public int queryDataListCount(FinAlreadyIncomeEntity finAlreadyIncomeEntity) {
		return this.finAlreadyIncomeMapper.queryDataListCount(finAlreadyIncomeEntity);
	}

	@Override
	public FinAlreadyIncomeEntity queryAlreadyIncome(FinAlreadyIncomeEntity finAlreadyIncomeEntity) {
		return this.finAlreadyIncomeMapper.queryAlreadyIncome(finAlreadyIncomeEntity);
	} 
}