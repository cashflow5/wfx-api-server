 /*
 * 版本信息
 
 * 日期 2016-03-28 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.dto.input;

import com.yougou.wfx.dto.base.InputDto;

/**
 * SellerInfoPageInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-03-28 16:58:55
 */
public class CheckProductNumberInputDto extends InputDto {
	private static final long serialVersionUID = 1L;
	//货品id
	private String productId;
	//订单数量
	private String  orderNum;
	
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
}
