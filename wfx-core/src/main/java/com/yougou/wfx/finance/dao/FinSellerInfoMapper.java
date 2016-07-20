 /*
 * 版本信息
 
 * 日期 2016-03-28 10:58:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinSellerInfoEntity;

/**
 * FinSellerInfoMapper
 * @author wfx
 * @Date 创建时间：2016-03-28 10:58:34
 */
public interface FinSellerInfoMapper{
	
	int findPageCount(FinSellerInfoEntity finSellerInfoEntity);

	List<FinSellerInfoEntity> findPage(FinSellerInfoEntity finSellerInfoEntity,RowBounds rowBounds);
	
	int insert(FinSellerInfoEntity finSellerInfoEntity);
	
	int update(FinSellerInfoEntity finSellerInfoEntity);
	
	int remove(String id);
	
	FinSellerInfoEntity getById(String id);
	
	/**
	 * 更新分销商信息
	 * @date 2016年7月6日
	 * @param finSellerInfoEntity
	 * @return
	 */
	public int updateSeller(FinSellerInfoEntity finSellerInfoEntity);
	
	/**
	 * 改变相应的金额.
	 * @param finSellerInfoEntity
	 * @return
	 */
	int changeSellerAmount(final FinSellerInfoEntity finSellerInfoEntity);
	
	/**
	 * 查询下级分销商详情(包括下级分销商的收益情况)(接口:查询下级分销商带来收益接口)
	 * @param subSellers
	 * @return
	 */
	List<FinSellerInfoEntity> querySubSellers(final List<String> subSellers);
	
	/**
	 * 根据条件查询所有分销商的资金汇总（账户余额，佣金收益，已提现金额，提现中金额）
	 * @date 2016年7月12日
	 * @param finSellerInfoEntity
	 * @return
	 */
	Map<String, Object> queryAccountSummary(FinSellerInfoEntity finSellerInfoEntity);
	
}
