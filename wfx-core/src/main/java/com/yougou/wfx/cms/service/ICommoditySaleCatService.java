 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.cms.model.CommoditySaleCatEntity;

/**
 * ICommoditySaleCatService接口
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
public interface ICommoditySaleCatService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommoditySaleCatEntity commoditySaleCatEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommoditySaleCatEntity> findPage(CommoditySaleCatEntity commoditySaleCatEntity,RowBounds rowBounds);
	
	/**
	 * 查询所有记录
	 * @param commoditySaleCatEntity
	 * @return
	 */
	public List<CommoditySaleCatEntity> queryList(CommoditySaleCatEntity commoditySaleCatEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(CommoditySaleCatEntity commoditySaleCatEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommoditySaleCatEntity commoditySaleCatEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommoditySaleCatEntity getById(String id);
	
	/**
	 * 获取销售分类下的所有子分类
	 * @param commoditySaleCatEntity
	 * @return
	 */
	public List<CommoditySaleCatEntity> getChildren(CommoditySaleCatEntity commoditySaleCatEntity);

	/**
	 * 更新子分类
	 * @param commoditySaleCatEntity
	 * @return
	 */
	int updateChildren(CommoditySaleCatEntity commoditySaleCatEntity);
	
	/**
	 * 分页查询热门分类
	 * @param commoditySaleCatEntity
	 * @param rowBounds
	 * @return
	 */
	List<CommoditySaleCatEntity> queryHotCatList(CommoditySaleCatEntity commoditySaleCatEntity,RowBounds rowBounds);
	
	/**
	 * 查询满足条件的所有热门分类
	 * @param commoditySaleCatEntity
	 * @return
	 */
	List<CommoditySaleCatEntity> queryHotCatList(CommoditySaleCatEntity commoditySaleCatEntity);
	
	/**
	 * 查询满足条件的热门分类数量
	 * @param commoditySaleCatEntity
	 * @return
	 */
	int getHotCatCount(CommoditySaleCatEntity commoditySaleCatEntity);
	
	/**
	 * 查询热门分类最大排序号
	 * @return
	 */
	int getMaxHotCatSn();
	
	/**
	 * 批量更新
	 * @return
	 */
	int batchUpdate(List<CommoditySaleCatEntity> entityList);
	
	/**
	 * 统计商品销售分类SKU数量
	 * @return
	 */
	public int saleCatSkuNumJob();
}