 /*
 * 版本信息
 
 * 日期 2016-04-13 16:39:55
 
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

import com.yougou.wfx.commodity.dao.SellerCommodityMapper;
import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.seller.model.SellerInfoEntity;

/**
 * ISellerCommodityService实现
 * @author wfx
 * @Date 创建时间：2016-04-13 16:39:55
 */
@Service
public class SellerCommodityServiceImpl extends BaseService implements ISellerCommodityService {
	
	@Resource
	private SellerCommodityMapper sellerCommodityMapper;

	@Override
	public int findPageCount(SellerCommodityEntity sellerCommodityEntity){
		return sellerCommodityMapper.findPageCount(sellerCommodityEntity);
	}

	@Override
	public List<SellerCommodityEntity> findPage(SellerCommodityEntity sellerCommodityEntity,RowBounds rowBounds){
		return sellerCommodityMapper.findPage(sellerCommodityEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(SellerCommodityEntity sellerCommodityEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		sellerCommodityEntity.setId(strId);
		sellerCommodityMapper.insert(sellerCommodityEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(SellerCommodityEntity sellerCommodityEntity){
		return  sellerCommodityMapper.update(sellerCommodityEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return sellerCommodityMapper.remove(id);
	}
	
	@Override
	public SellerCommodityEntity getById(String id){
		return sellerCommodityMapper.getById(id);
	}

	@Override
	@Transactional
	public int batchInsertSellerCommodity(
			List<SellerCommodityEntity> lstSellerCommodity) {
		return sellerCommodityMapper.batchInsertSellerCommodity(lstSellerCommodity);
	}
	
	@Override
	@Transactional
	public int batchUpdateSellerCommodity(List<SellerCommodityEntity> lstSellerCommodity) {
		return sellerCommodityMapper.batchUpdate(lstSellerCommodity);
	}

	@Override
	public List<SellerCommodityEntity> getSellerCommodity(SellerCommodityEntity entity) {
		return sellerCommodityMapper.getSellerCommodity(entity);
	}

	@Override
	public void updateSellerShelvesStatus(List<SellerCommodityEntity> sellerCommodity) {
		if(sellerCommodity!=null && sellerCommodity.size()>0){
			for (int i = 0; i < sellerCommodity.size(); i++) {
				sellerCommodityMapper.updateSellerShelvesStatus(sellerCommodity.get(i));
			}
		}
	} 
	
	@Override
	public List<SellerCommodityEntity> pickUpShelvesCommodities(List<String> list) {
		return sellerCommodityMapper.pickUpShelvesCommodities(list);
	}

	@Override
	public String querySellerCommodity(
			SellerCommodityEntity sellerCommodityEntity) {
		
		return sellerCommodityMapper.querySellerCommodity(sellerCommodityEntity);
	}

	@Override
	public int delete(String sellerId) {
		return sellerCommodityMapper.delete(sellerId);
	} 
	
//	@Override
//	public int proxyAll(SellerInfoEntity sellerInfo, String originalSellerId) {
////		SellerCommodityEntity sellerCommodity = new SellerCommodityEntity();
////		sellerCommodity.setSellerId( sellerInfo.getId() );
////		RowBounds rowBounds = new RowBounds();
//		int infectRows = delete(sellerInfo.getId());
//		return sellerCommodityMapper.proxyAll( sellerInfo,  originalSellerId,null);
//	} 
	
//	@Override
//	public int batchProxyAll(SellerInfoEntity sellerInfo, String originalSellerId) {
////		SellerCommodityEntity sellerCommodity = new SellerCommodityEntity();
////		sellerCommodity.setSellerId( sellerInfo.getId() );
////		RowBounds rowBounds = new RowBounds();
//		int infectRows = delete(sellerInfo.getId());
//		return sellerCommodityMapper.proxyAll( sellerInfo,  originalSellerId,null);
//	} 
}