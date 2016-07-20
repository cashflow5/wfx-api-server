 /*
 * 版本信息
 
 * 日期 2016-03-31 10:10:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity;

/**
 * FinSellerAccountWithdrawMapper
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
public interface FinSellerAccountWithdrawMapper{
	
	int findPageCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);

	List<FinSellerAccountWithdrawEntity> findPage(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity,RowBounds rowBounds);
	
	int insert(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	int update(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 更新基本信息
	 * @date 2016年7月11日
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	int updateBaseInfo(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	int remove(String id);
	
	FinSellerAccountWithdrawEntity getById(String id);
	
	FinSellerAccountWithdrawEntity queryDetailsWithdraw(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 查询提现记录列表
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public List<FinSellerAccountWithdrawEntity> queryWithdrawalList(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 查询提现记录列表-总记录数
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public int queryWithdrawalListCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 获取总条数,前端查询
	 */
	public int queryPageListCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);

	/**
	 * 获取分页数据，前端查询
	 */
	public List<FinSellerAccountWithdrawEntity> queryPageList(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity,RowBounds rowBounds);
	
	/**
	 * 获取所有数据，前端查询
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public List<FinSellerAccountWithdrawEntity> queryDataList(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 获取分销商月提现总额
	 * @param sellerId
	 * @return
	 */
	Map<String, Object> getCurrMonthWithdrawAmount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 查询一周内的提现次数
	 * @date 2016年6月24日
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	int queryWithdrawNumInWeek(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
}
