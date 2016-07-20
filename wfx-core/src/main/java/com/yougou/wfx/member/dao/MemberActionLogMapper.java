 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.member.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.member.model.MemberActionLogEntity;

/**
 * MemberActionLogMapper
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
public interface MemberActionLogMapper{
	
	int findPageCount(MemberActionLogEntity memberActionLogEntity);

	List<MemberActionLogEntity> findPage(MemberActionLogEntity memberActionLogEntity,RowBounds rowBounds);
	
	int insert(MemberActionLogEntity memberActionLogEntity);
	
	int update(MemberActionLogEntity memberActionLogEntity);
	
	int remove(String id);
	
	MemberActionLogEntity getById(String id);
}
