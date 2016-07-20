 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityBrandEntity;

/**
 * CommodityBrandMapper
 * @author wuyang
 * @Date 创建时间：2016-03-23 18:39:34
 */
public interface CommodityBrandMapper{
	
	int findPageCount(CommodityBrandEntity commodityBrandEntity);

	List<CommodityBrandEntity> findPage(CommodityBrandEntity commodityBrandEntity,RowBounds rowBounds);
	
	int insert(CommodityBrandEntity commodityBrandEntity);
	
	int update(CommodityBrandEntity commodityBrandEntity);
	
	int remove(String id);
	
	CommodityBrandEntity getById(String id);
	
	int getBrandMaxHotSn();
	
	List<CommodityBrandEntity> queryCommodityBrandByCatId(String catId);
	
	void batchUpdateBrandUseFlag();
	
	void initUpdateBrandUseFlag();
}
