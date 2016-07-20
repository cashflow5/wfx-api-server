package com.yougou.wfx.commodity.dto.output;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.OutputDto;

public class CommoditySearchOutputDto extends OutputDto{
	
	private static final long serialVersionUID = 1L;

	/** 商品ID  */
	private String commodityId;
	
	/** 商品名称  */
	private String commodityName;
	
	/** 商品状态*/
	private String commodityStatus;
	
	/** 款号*/
	private String styleNo;
	
	/** 上架日期*/
	private Date saleDate;
	
	/** 销售年份*/
	private String marketYear;
	
	/** 颜色*/
	private String commodityColor;
	
	/** 市场价*/
	private Double marketPrice;
	
	/** 优购价*/
	private Double ygPrice;
	
	/** 微分销价*/
	private Double wfxPrice;
	
	/** 销量*/
	private Integer saleNum;
	
	/** 库存*/
	private Integer stockNum;
	
	/** 默认图片*/
	private String defaultPic;
	
	/** 小图*/
	private String smallPic;
	
	/** 品牌ID*/
	private String brandId;
	
	/** 品牌名称*/
	private String brandName;
	
	/** 品牌编号*/
	private String brandNo;
	
	/** 品牌英文名称*/
	private String enBrandName;
	
	/** 品牌拼写名称*/
	private String speelBrandName;	
	
	/** 供应商编码*/
	private String supplierCode;
	
	/** 尺码*/
	private List<String> sizes;
	
	/** 一级分类名称*/
	private String firstCategoryName;
	
	/** 二级分类名称 */
	private String secondCategoryName;
	
	/** 三级分类名称*/
	private String thirdCategoryName;
	
	/** 一级分类编码*/
	private String firstCategoryNo;
	
	/** 二级分类编码*/
	private String secondCategoryNo;
	
	/** 三级分类编码*/
	private String thirdCategoryNo;
	
	/** 一级分类结构*/
	private String firstCategoryStruct;
	
	/** 二级分类结构 */
	private String secodeCategoryStruct;
	
	/** 三级分类结构*/
	private String thirdCategoryStruct;
	
	/** 微分销一级分类编码 多个逗号分开*/
	private String wfxFirstCatNo;
	
	/** 微分销一级分类名称 多个逗号分开*/
	private String wfxFirstCatName;
	
	/** 微分销二级级分类编码 多个逗号分开*/
	private String wfxSecondCatNo;
	
	/** 微分销二级级分类名称 多个逗号分开 */
	private String wfxSecondCatName;
	
	/** 属性列表 item_no=,item_value=,val_no=,val_value=*/
	private List<Map<String,String>> propList;
	
	/** 分销商ID列表*/
	private List<String> sellerIdList;

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCommodityStatus() {
		return commodityStatus;
	}

	public void setCommodityStatus(String commodityStatus) {
		this.commodityStatus = commodityStatus;
	}

	public String getStyleNo() {
		return styleNo;
	}

