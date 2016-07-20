 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.CommissionPercentEntity;
import com.yougou.wfx.finance.model.CommissionPercentLogEntity;

/**
 * CommissionPercentMapper
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
public interface CommissionPercentMapper{
	
	int findPageCount(CommissionPercentEntity commissionPercentEntity);
	List<CommissionPercentEntity> findPage(CommissionPercentEntity commissionPercentEntity,RowBounds rowBounds);
	
	int insert(CommissionPercentEntity commissionPercentEntity);
	int batchInsert(List<CommissionPercentEntity>  entityList);
	
	int update(CommissionPercentEntity commissionPercentEntity);
	
	int remove(String id);
    CommissionPercentEntity getCommissionByCondition(CommissionPercentEntity commissionPercentEntity);

    CommissionPercentEntity getByBaseCatId(String baseCatId);

    CommissionPercentEntity getById(String id);
    void batchInsertLog(List<CommissionPercentLogEntity> logEntities);
    void insertLog(CommissionPercentLogEntity logEntity);
    int findPageCountLog(CommissionPercentLogEntity logEntity);
    List<CommissionPercentLogEntity> findPageLog(CommissionPercentLogEntity logEntity,RowBounds rowBounds);
}
