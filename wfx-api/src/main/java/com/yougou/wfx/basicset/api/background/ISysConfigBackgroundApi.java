 /*
 * 版本信息
 
 * 日期 2016-04-09 13:01:36
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.basicset.api.background;

import com.yougou.wfx.basicset.dto.input.SysConfigInputDto;
import com.yougou.wfx.basicset.dto.input.SysConfigPageInputDto;
import com.yougou.wfx.basicset.dto.output.SysConfigOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ISysConfigBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-09 13:01:37
 */
public interface ISysConfigBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SysConfigInputDto sysConfigDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<SysConfigOutputDto> findPage(SysConfigPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(SysConfigInputDto sysConfigDto);
	
	/**
	 * 通过id查询数据
	 */
	public SysConfigOutputDto getById(String id);
	/**
	 * 通过key查询value
	 */
	String getValueBykey(String key);
}

