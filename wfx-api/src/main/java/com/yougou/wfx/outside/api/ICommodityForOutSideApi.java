package com.yougou.wfx.outside.api;

import com.yougou.wfx.outside.request.CommodityDetailGetRequest;
import com.yougou.wfx.outside.request.CommodityGetRequest;
import com.yougou.wfx.outside.request.SkuQuantityUpdateRequest;
import com.yougou.wfx.outside.response.CommodityDetailGetResponse;
import com.yougou.wfx.outside.response.CommodityGetResponse;
import com.yougou.wfx.outside.response.SkuQuantityUpdateResponse;

/**
 * 提供给第三方平台商品相关的接口
 * 
 * @author li.lq
 * @Date 2016年4月19日
 */
public interface ICommodityForOutSideApi {

	/**
	 * 商品列表下载接口
	 * 
	 * @param request
	 * @return
	 */
	public CommodityGetResponse queryCommodityList(CommodityGetRequest request) ;

	/**
	 * 商品详情查询接口		
	 * 
	 * @param request
	 * @return
	 */
	public CommodityDetailGetResponse queryCommodityDetailList(CommodityDetailGetRequest request) ;
	
	/**
	 * SKU库存修改接口		
	 * 
	 * @param request
	 * @return
	 */
	public SkuQuantityUpdateResponse updateSkuQuantity(SkuQuantityUpdateRequest request);
	
}
