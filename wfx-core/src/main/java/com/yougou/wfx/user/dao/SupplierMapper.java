 /*
 * 版本信息
 
 * 日期 2016-04-08 13:32:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.user.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.user.model.SupplierEntity;

/**
 * SupplierMapper
 * @author wfx
 * @Date 创建时间：2016-04-08 13:32:03
 */
public interface SupplierMapper{
	
	int findPageCount(SupplierEntity supplierEntity);

	List<SupplierEntity> findPage(SupplierEntity supplierEntity,RowBounds rowBounds);
	
	int insert(SupplierEntity supplierEntity);
	
	int update(SupplierEntity supplierEntity);
	
	int remove(String id);
	
	SupplierEntity getById(String id);
}
