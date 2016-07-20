 /*
 * 版本信息
 
 * 日期 2016-06-02 09:59:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.discover.dao.DiscoverArticleMapper;
import com.yougou.wfx.discover.model.ArticleInfoEntity;
import com.yougou.wfx.discover.model.DiscoverArticleEntity;
import com.yougou.wfx.discover.service.IDiscoverArticleService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.utils.BizNoGen;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * IDiscoverArticleService实现
 * @author wang.zf
 * @Date 创建时间：2016-06-02 09:59:26
 */
@Service
public class DiscoverArticleServiceImpl extends BaseService implements IDiscoverArticleService {
	
	@Resource
	private DiscoverArticleMapper discoverArticleMapper;

	@Override
	public int findPageCount(DiscoverArticleEntity discoverArticleEntity){
		return discoverArticleMapper.findPageCount(discoverArticleEntity);
	}

	@Override
	public List<DiscoverArticleEntity> findPage(DiscoverArticleEntity discoverArticleEntity,RowBounds rowBounds){
		return discoverArticleMapper.findPage(discoverArticleEntity,rowBounds);
	}

	@Override
	public int findInfoPageCount(ArticleInfoEntity articleInfoEntity) {
		return discoverArticleMapper.findInfoPageCount(articleInfoEntity);
	}

	@Override
	public List<ArticleInfoEntity> findInfoPage(
			ArticleInfoEntity articleInfoEntity, RowBounds rowBounds) {
		return discoverArticleMapper.findInfoPage(articleInfoEntity, rowBounds);
	}

	@Override
	public int getMaxSortNo() {
		Integer sortNo = discoverArticleMapper.getMaxSortNo();
		if(null == sortNo){
			sortNo = 0;
		}
		return sortNo.intValue();
	}

	@Override
	public List<DiscoverArticleEntity> queryList(DiscoverArticleEntity discoverArticleEntity){
		return discoverArticleMapper.queryList(discoverArticleEntity);
	}
	
	@Override
	@Transactional
	public String insert(DiscoverArticleEntity discoverArticleEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		discoverArticleEntity.setId(strId);
		if(null == discoverArticleEntity.getSortNo() || discoverArticleEntity.getSortNo() == 0){
			Integer maxNo = discoverArticleMapper.getMaxSortNo();
			if(null == maxNo){
				maxNo = 0;
			}
			maxNo++;
			discoverArticleEntity.setSortNo(maxNo);
		}
		if(null == discoverArticleEntity.getNo() || discoverArticleEntity.getNo() == 0){
			discoverArticleEntity.setNo(Integer.parseInt(BizNoGen.getArticleNo()));
		}
		if(null == discoverArticleEntity.getPublishStatus() || discoverArticleEntity.getPublishStatus() == 0){
			discoverArticleEntity.setPublishStatus(1);
		}
		if(null == discoverArticleEntity.getRecommendFlag() || discoverArticleEntity.getRecommendFlag() == 0){
			discoverArticleEntity.setRecommendFlag(1);
		}
		if(null == discoverArticleEntity.getDeleteFlag() || discoverArticleEntity.getDeleteFlag() == 0){
			discoverArticleEntity.setDeleteFlag(1);
		}
		discoverArticleMapper.insert(discoverArticleEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(DiscoverArticleEntity discoverArticleEntity){
		return  discoverArticleMapper.update(discoverArticleEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return discoverArticleMapper.remove(id);
	}
	
	@Override
	public DiscoverArticleEntity getById(String id){
		return discoverArticleMapper.getById(id);
	}

	@Override
	public int batchUpdate(DiscoverArticleEntity discoverArticleEntity) {
		return discoverArticleMapper.batchUpdate(discoverArticleEntity);
	}

	@Override
	public int batchRemove(String ids) {
		return discoverArticleMapper.batchRemove(ids);
	}

	@Override
	public int getPublishCountByIds(String ids) {
		return discoverArticleMapper.getPublishCountByIds(ids);
	}

	@Override
	public int getChannelCountByids(String ids) {
		return discoverArticleMapper.getChannelCountByids(ids);
	}

	@Override
	public List<DiscoverArticleEntity> findPage(
			DiscoverArticleEntity discoverArticleEntity) {
		return discoverArticleMapper.findPage(discoverArticleEntity);
	}
	@Override
	public void simpleAct(DiscoverArticleEntity article){
		String title = article.getTitle();
		String classify = "";
		if( StringUtils.isNotEmpty(title) ){
			int i1 = title.indexOf("【");
			int i2 = title.indexOf("[");
			if(  title.indexOf("【")!=-1 && title.indexOf("】")!=-1 ){
				if( i1+1==title.indexOf("】") ){
					classify = "";
				}else{
					classify = title.substring(title.indexOf("【")+1,title.indexOf("】"));
				}
			
				title = title.substring(title.indexOf("】")+1);
			}else{
				if( title.indexOf("[")!=-1 && title.indexOf("]")!=-1){
					if( i2+1==title.indexOf("]") ){
						classify = "";
					}else{
						classify = title.substring(title.indexOf("[")+1,title.indexOf("]"));
					}
					title = title.substring(title.indexOf("]")+1);
				}
			}
			article.setTitle( title );
			article.setClassify(classify);
		}
		
		String url = article.getPicCover();
		if(StringUtils.isNotEmpty( url )){
			article.setPicCover( PicPathUtil.getImageAbsUrl(url) );
		}
	}
}