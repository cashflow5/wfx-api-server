 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.component.logistics.api.IMemberLogisticsApi;
import com.yougou.component.logistics.constant.LogisticsCompany;
import com.yougou.component.logistics.vo.LogisticsData;
import com.yougou.component.logistics.vo.MemberLogisticsVo;
import com.yougou.wfx.aftersale.dao.OrderRefundMapper;
import com.yougou.wfx.aftersale.model.OrderApplyEntity;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.api.background.ICommodityProductBackgroundApi;
import com.yougou.wfx.commodity.api.front.ICommodityStyleFrontApi;
import com.yougou.wfx.commodity.dao.CommodityProductMapper;
import com.yougou.wfx.commodity.dao.CommodityStyleMapper;
import com.yougou.wfx.commodity.dto.input.CheckProductNumberInputDto;
import com.yougou.wfx.commodity.dto.output.CheckProductNumberDetailsOutputDto;
import com.yougou.wfx.commodity.dto.output.CheckProductNumberOutputDto;
import com.yougou.wfx.commodity.dto.output.CommoditySimplifyStyleOutputDto;
import com.yougou.wfx.commodity.model.CommodityProductEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.ICommodityPicsService;
import com.yougou.wfx.commodity.service.ICommodityProductService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.enums.OrderLogBelongTypeEnum;
import com.yougou.wfx.enums.OrderLogOptBelongEnum;
import com.yougou.wfx.enums.OrderLogOptEnum;
import com.yougou.wfx.enums.OrderLogOptResultEnum;
import com.yougou.wfx.enums.OrderStatusEnum;
import com.yougou.wfx.finance.api.front.ICommissionPercentFrontApi;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.member.dao.MemberAccountMapper;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.order.dao.OrderConsignDetailMapper;
import com.yougou.wfx.order.dao.OrderConsignMapper;
import com.yougou.wfx.order.dao.OrderMapper;
import com.yougou.wfx.order.dto.output.ConsignInfosOutPutDto;
import com.yougou.wfx.order.dto.output.OrderCreateDto;
import com.yougou.wfx.order.dto.output.OrderDetailCreateDto;
import com.yougou.wfx.order.dto.output.OrderDetailDto;
import com.yougou.wfx.order.dto.output.OrderInfoDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;
import com.yougou.wfx.order.dto.output.OrderResultDto;
import com.yougou.wfx.order.dto.output.StyleOutPutDto;
import com.yougou.wfx.order.dto.output.WfxLogisticsData;
import com.yougou.wfx.order.model.CommissionOrderEntity;
import com.yougou.wfx.order.model.OrderConsignDetailEntity;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.model.OrderCountEntity;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.model.OrderEntity;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderDetailService;
import com.yougou.wfx.order.service.IOrderLogService;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.seller.dao.SellerInfoMapper;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.shop.dao.ShopMapper;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.utils.BizNoGen;
import com.yougou.wfx.utils.LogisticsUtil;
import com.yougou.wfx.utils.MathUtil;
import com.yougou.wfx.utils.PicsUtil;

