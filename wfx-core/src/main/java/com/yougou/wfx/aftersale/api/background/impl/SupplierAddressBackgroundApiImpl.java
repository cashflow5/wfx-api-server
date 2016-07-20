 /*
 * 版本信息
 
 * 日期 2016-04-26 09:34:42
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.aftersale.api.background.ISupplierAddressBackgroundApi;
import com.yougou.wfx.aftersale.dto.input.SupplierAddressInputDto;
import com.yougou.wfx.aftersale.dto.input.SupplierAddressPageInputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.aftersale.model.SupplierAddressEntity;
import com.yougou.wfx.aftersale.service.ISupplierAddressService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ISupplierAddressBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-26 09:34:43
 */
@Service
public class SupplierAddressBackgroundApiImpl implements ISupplierAddressBackgroundApi{
	
	@Resource
	ISupplierAddressService supplierAddressService;
	
	@Override
	@LoggerProfile(methodNote = "通过id删除供应商地址记录")
	public int removeById(String id) {
		return supplierAddressService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote = "保存单条供应商地址记录")
	public String insert(SupplierAddressInputDto supplierAddressDto) {
		return supplierAddressService.insert(this.dtoToEntity(supplierAddressDto));
	}

	@Override
	@LoggerProfile(methodNote = "获取分页供应商地址数据")
	public PageModel<SupplierAddressOutputDto> findPage(SupplierAddressPageInputDto pageInputDto,PageModel pageModel) {
		SupplierAddressEntity supplierAddressEntity = (SupplierAddressEntity) BeanUtil.convertBean(pageInputDto,SupplierAddressEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = supplierAddressService.findPageCount(supplierAddressEntity);
		List<SupplierAddressEntity> lstSupplierAddresss = supplierAddressService.findPage(supplierAddressEntity, rowBounds);

		return new PageModel<SupplierAddressOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<SupplierAddressOutputDto>) BeanUtil.convertBeanList(lstSupplierAddresss,SupplierAddressOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote = "更新供应商地址记录")
	public int update(SupplierAddressInputDto supplierAddressDto) {
		return supplierAddressService.update(this.dtoToEntity(supplierAddressDto));
	}

	@Override
	@LoggerProfile(methodNote = "通过id查询供应商地址数据")
	public SupplierAddressOutputDto getById(String id) {
		SupplierAddressEntity supplierAddressEntity = supplierAddressService.getById(id);
		return this.entityToDto(supplierAddressEntity);
	}
	
	private SupplierAddressEntity dtoToEntity(Object obj){
		return (SupplierAddressEntity) BeanUtil.convertBean(obj,SupplierAddressEntity.class);
	}
	
	private SupplierAddressOutputDto entityToDto(Object obj){
		return (SupplierAddressOutputDto) BeanUtil.convertBean(obj,SupplierAddressOutputDto.class);
	}
}
