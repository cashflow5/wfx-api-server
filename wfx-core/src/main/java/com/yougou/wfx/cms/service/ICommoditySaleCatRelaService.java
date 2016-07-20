 /*
 * 版本信息
 
 * 日期 2016-04-01 18:28:14
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.cms.model.CommoditySaleCatRelaEntity;

/**
 * ICommoditySaleCatRelaService接口
 * @author wfx
 * @Date 创建时间：2016-04-01 18:28:15
 */
public interface ICommoditySaleCatRelaService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommoditySaleCatRelaEntity> findPage(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommoditySaleCatRelaEntity getById(String id);
	
	/**
	 * 查询所有满足条件的数据
	 * @param commoditySaleCatRelaEntity
	 * @return
	 */
	List<CommoditySaleCatRelaEntity> queryList(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
	
	/**
	 * 删除所有满足条件的数据，主要用于删除某个销售分类的绑定
	 * @param commoditySaleCatRelaEntity
	 * @return
	 */
	int multiRemove(CommoditySaleCatRelaEntity commoditySaleCatRelaEntity);
}