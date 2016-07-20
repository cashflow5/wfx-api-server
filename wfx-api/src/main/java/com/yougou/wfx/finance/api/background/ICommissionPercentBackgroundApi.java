 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.CommissionPercentInputDto;
import com.yougou.wfx.finance.dto.input.CommissionPercentLogPageInputDto;
import com.yougou.wfx.finance.dto.input.CommissionPercentPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionPercentLogOutputDto;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;

/**
 * ICommissionPercentBackgroundApi
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
public interface ICommissionPercentBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id,String updateUser);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommissionPercentInputDto commissionPercentDto);
	
	/**
	 *佣金比例 获取分页数据
	 */
	public PageModel<CommissionPercentOutputDto> findPage(CommissionPercentPageInputDto pageInputDto,PageModel<CommissionPercentOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(CommissionPercentInputDto commissionPercentDto);
	
	/**
	 * 通过id查询数据
	 */
	public CommissionPercentOutputDto getById(String id);
	
	/**
     * 佣金比例日志，获取分页数据
     */
    public PageModel<CommissionPercentLogOutputDto> findPageLog(CommissionPercentLogPageInputDto pageInputDto,PageModel<CommissionPercentLogOutputDto> pageModel);

    /**
     * 品牌分类和单品佣金设置-编辑和保存
     */
    String saveCommission(CommissionPercentInputDto commissionPercent);
    /**
     * 通过品牌编码，品牌分类id，商品id获取佣金比例信息
     */
    CommissionPercentOutputDto getCommissionByCondition(String brandNo, String baseCatId, String commodityid);
}

