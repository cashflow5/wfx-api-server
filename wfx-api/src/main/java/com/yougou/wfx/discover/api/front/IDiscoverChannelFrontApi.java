 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.api.front;

import java.util.List;
import com.yougou.wfx.discover.dto.output.DiscoverChannelOutputDto;

/**
 * IDiscoverChannelFrontApi
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
public interface IDiscoverChannelFrontApi{

	/**
	 * 通过id查询数据
	 */
	public DiscoverChannelOutputDto getById(String id);
	
	/**
	 * 查所有显示的频道（不含首页）
	 */
	public List<DiscoverChannelOutputDto> getAllShowChannels();
	
}

