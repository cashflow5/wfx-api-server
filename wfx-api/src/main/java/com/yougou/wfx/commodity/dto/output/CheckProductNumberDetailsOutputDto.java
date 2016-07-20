 /*
 * 版本信息
 
 * 日期 2016-03-28 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * SellerInfoPageInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-03-28 16:58:55
 */
public class CheckProductNumberDetailsOutputDto extends OutputDto{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//货品id
	private String productId;
	
	//可售库存数量  ，为了显示库存不足用的
	private String  inventoryNum;
	
	//可售库存数量状态，true有库存、false 无库存
	private boolean  inventoryStatus;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getInventoryNum() {
		return inventoryNum;
	}
	public void setInventoryNum(String inventoryNum) {
		this.inventoryNum = inventoryNum;
	}
	public boolean isInventoryStatus() {
		return inventoryStatus;
	}
	public void setInventoryStatus(boolean inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	
	
	
	
}
