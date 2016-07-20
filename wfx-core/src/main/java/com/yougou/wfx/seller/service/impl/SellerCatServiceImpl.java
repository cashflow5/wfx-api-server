 /*
 * 版本信息
 
 * 日期 2016-04-13 14:20:20
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.seller.dao.SellerCatMapper;
import com.yougou.wfx.seller.model.SellerCatEntity;
import com.yougou.wfx.seller.service.ISellerCatService;
import com.yougou.wfx.seller.service.ISellerCommodityCatRelaService;

/**
 * ISellerCatService实现
 * @author wfx
 * @Date 创建时间：2016-04-13 14:20:20
 */
@Service
public class SellerCatServiceImpl extends BaseService implements ISellerCatService {
	
	@Resource
	private SellerCatMapper sellerCatMapper;
	@Resource
	private ISellerCommodityService sellerCommodityService;
	@Resource
	ISellerCommodityCatRelaService sellerCommodityCatRelaService;

	@Override
	public int findPageCount(SellerCatEntity sellerCatEntity){
		return sellerCatMapper.findPageCount(sellerCatEntity);
	}

	@Override
	public List<SellerCatEntity> findPage(SellerCatEntity sellerCatEntity,RowBounds rowBounds){
		return sellerCatMapper.findPage(sellerCatEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SellerCatEntity sellerCatEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sellerCatEntity.setId(strId);
		sellerCatMapper.insert(sellerCatEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SellerCatEntity sellerCatEntity){
		return  sellerCatMapper.update(sellerCatEntity);
	}
	
	@Override
	@Transactional
	public void remove(String id){
		sellerCatMapper.remove(id);
		sellerCommodityCatRelaService.removeByCatId(id);
	}
	
	@Override
	public SellerCatEntity getById(String id){
		return sellerCatMapper.getById(id);
	}

	@Override
	public List<SellerCatEntity> queryList(SellerCatEntity sellerCatEntity) {
		return sellerCatMapper.queryList(sellerCatEntity);
	}

	@Override
	public List<SellerCatEntity> queryChildren(String parentId) {
		SellerCatEntity sellerCatEntity = new SellerCatEntity();
		sellerCatEntity.setParentId(parentId);
		return sellerCatMapper.queryList(sellerCatEntity);
	} 
}