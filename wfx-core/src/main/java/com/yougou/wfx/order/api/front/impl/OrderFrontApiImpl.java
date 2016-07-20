 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.front.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.pay.api.IPayApi;
import com.yougou.pay.constant.BankNoConstant;
import com.yougou.pay.vo.PayCallBackStateEnum;
import com.yougou.pay.vo.PayCallBackVo;
import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.model.OrderApplyEntity;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;
import com.yougou.wfx.aftersale.service.IOrderRefundService;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.api.background.ICommodityProductBackgroundApi;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.ICommodityPicsService;
import com.yougou.wfx.commodity.service.ICommodityProductService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.OrderLogOptBelongEnum;
import com.yougou.wfx.enums.OrderLogOptEnum;
import com.yougou.wfx.enums.OrderLogOptResultEnum;
import com.yougou.wfx.enums.OrderStatusEnum;
import com.yougou.wfx.enums.RefundStatusEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.enums.SellerLevelEnum;
import com.yougou.wfx.finance.dto.input.FinAlreadyIncomeInputDto;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.dto.output.MemberAddressOutputDto;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.member.model.MemberAddressEntity;
import com.yougou.wfx.member.service.IMemberAccountService;
import com.yougou.wfx.member.service.IMemberAddressService;
import com.yougou.wfx.order.api.front.IOrderFrontApi;
import com.yougou.wfx.order.biz.IOrderBiz;
import com.yougou.wfx.order.dto.input.OrderLogInputDto;
import com.yougou.wfx.order.dto.input.OrderSearchDto;
import com.yougou.wfx.order.dto.input.SellerOrderSearchDto;
import com.yougou.wfx.order.dto.output.AfterPayedCallBackDto;
import com.yougou.wfx.order.dto.output.BatchOrderResultDto;
import com.yougou.wfx.order.dto.output.BuyLimitOutPutDto;
import com.yougou.wfx.order.dto.output.CommissionOrderOutputDto;
import com.yougou.wfx.order.dto.output.ConsignInfosOutPutDto;
import com.yougou.wfx.order.dto.output.OrderCountOutputDto;
import com.yougou.wfx.order.dto.output.OrderCreateDto;
import com.yougou.wfx.order.dto.output.OrderDetailCommDto;
import com.yougou.wfx.order.dto.output.OrderDetailCreateDto;
import com.yougou.wfx.order.dto.output.OrderDetailDto;
import com.yougou.wfx.order.dto.output.OrderDetailProfile;
import com.yougou.wfx.order.dto.output.OrderInfoDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;
import com.yougou.wfx.order.dto.output.OrderResultDto;
import com.yougou.wfx.order.model.CommissionOrderEntity;
import com.yougou.wfx.order.model.OrderCountEntity;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.model.OrderEntity;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderDetailService;
import com.yougou.wfx.order.service.IOrderLogService;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.proxy.messenger.SmsProxyApi;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.MathUtil;
import com.yougou.wfx.utils.PicsUtil;

