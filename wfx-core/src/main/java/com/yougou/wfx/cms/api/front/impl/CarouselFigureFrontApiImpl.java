 /*
 * 版本信息
 
 * 日期 2016-03-31 16:07:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.cms.api.front.ICarouselFigureFrontApi;
import com.yougou.wfx.cms.dto.output.CarouselFigureOutputDto;
import com.yougou.wfx.cms.model.CarouselFigureEntity;
import com.yougou.wfx.cms.service.ICarouselFigureService;
import com.yougou.wfx.enums.CarouseFigureTypeEnum;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * ICarouselFigureFrontApi实现
 * @author li.lq
 * @Date   2016年6月23日
 */
@Service
public class CarouselFigureFrontApiImpl implements ICarouselFigureFrontApi{
	
	@Resource
	ICarouselFigureService carouselFigureService;

	@Override
	public List<CarouselFigureOutputDto> queryH5IndexCarouselFigureList() {
		CarouselFigureEntity  carouselFigureEntity = new CarouselFigureEntity();
		carouselFigureEntity.setStatus(2);
		carouselFigureEntity.setType(CarouseFigureTypeEnum.H5_INDEX.getKey());
		
		RowBounds rowBounds = new RowBounds(0,6);		
		List<CarouselFigureEntity> lstCarouselFigures = carouselFigureService.findPage(carouselFigureEntity, rowBounds);
		for(CarouselFigureEntity entity : lstCarouselFigures){
			entity.setPicUrl(PicPathUtil.getImageAbsUrl(entity.getPicUrl()));
		}
		
		return (List<CarouselFigureOutputDto>) BeanUtil.convertBeanList(lstCarouselFigures, CarouselFigureOutputDto.class);
	}
}
