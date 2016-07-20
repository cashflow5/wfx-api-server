package com.yougou.wfx.outside.request;

/**
 * <p>Title: OrderUpdateRequest</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderUpdateRequest extends BaseRequest{
	private static final long serialVersionUID = -1140448559396274185L;
	
	/**
	 * 微分销订单号
	 */
	private String wfxOrderNo;
	/**
	 * 订单状态
	 */
	private String status;
	
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

}
