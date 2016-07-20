 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.basicset.model.SystemmgAreaEntity;

/**
 * ISystemmgAreaService接口
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
public interface ISystemmgAreaService {
	
	/**
	 * 获取总条数
	 */
	int findPageCount(SystemmgAreaEntity systemmgAreaEntity);

	/**
	 * 获取分页数据
	 */
	List<SystemmgAreaEntity> findPage(SystemmgAreaEntity systemmgAreaEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	String insert(SystemmgAreaEntity systemmgAreaEntity);
	
	/**
	 * 更新记录
	 */
	int update(SystemmgAreaEntity systemmgAreaEntity);
	
	/**
	 * 通过id删除记录
	 */
	int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	SystemmgAreaEntity getById(String id); 
	
	/**
	 * 查询地区列表
	 * @param systemmgAreaEntity
	 * @return
	 */
	List<SystemmgAreaEntity> queryList(SystemmgAreaEntity systemmgAreaEntity);
	
	/**
	 * 删除当前地区及下级
	 * @param id
	 * @param no
	 * @return
	 */
	int removeCurrentAndSubArea(String id, String no);
}