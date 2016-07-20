 /*
 * 版本信息
 
 * 日期 2016-03-20 13:16:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.BagEntity;
import com.yougou.wfx.commodity.model.CommodityBagRelaEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
/**
 * @author wuyang
 * @Date 创建时间：2016-03-20 13:16:57
 */
public interface IBagService {
	
	public int findPageCount(BagEntity bagEntity);

	public List<BagEntity> findPage(BagEntity bagEntity,RowBounds rowBounds);
	
	public String insert(BagEntity bagEntity);
	
	public int update(BagEntity bagEntity);
	
	public int remove(String id);
	
	public BagEntity getById(String id); 
	
	public List<CommodityStyleEntity> findRelaCommodityList(String id);
	
	public String insertCommodityBagRela(CommodityBagRelaEntity commodityBagRelaEntity);
	
	public int removeCommodityBagRela(CommodityBagRelaEntity commodityBagRelaEntity);
	
	public List<CommodityStyleEntity> findBagCommodityList(String bagId,RowBounds rowBounds);
	
	int findBagCommodityCount(String bagId);
}