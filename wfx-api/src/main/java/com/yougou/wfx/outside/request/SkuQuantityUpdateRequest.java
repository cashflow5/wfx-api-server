package com.yougou.wfx.outside.request;

/**
 * SKU库存修改请求参数
 * 
 * @author zheng.qq
 * @Date 2016年4月19日
 */
public class SkuQuantityUpdateRequest extends BaseRequest {
	private static final long serialVersionUID = -1140448559396274185L;
	/**
	 * 商品编码
	 */
	private String commodityNo;
	/**
	 * 第三方货品条码
	 */
	private String outerSkuId;
	/**
	 * 库存同步数量
	 */
	private Integer quantity;
	/**
	 * 库存更新方式，1代表全量，2代表增量，如果不填，默认为全量。
	 */
	private Integer type = 1;
	
	public String getCommodityNo() {
		return commodityNo;
	}
	public void setCommodityNo(String commodityNo) {
		this.commodityNo = commodityNo;
	}
	public String getOuterSkuId() {
		return outerSkuId;
	}
	public void setOuterSkuId(String outerSkuId) {
		this.outerSkuId = outerSkuId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
