 /*
 * 版本信息
 
 * 日期 2016-03-31 10:10:25
 
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
import com.yougou.wfx.finance.api.background.IFinSellerAccountWithdrawBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerAccountWithdrawOutputDto;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.model.FinSellerAccountWithdrawEntity;
import com.yougou.wfx.finance.service.IFinSellerAccountWithdrawService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IFinSellerAccountWithdrawBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-03-31 10:10:25
 */
@Service
public class FinSellerAccountWithdrawBackgroundApiImpl implements IFinSellerAccountWithdrawBackgroundApi{
	
	@Resource
	private IFinSellerAccountWithdrawService finSellerAccountWithdrawService;
	
	@Override
	public PageModel<FinSellerAccountWithdrawOutputDto> findPage(FinSellerAccountWithdrawPageInputDto pageInputDto,PageModel pageModel) {
		FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity = (FinSellerAccountWithdrawEntity) BeanUtil.convertBean(pageInputDto,FinSellerAccountWithdrawEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finSellerAccountWithdrawService.findPageCount(finSellerAccountWithdrawEntity);
		List<FinSellerAccountWithdrawEntity> lstFinSellerAccountWithdraws = finSellerAccountWithdrawService.findPage(finSellerAccountWithdrawEntity, rowBounds);

		return new PageModel<FinSellerAccountWithdrawOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinSellerAccountWithdrawOutputDto>) BeanUtil.convertBeanList(lstFinSellerAccountWithdraws,FinSellerAccountWithdrawOutputDto.class));
	}

	@Override
	public int update(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		return finSellerAccountWithdrawService.update(this.dtoToEntity(finSellerAccountWithdrawDto));
	}

	@Override
	public FinSellerAccountWithdrawOutputDto getById(String id) {
		FinSellerAccountWithdrawEntity finSellerAccountWithdrawEntity = finSellerAccountWithdrawService.getById(id);
		return this.entityToDto(finSellerAccountWithdrawEntity);
	}
	
	private FinSellerAccountWithdrawEntity dtoToEntity(Object obj){
		return (FinSellerAccountWithdrawEntity) BeanUtil.convertBean(obj,FinSellerAccountWithdrawEntity.class);
	}
	
	private FinSellerAccountWithdrawOutputDto entityToDto(Object obj){
		return (FinSellerAccountWithdrawOutputDto) BeanUtil.convertBean(obj,FinSellerAccountWithdrawOutputDto.class);
	}

	/**
	 * @see com.yougou.wfx.finance.api.background.IFinSellerAccountWithdrawBackgroundApi#refusedApplyBill(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	@Deprecated
	public MessageOutputDto refusedModifyApplyBill(final FinSellerAccountWithdrawInputDto dto) {
		return finSellerAccountWithdrawService.refusedModifyApplyBill(this.dtoToEntity(dto));
	}

	/**
	 * @see com.yougou.wfx.finance.api.background.IFinSellerAccountWithdrawBackgroundApi#modifyApplyBill(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	public MessageOutputDto modifyApplyBill(final FinSellerAccountWithdrawInputDto dto) {
		return finSellerAccountWithdrawService.modifyApplyBill(this.dtoToEntity(dto));
	}

	/**
	 * @see com.yougou.wfx.finance.api.background.IFinSellerAccountWithdrawBackgroundApi#auditApplyBill(com.yougou.wfx.finance.dto.input.FinSellerAccountWithdrawInputDto)
	 */
	@Override
	public MessageOutputDto auditApplyBill(final FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		return finSellerAccountWithdrawService.auditApplyBill(this.dtoToEntity(finSellerAccountWithdrawDto));
	}
	
	@Override
	public MessageOutputDto auditRefuseApplyBill(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		return this.finSellerAccountWithdrawService.auditRefuseApplyBill(this.dtoToEntity(finSellerAccountWithdrawDto));
	}

	@Override
	public List<FinSellerAccountWithdrawOutputDto> queryWithdrawalList(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		List<FinSellerAccountWithdrawEntity> accountWidthdrawalList = this.finSellerAccountWithdrawService.queryWithdrawalList(this.dtoToEntity(finSellerAccountWithdrawDto));
		return (List<FinSellerAccountWithdrawOutputDto>) BeanUtil.convertBeanList(accountWidthdrawalList, FinSellerAccountWithdrawOutputDto.class);
	}

	@Override
	public int queryWithdrawalListCount(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		return this.finSellerAccountWithdrawService.queryWithdrawalListCount(this.dtoToEntity(finSellerAccountWithdrawDto));
	}

	@Override
	public MessageOutputDto auditConfirmApplyBill(FinSellerAccountWithdrawInputDto finSellerAccountWithdrawDto) {
		return finSellerAccountWithdrawService.auditConfirmApplyBill(dtoToEntity(finSellerAccountWithdrawDto));
	}
	
}
