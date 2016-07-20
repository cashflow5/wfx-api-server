 /*
 * 版本信息
 
 * 日期 2016-03-24 11:14:00
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;

/**
 * ICommodityCatb2cService接口
 * @author wuyang
 * @Date 创建时间：2016-03-24 11:14:01
 */
public interface ICommodityCatb2cService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityCatb2cEntity commodityCatb2cEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityCatb2cEntity> findPage(CommodityCatb2cEntity commodityCatb2cEntity,RowBounds rowBounds);
	
	/**
	 * 获取基础分类列表
	 */
	public List<CommodityCatb2cEntity> queryList(CommodityCatb2cEntity commodityCatb2cEntity);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityCatb2cEntity commodityCatb2cEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityCatb2cEntity commodityCatb2cEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityCatb2cEntity getById(String id);
	
	/**
	 * 查询某个分类下的所有下级分类
	 * @param commodityCatb2c
	 * @return
	 */
	List<CommodityCatb2cEntity> getNextLevelCats(CommodityCatb2cEntity commodityCatb2cEntity);
	
	/**
	 * 查询某个分类下的所有三级分类
	 * @param commodityCatb2c
	 * @return
	 */
	List<CommodityCatb2cEntity> getThreeLevelCats(CommodityCatb2cEntity commodityCatb2cEntity);
	
	/**
	 * 批量修改Sku
	 * @return
	 */
	public int batchUpdateSkuNum();
	
	HashMap<String, String> getCatIds(String no);

	List<CommodityCatb2cEntity> getCatList(String brandId,
			String baseCatId, int level);
}