 /*
 * 版本信息
 
 * 日期 2016-06-02 10:14:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.background;

import com.yougou.wfx.discover.dto.input.DiscoverCarouselFigureInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverCarouselFigurePageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverCarouselFigureOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * IDiscoverCarouselFigureBackgroundApi
 * @author wang.zf
 * @Date 创建时间：2016-06-02 10:14:16
 */
public interface IDiscoverCarouselFigureBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverCarouselFigureInputDto discoverCarouselFigureDto);

	/**
	 * 通过id批量删除记录
	 */
	public int batchRemove(String ids);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<DiscoverCarouselFigureOutputDto> findPage(DiscoverCarouselFigurePageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 获取轮播图信息的分页数据
	*/
	public PageModel<DiscoverCarouselFigureOutputDto> findInfoPage(DiscoverCarouselFigurePageInputDto pageInputDto,PageModel pageModel);
	
	public int getMaxSortNo();
	/**
	 * 更新记录
	 */
	public int update(DiscoverCarouselFigureInputDto discoverCarouselFigureDto);
	
	DiscoverCarouselFigureOutputDto getPrevious(DiscoverCarouselFigureInputDto discoverCarouselFigure);
	
	DiscoverCarouselFigureOutputDto getNext(DiscoverCarouselFigureInputDto discoverCarouselFigure);
	
	/**
	 * 轮播图，排序号加1
	 * @return
	 */
	public int updateSortNo();
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverCarouselFigureOutputDto getById(String id);
}

