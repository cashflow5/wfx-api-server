 /*
 * 版本信息
 
 * 日期 2016-03-31 16:07:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.cms.dao.CarouselFigureMapper;
import com.yougou.wfx.cms.model.CarouselFigureEntity;
import com.yougou.wfx.cms.service.ICarouselFigureService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICarouselFigureService实现
 * @author zhangfeng
 * @Date 创建时间：2016-03-31 16:07:39
 */
@Service
public class CarouselFigureServiceImpl extends BaseService implements ICarouselFigureService {
	
	@Resource
	private CarouselFigureMapper carouselFigureMapper;

	@Override
	public int findPageCount(CarouselFigureEntity carouselFigureEntity){
		return carouselFigureMapper.findPageCount(carouselFigureEntity);
	}

	@Override
	public List<CarouselFigureEntity> findPage(CarouselFigureEntity carouselFigureEntity,RowBounds rowBounds){
		return carouselFigureMapper.findPage(carouselFigureEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(CarouselFigureEntity carouselFigureEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		carouselFigureEntity.setId(strId);
		carouselFigureMapper.insert(carouselFigureEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(CarouselFigureEntity carouselFigureEntity){
		return  carouselFigureMapper.update(carouselFigureEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return carouselFigureMapper.remove(id);
	}
	
	@Override
	public CarouselFigureEntity getById(String id){
		return carouselFigureMapper.getById(id);
	}

	@Override
	public Map<String, Integer> getLimitInfo(String type) {
		// TODO Auto-generated method stub
		return carouselFigureMapper.getLimitInfo(type);
	}

	
}