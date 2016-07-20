package com.yougou.wfx.order.dto.input;

import com.yougou.wfx.dto.base.InputDto;

/**
 * 订单搜索参数类
 * 
 * @author wang.zf
 *
 */
public class OrderSearchDto extends InputDto {

	private static final long serialVersionUID = 1L;
	/**
	 * 订单状态
	 */
	private String status;
	/**
	 * 微分销订单编号
	 */
	private String wfxOrderNo;
	/**
	 * 商品名称
	 */
	private String prodName;
	/**
	 * 商品编号
	 */
	private String prodId;
	/**
	 * 收货人手机号码
	 */
	private String receiverMobile;
	/**
	 * 合并查 部分发货和已发货两个状态订单 (1：合并查询)
	 */
	private Integer mergeSearchFlag;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getWfxOrderNo() {
		return wfxOrderNo;
	}

	public void setWfxOrderNo(String wfxOrderNo) {
		this.wfxOrderNo = wfxOrderNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getReceiverMobile() {
		return receiverMobile;
	}

	public void setReceiverMobile(String receiverMobile) {
		this.receiverMobile = receiverMobile;
	}

	public Integer getMergeSearchFlag() {
		return mergeSearchFlag;
	}

	public void setMergeSearchFlag(Integer mergeSearchFlag) {
		this.mergeSearchFlag = mergeSearchFlag;
	}
}
