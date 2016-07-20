 /*
 * 版本信息
 
 * 日期 2016-03-29 14:08:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;

/**
 * FinSellerInfoDetailMapper
 * @author wfx
 * @Date 创建时间：2016-03-29 14:08:45
 */
public interface FinSellerInfoDetailMapper{
	
	int findPageCount(FinSellerInfoDetailEntity finSellerInfoDetailEntity);

	List<FinSellerInfoDetailEntity> findPage(FinSellerInfoDetailEntity finSellerInfoDetailEntity,RowBounds rowBounds);
	
	int insert(FinSellerInfoDetailEntity finSellerInfoDetailEntity);
	
	int update(FinSellerInfoDetailEntity finSellerInfoDetailEntity);
	
	int remove(String id);
	
	FinSellerInfoDetailEntity getById(String id);
	
	/**
	 * @param sellerId 上级分销商ID
	 * @param sellerNextId 下级分销商ID
	 * @return 直属下级分销商给上级分销商带来的佣金总收益
	 */
	public List<FinSellerInfoDetailEntity> getCommissionTotalAmountById(FinSellerInfoDetailEntity finSellerInfoDetailEntity);
	
	/**
	 * 查询分销商交易明细列表
	 * @param finSellerInfoDetailEntity
	 * @return
	 */
	List<FinSellerInfoDetailEntity> querySellerDetailList(FinSellerInfoDetailEntity finSellerInfoDetailEntity);
	
	/**
	 * 查询分销商交易列表-总记录数
	 * @param finSellerInfoDetailEntity
	 * @return
	 */
	int querySellerDetailListCount(FinSellerInfoDetailEntity finSellerInfoDetailEntity);
	
}
