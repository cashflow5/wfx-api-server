 /*
 * 版本信息
 
 * 日期 2016-03-25 16:46:46
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.api.front;

import java.util.Map;

import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleSearchDto;
import com.yougou.wfx.aftersale.dto.output.OrderRefundOutputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
/**
 * 售后接口
 * @author luoq
 * @Date 创建时间：2016-03-25 16:46:47
 */
public interface IAfterSaleFrontApi{
	
	/**
	 * 退货地址查询接口
	 * @param orderDetailId 子订单ID(必输)， supplierCode 供应商编码(必输)
	 * @return SupplierAddressOutputDto
	 */
	public SupplierAddressOutputDto getBackAddress(String orderDetailId,String supplierCode);
	
	/**
	 * 物流公司列表接口
	 * @return key： 物流公司编码-> value：物流公司名称
	 */
	public Map<String,String> getEMSList();
	
	/**
	 * 生成退货退款单接口 【金额校验】
	 * @param  子订单号、退款类型、登陆账号不能为空; 退款类型枚举类见RefundTypeEnum.java
	 * @return 
	 */
	public WFXResult<OrderRefundOutputDto> createRefundItem(OrderRefundInputDto orderRefundInputDto);
	
	/**
	 * 根据订单商品的orderDetailId查询其退货退款单详情(多次退款的情况取最近一次的退款记录，并查得该子订单的可退金额和可退数量)
	 * @param orderDetailId 子订单号（必填项）
	 * @return   ResultCode: 1 ,找到该订单商品的退款记录DTO(包括可退金额和可退数量信息); ResultCode:2 ,未找到该订单商品的退款记录，返回可退金额和可退数量信息。
	 * 						 3, 未找到该子订单.
	 */
	public WFXResult<OrderRefundOutputDto> getRefundItem(String orderDetailId );
	
	/**
	 * 修改退货退款单接口 【金额校验，状态流转要区别是否修改了退款类型】
	 * @param id不能为空、退款类型不能为空、订单主键orderID、登陆账号不能为空。    退款类型枚举类见RefundTypeEnum.java 
	 * @return 
	 */
	public WFXResult<OrderRefundOutputDto> updateRefundItem(OrderRefundInputDto orderRefundInputDto);
	
	/**
	 * 取消退货退款单接口 
	 * @param refundNo不能为空、登陆账号不能为空。    
	 * @return true 成功 ；false，代表操作失败！
	 */
	public WFXResult<Boolean> cancelRefund(OrderRefundInputDto orderRefundInputDto);
		
	/**
	 * 查询退货退款单详情接口
	 * @param refundNo 退款单号
	 */
	public OrderRefundOutputDto getDetailByRefundNo(String refundNo);
	
	/**
	 * 分页查询会员的售后退款列表
	 * @param memberId 会员id
	 * @return
	 */
	public PageModel<OrderAfterSaleDto> queryOrderAfterSalePage(String memberId,PageModel pageModel,OrderAfterSaleSearchDto search);

	/**
	 * 查询子订单-退款成功的金额
	 * @param orderDetailId
	 * @return
	 */
	Double queryRefundSuccessFeeByOrderDetailId(String orderDetailId);
}

