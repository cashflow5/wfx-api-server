 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import com.yougou.wfx.finance.dto.input.FinReturnDebtInputDto;
import com.yougou.wfx.finance.dto.output.FinRefundSynRes;
import com.yougou.wfx.finance.dto.output.FinReturnDebtOutputDto;

/**
 * IFinRefundBackgroundApi
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
public interface IFinRefundBackgroundApi{
	
	/**
	 * 售后同意退款，财务创建或修改退款单
	 * @param finReturnDebt
	 * @return
	 */
	public FinRefundSynRes applyAndModifyRefund(FinReturnDebtInputDto finReturnDebt);
	
	/**
	 * 通过退款单号查询退款信息
	 * @param backNo 退款单编号
	 * @return
	 */
	public FinReturnDebtOutputDto getRefundInfo(String backNo);
	
}

