package com.yougou.wfx.outside.response;

import java.util.ArrayList;
import java.util.List;

import com.yougou.wfx.outside.domain.Commodity;

/**
 * 
 * @author li.lq
 * @Date 2016年4月19日
 */
public class CommodityGetResponse extends BaseResponse {

	private static final long serialVersionUID = -1558660252045099700L;

	// 订单列表
	private List<Commodity> commodityList=new ArrayList<Commodity>();
	// 总数
	private int totalResults=0;

	public List<Commodity> getCommodityList() {
		return commodityList;
	}

	public void setCommodityList(List<Commodity> commodityList) {
		this.commodityList = commodityList;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
}
