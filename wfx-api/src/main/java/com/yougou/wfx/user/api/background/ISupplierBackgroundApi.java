 /*
 * 版本信息
 
 * 日期 2016-04-08 13:32:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.user.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.user.dto.input.SupplierInputDto;
import com.yougou.wfx.user.dto.input.SupplierPageInputDto;
import com.yougou.wfx.user.dto.output.SupplierOutputDto;

/**
 * ISupplierBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-08 13:32:03
 */
public interface ISupplierBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SupplierInputDto supplierDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<SupplierOutputDto> findPage(SupplierPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(SupplierInputDto supplierDto);
	
	/**
	 * 通过id查询数据
	 */
	public SupplierOutputDto getById(String id);
}

