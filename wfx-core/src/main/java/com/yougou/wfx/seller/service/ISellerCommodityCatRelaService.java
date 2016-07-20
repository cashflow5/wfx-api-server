 /*
 * 版本信息
 
 * 日期 2016-04-14 14:39:16
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerCommodityCatRelaEntity;

/**
 * ISellerCommodityCatRelaService接口
 * @author zheng.qq
 * @Date 创建时间：2016-04-14 14:39:16
 */
public interface ISellerCommodityCatRelaService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);

	/**
	 * 获取分页数据
	 */
	public List<SellerCommodityCatRelaEntity> findPage(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SellerCommodityCatRelaEntity getById(String id); 
	
	/**
	 * 检索数据
	 */
	public List<SellerCommodityCatRelaEntity> queryList(SellerCommodityCatRelaEntity sellerCommodityCatRelaEntity);
	
	/**
	 * 通过catId删除记录
	 */
	public int removeByCatId(String catId);
}