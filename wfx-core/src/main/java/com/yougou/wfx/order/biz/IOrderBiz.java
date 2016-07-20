 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.biz;

import java.util.List;

import com.yougou.wfx.order.dto.output.BatchOrderResultDto;
import com.yougou.wfx.order.dto.output.OrderCreateDto;


/**
 * IOrderService接口
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public interface IOrderBiz {
	

	void batchCreateOrder(List<OrderCreateDto> orderCreateList,BatchOrderResultDto result);
}