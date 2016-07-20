 /*
 * 版本信息
 
 * 日期 2016-03-30 16:50:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.api.background.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wfx.aftersale.api.background.IOrderRefundBackgroundApi;
import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.aftersale.dto.input.OrderRefundPageInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderRefundOutputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;
import com.yougou.wfx.aftersale.service.IOrderRefundService;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.OrderLogBelongTypeEnum;
import com.yougou.wfx.enums.OrderLogOptBelongEnum;
import com.yougou.wfx.enums.OrderLogOptEnum;
import com.yougou.wfx.enums.OrderLogOptResultEnum;
import com.yougou.wfx.enums.OrderStatusEnum;
import com.yougou.wfx.enums.RefundStatusEnum;
import com.yougou.wfx.enums.RefundTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.finance.api.background.IFinRefundBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinReturnDebtInputDto;
import com.yougou.wfx.finance.dto.output.FinRefundSynRes;
import com.yougou.wfx.finance.enums.FinRefundSynStateEnum;
import com.yougou.wfx.finance.model.CommissionDetailEntity;
import com.yougou.wfx.finance.service.ICommissionDetailService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.model.OrderEntity;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderDetailService;
import com.yougou.wfx.order.service.IOrderLogService;
import com.yougou.wfx.order.service.IOrderService;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.util.DatetimeUtil;
import com.yougou.wfx.utils.MathUtil;

/**
 * IOrderRefundBackgroundApi实现
 * @author luoq
 * @Date 创建时间：2016-03-30 16:50:25
 */
@Service
public class OrderRefundBackgroundApiImpl implements IOrderRefundBackgroundApi{
	
	private Logger logger = LoggerFactory.getLogger(OrderRefundBackgroundApiImpl.class);
	@Resource
	IOrderRefundService orderRefundService;
	@Resource
	IOrderLogService logService;
	@Resource
	IOrderService orderService;
	@Resource
	IOrderDetailService orderDetailService;
	@Resource
	ICommissionDetailService commissionDetailService;
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource
	ICommodityCatb2cService commodityCatb2cService;
	@Resource
	ISellerInfoService sellerInfoService;
	@Resource
	private IFinRefundBackgroundApi finRefundBackgroundApi;
	@Resource
	ISysConfigService sysConfigService;
	
	@Override
	@LoggerProfile(methodNote = "删除退款记录")
	public int removeById(String id) {
		return orderRefundService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote = "增加记录")
	public String insert(OrderRefundInputDto orderRefundDto) {
		return orderRefundService.insert(this.dtoToEntity(orderRefundDto));
	}

