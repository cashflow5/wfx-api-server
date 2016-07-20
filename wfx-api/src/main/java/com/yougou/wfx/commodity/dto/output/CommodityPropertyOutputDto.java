 /*
 * 版本信息
 
 * 日期 2016-03-28 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.dto.output;

import com.yougou.wfx.dto.base.OutputDto;


/**
 * SellerInfoPageInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-03-28 16:58:55
 */
public class CommodityPropertyOutputDto extends OutputDto{

	private static final long serialVersionUID = 1L;
	
	private String propItemNo;
	private String propItemName;
	private String propValueNo;
	private String propValue;
	
	public String getPropItemNo() {
		return propItemNo;
	}
	public void setPropItemNo(String propItemNo) {
		this.propItemNo = propItemNo;
	}
	public String getPropItemName() {
		return propItemName;
	}
	public void setPropItemName(String propItemName) {
		this.propItemName = propItemName;
	}
	public String getPropValueNo() {
		return propValueNo;
	}
	public void setPropValueNo(String propValueNo) {
		this.propValueNo = propValueNo;
	}
	public String getPropValue() {
		return propValue;
	}
	public void setPropValue(String propValue) {
		this.propValue = propValue;
	}
}
