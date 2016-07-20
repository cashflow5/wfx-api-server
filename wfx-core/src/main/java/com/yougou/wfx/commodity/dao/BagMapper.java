package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.BagEntity;
import com.yougou.wfx.commodity.model.CommodityBagRelaEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;

/**
 * @author wuyang
 * @Date 创建时间：2016-03-20 13:16:54
 */
public interface BagMapper {

	int findPageCount(BagEntity bagEntity);

	List<BagEntity> findPage(BagEntity bagEntity, RowBounds rowBounds);

	int insert(BagEntity bagEntity);

	int update(BagEntity bagEntity);

	int remove(String id);

	BagEntity getById(String id);
	
	List<CommodityStyleEntity> findRelaCommodityList(String id);
	
	List<CommodityStyleEntity> findBagCommodityList(String bagId, RowBounds rowBounds);
	
	int findBagCommodityCount(String bagId);
	
	int insertCommodityBagRela(CommodityBagRelaEntity commodityBagRelaEntity);
	
	int removeCommodityBagRela(CommodityBagRelaEntity commodityBagRelaEntity);
}
