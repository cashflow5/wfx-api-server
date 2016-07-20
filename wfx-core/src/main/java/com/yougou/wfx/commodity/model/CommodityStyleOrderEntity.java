 /*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * CommodityStyleEntity
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public class CommodityStyleOrderEntity implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	//按销量排序
	private String saleQuantityOrder;
	//按新品排序
	private String updateOrder;
	//按价格排序
	private String priceOrder;
	//分类编码
	private String catNo;
	//品牌编码
	private String brandNo;
	//店铺id
	private String shopId;
	//属性项名称
	private String propItemName; 
	//人气
	private String popularity;
	//分类编码list
	private List<String> catNoList;
	//品牌编码list
	private List<String> brandNoList;
	//性别list 
	private List<String> sexNoList;
	//价格区间list
	private Map<Integer,Integer> priceMap;
	
	//尺码list
	private List<String>  sizeNoList; 
	//颜色
	private List<String> specNameList;
	//属性项
	private List<String> propValueNolist;
	//分类id
	private String catId;
	public String getCatId() {
		return catId;
	}
	public void setCatId(String catId) {
		this.catId = catId;
	}
	public List<String> getCatNoList() {
		return catNoList;
	}
	public void setCatNoList(List<String> catNoList) {
		this.catNoList = catNoList;
	}
	public List<String> getBrandNoList() {
		return brandNoList;
	}
	public void setBrandNoList(List<String> brandNoList) {
		this.brandNoList = brandNoList;
	}
	public List<String> getSexNoList() {
		return sexNoList;
	}
	public void setSexNoList(List<String> sexNoList) {
		this.sexNoList = sexNoList;
	}

	public List<String> getSizeNoList() {
		return sizeNoList;
	}
	public void setSizeNoList(List<String> sizeNoList) {
		this.sizeNoList = sizeNoList;
	}
	public List<String> getSpecNameList() {
		return specNameList;
	}
	public void setSpecNameList(List<String> specNameList) {
		this.specNameList = specNameList;
	}
	public List<String> getPropValueNolist() {
		return propValueNolist;
	}
	public void setPropValueNolist(List<String> propValueNolist) {
		this.propValueNolist = propValueNolist;
	}
	public String getPopularity() {
		return popularity;
	}
	public void setPopularity(String popularity) {
		this.popularity = popularity;
	}
	public String getPropItemName() {
		return propItemName;
	}
	public void setPropItemName(String propItemName) {
		this.propItemName = propItemName;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getSaleQuantityOrder() {
		return saleQuantityOrder;
	}
	public void setSaleQuantityOrder(String saleQuantityOrder) {
		this.saleQuantityOrder = saleQuantityOrder;
	}
	public String getUpdateOrder() {
		return updateOrder;
	}
	public void setUpdateOrder(String updateOrder) {
		this.updateOrder = updateOrder;
	}
	public String getPriceOrder() {
		return priceOrder;
	}
	public void setPriceOrder(String priceOrder) {
		this.priceOrder = priceOrder;
	}
	public String getCatNo() {
		return catNo;
	}
	public void setCatNo(String catNo) {
		this.catNo = catNo;
	}
	public String getBrandNo() {
		return brandNo;
	}
	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}
	public Map<Integer, Integer> getPriceMap() {
		return priceMap;
	}
	public void setPriceMap(Map<Integer, Integer> priceMap) {
		this.priceMap = priceMap;
	}
}

