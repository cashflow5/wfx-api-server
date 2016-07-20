 /*
 * 版本信息
 
 * 日期 2016-06-02 09:59:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.background;

import java.util.List;

import com.yougou.wfx.discover.dto.input.DiscoverArticleInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverArticlePageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverArticleOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * IDiscoverArticleBackgroundApi
 * @author wang.zf
 * @Date 创建时间：2016-06-02 09:59:26
 */
public interface IDiscoverArticleBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverArticleInputDto discoverArticleDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<DiscoverArticleOutputDto> findPage(DiscoverArticlePageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 获取分页数据
	 */
	public List<DiscoverArticleOutputDto> findPage(DiscoverArticleInputDto pageInputDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<DiscoverArticleOutputDto> findInfoPage(DiscoverArticlePageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 
	 */
	public int getMaxSortNo();
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverArticleInputDto discoverArticleDto);
	
	/**
	 * 批量更新
	 */
	public int batchUpdate(DiscoverArticleInputDto discoverArticleDto);
	
	/**
	 * 批量删除
	 */
	public int batchRemove(String ids);
	
	/**
	 * 获取已发布的数量
	 * @param ids
	 * @return
	 */
	public int getPublishCountByIds(String ids);
	/**
	 * 获取被轮播图引用的数量
	 * @param ids
	 * @return
	 */
	public int getChannelCountByids(String ids);
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverArticleOutputDto getById(String id);
	/**
	 * 通过编号查询数据
	 */
	public DiscoverArticleOutputDto getByNo(int no);
	
	
	public int findPageCount(DiscoverArticlePageInputDto pageInputDto);
}

