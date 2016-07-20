 /*
 * 版本信息
 
 * 日期 2016-03-24 11:14:00
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.front;

import com.yougou.wfx.commodity.dto.input.CommodityCatb2cPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityExtendPropItemOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityExtendPropValueOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommodityCatb2cFrontApi
 * @author li.j1
 * @Date 创建时间：2016-03-24 11:14:01
 */
public interface ICommodityCatb2cFrontApi{
	
	/**
	 * 获取基础分类分页数据
	 */
	PageModel<CommodityCatb2cOutputDto> findCommodityCatb2cPage(CommodityCatb2cPageInputDto pageInputDto,PageModel<CommodityCatb2cOutputDto> pageModel);
	
	/**
	 * 获取商品属性项列表
	 * @param pageModel
	 * @return
	 */
	PageModel<CommodityExtendPropItemOutputDto> findCommodityExtendPropItemPage(PageModel pageModel);
	
	/**
	 * 获取商品属性项值列表
	 * @param pageModel
	 * @return
	 */
	PageModel<CommodityExtendPropValueOutputDto> findCommodityExtendPropValuePage(PageModel pageModel);
}

