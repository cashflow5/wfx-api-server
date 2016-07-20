package com.yougou.wfx.visitor.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

public class VisitorCountOutputDto extends OutputDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	/**
	 * 微信来源访客数量
	 */
	private int weixinVisitorCount=0;
	
	
	/**
	 * 其他来源访客数量
	 */
	private int otherVisitorCount=0;


	public int getWeixinVisitorCount() {
		return weixinVisitorCount;
	}


	public void setWeixinVisitorCount(int weixinVisitorCount) {
		this.weixinVisitorCount = weixinVisitorCount;
	}


	public int getOtherVisitorCount() {
		return otherVisitorCount;
	}


	public void setOtherVisitorCount(int otherVisitorCount) {
		this.otherVisitorCount = otherVisitorCount;
	}
	
	
	

}
