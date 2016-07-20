 /*
 * 版本信息
 
 * 日期 2016-04-07 18:29:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.basicset.dto.input.SystemmgAreaInputDto;
import com.yougou.wfx.basicset.dto.input.SystemmgAreaPageInputDto;
import com.yougou.wfx.basicset.dto.output.SystemmgAreaOutputDto;

/**
 * ISystemmgAreaBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-07 18:29:33
 */
public interface ISystemmgAreaBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	String insert(SystemmgAreaInputDto systemmgAreaDto);
	
	/**
	 * 获取分页数据
	 */
	PageModel<SystemmgAreaOutputDto> findPage(SystemmgAreaPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	int update(SystemmgAreaInputDto systemmgAreaDto);
	
	/**
	 * 通过id查询数据
	 */
	SystemmgAreaOutputDto getById(String id);
	
	/**
	 * 查询地区列表
	 * @param inputDto
	 * @return
	 */
	List<SystemmgAreaOutputDto> queryList(SystemmgAreaInputDto inputDto);
	
	/**
	 * 查询数量
	 * @param pageInputDto
	 * @return
	 */
	int findPageCount(SystemmgAreaInputDto pageInputDto);
	
	/**
	 * 删除当前及下级地区
	 * @param no
	 * @return
	 */
	int removeCurrentAndSubArea(String id, String no);
}

