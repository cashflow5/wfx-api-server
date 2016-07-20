 /*
 * 版本信息
 
 * 日期 2016-06-03 11:26:32
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.front.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yougou.wfx.discover.api.front.IDiscoverCarouselFigureFrontApi;
import com.yougou.wfx.discover.dto.output.DiscoverArticleOutputDto;
import com.yougou.wfx.discover.dto.output.DiscoverCarouselFigureOutputDto;
import com.yougou.wfx.discover.model.DiscoverArticleEntity;
import com.yougou.wfx.discover.model.DiscoverCarouselFigureEntity;
import com.yougou.wfx.discover.service.IDiscoverArticleService;
import com.yougou.wfx.discover.service.IDiscoverCarouselFigureService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * IDiscoverCarouselFigureFrontApi实现
 * @author luoq
 * @Date 创建时间：2016-06-03 11:26:34
 */
@Service
public class DiscoverCarouselFigureFrontApiImpl implements IDiscoverCarouselFigureFrontApi{
	
	@Resource
	IDiscoverCarouselFigureService discoverCarouselFigureService;

	@Resource
	IDiscoverArticleService discoverArticleService ;
	
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
	public List<DiscoverCarouselFigureOutputDto> getAllShow() {
		List<DiscoverCarouselFigureEntity>  list = discoverCarouselFigureService.getAllShow();
		if( list !=null && list.size()>0 ){
			List<DiscoverCarouselFigureOutputDto> result = new ArrayList<DiscoverCarouselFigureOutputDto>();
			for(DiscoverCarouselFigureEntity entity: list){
				DiscoverCarouselFigureOutputDto dto = new DiscoverCarouselFigureOutputDto();
				String picUrl = entity.getPicture();
				if( StringUtils.isNotEmpty(picUrl) ){
					entity.setPicture( PicPathUtil.getImageAbsUrl(picUrl) );
				}
				dto = (DiscoverCarouselFigureOutputDto)BeanUtil.convertBean(entity,DiscoverCarouselFigureOutputDto.class);
				DiscoverArticleEntity article = entity.getDiscoverArticle();
				if(article!=null){
					discoverArticleService.simpleAct(article); 
					DiscoverArticleOutputDto articleDto = (DiscoverArticleOutputDto)BeanUtil.convertBean(article,DiscoverArticleOutputDto.class);
					
					dto.setDiscoverArticle(articleDto);
				}
				result.add( dto );
			}
			
			return result;
		}else{
			return null;
		}
	}
	
	
}
