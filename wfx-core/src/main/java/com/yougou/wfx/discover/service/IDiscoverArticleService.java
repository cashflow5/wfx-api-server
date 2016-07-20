 /*
 * 版本信息
 
 * 日期 2016-06-02 09:59:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.ArticleInfoEntity;
import com.yougou.wfx.discover.model.DiscoverArticleEntity;

/**
 * IDiscoverArticleService接口
 * @author wang.zf
 * @Date 创建时间：2016-06-02 09:59:26
 */
public interface IDiscoverArticleService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(DiscoverArticleEntity discoverArticleEntity);

	/**
	 * 获取分页数据
	 */
	public List<DiscoverArticleEntity> findPage(DiscoverArticleEntity discoverArticleEntity,RowBounds rowBounds);
	
	/**
	 * 获取分页数据
	 */
	public List<DiscoverArticleEntity> findPage(DiscoverArticleEntity discoverArticleEntity);
	/**
	 * 查询满总条数
	 */
	int findInfoPageCount(ArticleInfoEntity articleInfoEntity);
	/**
	 * 查询文章信息分页数据
	 */
	List<ArticleInfoEntity> findInfoPage(ArticleInfoEntity articleInfoEntity,RowBounds rowBounds);
	/**
	 * 获取最大排序号
	 */
	int getMaxSortNo();
	/**
	 * 获取查询数据
	 */
	public List<DiscoverArticleEntity> queryList(DiscoverArticleEntity discoverArticleEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverArticleEntity discoverArticleEntity);
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverArticleEntity discoverArticleEntity);
	
	/**
	 * 批量更新
	 */
	public int batchUpdate(DiscoverArticleEntity discoverArticleEntity);
	
	/**
	 * 批量删除
	 */
	public int batchRemove(String ids);
	
	/**
	 * 获取已发布的数量
	 * @param ids
	 * @return
	 */
	int getPublishCountByIds(String ids);
	/**
	 * 获取被轮播图引用的数量
	 * @param ids
	 * @return
	 */
	int getChannelCountByids(String ids);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverArticleEntity getById(String id);

	void simpleAct(DiscoverArticleEntity article); 
}