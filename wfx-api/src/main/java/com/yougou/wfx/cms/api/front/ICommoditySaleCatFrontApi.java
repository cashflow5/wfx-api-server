 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.front;

import java.util.List;

import com.yougou.wfx.cms.dto.input.CommoditySaleCatPageInputDto;
import com.yougou.wfx.cms.dto.output.CommoditySaleCatOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommoditySaleCatFrontApi
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
public interface ICommoditySaleCatFrontApi{
	
	/**
	 * 查询一级销售分类列表
	 * @return
	 */
	List<CommoditySaleCatOutputDto> queryCommoditySaleCatLevelOneList();
	
	/**
	 * 查询一级分类下面的二级销售分类
	 * @param parentCatId
	 * @return
	 */
	List<CommoditySaleCatOutputDto> queryCommoditySaleCatLevelTwoList(String parentCatId);
	
	/**
	 * 查询一级销售分类列表
	 * @return
	 */
	PageModel<CommoditySaleCatOutputDto> queryCommoditySaleCat(CommoditySaleCatPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 查询一级分类对应的品牌
	 * @param catId
	 * @return
	 */
	List<CommodityBrandOutputDto> queryCommodityBrandByCatId(String catId);
}

