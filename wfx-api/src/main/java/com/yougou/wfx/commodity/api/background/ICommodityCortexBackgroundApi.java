 /*
 * 版本信息
 
 * 日期 2016-06-21 17:57:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import com.yougou.wfx.commodity.dto.input.CommodityCortexInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityCortexPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommodityCortexBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
public interface ICommodityCortexBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityCortexInputDto commodityCortexDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommodityCortexOutputDto> findPage(CommodityCortexPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityCortexInputDto commodityCortexDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityCortexOutputDto getById(String id);

	/**
	 * 更新皮质数据
	 * @return
	 */
	public int updateCommodityCortex();
}

