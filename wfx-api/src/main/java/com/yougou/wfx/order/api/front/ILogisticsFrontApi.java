 /*
 * 版本信息
 
 * 日期 2016-03-28 17:16:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.order.api.front;

import com.yougou.wfx.order.dto.output.LogisticsOutputDto;

/**
 * 物流查询api
 * @author wuyang
 * @Date 创建时间：2016-03-28 17:16:57
 */
public interface ILogisticsFrontApi {
	
	/**
	 * 查询订单物流信息
	 * @param orderNo 订单号
	 * @return
	 */
	LogisticsOutputDto getLogistics(String orderNo);
}