 /*
 * 版本信息
 
 * 日期 2016-03-25 14:38:15
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.seller.dto.input;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.dto.base.PageModel;

/**
 * BankInfoInputDto
 * @author luoq
 * @Date 创建时间：2016-03-25 14:38:16
 */
public class BankInfoInputDto extends PageModel<BankInfoInputDto> {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private String id;
	/**
	 * bankNo
	 */
	private String bankNo;
	/**
	 * bankName
	 */
	private String bankName;
	/**
	 * bankLogoUrl
	 */
	private String bankLogoUrl;
	/**
	 * sortNo
	 */
	private Integer sortNo;

	public BankInfoInputDto(){
	}

	public BankInfoInputDto(
		String id
	){
		this.id = id;
	}

	public void setId(String value) {
		this.id = value;
	}
	
	public String getId() {
		return this.id == null ? null : this.id.trim();
	}
	public void setBankNo(String value) {
		this.bankNo = value;
	}
	
	public String getBankNo() {
		return this.bankNo == null ? null : this.bankNo.trim();
	}
	public void setBankName(String value) {
		this.bankName = value;
	}
	
	public String getBankName() {
		return this.bankName == null ? null : this.bankName.trim();
	}
	public void setBankLogoUrl(String value) {
		this.bankLogoUrl = value;
	}
	
	public String getBankLogoUrl() {
		return this.bankLogoUrl == null ? null : this.bankLogoUrl.trim();
	}
	public void setSortNo(Integer value) {
		value = value == null ? 0 : value;
		this.sortNo = value;
	}
	
	public Integer getSortNo() {
		return this.sortNo == null ? 0 : this.sortNo;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

