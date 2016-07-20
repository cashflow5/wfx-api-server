/*
 * 版本信息

 * 日期 2016-03-24 11:14:00

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.commodity.dto.input.CommodityCatb2cInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityCatb2cPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityCatb2cOutputDto;

/**
 * ICommodityCatb2cBackgroundApi
 * 
 * @author wang.zf
 * @Date 创建时间：2016-03-24 11:14:01
 */
public interface ICommodityCatb2cBackgroundApi {
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);

	/**
	 * 保存单条记录
	 */
	public String insert(CommodityCatb2cInputDto commodityCatb2cDto);

	/**
	 * 获取分页数据
	 */
	public PageModel<CommodityCatb2cOutputDto> findPage(CommodityCatb2cPageInputDto pageInputDto, PageModel<CommodityCatb2cOutputDto> pageModel);

	/**
	 * 更新记录
	 */
	public int update(CommodityCatb2cInputDto commodityCatb2cDto);

	/**
	 * 通过id查询数据
	 */
	public CommodityCatb2cOutputDto getById(String id);

	/**
	 * 查询基础分类列表
	 */
	public List<CommodityCatb2cOutputDto> queryList(CommodityCatb2cInputDto commodityCatb2cDto);

	/**
	 * 查询出满足条件记录数量
	 */
	int findPageCount(CommodityCatb2cInputDto commodityCatb2cDto);

	/**
	 * 查询基础分类下是否有在售的商品
	 * 
	 * @param commodityCatb2cDto
	 * @return
	 */
	boolean hasCommodityOnCat(CommodityCatb2cInputDto commodityCatb2cDto);

	/**
	 * 批量更新SkuNum
	 */
	public int batchUpdateSkuNum();

	List<CommodityCatb2cOutputDto> getCatList(String brandNo, String baseCatId,
			int level);

}
