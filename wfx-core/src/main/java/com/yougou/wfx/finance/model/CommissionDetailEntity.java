 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.model;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.yougou.tools.common.utils.DatetimeUtil;

/**
 * CommissionDetailEntity
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
public class CommissionDetailEntity implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
//columns START
	/**
	 * 主键id
	 */
	private String id;
	/**
	 * tbl_wfx_order_detail.wfx_order_detail_no,子订单号
	 */
	private String wfxOrderDetailNo;
	/**
	 * 下单时间
	 */
	private Date orderTime;
	/**
	 * 店铺名称:tbl_wfx_shop.name(显示佣金拥有者)
	 */
	private String wfxShopName;
	/**
	 * 分销商账号:tbl_wfx_member_account.login_name(显示佣金拥有者)
	 */
	private String loginName;
	/**
	 * 商品编码
	 */
	private String commodityNo;
	/**
	 * 商家编码:对应优购“款色编码”tbl_commodity_style.supplier_code
	 */
	private String supplierCode;
	/**
	 * 品牌编码
	 */
	private String brandNo;
	/**
	 * 一级分类:tbl_commodity_catb2c.id基础分类ID
	 */
	private String baseCatId1;
	/**
	 * 二级分类:tbl_commodity_catb2c.id基础分类ID
	 */
	private String baseCatId2;
	/**
	 * 确认收货/退货时间
	 */
	private Date confirmTime;
	/**
	 * 结算类型:0妥投结算,1退货结算
	 */
	private String settlementType;
	/**
	 * 商品数量
	 */
	private Integer commodityQuantity;
	/**
	 * 商品单价
	 */
	private Double goodsPrice;
	/**
	 * 商品金额=单价*数量
	 */
	private Double goodsAmount;
	/**
	 * 运费
	 */
	private Double freightCharges;
	/**
	 * 扣佣比例%
	 */
	private Double deductRatio;
	/**
	 * 佣金收益=商品金额*扣拥比例
	 */
	private Double commissionAmount;
	/**
	 * 0:未结算,1:已结算,2:异常挂起,3:已关闭
	 */
	private String status;
	/**
	 * 结算处理时间
	 */
	private Date updateTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * tbl_wfx_seller_info.id,分销商ID(显示佣金拥有者)
	 */
	private String sellerId;
	/**
	 * 处理方式:0结算中,1重新结算,2异常关闭
	 */
	private String dealType;
	/**
	 * 备注
	 */
	private String remark;
	
	/**
     * 账户余额
     */
    private Double accountBalance;
    
    /**
     * 确认收货/退货时间-起始时间
     */
    private String confirmStartTime;
    /**
     * 确认收货/退货时间-结束时间
     */
    private String confirmEndTime;
    /**
     * 结算时间-起始时间
     */
    private String settleStartTime;
    /**
     * 结算时间-结束时间
     */
    private String settleEndTime;
    
    /**
     * 佣金级别：1一级，2二级，3三级
     */
    private String commissionLevel;
    
    /**
     * 微分销主订单号
     */
    private String wfxOrderNo;
    
    /**
     * 下级分销商ID(也即下单分销商id)
     */
    private String nextSellerId;
    
    
    public String getNextSellerId() {
        return nextSellerId;
    }

    public void setNextSellerId(String nextSellerId) {
        this.nextSellerId = nextSellerId;
    }

    public String getWfxOrderNo() {
        return wfxOrderNo;
    }

    public void setWfxOrderNo(String wfxOrderNo) {
        this.wfxOrderNo = wfxOrderNo;
    }

    public String getCommissionLevel() {
        return commissionLevel;
    }

    public void setCommissionLevel(String commissionLevel) {
        this.commissionLevel = commissionLevel;
    }

    public String getConfirmStartTime() {
        return confirmStartTime;
    }

    public void setConfirmStartTime(String confirmStartTime) {
        this.confirmStartTime = confirmStartTime;
    }

    public String getConfirmEndTime() {
        return confirmEndTime;
    }

    public void setConfirmEndTime(String confirmEndTime) {
        this.confirmEndTime = confirmEndTime;
    }

    public String getSettleStartTime() {
        return settleStartTime;
    }

    public void setSettleStartTime(String settleStartTime) {
        this.settleStartTime = settleStartTime;
    }

    public String getSettleEndTime() {
        return settleEndTime;
    }

    public void setSettleEndTime(String settleEndTime) {
        this.settleEndTime = settleEndTime;
    }

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
    }

	public CommissionDetailEntity(){
	}

	public CommissionDetailEntity(
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
	public void setWfxOrderDetailNo(String value) {
		this.wfxOrderDetailNo = value;
	}
	
	public String getWfxOrderDetailNo() {
		return this.wfxOrderDetailNo == null ? null : this.wfxOrderDetailNo.trim();
	}
	public void setOrderTime(Date value) {
		this.orderTime = value;
	}
	
	public Date getOrderTime() {
		return this.orderTime;
	}
	
	public String getStringOrderTime() {
		if(this.orderTime == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.orderTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setWfxShopName(String value) {
		this.wfxShopName = value;
	}
	
	public String getWfxShopName() {
		return this.wfxShopName == null ? null : this.wfxShopName.trim();
	}
	public void setLoginName(String value) {
		this.loginName = value;
	}
	
	public String getLoginName() {
		return this.loginName == null ? null : this.loginName.trim();
	}
	public void setCommodityNo(String value) {
		this.commodityNo = value;
	}
	
	public String getCommodityNo() {
		return this.commodityNo == null ? null : this.commodityNo.trim();
	}
	public void setSupplierCode(String value) {
		this.supplierCode = value;
	}
	
	public String getSupplierCode() {
		return this.supplierCode == null ? null : this.supplierCode.trim();
	}
	public void setBrandNo(String value) {
		this.brandNo = value;
	}
	
	public String getBrandNo() {
		return this.brandNo == null ? null : this.brandNo.trim();
	}
	public void setBaseCatId1(String value) {
		this.baseCatId1 = value;
	}
	
	public String getBaseCatId1() {
		return this.baseCatId1 == null ? null : this.baseCatId1.trim();
	}
	public void setBaseCatId2(String value) {
		this.baseCatId2 = value;
	}
	
	public String getBaseCatId2() {
		return this.baseCatId2 == null ? null : this.baseCatId2.trim();
	}
	public void setConfirmTime(Date value) {
		this.confirmTime = value;
	}
	
	public Date getConfirmTime() {
		return this.confirmTime;
	}
	
	public String getStringConfirmTime() {
		if(this.confirmTime == null){
			return null;
		}
		return DatetimeUtil.DateToString(this.confirmTime, DatetimeUtil.LONG_DATE_TIME_PATTERN);
	}
	public void setSettlementType(String value) {
		this.settlementType = value;
	}
	
	public String getSettlementType() {
		return this.settlementType == null ? null : this.settlementType.trim();
	}
	public void setCommodityQuantity(Integer value) {
		value = value == null ? 0 : value;
		this.commodityQuantity = value;
	}
	
	public Integer getCommodityQuantity() {
		return this.commodityQuantity == null ? 0 : this.commodityQuantity;
	}
	public void setGoodsPrice(Double value) {
		this.goodsPrice = value;
	}
	
	public Double getGoodsPrice() {
		return this.goodsPrice == null ? 0 : this.goodsPrice;
	}
	public void setGoodsAmount(Double value) {
		this.goodsAmount = value;
	}
	
	public Double getGoodsAmount() {
		return this.goodsAmount == null ? 0 : this.goodsAmount;
	}
	public void setFreightCharges(Double value) {
		this.freightCharges = value;
	}
	
	public Double getFreightCharges() {
	    return this.freightCharges == null ? 0 : this.freightCharges;
	}
	public void setDeductRatio(Double value) {
		this.deductRatio = value;
	}
	
	public Double getDeductRatio() {
		return this.deductRatio == null ? 0 : this.deductRatio;
	}
	public void setCommissionAmount(Double value) {
		this.commissionAmount = value;
	}
	
	public Double getCommissionAmount() {
		return this.commissionAmount == null ? 0 : this.commissionAmount;
	}
	public void setStatus(String value) {
		this.status = value;
	}
	
	public String getStatus() {
		return this.status == null ? null : this.status.trim();
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
	public void setSellerId(String value) {
		this.sellerId = value;
	}
	
	public String getSellerId() {
		return this.sellerId == null ? null : this.sellerId.trim();
	}
	public void setDealType(String value) {
		this.dealType = value;
	}
	
	public String getDealType() {
		return this.dealType == null ? null : this.dealType.trim();
	}
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark == null ? null : this.remark.trim();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

