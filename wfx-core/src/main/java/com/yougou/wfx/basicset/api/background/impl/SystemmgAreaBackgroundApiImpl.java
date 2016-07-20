 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.basicset.api.background.ISystemmgAreaBackgroundApi;
import com.yougou.wfx.basicset.dto.input.SystemmgAreaInputDto;
import com.yougou.wfx.basicset.dto.input.SystemmgAreaPageInputDto;
import com.yougou.wfx.basicset.dto.output.SystemmgAreaOutputDto;
import com.yougou.wfx.basicset.model.SystemmgAreaEntity;
import com.yougou.wfx.basicset.service.ISystemmgAreaService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ISystemmgAreaBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
@Service
public class SystemmgAreaBackgroundApiImpl implements ISystemmgAreaBackgroundApi{
	
	@Resource
	ISystemmgAreaService systemmgAreaService;
	
	@Override
	@LoggerProfile(methodNote="根据ID删除地区")
	public int removeById(String id) {
		return systemmgAreaService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="新增地区")
	public String insert(SystemmgAreaInputDto systemmgAreaDto) {
		return systemmgAreaService.insert(this.dtoToEntity(systemmgAreaDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询地区列表")
	public PageModel<SystemmgAreaOutputDto> findPage(SystemmgAreaPageInputDto pageInputDto,PageModel pageModel) {
		SystemmgAreaEntity systemmgAreaEntity = (SystemmgAreaEntity) BeanUtil.convertBean(pageInputDto,SystemmgAreaEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = systemmgAreaService.findPageCount(systemmgAreaEntity);
		List<SystemmgAreaEntity> lstSystemmgAreas = systemmgAreaService.findPage(systemmgAreaEntity, rowBounds);

		return new PageModel<SystemmgAreaOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<SystemmgAreaOutputDto>) BeanUtil.convertBeanList(lstSystemmgAreas,SystemmgAreaOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="更新地区信息")
	public int update(SystemmgAreaInputDto systemmgAreaDto) {
		return systemmgAreaService.update(this.dtoToEntity(systemmgAreaDto));
	}

	@Override
	@LoggerProfile(methodNote="根据Id查询地区")
	public SystemmgAreaOutputDto getById(String id) {
		SystemmgAreaEntity systemmgAreaEntity = systemmgAreaService.getById(id);
		return this.entityToDto(systemmgAreaEntity);
	}
	
	private SystemmgAreaEntity dtoToEntity(Object obj){
		return (SystemmgAreaEntity) BeanUtil.convertBean(obj,SystemmgAreaEntity.class);
	}
	
	private SystemmgAreaOutputDto entityToDto(Object obj){
		return (SystemmgAreaOutputDto) BeanUtil.convertBean(obj,SystemmgAreaOutputDto.class);
	}
	
	@Override
	@LoggerProfile(methodNote="查询地区列表")
	public List<SystemmgAreaOutputDto> queryList(SystemmgAreaInputDto inputDto){
		SystemmgAreaEntity systemmgAreaEntity = (SystemmgAreaEntity)BeanUtil.convertBean(inputDto, SystemmgAreaEntity.class);
		List<SystemmgAreaEntity> systemmgAreaList = systemmgAreaService.queryList(systemmgAreaEntity);
		return (List<SystemmgAreaOutputDto>)BeanUtil.convertBeanList(systemmgAreaList, SystemmgAreaOutputDto.class);
	}
	
	@Override
	@LoggerProfile(methodNote="查询地区数量")
	public int findPageCount(SystemmgAreaInputDto pageInputDto){
		SystemmgAreaEntity systemmgAreaEntity = (SystemmgAreaEntity) BeanUtil.convertBean(pageInputDto,SystemmgAreaEntity.class);

		int totalCount = systemmgAreaService.findPageCount(systemmgAreaEntity);
		return totalCount;
	}

	@Override
	@LoggerProfile(methodNote="删除当前地区及下级所有地区")
	public int removeCurrentAndSubArea(String id, String no) {
		return systemmgAreaService.removeCurrentAndSubArea(id, no);
	}
}
