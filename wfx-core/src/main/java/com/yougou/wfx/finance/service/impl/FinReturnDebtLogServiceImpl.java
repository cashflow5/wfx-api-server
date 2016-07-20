 /*
 * 版本信息
 
 * 日期 2016-04-05 19:11:06
 
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

import com.yougou.wfx.finance.dao.FinReturnDebtLogMapper;
import com.yougou.wfx.finance.model.FinReturnDebtLogEntity;
import com.yougou.wfx.finance.service.IFinReturnDebtLogService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IFinReturnDebtLogService实现
 * @author wfx
 * @Date 创建时间：2016-04-05 19:11:08
 */
@Service
public class FinReturnDebtLogServiceImpl extends BaseService implements IFinReturnDebtLogService {
	
	@Resource
	private FinReturnDebtLogMapper finReturnDebtLogMapper;

	@Override
	public int findPageCount(FinReturnDebtLogEntity finReturnDebtLogEntity){
		return finReturnDebtLogMapper.findPageCount(finReturnDebtLogEntity);
	}

	@Override
	public List<FinReturnDebtLogEntity> findPage(FinReturnDebtLogEntity finReturnDebtLogEntity,RowBounds rowBounds){
		return finReturnDebtLogMapper.findPage(finReturnDebtLogEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(FinReturnDebtLogEntity finReturnDebtLogEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		finReturnDebtLogEntity.setId(strId);
		finReturnDebtLogMapper.insert(finReturnDebtLogEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(FinReturnDebtLogEntity finReturnDebtLogEntity){
		return  finReturnDebtLogMapper.update(finReturnDebtLogEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return finReturnDebtLogMapper.remove(id);
	}
	
	@Override
	public FinReturnDebtLogEntity getById(String id){
		return finReturnDebtLogMapper.getById(id);
	}

	@Override
	public List<FinReturnDebtLogEntity> queryDataList(FinReturnDebtLogEntity finReturnDebtLogEntity) {
		return finReturnDebtLogMapper.queryDataList(finReturnDebtLogEntity);
	}
}