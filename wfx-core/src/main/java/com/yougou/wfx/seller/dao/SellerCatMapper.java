 /*
 * 版本信息
 
 * 日期 2016-04-13 14:20:20
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerCatEntity;

/**
 * SellerCatMapper
 * @author wfx
 * @Date 创建时间：2016-04-13 14:20:20
 */
public interface SellerCatMapper{
	
	int findPageCount(SellerCatEntity sellerCatEntity);

	List<SellerCatEntity> findPage(SellerCatEntity sellerCatEntity,RowBounds rowBounds);

	List<SellerCatEntity> queryList(SellerCatEntity sellerCatEntity);
	
	int insert(SellerCatEntity sellerCatEntity);
	
	int update(SellerCatEntity sellerCatEntity);
	
	int remove(String id);
	
	SellerCatEntity getById(String id);
}
