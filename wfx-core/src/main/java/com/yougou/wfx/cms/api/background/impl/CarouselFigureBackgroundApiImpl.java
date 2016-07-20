 /*
 * 版本信息
 
 * 日期 2016-03-31 16:07:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.background.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.cms.api.background.ICarouselFigureBackgroundApi;
import com.yougou.wfx.cms.dto.input.CarouselFigureInputDto;
import com.yougou.wfx.cms.dto.input.CarouselFigurePageInputDto;
import com.yougou.wfx.cms.dto.output.CarouselFigureOutputDto;
import com.yougou.wfx.cms.model.CarouselFigureEntity;
import com.yougou.wfx.cms.service.ICarouselFigureService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICarouselFigureBackgroundApi实现
 * @author zhangfeng
 * @Date 创建时间：2016-03-31 16:07:39
 */
@Service
public class CarouselFigureBackgroundApiImpl implements ICarouselFigureBackgroundApi{
	
	@Resource
	ICarouselFigureService carouselFigureService;
	
	@Override
	public int removeById(String id) {
		return carouselFigureService.remove(id);
	}
	
	@LoggerProfile(methodNote = "后台新增轮播图接口")
	@Override
	public String insert(CarouselFigureInputDto carouselFigureEntity) {
		return carouselFigureService.insert(this.dtoToEntity(carouselFigureEntity));
	}
	
	@LoggerProfile(methodNote = "后台轮播图列表查询接口")
	@Override
	public PageModel<CarouselFigureOutputDto> findPage(CarouselFigurePageInputDto pageInputDto,PageModel<CarouselFigureOutputDto> pageModel) {
		CarouselFigureEntity carouselFigureEntity = (CarouselFigureEntity) BeanUtil.convertBean(pageInputDto,CarouselFigureEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = carouselFigureService.findPageCount(carouselFigureEntity);
		List<CarouselFigureEntity> lstCmsCarouselFigures = carouselFigureService.findPage(carouselFigureEntity, rowBounds);

		return new PageModel<CarouselFigureOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CarouselFigureOutputDto>) BeanUtil.convertBeanList(lstCmsCarouselFigures,CarouselFigureOutputDto.class));
	}
	
	@LoggerProfile(methodNote = "后台修改轮播图接口")
	@Override
	public int update(CarouselFigureInputDto carouselFigureEntity) {
		return carouselFigureService.update(this.dtoToEntity(carouselFigureEntity));
	}
	
	@LoggerProfile(methodNote = "后台根据ID获取轮播图接口")
	@Override
	public CarouselFigureOutputDto getById(String id) {
		CarouselFigureEntity carouselFigureEntity = carouselFigureService.getById(id);
		return this.entityToDto(carouselFigureEntity);
	}
	
	private CarouselFigureEntity dtoToEntity(Object obj){
		return (CarouselFigureEntity) BeanUtil.convertBean(obj,CarouselFigureEntity.class);
	}
	
	private CarouselFigureOutputDto entityToDto(Object obj){
		return (CarouselFigureOutputDto) BeanUtil.convertBean(obj,CarouselFigureOutputDto.class);
	}
	
	@LoggerProfile(methodNote = "后台获取轮播图条件限制接口")
	@Override
	public Map<String, Integer> getLimitInfo(String type) {
		// TODO Auto-generated method stub
		return carouselFigureService.getLimitInfo(type);
	}

	
}
