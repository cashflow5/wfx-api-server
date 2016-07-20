 /*
 * 版本信息
 * 日期 2016-03-29 14:08:45
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinSellerInfoDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoDetailOutputDto;

/**
 * 账户余额明细前置接口类.
 * IFinSellerInfoDetailBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-29 14:08:45
 */
public interface IFinSellerInfoDetailFrontApi{
	
	/**
	 * 【帐户余额】查询收支明细列表接口(带分页)
	 * @param pageInputDto 按照分销商SELLERID 为维度.
	 * @param pageModel
	 * @return
	 */
	public PageModel<FinSellerInfoDetailOutputDto> querySellerDetails(FinSellerInfoDetailPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 【帐户余额】查询收支明细详单接口
	 * @param id  明细行ID.
	 * @return
	 */
	public FinSellerInfoDetailOutputDto getById(String id);
}

