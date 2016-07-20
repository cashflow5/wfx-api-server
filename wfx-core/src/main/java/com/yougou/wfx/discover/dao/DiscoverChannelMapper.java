 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.discover.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.DiscoverChannelEntity;

/**
 * DiscoverChannelMapper
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
public interface DiscoverChannelMapper{
	
	int findPageCount(DiscoverChannelEntity discoverChannelEntity);

	List<DiscoverChannelEntity> findPage(DiscoverChannelEntity discoverChannelEntity,RowBounds rowBounds);
	
	int insert(DiscoverChannelEntity discoverChannelEntity);
	
	int update(DiscoverChannelEntity discoverChannelEntity);
	
	int remove(String id);
	
	DiscoverChannelEntity getById(String id);
	
	
	Integer getorderMark(String id);

	List<DiscoverChannelEntity> queryList(
			DiscoverChannelEntity discoverChannelEntity);
	
	List<DiscoverChannelEntity> getChannelList(DiscoverChannelEntity discoverChannelEntity);

	int updateOrderMark();

	DiscoverChannelEntity getPrevious(DiscoverChannelEntity discoverChannelEntity);
	
	DiscoverChannelEntity getNext(DiscoverChannelEntity discoverChannelEntity);
	
	int updateReduceOrderMark(DiscoverChannelEntity discoverChannelEntity);
	
	int batchRemove(List<String> list);

}