	public void setStyleNo(String styleNo) {
		this.styleNo = styleNo;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getMarketYear() {
		return marketYear;
	}

	public void setMarketYear(String marketYear) {
		this.marketYear = marketYear;
	}

	public String getCommodityColor() {
		return commodityColor;
	}

	public void setCommodityColor(String commodityColor) {
		this.commodityColor = commodityColor;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getYgPrice() {
		return ygPrice;
	}

	public void setYgPrice(Double ygPrice) {
		this.ygPrice = ygPrice;
	}

	public Double getWfxPrice() {
		return wfxPrice;
	}

	public void setWfxPrice(Double wfxPrice) {
		this.wfxPrice = wfxPrice;
	}

	public Integer getSaleNum() {
		return saleNum;
	}

	public void setSaleNum(Integer saleNum) {
		this.saleNum = saleNum;
	}

	public Integer getStockNum() {
		return stockNum;
	}

	public void setStockNum(Integer stockNum) {
		this.stockNum = stockNum;
	}

	public String getDefaultPic() {
		return defaultPic;
	}

	public void setDefaultPic(String defaultPic) {
		this.defaultPic = defaultPic;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getBrandNo() {
		return brandNo;
	}

	public void setBrandNo(String brandNo) {
		this.brandNo = brandNo;
	}

	public String getEnBrandName() {
		return enBrandName;
	}

	public void setEnBrandName(String enBrandName) {
		this.enBrandName = enBrandName;
	}

	public String getSpeelBrandName() {
		return speelBrandName;
	}

	public void setSpeelBrandName(String speelBrandName) {
		this.speelBrandName = speelBrandName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public List<String> getSizes() {
		return sizes;
	}

	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}

	public String getFirstCategoryName() {
		return firstCategoryName;
	}

	public void setFirstCategoryName(String firstCategoryName) {
		this.firstCategoryName = firstCategoryName;
	}

	public String getSecondCategoryName() {
		return secondCategoryName;
	}

	public void setSecondCategoryName(String secondCategoryName) {
		this.secondCategoryName = secondCategoryName;
	}

	public String getThirdCategoryName() {
		return thirdCategoryName;
	}

	public void setThirdCategoryName(String thirdCategoryName) {
		this.thirdCategoryName = thirdCategoryName;
	}

	public String getFirstCategoryNo() {
		return firstCategoryNo;
	}

	public void setFirstCategoryNo(String firstCategoryNo) {
		this.firstCategoryNo = firstCategoryNo;
	}

	public String getSecondCategoryNo() {
		return secondCategoryNo;
	}

	public void setSecondCategoryNo(String secondCategoryNo) {
		this.secondCategoryNo = secondCategoryNo;
	}

	public String getThirdCategoryNo() {
		return thirdCategoryNo;
	}

	public void setThirdCategoryNo(String thirdCategoryNo) {
		this.thirdCategoryNo = thirdCategoryNo;
	}

	public String getFirstCategoryStruct() {
		return firstCategoryStruct;
	}

	public void setFirstCategoryStruct(String firstCategoryStruct) {
		this.firstCategoryStruct = firstCategoryStruct;
	}

	public String getSecodeCategoryStruct() {
		return secodeCategoryStruct;
	}

	public void setSecodeCategoryStruct(String secodeCategoryStruct) {
		this.secodeCategoryStruct = secodeCategoryStruct;
	}

	public String getThirdCategoryStruct() {
		return thirdCategoryStruct;
	}

	public void setThirdCategoryStruct(String thirdCategoryStruct) {
		this.thirdCategoryStruct = thirdCategoryStruct;
	}

	public String getWfxFirstCatNo() {
		return wfxFirstCatNo;
	}

	public void setWfxFirstCatNo(String wfxFirstCatNo) {
		this.wfxFirstCatNo = wfxFirstCatNo;
	}

	public String getWfxFirstCatName() {
		return wfxFirstCatName;
	}

	public void setWfxFirstCatName(String wfxFirstCatName) {
		this.wfxFirstCatName = wfxFirstCatName;
	}

	public String getWfxSecondCatNo() {
		return wfxSecondCatNo;
	}

	public void setWfxSecondCatNo(String wfxSecondCatNo) {
		this.wfxSecondCatNo = wfxSecondCatNo;
	}

	public String getWfxSecondCatName() {
		return wfxSecondCatName;
	}

	public void setWfxSecondCatName(String wfxSecondCatName) {
		this.wfxSecondCatName = wfxSecondCatName;
	}

	public List<Map<String, String>> getPropList() {
		return propList;
	}

	public void setPropList(List<Map<String, String>> propList) {
		this.propList = propList;
	}

	public List<String> getSellerIdList() {
		return sellerIdList;
	}

	public void setSellerIdList(List<String> sellerIdList) {
		this.sellerIdList = sellerIdList;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	
}
