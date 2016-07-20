 /*
 * 版本信息
 
 * 日期 2016-04-01 18:28:14
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.background;

import java.util.List;

import com.yougou.wfx.cms.dto.input.CommoditySaleCatRelaInputDto;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatRelaPageInputDto;
import com.yougou.wfx.cms.dto.output.CommoditySaleCatRelaOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommoditySaleCatRelaBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-01 18:28:15
 */
public interface ICommoditySaleCatRelaBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommoditySaleCatRelaOutputDto> findPage(CommoditySaleCatRelaPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommoditySaleCatRelaOutputDto getById(String id);
	
	/**
	 * 查询满足条件的记录数
	 */
	public int finaPageCount(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto);
	
	/**
	 * 查询所有满足条件的数据
	 * @param commoditySaleCatRelaEntity
	 * @return
	 */
	List<CommoditySaleCatRelaOutputDto> queryList(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto);
	
	/**
	 * 删除所有满足条件的数据，主要用于删除某个销售分类的绑定
	 * @param commoditySaleCatRelaEntity
	 * @return
	 */
	int multiRemove(CommoditySaleCatRelaInputDto commoditySaleCatRelaDto);
	
	Integer queryStyleNum(List<CommoditySaleCatRelaOutputDto> relaList);
	
	List<CommoditySaleCatRelaOutputDto> fillCatNames(List<CommoditySaleCatRelaOutputDto> relaList);
}

