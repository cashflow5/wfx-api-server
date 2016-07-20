package com.yougou.wfx.outside.request;

import java.util.List;

/**
 * <p>Title: OrderLogisticsSendRequest</p>
 * <p>Description: </p>
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
public class LogisticsSendRequest extends BaseRequest{
	private static final long serialVersionUID = -1140448559396274185L;
	
	/**
	 * 发货物流公司编号
	 */
	private String expressCode;
	/**
	 * 发货物流公司名称
	 */
	private String expressName;
	/**
	 * 快递单号
	 */
	private String expressNo;
	
	/**
	 * 退货地址编码
	 */
	private String returnAddressNo;
	
	/**
	 * 发货关联的主订单号
	 */
	private String wfxOrderNo;
	
	/**
	 * 发货关联的订单信息列表(格式：子订单号:数量)
	 */
	private List<String> orderInfoList;

	public String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(String expressCode) {
		this.expressCode = expressCode;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressNo() {
		return expressNo;
	}

	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}

	public String getReturnAddressNo() {
		return returnAddressNo;
	}

	public void setReturnAddressNo(String returnAddressNo) {
		this.returnAddressNo = returnAddressNo;
	}

	public String getWfxOrderNo() {
		return wfxOrderNo;
	}

	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}

	public List<String> getOrderInfoList() {
		return orderInfoList;
	}

	public void setOrderInfoList(List<String> orderInfoList) {
		this.orderInfoList = orderInfoList;
	}

}
