 /*
 * 版本信息
 
 * 日期 2016-04-09 13:01:36
 
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

import com.yougou.wfx.basicset.api.background.ISysConfigBackgroundApi;
import com.yougou.wfx.basicset.dto.input.SysConfigInputDto;
import com.yougou.wfx.basicset.dto.input.SysConfigPageInputDto;
import com.yougou.wfx.basicset.dto.output.SysConfigOutputDto;
import com.yougou.wfx.basicset.model.SysConfigEntity;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ISysConfigBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-09 13:01:37
 */
@Service
public class SysConfigBackgroundApiImpl implements ISysConfigBackgroundApi{
	
	@Resource
	ISysConfigService sysConfigService;
	
	@Override
	public int removeById(String id) {
		return sysConfigService.remove(id);
	}

	@Override
	public String insert(SysConfigInputDto sysConfigDto) {
		return sysConfigService.insert(this.dtoToEntity(sysConfigDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询系统配置")
	public PageModel<SysConfigOutputDto> findPage(SysConfigPageInputDto pageInputDto,PageModel pageModel) {
		SysConfigEntity sysConfigEntity = (SysConfigEntity) BeanUtil.convertBean(pageInputDto,SysConfigEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = sysConfigService.findPageCount(sysConfigEntity);
		List<SysConfigEntity> lstSysConfigs = sysConfigService.findPage(sysConfigEntity, rowBounds);

		return new PageModel<SysConfigOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<SysConfigOutputDto>) BeanUtil.convertBeanList(lstSysConfigs,SysConfigOutputDto.class));
	}

	@Override
	public int update(SysConfigInputDto sysConfigDto) {
		return sysConfigService.update(this.dtoToEntity(sysConfigDto));
	}

	@Override
	public SysConfigOutputDto getById(String id) {
		SysConfigEntity sysConfigEntity = sysConfigService.getById(id);
		return this.entityToDto(sysConfigEntity);
	}
	
	private SysConfigEntity dtoToEntity(Object obj){
		return (SysConfigEntity) BeanUtil.convertBean(obj,SysConfigEntity.class);
	}
	
	private SysConfigOutputDto entityToDto(Object obj){
		return (SysConfigOutputDto) BeanUtil.convertBean(obj,SysConfigOutputDto.class);
	}
	@Override
	public String getValueBykey(String key){
		return sysConfigService.getValueBykey(key);
	}
}
