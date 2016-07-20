 /*
 * 版本信息
 
 * 日期 2016-06-02 10:14:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.discover.api.background.IDiscoverCarouselFigureBackgroundApi;
import com.yougou.wfx.discover.dto.input.DiscoverCarouselFigureInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverCarouselFigurePageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverCarouselFigureOutputDto;
import com.yougou.wfx.discover.model.CarouselFigureIntoEntity;
import com.yougou.wfx.discover.model.DiscoverCarouselFigureEntity;
import com.yougou.wfx.discover.service.IDiscoverCarouselFigureService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IDiscoverCarouselFigureBackgroundApi实现
 * @author wang.zf
 * @Date 创建时间：2016-06-02 10:14:16
 */
@Service
public class DiscoverCarouselFigureBackgroundApiImpl implements IDiscoverCarouselFigureBackgroundApi{
	
	@Resource
	IDiscoverCarouselFigureService discoverCarouselFigureService;
	
	@Override
	public int removeById(String id) {
		return discoverCarouselFigureService.remove(id);
	}

	@Override
	public String insert(DiscoverCarouselFigureInputDto discoverCarouselFigureDto) {
		return discoverCarouselFigureService.insert(this.dtoToEntity(discoverCarouselFigureDto));
	}

	@Override
	public PageModel<DiscoverCarouselFigureOutputDto> findPage(DiscoverCarouselFigurePageInputDto pageInputDto,PageModel pageModel) {
		DiscoverCarouselFigureEntity discoverCarouselFigureEntity = (DiscoverCarouselFigureEntity) BeanUtil.convertBean(pageInputDto,DiscoverCarouselFigureEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverCarouselFigureService.findPageCount(discoverCarouselFigureEntity);
		List<DiscoverCarouselFigureEntity> lstDiscoverCarouselFigures = discoverCarouselFigureService.findPage(discoverCarouselFigureEntity, rowBounds);

		return new PageModel<DiscoverCarouselFigureOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<DiscoverCarouselFigureOutputDto>) BeanUtil.convertBeanList(lstDiscoverCarouselFigures,DiscoverCarouselFigureOutputDto.class));
	}

	@Override
	public PageModel<DiscoverCarouselFigureOutputDto> findInfoPage(
			DiscoverCarouselFigurePageInputDto pageInputDto, PageModel pageModel) {
		CarouselFigureIntoEntity carouselFigureIntoEntity = (CarouselFigureIntoEntity) BeanUtil.convertBean(pageInputDto,CarouselFigureIntoEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverCarouselFigureService.findInfoPageCount(carouselFigureIntoEntity);
		List<CarouselFigureIntoEntity> lstCarouselFigureInfos = discoverCarouselFigureService.findInfoPage(carouselFigureIntoEntity, rowBounds);

		return new PageModel<DiscoverCarouselFigureOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<DiscoverCarouselFigureOutputDto>) BeanUtil.convertBeanList(lstCarouselFigureInfos,DiscoverCarouselFigureOutputDto.class));
	}

	@Override
	public int getMaxSortNo() {
		return discoverCarouselFigureService.getMaxSortNo();
	}

	@Override
	public int update(DiscoverCarouselFigureInputDto discoverCarouselFigureDto) {
		return discoverCarouselFigureService.update(this.dtoToEntity(discoverCarouselFigureDto));
	}

	@Override
	public DiscoverCarouselFigureOutputDto getById(String id) {
		DiscoverCarouselFigureEntity discoverCarouselFigureEntity = discoverCarouselFigureService.getById(id);
		return this.entityToDto(discoverCarouselFigureEntity);
	}
	
	private DiscoverCarouselFigureEntity dtoToEntity(Object obj){
		return (DiscoverCarouselFigureEntity) BeanUtil.convertBean(obj,DiscoverCarouselFigureEntity.class);
	}
	
	private DiscoverCarouselFigureOutputDto entityToDto(Object obj){
		return (DiscoverCarouselFigureOutputDto) BeanUtil.convertBean(obj,DiscoverCarouselFigureOutputDto.class);
	}

	@Override
	public int batchRemove(String ids) {
		return discoverCarouselFigureService.batchRemove(ids);
	}

	@Override
	public int updateSortNo() {
		return discoverCarouselFigureService.updateSortNo();
	}

	@Override
	public DiscoverCarouselFigureOutputDto getPrevious(
			DiscoverCarouselFigureInputDto discoverCarouselFigure) {
		DiscoverCarouselFigureEntity discoverCarouselFigureEntity = this.dtoToEntity(discoverCarouselFigure);
		DiscoverCarouselFigureEntity pre = discoverCarouselFigureService.getPrevious(discoverCarouselFigureEntity);
		return this.entityToDto(pre);
	}

	@Override
	public DiscoverCarouselFigureOutputDto getNext(
			DiscoverCarouselFigureInputDto discoverCarouselFigure) {
		DiscoverCarouselFigureEntity discoverCarouselFigureEntity = this.dtoToEntity(discoverCarouselFigure);
		DiscoverCarouselFigureEntity next = discoverCarouselFigureService.getNext(discoverCarouselFigureEntity);
		return this.entityToDto(next);
	}
}
