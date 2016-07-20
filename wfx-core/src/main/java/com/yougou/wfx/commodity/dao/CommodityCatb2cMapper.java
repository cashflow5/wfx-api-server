/*
 * 版本信息

 * 日期 2016-03-24 11:14:00

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.commodity.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;

/**
 * CommodityCatb2cMapper
 * 
 * @author wzf
 * @Date 创建时间：2016-03-24 11:14:01
 */
public interface CommodityCatb2cMapper {

	int findPageCount(CommodityCatb2cEntity commodityCatb2cEntity);

	List<CommodityCatb2cEntity> findPage(CommodityCatb2cEntity commodityCatb2cEntity, RowBounds rowBounds);

	int insert(CommodityCatb2cEntity commodityCatb2cEntity);

	int update(CommodityCatb2cEntity commodityCatb2cEntity);

	int remove(String id);
	//<!--财务佣金设置有调用  -->
	CommodityCatb2cEntity getById(String id);

	List<CommodityCatb2cEntity> queryList(CommodityCatb2cEntity commodityCatb2cEntity);

	HashMap<String, String> getCatIds(String no);

	/**
	 * 批量更新3级分类的SKU数量
	 * @return
	 */
	int batchUpdateSkuNumLevel_3();

	/**
	 * 批量更新1，2级分类的SKU数量
	 * @return
	 */
	int batchUpdateSkuNumLevel_2_1();

	List<CommodityCatb2cEntity> getCatList(@Param("brandNo")String brandId, @Param("baseCatId")String baseCatId,
			@Param("level")int level);
}
