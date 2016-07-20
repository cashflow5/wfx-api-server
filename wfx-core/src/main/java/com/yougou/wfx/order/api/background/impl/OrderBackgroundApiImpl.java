 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.background.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.component.logistics.api.IMemberLogisticsApi;
import com.yougou.component.logistics.vo.LogisticsData;
import com.yougou.component.logistics.vo.MemberLogisticsVo;
import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.dto.input.OrderApplyInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderApplyOutputDto;
import com.yougou.wfx.aftersale.model.OrderApplyEntity;
import com.yougou.wfx.aftersale.service.IOrderRefundService;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.model.CommissionDetailEntity;
import com.yougou.wfx.finance.service.ICommissionDetailService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.order.api.background.IOrderBackgroundApi;
import com.yougou.wfx.order.dto.input.OrderInputDto;
import com.yougou.wfx.order.dto.input.OrderPageInputDto;
import com.yougou.wfx.order.dto.output.OrderConsignCommodityOutputDto;
import com.yougou.wfx.order.dto.output.OrderConsignOutputDto;
import com.yougou.wfx.order.dto.output.OrderDetailDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;
import com.yougou.wfx.order.dto.output.WfxLogisticsData;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.model.OrderEntity;
import com.yougou.wfx.order.service.IOrderDetailService;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.proxy.commodity.CommodityProxyApi;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.utils.MathUtil;

