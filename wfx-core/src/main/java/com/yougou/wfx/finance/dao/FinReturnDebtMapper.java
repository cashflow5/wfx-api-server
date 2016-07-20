 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinReturnDebtEntity;

/**
 * FinReturnDebtMapper
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
public interface FinReturnDebtMapper{
	
	int findPageCount(FinReturnDebtEntity finReturnDebtEntity);

	List<FinReturnDebtEntity> findPage(FinReturnDebtEntity finReturnDebtEntity,RowBounds rowBounds);
	
	void insert(FinReturnDebtEntity finReturnDebtEntity);
	
	int update(FinReturnDebtEntity finReturnDebtEntity);
	
	int remove(String id);
	
	FinReturnDebtEntity getById(String id);
	
	/**
	 * 通过退款单号查询退款信息
	 * @param refundNo
	 * @return
	 */
	FinReturnDebtEntity getReturnDebtByBackNo(String backNo);
	
	/**
	 * 查询退款单详情
	 * @param finReturnDebtEntity
	 * @return
	 */
	FinReturnDebtEntity getReturnDebtByQueryVo(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 查询退款数据列表
	 * @param finReturnDebt
	 * @return
	 */
	List<FinReturnDebtEntity> queryDataList(FinReturnDebtEntity finReturnDebtEntity);
	
	int queryDataListCount(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 通过退款申请单号更新退款单
	 * @param finReturnDebtEntity
	 * @return
	 */
	int updateDataByBackNo(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 通过订单号查询已退款的金额
	 * @date 2016年6月24日
	 * @param orderNo
	 * @return
	 */
	public Double queryRefundedAmountByOrderNo(String orderNo);
	
}
