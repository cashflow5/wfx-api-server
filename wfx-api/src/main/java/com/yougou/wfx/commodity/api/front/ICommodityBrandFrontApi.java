 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.front;

import com.yougou.wfx.commodity.dto.input.CommodityBrandPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommodityBrandFrontApi
 * @author li.j1
 * @Date 创建时间：2016-03-23 18:39:34
 */
public interface ICommodityBrandFrontApi{
	
	
	/**
	 * 获取分页数据
	 */
	PageModel<CommodityBrandOutputDto> findPage(CommodityBrandPageInputDto pageInputDto,PageModel pageModel);
			
}

