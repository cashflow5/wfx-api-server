 /*
 * 版本信息
 
 * 日期 2016-04-16 16:08:23
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerBankEntity;

/**
 * SellerBankMapper
 * @author luoq
 * @Date 创建时间：2016-04-16 16:08:24
 */
public interface SellerBankMapper{
	
	int findPageCount(SellerBankEntity sellerBankEntity);

	List<SellerBankEntity> findPage(SellerBankEntity sellerBankEntity,RowBounds rowBounds);
	
	int insert(SellerBankEntity sellerBankEntity);
	
	int update(SellerBankEntity sellerBankEntity);
	
	int remove(String id);
	
	SellerBankEntity getById(String id);

	List<SellerBankEntity> getSellerBanks(String sellerId);
}
