 /*
 * 版本信息
 
 * 日期 2016-06-02 10:14:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.discover.dao.DiscoverCarouselFigureMapper;
import com.yougou.wfx.discover.model.CarouselFigureIntoEntity;
import com.yougou.wfx.discover.model.DiscoverCarouselFigureEntity;
import com.yougou.wfx.discover.service.IDiscoverCarouselFigureService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * IDiscoverCarouselFigureService实现
 * @author wang.zf
 * @Date 创建时间：2016-06-02 10:14:16
 */
@Service
public class DiscoverCarouselFigureServiceImpl extends BaseService implements IDiscoverCarouselFigureService {
	
	@Resource
	private DiscoverCarouselFigureMapper discoverCarouselFigureMapper;

	@Override
	public int findPageCount(DiscoverCarouselFigureEntity discoverCarouselFigureEntity){
		return discoverCarouselFigureMapper.findPageCount(discoverCarouselFigureEntity);
	}

	@Override
	public List<DiscoverCarouselFigureEntity> findPage(DiscoverCarouselFigureEntity discoverCarouselFigureEntity,RowBounds rowBounds){
		return discoverCarouselFigureMapper.findPage(discoverCarouselFigureEntity,rowBounds);
	}

	@Override
	public int findInfoPageCount(CarouselFigureIntoEntity carouselFigureIntoEntity) {
		return discoverCarouselFigureMapper.findInfoPageCount(carouselFigureIntoEntity);
	}

	@Override
	public List<CarouselFigureIntoEntity> findInfoPage(
			CarouselFigureIntoEntity carouselFigureIntoEntity,RowBounds rowBounds) {
		return discoverCarouselFigureMapper.findInfoPage(carouselFigureIntoEntity, rowBounds);
	}

	@Override
	public int getMaxSortNo() {
		Integer count = discoverCarouselFigureMapper.getMaxSortNo();
		if(null == count){
			count = 0;
		}
		return count.intValue();
	}

	@Override
	public List<DiscoverCarouselFigureEntity> queryList(DiscoverCarouselFigureEntity discoverCarouselFigureEntity){
		return discoverCarouselFigureMapper.queryList(discoverCarouselFigureEntity);
	}
	
	@Override
	@Transactional
	public String insert(DiscoverCarouselFigureEntity discoverCarouselFigureEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		discoverCarouselFigureEntity.setId(strId);
		if(null == discoverCarouselFigureEntity.getSortNo() || discoverCarouselFigureEntity.getSortNo() == 0){
			/**Integer maxNo = discoverCarouselFigureMapper.getMaxSortNo();
			if(null == maxNo){
				maxNo = 0;
			}
			maxNo++;*/
			
			discoverCarouselFigureMapper.updateSortNo();
			discoverCarouselFigureEntity.setSortNo(1);
		}
		discoverCarouselFigureMapper.insert(discoverCarouselFigureEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(DiscoverCarouselFigureEntity discoverCarouselFigureEntity){
		return  discoverCarouselFigureMapper.update(discoverCarouselFigureEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return discoverCarouselFigureMapper.remove(id);
	}
	
	@Override
	public DiscoverCarouselFigureEntity getById(String id){
		return discoverCarouselFigureMapper.getById(id);
	}

	@Override
	public int batchRemove(String ids) {
		return discoverCarouselFigureMapper.batchRemove(ids);
	}

	@Override
	public int updateSortNo() {
		return discoverCarouselFigureMapper.updateSortNo();
	}

	@Override
	public DiscoverCarouselFigureEntity getPrevious(
			DiscoverCarouselFigureEntity discoverCarouselFigureEntity) {
		return discoverCarouselFigureMapper.getPrevious(discoverCarouselFigureEntity);
	}

	@Override
	public DiscoverCarouselFigureEntity getNext(
			DiscoverCarouselFigureEntity discoverCarouselFigureEntity) {
		return discoverCarouselFigureMapper.getNext(discoverCarouselFigureEntity);
	}
	@Override
	public List<DiscoverCarouselFigureEntity> getAllShow( ){
		return discoverCarouselFigureMapper.getAllShow();
	}
}