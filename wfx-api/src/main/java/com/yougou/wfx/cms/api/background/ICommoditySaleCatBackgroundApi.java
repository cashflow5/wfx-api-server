 /*
 * 版本信息
 
 * 日期 2016-03-24 17:49:13
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.cms.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatInputDto;
import com.yougou.wfx.cms.dto.input.CommoditySaleCatPageInputDto;
import com.yougou.wfx.cms.dto.output.CommoditySaleCatOutputDto;

/**
 * ICommoditySaleCatBackgroundApi
 * @author wzf
 * @Date 创建时间：2016-03-24 17:49:14
 */
public interface ICommoditySaleCatBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommoditySaleCatInputDto commoditySaleCatDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommoditySaleCatOutputDto> findPage(CommoditySaleCatPageInputDto pageInputDto,PageModel<CommoditySaleCatOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommoditySaleCatInputDto commoditySaleCatDto);

	/**
	 * 更新子分类
	 */
	public int updateChildren(CommoditySaleCatInputDto commoditySaleCatDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommoditySaleCatOutputDto getById(String id);
	
	/**
	 * 获取所有记录
	 * @param pageInputDto
	 * @return
	 */
	public List<CommoditySaleCatOutputDto> queryList(CommoditySaleCatInputDto inputDto);
	
	/**
	 * 查询符合条件记录数
	 * @param inputDto
	 * @return
	 */
	public int findPageCount(CommoditySaleCatInputDto inputDto);
	
	/**
	 * 查询某销售分类下是否有绑定的基础分类
	 * @param inputDto
	 * @return
	 */
	public boolean hasBindCat(CommoditySaleCatInputDto inputDto);
	
	PageModel<CommoditySaleCatOutputDto> queryHotCatPage(CommoditySaleCatPageInputDto pageInputDto,PageModel<CommoditySaleCatOutputDto> pageModel);

	List<CommoditySaleCatOutputDto> queryHotCatList(CommoditySaleCatInputDto inputDto);

	int getHotCatCount(CommoditySaleCatInputDto commoditySaleCatInputDto);

	int getMaxHotCatSn();
	
	int batchUpdate(List<CommoditySaleCatInputDto> inputDtoList);
	
	/**
	 * 统计商品销售分类SKU数量调度
	 * @return
	 */
	public void saleCatSkuNumJob();
}

