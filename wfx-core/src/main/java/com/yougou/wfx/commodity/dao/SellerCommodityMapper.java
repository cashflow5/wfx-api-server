 /*
 * 版本信息
 
 * 日期 2016-04-13 16:39:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.seller.model.SellerInfoEntity;

/**
 * SellerCommodityMapper
 * @author wfx
 * @Date 创建时间：2016-04-13 16:39:55
 */
public interface SellerCommodityMapper{
	
	int findPageCount(SellerCommodityEntity sellerCommodityEntity);

	List<SellerCommodityEntity> findPage(SellerCommodityEntity sellerCommodityEntity,RowBounds rowBounds);
	
	int insert(SellerCommodityEntity sellerCommodityEntity);
	
	int update(SellerCommodityEntity sellerCommodityEntity);
	
	int batchUpdate(List<SellerCommodityEntity> entityList);
	
	int remove(String id);
	
	SellerCommodityEntity getById(String id);
	
	List<SellerCommodityEntity> getSellerCommodity(SellerCommodityEntity sellerCommodityEntity);
	
	int batchInsertSellerCommodity(@Param("list")List<SellerCommodityEntity> lstSellerCommodity);
	
	int updateSellerShelvesStatus(SellerCommodityEntity sellerCommodityEntity);
	
	List<SellerCommodityEntity> pickUpShelvesCommodities(List<String> list);
	
	String   querySellerCommodity(SellerCommodityEntity sellerCommodityEntity);

	int delete(@Param("sellerId")String sellerId);
	
	int proxyAll(@Param("sellerInfo")SellerInfoEntity sellerInfo, @Param("originalSellerId")String originalSellerId,
			@Param("oldlist")List<SellerCommodityEntity> oldlist);
}
