package com.yougou.wfx.finance.dto.output;

import java.io.Serializable;

import com.yougou.wfx.dto.base.PageModel;

public class FinSellerAccountWithdrawVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 金额
	 */
	private Double amount;
	/**
	 * 分页数据
	 */
	private PageModel<FinSellerAccountWithdrawOutputDto> pageModel;
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public PageModel<FinSellerAccountWithdrawOutputDto> getPageModel() {
		return pageModel;
	}
	public void setPageModel(PageModel<FinSellerAccountWithdrawOutputDto> pageModel) {
		this.pageModel = pageModel;
	}
	
	@Override
	public String toString() {
		return "FinSellerAccountWithdrawVo [amount=" + amount + ", pageModel="
				+ pageModel + "]";
	}
	
}
