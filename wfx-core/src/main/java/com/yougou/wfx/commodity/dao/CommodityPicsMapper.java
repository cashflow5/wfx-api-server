 /*
 * 版本信息
 
 * 日期 2016-05-09 17:09:12
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityPicsEntity;

/**
 * CommodityPicsMapper
 * @author wzf
 * @Date 创建时间：2016-05-09 17:09:13
 */
public interface CommodityPicsMapper{
	
	int findPageCount(CommodityPicsEntity commodityPicsEntity);

	List<CommodityPicsEntity> findPage(CommodityPicsEntity commodityPicsEntity,RowBounds rowBounds);

	List<CommodityPicsEntity> queryList(CommodityPicsEntity commodityPicsEntity);
	
	int insert(CommodityPicsEntity commodityPicsEntity);
	
	int update(CommodityPicsEntity commodityPicsEntity);
	
	int remove(String id);
	
	CommodityPicsEntity getById(String id);
}
