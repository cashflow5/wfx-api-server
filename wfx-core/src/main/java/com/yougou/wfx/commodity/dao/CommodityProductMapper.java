 /*
 * 版本信息
 
 * 日期 2016-04-14 13:34:05
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityProductEntity;

/**
 * CommodityProductMapper
 * @author wfx
 * @Date 创建时间：2016-04-14 13:34:06
 */
public interface CommodityProductMapper{
	
	int findPageCount(CommodityProductEntity commodityProductEntity);

	List<CommodityProductEntity> findPage(CommodityProductEntity commodityProductEntity,RowBounds rowBounds);

	List<CommodityProductEntity> queryList(CommodityProductEntity commodityProductEntity);
	
	int insert(CommodityProductEntity commodityProductEntity);
	
	int update(CommodityProductEntity commodityProductEntity);
	
	int remove(String id);
	
	CommodityProductEntity getById(String id);
	
	List<String> getCatId(String id);
	
	List<CommodityProductEntity>  getProductByProductNos(List<String> productId);
	
	int clearNotSPYG();
	
	
	List<CommodityProductEntity> getProductByCommodityId(
			Map<String,String> map);
}
