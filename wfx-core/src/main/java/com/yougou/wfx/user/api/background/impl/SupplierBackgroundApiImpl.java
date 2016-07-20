 /*
 * 版本信息
 
 * 日期 2016-04-08 13:32:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.user.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.user.api.background.ISupplierBackgroundApi;
import com.yougou.wfx.user.dto.input.SupplierInputDto;
import com.yougou.wfx.user.dto.input.SupplierPageInputDto;
import com.yougou.wfx.user.dto.output.SupplierOutputDto;
import com.yougou.wfx.user.model.SupplierEntity;
import com.yougou.wfx.user.service.ISupplierService;

/**
 * ISupplierBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-08 13:32:03
 */
@Service
public class SupplierBackgroundApiImpl implements ISupplierBackgroundApi{
	
	@Resource
	ISupplierService supplierService;
	
	@Override
	public int removeById(String id) {
		return supplierService.remove(id);
	}

	@Override
	public String insert(SupplierInputDto supplierDto) {
		return supplierService.insert(this.dtoToEntity(supplierDto));
	}

	@Override
	public PageModel<SupplierOutputDto> findPage(SupplierPageInputDto pageInputDto,PageModel pageModel) {
		SupplierEntity supplierEntity = (SupplierEntity) BeanUtil.convertBean(pageInputDto,SupplierEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = supplierService.findPageCount(supplierEntity);
		List<SupplierEntity> lstSuppliers = supplierService.findPage(supplierEntity, rowBounds);

		return new PageModel<SupplierOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<SupplierOutputDto>) BeanUtil.convertBeanList(lstSuppliers,SupplierOutputDto.class));
	}

	@Override
	public int update(SupplierInputDto supplierDto) {
		return supplierService.update(this.dtoToEntity(supplierDto));
	}

	@Override
	public SupplierOutputDto getById(String id) {
		SupplierEntity supplierEntity = supplierService.getById(id);
		return this.entityToDto(supplierEntity);
	}
	
	private SupplierEntity dtoToEntity(Object obj){
		return (SupplierEntity) BeanUtil.convertBean(obj,SupplierEntity.class);
	}
	
	private SupplierOutputDto entityToDto(Object obj){
		return (SupplierOutputDto) BeanUtil.convertBean(obj,SupplierOutputDto.class);
	}
}
