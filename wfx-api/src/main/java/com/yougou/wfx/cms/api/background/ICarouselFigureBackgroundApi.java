 /*

 * 版本信息

 

 * 日期 2016-03-31 16:07:39

 

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd

 * All Rights Reserved.

 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得

 * 使用、复制、修改或发布本软件。

 */


package com.yougou.wfx.cms.api.background;

import java.util.Map;

import com.yougou.wfx.cms.dto.input.CarouselFigureInputDto;
import com.yougou.wfx.cms.dto.input.CarouselFigurePageInputDto;
import com.yougou.wfx.cms.dto.output.CarouselFigureOutputDto;
import com.yougou.wfx.dto.base.PageModel;



/**
 * ICmsCarouselFigureBackgroundApi
 * @author zhangfeng
 * @Date 创建时间：2016-03-31 16:07:39
 */
public interface ICarouselFigureBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CarouselFigureInputDto cmsCarouselFigureDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CarouselFigureOutputDto> findPage(CarouselFigurePageInputDto pageInputDto,PageModel<CarouselFigureOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CarouselFigureInputDto cmsCarouselFigureDto);
	
	/**
	 * 通过id查询数据
	 */
	public CarouselFigureOutputDto getById(String id);
	
	/**
	 * 获取轮播图限制信息：有效的轮播图当前最大排序号，总数
	 * @return
	 */
	Map<String,Integer> getLimitInfo(String type);
}

