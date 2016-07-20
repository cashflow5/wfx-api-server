package com.yougou.wfx.order.dto.output;

import com.yougou.wfx.dto.base.OutputDto;

public class StyleOutPutDto extends OutputDto{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 商品id
	 */
	private String commId;
	/**
	 * 商品图片url
	 */
	private String picUrl;
	/**
	 * 商品名称
	 */
	private String prod_name;
	/**
	 * 商品颜色、尺码
	 */
	private String prodSpec;
	/**
	 * 发货数量
	 */
	private Integer num;

	public String getCommId() {
		return commId;
	}

	public void setCommId(String commId) {
		this.commId = commId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getProd_name() {
		return prod_name;
	}

	public void setProd_name(String prod_name) {
		this.prod_name = prod_name;
	}

	public String getProdSpec() {
		return prodSpec;
	}

	public void setProdSpec(String prodSpec) {
		this.prodSpec = prodSpec;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
}
