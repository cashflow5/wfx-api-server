package com.yougou.wfx.order.dto.input;

import com.yougou.wfx.dto.base.InputDto;

/**
 * 订单搜索参数类
 * @author wang.zf
 *
 */
public class SellerOrderSearchDto extends InputDto{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 微分销订单编号
	 */
	private String wfxOrderNo;
	/**
	 * 收货人手机号码
	 */
	private String receiverMobile;
	/**
	 * 订单状态：AIT_PAY("WAIT_PAY","待付款"), 
			  WAIT_DELIVER("WAIT_DELIVER","待发货"), 
			  PART_DELIVERED("PART_DELIVERED","部分发货"), 
			  DELIVERED("DELIVERED","已发货"), 
			  TRADE_SUCCESS("TRADE_SUCCESS","交易成功"), 
			  TRADE_CLOSED("TRADE_CLOSED","交易关闭");
			  null:全部
	 * @return
	 */
	private String orderStatus;
	
	/**
	 * 合并查 部分发货和已发货两个状态订单 (1：合并查询)
	 */
	private Integer mergeSearchFlag;
	
	public Integer getMergeSearchFlag() {
		return mergeSearchFlag;
	}
	public void setMergeSearchFlag(Integer mergeSearchFlag) {
		this.mergeSearchFlag = mergeSearchFlag;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getWfxOrderNo() {
		return wfxOrderNo;
	}
	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}
	public String getReceiverMobile() {
		return receiverMobile;
	}
	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}
}
