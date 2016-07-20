 /*

 * 版本信息

 

 * 日期 2016-03-31 16:07:39

 

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd

 * All Rights Reserved.

 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得

 * 使用、复制、修改或发布本软件。

 */


package com.yougou.wfx.cms.api.front;

import java.util.List;

import com.yougou.wfx.cms.dto.output.CarouselFigureOutputDto;

/**
 * 轮播图接口
 * @author li.lq
 * @Date   2016年6月23日
 */
public interface ICarouselFigureFrontApi{
	/**
	 * 查询h5首页轮播图
	 * @return
	 */
	List<CarouselFigureOutputDto> queryH5IndexCarouselFigureList();
}

