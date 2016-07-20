 /*
 * 版本信息
 
 * 日期 2016-06-21 17:57:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;
import com.yougou.wfx.commodity.model.CommodityCortexEntity;

/**
 * CommodityCortexMapper
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
public interface CommodityCortexMapper{
	
	int findPageCount(CommodityCortexEntity commodityCortexEntity);

	List<CommodityCortexEntity> findPage(CommodityCortexEntity commodityCortexEntity,RowBounds rowBounds);
	
	int insert(CommodityCortexEntity commodityCortexEntity);
	
	int update(CommodityCortexEntity commodityCortexEntity);
	
	int remove(String id);
	
	CommodityCortexEntity getById(String id);

	List<CommodityCortexEntity> queryList(CommodityCortexEntity commodityCortexEntity);

	int updateCommodityCortex();

}
