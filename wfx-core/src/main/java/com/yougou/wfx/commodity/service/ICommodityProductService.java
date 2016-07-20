 /*
 * 版本信息
 
 * 日期 2016-04-14 13:34:05
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityProductEntity;

/**
 * ICommodityProductService接口
 * @author wfx
 * @Date 创建时间：2016-04-14 13:34:06
 */
public interface ICommodityProductService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityProductEntity commodityProductEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityProductEntity> findPage(CommodityProductEntity commodityProductEntity,RowBounds rowBounds);
	
	/**
	 * 获取查询数据
	 */
	public List<CommodityProductEntity> queryList(CommodityProductEntity commodityProductEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityProductEntity commodityProductEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityProductEntity commodityProductEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityProductEntity getById(String id);
	
	/**
	 * 预占库存接口
	 * @param commodityProductId
	 * @param num
	 * @return
	 */
	boolean preInventory(String commodityProductId,Integer num);
	
	/**
	 * 释放预占库存接口
	 * @param commodityProductId
	 * @param num
	 * @return
	 */
	boolean freePreInventory(String commodityProductId,Integer num);
	
	/**
	 * 查询货口的分类id
	 * @param id
	 * @return
	 */
	List<String> getCatId(String id);
	
	
    /**
    * 批量方法，根据货品编码集合获取货品列表
    *
    * @param productNos  
    * @return List<CommodityProductOutputDto>
    */
	List<CommodityProductEntity> getProductByProductNos(List<String> productNos);

	/**
	 * 删除非自营数据
	 * @return
	 */
	public int clearNotSPYG();
	
	 /**
	  * 通过商品id获取货品信心
	  * 
	  * @param commodityId
	  * @return
	  */
	List<CommodityProductEntity>  getProductByCommodityId(Map<String,String> map);
	
}