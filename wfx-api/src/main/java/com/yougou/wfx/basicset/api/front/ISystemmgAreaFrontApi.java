 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.front;

import java.util.List;

import com.yougou.wfx.basicset.dto.output.SystemmgAreaOutputDto;

/**
 * ISystemmgAreaFrontApi
 * @author li.j1
 * @Date 创建时间：2016-04-07 18:29:33
 */
public interface ISystemmgAreaFrontApi{
	
	/**
	 * 查询地区列表 
	 * @param inputDto
	 * @return level=1,no=root 返回所有省，level=2,no=省编码 返回该省下面的市，level=3,no=市编码 返回市下面所有的区
	 */
	List<SystemmgAreaOutputDto> queryAreaList(int level, String no);
}

