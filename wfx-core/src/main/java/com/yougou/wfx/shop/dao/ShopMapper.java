 /*
 * 版本信息
 
 * 日期 2016-03-31 10:45:08
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.shop.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.shop.dto.output.DiscoverShopOutputDto;
import com.yougou.wfx.shop.model.ShopEntity;

/**
 * ShopMapper
 * @author wuyang
 * @Date 创建时间：2016-03-31 10:45:08
 */
public interface ShopMapper{
	
	int findPageCount(ShopEntity shopEntity);

	List<ShopEntity> findPage(ShopEntity shopEntity,RowBounds rowBounds);
	
	int findInfoPageCount(DiscoverShopOutputDto disShop);
	
	List<DiscoverShopOutputDto> findInfoPage(DiscoverShopOutputDto disShop,RowBounds rowBounds);
	
	int insert(ShopEntity shopEntity);
	
	int update(ShopEntity shopEntity);
	
	int remove(String id);
	
	ShopEntity getById(String id);
	
	/**
	 * 分销商停止合作，关闭店铺
	 * @param shopEntity
	 */
	int closeShop(ShopEntity shopEntity);
	
	/**
	 * 校验店铺名称唯一性：根据店铺名称获取数量
	 * @param shopName
	 * @return
	 */
	int getCountsByShopName(String shopName);
	
	/**
	 * 根据分销商ID获取店铺信息
	 * @param sellerId
	 * @return
	 */
	ShopEntity getShopBySeller(String sellerId);
	
	/**
	 * 后分销商自动审核JOB,审核通过批量更新店铺状态及相关信息
	 * @param list
	 * @return
	 */
	int batchUpdateShopStatus(List<ShopEntity> list);
	
	/**
	 * 根据店铺ID 获取用户ID
	 * @param shopId
	 * @return
	 */
	String getMemberIdByShopId(String shopId);
	
	/**
	 * 批量插入敏感词，用户敏感词库初始化
	 * @param list 敏感词集合
	 */
	void insertSensitiveWord(List<String> list);
	
	/**
	 * 获取所有敏感词
	 * @return
	 */
	List<String> getAllSensitiveWord();

	/**
	 * 根据手机获取店铺
	 * @param loginName
	 * @return
	 */
	ShopEntity getShopByPhoneNumber(String loginName);

	ShopEntity getShopByMemberId(String memberId);
	
}
