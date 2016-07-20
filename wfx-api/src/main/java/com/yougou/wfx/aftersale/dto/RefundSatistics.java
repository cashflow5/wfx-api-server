package com.yougou.wfx.aftersale.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.BaseDto;
import com.yougou.wfx.enums.RefundStatusEnum;

public class RefundSatistics extends BaseDto{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 子订单id
	 */
	private String orderDetailId;
	/**
	 * 统计退款数量
	 */
	private Integer returnNum;
	/**
	 * 统计退款金额
	 */
	private Double returnFee;
	/**
	 * 动态查询参数
	 */
	private Map paramMap;
	
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public Integer getReturnNum() {
		return returnNum;
	}
	public void setReturnNum(Integer returnNum) {
		this.returnNum = returnNum;
	}
	public Double getReturnFee() {
		return returnFee;
	}
	public void setReturnFee(Double returnFee) {
		this.returnFee = returnFee;
	}
	public Map getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}
	public void initDefaultStatus() {
		List<String> statusList = new ArrayList<String>();
		statusList.add(RefundStatusEnum.APPLYING.getKey());
		statusList.add(RefundStatusEnum.PENDING_DELIVERD.getKey());
		statusList.add(RefundStatusEnum.SUCCESS_REFUND.getKey());
		statusList.add(RefundStatusEnum.UNDER_REFUND.getKey());
		Map paramMap = new HashMap();
		paramMap.put("statusList", statusList);
		setParamMap(paramMap);
	}
	public void initSuccessStatus() {
		List<String> statusList = new ArrayList<String>();
		statusList.add(RefundStatusEnum.SUCCESS_REFUND.getKey());
		Map paramMap = new HashMap();
		paramMap.put("statusList", statusList);
		setParamMap(paramMap);
	}
}
