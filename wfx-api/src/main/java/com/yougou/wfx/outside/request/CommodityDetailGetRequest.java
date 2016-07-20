package com.yougou.wfx.outside.request;

/**
 * 商品详情请求参数
 * 
 * @author li.lq
 * @Date 2016年4月20日
 */
public class CommodityDetailGetRequest extends BaseRequest {
	private static final long serialVersionUID = -1140448559396274185L;
	/*** 商品编号 */
	private String noArr[];

	public String[] getNoArr() {
		return noArr;
	}
	public void setNoArr(String[] noArr) {
		this.noArr = noArr;
	}
}
