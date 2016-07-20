 /*
 * 版本信息
 
 * 日期 2016-06-02 20:18:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.DiscoverLogEntity;

/**
 * DiscoverLogMapper
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 20:18:26
 */
public interface DiscoverLogMapper{
	
	int findPageCount(DiscoverLogEntity discoverLogEntity);

	List<DiscoverLogEntity> findPage(DiscoverLogEntity discoverLogEntity,RowBounds rowBounds);
	
	int insert(DiscoverLogEntity discoverLogEntity);
	
	int update(DiscoverLogEntity discoverLogEntity);
	
	int remove(String id);
	
	DiscoverLogEntity getById(String id);
}
