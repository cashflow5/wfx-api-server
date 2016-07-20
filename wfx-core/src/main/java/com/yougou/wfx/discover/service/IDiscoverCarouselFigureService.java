 /*
 * 版本信息
 
 * 日期 2016-06-02 10:14:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.CarouselFigureIntoEntity;
import com.yougou.wfx.discover.model.DiscoverCarouselFigureEntity;

/**
 * IDiscoverCarouselFigureService接口
 * @author wang.zf
 * @Date 创建时间：2016-06-02 10:14:16
 */
public interface IDiscoverCarouselFigureService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);

	/**
	 * 获取分页数据
	 */
	public List<DiscoverCarouselFigureEntity> findPage(DiscoverCarouselFigureEntity discoverCarouselFigureEntity,RowBounds rowBounds);
	
	/**
	 * 获取满足条件的轮播图数量
	 */
	int findInfoPageCount(CarouselFigureIntoEntity carouselFigureIntoEntity);
	
	/**
	 * 获取满足条件的轮播图数量
	 */
	List<CarouselFigureIntoEntity> findInfoPage(CarouselFigureIntoEntity carouselFigureIntoEntity,RowBounds rowBounds);

	/**
	 * 轮播图最大序号
	 */
	int getMaxSortNo();
	
	/**
	 * 获取查询数据
	 */
	public List<DiscoverCarouselFigureEntity> queryList(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id批量删除记录
	 */
	public int batchRemove(String ids);
	
	DiscoverCarouselFigureEntity getPrevious(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	DiscoverCarouselFigureEntity getNext(DiscoverCarouselFigureEntity discoverCarouselFigureEntity);
	
	/**
	 * 轮播图，排序号加1
	 * @return
	 */
	public int updateSortNo();
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverCarouselFigureEntity getById(String id);
	/**
	 * 查前几个供显示的轮播图
	 */
	List<DiscoverCarouselFigureEntity> getAllShow(); 
}