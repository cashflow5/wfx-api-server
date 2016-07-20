 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.commodity.dto.input.CommodityBrandInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBrandPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandOutputDto;

/**
 * ICommodityBrandBackgroundApi
 * @author li.j1
 * @Date 创建时间：2016-03-23 18:39:34
 */
public interface ICommodityBrandBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityBrandInputDto commodityBrandDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommodityBrandOutputDto> findPage(CommodityBrandPageInputDto pageInputDto,PageModel<CommodityBrandOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityBrandInputDto commodityBrandDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityBrandOutputDto getById(String id);
	
	/**
	 * 批量更新品牌是否热门
	 * @param brandIds
	 * @param flag  1 新增，2 删除
	 * @return
	 */
	boolean batchUpdateHotBrand(String[] brandIds, String userName, int flag);
	
	/**
	 * 查询热门商品数量
	 * @param pageInputDto
	 * @return
	 */
	int findPageCount(CommodityBrandPageInputDto pageInputDto);
	
	void batchUpdateBrandUseFlag();
	
}

