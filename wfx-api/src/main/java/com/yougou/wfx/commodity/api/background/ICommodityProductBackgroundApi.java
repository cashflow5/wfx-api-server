 /*
 * 版本信息
 
 * 日期 2016-04-14 15:33:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import java.util.Map;

import com.yougou.wfx.commodity.dto.input.CommodityProductInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityProductPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityProductOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommodityProductBackgroundApi
 * @author zheng.qq
 * @Date 创建时间：2016-04-14 15:33:41
 */
public interface ICommodityProductBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityProductInputDto commodityProductDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommodityProductOutputDto> findPage(CommodityProductPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityProductInputDto commodityProductDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityProductOutputDto getById(String id);
	
	/**
	 * 批量预占库存接品
	 * @param preMap
	 * @return
	 */
	public boolean batchPreInventory(Map<String,Integer> preMap);
	
	/**
	 * 批量预占库存接品
	 * @param preMap
	 * @return
	 */
	public boolean batchFreePreInventory(Map<String,Integer> preMap);
	

	/**
	 * 清楚非自营数据
	 * @return
	 */
	public int clearNotSPYG();
}

