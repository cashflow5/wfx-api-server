package com.yougou.wfx.outside.request;

/**
 * <p>Title: OrderGetRequest</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class OrderGetRequest extends BaseRequest{
	private static final long serialVersionUID = -1140448559396274185L;
	
	/**
	 * 微分销订单号
	 */
	private String wfxOrderNo;


	public String getWfxOrderNo() {
		return wfxOrderNo;
	}
	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}

}
