 /*
 * 版本信息
 
 * 日期 2016-03-24 15:23:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerInfoEntity;

/**
 * SellerInfoMapper
 * @author zhangfeng
 * @Date 创建时间：2016-03-24 15:23:34
 */
public interface SellerInfoMapper{
	
	int findPageCount(SellerInfoEntity sellerInfoEntity);

	List<SellerInfoEntity> findPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds);
	
	int insert(SellerInfoEntity sellerInfoEntity);
	
	int update(SellerInfoEntity sellerInfoEntity);
	
	int remove(String id);
	
	SellerInfoEntity getById(String id);
	
	List<SellerInfoEntity> getLevelTwoSeller(String id);
	
	List<SellerInfoEntity> getLevelThreeSeller(String id);
	
	/**
	 * 分销商审核列表
	 * @param sellerInfoEntity
	 * @param rowBounds
	 * @return
	 */
	List<SellerInfoEntity> findSellerAuditPage(SellerInfoEntity sellerInfoEntity,RowBounds rowBounds);
	
	/**
	 * 分销商审核列表总数
	 * @param sellerInfoEntity
	 * @return
	 */
	int findSellerAuditCount(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 根据分销商ID获取分销商详情
	 * @param sellerId
	 * @return
	 */
	SellerInfoEntity getSellerInfoById(String sellerId);
	
	/**
	 * 分销商列表
	 * @param sellerInfoEntity
	 * @param rowBounds
	 * @return
	 */
	List<SellerInfoEntity> findSellerPage(SellerInfoEntity sellerInfoEntity, RowBounds rowBounds);
	
	/**
	 * 分销商列表总数
	 * @param sellerInfoEntity
	 * @return
	 */
	int findSellerCount(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 查询分销商列表
	 * @param sellerInfoEntity
	 * @return
	 */
	List<SellerInfoEntity> queryList(SellerInfoEntity sellerInfoEntity);
	
	/**
	 * 根据用户ID获取分销商和店铺状态
	 * @param memberId
	 * @return
	 */
	Map<String,Object>getSellerAndShopStatusByMemberId(String memberId);
	
	/**
	 * 根据用户ID获取分销商信息
	 * @param memberId
	 * @return
	 */
	SellerInfoEntity getSellerByMemberId(String memberId);
	
	
	/**
	 * 获取下级分销商列表
	 * @param sellerId
	 * @param rowBounds
	 * @return
	 */
	List<SellerInfoEntity>  getSubSellerList(String sellerId,RowBounds rowBounds);
	
	/**
	 * 获取下级分销商总数
	 * @param sellerId
	 * @return
	 */
	int getSubSellerCount(String sellerId);
	
	/**
	 * 分页查询所有成为分销商申请信息
	 * @return
	 */
	List<Map<String,String>> queryAllApplyToSeller(RowBounds rowBounds);
	
	/**
	 * 查询所有成为分销商申请信息总数
	 * @return
	 */
	int queryAllApplyToSellerCount();
	
	/**
	 * 批量审核通过，修改分销商状态及相关信息
	 * @param list
	 * @return
	 */
	int batchUpdateSellerStatus(List<SellerInfoEntity> list);
	
	/**
     * 根据店铺ID 获取分销商及店铺状态
     * @param shopId
     * @return
     */
    Map<String, Object> getSellerAndShopStatusByShopId(String shopId);
    
    String getSellerIdByLoginName(String loginName);

	Integer getLevelThreeSellerNum(String sellerId);

	List<SellerInfoEntity> getLevelThreeSellerListById(String sellerId,
			RowBounds rowBounds);
	/* 查粉丝数目 */
	Integer getFansCountBySellerId(String sellerId);

}
