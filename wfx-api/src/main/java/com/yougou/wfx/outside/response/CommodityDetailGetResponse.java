package com.yougou.wfx.outside.response;

import java.util.ArrayList;
import java.util.List;

import com.yougou.wfx.outside.domain.CommodityDetail;

/**
 * 
 * @author li.lq
 * @Date 2016年4月19日
 */
public class CommodityDetailGetResponse extends BaseResponse {

	private static final long serialVersionUID = -1558660252045099700L;

	// 订单列表
	private List<CommodityDetail> commodityDetailList = new ArrayList<CommodityDetail>();
	// 总数
	private int totalResults = 0;

	public List<CommodityDetail> getCommodityDetailList() {
		return commodityDetailList;
	}

	public void setCommodityDetailList(List<CommodityDetail> commodityDetailList) {
		this.commodityDetailList = commodityDetailList;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
}
