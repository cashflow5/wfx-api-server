 /*
 * 版本信息
 
 * 日期 2016-05-19 09:43:26
 
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

import com.yougou.wfx.basicset.api.background.IAppVersionBackgroundApi;
import com.yougou.wfx.basicset.dto.input.AppVersionInputDto;
import com.yougou.wfx.basicset.dto.input.AppVersionPageInputDto;
import com.yougou.wfx.basicset.dto.output.AppVersionOutputDto;
import com.yougou.wfx.basicset.model.AppVersionEntity;
import com.yougou.wfx.basicset.service.IAppVersionService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IAppVersionBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-05-19 09:43:27
 */
@Service
public class AppVersionBackgroundApiImpl implements IAppVersionBackgroundApi{
	
	@Resource
	IAppVersionService appVersionService;
	
	@Override
	public int removeById(String id) {
		return appVersionService.remove(id);
	}

	@Override
	public String insert(AppVersionInputDto appVersionDto) {
		return appVersionService.insert(this.dtoToEntity(appVersionDto));
	}

	@Override
	public PageModel<AppVersionOutputDto> findPage(AppVersionPageInputDto pageInputDto,PageModel pageModel) {
		AppVersionEntity appVersionEntity = (AppVersionEntity) BeanUtil.convertBean(pageInputDto,AppVersionEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = appVersionService.findPageCount(appVersionEntity);
		List<AppVersionEntity> lstAppVersions = appVersionService.findPage(appVersionEntity, rowBounds);

		return new PageModel<AppVersionOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<AppVersionOutputDto>) BeanUtil.convertBeanList(lstAppVersions,AppVersionOutputDto.class));
	}

	@Override
	public int update(AppVersionInputDto appVersionDto) {
		return appVersionService.update(this.dtoToEntity(appVersionDto));
	}

	@Override
	public AppVersionOutputDto getById(String id) {
		AppVersionEntity appVersionEntity = appVersionService.getById(id);
		return this.entityToDto(appVersionEntity);
	}
	
	private AppVersionEntity dtoToEntity(Object obj){
		return (AppVersionEntity) BeanUtil.convertBean(obj,AppVersionEntity.class);
	}
	
	private AppVersionOutputDto entityToDto(Object obj){
		return (AppVersionOutputDto) BeanUtil.convertBean(obj,AppVersionOutputDto.class);
	}
}
