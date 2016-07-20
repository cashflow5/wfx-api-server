 /*
 * 版本信息
 
 * 日期 2016-06-02 09:59:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.ArticleInfoEntity;
import com.yougou.wfx.discover.model.DiscoverArticleEntity;

/**
 * DiscoverArticleMapper
 * @author wang.zf
 * @Date 创建时间：2016-06-02 09:59:26
 */
public interface DiscoverArticleMapper{
	
	int findPageCount(DiscoverArticleEntity discoverArticleEntity);

	List<DiscoverArticleEntity> findPage(DiscoverArticleEntity discoverArticleEntity,RowBounds rowBounds);
	
	List<DiscoverArticleEntity> findPage(DiscoverArticleEntity discoverArticleEntity);
	
	int findInfoPageCount(ArticleInfoEntity articleInfoEntity);
	
	List<ArticleInfoEntity> findInfoPage(ArticleInfoEntity articleInfoEntity,RowBounds rowBounds);
	
	Integer getMaxSortNo();
	
	List<DiscoverArticleEntity> queryList(DiscoverArticleEntity discoverArticleEntity);
	
	int insert(DiscoverArticleEntity discoverArticleEntity);
	
	int update(DiscoverArticleEntity discoverArticleEntity);
	
	int batchUpdate(DiscoverArticleEntity discoverArticleEntity);
	
	int batchRemove(String ids);
	
	int getPublishCountByIds(String ids);
	
	int getChannelCountByids(String ids);
	
	int remove(String id);
	
	DiscoverArticleEntity getById(String id);
}
