package com.yougou.wfx.order.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * C端用户中心，订单各状态总数
 * @author zhang.f1
 *
 */
public class OrderCountOutputDto extends OutputDto {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 所有订单
	 */
	private Integer AllOrderCount;
	
	/**
	 * 待付款订单
	 */
	private Integer notPayOrderCount;
	
	/**
	 * 已发货订单
	 */
	private Integer shipedOrderCount;
	
	/**
	 * 退款/售后  订单
	 */
	private Integer refundOrderCount;
	
	private Integer waitDeliverOrderCount;
	
	public Integer getAllOrderCount() {
		return AllOrderCount;
	}

	public void setAllOrderCount(Integer allOrderCount) {
		AllOrderCount = allOrderCount;
	}

	public Integer getNotPayOrderCount() {
		return notPayOrderCount;
	}

	public void setNotPayOrderCount(Integer notPayOrderCount) {
		this.notPayOrderCount = notPayOrderCount;
	}

	public Integer getShipedOrderCount() {
		return shipedOrderCount;
	}

	public void setShipedOrderCount(Integer shipedOrderCount) {
		this.shipedOrderCount = shipedOrderCount;
	}

	public Integer getRefundOrderCount() {
		return refundOrderCount;
	}

	public void setRefundOrderCount(Integer refundOrderCount) {
		this.refundOrderCount = refundOrderCount;
	}

	public Integer getWaitDeliverOrderCount() {
		return waitDeliverOrderCount;
	}

	public void setWaitDeliverOrderCount(Integer waitDeliverOrderCount) {
		this.waitDeliverOrderCount = waitDeliverOrderCount;
	}

	
}