/**
 * IOrderService实现
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
@Service
public class OrderServiceImpl extends BaseService implements IOrderService {
	
	Logger logger = LoggerFactory.getLogger( OrderServiceImpl.class );
	
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private OrderConsignMapper orderConsignMapper;
	@Resource
	private OrderConsignDetailMapper orderConsignDetailMapper;
	@Resource
	private OrderRefundMapper orderRefundMapper;
	@Resource
	private IMemberLogisticsApi logisticsApi;
	@Resource
	private CommodityProductMapper commodityProductMapper;
	@Resource
	private CommodityStyleMapper commodityStyleMapper;
	@Resource
	private ICommodityStyleFrontApi commodityStyleFrontApi;
	@Resource
	private ICommodityProductBackgroundApi commodityProductBackgroundApi;
	@Resource
	private IOrderDetailService orderDetailService;
	@Resource
	private ShopMapper shopMapper;
	@Resource
	private ICommissionPercentFrontApi commissionPercentFrontApi;
	@Resource
	private IOrderLogService orderLogService;
	@Resource
	private ISysConfigService sysConfigService;
	@Resource
	private ICommodityPicsService commodityPicsService;
	@Resource
	private ICommodityProductService commodityProductService;
	@Resource
	private SellerInfoMapper sellerInfoMapper;
	@Resource
	private MemberAccountMapper memberAccountMapper;
	
	@Override
	public int findPageCount(OrderEntity orderEntity){
		return orderMapper.findPageCount(orderEntity);
	}

	@Override
	public List<OrderEntity> findPage(OrderEntity orderEntity,RowBounds rowBounds){
		return orderMapper.findPage(orderEntity,rowBounds);
	}
	
	@Override
	public List<OrderEntity> findPage(OrderEntity orderEntity){
		return orderMapper.findPage(orderEntity);
	}
	
	@Override
	@Transactional
	public String insert(OrderEntity orderEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		orderEntity.setId(strId);
		orderMapper.insert(orderEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderEntity orderEntity){
		return  orderMapper.update(orderEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderMapper.remove(id);
	}
	
	@Override
	public OrderEntity getById(String id){
		return orderMapper.getById(id);
	}

	@Override
	public List<OrderApplyEntity> queryApplyList(OrderApplyEntity orderApplyEntity, RowBounds rowBounds) {
		return orderMapper.queryApplyList(orderApplyEntity, rowBounds);
	}

	@Override
	public int queryApplyCount(OrderApplyEntity orderApplyEntity) {
		return orderMapper.queryApplyCount(orderApplyEntity);
	}

	@Override
	public OrderApplyEntity   queryOrderDetails(String id) {
		return orderMapper.queryOrderDetails(id);
	} 
	@Override
	public OrderApplyEntity   queryOrderDetailsByNo(String wfxOrderNo) {
		return orderMapper.queryOrderDetailsByNo(wfxOrderNo);
	}

	@Override
	public PageModel<OrderOutputDto> queryPage(OrderEntity orderEntity,
			PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = orderMapper.findPageCount(orderEntity);
		List<OrderEntity> entityList = orderMapper.findPage(orderEntity, rowBounds);
		return new PageModel<OrderOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<OrderOutputDto>)BeanUtil.convertBeanList(entityList, OrderOutputDto.class));
	}

	@Override
	public List<OrderEntity> querySellerList(OrderEntity orderEntity,
			RowBounds rowBounds) {
		return orderMapper.querySellerList(orderEntity, rowBounds);
	}

	@Override
	public PageModel<OrderOutputDto> querySellerPage(OrderEntity orderEntity,
			PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = orderMapper.querySellerListCount(orderEntity);
		List<OrderEntity> entityList = orderMapper.querySellerList(orderEntity, rowBounds);
		return new PageModel<OrderOutputDto>(pageModel.getPage(), pageModel.getLimit(), totalCount, (List<OrderOutputDto>)BeanUtil.convertBeanList(entityList, OrderOutputDto.class));
	}

	@Override
	public int queryReceivedCount(String memberId) {
		// TODO Auto-generated method stub
		return orderMapper.queryReceivedCount(memberId);
	}

	@Override
	public List<ConsignInfosOutPutDto> getConsignInfosByOrderId(@NotBlank String orderId) {
		List<ConsignInfosOutPutDto> consignList = new ArrayList<ConsignInfosOutPutDto>();
		ConsignInfosOutPutDto consign = null;
		OrderEntity order = orderMapper.getById(orderId);
		if(null == order){
			throw new RuntimeException("订单不存在");
		}
		OrderConsignEntity confignEntity = new OrderConsignEntity();
		confignEntity.setOrderId(orderId);
		List<OrderConsignEntity> orderConsignList = orderConsignMapper.queryList(confignEntity);
		if(null != orderConsignList && orderConsignList.size() > 0){
			for(OrderConsignEntity consignTemp:orderConsignList){
				consign = new ConsignInfosOutPutDto();
				consign.setExpressCode(consignTemp.getExpressCode());
				consign.setExpressName(consignTemp.getExpressName());
				consign.setExpressNo(consignTemp.getExpressNo());
				consign.setConsignTime(consignTemp.getCreateTime());
				
				LogisticsCompany logisticsCompany = LogisticsUtil.getLogisticsByCode(consignTemp.getExpressCode());
				//查询物流跟踪信息
				MemberLogisticsVo memberLogisticsVo = logisticsApi.getLogistics4wfx(logisticsCompany, consignTemp.getExpressNo());
				if(null != memberLogisticsVo){
					List<WfxLogisticsData> wfxLogisticsDataList=new ArrayList<WfxLogisticsData>();
					List<LogisticsData> logisticsDateList=memberLogisticsVo.getData();
					if(logisticsDateList!=null && logisticsDateList.size()>0){
						for(LogisticsData logisticsData : logisticsDateList) {
							WfxLogisticsData wfxLogisticsData=new WfxLogisticsData();
							wfxLogisticsData.setTime(logisticsData.getTime());
							wfxLogisticsData.setContext(logisticsData.getContext());
							wfxLogisticsDataList.add(wfxLogisticsData);
						}
					}
					consign.setTraceData(wfxLogisticsDataList);
				}
				
				//查询该物流包括的商品信息
				OrderConsignDetailEntity detail = new OrderConsignDetailEntity();
				detail.setConsignId(consignTemp.getId());
				List<OrderConsignDetailEntity> detailList = orderConsignDetailMapper.queryList(detail);
				if(null != detailList && detailList.size() > 0){
					//商品信息
					List<StyleOutPutDto> styleList = new ArrayList<StyleOutPutDto>();
					StyleOutPutDto style = null;
					for(OrderConsignDetailEntity detailTemp:detailList){
						style = new StyleOutPutDto();
						OrderDetailEntity orderDetail = orderDetailService.getById(detailTemp.getOrderDetailId());
						if(null != orderDetail){
							style.setProdSpec(orderDetail.getProdSpec());
						}
						style.setNum(detailTemp.getNum());
						String prodId = detailTemp.getProdId();
						CommodityProductEntity product = commodityProductMapper.getById(prodId);
						if(null != product){
							CommodityStyleEntity styleEntity = commodityStyleMapper.getById(product.getCommodityId());
							if(null != styleEntity){
								String picSmall = commodityPicsService.getUrlByCommNo(styleEntity.getNo(), Constant.COMMODITY_PICS_SMALL);
								if(StringUtils.isNotBlank(picSmall)){
									picSmall = PicsUtil.getPicUrl() + picSmall;
								}
								style.setCommId(styleEntity.getId());
								style.setPicUrl(picSmall);
								style.setProd_name(styleEntity.getCommodityName());
							}
						}
						styleList.add(style);
					}
					consign.setStyleList(styleList);
				}
				consignList.add(consign);
			}
		}
		return consignList;
	}
	
	/**
	 * 创建订单接口
	 */
	@Override
	@Transactional
	public OrderResultDto createOrder(OrderCreateDto orderCreateDto) {
		OrderResultDto result = new OrderResultDto();
		try{
			List<OrderDetailCreateDto> commDtoList = orderCreateDto.getOrderDetailCreateDto();
			//校验商品的上下架状态
			//默认为上架状态
			for(OrderDetailCreateDto commDto:commDtoList){
				CommoditySimplifyStyleOutputDto cssDto = commodityStyleFrontApi.queryShelvesStatusAndInventoryStatus(commDto.getCommodityId(), commDto.getProdId());
				if(null == cssDto || !cssDto.isShelvesStatus()){
					result.setCode(2);
					result.setShopId(orderCreateDto.getShopId());
					setShopCodeByShopId(result,orderCreateDto.getShopId());
					result.setProdId(commDto.getProdId());
					result.setProdName(commDto.getProdName() + " " + commDto.getProdSpec());
					String message = MessageFormat.format("订单创建失败，店铺：{0}，商品：{1}{2}",orderCreateDto.getShopName(), commDto.getProdName() + " " + commDto.getProdSpec()," 已下架！");
					result.setMessage(message);
					return result;
				}
			}
			//校验商品的库存是否充足
			List<CheckProductNumberInputDto> checkList = new ArrayList<CheckProductNumberInputDto>();
			CheckProductNumberInputDto check = null;
			Map prodMap = new HashMap();
			Map preMap = new HashMap();
			for(OrderDetailCreateDto commDto:commDtoList){
				//组装校验对象
				check = new CheckProductNumberInputDto();
				check.setProductId(commDto.getProdId());
				check.setOrderNum(commDto.getNum()+"");
				checkList.add(check);
				prodMap.put(commDto.getProdId(), commDto.getProdName()+" "+commDto.getProdSpec());
				
				//预占库存map
				preMap.put(commDto.getProdId(), commDto.getNum());
			}
			CheckProductNumberOutputDto checkResult = commodityStyleFrontApi.checkProductNumber(checkList);
			if(null == checkResult){
				result.setCode(2);
				result.setMessage("订单创建失败，查询库存时出错！");
				return result;
			}
			String resultStatus = checkResult.getRoductViaStatus();
			String prodIds = "";
			String prodNames = "";
			if(null == resultStatus || "false".equals(resultStatus)){
				List<CheckProductNumberDetailsOutputDto> checkDetailList = checkResult.getDto();
				if(null != checkDetailList && checkDetailList.size() > 0){
					for(CheckProductNumberDetailsOutputDto dto:checkDetailList){
						if(!dto.isInventoryStatus()){
							if("".equals(prodIds)){
								prodIds += dto.getProductId();
								prodNames += prodMap.get(dto.getProductId());
							}else{
								prodIds += ","+dto.getProductId() + ",";
								prodNames += ","+prodMap.get(dto.getProductId());
							}
						}
					}
				}
				result.setCode(2);
				result.setShopId(orderCreateDto.getShopId());
				setShopCodeByShopId(result,orderCreateDto.getShopId());
				result.setProdId(prodIds);
				result.setProdName(prodNames);
				String message = MessageFormat.format("订单创建失败，店铺：{0}的商品：{1}{2}",orderCreateDto.getShopName(), prodNames,"库存不足！");
				result.setMessage(message);
				return result;
			}
			//预占库存
			boolean preFlag = commodityProductBackgroundApi.batchPreInventory(preMap);
			if(!preFlag){
				//result.setCode(6);
				//result.setMessage("创建订单失败，创建订单时发生异常！");
				result.setCode(2);
				result.setShopId(orderCreateDto.getShopId());
				setShopCodeByShopId(result,orderCreateDto.getShopId());
				result.setProdId(prodIds);
				result.setProdName(prodNames);
				String message = "订单创建失败，您选购的商品库存不足！";
				result.setMessage(message);
				logger.error("创建订单失败，预占库存时出错,proids:"+prodIds);
				return result;
			}
			SellerInfoEntity commisionSeller = sellerInfoMapper.getSellerByMemberId( orderCreateDto.getBuyerId() );
			String commisionSellerId ;
			if( commisionSeller!=null ){
				commisionSellerId = commisionSeller.getId();
			}else{
				MemberAccountEntity  commisionMember = memberAccountMapper.getById( orderCreateDto.getBuyerId() );
				commisionSellerId = commisionMember.getParentSellerId();
			}
			orderCreateDto.setSellerId(commisionSellerId);
			//返回创建后的数据
			OrderInfoDto orderInfoDto = null;
			//保存订单
			OrderEntity orderEntity = this.converOrderDtoToEntity(orderCreateDto);
			String orderId = UUIDGenerator.get32LowCaseUUID();
			orderEntity.setId(orderId);
			orderEntity.setCommissionSellerId(commisionSellerId);
			// shopId,shopName,shopCode 根据commisionSeller重设
			 ShopEntity displayShop = shopMapper.getShopBySeller(orderEntity.getCommissionSellerId());
			 if(displayShop!=null){
				 orderEntity.setShopCode( displayShop.getShopCode() );
				 orderEntity.setShopId( displayShop.getId() );
				 orderEntity.setShopName( displayShop.getShopCode() );
			 }else{
				 logger.error("--订单结算人的shopCode未找到,buyerId: "+orderCreateDto.getBuyerId());
			 }
			 
			orderMapper.insert(orderEntity);
			orderInfoDto = (OrderInfoDto)BeanUtil.convertBean(orderEntity, OrderInfoDto.class);
			//保存子订单
			List<OrderDetailEntity> detailEntityList = this.converOrderDetailDto2Entity(commDtoList, orderEntity);
			if(null != detailEntityList && detailEntityList.size() > 0){
				for(OrderDetailEntity orderDetailEntity:detailEntityList){
					String detailId = orderDetailService.insert(orderDetailEntity);
					orderDetailEntity.setId(detailId);
				}
			}
			List<OrderDetailDto> orderDetailList = (List<OrderDetailDto>)BeanUtil.convertBeanList(detailEntityList, OrderDetailDto.class);
			orderInfoDto.setOrderDetailList(orderDetailList);
			result.setCode(1);
			result.setMessage("创建订单成功");
			result.setOrderInfoDto(orderInfoDto);
			
			//创建订单日志
			orderLogService.insertOrderLog(orderEntity.getWfxOrderNo(),orderEntity.getBuyerAccount(), 
					OrderLogOptEnum.OPT_CREATE_ORDER.getKey(),OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey(),
					OrderLogOptResultEnum.OPT_SUCCESS.getKey());
		}catch(Exception e){
			logger.error("创建订单异常create order error:",e);
			throw new RuntimeException("创建订单异常");
		}
		return result;
	}
	
	private OrderEntity converOrderDtoToEntity(OrderCreateDto orderDto){
		OrderEntity orderEntity = new OrderEntity();
		orderEntity = (OrderEntity)BeanUtil.convertBean(orderDto, OrderEntity.class);
		ShopEntity shopEntity = shopMapper.getShopBySeller(orderDto.getSellerId());
		//String shopId = orderEntity.getShopId();
		//ShopEntity shopEntity = shopMapper.getById(shopId);
		if(null != shopEntity){
			orderEntity.setShopName(shopEntity.getName());
			orderEntity.setShopCode(shopEntity.getShopCode());
		}
		orderEntity.setStatus(OrderStatusEnum.WAIT_PAY.getKey());
		orderEntity.setPayTime(null);//用来判断是否付款
		Date date = new Date();
		orderEntity.setWfxOrderNo(BizNoGen.getOrderNo());
		orderEntity.setCreatedTime(date);
		orderEntity.setUpdateTime(date);
		orderEntity.setCommissionStatus(1);//未生成佣金
		return orderEntity;
	}
	
	private List<OrderDetailEntity> converOrderDetailDto2Entity(List<OrderDetailCreateDto> commDtoList,OrderEntity orderEntity){
		List<OrderDetailEntity> entityList = new ArrayList<OrderDetailEntity>();
		if(null != commDtoList && commDtoList.size() > 0){
			for(int i = 0;i < commDtoList.size();i++){
				OrderDetailCreateDto createDto = commDtoList.get(i);
				OrderDetailEntity detailEntity = (OrderDetailEntity)BeanUtil.convertBean(createDto, OrderDetailEntity.class);
				//保存主订单号
				detailEntity.setOrderId(orderEntity.getId());
				detailEntity.setSellerId(orderEntity.getSellerId());
				detailEntity.setShopId(orderEntity.getShopId());
				detailEntity.setShopName(orderEntity.getShopName());
				//根据主订单编号生成子订单编号
				detailEntity.setWfxOrderDetailNo(orderEntity.getWfxOrderNo() + "_" + (i+1));
				//佣金
				Double commissionLevel1Percent = 0d;
				Double commissionLevel2Percent = 0d;
				Double commissionLevel3Percent = 0d;
//				List catIdList = commodityProductMapper.getCatId(createDto.getProdId());
//				List<CommissionPercentOutputDto> commissionPercents = commissionPercentFrontApi.getByBaseCatId(catIdList);
//				if(null == commissionPercents || commissionPercents.size() <= 0){
//					throw new RuntimeException("查询佣金比例出错");
//				}
				List<String> baseCatIds = commodityStyleMapper.getCatId(createDto.getCommodityId());
				CommissionPercentOutputDto commissionPercent = commissionPercentFrontApi.getCommissionByCondition(commodityStyleMapper.getCommodityBrandNo(createDto.getCommodityId()), baseCatIds != null ? baseCatIds.get(0) : "", createDto.getCommodityId());
				if(null == commissionPercent){
					throw new RuntimeException("查询佣金比例出错");
				}
				commissionLevel1Percent = commissionPercent.getCommissionLevel1Percent();
				commissionLevel2Percent = commissionPercent.getCommissionLevel2Percent();
				commissionLevel3Percent = commissionPercent.getCommissionLevel3Percent();
				detailEntity.setCommissionLevel1Percent(commissionLevel1Percent);
				detailEntity.setCommissionLevel2Percent(commissionLevel2Percent);
				detailEntity.setCommissionLevel3Percent(commissionLevel3Percent);
				Double payment = detailEntity.getPayment();
				detailEntity.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1Percent*payment));
				detailEntity.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2Percent*payment));
				detailEntity.setCommissionLevel3(MathUtil.roundHalfUp(commissionLevel3Percent*payment));
				detailEntity.setRefundStatus(null);
				Date date = new Date();
				detailEntity.setCreateTime(date);
				detailEntity.setUpdateTime(date);
				entityList.add(detailEntity);
			}
		}
		return entityList;
	}
	
	@Override
	public OrderCountEntity getMemberOrderCount(String memberId) {
		// TODO Auto-generated method stub
		OrderCountEntity orderCount = new OrderCountEntity();
		//代付款订单数
		int notPayOrderCount = orderMapper.queryMemberOrderCountByStatus(memberId, OrderStatusEnum.WAIT_PAY.getKey());
		//退款/售后 订单数
		int refundOrderCount = orderMapper.queryMemberRefundOrderCount(memberId);
		/**
		//部分发货
		int partDeleveredOrderCount = orderMapper.queryMemberOrderCountByStatus(memberId, OrderStatusEnum.PART_DELIVERED.getKey());
		//已发货
		int deliveredOrderCount = orderMapper.queryMemberOrderCountByStatus(memberId, OrderStatusEnum.DELIVERED.getKey());
		//待发货
		int waitDeliverOrderCount = orderMapper.queryMemberOrderCountByStatus(memberId, OrderStatusEnum.WAIT_DELIVER.getKey());
		*/
		orderCount.setNotPayOrderCount(notPayOrderCount);
		orderCount.setRefundOrderCount(refundOrderCount);
		/**
		orderCount.setShipedOrderCount(partDeleveredOrderCount+deliveredOrderCount);
		orderCount.setWaitDeliverOrderCount(waitDeliverOrderCount);
		*/
		return orderCount;
	}

	@Override
	public List<OrderEntity> queryUnCommissionOrders(OrderEntity orderEntity) {
		return orderMapper.queryUnCommissionOrders(orderEntity);
	}

	@Override
	public String closeOrders() {
		int seconds = 30*60;
		String setValue = sysConfigService.getValueBykey( Constant.KEY_CLOSEORDERS_TIME );
		if( StringUtils.isNotEmpty(setValue) ){
			try {
				seconds = Integer.parseInt( setValue.trim() )*60;
			} catch (NumberFormatException e) {
				logger.error( "从系统配置参数取数异常："+setValue, e );
			}
		}
		Date curDate = new Date();
		List<OrderEntity> list = orderMapper.queryForCloseOrders(seconds, curDate);
		int rowNum = 0;
		if( list!=null&& list.size()>0 ){
			rowNum =  orderMapper.closeOrders(seconds,curDate);
			// 写日志
			for(OrderEntity orderEntity :list ){
				//释放预占库存
				try {
					OrderDetailEntity detailEntity = new OrderDetailEntity();
					detailEntity.setOrderId(orderEntity.getId());
					List<OrderDetailEntity> detailList = orderDetailService.queryList(detailEntity);
					for(OrderDetailEntity orderDetail : detailList){
						commodityProductService.freePreInventory(orderDetail.getProdId(), orderDetail.getNum());
					}
				} catch (Exception e) {
					logger.error("订单号["+orderEntity.getWfxOrderNo()+"]释放预占库存出错。",e);
				}
				
				//创建订单日志
				OrderLogEntity orderLogEntity = new OrderLogEntity();
				orderLogEntity.setCreateTime( curDate );
				orderLogEntity.setOptUser("system");
				orderLogEntity.setOrderNo( orderEntity.getWfxOrderNo() );
				orderLogEntity.setOptType( OrderLogOptEnum.OPT_CANCEL_ORDER.getKey()  );
				orderLogEntity.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey() );
				orderLogEntity.setOptResult( OrderLogOptResultEnum.OPT_SUCCESS.getKey() );
				orderLogEntity.setLogType( OrderLogBelongTypeEnum.ORDER_LOG.getKey() );
				orderLogEntity.setLogInfo("超时未支付系统取消订单 ");
				orderLogService.insert(orderLogEntity);
			}
			logger.info("自动关闭未付款订单"+rowNum+"条！");
		}
		
		return "success:"+rowNum;
	}

	/**
	 SELECT DISTINCT
	o.*
FROM
	tbl_wfx_order o
WHERE
	(o. STATUS = 'DELIVERED' or o.status ='PART_DELIVERED' )
AND o.id NOT IN (
	SELECT
		distinct order_id
	FROM
		tbl_wfx_order_refund
	WHERE
		STATUS IN ( 
			'APPLYING',
			'PENDING_DELIVERD',
			'REJECT_REFUND',
			'UNDER_REFUND'
		)
)
# 遍历list，查所有的order_detail_id
select d.* from tbl_wfx_order_detail d
where d.order_id = '20160322002';

#step 2.1: 在物流表查order_detail_id是否有记录
select DISTINCT DATEDIFF(NOW(), IFNULL(update_time,create_time)) as diffFromNow
from tbl_wfx_order_consign_detail
where order_detail_id='' 
# 2.2 有记录，diffFromNow<days*24*60*60 , break and RETURN

# 2.3 无记录，在退款表查

select order_detail_id,sum(pro_num) as returnNum
       from tbl_wfx_order_refund 
where   status ='SUCCESS_REFUND'
 and order_detail_id = #{orderDetailId,jdbcType=VARCHAR}

# 2.4 若 找不到或者returnNum ！= buyNum ，break and RETURN

# 2.5 判断为符合条件，更新订单状态，记录日志
	 */
	
	@Override
	public String autoChangeOrderStatusToDeliverd() {
		
		int days = 7;
		String setValue = sysConfigService.getValueBykey( Constant.KEY_AUTOCHANGE_ORDERSTATUS_DAYS );
		if( StringUtils.isNotEmpty(setValue) ){
			try {
				days = Integer.parseInt( setValue.trim() );
			} catch (NumberFormatException e) {
				logger.error( "从系统配置参数"+Constant.KEY_AUTOCHANGE_ORDERSTATUS_DAYS+"取数异常："+setValue, e );
			}
		}
		Date curDate = new Date();
		int numOfQualified = 0;
		List<OrderEntity> firstList = orderMapper.queryAutoChangeOrderStatusList();
		if( firstList!=null && firstList.size()>0 ){
			logger.debug(curDate+" ,autoChangeOrderStatusToDeliverd: firstList.size():"+firstList.size());
			for( OrderEntity orderEntity:firstList ){
				String orderId = orderEntity.getId();
				int buyNum = orderEntity.getNum();
				logger.debug("~ ~ ~orderId :"+orderId+" buyNum: "+ buyNum);
				//查所有的order_detail_id
				List<String> secondList = orderDetailService.getByOrderId(orderId);
				if( null!=secondList && secondList.size()>0 ){
					boolean isQualified = true;
					for(String orderDetailId :secondList ){
						//在物流表查order_detail_id是否有记录
						Integer diffFromNow = orderConsignDetailMapper.queryQualifiedByOrderDetailId(orderDetailId, curDate);
						if( diffFromNow!= null){//找到了记录
							if( diffFromNow< days*86400 ){// diffFromNow<days*24*60*60 , break and RETURN
								isQualified = false;
								break;
							}
						}else{//在退款表接着查
							Integer backNum = orderRefundMapper.queryBackNumByOrderDetailId(orderDetailId);
							if(backNum==null || backNum!=buyNum ){//find it
								isQualified = false;
								break;
							}
						}
						
					}
					
					// 处理该orderId的状态、记录日志
					if(isQualified){
						OrderEntity orderForUpdate = new  OrderEntity();
						orderForUpdate.setStatus( OrderStatusEnum.TRADE_SUCCESS.getKey() );
						orderForUpdate.setUpdateTime(curDate);
						orderForUpdate.setConfirmTime( curDate );
						orderForUpdate.setId(orderId);
						int result = orderMapper.update(orderForUpdate);
						if( result>0 ){
							numOfQualified++;
							logger.info(" 找到超时需要自动确认收货的订单更新订单状态，订单编号："+orderEntity.getWfxOrderNo());
							// 写日志
							orderLogService.insertOrderLog(orderEntity.getWfxOrderNo(), "system",
									OrderLogOptEnum.OPT_RECEIVED_ORDER.getKey(), OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey(), 
									OrderLogOptResultEnum.OPT_SUCCESS.getKey());
						}
					}
					
				}
			}
		}
		return "success:"+numOfQualified;
	}

	@Override
	public int getSellerSevenDayOrderCount(OrderEntity orderEntity) {
		return orderMapper.getSellerSevenDayOrderCount(orderEntity);
	}
	@Override
	public List<OrderConsignEntity> queryOrderConsign(String orderId) {
		// TODO Auto-generated method stub
		return orderMapper.queryOrderConsign(orderId);
	}
	
	private void setShopCodeByShopId(OrderResultDto result,String shopId){
		if( StringUtils.isEmpty(shopId) ){
			return ;
		}
		ShopEntity shop = shopMapper.getById(shopId);
		if( shop!=null ){
			result.setShopCode( shop.getShopCode() );
			result.setShopName( shop.getName() );
		}
		return ;
	}

	@Override
	public Double queryPreCommFee(Map<String, Object> paramMap) {
		Double preFee = orderMapper.queryPreCommFee(paramMap);
		if(null == preFee){
			preFee = 0d;
		}
		return preFee;
	}

	@Override
	public Double queryRefundCommFee(Map<String, Object> paramMap) {
		Double preFee = orderMapper.queryRefundCommFee(paramMap);
		if(null == preFee){
			preFee = 0d;
		}
		return preFee;
	}
	
	@Override
	public List<CommissionOrderEntity> findCommissionOrderPage(String sellerId,
			RowBounds rowBounds) {
		return orderMapper.queryPreCommFeeList(sellerId,rowBounds);
	}
	
	@Override
	public int findCommissionOrderPageCount(String sellerId) {
		return orderMapper.queryPreCommFeeCount(sellerId);
	}

	@Override
	public OrderEntity getByWFXOrderNo(String wfxOrderNo) {
		return orderMapper.getByWFXOrderNo(wfxOrderNo);
	}

	
}
