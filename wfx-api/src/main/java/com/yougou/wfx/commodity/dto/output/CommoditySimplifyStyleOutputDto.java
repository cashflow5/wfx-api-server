 /*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dto.output;


import com.yougou.wfx.dto.base.OutputDto;

/**
 * CommoditySimplifyStyleOutputDto
 * @author zhang.wj1
 * @Date 创建时间：2016-03-24 16:58:55
 */
public class CommoditySimplifyStyleOutputDto extends OutputDto {
	private static final long serialVersionUID = 1L;
	/**
	 * 下架/上架  状态
	 */
	private boolean  shelvesStatus;
	/**
	 * 是否还有库存,1有库存  2，没有库存
	 */
	private int inventoryStatus;
	
	
	public boolean isShelvesStatus() {
		return shelvesStatus;
	}
	
	public void setShelvesStatus(boolean shelvesStatus) {
		this.shelvesStatus = shelvesStatus;
	}
	
	public int getInventoryStatus() {
		return inventoryStatus;
	}
	
	public void setInventoryStatus(int inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}
	

}

