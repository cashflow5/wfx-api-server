 /*
 * 版本信息
 
 * 日期 2016-06-02 09:59:24
 
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

import com.yougou.wfx.discover.api.background.IDiscoverArticleBackgroundApi;
import com.yougou.wfx.discover.dto.input.DiscoverArticleInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverArticlePageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverArticleOutputDto;
import com.yougou.wfx.discover.model.ArticleInfoEntity;
import com.yougou.wfx.discover.model.DiscoverArticleEntity;
import com.yougou.wfx.discover.service.IDiscoverArticleService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IDiscoverArticleBackgroundApi实现
 * @author wang.zf
 * @Date 创建时间：2016-06-02 09:59:26
 */
@Service
public class DiscoverArticleBackgroundApiImpl implements IDiscoverArticleBackgroundApi{
	
	@Resource
	IDiscoverArticleService discoverArticleService;
	
	@Override
	public int removeById(String id) {
		return discoverArticleService.remove(id);
	}

	@Override
	public String insert(DiscoverArticleInputDto discoverArticleDto) {
		return discoverArticleService.insert(this.dtoToEntity(discoverArticleDto));
	}

	@Override
	public PageModel<DiscoverArticleOutputDto> findPage(DiscoverArticlePageInputDto pageInputDto,PageModel pageModel) {
		DiscoverArticleEntity discoverArticleEntity = (DiscoverArticleEntity) BeanUtil.convertBean(pageInputDto,DiscoverArticleEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverArticleService.findPageCount(discoverArticleEntity);
		List<DiscoverArticleEntity> lstDiscoverArticles = discoverArticleService.findPage(discoverArticleEntity, rowBounds);

		return new PageModel<DiscoverArticleOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<DiscoverArticleOutputDto>) BeanUtil.convertBeanList(lstDiscoverArticles,DiscoverArticleOutputDto.class));
	}

	@Override
	public PageModel<DiscoverArticleOutputDto> findInfoPage(DiscoverArticlePageInputDto pageInputDto, PageModel pageModel) {
		ArticleInfoEntity articleInfoEntity = (ArticleInfoEntity) BeanUtil.convertBean(pageInputDto,ArticleInfoEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = discoverArticleService.findInfoPageCount(articleInfoEntity);
		List<ArticleInfoEntity> lstArticleInfos = discoverArticleService.findInfoPage(articleInfoEntity, rowBounds);

		return new PageModel<DiscoverArticleOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<DiscoverArticleOutputDto>) BeanUtil.convertBeanList(lstArticleInfos,DiscoverArticleOutputDto.class));
	}

	@Override
	public int getMaxSortNo() {
		return discoverArticleService.getMaxSortNo();
	}

	@Override
	public int update(DiscoverArticleInputDto discoverArticleDto) {
		return discoverArticleService.update(this.dtoToEntity(discoverArticleDto));
	}

	@Override
	public int batchUpdate(DiscoverArticleInputDto discoverArticleDto) {
		DiscoverArticleEntity discoverArticleEntity = (DiscoverArticleEntity)BeanUtil.convertBean(discoverArticleDto, DiscoverArticleEntity.class);
		return discoverArticleService.batchUpdate(discoverArticleEntity);
	}
	
	@Override
	public DiscoverArticleOutputDto getById(String id) {
		DiscoverArticleEntity discoverArticleEntity = discoverArticleService.getById(id);
		return this.entityToDto(discoverArticleEntity);
	}
	
	private DiscoverArticleEntity dtoToEntity(Object obj){
		return (DiscoverArticleEntity) BeanUtil.convertBean(obj,DiscoverArticleEntity.class);
	}
	
	private DiscoverArticleOutputDto entityToDto(Object obj){
		return (DiscoverArticleOutputDto) BeanUtil.convertBean(obj,DiscoverArticleOutputDto.class);
	}

	@Override
	public int batchRemove(String ids) {
		return discoverArticleService.batchRemove(ids);
	}

	@Override
	public List<DiscoverArticleOutputDto> findPage(DiscoverArticleInputDto inputDto) {
		DiscoverArticleEntity entity = this.dtoToEntity(inputDto);
		List<DiscoverArticleEntity> entityList = discoverArticleService.findPage(entity);
		return (List<DiscoverArticleOutputDto>)BeanUtil.convertBeanList(entityList, DiscoverArticleOutputDto.class);
	}

	@Override
	public int findPageCount(DiscoverArticlePageInputDto pageInputDto) {
		DiscoverArticleEntity discoverArticleEntity = (DiscoverArticleEntity) BeanUtil.convertBean(pageInputDto,DiscoverArticleEntity.class);
		return discoverArticleService.findPageCount(discoverArticleEntity);
	}

	@Override
	public int getPublishCountByIds(String ids) {
		return discoverArticleService.getPublishCountByIds(ids);
	}

	@Override
	public int getChannelCountByids(String ids) {
		return discoverArticleService.getChannelCountByids(ids);
	}

	@Override
	public DiscoverArticleOutputDto getByNo(int no) {
		DiscoverArticleEntity entiyParam=new DiscoverArticleEntity();
		entiyParam.setNo(no);
		List<DiscoverArticleEntity> entityList= discoverArticleService.queryList(entiyParam);
		if(entityList!=null&&!entityList.isEmpty()){
			return this.entityToDto(entityList.get(0));
		}
		return null;
	}
}
