 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.finance.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
public class CommissionCollectOutputDto extends OutputDto {
    private static final long serialVersionUID = 7035946105247059930L;
    
    /**
     * tbl_wfx_seller_info.id,分销商ID
     */
    private String sellerId;
    
    /**
     * 未结算金额
     */
    private double unSettlementAmount=0;
    
    /**
     * 已结算金额
     */
    private double settlementAmount=0;

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }

    public double getUnSettlementAmount() {
        return unSettlementAmount;
    }

    public void setUnSettlementAmount(double unSettlementAmount) {
        this.unSettlementAmount = unSettlementAmount;
    }

    public double getSettlementAmount() {
        return settlementAmount;
    }

    public void setSettlementAmount(double settlementAmount) {
        this.settlementAmount = settlementAmount;
    }
    
    
    
}