	@Override
	@LoggerProfile(methodNote = "查询退款记录")
	public PageModel<OrderRefundOutputDto> findPage(OrderRefundPageInputDto pageInputDto,PageModel<OrderRefundOutputDto> pageModel) {
		OrderRefundEntity orderRefundEntity = (OrderRefundEntity) BeanUtil.convertBean(pageInputDto,OrderRefundEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = orderRefundService.findPageCount(orderRefundEntity);
		List<OrderRefundEntity> lstOrderRefunds = orderRefundService.findPage(orderRefundEntity, rowBounds);

		return new PageModel<OrderRefundOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<OrderRefundOutputDto>) BeanUtil.convertBeanList(lstOrderRefunds,OrderRefundOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote = "更新记录")
	public int update(OrderRefundInputDto orderRefundDto) {
		return orderRefundService.update(this.dtoToEntity(orderRefundDto));
	}

	@Override
	@LoggerProfile(methodNote = "根据退款编号查询退款详情")
	public OrderRefundOutputDto getDetailByRefundNo(String refundNo)  {
		OrderRefundEntity orderRefundEntity = orderRefundService.getDetailByRefundNo(refundNo);
		return this.entityToDto(orderRefundEntity);
	}
	
	private OrderRefundEntity dtoToEntity(Object obj){
		return (OrderRefundEntity) BeanUtil.convertBean(obj,OrderRefundEntity.class);
	}
	
	private OrderRefundOutputDto entityToDto(Object obj){
		return (OrderRefundOutputDto) BeanUtil.convertBean(obj,OrderRefundOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "创建退款")
	public WFXResult<OrderRefundOutputDto> createRefundItem(
			OrderRefundInputDto orderRefundInputDto) {
		return orderRefundService.createRefundRecord(orderRefundInputDto);
	}
	@Override
	@LoggerProfile(methodNote = "根据ID查退款详情")
	public OrderRefundOutputDto getById(String id) {
		OrderRefundEntity orderRefundEntity = orderRefundService.getById(id);
		return this.entityToDto(orderRefundEntity);
	}
	

	@Override
	@LoggerProfile(methodNote = "统计订单的可退金额可退数量")
	public RefundSatistics refundSatistics(RefundSatistics refundSatistics){
		return orderRefundService.refundSatistics(refundSatistics);
	}

	@Override
	@LoggerProfile(methodNote = "超时自动关闭退款接口")
	public String timeOutCloseRefund() {
		return orderRefundService.timeOutCloseRefund();
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	@LoggerProfile(methodNote="财务退款成功后回调方法")
	public WFXResult<Boolean> updateStatusOfRefund(@NotBlank.List(value={@NotBlank(target="refundNo"),@NotBlank(target="refundType"),@NotBlank(target="payTime")})
			OrderRefundInputDto orderRefundInputDto) {
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		OrderRefundEntity orderRefundEntity = new OrderRefundEntity();
		orderRefundEntity.setRefundNo( orderRefundInputDto.getRefundNo() );
		orderRefundEntity.setUpdateTime( new Date() );
		orderRefundEntity.setStatus( RefundStatusEnum.SUCCESS_REFUND.getKey() );
		orderRefundEntity.setPayTime( orderRefundInputDto.getPayTime() );
		int rowNum = orderRefundService.update(orderRefundEntity);
		logger.info("orderRefundEntity update operation refects rowNum : "+rowNum);
		if( rowNum>0 ){
			
			// 如果订单全部商品退款完毕，更新订单状态为关闭。
			orderRefundEntity  = orderRefundService.getDetailByRefundNo( orderRefundInputDto.getRefundNo() );
			String orderId = orderRefundEntity.getOrderId();
			String orderDetailId = orderRefundEntity.getOrderDetailId();
			OrderEntity order = orderService.getById(orderId);
			OrderDetailEntity detail = orderDetailService.getById(orderDetailId);
			closeOrderAccordingRefundDetails( order,detail );// 两个判定！
			
			//退款成功后生成退款单的负佣金
			this.insertCommisionDetail(orderRefundEntity,order, detail);
			
			//记录售后日志
			OrderLogEntity orderLog = new OrderLogEntity();
			orderLog.setRejectedNo(orderRefundInputDto.getRefundNo());
			orderLog.setLogType( OrderLogBelongTypeEnum.REFUND_LOG.getKey() );
			Date curDate =  new Date();
			orderLog.setUpdateTime( curDate );
			orderLog.setCreateTime( curDate );
			//? 供货商名称
			orderLog.setOptUser( orderRefundInputDto.getSupplierName() );
			StringBuilder logMessage = new StringBuilder("退款类型：").append( RefundTypeEnum.getDescByKey( orderRefundInputDto.getRefundType() ) )
					.append("#退款单：").append(orderRefundInputDto.getRefundNo()).append("，退款成功");
			orderLog.setLogInfo( logMessage.toString());
			orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey() );
			logService.insert(orderLog);
			
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(true);
			rs.setResultMsg("完成更新退款单状态到‘退款成功’。");
		}else{
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(false);
			rs.setResultMsg("更新退款单状态失败：未定位到该退款记录，退款编号："+orderRefundInputDto.getRefundNo());
		}
		return rs;
	}
	
	 // 如果"部分发货" 这种状态的主订单 ，符合条件 :'订单中已发货+已退款成功的子订单数量等于主订单的全部子订单数量'时，改主订单的状态为已发货。
	private boolean upateOrderAccordingRefundDetails(OrderEntity order,OrderDetailEntity detail,List<OrderDetailEntity> detailList){
			
		boolean firstResult = false;//初始值
		boolean hasDelivered = false;
		if( StringUtils.isNotEmpty(order.getStatus() ) && 	order.getStatus().equals( OrderStatusEnum.PART_DELIVERED.getKey() ) ){
			firstResult = true;//有必要进行step2
		}else{
			return false;
		}
		for(OrderDetailEntity detailVo: detailList){
			if( !detailVo.getId().equals(detail.getId() ) ){
				// 一旦有子订单未发货或者未退完，则跳出循环
				boolean isDelivered = orderRefundService.isOrderDetailDelivered(detailVo);
				if( isDelivered ){
					hasDelivered = true;//有发货的子订单
					continue;
				}else{
					if( !specifyOrderDetail(detailVo) ){// 没发货未退完
						firstResult =  false;
						break;
					}
				}
			}
		}
		
		if(firstResult && hasDelivered){
			//更新主订单状态为已发货
			Date curDate = new Date() ;
			OrderEntity orderEntity = new OrderEntity();
			orderEntity.setId( order.getId() );
			orderEntity.setUpdateTime( curDate ); 
			orderEntity.setStatus( OrderStatusEnum.DELIVERED.getKey() );
			orderService.update(orderEntity);
			//记录订单日志
			logService.insertOrderLog(order.getWfxOrderNo(), "system",
							OrderLogOptEnum.OPT_DELIVERED.getKey(),OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey(),
								OrderLogOptResultEnum.OPT_SUCCESS.getKey());
			return true;
		}else{
			return false;
		}
	}
		
	// step1:如果订单全部商品退款完毕，更新订单状态为关闭。 
	// step2:如果"部分发货" 这种状态的主订单 ，符合条件 :'订单中已发货+已退款成功的子订单数量等于主订单的全部子订单数量'时，改主订单的状态为已发货。
	private void closeOrderAccordingRefundDetails(OrderEntity order,OrderDetailEntity detail){
		if( detail==null || order==null){
			logger.error("财务退款成功支付之后回调判定是否为全部商品退款完毕订单时，在数据库中找不到对应主订单或者子订单...");
			return;
		}
		if( specifyOrderDetail(detail) ){//该子订单已全部退完
			
			// 判定其他子订单！！！！！！！
			OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
			orderDetailEntity.setOrderId( order.getId() );
			List<OrderDetailEntity> detailList = orderDetailService.queryList(orderDetailEntity);
			// step2:
			boolean step2Result = upateOrderAccordingRefundDetails( order,detail,detailList );
			if( !step2Result ){// 未通过step2校验，进入step1校验！
				boolean firstResult = true;//初始值
				for(OrderDetailEntity detailVo: detailList){
					if( !detailVo.getId().equals(detail.getId() ) ){
						if(!specifyOrderDetail(detailVo)){
							firstResult =  false;// 一旦有子订单未退，则跳出循环
							break;
						}
					}
				}
				
				if(firstResult){
					//更新主订单状态为关闭
					Date curDate = new Date() ;
					OrderEntity orderEntity = new OrderEntity();
					orderEntity.setId( order.getId() );
					orderEntity.setUpdateTime( curDate ); 
					orderEntity.setStatus( OrderStatusEnum.TRADE_CLOSED.getKey() );
					orderService.update(orderEntity);
					//记录订单日志
					logService.insertOrderLog(order.getWfxOrderNo(), "system",
									OrderLogOptEnum.OPT_CLOSE_ORDER.getKey(),OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey(),
										OrderLogOptResultEnum.OPT_SUCCESS.getKey());
				}
			}
		}
		
	}
	/*该子订单是否已全部退完*/
	private boolean specifyOrderDetail(OrderDetailEntity detail){
		boolean resultFlag = false;
		// 查该子订单的退款记录
		OrderRefundEntity orderRefundEntity = new OrderRefundEntity();
		orderRefundEntity.setOrderDetailId( detail.getId() );
		 List<OrderRefundEntity> refundList = orderRefundService.queryList(orderRefundEntity);
		if( refundList== null || refundList.size()==0 )
		{
			return resultFlag;
		}else{
			String status = refundList.get(0).getStatus();
			if (  StringUtils.isEmpty( status ) || 
					 status.equals( RefundStatusEnum.APPLYING ) ||
					 	status.equals( RefundStatusEnum.PENDING_DELIVERD ) ||
					 		status.equals( RefundStatusEnum.UNDER_REFUND ) ||
					 		status.equals( RefundStatusEnum.REJECT_REFUND ) ) {// 退款非成功、非关闭状态，视为还有可退的金额
				return resultFlag;
			}else{// 退款成功、关闭状态
				
				RefundSatistics refundSatistics = new RefundSatistics();
				refundSatistics.setOrderDetailId( detail.getId() );
				refundSatistics.initSuccessStatus();
				RefundSatistics result = orderRefundService.refundSatistics(refundSatistics);
				Integer num = detail.getNum()==null?0:detail.getNum();
				Double price = detail.getPrice()==null?0.0:detail.getPrice();
				if( result!=null && ( result.getReturnFee()== price*num ) ){//该子订单已全部退完
					resultFlag = true;
					return resultFlag;
				}else{
					return resultFlag;
				}
				
			} 
		}
		
	}
	
	/**
	 * 生成退款单的负佣金
	 * @param order
	 * @param detail
	 */
	public Boolean insertCommisionDetail(OrderRefundEntity refund,OrderEntity order,OrderDetailEntity detail){
		try{
			//确认收货7天后退款成功，才生成负佣金
			Date refundTime = refund.getCreateTime();
			Date confirmTime = order.getConfirmTime();
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
			if(null != refundTime && null != confirmTime){
				//如果退款单创建时间在确认收货后7天
				Long diffTime = refundTime.getTime() - confirmTime.getTime();
				if(diffTime > dayNum*24*60*60*1000){
					return false;
				}
			}
			Double refundFee = refund.getRefundFee()==null?0:refund.getRefundFee();
			Integer refundNum = refund.getProNum()==null?0:refund.getProNum();
			//如果已生成过负的佣金,则不再生成佣金
			CommissionDetailEntity detailEntity = new CommissionDetailEntity();
			detailEntity.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
			detailEntity.setSettlementType("1");//退款结算
			List<CommissionDetailEntity> detailLIst = commissionDetailService.queryList(detailEntity);
			if(null != detailLIst && detailLIst.size() > 0){//查询数据大于0,说明已经生成负的佣金了,直接返回
				return false;
			}
			//退款金额大于0时才生成佣金
			if(refundFee > 0){
				//生成佣金数据
				CommissionDetailEntity commission = new CommissionDetailEntity();
				commission.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
				commission.setWfxOrderNo(order.getWfxOrderNo());
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
				commission.setConfirmTime(refund.getPayTime());
				commission.setSettlementType("1");
				commission.setCommodityQuantity(refundNum*-1);
				commission.setGoodsPrice(detail.getPrice());
				commission.setGoodsAmount(detail.getTotalFee());
				commission.setFreightCharges(order.getPostFee());
				commission.setStatus("0");
				Date date = new Date();
				commission.setCreateTime(date);
				commission.setUpdateTime(date);
				commission.setNextSellerId(order.getCommissionSellerId());
				//一级佣金
				Double cp1 = detail.getCommissionLevel1Percent()==null?0d:detail.getCommissionLevel1Percent();
				CommissionDetailEntity comDetail1 = new CommissionDetailEntity();
				comDetail1.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
				comDetail1.setSettlementType("0");//妥投结算
				comDetail1.setSellerId(order.getCommissionSellerId());
				List<CommissionDetailEntity> commissionList1 = commissionDetailService.queryList(comDetail1);
				SellerInfoEntity seller1Info = sellerInfoService.getSellerInfoById(order.getCommissionSellerId());
				if(cp1 > 0 && null != commissionList1 && commissionList1.size() > 0){//只有佣金比例大于0时才生成
					//一级佣金
					Double percent1 = MathUtil.roundHalfUp(cp1*100);
					Double refundAcount = MathUtil.roundHalfUp(-refundFee * cp1);
					commission.setWfxShopName(seller1Info.getShopName());
					commission.setLoginName(seller1Info.getLoginName());
					commission.setCommissionAmount(refundAcount);
					commission.setDeductRatio(percent1);
					commission.setSellerId(order.getCommissionSellerId());
					commission.setCommissionLevel("1");
					
					//保存一级佣金
					if(refundAcount < 0){
						commissionDetailService.insert(commission);
					}
				}
				String seller2Id = seller1Info.getParentId();
				SellerInfoEntity seller2Info = sellerInfoService.getSellerInfoById(seller2Id);
				if(null == seller2Info){
					return true;
				}
				//二级佣金
				Double cp2 = detail.getCommissionLevel2Percent()==null?0d:detail.getCommissionLevel2Percent();
				CommissionDetailEntity comDetail2 = new CommissionDetailEntity();
				comDetail2.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
				comDetail2.setSettlementType("0");//妥投结算
				comDetail2.setSellerId(seller2Id);
				List<CommissionDetailEntity> commissionList2 = commissionDetailService.queryList(comDetail2);
				//二级佣金
				if(cp2 > 0 && commissionList2 != null && commissionList2.size() > 0){//只有佣金比例大于0且上级分销商存在时才生成
					commission.setId(null);
					Double percent2 = MathUtil.roundHalfUp(cp2*100);
					Double refundAcount2 = MathUtil.roundHalfUp(-refundFee * cp2);
					commission.setWfxShopName(seller2Info.getShopName());
					commission.setLoginName(seller2Info.getLoginName());
					commission.setDeductRatio(percent2);
					commission.setCommissionAmount(refundAcount2);
					commission.setSellerId(seller2Id);
					commission.setCommissionLevel("2");
					//保存二级佣金
					if(refundAcount2 < 0){
						commissionDetailService.insert(commission);
					}
				}
				//三级佣金
				Double cp3 = detail.getCommissionLevel3Percent()==null?0d:detail.getCommissionLevel3Percent();
				String seller3Id = seller2Info.getParentId();
				SellerInfoEntity seller3Info = sellerInfoService.getSellerInfoById(seller3Id);
				if(null == seller3Info){
					return true;
				}
				CommissionDetailEntity comDetail3 = new CommissionDetailEntity();
				comDetail3.setWfxOrderDetailNo(detail.getWfxOrderDetailNo());
				comDetail3.setSettlementType("0");//妥投结算
				comDetail3.setSellerId(seller3Id);
				List<CommissionDetailEntity> commissionList3 = commissionDetailService.queryList(comDetail3);
				//二级佣金
				if(cp3 > 0 && commissionList3 != null && commissionList3.size() > 0){//只有佣金比例大于0且上级分销商存在时才生成
					commission.setId(null);
					Double percent3 = MathUtil.roundHalfUp(cp3*100);
					Double refundAcount3 = MathUtil.roundHalfUp(-refundFee * cp3);
					commission.setWfxShopName(seller3Info.getShopName());
					commission.setLoginName(seller3Info.getLoginName());
					commission.setDeductRatio(percent3);
					commission.setCommissionAmount(refundAcount3);
					commission.setSellerId(seller3Id);
					commission.setCommissionLevel("3");
					//保存二级佣金
					if(refundAcount3 < 0){
						commissionDetailService.insert(commission);
					}
				}
			}
			return true;
		}catch(Exception e){
			logger.info("退款单：["+refund.getRefundNo()+"]生成负的佣金出错。",e);
			return false;
		}
	}

	@Override
	@LoggerProfile(methodNote="根据子订单id号和供应商code查询退货地址")
	public String getRefundAddredd(String orderDetailId, String supplierCode) {
		String refundAddr = "";
		SupplierAddressOutputDto addressDto = orderRefundService.getBackAddress(orderDetailId, supplierCode);
		if(null != addressDto){
			refundAddr = addressDto.getProvince()+addressDto.getCity()+ addressDto.getArea()+addressDto.getAddress();
		}
		return refundAddr;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	@LoggerProfile(methodNote="审核退款")
	public String auditRefund(String operator,int manualRefundFlag,String denyReason, OrderRefundOutputDto outputDto ) {
				StringBuilder logMessage = new StringBuilder();
				OrderRefundInputDto inputDto = new OrderRefundInputDto();
				inputDto.setId( outputDto.getId() );
				inputDto.setUpdateTime( new Date() );
				FinReturnDebtInputDto finReturnDebt = new FinReturnDebtInputDto();
				FinRefundSynRes res = null;
				if( manualRefundFlag ==1 ||  manualRefundFlag ==2 ) {
					inputDto.setStatus(RefundStatusEnum.UNDER_REFUND.getKey() );
					if(  manualRefundFlag ==2 ){
						logMessage .append("确认收货->退款处理中,操作人 【");
					}else{
						logMessage.append("同意退款->退款处理中,操作人 【");
					}
					logMessage.append( operator ).append("】#退款类型：")
						.append( RefundTypeEnum.getDescByKey( outputDto.getRefundType() ) ).append("#退款金额：")
						.append(outputDto.getRefundFee()).append("元");
					
					// 调财务接口生成财务的退款单  
					finReturnDebt.setOrderNo( outputDto.getWfxOrderNo() );
					OrderDetailEntity detail = orderDetailService.getById( outputDto.getOrderDetailId() );
					if(detail!=null){
						finReturnDebt.setOrderSubNo( detail.getWfxOrderDetailNo() );// 子订单号
					}
					finReturnDebt.setBackNo( outputDto.getRefundNo() );
					finReturnDebt.setApplyDate( outputDto.getCreateTime() );
					finReturnDebt.setRefundType( outputDto.getRefundType() );
					finReturnDebt.setApplyAmount( outputDto.getRefundFee() );
					finReturnDebt.setCustomerNo( outputDto.getBuyerLoginName() );
					finReturnDebt.setOperator( operator );
					finReturnDebt.setStoreName( outputDto.getShopName() );
					finReturnDebt.setStoreId( outputDto.getShopId() );
					finReturnDebt.setApplyStatus( RefundStatusEnum.UNDER_REFUND.getKey());
					finReturnDebt.setRefundDesc( outputDto.getDescription() );
					finReturnDebt.setRefundNote( outputDto.getReason() );
					res = finRefundBackgroundApi.applyAndModifyRefund(finReturnDebt);
					if (res == null || res.getRefundSynState() == null || FinRefundSynStateEnum.STATE_SUCCESS != res.getRefundSynState()) {
					   //代表失败
				    	logger.error("调财务接口生成退款单失败.接口返回状态：",res.getRefundSynState().getDesc(),res.getStatusMsg());
				    	return "error1:"+res.getStatusMsg();
				    }
					
				}else if( manualRefundFlag ==3 ){
					inputDto.setStatus(RefundStatusEnum.REJECT_REFUND.getKey() );
					inputDto.setDenyReason(denyReason);
					logMessage .append("拒绝退款,操作人 【").append( operator  )
								.append("】#拒绝原因：").append(denyReason).append("#");
				}else{
					return "error2";
				}
				
				// 本地审核退款
				int result = update( inputDto );
				
				if( result >0 ){
					//记录售后日志
					OrderLogEntity orderLog = new OrderLogEntity();
					orderLog.setRejectedNo(outputDto.getRefundNo());
					orderLog.setLogType( OrderLogBelongTypeEnum.REFUND_LOG.getKey() );
					orderLog.setUpdateTime( new Date() );
					orderLog.setCreateTime( new Date() );
					orderLog.setOptUser( operator );
					orderLog.setLogInfo( logMessage.toString());
					orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey() );
					logService.insert(orderLog);
					return "SUCCESS";
				}else{
					return "error3";
				}
	}
}
