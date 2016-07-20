 /*
 * 版本信息
 
 * 日期 2016-03-28 10:58:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.Map;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerInfoPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoOutputDto;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;

/**
 * IFinSellerInfoBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-28 10:58:34
 */
public interface IFinSellerInfoBackgroundApi{
	
	/**
	 * 创建分销商账户信息.
	 * 保存单条记录
	 */
	public MessageOutputDto createSeller(FinSellerInfoInputDto finSellerInfoDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<FinSellerInfoOutputDto> findPage(FinSellerInfoPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(FinSellerInfoInputDto finSellerInfoDto);
	
	/**
	 * 根据ID查询相应的分销商信息.
	 * 通过id查询数据
	 */
	public FinSellerInfoOutputDto getById(String id);
	
	/**
	 * 根据条件查询所有分销商的资金汇总（账户余额，佣金收益，已提现金额，提现中金额）
	 * @date 2016年7月12日
	 * @param finSellerInfoInputDto
	 * @return
	 */
	public Map<String, Object> queryAccountSummary(FinSellerInfoInputDto finSellerInfoInputDto);
	
}

