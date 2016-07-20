 /*
 * 版本信息
 
 * 日期 2016-05-31 15:04:39
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.front;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.order.dto.input.OrderRemarkInputDto;
import com.yougou.wfx.order.dto.input.OrderRemarkPageInputDto;
import com.yougou.wfx.order.dto.output.OrderRemarkOutputDto;

/**
 * IOrderRemarkFrontApi
 * @author luoq
 * @Date 创建时间：2016-05-31 15:04:40
 */
public interface IOrderRemarkFrontApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderRemarkInputDto orderRemarkDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<OrderRemarkOutputDto> findPage(OrderRemarkPageInputDto pageInputDto,PageModel<OrderRemarkOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(OrderRemarkInputDto orderRemarkDto);
	
	/**
	 * 通过id查询数据
	 */
	public OrderRemarkOutputDto getById(String id);
}

