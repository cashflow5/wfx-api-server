 /*
 * 版本信息
 
 * 日期 2016-06-02 20:18:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.discover.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.discover.model.DiscoverLogEntity;

/**
 * IDiscoverLogService接口
 * @author zhang.wj
 * @Date 创建时间：2016-06-02 20:18:26
 */
public interface IDiscoverLogService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(DiscoverLogEntity discoverLogEntity);

	/**
	 * 获取分页数据
	 */
	public List<DiscoverLogEntity> findPage(DiscoverLogEntity discoverLogEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(DiscoverLogEntity discoverLogEntity);
	
	/**
	 * 更新记录
	 */
	public int update(DiscoverLogEntity discoverLogEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public DiscoverLogEntity getById(String id); 
}