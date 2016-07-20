 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front;


import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.dto.input.CommissionDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionCollectOutputDto;
import com.yougou.wfx.finance.dto.output.CommissionDetailOutputDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;

/**
 * ICommissionDetailFrontApi
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
public interface ICommissionDetailFrontApi{
    
    /**
	 * 手机客户端展示佣金明细列表，分页数据
     */
    public PageModel<CommissionDetailOutputDto> findPage(CommissionDetailPageInputDto pageInputDto,PageModel<CommissionDetailOutputDto> pageModel);
    
    /**
     * H5展示佣金明细列表，需要根据订单维度展示，所以分组查询
     * 传入参数map：分销商id：sellerId，结算状态:status 0未结算，1已结算，2异常挂起
     * 输出数据已经根据orderTime 下单时间降序排列
     */
    public PageModel<CommissionDetailOutputDto> findPageH5(Map<String,String> map,PageModel<CommissionDetailOutputDto> pageModel);
    
	/**
	 * 根据佣金明细Id，获取佣金明细详情
	 * @param id 主键
	 * @return
	 */
    public OrderDetailCommissionOutPutDto getCommissionDetailByNo(String commissionId);
	
	/**
     * 根据分销商ID，获取佣金明细中的汇总信息
     */
    public CommissionCollectOutputDto getBySellerId(String sellerId);
    
    /**
     * H5展示佣金明细商品详情，需要根据商品维度展
     * 传入参数map：分销商id：sellerId，子订单号:wfxOrderNo
     */
    List<OrderDetailCommissionOutPutDto> getCommissionDetailByH5(Map<String, String> map);
}

