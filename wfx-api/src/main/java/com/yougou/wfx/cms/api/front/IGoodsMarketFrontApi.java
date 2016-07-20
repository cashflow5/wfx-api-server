 /*
 * 版本信息
 
 * 日期 2016-03-25 14:21:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.front;

import java.util.List;

import com.yougou.wfx.cms.dto.output.CarouselFigureOutputDto;
import com.yougou.wfx.cms.dto.output.HotBrandOutputDto;
import com.yougou.wfx.cms.dto.output.HotSaleCatOutputDto;
import com.yougou.wfx.commodity.dto.output.BagOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;


/**
 * ICarouselFigureFrontApi
 * @author lijun
 * @Date 创建时间：2016-03-25 14:21:13
 */
public interface IGoodsMarketFrontApi{
	
	/**
	 * 查询货源市场轮播图列表接口
	 * @return
	 */
	List<CarouselFigureOutputDto> queryCarouselFigureList();
	
	/**
	 * 查询货源市场热门品牌列表接口
	 * @return
	 */
	List<HotBrandOutputDto> queryHotBrandList();
	
	/**
	 * 查询货源市场热门分类列表接口
	 * @return
	 */
	List<HotSaleCatOutputDto> queryHotSaleCatList();
	
	/**
	 * 查询货源市场分销包列表接口
	 * @return
	 */
	List<BagOutputDto> queryBagList();
	
	/**
	 * 根据分销包ID查询货源市场分销包
	 * @param bagId 分销包ID
	 * @return
	 */
	BagOutputDto getBagById(String bagId);
	
	/**
	 * 查询分销包的商品列表
	 * @param bagId 分销包ID
	 * @param pageModel
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> queryBagCommodityList(String bagId, PageModel pageModel);
	
	/**
	 * 根据分销包ID代理分销包所有商品，已代理商品不重复代理，返回实际本次操作代理商品数量
	 * @param bagId 分销包ID
	 * @param sellerId 分销商ID
	 * @return
	 */
	WFXResult<Integer> proxyBagById(String bagId, String sellerId);
	
	/**
	 * 批量代理商品
	 * @param sellerId 分销商ID
	 * @param style  style_no，brand_no，cat_no，years  拼成一个字符串，逗号分隔
	 * @return
	 */
	WFXResult<Integer> batchProxyCommodity(String sellerId, List<String> style);
	
	/**
	 * 一键代理所有商品，已代理商品不重复代理，返回实际本次操作代理商品数量
	 * @param bagId 分销包ID
	 * @param sellerId 分销商ID
	 * @return resultCode: 200,"成功" ; 500,"失败"
	 */
	WFXResult<Integer> proxyAll( String sellerId );
	
}

