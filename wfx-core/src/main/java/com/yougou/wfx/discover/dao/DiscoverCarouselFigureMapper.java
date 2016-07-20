 /*
 * 版本信息
 
 * 日期 2016-06-02 10:14:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.CarouselFigureIntoEntity;
import com.yougou.wfx.discover.model.DiscoverCarouselFigureEntity;

/**
 * DiscoverCarouselFigureMapper
 * @author wang.zf
 * @Date 创建时间：2016-06-02 10:14:16
 */
public interface DiscoverCarouselFigureMapper{
	
	int findPageCount(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);

	List<DiscoverCarouselFigureEntity> findPage(DiscoverCarouselFigureEntity discoverCarouselFigureEntity,RowBounds rowBounds);
	
	int findInfoPageCount(CarouselFigureIntoEntity carouselFigureIntoEntity);
	
	List<CarouselFigureIntoEntity> findInfoPage(CarouselFigureIntoEntity carouselFigureIntoEntity,RowBounds rowBounds);
	
	Integer getMaxSortNo();
	
	List<DiscoverCarouselFigureEntity> queryList(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	List<DiscoverCarouselFigureEntity> getAllShow();
	
	int insert(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	int update(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	DiscoverCarouselFigureEntity getPrevious(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	DiscoverCarouselFigureEntity getNext(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	int remove(String id);
	
	int batchRemove(String ids);
	
	int updateSortNo();
	
	DiscoverCarouselFigureEntity getById(String id);
}
