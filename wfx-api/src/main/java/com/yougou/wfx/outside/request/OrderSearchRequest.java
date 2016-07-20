package com.yougou.wfx.outside.request;

/**
 * <p>Title: OrderSearchRequest</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderSearchRequest extends BaseRequest{
	private static final long serialVersionUID = -1140448559396274185L;
	
	/**
	 * 微分销订单号
	 */
	private String wfxOrderNo;
	
	/**
	 * 订单状态
	 */
	private String status;
	/**
	 * 开始时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	/**
	 * 页码
	 */
	private Integer pageNo = 1;
	/**
	 * 每页条数
	 */
	private Integer pageSize = 50;
	
	public String getWfxOrderNo() {
		return wfxOrderNo;
	}
	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
