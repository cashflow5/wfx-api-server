 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinReturnDebtInputDto;
import com.yougou.wfx.finance.dto.input.FinReturnDebtPageInputDto;
import com.yougou.wfx.finance.dto.output.FinReturnDebtDetailOutputDto;
import com.yougou.wfx.finance.dto.output.FinReturnDebtOutputDto;

/**
 * IFinReturnDebtBackgroundApi
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
public interface IFinReturnDebtBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(FinReturnDebtInputDto finReturnDebtDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<FinReturnDebtOutputDto> findPage(FinReturnDebtPageInputDto pageInputDto,PageModel<FinReturnDebtOutputDto> pageModel);
	
	/**
	 * 获取分页数据，封装商品信息
	 */
	public PageModel<FinReturnDebtDetailOutputDto> findDataPage(FinReturnDebtPageInputDto pageInputDto,PageModel<FinReturnDebtDetailOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(FinReturnDebtInputDto finReturnDebtDto);
	
	/**
	 * 通过id查询数据
	 */
	public FinReturnDebtOutputDto getById(String id);
	
	/**
	 * 通过退款单号查询退款信息
	 * @param refundNo
	 * @return
	 */
	FinReturnDebtOutputDto getReturnDebtByBackNo(String backNo);
	
	/**
	 * 查询退款单详情
	 * @param finReturnDebtEntity
	 * @return
	 */
	FinReturnDebtOutputDto getReturnDebtByQueryVo(FinReturnDebtInputDto finReturnDebt);
	
	/**
	 * 查询退款数据列表
	 * @param finReturnDebt
	 * @return
	 */
	public List<FinReturnDebtOutputDto> queryDataList(FinReturnDebtInputDto finReturnDebt);
	
	public int queryDataListCount(FinReturnDebtInputDto finReturnDebt);
	
	/**
	 * 通过订单号查询已退款的金额
	 * @date 2016年6月24日
	 * @param orderNo
	 * @return
	 */
	public Double queryRefundedAmountByOrderNo(String orderNo);
	
}

