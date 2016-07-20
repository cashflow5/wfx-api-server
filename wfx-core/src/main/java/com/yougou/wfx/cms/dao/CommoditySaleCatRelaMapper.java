 /*
 * 版本信息
 
 * 日期 2016-04-01 18:28:14
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.cms.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.cms.model.CommoditySaleCatRelaEntity;

/**
 * CommoditySaleCatRelaMapper
 * @author wfx
 * @Date 创建时间：2016-04-01 18:28:15
 */
public interface CommoditySaleCatRelaMapper{
	
	int findPageCount(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);

	List<CommoditySaleCatRelaEntity> findPage(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity,RowBounds rowBounds);
	
	int insert(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
	
	int update(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
	
	int remove(String id);
	
	CommoditySaleCatRelaEntity getById(String id);
	
	List<CommoditySaleCatRelaEntity> queryList(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
	
	int multiRemove(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
}
