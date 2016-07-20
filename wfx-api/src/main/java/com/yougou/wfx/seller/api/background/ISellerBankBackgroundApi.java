 /*
 * 版本信息
 
 * 日期 2016-03-25 14:23:06
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.seller.dto.input.SellerBankInputDto;
import com.yougou.wfx.seller.dto.input.SellerBankPageInputDto;
import com.yougou.wfx.seller.dto.output.SellerBankOutputDto;

/**
 * ISellerBankBackgroundApi
 * @author luoq
 * @Date 创建时间：2016-03-25 14:23:06
 */
public interface ISellerBankBackgroundApi{
	
	/**
	 * 通过分销商ID查询数据
	 */
	public List<SellerBankOutputDto> getSellerBanks(String sellerId); 
}

