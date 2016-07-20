 /*
 * 版本信息
 
 * 日期 2016-04-07 10:33:52
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.background;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.order.dto.input.OrderLogInputDto;
import com.yougou.wfx.order.dto.input.OrderLogPageInputDto;
import com.yougou.wfx.order.dto.output.OrderLogOutputDto;

/**
 * IOrderLogBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-07 10:33:53
 */
public interface IOrderLogBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderLogInputDto orderLogDto);
	
	/**
	 * 分页获取订单/退款单日志
	 * @param pageInputDto{订单日志：传入订单号,logType = 1 ;退款单日志：传入退款单号：logType = 2}
	 * @param pageModel
	 * @return PageModel<OrderLogOutputDto>
	 */
	public PageModel<OrderLogOutputDto> findPage(OrderLogPageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 不分页获取订单/退款单所有日志
	 * @param pageInputDto{订单日志：传入订单号,logType = 1 ;退款单日志：传入退款单号：logType = 2}
	 * @return List<OrderLogOutputDto> 
	 */
	public List<OrderLogOutputDto> queryList(OrderLogPageInputDto pageInputDto);
	
	/**
	 * 更新记录
	 */
	public int update(OrderLogInputDto orderLogDto);
	
	/**
	 * 通过id查询数据
	 */
	public OrderLogOutputDto getById(String id);
}

