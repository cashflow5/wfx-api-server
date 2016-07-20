/*
 * 版本信息

 * 日期 2016-03-24 16:58:55

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.dto.output.CommodityPropertyOutputDto;
import com.yougou.wfx.commodity.model.CommodityInventoryEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.model.CommodityStyleFilterEntity;
import com.yougou.wfx.commodity.model.CommodityStyleOrderEntity;

/**
 * CommodityStyleMapper
 * 
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public interface CommodityStyleMapper {

	int findPageCount(CommodityStyleEntity commodityStyleEntity);

	List<CommodityStyleEntity> findPage(CommodityStyleEntity commodityStyleEntity, RowBounds rowBounds);

	List<CommodityStyleEntity> queryList(CommodityStyleEntity commodityStyleEntity);

	int insert(CommodityStyleEntity commodityStyleEntity);

	int update(CommodityStyleEntity commodityStyleEntity);

	int remove(String id);

	CommodityStyleEntity getById(String id);

	void batchCommodityAddToWfx(CommodityStyleEntity commodityStyleEntity);

	void batchUpdateShelvesStatus(CommodityStyleEntity commodityStyleEntity);

	List<CommodityStyleEntity> queryExportCommodityInfo(List<String> list);

	void readjustPrices(List<CommodityStyleEntity> commodtyList);

	void batchReadjustPrices(CommodityStyleEntity commodtyList);

	int findInventoryPageCount(CommodityInventoryEntity commodityInventoryEntity);

	List<CommodityInventoryEntity> findInventoryPage(CommodityInventoryEntity commodityInventoryEntity, RowBounds rowBounds);

	int queryListCount(CommodityStyleEntity commodityStyleEntity);

	List<CommodityStyleEntity> queryList(CommodityStyleEntity commodityStyleEntity, RowBounds rowBounds);

	List<CommodityStyleEntity> findPageByCommodityIds(@Param("list") List<String> commodityIds);

	int getStock(String commodityId);

	List<CommodityStyleEntity> getSameSellerCommodity(String commodityId) throws Exception;

	List<CommodityStyleEntity> queryProxyCommodity(String sellerId, RowBounds rowBounds) throws Exception;

	int queryProxyCommodityCount(String sellerId) throws Exception;

	void updateCommodityClassify(List<Map<String, String>> map) throws Exception;

	void deleteDellerCommodityCatRela(List<String> commodityId) throws Exception;

	List<CommodityStyleEntity> queryHotCommodity(Map<String, Object> hotMap  , RowBounds rowBounds) throws Exception;
	
	int queryHotCommodityCount(Map<String, Object> hotMap) throws Exception;
	
	List<CommodityStyleEntity> queryHotCommodityList(Map<String, Object> hotMap) throws Exception;
	
	
	List<CommodityStyleEntity> getShopCategoryCommodity(Map<String, Object> shopCategoryCommodity, RowBounds rowBounds) throws Exception;

	int getShopCategoryCommodityCount(Map<String, Object> shopCategoryCommodity) throws Exception;

	List<CommodityStyleEntity> getCommodityByNos(List<String> nos);

	List<CommodityStyleEntity> getCommodityByParameter(@Param("paramMap") Map<String, Object> paramMap);

	CommodityStyleEntity getByNo(String no);

	int batchUpdateSaleQuantity();
	
    List<String> getCatId(String commodityId);
    String getCommodityBrandNo(String commodityId);
	 

	List<CommodityStyleEntity> getCommodityByBrandNo(Map<String,String> map,RowBounds rowBounds);
	 
	int getCommodityByBrandNoCount(String brandId);

	List<CommodityStyleEntity> getCommodityByCatId(Map<String,String> map,RowBounds rowBounds);
	 
	 
	int getCommodityByCatIdCount(String catId);
	
	
	List<CommodityStyleEntity> queryCommodityListForSearch(Map<String,String> map,RowBounds rowBounds);
	
	
   int queryCommodityListForSearchCount(String searchName);
   
   int clearPicNotSPYG();
   
   List<CommodityStyleEntity> queryDiscoverStyleInfo(CommodityStyleEntity commodityStyleEntity , RowBounds rowBounds);
   
   int queryDiscoverStyleInfoCount(CommodityStyleEntity commodityStyleEntity);
   
   String getPicBigName(String commodityNo);
   
   List<CommodityStyleEntity> queryCommodityOrder(CommodityStyleOrderEntity commodityStyleEntity , RowBounds rowBounds);
   
   List<CommodityPropertyOutputDto> getPropByCommodityId(String commodityNo);
   
   int queryCommodityOrderCount(CommodityStyleOrderEntity commodityStyleEntity);
  
   List<CommodityStyleFilterEntity> getBrandInfo(CommodityStyleFilterEntity commodityStyleEntity);
  
   List<CommodityStyleFilterEntity> getCatInfo(CommodityStyleFilterEntity commodityStyleEntity);
  
   List<CommodityStyleFilterEntity> getPriceInfo(CommodityStyleFilterEntity commodityStyleEntity);
  
   List<CommodityStyleFilterEntity> getSizeInfo(CommodityStyleFilterEntity commodityStyleEntity);
   
 
   List<CommodityStyleFilterEntity> getSpecInfo(CommodityStyleFilterEntity commodityStyleEntity);
   
  
   List<CommodityStyleFilterEntity> getPropInfo(CommodityStyleFilterEntity commodityStyleEntity);

   String getPictureUrl(String no);

   List<CommodityStyleEntity> queryCommodityList(@Param("listSupplierCode")List<String> lstSupplierCode,
		@Param("listCommodityId")List<String> lstCommodityId);

}
