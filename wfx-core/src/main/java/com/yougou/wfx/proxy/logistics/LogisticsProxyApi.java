/*
 * 版本信息

 * 日期 2016-03-24 17:16:57

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.proxy.logistics;

import com.yougou.component.logistics.api.IMemberLogisticsApi;
import com.yougou.component.logistics.vo.MemberLogisticsVo;
import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * 物流查询API代理
 * 
 * @author wuyang
 * @Date 创建时间：2016-03-24 17:16:57
 */
public class LogisticsProxyApi{
	private final static Logger logger = LoggerFactory.getLogger(LogisticsProxyApi.class);
	
	/**
	 * 通过订单号查询发货包裹物流信息
	 * @param orderNo
	 * @return
	 * @throws Exception 
	 */
	public static MemberLogisticsVo getLogistics(String orderNo) throws Exception{
		logger.debug(String.format("开始查询订单包裹物流!orderNo:%s",orderNo));
		MemberLogisticsVo memberLogisticsVo = null;
		try{
			IMemberLogisticsApi logisticsApi = SpringContextHolder.getBean(IMemberLogisticsApi.class);
			// TODO 未完成
			//LogisticsCompany logisticsCompany = null;// 物流公司编码
			String expressOrderId = "";// 货运单号
			//memberLogisticsVo = logisticsApi.getLogistics4wfx(logisticsCompany, expressOrderId);
		}catch(Exception ex){
			logger.error("查询物流接口时发送异常", ex);
			throw ex;
		}
		logger.debug(String.format("订单包裹物流结果!orderNo:%s,结果:%s",orderNo,memberLogisticsVo.toString()));
		return memberLogisticsVo;
	}
}
