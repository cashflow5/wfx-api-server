 /*
 * 版本信息
 
 * 日期 2016-03-31 19:13:58
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.CommodityBrandCatb2cMapper;
import com.yougou.wfx.commodity.model.CommodityBrandCatb2cEntity;
import com.yougou.wfx.commodity.service.ICommodityBrandCatb2cService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommodityBrandCatb2cService实现
 * @author wfx
 * @Date 创建时间：2016-03-31 19:13:58
 */
@Service
public class CommodityBrandCatb2cServiceImpl extends BaseService implements ICommodityBrandCatb2cService {
	
	@Resource
	private CommodityBrandCatb2cMapper commodityBrandCatb2cMapper;

	@Override
	public int findPageCount(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity){
		return commodityBrandCatb2cMapper.findPageCount(commodityBrandCatb2cEntity);
	}

	@Override
	public List<CommodityBrandCatb2cEntity> findPage(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity,RowBounds rowBounds){
		return commodityBrandCatb2cMapper.findPage(commodityBrandCatb2cEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityBrandCatb2cEntity.setId(strId);
		commodityBrandCatb2cMapper.insert(commodityBrandCatb2cEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public void batchInsert(List<CommodityBrandCatb2cEntity> commodityBrandCatb2cEntityList){
		commodityBrandCatb2cMapper.batchInsert(commodityBrandCatb2cEntityList);
	}
	
	@Override
	@Transactional
	public int update(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity){
		return  commodityBrandCatb2cMapper.update(commodityBrandCatb2cEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return commodityBrandCatb2cMapper.remove(id);
	}
	
	@Override
	@Transactional
	public int deleteByBrandId(String brandId){
		return commodityBrandCatb2cMapper.deleteByBrandId(brandId);
	}
	
	@Override
	public CommodityBrandCatb2cEntity getById(String id){
		return commodityBrandCatb2cMapper.getById(id);
	} 
}