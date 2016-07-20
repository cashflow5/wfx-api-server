 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.CommodityBrandMapper;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.service.ICommodityBrandService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommodityBrandService实现
 * @author wuyang
 * @Date 创建时间：2016-03-23 18:39:34
 */
@Service
public class CommodityBrandServiceImpl extends BaseService implements ICommodityBrandService {
	
	@Resource
	private CommodityBrandMapper commodityBrandMapper;

	@Override
	public int findPageCount(CommodityBrandEntity commodityBrandEntity){
		return commodityBrandMapper.findPageCount(commodityBrandEntity);
	}

	@Override
	public List<CommodityBrandEntity> findPage(CommodityBrandEntity commodityBrandEntity,RowBounds rowBounds){
		return commodityBrandMapper.findPage(commodityBrandEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(CommodityBrandEntity commodityBrandEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityBrandEntity.setId(strId);
		commodityBrandMapper.insert(commodityBrandEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(CommodityBrandEntity commodityBrandEntity){
		return  commodityBrandMapper.update(commodityBrandEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return commodityBrandMapper.remove(id);
	}
	
	@Override
	public CommodityBrandEntity getById(String id){
		return commodityBrandMapper.getById(id);
	} 
	
	@Override
	public boolean batchUpdateHotBrand(String[] brandIds,String userName, int flag) {
		CommodityBrandEntity entity = null;
		int maxHotSn = 0;
		int count = 0;
		if(1 == flag){
			maxHotSn = commodityBrandMapper.getBrandMaxHotSn();
		}
		for(String brandId : brandIds){
			entity = new CommodityBrandEntity();
			entity.setId(brandId);
			entity.setHotFlag(flag);
			entity.setStatus(1);
			entity.setUpdatePerson(userName);
			entity.setUpdateTime(new Date(System.currentTimeMillis()));
			if(1 == flag){
				entity.setHotSn(++maxHotSn);
			}else{
				entity.setHotSn(1);
			}
			count += commodityBrandMapper.update(entity);
		}
		return count > 0;
	}
	
	@Override
	public List<CommodityBrandEntity> queryCommodityBrandByCatId(String catId){
		return commodityBrandMapper.queryCommodityBrandByCatId(catId);
	}
	
	@Override
	public void batchUpdateBrandUseFlag(){
		commodityBrandMapper.initUpdateBrandUseFlag();
		commodityBrandMapper.batchUpdateBrandUseFlag();
	}
}