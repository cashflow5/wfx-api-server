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

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.discover.api.front.IDiscoverArticleFrontApi;
import com.yougou.wfx.discover.dto.input.DiscoverArticlePageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverArticleOutputDto;
import com.yougou.wfx.discover.model.DiscoverArticleEntity;
import com.yougou.wfx.discover.service.IDiscoverArticleService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IDiscoverArticleFrontApi实现
 * @author luoq
 * @Date 创建时间：2016-06-03 11:26:33
 */
@Service
public class DiscoverArticleFrontApiImpl implements IDiscoverArticleFrontApi{
	
	@Resource
	IDiscoverArticleService discoverArticleService;

	@Override
	public PageModel<DiscoverArticleOutputDto> findPage(DiscoverArticlePageInputDto pageInputDto,PageModel<DiscoverArticleOutputDto> pageModel) {
		
		if( pageInputDto!=null && pageInputDto.getChannelId().equals("0") ){
			pageInputDto.setRecommendFlag( Constant.WFX_ARTICLE_RECOMMEND );
			pageInputDto.setChannelId( null );
		}else{
			pageInputDto.setPublishStatus( Constant.WFX_ARTICLE_IS_PUBLISH );
		}
		pageInputDto.setDeleteFlag(1);//未删除
		DiscoverArticleEntity discoverArticleEntity = (DiscoverArticleEntity) BeanUtil.convertBean(pageInputDto,DiscoverArticleEntity.class);
		
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverArticleService.findPageCount(discoverArticleEntity);
		List<DiscoverArticleEntity> lstDiscoverArticles = discoverArticleService.findPage(discoverArticleEntity, rowBounds);
		List<DiscoverArticleOutputDto> list = new ArrayList<DiscoverArticleOutputDto>();
		for(DiscoverArticleEntity entity: lstDiscoverArticles){
			discoverArticleService.simpleAct(entity);
			DiscoverArticleOutputDto dto = 	(DiscoverArticleOutputDto) BeanUtil.convertBean(entity,DiscoverArticleOutputDto.class);
			list.add( dto )	;
		}
		return new PageModel<DiscoverArticleOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,list);
	}


	@Override
	public DiscoverArticleOutputDto getById(String id) {
		DiscoverArticleEntity discoverArticleEntity = discoverArticleService.getById(id);
		discoverArticleService.simpleAct(discoverArticleEntity);
		return this.entityToDto(discoverArticleEntity);
	}
	
	private DiscoverArticleEntity dtoToEntity(Object obj){
		return (DiscoverArticleEntity) BeanUtil.convertBean(obj,DiscoverArticleEntity.class);
	}
	
	private DiscoverArticleOutputDto entityToDto(Object obj){
		return (DiscoverArticleOutputDto) BeanUtil.convertBean(obj,DiscoverArticleOutputDto.class);
	}
}
