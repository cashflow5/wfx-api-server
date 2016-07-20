 /*
 * 版本信息
 
 * 日期 2016-03-30 16:50:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.api.background;

import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.aftersale.dto.input.OrderRefundPageInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderRefundOutputDto;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;

/**
 * IOrderRefundBackgroundApi
 * @author luoq
 * @Date 创建时间：2016-03-30 16:50:25
 */
public interface IOrderRefundBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(OrderRefundInputDto orderRefundDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<OrderRefundOutputDto> findPage(OrderRefundPageInputDto pageInputDto,PageModel<OrderRefundOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(OrderRefundInputDto orderRefundDto);
	
	OrderRefundOutputDto getDetailByRefundNo(String refundNo);/**
	 * 生成退货退款单接口
	 * @param orderId 主订单ID（必填项），proId 商品ID（必填项）; 退款类型枚举类见RefundTypeEnum.java
	 * @return OrderRefundOutputDto 退款对象；若返回为null，代表操作失败！
	 */
	public WFXResult<OrderRefundOutputDto> createRefundItem(OrderRefundInputDto orderRefundInputDto);
	
	/**
	 *  拿到退款表的记录一条
	 */
	OrderRefundOutputDto getById(String id);
	
	public RefundSatistics refundSatistics(RefundSatistics refundSatistics);
	
	/**
	 * 更新退货退款单状态的接口 
	 * @param 退款编号不能为空、退款类型不能为空、实际退款时间不能为空。    
	 * @return true 成功 ；false，代表操作失败！
	 */
	public WFXResult<Boolean> updateStatusOfRefund(OrderRefundInputDto orderRefundInputDto);


	/**
	 * 拒绝退款之后N天超时处理(N=7)
	 * @return
	 */
	public String timeOutCloseRefund();
	
	/**
	 * 查询退货地址
	 * @param orderDetailId
	 * @param supplierCode
	 * @return
	 */
	public String getRefundAddredd(String orderDetailId,String supplierCode);
	/**
	 * 审核退款-后台售后详情页面
	 * @param operator
	 * @param manualRefundFlag 3拒绝退款 2确认收货  1 同意退款
	 * @param denyReason
	 * @param outputDto 
	 * @return
	 */
	String auditRefund(String operator, int manualRefundFlag, String denyReason, OrderRefundOutputDto outputDto);
}

