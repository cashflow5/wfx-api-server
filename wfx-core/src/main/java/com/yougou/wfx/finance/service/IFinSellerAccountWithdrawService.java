 /*
 * 版本信息
 * 日期 2016-03-31 10:10:25
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity;

/**
 * IFinSellerAccountWithdrawService接口
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
public interface IFinSellerAccountWithdrawService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);

	/**
	 * 获取分页数据
	 */
	public List<FinSellerAccountWithdrawEntity> findPage(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity,RowBounds rowBounds);
	
	/**
	 * 创建提现申请单接口.
	 * 保存单条记录
	 */
	public MessageOutputDto createApplyBill(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 提现申请单审核不通过.
	 * @param dto
	 * @return
	 */
	public MessageOutputDto refusedModifyApplyBill(final FinSellerAccountWithdrawEntity dto);
	
	/**
	 * 提现申请单，审核通过
	 * @param finSellerAccountWithdraw
	 * @return
	 */
	public MessageOutputDto auditApplyBill(FinSellerAccountWithdrawEntity finSellerAccountWithdraw);
	
	/**
	 * 提现申请单，审核拒绝
	 * @param finSellerAccountWithdrawDto
	 * @return
	 */
	public MessageOutputDto auditRefuseApplyBill(FinSellerAccountWithdrawEntity finSellerAccountWithdraw);
	
	/**
	 * 提现申请单，审核确认
	 * @date 2016年7月11日
	 * @param finSellerAccountWithdrawDto
	 * @return
	 */
	public MessageOutputDto auditConfirmApplyBill(FinSellerAccountWithdrawEntity finSellerAccountWithdraw);
	
	/**
	 * 提现申请单，确认付款
	 * @param finSellerAccountWithdraw
	 * @return
	 */
	public MessageOutputDto modifyApplyBill(FinSellerAccountWithdrawEntity finSellerAccountWithdraw);
	
	/**
	 * 更新记录
	 */
	public int update(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public FinSellerAccountWithdrawEntity getById(String id); 
	
	/**
	 * 【提现】查询提现详情接口(通过提现申请单号,查询提现申请单详情接口).
	 * 查询提现申请单详情(接口调用)
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public FinSellerAccountWithdrawEntity queryDetailsWithdraw(final FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
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
	 * 获取总条数
	 */
	public int queryPageListCount(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);

	/**
	 * 获取分页数据
	 */
	public List<FinSellerAccountWithdrawEntity> queryPageList(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity,RowBounds rowBounds);
	
	/**
	 * 根据状态（提现中，已提现，提现失败）查询总金额，前端查询
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public Double queryTotalAmountByStatus(FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity);
	
	/**
	 * 申请提现验证接口
	 * @date 2016年6月27日
	 * @param sellerId
	 * @return
	 */
	public MessageOutputDto applyBillValidation(String sellerId);
	
}