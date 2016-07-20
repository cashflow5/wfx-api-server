package com.yougou.wfx.outside.domain;

import java.util.List;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 商品详情信息
 * 
 * @author li.lq
 * @Date 2016年4月19日
 */
public class CommodityDetail  extends OutputDto {

	private static final long serialVersionUID = 1L;
	/*** 商品信息 */
	private	Commodity commodity;
	/*** 所有货品SKU库存之和 */
	private int stock;
	/*** 货品集合信息 */
	private List<CommodityProduct> commodityProducts;

	public List<CommodityProduct> getCommodityProducts() {
		return commodityProducts;
	}
	public void setCommodityProducts(List<CommodityProduct> commodityProducts) {
		this.commodityProducts = commodityProducts;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Commodity getCommodity() {
		return commodity;
	}
	public void setCommodity(Commodity commodity) {
		this.commodity = commodity;
	}
}
