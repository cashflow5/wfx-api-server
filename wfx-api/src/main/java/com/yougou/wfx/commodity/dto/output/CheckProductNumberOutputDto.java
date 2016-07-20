 /*
 * 版本信息
 
 * 日期 2016-03-28 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.dto.output;

import java.util.List;

import com.yougou.wfx.dto.base.OutputDto;

/**
 * SellerInfoPageInputDto
 * @author zhang.wj
 * @Date 创建时间：2016-03-28 16:58:55
 */
public class CheckProductNumberOutputDto extends OutputDto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//是否校验通过   true校验通过，false校验不通过
	private String roductViaStatus;
	
	//库存不足或者无库存的货品dto
	private List<CheckProductNumberDetailsOutputDto>  dto;
	
	public String getRoductViaStatus() {
		return roductViaStatus;
	}

	public void setRoductViaStatus(String roductViaStatus) {
		this.roductViaStatus = roductViaStatus;
	}

	public List<CheckProductNumberDetailsOutputDto> getDto() {
		return dto;
	}

	public void setDto(List<CheckProductNumberDetailsOutputDto> dto) {
		this.dto = dto;
	}

	
	
	
	
	
	
}
