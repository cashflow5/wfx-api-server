 /*
 * 版本信息
 
 * 日期 2016-04-08 13:32:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.user.dao.SupplierMapper;
import com.yougou.wfx.user.model.SupplierEntity;
import com.yougou.wfx.user.service.ISupplierService;

/**
 * ISupplierService实现
 * @author wfx
 * @Date 创建时间：2016-04-08 13:32:03
 */
@Service
public class SupplierServiceImpl extends BaseService implements ISupplierService {
	
	@Resource
	private SupplierMapper supplierMapper;

	@Override
	public int findPageCount(SupplierEntity supplierEntity){
		return supplierMapper.findPageCount(supplierEntity);
	}

	@Override
	public List<SupplierEntity> findPage(SupplierEntity supplierEntity,RowBounds rowBounds){
		return supplierMapper.findPage(supplierEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SupplierEntity supplierEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		supplierEntity.setId(strId);
		supplierMapper.insert(supplierEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SupplierEntity supplierEntity){
		return  supplierMapper.update(supplierEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return supplierMapper.remove(id);
	}
	
	@Override
	public SupplierEntity getById(String id){
		return supplierMapper.getById(id);
	} 
}