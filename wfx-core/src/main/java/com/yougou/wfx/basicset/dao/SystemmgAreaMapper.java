 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.basicset.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.basicset.model.SystemmgAreaEntity;

/**
 * SystemmgAreaMapper
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
public interface SystemmgAreaMapper{
	
	int findPageCount(SystemmgAreaEntity systemmgAreaEntity);

	List<SystemmgAreaEntity> findPage(SystemmgAreaEntity systemmgAreaEntity,RowBounds rowBounds);
	
	int insert(SystemmgAreaEntity systemmgAreaEntity);
	
	int update(SystemmgAreaEntity systemmgAreaEntity);
	
	int remove(String id);
	
	SystemmgAreaEntity getById(String id);
	
	int removeSubAreaByNo(@Param("no")String no);
}
