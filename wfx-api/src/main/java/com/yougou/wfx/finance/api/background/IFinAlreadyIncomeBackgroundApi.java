 /*
 * 版本信息
 
 * 日期 2016-03-28 17:15:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinAlreadyIncomeInputDto;
import com.yougou.wfx.finance.dto.input.FinAlreadyIncomePageInputDto;
import com.yougou.wfx.finance.dto.output.FinAlreadyIncomeOutputDto;

/**
 * IFinAlreadyIncomeBackgroundApi
 * @author he.xx
 * @Date 创建时间：2016-03-28 17:15:58
 */
public interface IFinAlreadyIncomeBackgroundApi{
	
	/**
	 * 保存单条记录
	 */
	public String insert(FinAlreadyIncomeInputDto finAlreadyIncomeDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<FinAlreadyIncomeOutputDto> findPage(FinAlreadyIncomePageInputDto pageInputDto,PageModel<FinAlreadyIncomeOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(FinAlreadyIncomeInputDto finAlreadyIncomeDto);
	
	/**
	 * 通过id查询数据
	 */
	public FinAlreadyIncomeOutputDto getById(String id);
	
	/**
	 * 查询收款数据列表
	 * @param finAlreadyIncome
	 * @return
	 */
	public List<FinAlreadyIncomeOutputDto> queryDataList(FinAlreadyIncomeInputDto finAlreadyIncome);
	
	public int queryDataListCount(FinAlreadyIncomeInputDto finAlreadyIncome);
	
	/**
	 * 查询收款信息
	 * @param finAlreadyIncomeEntity
	 * @return
	 */
	public FinAlreadyIncomeOutputDto queryAlreadyIncome(FinAlreadyIncomeInputDto finAlreadyIncome);
	
}

