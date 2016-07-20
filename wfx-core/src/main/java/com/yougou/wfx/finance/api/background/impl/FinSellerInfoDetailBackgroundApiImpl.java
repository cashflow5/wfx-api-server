 /*
 * 版本信息
 
 * 日期 2016-03-29 14:08:45
 
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
import com.yougou.wfx.finance.api.background.IFinSellerInfoDetailBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoDetailInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerInfoDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoDetailOutputDto;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.service.IFinSellerInfoDetailService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IFinSellerInfoDetailBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-03-29 14:08:45
 */
@Service
public class FinSellerInfoDetailBackgroundApiImpl implements IFinSellerInfoDetailBackgroundApi{
	
	@Resource
	IFinSellerInfoDetailService finSellerInfoDetailService;
	
	@Override
	public int removeById(String id) {
		return finSellerInfoDetailService.remove(id);
	}

	@Override
	public String insert(FinSellerInfoDetailInputDto finSellerInfoDetailDto) {
		return finSellerInfoDetailService.insert(this.dtoToEntity(finSellerInfoDetailDto));
	}

	@Override
	public PageModel<FinSellerInfoDetailOutputDto> findPage(FinSellerInfoDetailPageInputDto pageInputDto,PageModel pageModel) {
		FinSellerInfoDetailEntity finSellerInfoDetailEntity = (FinSellerInfoDetailEntity) BeanUtil.convertBean(pageInputDto,FinSellerInfoDetailEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finSellerInfoDetailService.findPageCount(finSellerInfoDetailEntity);
		List<FinSellerInfoDetailEntity> lstFinSellerInfoDetails = finSellerInfoDetailService.findPage(finSellerInfoDetailEntity, rowBounds);

		return new PageModel<FinSellerInfoDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinSellerInfoDetailOutputDto>) BeanUtil.convertBeanList(lstFinSellerInfoDetails,FinSellerInfoDetailOutputDto.class));
	}

	@Override
	public int update(FinSellerInfoDetailInputDto finSellerInfoDetailDto) {
		return finSellerInfoDetailService.update(this.dtoToEntity(finSellerInfoDetailDto));
	}

	@Override
	public FinSellerInfoDetailOutputDto getById(String id) {
		FinSellerInfoDetailEntity finSellerInfoDetailEntity = finSellerInfoDetailService.getById(id);
		return this.entityToDto(finSellerInfoDetailEntity);
	}
	
	private FinSellerInfoDetailEntity dtoToEntity(Object obj){
		return (FinSellerInfoDetailEntity) BeanUtil.convertBean(obj,FinSellerInfoDetailEntity.class);
	}
	
	private FinSellerInfoDetailOutputDto entityToDto(Object obj){
		return (FinSellerInfoDetailOutputDto) BeanUtil.convertBean(obj,FinSellerInfoDetailOutputDto.class);
	}

	@Override
	public List<FinSellerInfoDetailOutputDto> querySellerDetailList(FinSellerInfoDetailInputDto finSellerInfoDetailDto) {
		List<FinSellerInfoDetailEntity> sellerDetailList = this.finSellerInfoDetailService.querySellerDetailList(this.dtoToEntity(finSellerInfoDetailDto));
		return (List<FinSellerInfoDetailOutputDto>) BeanUtil.convertBeanList(sellerDetailList, FinSellerInfoDetailOutputDto.class);
	}

	@Override
	public int querySellerDetailListCount(FinSellerInfoDetailInputDto finSellerInfoDetailDto) {
		return this.finSellerInfoDetailService.querySellerDetailListCount(this.dtoToEntity(finSellerInfoDetailDto));
	}
	
}
