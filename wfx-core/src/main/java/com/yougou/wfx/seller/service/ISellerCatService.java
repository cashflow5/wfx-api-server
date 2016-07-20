 /*
 * 版本信息
 
 * 日期 2016-04-13 14:20:20
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.seller.model.SellerCatEntity;

/**
 * ISellerCatService接口
 * @author wfx
 * @Date 创建时间：2016-04-13 14:20:20
 */
public interface ISellerCatService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SellerCatEntity sellerCatEntity);

	/**
	 * 获取分页数据
	 */
	public List<SellerCatEntity> findPage(SellerCatEntity sellerCatEntity,RowBounds rowBounds);
	
	/**
	 * 获取查询数据
	 */
	public List<SellerCatEntity> queryList(SellerCatEntity sellerCatEntity);
	
	/**
	 * 获取查询数据
	 */
	public List<SellerCatEntity> queryChildren(String parentId);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SellerCatEntity sellerCatEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SellerCatEntity sellerCatEntity);
	
	/**
	 * 通过id删除记录
	 */
	public void remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SellerCatEntity getById(String id); 
}