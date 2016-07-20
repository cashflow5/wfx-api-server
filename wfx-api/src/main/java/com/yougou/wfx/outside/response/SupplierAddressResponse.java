/*
 * 版本信息

 * 日期 2016-04-26 09:34:42

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.outside.response;

import java.util.ArrayList;
import java.util.List;

import com.yougou.wfx.outside.domain.SupplierAddress;

/**
 * SupplierAddressEntity
 * 
 * @author wfx
 * @Date 创建时间：2016-04-26 09:34:43
 */
public class SupplierAddressResponse extends BaseResponse {
	private static final long serialVersionUID = 1L;
	private List<SupplierAddress> supplierAddressList = new ArrayList<SupplierAddress>();

	public List<SupplierAddress> getSupplierAddressList() {
		return supplierAddressList;
	}

	public void setSupplierAddressList(List<SupplierAddress> supplierAddressList) {
		this.supplierAddressList = supplierAddressList;
	}
}
