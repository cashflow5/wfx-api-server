 /*
 * 版本信息
 
 * 日期 2016-03-29 14:10:40
 
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
import com.yougou.wfx.finance.api.background.IFinReturnDebtBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinReturnDebtInputDto;
import com.yougou.wfx.finance.dto.input.FinReturnDebtPageInputDto;
import com.yougou.wfx.finance.dto.output.FinReturnDebtDetailOutputDto;
import com.yougou.wfx.finance.dto.output.FinReturnDebtOutputDto;
import com.yougou.wfx.finance.model.FinReturnDebtEntity;
import com.yougou.wfx.finance.service.IFinReturnDebtService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IFinReturnDebtBackgroundApi实现
 * @author he.xx
 * @Date 创建时间：2016-03-29 14:10:41
 */
@Service
public class FinReturnDebtBackgroundApiImpl implements IFinReturnDebtBackgroundApi{
	
	@Resource
	IFinReturnDebtService finReturnDebtService;
	
	@Override
	public int removeById(String id) {
		return finReturnDebtService.remove(id);
	}

	@Override
	public String insert(FinReturnDebtInputDto finReturnDebtDto) {
		return finReturnDebtService.insert(this.dtoToEntity(finReturnDebtDto));
	}

	/**
	 * 获取数据分页
	 */
	@Override
	public PageModel<FinReturnDebtOutputDto> findPage(FinReturnDebtPageInputDto pageInputDto,PageModel<FinReturnDebtOutputDto> pageModel) {
		FinReturnDebtEntity finReturnDebtEntity = (FinReturnDebtEntity) BeanUtil.convertBean(pageInputDto,FinReturnDebtEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finReturnDebtService.findPageCount(finReturnDebtEntity);
		List<FinReturnDebtEntity> lstFinReturnDebts = finReturnDebtService.findPage(finReturnDebtEntity, rowBounds);

		return new PageModel<FinReturnDebtOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinReturnDebtOutputDto>) BeanUtil.convertBeanList(lstFinReturnDebts,FinReturnDebtOutputDto.class));
	}

	/**
	 * 获取数据分页，封装商品信息
	 */
	@Override
	public PageModel<FinReturnDebtDetailOutputDto> findDataPage(FinReturnDebtPageInputDto pageInputDto, PageModel<FinReturnDebtDetailOutputDto> pageModel) {
		FinReturnDebtEntity finReturnDebtEntity = (FinReturnDebtEntity) BeanUtil.convertBean(pageInputDto,FinReturnDebtEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finReturnDebtService.findPageCount(finReturnDebtEntity);
		List<FinReturnDebtEntity> lstFinReturnDebts = finReturnDebtService.findPage(finReturnDebtEntity, rowBounds);

		List<FinReturnDebtDetailOutputDto> returnDetbList = (List<FinReturnDebtDetailOutputDto>) BeanUtil.convertBeanList(lstFinReturnDebts,FinReturnDebtDetailOutputDto.class);
		
		return new PageModel<FinReturnDebtDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,returnDetbList);
	}
	
	@Override
	public int update(FinReturnDebtInputDto finReturnDebtDto) {
		return finReturnDebtService.update(this.dtoToEntity(finReturnDebtDto));
	}

	@Override
	public FinReturnDebtOutputDto getById(String id) {
		FinReturnDebtEntity finReturnDebtEntity = finReturnDebtService.getById(id);
		return this.entityToDto(finReturnDebtEntity);
	}
	
	private FinReturnDebtEntity dtoToEntity(Object obj){
		return (FinReturnDebtEntity) BeanUtil.convertBean(obj,FinReturnDebtEntity.class);
	}
	
	private FinReturnDebtOutputDto entityToDto(Object obj){
		return (FinReturnDebtOutputDto) BeanUtil.convertBean(obj,FinReturnDebtOutputDto.class);
	}

	@Override
	public List<FinReturnDebtOutputDto> queryDataList(FinReturnDebtInputDto finReturnDebt) {
		List<FinReturnDebtEntity> lstFinReturnDebts = this.finReturnDebtService.queryDataList(this.dtoToEntity(finReturnDebt));
		return (List<FinReturnDebtOutputDto>) BeanUtil.convertBeanList(lstFinReturnDebts, FinReturnDebtOutputDto.class);
	}

	@Override
	public int queryDataListCount(FinReturnDebtInputDto finReturnDebt) {
		return this.finReturnDebtService.queryDataListCount(this.dtoToEntity(finReturnDebt));
	}

	@Override
	public FinReturnDebtOutputDto getReturnDebtByBackNo(String backNo) {
		return this.entityToDto(this.finReturnDebtService.getReturnDebtByBackNo(backNo));
	}

	@Override
	public FinReturnDebtOutputDto getReturnDebtByQueryVo(FinReturnDebtInputDto finReturnDebt) {
		return this.entityToDto(this.finReturnDebtService.getReturnDebtByQueryVo(this.dtoToEntity(finReturnDebt)));
	}

	@Override
	public Double queryRefundedAmountByOrderNo(String orderNo) {
		return this.finReturnDebtService.queryRefundedAmountByOrderNo(orderNo);
	}
	
}
