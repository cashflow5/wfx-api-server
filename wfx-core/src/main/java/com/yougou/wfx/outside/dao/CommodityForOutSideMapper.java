package com.yougou.wfx.outside.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.outside.domain.Commodity;
import com.yougou.wfx.outside.domain.CommodityDetail;
import com.yougou.wfx.outside.request.CommodityDetailGetRequest;
import com.yougou.wfx.outside.request.CommodityGetRequest;
import com.yougou.wfx.outside.request.SkuQuantityUpdateRequest;

/**
 * CommodityForOutSideMapper
 * 
 * @author li.lq
 * @Date 2016年4月20日
 */
public interface CommodityForOutSideMapper {

	List<Commodity> queryCommodityList(CommodityGetRequest request, RowBounds rowBounds);

	int queryCommodityCount(CommodityGetRequest request);
	
	List<CommodityDetail> queryCommodityDetailList(CommodityDetailGetRequest request);
	
	int queryCommodityDetailCount(CommodityDetailGetRequest request);
	
	int updateSkuQuantity(@Param(value = "request") SkuQuantityUpdateRequest request, @Param(value = "updateTime") Date date);
}
