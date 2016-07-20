 /*
 * 版本信息
 
 * 日期 2016-05-19 09:43:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.basicset.model.AppVersionEntity;

/**
 * IAppVersionService接口
 * @author wfx
 * @Date 创建时间：2016-05-19 09:43:27
 */
public interface IAppVersionService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(AppVersionEntity appVersionEntity);

	/**
	 * 获取分页数据
	 */
	public List<AppVersionEntity> findPage(AppVersionEntity appVersionEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(AppVersionEntity appVersionEntity);
	
	/**
	 * 更新记录
	 */
	public int update(AppVersionEntity appVersionEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public AppVersionEntity getById(String id);

	/**
	 * 获取最新的App版本
	 * @return
	 */
	public AppVersionEntity getAndroidNewestVersion(); 
}