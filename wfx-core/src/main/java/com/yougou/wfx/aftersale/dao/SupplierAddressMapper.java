 /*
 * 版本信息
 
 * 日期 2016-04-26 09:34:42
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.aftersale.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.aftersale.model.SupplierAddressEntity;

/**
 * SupplierAddressMapper
 * @author wfx
 * @Date 创建时间：2016-04-26 09:34:43
 */
public interface SupplierAddressMapper{
	
	int findPageCount(SupplierAddressEntity supplierAddressEntity);

	List<SupplierAddressEntity> findPage(SupplierAddressEntity supplierAddressEntity,RowBounds rowBounds);
	
	int insert(SupplierAddressEntity supplierAddressEntity);
	
	int update(SupplierAddressEntity supplierAddressEntity);
	
	int remove(String id);
	
	SupplierAddressEntity getById(String id);

	SupplierAddressEntity getByOutSideNo(String outSideNo);

	SupplierAddressEntity queryByOrderDetailId(String orderDetailId);

	SupplierAddressEntity queryBySupplierCode(String supplierCode);
}