/**
 * IOrderBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
@Service
public class OrderBackgroundApiImpl implements IOrderBackgroundApi{
	
	private Logger logger = LoggerFactory.getLogger(OrderBackgroundApiImpl.class);
	
	@Resource
	IOrderService orderService;
	@Resource
	IMemberLogisticsApi logisticsApi;
	@Resource
	ISysConfigService sysConfigService;
	@Resource
	IOrderDetailService orderDetailService;
	@Resource
	IOrderRefundService orderRefundService;
	@Resource
	ICommissionDetailService commissionDetailService;
	@Resource
	ISellerInfoService sellerInfoService;
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource
	ICommodityCatb2cService commodityCatb2cService;
	@Resource
	IShopService shopService;
	
	@Override
	@LoggerProfile(methodNote="通过id删除订单主表")
	public int removeById(String id) {
		return orderService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="插入订单主表")
	public String insert(OrderInputDto orderDto) {
		return orderService.insert(this.dtoToEntity(orderDto));
	}

	@Override
	@LoggerProfile(methodNote="分页查询订单")
	public PageModel<OrderOutputDto> findPage(OrderPageInputDto pageInputDto,PageModel pageModel) {
		OrderEntity orderEntity = (OrderEntity) BeanUtil.convertBean(pageInputDto,OrderEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = orderService.findPageCount(orderEntity);
		List<OrderEntity> lstOrders = orderService.findPage(orderEntity, rowBounds);

		return new PageModel(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<OrderOutputDto>) BeanUtil.convertBeanList(lstOrders,OrderOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="更新订单主表")
	public int update(OrderInputDto orderDto) {
		return orderService.update(this.dtoToEntity(orderDto));
	}

	@Override
	@LoggerProfile(methodNote="通过id查询订单主表")
	public OrderOutputDto getById(String id) {
		OrderEntity orderEntity = orderService.getById(id);
		return this.entityToDto(orderEntity);
	}
	
	private OrderEntity dtoToEntity(Object obj){
		return (OrderEntity) BeanUtil.convertBean(obj,OrderEntity.class);
	}
	
	private OrderOutputDto entityToDto(Object obj){
		return (OrderOutputDto) BeanUtil.convertBean(obj,OrderOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="分页查询售后退款列表")
	public PageModel<OrderApplyOutputDto> queryApplyList(OrderApplyInputDto inputDto, PageModel pageModel) {
		OrderApplyEntity orderApplyEntity = (OrderApplyEntity)BeanUtil.convertBean(inputDto, OrderApplyEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = orderService.queryApplyCount(orderApplyEntity);
		List<OrderApplyEntity> entityList = orderService.queryApplyList(orderApplyEntity, rowBounds);
		return new PageModel(pageModel.getPage(),pageModel.getLimit(),totalCount,(List<OrderApplyOutputDto>)BeanUtil.convertBeanList(entityList, OrderApplyOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="查询售后退款单数量")
	public int queryApplyCount(OrderApplyInputDto inputDto) {
		OrderApplyEntity orderApplyEntity = (OrderApplyEntity)BeanUtil.convertBean(inputDto, OrderApplyEntity.class);
		return orderService.queryApplyCount(orderApplyEntity);
	}

	@Override
	@LoggerProfile(methodNote="通过订单id查询订单详情")
	public OrderApplyOutputDto queryOrderDetails(String id) {
		
		OrderApplyEntity orderApplyEntity = orderService.queryOrderDetails(id);
		
		OrderApplyOutputDto  orderApplyOutputDto=(OrderApplyOutputDto) BeanUtil.convertBean(orderApplyEntity,OrderApplyOutputDto.class);
		//订单详情
		List<OrderDetailDto>  orderDetailDto=(List<OrderDetailDto>) BeanUtil.convertBeanList(orderApplyEntity.getOrderDetailDto(),OrderDetailDto.class);
		
		orderApplyOutputDto.setOrderDetail(orderDetailDto);
		
		/*//物流信息
		List<OrderConsignOutputDto>   orderConsignOutPutDto=(List<OrderConsignOutputDto>) BeanUtil.convertBeanList(orderApplyEntity.getOrderConsign()   ,OrderConsignOutputDto.class);
		//查询物流信息
		if(orderConsignOutPutDto!=null && orderConsignOutPutDto.size()>0){
			for (int i = 0; i < orderConsignOutPutDto.size(); i++) {
				OrderConsignOutputDto  dto=orderConsignOutPutDto.get(i);
				String expressOrderId = dto.getExpressNo();// 货运单号
				MemberLogisticsVo memberLogisticsVo =CommodityProxyApi.getMemberLogistics(expressOrderId, dto.getExpressCode());
				List<WfxLogisticsData>   wfxLogisticsDataList=new ArrayList<WfxLogisticsData>();
				if(memberLogisticsVo!=null){
					List<LogisticsData>  logisticsDateList=memberLogisticsVo.getData();
					if(logisticsDateList!=null && logisticsDateList.size()>0){
						for (LogisticsData logisticsData : logisticsDateList) {
							WfxLogisticsData  wfxLogisticsData=new WfxLogisticsData();
							wfxLogisticsData.setTime(logisticsData.getTime());
							wfxLogisticsData.setContext(logisticsData.getContext());
							wfxLogisticsDataList.add(wfxLogisticsData);
						 }
					}
				}
				dto.setLogisticsData(wfxLogisticsDataList);
			}
		}
		//包裹商品信息
		List<OrderConsignEntity>  orderConsignEntity=orderApplyEntity.getOrderConsign();
		if(orderConsignEntity!=null && orderConsignEntity.size()>0){
			for (int i = 0; i < orderConsignEntity.size(); i++) {
				OrderConsignEntity orderConsign=orderConsignEntity.get(i);
				List<OrderConsignCommodityOutputDto>   orderConsignCommodityDto=(List<OrderConsignCommodityOutputDto>) BeanUtil.convertBeanList( orderConsign.getOrderConsignCommodity() ,OrderConsignCommodityOutputDto.class);
				OrderConsignOutputDto   consignOutput=orderConsignOutPutDto.get(i);
				consignOutput.setOrderConsigncommodity(orderConsignCommodityDto);
			}
		}
		orderApplyOutputDto.setOrderConsign(orderConsignOutPutDto);*/
		
		return orderApplyOutputDto;
		
	}
	
	@Override
	@LoggerProfile(methodNote="确认收货N天后自动生成佣金数据job")
	public void generateCommission() {
		logger.info("执行[确认收货N天后自动生成佣金数据]调度开始");
		//获取满足条件的订单
		OrderEntity orderEntity = new OrderEntity();
		//获取自动生成佣金数据的时间
		Integer dayNum = 7;
		String dayStr = sysConfigService.getValueBykey(Constant.WFX_ORDER_COMMISION_GENERATE_DAY);
		if(StringUtils.isNotBlank(dayStr)){
			try {
				dayNum = Integer.parseInt(dayStr);
			} catch (Exception e) {
				logger.error("确认收货后自动生成佣金的时间配置错误");
			}
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -dayNum);
		logger.info("时间：" + cal.get(Calendar.YEAR) + cal.get(Calendar.MONTH) + cal.get(Calendar.DAY_OF_MONTH));
		orderEntity.setConfirmTime(cal.getTime());
		List<OrderEntity> orderList = orderService.queryUnCommissionOrders(orderEntity);
		if(null != orderList && orderList.size() > 0){
			OrderEntity updateOrder = null;
			for(OrderEntity tempOrder:orderList){
				try{
					updateOrder = new OrderEntity();
					updateOrder.setId(tempOrder.getId());
					//查询子订单
					OrderDetailEntity detailEntity = new OrderDetailEntity();
					detailEntity.setOrderId(tempOrder.getId());
					List<OrderDetailEntity> detailList = orderDetailService.queryList(detailEntity);
					if(null == detailList || detailList.size() <= 0){
						//更新生成佣金数据的状态
						updateOrder.setCommissionStatus(3);
						orderService.update(updateOrder);
						continue;
					}
					for(OrderDetailEntity detail:detailList){
						this.insertCommisionDetail(tempOrder, detail);
						//更新生成佣金数据的状态
						updateOrder.setCommissionStatus(3);
						orderService.update(updateOrder);
					}
				}catch(Exception e){
					logger.error("生成订单：["+tempOrder.getId()+"]的佣金出现异常，",e);
					//更新生成佣金数据的状态
					updateOrder.setCommissionStatus(2);
					orderService.update(updateOrder);
				}
			}
		}
		logger.info("执行[确认收货N天后自动生成佣金数据]调度结束");
	}
	
	/**
	 * 生成订单的佣金数据
	 * @param order
	 * @param detail
	 */
	public void insertCommisionDetail(OrderEntity order,OrderDetailEntity detail){
		RefundSatistics rs = new RefundSatistics();
		rs.setOrderDetailId(detail.getId());
		rs.initSuccessStatus();
		RefundSatistics refundSatistics = orderRefundService.refundSatistics(rs);
		Integer returnNum = 0;
		Double returnFee = 0.0;
		if(null != refundSatistics){
			returnNum = refundSatistics.getReturnNum()==null?0:refundSatistics.getReturnNum();
			returnFee = refundSatistics.getReturnFee()==null?0:refundSatistics.getReturnFee();
		}
		Integer num = detail.getNum()==null?0:detail.getNum();
		Double price = detail.getPrice()==null?0.0:detail.getPrice();
		Double totalFee = MathUtil.roundHalfUp(price*num);
		Integer realNum = num - returnNum;
		Double realFee =  MathUtil.roundHalfUp(totalFee - returnFee) ;
		if(num > 0 && realFee > 0 ){
			//生成佣金数据
			CommissionDetailEntity commission = new CommissionDetailEntity();
			commission.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
			commission.setSettlementType("0");
			int commissionNum = commissionDetailService.findPageCount(commission);
			//没有生成过佣金才会生成
			if(commissionNum <= 0){
				commission = new CommissionDetailEntity();
				commission.setWfxOrderNo(order.getWfxOrderNo());
				commission.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
				commission.setSettlementType("0");
				commission.setOrderTime(order.getCreatedTime());
				CommodityStyleEntity style = commodityStyleService.getById(detail.getCommodityId());
				if(style != null){
					commission.setCommodityNo(style.getNo());
					commission.setBrandNo(style.getBrandNo());
					Map<String,String> catMap = commodityCatb2cService.getCatIds(style.getCatNo());
					if(null != catMap){
						commission.setBaseCatId1(catMap.get("catId1"));
						commission.setBaseCatId2(catMap.get("catId2"));
					}
				}
				commission.setSupplierCode(order.getSupplierId());
				commission.setConfirmTime(order.getConfirmTime());
				commission.setSettlementType("0");
				commission.setCommodityQuantity(num);
				commission.setGoodsPrice(price);
				commission.setGoodsAmount(totalFee);
				commission.setFreightCharges(order.getPostFee());
				commission.setStatus("0");
				Date date = new Date();
				commission.setCreateTime(date);
				commission.setUpdateTime(date);
				String buyerId = order.getBuyerId();// 下单人
				SellerInfoEntity  buyerSeller = sellerInfoService.getSellerByMemberId(buyerId);
				if( buyerSeller!=null ){
					commission.setNextSellerId( buyerSeller.getId() );// 下单人的SellerId. Update by LQ.
				}else{
					logger.error( "生成佣金对象时，未找到下单人的SellerID :  buyerId - "+buyerId +" , WfxOrderDetailNo - "+detail.getWfxOrderDetailNo());
					commission.setNextSellerId( buyerId );
				}
				Double cp1 = detail.getCommissionLevel1Percent()==null?0d:detail.getCommissionLevel1Percent();
				String seller1Id = order.getCommissionSellerId();
				SellerInfoEntity seller1Info = sellerInfoService.getById(seller1Id);
				boolean seller1Flag = this.generateCommissionFlag(cp1, seller1Info, order);
				//生成佣金的条件，佣金金额大于0，分销商处于非关闭状态
				if(seller1Flag){
					//一级佣金
					Double percent1 = MathUtil.roundHalfUp(cp1*100);
					commission.setWfxShopName(seller1Info.getShopName());
					commission.setLoginName(seller1Info.getLoginName());
					commission.setDeductRatio(percent1);
					commission.setCommissionAmount(MathUtil.roundHalfUp(realFee * cp1));
					commission.setSellerId(seller1Id);
					commission.setCommissionLevel("1");
					
					//当佣金大于0时保存一级佣金
					if(MathUtil.roundHalfUp(realFee * cp1) > 0){
						commissionDetailService.insert(commission);
					}
				}
				//二级佣金是否保存判断
				Double cp2 = detail.getCommissionLevel2Percent()==null?0d:detail.getCommissionLevel2Percent();
				String seller2Id = seller1Info.getParentId();
				SellerInfoEntity seller2Info = sellerInfoService.getById(seller2Id);
				boolean seller2Flag = this.generateCommissionFlag(cp2, seller2Info, order);
				//二级佣金
				if(seller2Flag){//只有佣金比例大于0且上级分销商存在时才生成
					commission.setId(null);
					commission.setWfxShopName(seller2Info.getShopName());
					commission.setLoginName(seller2Info.getLoginName());
					Double percent2 = MathUtil.roundHalfUp(cp2*100);
					commission.setDeductRatio(percent2);
					commission.setCommissionAmount(MathUtil.roundHalfUp(realFee * cp2));
					commission.setSellerId(seller2Id);
					commission.setCommissionLevel("2");
					//当佣金大于0时保存二级佣金
					if(MathUtil.roundHalfUp(realFee * cp2) > 0){
						commissionDetailService.insert(commission);
					}
				}
				if(null != seller2Info && StringUtils.isNotBlank(seller2Info.getParentId())){
					//三级佣金是否保存判断
					Double cp3 = detail.getCommissionLevel3Percent()==null?0d:detail.getCommissionLevel3Percent();
					String seller3Id = seller2Info.getParentId();
					SellerInfoEntity seller3Info = sellerInfoService.getById(seller3Id);
					boolean seller3Flag = this.generateCommissionFlag(cp3, seller3Info, order);
					//三级佣金
					if(seller3Flag){//只有佣金比例大于0且上级分销商存在时才生成
						commission.setId(null);
						commission.setWfxShopName(seller3Info.getShopName());
						commission.setLoginName(seller3Info.getLoginName());
						Double percent3 = MathUtil.roundHalfUp(cp3*100);
						commission.setDeductRatio(percent3);
						commission.setCommissionAmount(MathUtil.roundHalfUp(realFee * cp3));
						commission.setSellerId(seller3Id);
						commission.setCommissionLevel("3");
						//当佣金大于0时保存二级佣金
						if(MathUtil.roundHalfUp(realFee * cp3) > 0){
							commissionDetailService.insert(commission);
						}
					}
				}
			}
		}
	}

	private boolean generateCommissionFlag(Double cp,SellerInfoEntity sellerInfo,OrderEntity order){
		//佣金比例小于0不生成
		if(cp <= 0 ){
			return false;
		}
		//如果分销商信息为空，或者状态信息为空，则不保存佣金
		if(null == sellerInfo || StringUtils.isBlank(sellerInfo.getState())){
			return false;
		}
		//分销商店状态为4时表明是停止合作店铺
		//自分销商停止合作时间时，在停止时间点前：分销商未结算的订单佣金、余额提现都不能操作；在停止时间点后：其下级分销商产生的订单不能给其返佣
		if("4".equals(sellerInfo.getState())){
			Date orderCreate = order.getCreatedTime();
			Date lastUnActive = sellerInfo.getLastUnactiveDate();
			if(null == orderCreate || null == lastUnActive){
				return false;
			}
			long diffTime = orderCreate.getTime() - lastUnActive.getTime();
			if(diffTime > 0){
				return false;
			}
		}
		String sellerId = sellerInfo.getId();
		if(StringUtils.isBlank(sellerId)){
			return false;
		}
		ShopEntity shop = shopService.getShopBySeller(sellerId);
		//如果是总店，则不保存佣金
		if(null == shop || StringUtils.isBlank(shop.getId()) || "yougoushop".equals(shop.getId())){
			return false;
		}
		sellerInfo.setShopName(shop.getName());
		return true;
	}
	
	@Override
	@LoggerProfile(methodNote="定时作废未付款订单job")
	public String closeOrders() {
		return orderService.closeOrders();
	}

	@Override
	@LoggerProfile(methodNote="超时自动确认订单收货")
	public String autoChangeOrderStatusToDeliverd() {
		return orderService.autoChangeOrderStatusToDeliverd();
	}
	@Override
	public List<OrderConsignOutputDto> getOrderLogisDetail(String orderId) {
		// TODO Auto-generated method stub
		List<OrderConsignEntity> consignEntityList =  orderService.queryOrderConsign(orderId);
		//物流信息
		List<OrderConsignOutputDto>   consignList= (List<OrderConsignOutputDto>) BeanUtil.convertBeanList( consignEntityList ,OrderConsignOutputDto.class);;
		
		//查询物流信息
		if(consignList!=null && consignList.size()>0){
			for (int i = 0; i < consignList.size(); i++) {
				OrderConsignOutputDto  dto=consignList.get(i);
				
				String expressOrderId = dto.getExpressNo();// 货运单号
				
				MemberLogisticsVo memberLogisticsVo =CommodityProxyApi.getMemberLogistics(expressOrderId, dto.getExpressCode());
				
				List<WfxLogisticsData>   wfxLogisticsDataList=new ArrayList<WfxLogisticsData>();
				if(memberLogisticsVo!=null){
					List<LogisticsData>  logisticsDateList=memberLogisticsVo.getData();
					if(logisticsDateList!=null && logisticsDateList.size()>0){
						for (LogisticsData logisticsData : logisticsDateList) {
							WfxLogisticsData  wfxLogisticsData=new WfxLogisticsData();
							wfxLogisticsData.setTime(logisticsData.getTime());
							wfxLogisticsData.setContext(logisticsData.getContext());
							wfxLogisticsDataList.add(wfxLogisticsData);
						 }
					}
					
				}
				
				dto.setLogisticsData(wfxLogisticsDataList);
				//包裹内商品详情
				OrderConsignEntity orderConsign=consignEntityList.get(i);
				List<OrderConsignCommodityOutputDto>   orderConsignCommodityDto=(List<OrderConsignCommodityOutputDto>) BeanUtil.convertBeanList( orderConsign.getOrderConsignCommodity() ,OrderConsignCommodityOutputDto.class);				
				dto.setOrderConsigncommodity(orderConsignCommodityDto);
			}
		}
		
		
		//包裹商品信息
		/*List<OrderConsignEntity>  orderConsignEntity=orderApplyEntity.getOrderConsign();
		if(orderConsignEntity!=null && orderConsignEntity.size()>0){
			for (int i = 0; i < orderConsignEntity.size(); i++) {
				OrderConsignEntity orderConsign=orderConsignEntity.get(i);
				List<OrderConsignCommodityOutputDto>   orderConsignCommodityDto=(List<OrderConsignCommodityOutputDto>) BeanUtil.convertBeanList( orderConsign.getOrderConsignCommodity() ,OrderConsignCommodityOutputDto.class);
				OrderConsignOutputDto   consignOutput=orderConsignOutPutDto.get(i);
				consignOutput.setOrderConsigncommodity(orderConsignCommodityDto);
			}
			
		}*/
		return consignList;
	}
}
