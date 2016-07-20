 /*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dto.input;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

/**
 * CommodityStylePageInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public class CommodityStylePageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * 商品名称
	 */
	private String commodityName;
	/**
	 * 商品别名
	 */
	private String alias;
	/**
	 * 审核(1审核,0未审核)
	 */
	private Integer isAudit;
	/**
	 * 商品简介
	 */
	private String prodBriff;
	/**
	 * prodDesc
	 */
	private String prodDesc;
	/**
	 * 商品单位编码 
	 */
	private String unitNo;
	/**
	 * 干扰排序值
	 */
	private Integer dsno;
	/**
	 * 上架时间
	 */
	private Date showDate;
	/**
	 * 下架时间
	 */
	private Date downDate;
	/**
	 * preShowDate
	 */
	private Date preShowDate;
	/**
	 * 0下架，1上架
	 */
	private Integer showind;
	/**
	 * 1下架(停售),2上架(在售)，3停用,(启用重新变成1),4预售,5待进货,6待售
	 */
	private Integer commodityStatus;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改时间
	 */
	private Date updateDate;
	/**
	 * 1=有效，0=删除
	 */
	private Integer deleteFlag;
	/**
	 * 商品编号
	 */
	private String no;
	/**
	 * 更新时间戳 
	 */
	private java.lang.Long updateTimestamp;
	/**
	 * 分类编码
	 */
	private String catNo;
	/**
	 * 品牌编码
	 */
	private String brandNo;
	/**
	 * 是否已经生成图片(0为未生成图片名称，1已经导出Excel但是未上传图片，2已经上传图片)
	 */
	private Integer isGenImage;
	/**
	 * 分类结构字符串
	 */
	private String catStructname;
	/**
	 * 价格
	 */
	private Double minPrice;
	/**
	 * 分类名称
	 */
	private String catName;
	/**
	 * 售销数量
	 */
	private Integer salesQuantity;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 市场价
	 */
	private Double publicPrice;
	/**
	 * 最高价格
	 */
	private Double maxPrice;
	/**
	 * 默认显示图片(搜索使用)
	 */
	private String defaultPic;
	/**
	 * 商品单位名称
	 */
	private String unitName;
	/**
	 * 默认显示大图
	 */
	private String picBig;
	/**
	 * 默认显示小图
	 */
	private String picSmall;
	/**
	 * 发布预状态(1立即发布,0预发布)
	 */
	private Integer preShowStatus;
	/**
	 * 商品类型 
	 */
	private Integer commodityType;
	/**
	 * 关键词
	 */
	private String keywords;
	/**
	 * 出品年份
	 */
	private String years;
	/**
	 * 供应商款色编码
	 */
	private String supplierCode;
	/**
	 * 价格范围 
	 */
	private String priceScpe;
	/**
	 * 销售价
	 */
	private Double salePrice;
	/**
	 * 成本价
	 */
	private Double costPrice;
	/**
	 * 款号
	 */
	private String styleNo;
	/**
	 * 规格编码
	 */
	private String specNo;
	/**
	 * 规格名称(一般为颜色的名称)
	 */
	private String specName;
	/**
	 * 鞋跟名称
	 */
	private String heelName;
	/**
	 * 鞋跟编码
	 */
	private String heelNo;
	/**
	 * 更新者
	 */
	private String updatePerson;
	/**
	 * seoKeyword
	 */
	private String seoKeyword;
	/**
	 * seoTitle
	 */
	private String seoTitle;
	/**
	 * seoDescription
	 */
	private String seoDescription;
	/**
	 * 颜色系列号 
	 */
	private String colorSeriesNo;
	/**
	 * 所属站点编号,yg : 优购平台,ol : outlets平台 
	 */
	private Integer sortNo;
	/**
	 * 打标关联
	 */
	private String markid;
	/**
	 * 颜色组成,单选颜色，就和specName一致,多选颜色，就为颜色1+颜色2 ，specName就为拼色
	 */
	private String colorDesc;
	/**
	 * 成本价2
	 */
	private Double costPrice2;
	/**
	 * 尺码规范编号，tbl_commodity_code.code_no
	 */
	private String codeNo;
	/**
	 * 商品上下架触发生成静态页时间戳
	 */
	private String staticTimestamp;
	/**
	 * 商品价格资料标示,0:基础资料；1:销售资料
	 */
	private Integer priceFlag;
	/**
	 * 商品图片资料标示,0:基础资料；1:销售资料
	 */
	private Integer picFlag;
	/**
	 * 商品销售资料标示,0:基础资料；1:销售资料
	 */
	private Integer saleFlag;
	/**
	 * 商品基础资料标示,0:基础资料；1:销售资料
	 */
	private Integer baseFlag;
	/**
	 * 首次销售时间
	 */
	private Date firstSellDate;
	/**
	 * 订单配送方(0.优购、1.商家)
	 */
	private Integer orderDistributionSide;
	/**
	 * 商家编号
	 */
	private String merchantCode;
	/**
	 * 对应尺码表id
	 */
	private Integer sizeChartId;
	/**
	 * 抽样标志，0是不需要抽样，1是需要抽样
	 */
	private Integer sampleFlag;
	/**
	 * 是否供应商提供照片，0是不提供，1是提供
	 */
	private Integer suppliersPicFlag;
	/**
	 * 1：客户端导入，2：招商导入
	 */
	private Integer sourceFlag;
	/**
	 * 所属站点编号,yg : 优购平台,ol : outlets平台
	 */
	private String siteNo;
	/**
	 * 是否存在有效地采购单,1存在 0不存在
	 */
	private Integer existsPurchase;
	/**
	 * 调价时，0/NULL自动切平台,1手动切平台
	 */
	private Integer isSiteNoManual;
	/**
	 * 是否存在库存：1存在0不存在
	 */
	private Integer existsInventory;
	/**
	 * 下架原因：1,2,3,4:产品质量问题,其它渠道做活动,产品资料（宝贝描述）有问题需修改,售罄不补货
	 */
	private Integer downReason;
	/**
	 * 卖点
	 */
	private String sellingPoint;
	/**
	 * 商品是否直销，0否；1是
	 */
	private Integer isDirectMarketing;
	/**
	 * 税号
	 */
	private String taxNo;
	/**
	 * 否为回收站商品,0否（默认为0）,1是
	 */
	private Integer recyclebinFlag;
	/**
	 * 吊牌价扩展，供外国货展示价格使用
	 */
	private Double outerMarkPrice;
	/**
	 * 销售价扩展，供外国货展示价格使用
	 */
	private Double outerSellPrice;
	/**
	 * 是否微分销商品,1是，0否
	 */
	private Integer isWfxCommodity;
	/**
	 * 微分销商品编码
	 */
	private String wfxCommodityNo;
	/**
	 * 微分销平台上下架状态
	 */
	private Integer isOnsale;
	/**
	 * wfxPrice
	 */
	private Double wfxPrice;
	/**
	 * 添加到微分销时间
	 */
	private Date addWfxTime;
	/**
	 * 添加到微分销姓名
	 */
	private String addWfxUser;
	/**
	 * 销量
	 */
	private Integer saleQuantity;
	/**
	 * 人气
	 */
	private Integer popularity;
	/**
	 * 代理人数
	 */
	private Integer proxyQuantity;
	/**
	 * 供应商id
	 */
	private String supplierId;
	/**
	 * 一级分类
	 */
	private String levelone;
	/**
	 * 二级分类
	 */
	private String leveltwo;
	
	/**
	 * 三级分类
	 */
	private String levelthree;
	/**
	 * 微分销一级分类
	 */
	
	private String wfxLevelOne;
	/**
	 * 微分销二级分类
	 */
	private String wfxLevelTwo;
	/**
	 * 微分销三级分类
	 */
	private String wfxLevelThree;
	
	 /**
     * 微分销成本价
     */
    private Double  wfxCostPrice;
	/**
	 * 可分配佣金金额
	 */
	private Double distributableMoney;
	
	public Double getWfxCostPrice() {
		return wfxCostPrice;
	}

	public void setWfxCostPrice(Double wfxCostPrice) {
		this.wfxCostPrice = wfxCostPrice;
	}

	public Double getDistributableMoney() {
		return distributableMoney;
	}

	public void setDistributableMoney(Double distributableMoney) {
		this.distributableMoney = distributableMoney;
	}

	public String getWfxLevelOne() {
		return wfxLevelOne;
	}

	public void setWfxLevelOne(String wfxLevelOne) {
		this.wfxLevelOne = wfxLevelOne;
	}

	public String getWfxLevelTwo() {
		return wfxLevelTwo;
	}

	public void setWfxLevelTwo(String wfxLevelTwo) {
		this.wfxLevelTwo = wfxLevelTwo;
	}

	public String getWfxLevelThree() {
		return wfxLevelThree;
	}

	public void setWfxLevelThree(String wfxLevelThree) {
		this.wfxLevelThree = wfxLevelThree;
	}

	

	
	public String getLevelone() {
		return levelone;
	}

	public void setLevelone(String levelone) {
		this.levelone = levelone;
	}

	public String getLeveltwo() {
		return leveltwo;
	}

	public void setLeveltwo(String leveltwo) {
		this.leveltwo = leveltwo;
	}

	public String getLevelthree() {
		return levelthree;
	}

	public void setLevelthree(String levelthree) {
		this.levelthree = levelthree;
	}

	
	public CommodityStylePageInputDto(){
	}

	public CommodityStylePageInputDto(
		String id
	){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setCommodityName(String value) {
		this.commodityName = value;
	}
	
	public String getCommodityName() {
		return this.commodityName == null ? null : this.commodityName.trim();
	}
	public void setAlias(String value) {
		this.alias = value;
	}
	
	public String getAlias() {
		return this.alias == null ? null : this.alias.trim();
	}
	public void setIsAudit(Integer value) {
		value = value == null ? 0 : value;
		this.isAudit = value;
	}
	
	public Integer getIsAudit() {
		return this.isAudit == null ? 0 : this.isAudit;
	}
	public void setProdBriff(String value) {
		this.prodBriff = value;
	}
	
	public String getProdBriff() {
		return this.prodBriff == null ? null : this.prodBriff.trim();
	}
	public void setProdDesc(String value) {
		this.prodDesc = value;
	}
	
	public String getProdDesc() {
		return this.prodDesc == null ? null : this.prodDesc.trim();
	}
	public void setUnitNo(String value) {
		this.unitNo = value;
	}
	
	public String getUnitNo() {
		return this.unitNo == null ? null : this.unitNo.trim();
	}
	public void setDsno(Integer value) {
		value = value == null ? 0 : value;
		this.dsno = value;
	}
	
	public Integer getDsno() {
		return this.dsno == null ? 0 : this.dsno;
	}
	public void setShowDate(Date value) {
		this.showDate = value;
	}
	
	public Date getShowDate() {
		return this.showDate;
	}
	
	public String getStringShowDate() {
		if(this.showDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.showDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setDownDate(Date value) {
		this.downDate = value;
	}
	
	public Date getDownDate() {
		return this.downDate;
	}
	
	public String getStringDownDate() {
		if(this.downDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.downDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setPreShowDate(Date value) {
		this.preShowDate = value;
	}
	
	public Date getPreShowDate() {
		return this.preShowDate;
	}
	
	public String getStringPreShowDate() {
		if(this.preShowDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.preShowDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setShowind(Integer value) {
		value = value == null ? 0 : value;
		this.showind = value;
	}
	
	public Integer getShowind() {
		return this.showind == null ? 0 : this.showind;
	}
	public void setCommodityStatus(Integer value) {
		value = value == null ? 0 : value;
		this.commodityStatus = value;
	}
	
	public Integer getCommodityStatus() {
		return this.commodityStatus == null ? 0 : this.commodityStatus;
	}
	public void setCreateDate(Date value) {
		this.createDate = value;
	}
	
	public Date getCreateDate() {
		return this.createDate;
	}
	
	public String getStringCreateDate() {
		if(this.createDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setUpdateDate(Date value) {
		this.updateDate = value;
	}
	
	public Date getUpdateDate() {
		return this.updateDate;
	}
	
	public String getStringUpdateDate() {
		if(this.updateDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.updateDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setDeleteFlag(Integer value) {
		value = value == null ? 0 : value;
		this.deleteFlag = value;
	}
	
	public Integer getDeleteFlag() {
		return this.deleteFlag == null ? 0 : this.deleteFlag;
	}
	public void setNo(String value) {
		this.no = value;
	}
	
	public String getNo() {
		return this.no == null ? null : this.no.trim();
	}
	public void setUpdateTimestamp(java.lang.Long value) {
		this.updateTimestamp = value;
	}
	
	public java.lang.Long getUpdateTimestamp() {
		return this.updateTimestamp;
	}
	public void setCatNo(String value) {
		this.catNo = value;
	}
	
	public String getCatNo() {
		return this.catNo == null ? null : this.catNo.trim();
	}
	public void setBrandNo(String value) {
		this.brandNo = value;
	}
	
	public String getBrandNo() {
		return this.brandNo == null ? null : this.brandNo.trim();
	}
	public void setIsGenImage(Integer value) {
		value = value == null ? 0 : value;
		this.isGenImage = value;
	}
	
	public Integer getIsGenImage() {
		return this.isGenImage == null ? 0 : this.isGenImage;
	}
	public void setCatStructname(String value) {
		this.catStructname = value;
	}
	
	public String getCatStructname() {
		return this.catStructname == null ? null : this.catStructname.trim();
	}
	public void setMinPrice(Double value) {
		this.minPrice = value;
	}
	
	public Double getMinPrice() {
		return this.minPrice;
	}
	public void setCatName(String value) {
		this.catName = value;
	}
	
	public String getCatName() {
		return this.catName == null ? null : this.catName.trim();
	}
	public void setSalesQuantity(Integer value) {
		value = value == null ? 0 : value;
		this.salesQuantity = value;
	}
	
	public Integer getSalesQuantity() {
		return this.salesQuantity == null ? 0 : this.salesQuantity;
	}
	public void setBrandName(String value) {
		this.brandName = value;
	}
	
	public String getBrandName() {
		return this.brandName == null ? null : this.brandName.trim();
	}
	public void setPublicPrice(Double value) {
		this.publicPrice = value;
	}
	
	public Double getPublicPrice() {
		return this.publicPrice;
	}
	public void setMaxPrice(Double value) {
		this.maxPrice = value;
	}
	
	public Double getMaxPrice() {
		return this.maxPrice;
	}
	public void setDefaultPic(String value) {
		this.defaultPic = value;
	}
	
	public String getDefaultPic() {
		return this.defaultPic == null ? null : this.defaultPic.trim();
	}
	public void setUnitName(String value) {
		this.unitName = value;
	}
	
	public String getUnitName() {
		return this.unitName == null ? null : this.unitName.trim();
	}
	public void setPicBig(String value) {
		this.picBig = value;
	}
	
	public String getPicBig() {
		return this.picBig == null ? null : this.picBig.trim();
	}
	public void setPicSmall(String value) {
		this.picSmall = value;
	}
	
	public String getPicSmall() {
		return this.picSmall == null ? null : this.picSmall.trim();
	}
	public void setPreShowStatus(Integer value) {
		value = value == null ? 0 : value;
		this.preShowStatus = value;
	}
	
	public Integer getPreShowStatus() {
		return this.preShowStatus == null ? 0 : this.preShowStatus;
	}
	public void setCommodityType(Integer value) {
		value = value == null ? 0 : value;
		this.commodityType = value;
	}
	
	public Integer getCommodityType() {
		return this.commodityType == null ? 0 : this.commodityType;
	}
	public void setKeywords(String value) {
		this.keywords = value;
	}
	
	public String getKeywords() {
		return this.keywords == null ? null : this.keywords.trim();
	}
	public void setYears(String value) {
		this.years = value;
	}
	
	public String getYears() {
		return this.years == null ? null : this.years.trim();
	}
	public void setSupplierCode(String value) {
		this.supplierCode = value;
	}
	
	public String getSupplierCode() {
		return this.supplierCode == null ? null : this.supplierCode.trim();
	}
	public void setPriceScpe(String value) {
		this.priceScpe = value;
	}
	
	public String getPriceScpe() {
		return this.priceScpe == null ? null : this.priceScpe.trim();
	}
	public void setSalePrice(Double value) {
		this.salePrice = value;
	}
	
	public Double getSalePrice() {
		return this.salePrice;
	}
	public void setCostPrice(Double value) {
		this.costPrice = value;
	}
	
	public Double getCostPrice() {
		return this.costPrice;
	}
	public void setStyleNo(String value) {
		this.styleNo = value;
	}
	
	public String getStyleNo() {
		return this.styleNo == null ? null : this.styleNo.trim();
	}
	public void setSpecNo(String value) {
		this.specNo = value;
	}
	
	public String getSpecNo() {
		return this.specNo == null ? null : this.specNo.trim();
	}
	public void setSpecName(String value) {
		this.specName = value;
	}
	
	public String getSpecName() {
		return this.specName == null ? null : this.specName.trim();
	}
	public void setHeelName(String value) {
		this.heelName = value;
	}
	
	public String getHeelName() {
		return this.heelName == null ? null : this.heelName.trim();
	}
	public void setHeelNo(String value) {
		this.heelNo = value;
	}
	
	public String getHeelNo() {
		return this.heelNo == null ? null : this.heelNo.trim();
	}
	public void setUpdatePerson(String value) {
		this.updatePerson = value;
	}
	
	public String getUpdatePerson() {
		return this.updatePerson == null ? null : this.updatePerson.trim();
	}
	public void setSeoKeyword(String value) {
		this.seoKeyword = value;
	}
	
	public String getSeoKeyword() {
		return this.seoKeyword == null ? null : this.seoKeyword.trim();
	}
	public void setSeoTitle(String value) {
		this.seoTitle = value;
	}
	
	public String getSeoTitle() {
		return this.seoTitle == null ? null : this.seoTitle.trim();
	}
	public void setSeoDescription(String value) {
		this.seoDescription = value;
	}
	
	public String getSeoDescription() {
		return this.seoDescription == null ? null : this.seoDescription.trim();
	}
	public void setColorSeriesNo(String value) {
		this.colorSeriesNo = value;
	}
	
	public String getColorSeriesNo() {
		return this.colorSeriesNo == null ? null : this.colorSeriesNo.trim();
	}
	public void setSortNo(Integer value) {
		value = value == null ? 0 : value;
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo == null ? 0 : this.sortNo;
	}
	public void setMarkid(String value) {
		this.markid = value;
	}
	
	public String getMarkid() {
		return this.markid == null ? null : this.markid.trim();
	}
	public void setColorDesc(String value) {
		this.colorDesc = value;
	}
	
	public String getColorDesc() {
		return this.colorDesc == null ? null : this.colorDesc.trim();
	}
	public void setCostPrice2(Double value) {
		this.costPrice2 = value;
	}
	
	public Double getCostPrice2() {
		return this.costPrice2;
	}
	public void setCodeNo(String value) {
		this.codeNo = value;
	}
	
	public String getCodeNo() {
		return this.codeNo == null ? null : this.codeNo.trim();
	}
	public void setStaticTimestamp(String value) {
		this.staticTimestamp = value;
	}
	
	public String getStaticTimestamp() {
		return this.staticTimestamp == null ? null : this.staticTimestamp.trim();
	}
	public void setPriceFlag(Integer value) {
		value = value == null ? 0 : value;
		this.priceFlag = value;
	}
	
	public Integer getPriceFlag() {
		return this.priceFlag == null ? 0 : this.priceFlag;
	}
	public void setPicFlag(Integer value) {
		value = value == null ? 0 : value;
		this.picFlag = value;
	}
	
	public Integer getPicFlag() {
		return this.picFlag == null ? 0 : this.picFlag;
	}
	public void setSaleFlag(Integer value) {
		value = value == null ? 0 : value;
		this.saleFlag = value;
	}
	
	public Integer getSaleFlag() {
		return this.saleFlag == null ? 0 : this.saleFlag;
	}
	public void setBaseFlag(Integer value) {
		value = value == null ? 0 : value;
		this.baseFlag = value;
	}
	
	public Integer getBaseFlag() {
		return this.baseFlag == null ? 0 : this.baseFlag;
	}
	public void setFirstSellDate(Date value) {
		this.firstSellDate = value;
	}
	
	public Date getFirstSellDate() {
		return this.firstSellDate;
	}
	
	public String getStringFirstSellDate() {
		if(this.firstSellDate == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.firstSellDate, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setOrderDistributionSide(Integer value) {
		value = value == null ? 0 : value;
		this.orderDistributionSide = value;
	}
	
	public Integer getOrderDistributionSide() {
		return this.orderDistributionSide == null ? 0 : this.orderDistributionSide;
	}
	public void setMerchantCode(String value) {
		this.merchantCode = value;
	}
	
	public String getMerchantCode() {
		return this.merchantCode == null ? null : this.merchantCode.trim();
	}
	public void setSizeChartId(Integer value) {
		value = value == null ? 0 : value;
		this.sizeChartId = value;
	}
	
	public Integer getSizeChartId() {
		return this.sizeChartId == null ? 0 : this.sizeChartId;
	}
	public void setSampleFlag(Integer value) {
		value = value == null ? 0 : value;
		this.sampleFlag = value;
	}
	
	public Integer getSampleFlag() {
		return this.sampleFlag == null ? 0 : this.sampleFlag;
	}
	public void setSuppliersPicFlag(Integer value) {
		value = value == null ? 0 : value;
		this.suppliersPicFlag = value;
	}
	
	public Integer getSuppliersPicFlag() {
		return this.suppliersPicFlag == null ? 0 : this.suppliersPicFlag;
	}
	public void setSourceFlag(Integer value) {
		value = value == null ? 0 : value;
		this.sourceFlag = value;
	}
	
	public Integer getSourceFlag() {
		return this.sourceFlag == null ? 0 : this.sourceFlag;
	}
	public void setSiteNo(String value) {
		this.siteNo = value;
	}
	
	public String getSiteNo() {
		return this.siteNo == null ? null : this.siteNo.trim();
	}
	public void setExistsPurchase(Integer value) {
		value = value == null ? 0 : value;
		this.existsPurchase = value;
	}
	
	public Integer getExistsPurchase() {
		return this.existsPurchase == null ? 0 : this.existsPurchase;
	}
	public void setIsSiteNoManual(Integer value) {
		value = value == null ? 0 : value;
		this.isSiteNoManual = value;
	}
	
	public Integer getIsSiteNoManual() {
		return this.isSiteNoManual == null ? 0 : this.isSiteNoManual;
	}
	public void setExistsInventory(Integer value) {
		value = value == null ? 0 : value;
		this.existsInventory = value;
	}
	
	public Integer getExistsInventory() {
		return this.existsInventory == null ? 0 : this.existsInventory;
	}
	public void setDownReason(Integer value) {
		value = value == null ? 0 : value;
		this.downReason = value;
	}
	
	public Integer getDownReason() {
		return this.downReason == null ? 0 : this.downReason;
	}
	public void setSellingPoint(String value) {
		this.sellingPoint = value;
	}
	
	public String getSellingPoint() {
		return this.sellingPoint == null ? null : this.sellingPoint.trim();
	}
	public void setIsDirectMarketing(Integer value) {
		value = value == null ? 0 : value;
		this.isDirectMarketing = value;
	}
	
	public Integer getIsDirectMarketing() {
		return this.isDirectMarketing == null ? 0 : this.isDirectMarketing;
	}
	public void setTaxNo(String value) {
		this.taxNo = value;
	}
	
	public String getTaxNo() {
		return this.taxNo == null ? null : this.taxNo.trim();
	}
	public void setRecyclebinFlag(Integer value) {
		value = value == null ? 0 : value;
		this.recyclebinFlag = value;
	}
	
	public Integer getRecyclebinFlag() {
		return this.recyclebinFlag == null ? 0 : this.recyclebinFlag;
	}
	public void setOuterMarkPrice(Double value) {
		this.outerMarkPrice = value;
	}
	
	public Double getOuterMarkPrice() {
		return this.outerMarkPrice;
	}
	public void setOuterSellPrice(Double value) {
		this.outerSellPrice = value;
	}
	
	public Double getOuterSellPrice() {
		return this.outerSellPrice;
	}
	public void setIsWfxCommodity(Integer value) {
		value = value == null ? 0 : value;
		this.isWfxCommodity = value;
	}
	
	public Integer getIsWfxCommodity() {
		return this.isWfxCommodity == null ? 0 : this.isWfxCommodity;
	}
	public void setWfxCommodityNo(String value) {
		this.wfxCommodityNo = value;
	}
	
	public String getWfxCommodityNo() {
		return this.wfxCommodityNo == null ? null : this.wfxCommodityNo.trim();
	}
	public void setIsOnsale(Integer value) {
		value = value == null ? 0 : value;
		this.isOnsale = value;
	}
	
	public Integer getIsOnsale() {
		return this.isOnsale == null ? 0 : this.isOnsale;
	}
	public void setWfxPrice(Double value) {
		this.wfxPrice = value;
	}
	
	public Double getWfxPrice() {
		return this.wfxPrice;
	}
	public void setAddWfxTime(Date value) {
		this.addWfxTime = value;
	}
	
	public Date getAddWfxTime() {
		return this.addWfxTime;
	}
	
	public String getStringAddWfxTime() {
		if(this.addWfxTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.addWfxTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setAddWfxUser(String value) {
		this.addWfxUser = value;
	}
	
	public String getAddWfxUser() {
		return this.addWfxUser == null ? null : this.addWfxUser.trim();
	}
	public void setSaleQuantity(Integer value) {
		value = value == null ? 0 : value;
		this.saleQuantity = value;
	}
	
	public Integer getSaleQuantity() {
		return this.saleQuantity == null ? 0 : this.saleQuantity;
	}
	public void setPopularity(Integer value) {
		value = value == null ? 0 : value;
		this.popularity = value;
	}
	
	public Integer getPopularity() {
		return this.popularity == null ? 0 : this.popularity;
	}
	public void setProxyQuantity(Integer value) {
		value = value == null ? 0 : value;
		this.proxyQuantity = value;
	}
	
	public Integer getProxyQuantity() {
		return this.proxyQuantity == null ? 0 : this.proxyQuantity;
	}
	public void setSupplierId(String value) {
		this.supplierId = value;
	}
	
	public String getSupplierId() {
		return this.supplierId == null ? null : this.supplierId.trim();
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