/**
 * IOrderFrontApi实现
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
@Service
public class OrderFrontApiImpl implements IOrderFrontApi{
	private Logger logger = LoggerFactory.getLogger(OrderFrontApiImpl.class);
	@Resource
	IOrderService orderService;
	@Resource
	IOrderDetailService orderDetailService;
	@Resource
	IOrderLogService orderLogService;
	@Resource
	ISysConfigService sysConfigService;
	@Resource
	IMemberAddressService memberAddressService;
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource
	ICommodityProductService commodityProductService;
	@Resource
	ICommodityProductBackgroundApi commodityProductBackgroundApi;
	@Resource
	ISellerInfoService sellerInfoService;
	@Resource
	@Qualifier(value="jmsTemplate")
	private AmqpTemplate amqpTemplate;
	@Resource
	private IMemberAccountService memberAccountService;
	@Resource
	private IPayApi payApi;
	@Resource
	private IOrderRefundService orderRefundService;
	@Resource
	private IOrderBiz orderBiz;
	@Resource
	private ICommodityPicsService commodityPicsService;
	@Resource
	private ISellerInfoService sellerInfoServiceImpl;
	
	@Override
	@LoggerProfile(methodNote="查询支付方式列表")
	public List<String> getPayInfo() {
		List<String> payList = null;
		String payStr = sysConfigService.getValueBykey(Constant.WFX_PAY_TYPE);
		if(StringUtils.isNoneBlank(payStr)){
			String[] payArr = payStr.split(",");
			payList = Arrays.asList(payArr);
		}
		return payList;
	}

	@Override
	@LoggerProfile(methodNote="通过会员id查询地址列表")
	public List<MemberAddressOutputDto> getMemberAddressByMemberId(
			@NotBlank String memberId, Boolean defaultFlag) {
		List<MemberAddressEntity> addressList = null;
		MemberAddressEntity memberAddress = new MemberAddressEntity();
		memberAddress.setLoginacccountId(memberId);
		if(null != defaultFlag && defaultFlag){
			memberAddress.setIsDefaultAddress(1);
		}
		addressList = memberAddressService.queryList(memberAddress);
		return (List<MemberAddressOutputDto>)BeanUtil.convertBeanList(addressList, MemberAddressOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="通过收货地址id查询收货信息")
	public MemberAddressOutputDto getMemberAddressByAddressId(@NotBlank String addressId) {
		MemberAddressEntity memberAddress = memberAddressService.getById(addressId);
		return (MemberAddressOutputDto)BeanUtil.convertBean(memberAddress, MemberAddressOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="分页查询订单信息")
	public PageModel<OrderInfoDto> queryPageOrders(@NotBlank String sellerId,
			PageModel pageModel) {
		//SellerInfoEntity sellerEntity = new SellerInfoEntity();
		//sellerEntity.setParentId(sellerId);
		List<String> sellerList = new ArrayList<String>();
		sellerList.add(sellerId);
		//List<SellerInfoEntity> entityList = sellerInfoService.queryList(sellerEntity);
		//if(null != entityList && entityList.size() > 0){
			//for(SellerInfoEntity tempEntity:entityList){
				//sellerList.add(tempEntity.getId());
			//}
		//}
		OrderEntity orderEntity = new OrderEntity();
		Map extraMap = new HashMap<String, Object>();
		extraMap.put("sellerList", sellerList);
		orderEntity.setExtraMap(extraMap);
		PageModel<OrderOutputDto> orderPage = orderService.querySellerPage(orderEntity, pageModel);
		List<OrderOutputDto> dtoList = (List<OrderOutputDto>)orderPage.getItems();
		List<OrderInfoDto> orderInfoList = (List<OrderInfoDto>)BeanUtil.convertBeanList(dtoList, OrderInfoDto.class);
		List<OrderDetailEntity> orderDetailList = null;
		if(null != orderInfoList && orderInfoList.size() > 0){
			for(OrderInfoDto orderInfo:orderInfoList){
				OrderDetailEntity detail = new OrderDetailEntity();
				detail.setOrderId(orderInfo.getId());
				orderDetailList = orderDetailService.queryList(detail);
				orderInfo.setOrderDetailList((List<OrderDetailDto>)BeanUtil.convertBeanList(orderDetailList, OrderDetailDto.class));
			}
		}
		PageModel<OrderInfoDto> infoPage = new PageModel<OrderInfoDto>(orderPage.getPage(),orderPage.getLimit(),orderPage.getTotalCount(),orderInfoList);
		return infoPage;
	}

	@Override
	@LoggerProfile(methodNote="通过主订单号id查询订单详情")
	public OrderInfoDto getOrderById(@NotBlank String orderId) {
		OrderInfoDto orderInfoDto = null;
		OrderEntity entity = orderService.getById(orderId);
		if(null != entity){
			orderInfoDto = (OrderInfoDto)BeanUtil.convertBean(entity, OrderInfoDto.class);
			OrderDetailEntity detailEntity = new OrderDetailEntity();
			detailEntity.setOrderId(orderId);
			List<OrderDetailEntity> detailList = orderDetailService.queryList(detailEntity);
			List<OrderDetailDto> detailOutList = (List<OrderDetailDto>)BeanUtil.convertBeanList(detailList, OrderDetailDto.class);
			if(null != detailList && detailList.size() > 0){
				Double commission1 = 0.0d;
				Double commission2 = 0.0d;
				Double commission3 = 0.0d;
				Double cp1,cp2,cp3;
				for(OrderDetailEntity detail:detailList){
					cp1 = detail.getCommissionLevel1()==null?0d:detail.getCommissionLevel1();
					cp2 = detail.getCommissionLevel2()==null?0d:detail.getCommissionLevel2();
					cp3 = detail.getCommissionLevel3()==null?0d:detail.getCommissionLevel3();
					commission1 += cp1;
					commission2 += cp2;
					commission3 += cp3;
				}
				orderInfoDto.setCommissionLevel1(commission1);
				orderInfoDto.setCommissionLevel2(commission2);
				orderInfoDto.setCommissionLevel3(commission3);
			}
			orderInfoDto.setPayTypeName(this.getPayTypeName(orderInfoDto.getPayType()));
			if(null != detailOutList && detailOutList.size() > 0){
				for(OrderDetailDto detail:detailOutList){
					//退货状态
					OrderDetailProfile odp = this.setOrderDetails(orderId,detail.getId());
					detail.setRefundShowStatus( odp.getRefudnShowStatus());
					
					CommodityStyleEntity style = commodityStyleService.getById(detail.getCommodityId());
					if(null != style){
						String picSmall = commodityPicsService.getUrlByCommNo(style.getNo(), Constant.COMMODITY_PICS_SMALL);
						if(StringUtils.isNotBlank(picSmall)){
							picSmall = PicsUtil.getPicUrl() + picSmall;
						}
						detail.setPicSmall(picSmall);
					}
				}
			}
			orderInfoDto.setOrderDetailList(detailOutList);
		}
		return orderInfoDto;
	}

	@Override
	@LoggerProfile(methodNote="通过商品id查询商品列表")
	public List<CommodityStyleOutputDto> getCommodity(@NotEmpty List<String> commodityList) {
		List<CommodityStyleOutputDto> styleList = new ArrayList<CommodityStyleOutputDto>();
		CommodityStyleEntity styleEntity = null;
		for(String commodityId:commodityList){
			if(StringUtils.isNotBlank(commodityId)){
				styleEntity = new CommodityStyleEntity();
				styleEntity.setId(commodityId);
				List<CommodityStyleEntity> entityLit = commodityStyleService.queryList(styleEntity);
				styleList.addAll((List<CommodityStyleOutputDto>)BeanUtil.convertBeanList(entityLit, CommodityStyleOutputDto.class));
			}
		}
		return styleList;
	}

	@Override
	@Transactional
	@LoggerProfile(methodNote="预占库存")
	public WFXResult<Boolean> preInventory(@NotBlank String commodityProductId,@NotNull Integer num) {
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		boolean preResult = commodityProductService.preInventory(commodityProductId, num);
		if(preResult){
			result.setResult(true);
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResultMsg("预占库存成功");
		}else{
			result.setResult(false);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("预占库存失败");
		}
		return result;
	}
	
	@Override
	@Transactional
	@LoggerProfile(methodNote="批量预占库存")
	public WFXResult<Boolean> batchPreInventory(@NotNull Map<String,Integer> preMap){
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		if(null != preMap && preMap.size() > 0){
			boolean preResult;
			for(String productId:preMap.keySet()){
				preResult = commodityProductService.preInventory(productId, preMap.get(productId));
				if(!preResult){
					result.setResult(false);
					result.setResultCode(ResultCodeEnum.FAILURE.getKey());
					result.setResultMsg("批量预占库存失败");
					return result;
				}
			}
		}
		result.setResult(true);
		result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		result.setResultMsg("批量预占库存成功");
		return result;
	}

	@Override
	@LoggerProfile(methodNote="创建订单")
	public OrderResultDto createOrder(@NotBlank.List(value={
			@NotBlank(target="buyerId"),@NotBlank(target="receiverName"),
			@NotBlank(target="receiverMobile"),@NotBlank(target="receiverPhone"),
			@NotBlank(target="receiverState"),@NotBlank(target="receiverCity"),
			@NotBlank(target="receiverDistrict"),@NotBlank(target="receiverAddress")}) 
			OrderCreateDto orderCreateDto) {
		List<OrderDetailCreateDto> commDtoList = orderCreateDto.getOrderDetailCreateDto();
		if(null == commDtoList || commDtoList.size() <= 0){
			logger.error("订单的商品列表为空");
			throw new RuntimeException("订单的商品列表为空");
		}
		return orderService.createOrder(orderCreateDto);
	}
	
	@Override
	@LoggerProfile(methodNote="批量创建订单")
	public BatchOrderResultDto batchCreateOrder(List<OrderCreateDto> orderCreateList){
		BatchOrderResultDto result = new BatchOrderResultDto();
		try {
			orderBiz.batchCreateOrder(orderCreateList, result);
		} catch (Exception e) {
			//创建订单失败
		}
		return result;
	}

	@Override
	public BuyLimitOutPutDto getBuyLimit() {
		BuyLimitOutPutDto buyLimit = new BuyLimitOutPutDto();
		Integer maxBuyNum = 0;
		Integer maxDayOrderNum = 0;
		String maxBuyStr = sysConfigService.getValueBykey(Constant.WFX_ORDER_SINGLE_COMM_MAX_NUM);
		String maxDayOrderStr = sysConfigService.getValueBykey(Constant.WFX_ORDER_ONEDAY_MAX_ORDER_NUM);
		if(StringUtils.isNotBlank(maxBuyStr)){
			maxBuyNum = Integer.parseInt(maxBuyStr);
		}
		if(StringUtils.isNotBlank(maxDayOrderStr)){
			maxDayOrderNum = Integer.parseInt(maxDayOrderStr);
		}
		buyLimit.setMaxBuyNum(maxBuyNum);
		buyLimit.setMaxDayOrderNum(maxDayOrderNum);
		return buyLimit;
	}
	
	@LoggerProfile(methodNote = "获取用户中心各订单状态数量接口")
	@Override
	public OrderCountOutputDto getMemberOrderCount(String memberId) {
		// TODO Auto-generated method stub
		OrderCountEntity orderCountEntity = orderService.getMemberOrderCount(memberId);
		OrderCountOutputDto orderCountOutputDto =  (OrderCountOutputDto) BeanUtil.convertBean(orderCountEntity,OrderCountOutputDto.class);
		return orderCountOutputDto;
	}

	@Override
	@LoggerProfile(methodNote="确认收货")
	public WFXResult<Boolean> conformOrder(@NotBlank String orderId) {
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		OrderEntity order = orderService.getById(orderId);
		if(null == order){
			result.setResult(false);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("不存在订单id为：["+orderId+"]的订单");
			return result;
		}
		String status = order.getStatus();
		if(null == status){
			result.setResult(false);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("订单id：["+orderId+"]的状态为NULL，不能确认收货");
			return result;
		}
		if(!status.equals(OrderStatusEnum.WAIT_DELIVER.getKey()) && 
				!status.equals(OrderStatusEnum.PART_DELIVERED.getKey()) &&
				!status.equals(OrderStatusEnum.DELIVERED.getKey())){
			result.setResult(false);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("订单id：["+orderId+"]的状态为["+status+"]，不能确认收货");
			return result;
		}
		OrderEntity orderUpdate = new OrderEntity();
		orderUpdate.setId(order.getId());
		orderUpdate.setStatus(OrderStatusEnum.TRADE_SUCCESS.getKey());
		Date date = new Date();
		orderUpdate.setConfirmTime(date);
		orderUpdate.setUpdateTime(date);
		orderService.update(orderUpdate);
		result.setResult(true);
		result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		result.setResultMsg("确认收货成功");
		
		//确认收货日志
		orderLogService.insertOrderLog(order.getWfxOrderNo(),order.getBuyerAccount(), 
				OrderLogOptEnum.OPT_RECEIVED_ORDER.getKey(),OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey(),
				OrderLogOptResultEnum.OPT_SUCCESS.getKey());
		return result;
	}

	@Override
	@LoggerProfile(methodNote="取消订单")
	public WFXResult<Boolean> cancelOrder(@NotBlank String orderId) {
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		OrderEntity order = orderService.getById(orderId);
		if(null == order){
			result.setResult(false);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("不存在订单id为：["+orderId+"]的订单");
			return result;
		}
		String status = order.getStatus();
		if(null == status || !status.equals(OrderStatusEnum.WAIT_PAY.getKey())){
			result.setResult(false);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("订单id：["+orderId+"]的状态为：["+status+"],不是WAIT_PAY，不能取消");
			return result;
		}
		try {
			//释放预占库存
			OrderDetailEntity detailEntity = new OrderDetailEntity();
			detailEntity.setOrderId(order.getId());
			List<OrderDetailEntity> detailList = orderDetailService.queryList(detailEntity);
			for(OrderDetailEntity orderDetail : detailList){
				commodityProductService.freePreInventory(orderDetail.getProdId(), orderDetail.getNum());
			}
		} catch (Exception e) {
			logger.error("订单号["+order.getWfxOrderNo()+"]释放预占库存出错。",e);
		}
		OrderEntity orderUpdate = new OrderEntity();
		orderUpdate.setId(order.getId());
		orderUpdate.setStatus(OrderStatusEnum.TRADE_CLOSED.getKey());
		orderUpdate.setUpdateTime(new Date());
		orderService.update(orderUpdate);
		result.setResult(true);
		result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		result.setResultMsg("取消订单成功");
		
		//取消订单日志
		orderLogService.insertOrderLog(order.getWfxOrderNo(),order.getBuyerAccount(), 
				OrderLogOptEnum.OPT_CANCEL_ORDER.getKey(),OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey(),
				OrderLogOptResultEnum.OPT_FAILED.getKey());
		return result;
	}

	@Override
	@LoggerProfile(methodNote="通过主订单id查询订单详情")
	public OrderOutputDto getSellerOrder(@NotBlank String orderId) {
		OrderEntity entity = orderService.getById(orderId);
		OrderOutputDto outputDto = (OrderOutputDto)BeanUtil.convertBean(entity, OrderOutputDto.class);
		return outputDto;
	}

	@Override
	@LoggerProfile(methodNote="分页查询买家的订单列表")
	public PageModel<OrderOutputDto> getOrders(@NotBlank String memberId,
			PageModel pageModel, OrderSearchDto orderSearchDto) {
		if(null == orderSearchDto){
			orderSearchDto = new OrderSearchDto();
		}
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setBuyerId(memberId);
		orderEntity.setStatus(orderSearchDto.getStatus());
		orderEntity.setWfxOrderNo(orderSearchDto.getWfxOrderNo());
		orderEntity.setReceiverMobile(orderSearchDto.getReceiverMobile());
		orderEntity.setMergeSearchFlag(orderSearchDto.getMergeSearchFlag());
		Map extraMap = new HashMap<String, Object>();
		extraMap.put("prodName", orderSearchDto.getProdName());
		extraMap.put("prodId", orderSearchDto.getProdId());
		orderEntity.setExtraMap(extraMap);
		PageModel<OrderOutputDto> orderPage = orderService.querySellerPage(orderEntity, pageModel);
		if(null == orderPage){
			return null;
		}
		List<OrderOutputDto> orderList = orderPage.getItems();
		if(null == orderList || orderList.size() <= 0){
			return orderPage;
		}
		//计算订单的退款状态
		for(OrderOutputDto order:orderList){
			OrderDetailEntity detail = new OrderDetailEntity();
			detail.setOrderId(order.getId());
			List<OrderDetailEntity> detailList = orderDetailService.queryList(detail);
			List<OrderDetailCommDto> commList = (List<OrderDetailCommDto>)BeanUtil.convertBeanList(detailList, OrderDetailCommDto.class);
			if(commList != null && commList.size() > 0){
				order.setCommList(commList);
				for(int i=0;i<commList.size();i++){
					OrderDetailCommDto comm = commList.get(i);
					OrderDetailEntity detailEntity = detailList.get(i);
					comm.setOrderDetailId(detailEntity.getId());
					comm.setWfxPrice(detailEntity.getPrice());
					CommodityStyleEntity style = commodityStyleService.getById(detailEntity.getCommodityId());
					if(null != style){
						String picSmall = commodityPicsService.getUrlByCommNo(style.getNo(), Constant.COMMODITY_PICS_SMALL);
						if(StringUtils.isNotBlank(picSmall)){
							picSmall = PicsUtil.getPicUrl() + picSmall;
						}
						comm.setPicSmall(picSmall);
					}
					OrderDetailProfile odp = this.setOrderDetails(order.getId(),comm.getOrderDetailId());
					comm.setRefundShowStatus(odp.getRefudnShowStatus());
				}
			}
		}
		return orderPage;
	}

	@Override
	@LoggerProfile(methodNote="分页查询分销商及下级订单列表")
	public PageModel<OrderOutputDto> getSellerOrders(@NotBlank String sellerId,
			@NotNull PageModel pageModel, SellerOrderSearchDto sellerOrderSearchDto) {
		//SellerInfoEntity sellerEntity = new SellerInfoEntity();
		//sellerEntity.setParentId(sellerId);
		//分销商id列表
		List<String> sellerList = new ArrayList<String>();
		sellerList.add(sellerId);
		//前端逻辑修改为，分销商仅能看到在自己店铺的订单，不能看到下级分销商的订单
		/*List<SellerInfoEntity> entityList = sellerInfoService.queryList(sellerEntity);
		if(null != entityList && entityList.size() > 0){
			for(SellerInfoEntity tempEntity:entityList){
				sellerList.add(tempEntity.getId());
			}
		}*/
		OrderEntity orderEntity = new OrderEntity();
		if(null == sellerOrderSearchDto){
			sellerOrderSearchDto = new SellerOrderSearchDto();
		}
		orderEntity.setWfxOrderNo(sellerOrderSearchDto.getWfxOrderNo());
		orderEntity.setReceiverMobile(sellerOrderSearchDto.getReceiverMobile());
		orderEntity.setStatus(sellerOrderSearchDto.getOrderStatus());
		Map extraMap = new HashMap<String, Object>();
		extraMap.put("sellerList", sellerList);
		orderEntity.setExtraMap(extraMap);
		PageModel<OrderOutputDto> orderPage = orderService.querySellerPage(orderEntity, pageModel);
		List<OrderOutputDto> orderList = orderPage.getItems();
		if(null != orderList && orderList.size() > 0){
			List<OrderDetailCommDto> commList = null;
			for(OrderOutputDto order:orderList){
				//支付方式名称
				String payType = order.getPayType();
				order.setPayTypeName(this.getPayTypeName(payType));
				commList = orderDetailService.queryCommsByOrderId(order.getId());
				Double commissionLevel1 = 0d;
				Double commissionLevel2 = 0d;
				if(null != commList && commList.size() > 0){
					order.setCommList(commList);
					Double cl1,cl2;
					for(OrderDetailCommDto comm:commList){
						cl1 = comm.getCommissionLevel1()==null?0d:comm.getCommissionLevel1();
						cl2 = comm.getCommissionLevel2()==null?0d:comm.getCommissionLevel2();
						commissionLevel1 += cl1;
						commissionLevel2 += cl2;
					}
				}
				order.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1));
				order.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2));
			}
		}
		return orderPage;
	}

	@Override
	public String insertOrderLog(OrderLogInputDto orderLogDto) {
		// TODO Auto-generated method stub
		OrderLogEntity orderLogEntity = (OrderLogEntity) BeanUtil.convertBean(orderLogDto,OrderLogEntity.class);
		return orderLogService.insert(orderLogEntity);
	}

	@Override
	@LoggerProfile(methodNote="查询订单的物流跟踪信息")
	public List<ConsignInfosOutPutDto> getConsignInfosByOrderId(@NotBlank String orderId) {
		return orderService.getConsignInfosByOrderId(orderId);
	}
	
	@Override
	@LoggerProfile(methodNote="C端支付后回调")
	public WFXResult<AfterPayedCallBackDto> callbackAfterPayed(@NotNull Map<String, String> params, int callBackType) {
		WFXResult<AfterPayedCallBackDto> result = new WFXResult<AfterPayedCallBackDto>();
		AfterPayedCallBackDto afterPayedCallBackDto = new AfterPayedCallBackDto();
		
		PayCallBackVo payCallBackVo = null;
		SimpleDateFormat format = null;
		MemberAccountEntity member = null;
		try {
			// 调用验签接口
			payCallBackVo = payApi.getCallBackPayData(params, callBackType);
		} catch (Exception e) {
			logger.error("发送付款消息给财务出现异常-返回数据：{}", payCallBackVo, e);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("验签出现异常");
			return result;
		}
		
		if (payCallBackVo.getPayCallBackState() == PayCallBackStateEnum.STATE_SUCCESS) {
			//修改订单
			String payType = null;
			String bankNo = payCallBackVo.getBankNo();
			if (StringUtils.startsWith(bankNo, BankNoConstant.WFX_H5_BANK_NO_ALIPAY)) {
				 payType = "alipay";
				 format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			} else if (StringUtils.startsWith(bankNo, BankNoConstant.WFX_H5_BANK_NO_WEIXIN)) {
				 payType = "wechatpay";
				 format = new SimpleDateFormat("yyyyMMddHHmmss");
			}
			Date tradeTime = new Date();
			OrderApplyEntity order = null;
			int updateOrderCount = 0;
			OrderEntity orderEntity = null;
			try{			
				//返回C端
				if( payCallBackVo!=null && payCallBackVo.getTradeTime()!=null ){
					tradeTime = format.parse(payCallBackVo.getTradeTime());
				}
				afterPayedCallBackDto.setOrderNo(payCallBackVo.getBizNo());
				afterPayedCallBackDto.setBankNo(payCallBackVo.getBankNo());
				afterPayedCallBackDto.setBankName(payCallBackVo.getBankName());
				afterPayedCallBackDto.setPayType(payType);
				afterPayedCallBackDto.setTradeAmount(Double.valueOf(payCallBackVo.getTradeAmount()));
				afterPayedCallBackDto.setTradeTime(tradeTime);
				afterPayedCallBackDto.setStatus(payCallBackVo.getPayCallBackState().getCode());
				result.setResult(afterPayedCallBackDto);
				order = orderService.queryOrderDetailsByNo(payCallBackVo.getBizNo());
				//判断状态是不是 waitpay
				if(order == null){
					logger.error("没有找到订单数据-返回数据：{}", payCallBackVo);
					result.setResultCode(ResultCodeEnum.FAILURE.getKey());
					result.setResultMsg("没有找到订单数据");
					return result;
				}
				afterPayedCallBackDto.setOrderId(order.getId());
				if(OrderStatusEnum.WAIT_PAY.getKey().equals(order.getStatus())){
					BigDecimal tradeAmount = new BigDecimal(payCallBackVo.getTradeAmount());
					BigDecimal hasToPayAmount = new BigDecimal(order.getPayment()+"");
					
					if(hasToPayAmount.compareTo(tradeAmount) == 0){
						orderEntity = new OrderEntity();
						orderEntity.setId(order.getId());
						orderEntity.setStatus(OrderStatusEnum.WAIT_DELIVER.getKey());
						orderEntity.setPayType(payType);
						orderEntity.setPayTime(tradeTime);
						orderEntity.setUpdateTime(new Date(System.currentTimeMillis()));
						updateOrderCount = orderService.update(orderEntity);
						
						// 记录订单日志
						orderLogService.insertOrderLog(order.getWfxOrderNo(), 
								order.getBuyerAccount(),
								OrderLogOptEnum.OPT_PAYED_ORDER.getKey(),
								OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey(),
									OrderLogOptResultEnum.OPT_SUCCESS.getKey());
						
						//恢复预占库存
						OrderDetailEntity detailEntity = new OrderDetailEntity();
						detailEntity.setOrderId(order.getId());
						List<OrderDetailEntity> detailList = orderDetailService.queryList(detailEntity);
						for(OrderDetailEntity orderDetail : detailList){
							commodityProductService.freePreInventory(orderDetail.getProdId(), orderDetail.getNum());
						}
						
						//发送支付成功的短信给用户
						try{
							String[] phones = new String[1];
							member = memberAccountService.getById(order.getBuyerId());
							if(null != member){
							
								if( StringUtils.isNotBlank(member.getRegisterCheckMobile())){
									phones[0] = member.getRegisterCheckMobile();
									String content = "亲爱的优购家人，您的订单"+order.getWfxOrderNo()+"已支付成功，我们将尽快为您发货，欢迎再次光临！";
									SmsProxyApi.sendNow(phones, content, "pay_success");
								}
							}
						}catch(Exception e){
							logger.error("订单号：["+order.getWfxOrderNo()+"]发送支付成功短信出错。",e);
						}
					}else{
						logger.error("订单待付金额{}不等于实付金额{}", hasToPayAmount, tradeAmount);
						updateOrderCount = -1;
					}
				}else{
					logger.warn("订单不是待支付状态{}", order);
				}
			}catch (ParseException e) {
				logger.error("转换日期格式出现异常:", e);
			}catch(Exception e){
				logger.error("更新订单出现异常：{}", orderEntity, e);
			}
			//发消息给财务
			//收款队列名：wfx.finance.alreadyincome.queue  
			//收款消息实体：FinAlreadyIncomeInputDto
			try {
				FinAlreadyIncomeInputDto incomeInputDto = new FinAlreadyIncomeInputDto();
				incomeInputDto.setBankName(payCallBackVo.getBankName());
				incomeInputDto.setBankNo(payCallBackVo.getBankNo());
				incomeInputDto.setBankTradeNo(payCallBackVo.getBankTradeNo());
				incomeInputDto.setCreateDate(null);//无需
				incomeInputDto.setCustomerName("");//订单表无
				incomeInputDto.setCustomerNo(order.getBuyerAccount());
				incomeInputDto.setFareAmount(order.getPostFee());
				incomeInputDto.setIncomeAccount(payCallBackVo.getSellerEmail());
				Double totalFee = order.getTotalFee()==null?0d:order.getTotalFee();
				Double postFee = order.getPostFee()==null?0d:order.getPostFee();
				//Double disCountFee = order.getDiscountFee()==null?0d:order.getDiscountFee();
				incomeInputDto.setIncomedAmount(Double.valueOf(payCallBackVo.getTradeAmount()));
				if(updateOrderCount > 0){
					incomeInputDto.setIncomeType(1);
				}else{
					incomeInputDto.setIncomeType(3);
					incomeInputDto.setIncomeTypeDesc("更新订单数据失败");
				}
				
				incomeInputDto.setOperateType(1);
				incomeInputDto.setOperator("system");
				incomeInputDto.setOrderAmount(MathUtil.roundHalfUp(totalFee + postFee));
				incomeInputDto.setOrderDate(order.getCreatedTime());
				incomeInputDto.setOrderNo(order.getWfxOrderNo());
				// 支付号 如： 交易号_1 、交易号_2 或者 主订单号_1 、主订单号_2 
				incomeInputDto.setOutTradeNo(payCallBackVo.getOutTradeNo());
				incomeInputDto.setProductAmount(order.getTotalFee());
				incomeInputDto.setStoreId(order.getShopId());
				incomeInputDto.setStoreName(order.getShopName());
				incomeInputDto.setTradeAmount(Double.valueOf(payCallBackVo.getTradeAmount()));
				incomeInputDto.setTradeDate(tradeTime);
			
				amqpTemplate.convertAndSend("wfx.finance.alreadyincome.queue",incomeInputDto);
				//amqpTemplate.convertAndSend("wfx.finance.alreadyincome.test.queue",incomeInputDto);
				result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				result.setResultMsg("支付成功");
				
				//升级为优粉 返回： shop_id、shop_code
				if(order.getBuyerId()!=null){// 如果买家会员真实存在，若不是优粉（分销商），则自动升级为优粉
					Map<String,String> cacheResult = sellerInfoService.queryIfAutoTransfer(order.getBuyerId(),order.getWfxOrderNo());
					if( cacheResult!=null && cacheResult.size()>0 && result.getResult()!=null ){
						String shopId = cacheResult.get("shopId");
						String shopCode = cacheResult.get("shopCode");
						AfterPayedCallBackDto dto = result.getResult();
						dto.setShopCode(shopCode);
						dto.setShopId(shopId);
						return result;
					}
					if( member== null ){
						member =  memberAccountService.getById(order.getBuyerId());
					}
					sellerInfoServiceImpl.autoBecomeSeller(result, member,order.getWfxOrderNo());
				}
				return result;
			} catch (AmqpException e) {
				logger.error("发送付款消息给财务出现异常:", e);
			} 
			
			
		}else{
			logger.error("验签不通过，支付不成功:",payCallBackVo);
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResultMsg("验签不通过，支付不成功");
			return result;
		}
		
		result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
		result.setResultMsg("支付完成！");
		return result;
	}
	
	@LoggerProfile(methodNote="B端获取分销商7日订单数量接口")
	@Override
	public int getSellerSevenDayOrderCount(String sellerId) {

		List<SellerInfoEntity> entityList = sellerInfoService.getAllLevelSeller(sellerId);
		//如果不存在该等级分销商，则返回null
		if(null == entityList || entityList.size() <= 0){
			return 0;
		}
		List<String> sellerList = new ArrayList<String>();
		for(SellerInfoEntity tempEntity:entityList){
			sellerList.add(tempEntity.getId());
		}
		OrderEntity orderEntity = new OrderEntity();
		Map extraMap = new HashMap<String, Object>();
		extraMap.put("commissionSellerList", sellerList);
		orderEntity.setExtraMap(extraMap);
		return orderService.getSellerSevenDayOrderCount(orderEntity);
	}
	
	@Override
	@LoggerProfile(methodNote="设置子订单退款状态")
	public OrderDetailProfile setOrderDetails(String orderId,String orderDetailId){
		OrderDetailProfile odp = new OrderDetailProfile();
		String refundShowStatus = "4";
		Double refundMoney = 0.0d;
		Integer refundNum = 0;
		Double canRefundMoney;
		Integer canRefundNum;
		odp.setRefudnShowStatus(refundShowStatus);
		OrderEntity order = orderService.getById(orderId);
		if(null == order || StringUtils.isBlank(order.getStatus())){
			return odp;
		}
		String status = order.getStatus();
		//等待付款、关易关闭
		if(OrderStatusEnum.WAIT_PAY.getKey().equals(status)){
			return odp;
		}
		//交易关闭
		if(OrderStatusEnum.TRADE_CLOSED.getKey().equals(status)){
			OrderRefundEntity refund = new OrderRefundEntity();
			refund.setOrderDetailId(orderDetailId);
			refund.setStatus(RefundStatusEnum.SUCCESS_REFUND.getKey());
			List<OrderRefundEntity> refundList = orderRefundService.queryList(refund);
			if(null != refundList && refundList.size() > 0){
				odp.setRefudnShowStatus("3");
			}
			return odp;
		}
		//boolean timeOutFlag = false;
		//交易成功
		if(OrderStatusEnum.TRADE_SUCCESS.getKey().equals(status)){
			Date confimDate = order.getConfirmTime();
			Date now = new Date();
			Integer timeOutDay = 7;
			String timeOutStr = sysConfigService.getValueBykey(Constant.WFX_ORDER_COMMISION_GENERATE_DAY);
			if(StringUtils.isNotBlank(timeOutStr)){
				try{
					timeOutDay = Integer.parseInt(timeOutStr);
				}catch(Exception e){
					logger.error("确认收货后超时时间设置错误");
				}
			}
			//确认收货的时间在7天前，说明已超时，关闭退款按钮
			if(null != confimDate && DateUtil.diffDate(now,confimDate) >= timeOutDay){
				//timeOutFlag = true;
				//如果超时，不显示退款相关信息
				return odp;
			}
		}
		OrderRefundEntity refund = new OrderRefundEntity();
		refund.setOrderDetailId(orderDetailId);
		OrderDetailEntity detail = orderDetailService.getById(orderDetailId);
		if(null == detail){
			detail = new OrderDetailEntity();
		}
		List<OrderRefundEntity> refundList = orderRefundService.queryList(refund);
		if(null != refundList && refundList.size() > 0){
			refundShowStatus = "1";
			//判断退款的状态
			for(OrderRefundEntity refundTemp:refundList){
				String refundStatus = refundTemp.getStatus();
				if(StringUtils.isNotBlank(refundStatus)){
					if(RefundStatusEnum.APPLYING.getKey().equals(refundStatus)||
							RefundStatusEnum.PENDING_DELIVERD.getKey().equals(refundStatus)||
							RefundStatusEnum.UNDER_REFUND.getKey().equals(refundStatus)){
						refundShowStatus = "2";
						break;
					}
				}
			}
		}else{//如果没有退款记录，那么前台显示的状态是“退货/退款”
			refundShowStatus = "1";
			odp.setRefudnShowStatus(refundShowStatus);
			return odp;
		}
		if("1".equals(refundShowStatus)){
			RefundSatistics rs = new RefundSatistics();
			rs.setOrderDetailId(orderDetailId);
			rs.initDefaultStatus();
			rs = orderRefundService.refundSatistics(rs);
			if(null == rs){
				rs = new RefundSatistics();
			}
			refundMoney = rs.getReturnFee()==null?0d:rs.getReturnFee();
			refundNum = rs.getReturnNum()==null?0:rs.getReturnNum();
			Double toltalFee = detail.getPayment()==null?0d:detail.getPayment();
			Integer num = detail.getNum()==null?0:detail.getNum();
			canRefundMoney = MathUtil.roundHalfUp(toltalFee - refundMoney);
			canRefundNum = num - refundNum;
			if(canRefundMoney <= 0){
				refundShowStatus = "3";
			}
		}
		odp.setRefudnShowStatus(refundShowStatus);
		return odp;
	}

	@Override
	@LoggerProfile(methodNote="查询会员当天的下单数量")
	public int getOrderDayNumberByMemberId(@NotBlank String memberId) {
		OrderApplyEntity order = new OrderApplyEntity();
		order.setBuyerId(memberId);
		Calendar cts = Calendar.getInstance();
		//获取当天的开始时间
		cts.set(Calendar.HOUR_OF_DAY, 0);
		cts.set(Calendar.MINUTE, 0);
		cts.set(Calendar.SECOND, 0);
		cts.set(Calendar.MILLISECOND, 0);
		order.setCreatedTimeStart(cts.getTime());
		//获取当天的结束时间
		Calendar cte = Calendar.getInstance();
		cte.set(Calendar.HOUR_OF_DAY, 23);
		cte.set(Calendar.MINUTE, 59);
		cte.set(Calendar.SECOND, 59);
		cte.set(Calendar.MILLISECOND, 999);
		order.setCreatedTimeEnd(cte.getTime());
		return orderService.queryApplyCount(order);
	}
	
	public String getPayTypeName(String payType){
		String payTypeName = "";
		if(StringUtils.isNotBlank(payType)){
			if("alipay".equals(payType)){
				payTypeName = "在线支付（支付宝）";
			}else if("wechatpay".equals(payType)){
				payTypeName = "在线支付（微信）";
			}
		}
		return payTypeName;
	}

