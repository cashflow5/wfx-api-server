 /*
 * 版本信息
 
 * 日期 2016-03-31 19:13:58
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityBrandCatb2cEntity;

/**
 * ICommodityBrandCatb2cService接口
 * @author wfx
 * @Date 创建时间：2016-03-31 19:13:58
 */
public interface ICommodityBrandCatb2cService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityBrandCatb2cEntity> findPage(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity);
	
	public void batchInsert(List<CommodityBrandCatb2cEntity> commodityBrandCatb2cEntityList);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityBrandCatb2cEntity commodityBrandCatb2cEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过品牌ID批量删除
	 * @param brandId
	 * @return
	 */
	public int deleteByBrandId(String brandId);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityBrandCatb2cEntity getById(String id); 
}