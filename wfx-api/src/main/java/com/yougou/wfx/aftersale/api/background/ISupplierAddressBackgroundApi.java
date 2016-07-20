 /*
 * 版本信息
 
 * 日期 2016-04-26 09:34:42
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.api.background;

import com.yougou.wfx.aftersale.dto.input.SupplierAddressInputDto;
import com.yougou.wfx.aftersale.dto.input.SupplierAddressPageInputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ISupplierAddressBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-26 09:34:43
 */
public interface ISupplierAddressBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(SupplierAddressInputDto supplierAddressDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<SupplierAddressOutputDto> findPage(SupplierAddressPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(SupplierAddressInputDto supplierAddressDto);
	
	/**
	 * 通过id查询数据
	 */
	public SupplierAddressOutputDto getById(String id);
}

