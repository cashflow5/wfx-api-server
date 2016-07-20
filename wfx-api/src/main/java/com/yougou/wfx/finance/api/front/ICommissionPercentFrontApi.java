 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front;

import java.util.List;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.CommissionPercentPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;

/**
 * ICommissionPercentFrontApi
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
public interface ICommissionPercentFrontApi{
	
	/**
	 * 获取分页数据
	 */
	public PageModel<CommissionPercentOutputDto> findPage(CommissionPercentPageInputDto pageInputDto,PageModel<CommissionPercentOutputDto> pageModel);
	
	/**
     * 通过分类id获取佣金比例信息
     */
    public List<CommissionPercentOutputDto> getByBaseCatId(List<String> idList);
    /**
     * 通过品牌编码，品牌分类id，商品id获取佣金比例信息
     */
    CommissionPercentOutputDto getCommissionByCondition(String brandNo, String baseCatId, String commodityid);
}

