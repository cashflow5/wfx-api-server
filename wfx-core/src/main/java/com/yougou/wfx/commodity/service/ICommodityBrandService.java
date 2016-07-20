 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityBrandEntity;

/**
 * ICommodityBrandService接口
 * @author wuyang
 * @Date 创建时间：2016-03-23 18:39:34
 */
public interface ICommodityBrandService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityBrandEntity commodityBrandEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityBrandEntity> findPage(CommodityBrandEntity commodityBrandEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityBrandEntity commodityBrandEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityBrandEntity commodityBrandEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityBrandEntity getById(String id); 
	
	boolean batchUpdateHotBrand(String[] brandIds, String userName, int flag);
	
	/**
	 * 根据一级销售分类ID查询对应的品牌
	 * @param catId
	 * @return
	 */
	List<CommodityBrandEntity> queryCommodityBrandByCatId(String catId);
	
	void batchUpdateBrandUseFlag();
}