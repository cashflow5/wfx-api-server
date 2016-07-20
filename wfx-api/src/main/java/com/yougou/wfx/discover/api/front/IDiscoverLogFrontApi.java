 /*
 * 版本信息
 
 * 日期 2016-06-02 20:18:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.discover.dto.input.DiscoverLogInputDto;
import com.yougou.wfx.discover.dto.input.DiscoverLogPageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverLogOutputDto;

/**
 * IDiscoverLogFrontApi
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 20:18:26
 */
public interface IDiscoverLogFrontApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverLogInputDto discoverLogDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<DiscoverLogOutputDto> findPage(DiscoverLogPageInputDto pageInputDto,PageModel<DiscoverLogOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverLogInputDto discoverLogDto);
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverLogOutputDto getById(String id);
}

