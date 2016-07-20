 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.CommissionDetailEntity;

/**
 * CommissionDetailMapper
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
public interface CommissionDetailMapper{
	
	int findPageCount(CommissionDetailEntity commissionDetailEntity);

	List<CommissionDetailEntity> findPage(CommissionDetailEntity commissionDetailEntity,RowBounds rowBounds);
	
	List<CommissionDetailEntity> queryList(CommissionDetailEntity commissionDetailEntity);
	
	int insert(CommissionDetailEntity commissionDetailEntity);
	
	int update(CommissionDetailEntity commissionDetailEntity);
	
	CommissionDetailEntity getById(String id);

    List<CommissionDetailEntity> queryUnSettleCommission();

    int dispatchEndUpdate(CommissionDetailEntity commissionDetailEntity);

    Map<String, Object> findCommissionCollect(CommissionDetailEntity commissionDetailEntity);

    int findPageCountGroup(Map<String, String> map);
    List<CommissionDetailEntity> findPageGroup(Map<String, String> map, RowBounds rowBounds);
}
