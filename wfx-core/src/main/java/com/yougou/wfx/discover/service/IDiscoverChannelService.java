 /*
 * 版本信息
 
 * 日期 2016-06-02 13:51:45
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.DiscoverChannelEntity;

/**
 * IDiscoverChannelService接口
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 13:51:45
 */
public interface IDiscoverChannelService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(DiscoverChannelEntity discoverChannelEntity);

	/**
	 * 获取分页数据
	 */
	public List<DiscoverChannelEntity> findPage(DiscoverChannelEntity discoverChannelEntity,RowBounds rowBounds);
	
	/**
	 * 获取频道列表
	 * @param discoverChannelEntity
	 * @return
	 */
	List<DiscoverChannelEntity> getChannelList(DiscoverChannelEntity discoverChannelEntity);
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverChannelEntity discoverChannelEntity);
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverChannelEntity discoverChannelEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverChannelEntity getById(String id);
	
	/**
	 * 获取排序号
	 * @param id
	 * @return
	 */
	public int getorderMark(String id);

	public List<DiscoverChannelEntity> queryList(
			DiscoverChannelEntity discoverChannelEntity);
	/**
	 * 修改序号
	 * @return
	 */
	public int updateOrderMark();
	
	
	public DiscoverChannelEntity getPrevious(DiscoverChannelEntity discoverChannelEntity);
	
	
	public DiscoverChannelEntity getNext(DiscoverChannelEntity discoverChannelEntity);
	
	public int batchRemove(List<String> list);
}