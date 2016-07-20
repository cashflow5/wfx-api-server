 /*
 * 版本信息
 
 * 日期 2016-04-05 19:11:06
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinReturnDebtLogEntity;

/**
 * FinReturnDebtLogMapper
 * @author wfx
 * @Date 创建时间：2016-04-05 19:11:08
 */
public interface FinReturnDebtLogMapper{
	
	int findPageCount(FinReturnDebtLogEntity finReturnDebtLogEntity);

	List<FinReturnDebtLogEntity> findPage(FinReturnDebtLogEntity finReturnDebtLogEntity,RowBounds rowBounds);
	
	int insert(FinReturnDebtLogEntity finReturnDebtLogEntity);
	
	int update(FinReturnDebtLogEntity finReturnDebtLogEntity);
	
	int remove(String id);
	
	FinReturnDebtLogEntity getById(String id);
	
	List<FinReturnDebtLogEntity> queryDataList(FinReturnDebtLogEntity finReturnDebtLogEntity);
}
