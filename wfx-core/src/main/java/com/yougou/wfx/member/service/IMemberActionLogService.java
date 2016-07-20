 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.member.model.MemberActionLogEntity;

/**
 * IMemberActionLogService接口
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
public interface IMemberActionLogService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(MemberActionLogEntity memberActionLogEntity);

	/**
	 * 获取分页数据
	 */
	public List<MemberActionLogEntity> findPage(MemberActionLogEntity memberActionLogEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(MemberActionLogEntity memberActionLogEntity);
	
	/**
	 * 更新记录
	 */
	public int update(MemberActionLogEntity memberActionLogEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public MemberActionLogEntity getById(String id); 
}