//	// 旧的佣金规则逻辑
//	@Override
//	public PageModel<OrderOutputDto> getSellerOrdersByLevel(String sellerId,
//			SellerLevelEnum level, PageModel pageMode,SellerOrderSearchDto search) {
//		List<String> sellerList = new ArrayList<String>();
//		List<SellerInfoEntity> entityList = null;
//		if(null == level){
//			entityList = sellerInfoService.getAllLevelSeller(sellerId);
//		}else if(level == SellerLevelEnum.LEVEL_ONE){
//			entityList = new ArrayList<SellerInfoEntity>();
//			SellerInfoEntity seller = new SellerInfoEntity();
//			seller.setId(sellerId);
//			entityList.add(seller);
//		}else if(level == SellerLevelEnum.LEVEL_TWO){
//			entityList = sellerInfoService.getLevelTwoSeller(sellerId);
//		}else if(level == SellerLevelEnum.LEVEL_THREE){
//			entityList = sellerInfoService.getLevelThreeSeller(sellerId);
//		}
//		//如果不存在该等级分销商，则返回null
//		if(null == entityList || entityList.size() <= 0){
//			return null;
//		}
//		Map<String,Integer> levelMap = new HashMap<String,Integer>();
//		for(SellerInfoEntity tempEntity:entityList){
//			levelMap.put(tempEntity.getId(), tempEntity.getSellerLevel());
//			sellerList.add(tempEntity.getId());
//		}
//		OrderEntity orderEntity = new OrderEntity();
//		if(null == search){
//			search = new SellerOrderSearchDto();
//		}
//		orderEntity.setWfxOrderNo(search.getWfxOrderNo());
//		orderEntity.setReceiverMobile(search.getReceiverMobile());
//		orderEntity.setStatus(search.getOrderStatus());
//		orderEntity.setMergeSearchFlag( search.getMergeSearchFlag() );
//		Map extraMap = new HashMap<String, Object>();
//		extraMap.put("sellerList", sellerList);
//		orderEntity.setExtraMap(extraMap);
//		PageModel<OrderOutputDto> orderPage = orderService.querySellerPage(orderEntity, pageMode);
//		List<OrderOutputDto> orderList = orderPage.getItems();
//		if(null != orderList && orderList.size() > 0){
//			List<OrderDetailCommDto> commList = null;
//			for(OrderOutputDto order:orderList){
//				//支付方式名称
//				String payType = order.getPayType();
//				order.setPayTypeName(this.getPayTypeName(payType));
//				commList = orderDetailService.queryCommsByOrderId(order.getId());
//				Double commissionLevel1 = 0d;
//				Double commissionLevel2 = 0d;
//				Double commissionLevel3 = 0d;
//				if(null != commList && commList.size() > 0){
//					Double cl1,cl2,cl3;
//					for(OrderDetailCommDto comm:commList){
//						cl1 = comm.getCommissionLevel1()==null?0d:comm.getCommissionLevel1();
//						cl2 = comm.getCommissionLevel2()==null?0d:comm.getCommissionLevel2();
//						cl3 = comm.getCommissionLevel3()==null?0d:comm.getCommissionLevel3();
//						commissionLevel1 += cl1;
//						commissionLevel2 += cl2;
//						commissionLevel3 += cl3;
//						
//						OrderDetailProfile odp = this.setOrderDetails(order.getId(),comm.getOrderDetailId());
//						comm.setRefundShowStatus(odp.getRefudnShowStatus());
//					}
//					order.setCommList(commList);
//				}
//				order.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1));
//				order.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2));
//				order.setCommissionLevel3(MathUtil.roundHalfUp(commissionLevel3));
//				
//				//增加订单所属分销商的等级
//				Integer sellerLevel = levelMap.get(order.getSellerId());
//				if(null == sellerLevel){
//					sellerLevel = 1;
//				}
//				order.setSellerLevel(sellerLevel);
//			}
//			
//			
//		}
//		return orderPage;
//	}
	
	// 新佣金规则-佣金结算只和下单人挂钩不和店铺挂钩
	@Override
	public PageModel<OrderOutputDto> getSellerOrdersByLevel(String sellerId,
			SellerLevelEnum level, PageModel pageMode,SellerOrderSearchDto search) {
		// step1 构建订单的查询范围之一———— 佣金结算人、订单等级
		List<String> sellerList = new ArrayList<String>();
		List<SellerInfoEntity> entityList = null;
		if(null == level){
			entityList = sellerInfoService.getAllLevelSeller(sellerId);
		}else if(level == SellerLevelEnum.LEVEL_ONE){// commisionSellerId = 我
			entityList = new ArrayList<SellerInfoEntity>();
			SellerInfoEntity seller = new SellerInfoEntity();
			seller.setId(sellerId);
			entityList.add(seller);
		}else if(level == SellerLevelEnum.LEVEL_TWO){// commisionSellerId =  我的下级经销商
			entityList = sellerInfoService.getLevelTwoSeller(sellerId);
		}else if(level == SellerLevelEnum.LEVEL_THREE){//  commisionSellerId = 我的下下级经销商
			entityList = sellerInfoService.getLevelThreeSeller(sellerId);
		}
		//如果不存在该等级分销商，则返回null
		if(null == entityList || entityList.size() <= 0){
			return null;
		}
		Map<String,Integer> levelMap = new HashMap<String,Integer>();
		for(SellerInfoEntity tempEntity:entityList){
			levelMap.put(tempEntity.getId(), tempEntity.getSellerLevel());
			sellerList.add(tempEntity.getId());
		}
		
		OrderEntity orderEntity = new OrderEntity();
		if(null == search){
			search = new SellerOrderSearchDto();
		}
		orderEntity.setWfxOrderNo(search.getWfxOrderNo());
		orderEntity.setReceiverMobile(search.getReceiverMobile());
		orderEntity.setStatus(search.getOrderStatus());
		orderEntity.setMergeSearchFlag( search.getMergeSearchFlag() );
		Map extraMap = new HashMap<String, Object>();
		extraMap.put("commissionSellerList", sellerList);
		orderEntity.setExtraMap(extraMap);
		PageModel<OrderOutputDto> orderPage = orderService.querySellerPage(orderEntity, pageMode);
		List<OrderOutputDto> orderList = orderPage.getItems();
		if(null != orderList && orderList.size() > 0){
			List<OrderDetailCommDto> commList = null;
			for(OrderOutputDto order:orderList){
				//支付方式名称
				String payType = order.getPayType();
				order.setPayTypeName(this.getPayTypeName(payType));
				commList = orderDetailService.queryCommsByOrderId(order.getId());
				Double commissionLevel1 = 0d;
				Double commissionLevel2 = 0d;
				Double commissionLevel3 = 0d;
				if(null != commList && commList.size() > 0){
					Double cl1,cl2,cl3;
					for(OrderDetailCommDto comm:commList){
						cl1 = comm.getCommissionLevel1()==null?0d:comm.getCommissionLevel1();
						cl2 = comm.getCommissionLevel2()==null?0d:comm.getCommissionLevel2();
						cl3 = comm.getCommissionLevel3()==null?0d:comm.getCommissionLevel3();
						commissionLevel1 += cl1;
						commissionLevel2 += cl2;
						commissionLevel3 += cl3;
						
						OrderDetailProfile odp = this.setOrderDetails(order.getId(),comm.getOrderDetailId());
						comm.setRefundShowStatus(odp.getRefudnShowStatus());
					}
					order.setCommList(commList);
				}
				order.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1));
				order.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2));
				order.setCommissionLevel3(MathUtil.roundHalfUp(commissionLevel3));
				
				//增加订单所属分销商的等级
				Integer sellerLevel = levelMap.get(order.getSellerId());
				if(null == sellerLevel){
					sellerLevel = 1;
				}
				order.setSellerLevel(sellerLevel);
			}
			
			
		}
		return orderPage;
	}

	@Override
	public Double getAllPreCommission(String sellerId) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", sellerId);
		Double allFee = orderService.queryPreCommFee(paramMap);
		Double refundFee = orderService.queryRefundCommFee(paramMap);
		return allFee - refundFee;
	}
	
	@Override
	public PageModel<CommissionOrderOutputDto> findCommissionOrderPage(String sellerId, PageModel pageMode){
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageMode);

		int totalCount = orderService.findCommissionOrderPageCount(sellerId);
		List<CommissionOrderEntity> lstCommissionOrders = orderService.findCommissionOrderPage(sellerId, rowBounds);

		return new PageModel<CommissionOrderOutputDto>(pageMode.getPage(),pageMode.getLimit(), totalCount,(List<CommissionOrderOutputDto>) BeanUtil.convertBeanList(lstCommissionOrders,CommissionOrderOutputDto.class));
	}

	@Override
	public Double getTodayCommission(String sellerId) {
		Calendar calStart = Calendar.getInstance();
		//获取当天的开始时间
		calStart.set(Calendar.HOUR_OF_DAY, 0);
		calStart.set(Calendar.MINUTE, 0);
		calStart.set(Calendar.SECOND, 0);
		calStart.set(Calendar.MILLISECOND, 0);
		//获取当天的结束时间
		Calendar calEnd = Calendar.getInstance();
		calEnd.set(Calendar.HOUR_OF_DAY, 23);
		calEnd.set(Calendar.MINUTE, 59);
		calEnd.set(Calendar.SECOND, 59);
		calEnd.set(Calendar.MILLISECOND, 999);
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", sellerId);
		paramMap.put("dateStart",calStart.getTime());
		paramMap.put("dateEnd",calEnd.getTime());
		Double allFee = orderService.queryPreCommFee(paramMap);
		Double refundFee = orderService.queryRefundCommFee(paramMap);
		return allFee - refundFee;
	}

	@Override
	public int getSellerLevel(String orderSellerId, String loginSellerId) {
		if(StringUtils.isBlank(orderSellerId) || StringUtils.isBlank(loginSellerId)){
			return 0;
		}
		if(orderSellerId.equals(loginSellerId)){
			return 1;
		}
		List<SellerInfoEntity> sellerList = sellerInfoService.getAllLevelSeller(loginSellerId);
		if(null != sellerList && sellerList.size() > 0){
			for(SellerInfoEntity seller:sellerList){
				if(null != seller && null != seller.getId() && orderSellerId.equals(seller.getId())){
					return seller.getSellerLevel();
				}
			}
		}
		return 0;
	}

	@Override
	@LoggerProfile(methodNote="检查和查询会员首单支付成为优粉信息")
	public Map<String, String> queryIfAutoTransfer(@NotBlank String memberId,@NotBlank String wfxOrderNo) {
		return sellerInfoService.queryIfAutoTransfer(memberId,wfxOrderNo);
	}

	@Override
	public OrderOutputDto getOrderByWFXOrderNo(String wfxOrderNo) {
		OrderEntity orderEntity = orderService.getByWFXOrderNo(wfxOrderNo);
		OrderOutputDto result = (OrderOutputDto) BeanUtil.convertBean(orderEntity,OrderOutputDto.class);
		return result;
	}

	@Override
	public OrderOutputDto getCommiOrderById(String orderId) {
		OrderEntity orderEntity = orderService.getById(orderId);
		if( orderEntity!=null){
				OrderOutputDto result = (OrderOutputDto) BeanUtil.convertBean(orderEntity,OrderOutputDto.class);
				
				Map<Integer,CommissionOrderOutputDto> commissionMap = new HashMap<Integer,CommissionOrderOutputDto>();
				// 1.查和计算佣金
				List<OrderDetailCommDto> commList =  orderDetailService.queryCommsByOrderId(orderId);
				Double commissionLevel1 = 0d;
				Double commissionLevel2 = 0d;
				Double commissionLevel3 = 0d;
				if(null != commList && commList.size() > 0){
					Double cl1,cl2,cl3;
					for(OrderDetailCommDto comm:commList){
						cl1 = comm.getCommissionLevel1()==null?0d:comm.getCommissionLevel1();
						cl2 = comm.getCommissionLevel2()==null?0d:comm.getCommissionLevel2();
						cl3 = comm.getCommissionLevel3()==null?0d:comm.getCommissionLevel3();
						commissionLevel1 += cl1;
						commissionLevel2 += cl2;
						commissionLevel3 += cl3;
					}
				}
				// 2.查佣金所属人三级
				String sellerId1stLevel = "";
				String sellerId2ndLevel = "";
				String sellerId3rdLevel = "";
				if( StringUtils.isNotEmpty(result.getCommissionSellerId() )){
					sellerId1stLevel= result.getCommissionSellerId();
					CommissionOrderOutputDto comm1st = new CommissionOrderOutputDto();
					comm1st.setSellerId(sellerId1stLevel);
					comm1st.setCommission(commissionLevel1);
					comm1st.setLevel("1");
					comm1st.setOrderId(orderId);
					commissionMap.put(1, comm1st);
					
					SellerInfoEntity  seller2nd = sellerInfoService.getById(sellerId1stLevel);
					if( seller2nd!=null && !ApiConstant.ORIGINAL_SELLER_ID_DEFAULT.equals(seller2nd.getParentId()) ){
						sellerId2ndLevel = seller2nd.getParentId();
						CommissionOrderOutputDto comm2nd = new CommissionOrderOutputDto();
						comm2nd.setSellerId(sellerId2ndLevel);
						comm2nd.setCommission(commissionLevel2);
						comm2nd.setLevel("2");
						comm2nd.setOrderId(orderId);
						commissionMap.put(2, comm2nd);
						SellerInfoEntity  seller3rd = sellerInfoService.getById(sellerId2ndLevel);
						if( seller3rd!=null && !ApiConstant.ORIGINAL_SELLER_ID_DEFAULT.equals(seller3rd.getParentId()) ){
							sellerId3rdLevel = seller3rd.getParentId();
							CommissionOrderOutputDto comm3rd = new CommissionOrderOutputDto();
							comm3rd.setSellerId(sellerId3rdLevel);
							comm3rd.setCommission(commissionLevel3);
							comm3rd.setLevel("3");
							comm3rd.setOrderId(orderId);
							commissionMap.put(3, comm3rd);
						}
					}
				}
				result.setCommissionMap(commissionMap);
				return result;
		}
		return null;
	}

	@Override
	public Double getRealCommission(String wfxOrderDetailNo, String level) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("level", level);
		paramMap.put("wfxOrderDetailNo", wfxOrderDetailNo);
		return orderDetailService.getRealCommission(paramMap);
	}
	
}
