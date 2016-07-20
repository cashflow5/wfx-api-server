 /*
 * 版本信息
 
 * 日期 2016-03-28 10:58:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerInfoPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoOutputDto;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;

/**
 * IFinSellerInfoBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-28 10:58:34
 */
public interface IFinSellerInfoFrontApi{
	
	/**
	 * 创建分销商账户信息.
	 * @param finSellerInfoDto
	 * @return
	 */
	public MessageOutputDto createSeller(FinSellerInfoInputDto finSellerInfoDto);
	
	/**
	 * 更新分销商信息
	 * @param finSellerInfoDto
	 * @return
	 */
	public WFXResult<Boolean> updateSeller(FinSellerInfoInputDto finSellerInfoDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<FinSellerInfoOutputDto> findPage(FinSellerInfoPageInputDto pageInputDto,PageModel pageModel);
		
	/**
	 * 通过上级分销商ID，查询相应分销商信息（账户余额，提现中金额，已提现金额）
	 * @param sellerId 上级分销商ID
	 * @return
	 */
	public FinSellerInfoOutputDto getById(String sellerId);
	
	/**
	 * 查询下级分销商详情(包括下级分销商的收益情况)(接口:查询下级分销商带来收益接口)
	 * @param subSellers 封装下级分销商ID
	 * @return
	 */
	public List<FinSellerInfoOutputDto> querySubSellers(final List<String> subSellers);
	
	/**
	 * @param sellerId 上级分销商ID
	 * @param sellerNextId 下级分销商ID
	 * @return 直属下级分销商给上级分销商带来的佣金总收益
	 */
	public Double getCommissionTotalAmountById(String sellerId, String sellerNextId);
	
	/**
	 * 累计收益金额：获取已结算（不包含未结算）的佣金收益总额
	 * @param sellerId 分销商ID
	 * @return
	 */
	public Double getTotalCommissionAmount(String sellerId);
	
	/**
	 * 今日收益：当天财务系统生成该分销商的佣金收益总额（正，负佣金之和）
	 * @param sellerId 分销商ID
	 * @return
	 */
	public Double getTodayCommissionAmount(String sellerId);
	
}

