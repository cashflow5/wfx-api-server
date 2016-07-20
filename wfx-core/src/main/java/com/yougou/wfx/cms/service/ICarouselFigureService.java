 /*
 * 版本信息
 
 * 日期 2016-03-31 16:07:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.cms.model.CarouselFigureEntity;

/**
 * ICmsCarouselFigureService接口
 * @author zhangfeng
 * @Date 创建时间：2016-03-31 16:07:39
 */
public interface ICarouselFigureService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CarouselFigureEntity carouselFigureEntity);

	/**
	 * 获取分页数据
	 */
	public List<CarouselFigureEntity> findPage(CarouselFigureEntity carouselFigureEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CarouselFigureEntity carouselFigureEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CarouselFigureEntity carouselFigureEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CarouselFigureEntity getById(String id); 
	
	/**
	 * /**
	 * 获取轮播图限制信息：有效的轮播图当前最大排序号，总数
	 * @return
	 */
	Map<String,Integer> getLimitInfo(String type);
}