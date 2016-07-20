 /*
 * 版本信息
 
 * 日期 2016-04-01 18:28:14
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.cms.dao.CommoditySaleCatRelaMapper;
import com.yougou.wfx.cms.model.CommoditySaleCatRelaEntity;
import com.yougou.wfx.cms.service.ICommoditySaleCatRelaService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommoditySaleCatRelaService实现
 * @author wfx
 * @Date 创建时间：2016-04-01 18:28:15
 */
@Service
public class CommoditySaleCatRelaServiceImpl extends BaseService implements ICommoditySaleCatRelaService {
	
	@Resource
	private CommoditySaleCatRelaMapper commoditySaleCatRelaMapper;

	@Override
	public int findPageCount(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity){
		return commoditySaleCatRelaMapper.findPageCount(commoditySaleCatRelaEntity);
	}

	@Override
	public List<CommoditySaleCatRelaEntity> findPage(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity,RowBounds rowBounds){
		return commoditySaleCatRelaMapper.findPage(commoditySaleCatRelaEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		commoditySaleCatRelaEntity.setId(strId);
		commoditySaleCatRelaMapper.insert(commoditySaleCatRelaEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity){
		return  commoditySaleCatRelaMapper.update(commoditySaleCatRelaEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return commoditySaleCatRelaMapper.remove(id);
	}
	
	@Override
	public CommoditySaleCatRelaEntity getById(String id){
		return commoditySaleCatRelaMapper.getById(id);
	}

	@Override
	public List<CommoditySaleCatRelaEntity> queryList(
			CommoditySaleCatRelaEntity commoditySaleCatRelaEntity) {
		return commoditySaleCatRelaMapper.queryList(commoditySaleCatRelaEntity);
	}

	@Override
	public int multiRemove(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity) {
		return commoditySaleCatRelaMapper.multiRemove(commoditySaleCatRelaEntity);
	} 
}