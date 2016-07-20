 /*
 * 版本信息
 
 * 日期 2016-03-25 17:36:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.member.model.MemberAddressEntity;

/**
 * IMemberAddressService接口
 * @author wfx
 * @Date 创建时间：2016-03-25 17:36:56
 */
public interface IMemberAddressService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(MemberAddressEntity memberAddressEntity);

	/**
	 * 获取分页数据
	 */
	public List<MemberAddressEntity> findPage(MemberAddressEntity memberAddressEntity,RowBounds rowBounds);
	
	/**
	 * 获取查询数据
	 */
	public List<MemberAddressEntity> queryList(MemberAddressEntity memberAddressEntity);
	
	/**
	 * 保存单条记录
	 */
	public String insert(MemberAddressEntity memberAddressEntity);
	
	/**
	 * 更新记录
	 */
	public int update(MemberAddressEntity memberAddressEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public MemberAddressEntity getById(String id); 
}