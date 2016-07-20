package com.yougou.wfx.commodity.api.front;

import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;

/**
 * 皮质接口
 * 
 * @author li.lq
 * @Date 2016年6月22日
 */
public interface ICommodityCortexFrontApi {
	/**
	 * 通过皮质名查询数据
	 */
	public CommodityCortexOutputDto getByName(String cortexName);
	
	/**
	 * 通过皮质编码查询数据
	 */
	public CommodityCortexOutputDto getByNo(String no);
}
