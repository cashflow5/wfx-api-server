 /*
 * 版本信息
 
 * 日期 2016-04-14 13:34:05
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.CommodityProductMapper;
import com.yougou.wfx.commodity.model.CommodityProductEntity;
import com.yougou.wfx.commodity.service.ICommodityProductService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommodityProductService实现
 * @author wfx
 * @Date 创建时间：2016-04-14 13:34:06
 */
@Service
public class CommodityProductServiceImpl extends BaseService implements ICommodityProductService {
	
	@Resource
	private CommodityProductMapper commodityProductMapper;

	@Override
	public int findPageCount(CommodityProductEntity commodityProductEntity){
		return commodityProductMapper.findPageCount(commodityProductEntity);
	}

	@Override
	public List<CommodityProductEntity> findPage(CommodityProductEntity commodityProductEntity,RowBounds rowBounds){
		return commodityProductMapper.findPage(commodityProductEntity,rowBounds);
	}

	@Override
	public List<CommodityProductEntity> queryList(CommodityProductEntity commodityProductEntity){
		return commodityProductMapper.queryList(commodityProductEntity);
	}
	
	@Override
	@Transactional
	public String insert(CommodityProductEntity commodityProductEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityProductEntity.setId(strId);
		commodityProductMapper.insert(commodityProductEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(CommodityProductEntity commodityProductEntity){
		return  commodityProductMapper.update(commodityProductEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return commodityProductMapper.remove(id);
	}
	
	@Override
	public CommodityProductEntity getById(String id){
		return commodityProductMapper.getById(id);
	}

	@Override
	public boolean preInventory(String commodityProductId,Integer num) {
		boolean result = true;
		CommodityProductEntity product = commodityProductMapper.getById(commodityProductId);
		if(null == product){
			result = false;
			return result;
		}
		//可售库存
		Integer inventoryNum = product.getInventoryNum();
		//预占库存数
		Integer preInventtoryNum = product.getPrestoreInventoryNum();
		if(null == inventoryNum || inventoryNum < 0){
			result = false;
			return result;
		}
		if(null == preInventtoryNum){
			preInventtoryNum = 0;
		}
		Integer remain = inventoryNum - preInventtoryNum;
		if(num > remain){
			result = false;
			return result;
		}
		CommodityProductEntity entity = new CommodityProductEntity();
		entity.setId(product.getId());
		entity.setPrestoreInventoryNum(preInventtoryNum + num);
		entity.setUpdateDate(new Date());
		commodityProductMapper.update(entity);
		return result;
	}

	@Override
	public boolean freePreInventory(String commodityProductId, Integer num) {
		CommodityProductEntity product = commodityProductMapper.getById(commodityProductId);
		if(null == product){
			return false;
		}
		//预占库存数
		Integer preInventtoryNum = product.getPrestoreInventoryNum();
		if(null == preInventtoryNum){
			preInventtoryNum = 0;
		}
		preInventtoryNum = preInventtoryNum - num;
		if(preInventtoryNum < 0){
			preInventtoryNum = 0;
		}
		CommodityProductEntity entity = new CommodityProductEntity();
		entity.setId(product.getId());
		entity.setPrestoreInventoryNum(preInventtoryNum);
		entity.setUpdateDate(new Date());
		commodityProductMapper.update(entity);
		return true;
	}

	@Override
	public List<String> getCatId(String id) {
		return commodityProductMapper.getCatId(id);
	}

	@Override
	public List<CommodityProductEntity> getProductByProductNos(
			@NotNull List<String> productNos) {
		return commodityProductMapper.getProductByProductNos(productNos);
	}

	@Override
	public int clearNotSPYG() {
		return commodityProductMapper.clearNotSPYG();
	}

	@Override
	public List<CommodityProductEntity> getProductByCommodityId(
			Map<String,String> map) {
		
		return commodityProductMapper.getProductByCommodityId(map);
	}
}