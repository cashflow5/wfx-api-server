 /*
 * 版本信息
 
 * 日期 2016-06-21 17:57:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.front.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.api.front.ICommodityCortexFrontApi;
import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;
import com.yougou.wfx.commodity.service.ICommodityCortexService;

/**
 * ICommodityCortexBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
@Service
public class CommodityCortexFrontApiImpl implements ICommodityCortexFrontApi{
	
	@Resource
	ICommodityCortexService commodityCortexService;

	@Override
	public CommodityCortexOutputDto getByName(String cortexName) {
		return commodityCortexService.getByName(cortexName);
	}

	@Override
	public CommodityCortexOutputDto getByNo(String no) {
		return commodityCortexService.getByNo(no);
	}
}
