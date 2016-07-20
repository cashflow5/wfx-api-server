 /*
 * 版本信息
 
 * 日期 2016-04-09 13:01:36
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.basicset.model.SysConfigEntity;

/**
 * ISysConfigService接口
 * @author wfx
 * @Date 创建时间：2016-04-09 13:01:37
 */
public interface ISysConfigService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(SysConfigEntity sysConfigEntity);

	/**
	 * 获取分页数据
	 */
	public List<SysConfigEntity> findPage(SysConfigEntity sysConfigEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SysConfigEntity sysConfigEntity);
	
	/**
	 * 更新记录
	 */
	public int update(SysConfigEntity sysConfigEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public SysConfigEntity getById(String id); 
	
	/**
	 * 保存系统配置的操作日志
	 * @param sysConfigEntity
	 * @param operationType
	 */
	public void saveSysLog(SysConfigEntity sysConfigEntity,String operationType);
	
	public String getValueBykey(String key);
}