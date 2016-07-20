 /*
 * 版本信息
 
 * 日期 2016-05-19 09:43:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.basicset.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.basicset.model.AppVersionEntity;

/**
 * AppVersionMapper
 * @author wfx
 * @Date 创建时间：2016-05-19 09:43:27
 */
public interface AppVersionMapper{
	
	int findPageCount(AppVersionEntity appVersionEntity);

	List<AppVersionEntity> findPage(AppVersionEntity appVersionEntity,RowBounds rowBounds);
	
	int insert(AppVersionEntity appVersionEntity);
	
	int update(AppVersionEntity appVersionEntity);
	
	int remove(String id);
	
	AppVersionEntity getById(String id);

	AppVersionEntity getAndroidNewestVersion();
}
