 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.dto.output.FinRefundSynRes;
import com.yougou.wfx.finance.model.FinReturnDebtEntity;

/**
 * IFinReturnDebtService接口
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
public interface IFinReturnDebtService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(FinReturnDebtEntity finReturnDebtEntity);

	/**
	 * 获取分页数据
	 */
	public List<FinReturnDebtEntity> findPage(FinReturnDebtEntity finReturnDebtEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 更新记录
	 */
	public int update(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public FinReturnDebtEntity getById(String id); 
	
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
	public List<FinReturnDebtEntity> queryDataList(FinReturnDebtEntity finReturnDebtEntity);
	
	public int queryDataListCount(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 售后同意退款，财务创建或修改退款单
	 * @param finReturnDebt
	 * @return
	 */
	public FinRefundSynRes applyAndModifyRefund(FinReturnDebtEntity finReturnDebtEntity);
	
	/**
	 * 通过订单号查询已退款的金额
	 * @date 2016年6月24日
	 * @param orderNo
	 * @return
	 */
	public Double queryRefundedAmountByOrderNo(String orderNo);
	
}