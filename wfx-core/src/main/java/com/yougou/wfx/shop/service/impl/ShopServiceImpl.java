 /*
 * 版本信息
 
 * 日期 2016-03-31 10:45:08
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.shop.dao.ShopMapper;
import com.yougou.wfx.shop.dto.output.DiscoverShopOutputDto;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;

/**
 * IShopService实现
 * @author wuyang
 * @Date 创建时间：2016-03-31 10:45:08
 */
@Service
public class ShopServiceImpl extends BaseService implements IShopService {
	
	@Resource
	private ShopMapper shopMapper;

	@Override
	public int findPageCount(ShopEntity shopEntity){
		return shopMapper.findPageCount(shopEntity);
	}

	@Override
	public List<ShopEntity> findPage(ShopEntity shopEntity,RowBounds rowBounds){
		return shopMapper.findPage(shopEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(ShopEntity shopEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		shopEntity.setId(strId);
		shopMapper.insert(shopEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(ShopEntity shopEntity){
		//清除店铺和分销商状态缓存
		String memberId = shopMapper.getMemberIdByShopId(shopEntity.getId());
		WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_MEMBER, memberId);		
		WFXRedisUtil.delete(WFXModuleType.SELLER_STATUS_BY_SHOP, shopEntity.getId());
		return  shopMapper.update(shopEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return shopMapper.remove(id);
	}
	
	@Override
	public ShopEntity getById(String id){
		return shopMapper.getById(id);
	}

	@Override
	public int closeShop(ShopEntity shopEntity) {
		// TODO Auto-generated method stub
		return shopMapper.closeShop(shopEntity);
	}

	@Override
	public int getCountsByShopName(String shopName) {
		// TODO Auto-generated method stub
		return shopMapper.getCountsByShopName(shopName);
	}

	@Override
	public ShopEntity getShopBySeller(String sellerId) {
		return shopMapper.getShopBySeller(sellerId);
	}

	@Override
	public int batchUpdateShopStatus(List<ShopEntity> list) {
		// TODO Auto-generated method stub
		return shopMapper.batchUpdateShopStatus(list);
	}

	@Override
	public int findInfoPageCount(DiscoverShopOutputDto disShop) {
		return shopMapper.findInfoPageCount(disShop);
	}

	@Override
	public List<DiscoverShopOutputDto> findInfoPage(
			DiscoverShopOutputDto disShop, RowBounds rowBounds) {
		return shopMapper.findInfoPage(disShop, rowBounds);
	}

	@Override
	public ShopEntity getShopByPhoneNumber(String loginName) {
		return shopMapper.getShopByPhoneNumber(loginName);
	}

	@Override
	public ShopEntity getShopByMemberId(String memberId) {
		return shopMapper.getShopByMemberId(memberId);
	} 
}
