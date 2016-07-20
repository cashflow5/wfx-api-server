 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dto.input;

import java.util.Date;

import com.yougou.wfx.util.DatetimeUtil;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.wfx.dto.base.InputDto;

/**
 * CommissionPercentPageInputDto
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
public class CommissionPercentPageInputDto extends InputDto{

	private static final long serialVersionUID = 1L;

	/**
	 * 主键id
	 */
	private String id;
	/**
	 * tbl_commodity_catb2c.id 商品基础分类ID,默认比例设置此字段为空
	 */
	private String baseCatId;
	/**
	 * 品牌编码
	 */
	private String brandNo;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后更新时间
	 */
	private Date updateTime;
	/**
	 * 最后更新用户
	 */
	private String updateUser;
	/**
	 * 一级佣金比例
	 */
	private Double commissionLevel1Percent=0d;
	/**
	 * 二级佣金比例
	 */
	private Double commissionLevel2Percent=0d;
	
	/**
     * 三级佣金比例
     */
    private Double commissionLevel3Percent=0d;
	/**
	 * 1: 默认比例设置 2：品牌分类比例设置，3单品佣金比例
	 */
	private Integer type;
	/**
     * 商品分类名称
     */
    private String catName;
    
    /**
     * 品牌名称
     */
    private String brandName;
    
    /**
     * 商品id
     */
    private String commodityId;
    
    /**
     * 商品名称
     */
    private String commodityName;
    
    /**
     * 款色编码
     */
    private String supplierCode;
	
	
	public String getBrandNo() {
        return brandNo;
    }

    public void setBrandNo(String brandNo) {
        this.brandNo = brandNo;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

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

    public String getSupplierCode() {
        return supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    public CommissionPercentPageInputDto(){
	}

	public CommissionPercentPageInputDto(
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
	public void setBaseCatId(String value) {
		this.baseCatId = value;
	}
	
	public String getBaseCatId() {
		return this.baseCatId == null ? null : this.baseCatId.trim();
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public String getStringCreateTime() {
		if(this.createTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.createTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	
	public Date getUpdateTime() {
		return this.updateTime;
	}
	
	public String getStringUpdateTime() {
		if(this.updateTime == null){
		return null;
		}
		return DatetimeUtil.DateToString(this.updateTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	public String getUpdateUser() {
		return this.updateUser == null ? null : this.updateUser.trim();
	}
	public void setCommissionLevel1Percent(Double value) {
		this.commissionLevel1Percent = value;
	}
	
	public Double getCommissionLevel1Percent() {
		return this.commissionLevel1Percent;
	}
	public void setCommissionLevel2Percent(Double value) {
		this.commissionLevel2Percent = value;
	}
	
	public Double getCommissionLevel2Percent() {
		return this.commissionLevel2Percent;
	}
	
	public Double getCommissionLevel3Percent() {
        return commissionLevel3Percent;
    }

    public void setCommissionLevel3Percent(Double commissionLevel3Percent) {
        this.commissionLevel3Percent = commissionLevel3Percent;
    }

    public void setType(Integer value) {
		value = value == null ? 0 : value;
		this.type = value;
	}
	
	public Integer getType() {
		return this.type == null ? 0 : this.type;
	}
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
	
