 /*
 * 版本信息
 
 * 日期 2016-03-28 17:15:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.background.IFinAlreadyIncomeBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinAlreadyIncomeInputDto;
import com.yougou.wfx.finance.dto.input.FinAlreadyIncomePageInputDto;
import com.yougou.wfx.finance.dto.output.FinAlreadyIncomeOutputDto;
import com.yougou.wfx.finance.model.FinAlreadyIncomeEntity;
import com.yougou.wfx.finance.service.IFinAlreadyIncomeService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IFinAlreadyIncomeBackgroundApi实现
 * @author he.xx
 * @Date 创建时间：2016-03-28 17:15:58
 */
@Service
public class FinAlreadyIncomeBackgroundApiImpl implements IFinAlreadyIncomeBackgroundApi{
	
	@Resource
	private IFinAlreadyIncomeService finAlreadyIncomeService;
	
	@Override
	public String insert(FinAlreadyIncomeInputDto finAlreadyIncomeDto) {
		return finAlreadyIncomeService.insert(this.dtoToEntity(finAlreadyIncomeDto));
	}

	@Override
	public PageModel<FinAlreadyIncomeOutputDto> findPage(FinAlreadyIncomePageInputDto pageInputDto,PageModel<FinAlreadyIncomeOutputDto> pageModel) {
		FinAlreadyIncomeEntity finAlreadyIncomeEntity = (FinAlreadyIncomeEntity) BeanUtil.convertBean(pageInputDto,FinAlreadyIncomeEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finAlreadyIncomeService.findPageCount(finAlreadyIncomeEntity);
		List<FinAlreadyIncomeEntity> lstFinAlreadyIncomes = finAlreadyIncomeService.findPage(finAlreadyIncomeEntity, rowBounds);
		
		return new PageModel<FinAlreadyIncomeOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinAlreadyIncomeOutputDto>) BeanUtil.convertBeanList(lstFinAlreadyIncomes,FinAlreadyIncomeOutputDto.class));
	}

	@Override
	public int update(FinAlreadyIncomeInputDto finAlreadyIncomeDto) {
		return finAlreadyIncomeService.update(this.dtoToEntity(finAlreadyIncomeDto));
	}

	@Override
	public FinAlreadyIncomeOutputDto getById(String id) {
		FinAlreadyIncomeEntity finAlreadyIncomeEntity = finAlreadyIncomeService.getById(id);
		return this.entityToDto(finAlreadyIncomeEntity);
	}
	
	private FinAlreadyIncomeEntity dtoToEntity(Object obj){
		return (FinAlreadyIncomeEntity) BeanUtil.convertBean(obj,FinAlreadyIncomeEntity.class);
	}
	
	private FinAlreadyIncomeOutputDto entityToDto(Object obj){
		return (FinAlreadyIncomeOutputDto) BeanUtil.convertBean(obj,FinAlreadyIncomeOutputDto.class);
	}

	@Override
	public List<FinAlreadyIncomeOutputDto> queryDataList(FinAlreadyIncomeInputDto finAlreadyIncome) {
		List<FinAlreadyIncomeEntity> lstFinAlreadyIncomes = this.finAlreadyIncomeService.queryDataList(this.dtoToEntity(finAlreadyIncome));
		return (List<FinAlreadyIncomeOutputDto>) BeanUtil.convertBeanList(lstFinAlreadyIncomes,FinAlreadyIncomeOutputDto.class);
	}

	@Override
	public int queryDataListCount(FinAlreadyIncomeInputDto finAlreadyIncome) {
		return this.finAlreadyIncomeService.queryDataListCount(this.dtoToEntity(finAlreadyIncome));
	}

	@Override
	public FinAlreadyIncomeOutputDto queryAlreadyIncome(FinAlreadyIncomeInputDto finAlreadyIncome) {
		return this.entityToDto(this.finAlreadyIncomeService.queryAlreadyIncome(this.dtoToEntity(finAlreadyIncome)));
	}
	
}
