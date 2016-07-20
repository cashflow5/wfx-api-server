 /*
 * 版本信息
 
 * 日期 2016-04-14 14:39:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerCommodityCatRelaEntity;

/**
 * SellerCommodityCatRelaMapper
 * @author zheng.qq
 * @Date 创建时间：2016-04-14 14:39:16
 */
public interface SellerCommodityCatRelaMapper{
	
	int findPageCount(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);

	List<SellerCommodityCatRelaEntity> findPage(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity,RowBounds rowBounds);
	
	int insert(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);
	
	int update(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);
	
	int remove(String id);
	
	SellerCommodityCatRelaEntity getById(String id);
	
	List<SellerCommodityCatRelaEntity> queryList(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);
	
	int removeByCatId(String catId);
}
