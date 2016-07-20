 /*
 * 版本信息
 
 * 日期 2016-03-24 16:17:44
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.member.model.MemberProfileEntity;

/**
 * IMemberProfileService接口
 * @author wuyang
 * @Date 创建时间：2016-03-24 16:17:44
 */
public interface IMemberProfileService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(MemberProfileEntity memberProfileEntity);

	/**
	 * 获取分页数据
	 */
	public List<MemberProfileEntity> findPage(MemberProfileEntity memberProfileEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(MemberProfileEntity memberProfileEntity);
	
	/**
	 * 更新记录
	 */
	public int update(MemberProfileEntity memberProfileEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public MemberProfileEntity getById(String id); 
	
	/**
	 * 根据用户ID 获取用户基本信息
	 * @param memberId
	 * @return
	 */
	MemberProfileEntity getMemberProfileByMemberId(String memberId);
	
	/**
	 * 批量更新订单数和金额字段
	 * @return
	 */
	public int batchUpdateOrderAmountAndCount();
}