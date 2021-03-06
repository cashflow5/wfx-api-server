 /*
 * 版本信息
 
 * 日期 2016-03-31 19:13:58
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityBrandCatb2cEntity;

/**
 * CommodityBrandCatb2cMapper
 * @author wfx
 * @Date 创建时间：2016-03-31 19:13:58
 */
public interface CommodityBrandCatb2cMapper{
	
	int findPageCount(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity);

	List<CommodityBrandCatb2cEntity> findPage(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity,RowBounds rowBounds);
	
	int insert(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity);
	
	int update(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity);
	
	int remove(String id);
	
	CommodityBrandCatb2cEntity getById(String id);
	
	void batchInsert(@Param("list")List<CommodityBrandCatb2cEntity> list);

	int deleteByBrandId(String brandId);
	
}
