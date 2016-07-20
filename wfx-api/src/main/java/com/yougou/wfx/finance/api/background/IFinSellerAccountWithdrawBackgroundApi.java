 /*
 * 版本信息
 * 日期 2016-03-31 10:10:25
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerAccountWithdrawOutputDto;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;

/**
 * IFinSellerAccountWithdrawBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
public interface IFinSellerAccountWithdrawBackgroundApi{
	
	/**
	 * 获取分页数据
	 */
	public PageModel<FinSellerAccountWithdrawOutputDto> findPage(FinSellerAccountWithdrawPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 提现申请单审核拒绝.
	 * @param finSellerAccountWithdrawDto
	 * @return
	 */
	public MessageOutputDto refusedModifyApplyBill(final FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 提现申请单，审核通过
	 * @param finSellerAccountWithdrawDto
	 * @return
	 */
	public MessageOutputDto auditApplyBill(final FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 提现申请单，审核拒绝
	 * @param finSellerAccountWithdrawDto
	 * @return
	 */
	public MessageOutputDto auditRefuseApplyBill(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 提现申请单，审核确认
	 * @date 2016年7月11日
	 * @param finSellerAccountWithdrawDto
	 * @return
	 */
	public MessageOutputDto auditConfirmApplyBill(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 提现申请单付款. 
	 * @param finSellerAccountWithdraw
	 * @return
	 */
	public MessageOutputDto modifyApplyBill(FinSellerAccountWithdrawInputDto finSellerAccountWithdraw);
	
	/**
	 * 通过id查询数据
	 */
	public FinSellerAccountWithdrawOutputDto getById(String id);
	
	/**
	 * 查询提现记录列表
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public List<FinSellerAccountWithdrawOutputDto> queryWithdrawalList(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 查询提现记录列表-总记录数
	 * @param finSellerAccountWithdrawEntity
	 * @return
	 */
	public int queryWithdrawalListCount(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
}

