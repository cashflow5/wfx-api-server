 /*
 * 版本信息
 
 * 日期 2016-06-03 11:26:32
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.discover.dto.input.DiscoverArticlePageInputDto;
import com.yougou.wfx.discover.dto.output.DiscoverArticleOutputDto;
/**
 * IDiscoverArticleFrontApi
 * @author luoq
 * @Date 创建时间：2016-06-03 11:26:33
 */
public interface IDiscoverArticleFrontApi{
		
	/**
	 * 获取分页数据-查频道或首页（channelId='0'）下的文章列表
	 * @param pageInputDto:channelId 必输，若是首页，请输入'0'; pageModel 必输
	 * @param
	 * @return 
	 */
	public PageModel<DiscoverArticleOutputDto> findPage(DiscoverArticlePageInputDto pageInputDto,PageModel<DiscoverArticleOutputDto> pageModel);
	
	/**
	 * 通过id查询文章详情
	 */
	public DiscoverArticleOutputDto getById(String id);
	
}

