/*
 * 版本信息

 * 日期 2016-03-20 13:16:54

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.BagMapper;
import com.yougou.wfx.commodity.model.BagEntity;
import com.yougou.wfx.commodity.model.CommodityBagRelaEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.IBagService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * 分销包实现
 * @author wuyang
 * @Date 创建时间：2016-03-20 13:16:57
 */
@Service
public class BagServiceImpl extends BaseService implements IBagService {

	@Autowired
	private BagMapper bagMapper;

	@Override
	public int findPageCount(BagEntity bagEntity) {
		return bagMapper.findPageCount(bagEntity);
	}

	@Override
	public List<BagEntity> findPage(BagEntity bagEntity, RowBounds rowBounds) {
		return bagMapper.findPage(bagEntity, rowBounds);
	}

	@Override
	@Transactional
	public String insert(BagEntity bagEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		bagEntity.setId(strId);
		bagMapper.insert(bagEntity);
		return strId;
	}

	@Override
	@Transactional
	public int update(BagEntity bagEntity) {
		return bagMapper.update(bagEntity);
	}

	@Override
	@Transactional
	public int remove(String id) {
		return bagMapper.remove(id);
	}

	@Override
	public BagEntity getById(String id) {
		return bagMapper.getById(id);
	}

	@Override
	public List<CommodityStyleEntity> findRelaCommodityList(String id) {
		List<CommodityStyleEntity> list = bagMapper.findRelaCommodityList(id);
		return list;
	}

	@Override
	@Transactional
	public String insertCommodityBagRela(CommodityBagRelaEntity commodityBagRelaEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityBagRelaEntity.setId(strId);
		bagMapper.insertCommodityBagRela(commodityBagRelaEntity);
		return strId;
	}

	@Override
	@Transactional
	public int removeCommodityBagRela(
			CommodityBagRelaEntity commodityBagRelaEntity) {
		// TODO Auto-generated method stub
		return bagMapper.removeCommodityBagRela(commodityBagRelaEntity);
	}

	@Override
	public List<CommodityStyleEntity> findBagCommodityList(String bagId,
			RowBounds rowBounds) {
		return bagMapper.findBagCommodityList(bagId, rowBounds);
	}

	@Override
	public int findBagCommodityCount(String bagId) {
		return bagMapper.findBagCommodityCount(bagId);
	}
}