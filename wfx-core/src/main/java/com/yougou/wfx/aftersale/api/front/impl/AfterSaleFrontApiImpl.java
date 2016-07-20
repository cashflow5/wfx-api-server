package com.yougou.wfx.aftersale.api.front.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.wfx.aftersale.api.front.IAfterSaleFrontApi;
import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleSearchDto;
import com.yougou.wfx.aftersale.dto.output.OrderRefundOutputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;
import com.yougou.wfx.aftersale.service.IOrderRefundService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.DistributionCompanyEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.order.service.IOrderLogService;

@Service
public class AfterSaleFrontApiImpl implements IAfterSaleFrontApi{

	private Logger logger = LoggerFactory.getLogger(AfterSaleFrontApiImpl.class);
	
	@Resource
	IOrderRefundService orderRefundService;
	@Resource
	IOrderLogService logService;
	
	@Override
	@LoggerProfile(methodNote = "退货地址查询接口")
	public SupplierAddressOutputDto getBackAddress(@NotBlank String orderDetailId,
			@NotBlank String supplierCode) {
		return orderRefundService.getBackAddress(orderDetailId,supplierCode);
	}

	@Override
	@LoggerProfile(methodNote = "物流公司列表接口")
	public Map<String, String> getEMSList() {
		Map<String, String> resultMap = new HashMap<String, String>();
		for(DistributionCompanyEnum dc :DistributionCompanyEnum.values() ){
			resultMap.put(dc.getKey(),dc.getDesc());
		}
		return resultMap;
	}

	@Override
	@LoggerProfile(methodNote = "查询退货退款单接口")
	public WFXResult<OrderRefundOutputDto> getRefundItem(@NotBlank String orderDetailId ) {
		logger.info("Come into AfterSaleFrontApiImpl.getRefundItem(),param is orderDetailId="+orderDetailId );
		WFXResult<OrderRefundOutputDto> rs = orderRefundService.getRefundRecordInfo(orderDetailId);
		logger.info("Completed with dto: "+rs.getResult().toString() );
		return rs;
	}
	
	@Override
	@LoggerProfile(methodNote = "创建退货退款单接口")
	public WFXResult<OrderRefundOutputDto> createRefundItem(@NotBlank.List(value={@NotBlank(target="orderDetailId"),
			@NotBlank(target="refundType"),@NotBlank(target="buyerLoginName")}) OrderRefundInputDto orderRefundInputDto ) {
		logger.info("Come into AfterSaleFrontApiImpl.createRefundItem(),param is orderRefundInputDto="+orderRefundInputDto);
		return orderRefundService.createRefundRecord(orderRefundInputDto);
	}

	@Override
	@LoggerProfile(methodNote = "更新退货退款单接口")
	public WFXResult<OrderRefundOutputDto> updateRefundItem(
			@NotBlank.List(value={@NotBlank(target="id"),@NotBlank(target="refundType"),@NotBlank(target="orderId"),@NotBlank(target="buyerLoginName")})OrderRefundInputDto orderRefundInputDto) {
		logger.info("Come into AfterSaleFrontApiImpl.updateRefundItem(),param is orderRefundInputDto="+orderRefundInputDto);
		return orderRefundService.updateRefundRecord(orderRefundInputDto);
	}

	@Override
	@LoggerProfile(methodNote = "根据退款编号查询退货退款单接口")
	public OrderRefundOutputDto getDetailByRefundNo(@NotBlank String refundNo) {
		logger.info("Come into AfterSaleFrontApiImpl.getDetailByRefundNo(),param is refundNo="+refundNo);
		OrderRefundEntity orderRefundEntity = orderRefundService.getDetailByRefundNo(refundNo);
		logger.info("Completed with dto: "+orderRefundEntity);
		return (OrderRefundOutputDto)BeanUtil.convertBean(orderRefundEntity,OrderRefundOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "取消退货退款单")
	public WFXResult<Boolean> cancelRefund(
			@NotBlank.List(value={@NotBlank(target="refundNo"),@NotBlank(target="buyerLoginName")})OrderRefundInputDto orderRefundInputDto) {
		return orderRefundService.cancelRefund(orderRefundInputDto);
	}

	@Override
	@LoggerProfile(methodNote="分页查询订单售后列表")
	public PageModel<OrderAfterSaleDto> queryOrderAfterSalePage(@NotBlank String memberId,PageModel pageModel,OrderAfterSaleSearchDto search) {
		return orderRefundService.queryOrderAfterSalePage(memberId,pageModel,search);
	}

	@Override
	@LoggerProfile(methodNote="查询商品行在售后成功退款的总金额")
	public Double queryRefundSuccessFeeByOrderDetailId(String orderDetailId) {
		return orderRefundService.queryRefundSuccessFeeByOrderDetailId(orderDetailId);
	}
	
}
