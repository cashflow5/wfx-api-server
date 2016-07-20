 /*
 * 版本信息
 
 * 日期 2016-04-09 13:01:36
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.basicset.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.basicset.model.SysConfigEntity;

/**
 * SysConfigMapper
 * @author wfx
 * @Date 创建时间：2016-04-09 13:01:37
 */
public interface SysConfigMapper{
	
	int findPageCount(SysConfigEntity sysConfigEntity);

	List<SysConfigEntity> findPage(SysConfigEntity sysConfigEntity,RowBounds rowBounds);
	
	int insert(SysConfigEntity sysConfigEntity);
	
	int update(SysConfigEntity sysConfigEntity);
	
	int remove(String id);
	
	SysConfigEntity getById(String id);

	List<SysConfigEntity> queryList(SysConfigEntity sysConfigEntity);
}
