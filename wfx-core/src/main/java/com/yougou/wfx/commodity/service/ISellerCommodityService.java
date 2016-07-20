 /*
 * 版本信息
 
 * 日期 2016-04-13 16:39:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.seller.model.SellerInfoEntity;

/**
 * ISellerCommodityService接口
 * @author wfx
 * @Date 创建时间：2016-04-13 16:39:55
 */
public interface ISellerCommodityService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SellerCommodityEntity sellerCommodityEntity);

	/**
	 * 获取分页数据
	 */
	public List<SellerCommodityEntity> findPage(SellerCommodityEntity sellerCommodityEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerCommodityEntity sellerCommodityEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SellerCommodityEntity sellerCommodityEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SellerCommodityEntity getById(String id); 
	
	/**
	 * 根据分销商ID和商品四个属性查询代理商品
	 * @param sellerId
	 * @param commodityId
	 * @return
	 */
	List<SellerCommodityEntity> getSellerCommodity( SellerCommodityEntity entity); 
	
	/**
	 * 批量新增代理商品
	 * @param list
	 * @return
	 */
	int batchInsertSellerCommodity(List<SellerCommodityEntity> lstSellerCommodity);
	
	/**
	 * 批量修改代理商品
	 * @param list
	 * @return
	 */
	public int batchUpdateSellerCommodity(List<SellerCommodityEntity> lstSellerCommodity);
	
	/**
	 * 修改上下架商品
	 * @param list
	 * @return
	 */
	public void updateSellerShelvesStatus(List<SellerCommodityEntity> sellerCommodity);
	
	/**
	 * 提取可上架代理商品(返回值说明：0:不能,非0:可以)
	 * @param list
	 * @return
	 */
	public List<SellerCommodityEntity> pickUpShelvesCommodities(List<String> ids);
	
	/**
	 * 获取代理商品Id
	 * @param sellerCommodityEntity
	 * @return
	 */
	public 	String   querySellerCommodity(SellerCommodityEntity sellerCommodityEntity);

	public int delete(String sellerId);
	
//	/**
//	 * 一键代理所有商品
//	 * @param sellerInfo
//	 * @param originalSellerId
//	 * @return
//	 */
//	public int proxyAll(SellerInfoEntity sellerInfo, String originalSellerId);
}