 /*
 * 版本信息
 
 * 日期 2016-03-31 10:10:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerAccountWithdrawOutputDto;
import com.yougou.wfx.finance.dto.output.FinSellerAccountWithdrawVo;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;

/**
 * IFinSellerAccountWithdrawBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
public interface IFinSellerAccountWithdrawFrontApi{
	
	/**
	 * 申请提现申请单.
	 * 保存单条记录
	 */
	public MessageOutputDto createApplyBill(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 查询提现申请单详情，前端app使用
	 * @param finSellerAccountWithdrawDto (参数：主键ID或提现申请单号)
	 * @return
	 */
	public FinSellerAccountWithdrawOutputDto queryDetailsWithdraw(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto);
	
	/**
	 * 查询提现详情接口，前端H5调用
	 * @date 2016年6月24日
	 * @param id （参数：主键id）
	 * @return
	 */
	public FinSellerAccountWithdrawOutputDto queryDetailById(String id);
	
	/**
	 * 查询提现记录列表接口
	 * @param pageInputDto（参数：分销商ID，状态statusFlag）
	 * @param pageModel
	 * @return
	 */
	public FinSellerAccountWithdrawVo queryPageList(FinSellerAccountWithdrawPageInputDto pageInputDto, PageModel pageModel);
	
	/**
	 * 申请提现验证接口（1：一周（自然周）提现一次）
	 * @date 2016年6月27日
	 * @param sellerId
	 * @return
	 */
	public MessageOutputDto applyBillValidation(String sellerId); 
	
}
