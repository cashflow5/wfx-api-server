 /*
 * 版本信息
 
 * 日期 2016-06-02 20:18:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.discover.api.front.IDiscoverLogFrontApi;
import com.yougou.wfx.discover.dto.input.DiscoverLogInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverLogPageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverLogOutputDto;
import com.yougou.wfx.discover.model.DiscoverLogEntity;
import com.yougou.wfx.discover.service.IDiscoverLogService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IDiscoverLogFrontApi实现
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 20:18:26
 */
@Service
public class DiscoverLogFrontApiImpl implements IDiscoverLogFrontApi{
	
	@Resource
	IDiscoverLogService discoverLogService;
	
	@Override
	public int removeById(String id) {
		return discoverLogService.remove(id);
	}

	@Override
	public String insert(DiscoverLogInputDto discoverLogDto) {
		return discoverLogService.insert(this.dtoToEntity(discoverLogDto));
	}

	@Override
	public PageModel<DiscoverLogOutputDto> findPage(DiscoverLogPageInputDto pageInputDto,PageModel<DiscoverLogOutputDto> pageModel) {
		DiscoverLogEntity discoverLogEntity = (DiscoverLogEntity) BeanUtil.convertBean(pageInputDto,DiscoverLogEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverLogService.findPageCount(discoverLogEntity);
		List<DiscoverLogEntity> lstDiscoverLogs = discoverLogService.findPage(discoverLogEntity, rowBounds);

		return new PageModel<DiscoverLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<DiscoverLogOutputDto>) BeanUtil.convertBeanList(lstDiscoverLogs,DiscoverLogOutputDto.class));
	}

	@Override
	public int update(DiscoverLogInputDto discoverLogDto) {
		return discoverLogService.update(this.dtoToEntity(discoverLogDto));
	}

	@Override
	public DiscoverLogOutputDto getById(String id) {
		DiscoverLogEntity discoverLogEntity = discoverLogService.getById(id);
		return this.entityToDto(discoverLogEntity);
	}
	
	private DiscoverLogEntity dtoToEntity(Object obj){
		return (DiscoverLogEntity) BeanUtil.convertBean(obj,DiscoverLogEntity.class);
	}
	
	private DiscoverLogOutputDto entityToDto(Object obj){
		return (DiscoverLogOutputDto) BeanUtil.convertBean(obj,DiscoverLogOutputDto.class);
	}
}
