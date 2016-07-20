 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.cms.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.cms.model.CommoditySaleCatEntity;

/**
 * CommoditySaleCatMapper
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
public interface CommoditySaleCatMapper{
	
	int findPageCount(CommoditySaleCatEntity commoditySaleCatEntity);

	List<CommoditySaleCatEntity> findPage(CommoditySaleCatEntity commoditySaleCatEntity,RowBounds rowBounds);
	
	List<CommoditySaleCatEntity> queryList(CommoditySaleCatEntity commoditySaleCatEntity);
	
	int insert(CommoditySaleCatEntity commoditySaleCatEntity);
	
	int update(CommoditySaleCatEntity commoditySaleCatEntity);

	int updateChildren(CommoditySaleCatEntity commoditySaleCatEntity);
	
	int remove(String id);
	
	CommoditySaleCatEntity getById(String id);
	
	List<CommoditySaleCatEntity> queryHotCatList(CommoditySaleCatEntity commoditySaleCatEntity,RowBounds rowBounds);
	
	List<CommoditySaleCatEntity> queryHotCatList(CommoditySaleCatEntity commoditySaleCatEntity);
	
	int getHotCatCount(CommoditySaleCatEntity commoditySaleCatEntity);

	Integer getMaxHotCatSn();
	
	int batchUpdate(List<CommoditySaleCatEntity> entityList);
	
	int computeSaleCatSkuNum();
}
