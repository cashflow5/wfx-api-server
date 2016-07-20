 /*
 * 版本信息
 
 * 日期 2016-03-30 10:51:22
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.SysLogEntity;

/**
 * SysLogMapper
 * @author luoq
 * @Date 创建时间：2016-03-30 10:51:23
 */
public interface SysLogMapper{
	
	int findPageCount(SysLogEntity sysLogEntity);

	List<SysLogEntity> findPage(SysLogEntity sysLogEntity,RowBounds rowBounds);
	
	int insert(SysLogEntity sysLogEntity);
	
	int update(SysLogEntity sysLogEntity);
	
	int remove(String id);
	
	SysLogEntity getById(String id);
}
