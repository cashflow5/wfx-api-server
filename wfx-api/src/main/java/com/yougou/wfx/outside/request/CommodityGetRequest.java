package com.yougou.wfx.outside.request;

/**
 * 商品列表查询请求参数
 * 
 * @author li.lq
 * @Date 2016年4月19日
 */
public class CommodityGetRequest extends BaseRequest {
	private static final long serialVersionUID = -1140448559396274185L;
	/*** 商品更新时间(开始) */
	private String updateStartTime;
	/*** 商品更新时间(结束) */
	private String updateEndTime;
	/*** 微分销平台上下架状态 */
	private Integer isOnsale;
	/*** 商品编号 */
	private String noArr[];
	/*** 页码*/
	private Integer pageNo;
	/*** 每页条数*/
	private Integer pageSize;

	public String getUpdateStartTime() {
		return updateStartTime;
	}

	public void setUpdateStartTime(String updateStartTime) {
		this.updateStartTime = updateStartTime;
	}

	public String getUpdateEndTime() {
		return updateEndTime;
	}

	public void setUpdateEndTime(String updateEndTime) {
		this.updateEndTime = updateEndTime;
	}

	public Integer getIsOnsale() {
		return isOnsale;
	}

	public void setIsOnsale(Integer isOnsale) {
		this.isOnsale = isOnsale;
	}

	public String[] getNoArr() {
		return noArr;
	}

	public void setNoArr(String[] noArr) {
		this.noArr = noArr;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
