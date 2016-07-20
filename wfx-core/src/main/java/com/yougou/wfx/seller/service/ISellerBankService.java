 /*
 * 版本信息
 
 * 日期 2016-04-16 16:08:23
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerBankEntity;

/**
 * ISellerBankService接口
 * @author luoq
 * @Date 创建时间：2016-04-16 16:08:24
 */
public interface ISellerBankService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SellerBankEntity sellerBankEntity);

	/**
	 * 获取分页数据
	 */
	public List<SellerBankEntity> findPage(SellerBankEntity sellerBankEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerBankEntity sellerBankEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SellerBankEntity sellerBankEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SellerBankEntity getById(String id);

	public List<SellerBankEntity> getSellerBanks(String sellerId); 
}