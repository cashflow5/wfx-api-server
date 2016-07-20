 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.background;

import java.util.List;

import com.yougou.wfx.discover.dto.input.DiscoverChannelInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverChannelPageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverCarouselFigureOutputDto;
import com.yougou.wfx.discover.dto.output.DiscoverChannelOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * IDiscoverChannelBackgroundApi
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
public interface IDiscoverChannelBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	/**
	 * 批量
	 * @param list
	 * @return
	 */
	public int batchRemove(List<String> list);
	
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverChannelInputDto discoverChannelDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<DiscoverChannelOutputDto> findPage(DiscoverChannelPageInputDto pageInputDto,PageModel<DiscoverChannelOutputDto> pageModel);
	
	public List<DiscoverChannelOutputDto> getChannelList(DiscoverChannelInputDto InputDto);
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverChannelInputDto discoverChannelDto);
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverChannelOutputDto getById(String id);
	
	DiscoverChannelOutputDto getPrevious(DiscoverChannelInputDto InputDto);
	
	DiscoverChannelOutputDto getNext(DiscoverChannelInputDto InputDto);
	
	/**
	 * 获取排序号
	 * @param id
	 * @return
	 */
	public  int getorderMark(String id);
	
	/**
	 * 修改序号
	 * @return
	 */
	public int updateOrderMark();
	/**
	 * 获取总条数
	 */
	public int findPageCount(DiscoverChannelInputDto discoverChannelInputDto);
}

