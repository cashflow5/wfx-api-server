 /*
 * 版本信息
 
 * 日期 2016-03-30 10:51:22
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.commodity.dto.input.SysLogInputDto;
import com.yougou.wfx.commodity.dto.input.SysLogPageInputDto;
import com.yougou.wfx.commodity.dto.output.SysLogOutputDto;

/**
 * ISysLogBackgroundApi
 * @author luoq
 * @Date 创建时间：2016-03-30 10:51:23
 */
public interface ISysLogBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insertSysLog(SysLogInputDto sysLogDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<SysLogOutputDto> findPage(SysLogPageInputDto pageInputDto,PageModel<SysLogOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(SysLogInputDto sysLogDto);
	
	/**
	 * 通过id查询数据
	 */
	public SysLogOutputDto getSysLogById(String id);
}

