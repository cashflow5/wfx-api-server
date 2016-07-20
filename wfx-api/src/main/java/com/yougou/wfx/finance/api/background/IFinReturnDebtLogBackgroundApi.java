/*
 * 版本信息

 * 日期 2016-04-05 19:11:06

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.FinReturnDebtLogInputDto;
import com.yougou.wfx.finance.dto.input.FinReturnDebtLogPageInputDto;
import com.yougou.wfx.finance.dto.output.FinReturnDebtLogOutputDto;

/**
 * IFinReturnDebtLogBackgroundApi
 * 
 * @author he.xx
 * @Date 创建时间：2016-04-05 19:11:08
 */
public interface IFinReturnDebtLogBackgroundApi {
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);

	/**
	 * 保存单条记录
	 */
	public String insert(FinReturnDebtLogInputDto finReturnDebtLogDto);

	/**
	 * 获取分页数据
	 */
	public PageModel<FinReturnDebtLogOutputDto> findPage(FinReturnDebtLogPageInputDto pageInputDto, PageModel pageModel);

	/**
	 * 更新记录
	 */
	public int update(FinReturnDebtLogInputDto finReturnDebtLogDto);

	/**
	 * 通过id查询数据
	 */
	public FinReturnDebtLogOutputDto getById(String id);

	/**
	 * 查询日志列表
	 * @param paramMap
	 * @return
	 */
	public List<FinReturnDebtLogOutputDto> queryDataList(FinReturnDebtLogOutputDto finReturnDebtLogOutputDto);
}
