 /*
 * 版本信息
 
 * 日期 2016-03-31 19:13:58
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.commodity.dto.input.CommodityBrandCatb2cInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBrandCatb2cPageInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBrandInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandCatb2cOutputDto;

/**
 * ICommodityBrandCatb2cBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-03-31 19:13:58
 */
public interface ICommodityBrandCatb2cBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityBrandCatb2cInputDto commodityBrandCatb2cDto);
	
	public void batchInsert(String brandId,String[] catIds);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommodityBrandCatb2cOutputDto> findPage(CommodityBrandCatb2cPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 根据品牌ID查询关系数据
	 * @param brandId
	 * @return
	 */
	public List<String> queryCatb2cIdsByBrandId(String brandId);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityBrandCatb2cInputDto commodityBrandCatb2cDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityBrandCatb2cOutputDto getById(String id);
}

