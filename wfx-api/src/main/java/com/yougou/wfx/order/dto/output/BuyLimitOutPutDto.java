package com.yougou.wfx.order.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * 微分销平台购买数量及次数等限制
 * 通过配置项进行配置
 * @author wang.zf
 *
 */
public class BuyLimitOutPutDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 单个商品购买数量限制
	 */
	private Integer maxBuyNum;
	
	/**
	 * 用户每天下单数量限制
	 */
	private Integer maxDayOrderNum;

	public Integer getMaxBuyNum() {
		return maxBuyNum;
	}

	public void setMaxBuyNum(Integer maxBuyNum) {
		this.maxBuyNum = maxBuyNum;
	}

	public Integer getMaxDayOrderNum() {
		return maxDayOrderNum;
	}

	public void setMaxDayOrderNum(Integer maxDayOrderNum) {
		this.maxDayOrderNum = maxDayOrderNum;
	}
}
