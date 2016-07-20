 /*
 * 版本信息
 
 * 日期 2016-03-30 16:50:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wfx.aftersale.dao.OrderRefundMapper;
import com.yougou.wfx.aftersale.dao.SupplierAddressMapper;
import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleSearchDto;
import com.yougou.wfx.aftersale.dto.output.OrderRefundOutputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;
import com.yougou.wfx.aftersale.model.SupplierAddressEntity;
import com.yougou.wfx.aftersale.service.IOrderRefundService;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.model.CommodityProductEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.ICommodityProductService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.OrderLogBelongTypeEnum;
import com.yougou.wfx.enums.OrderLogOptBelongEnum;
import com.yougou.wfx.enums.OrderStatusEnum;
import com.yougou.wfx.enums.RefundStatusEnum;
import com.yougou.wfx.enums.RefundTypeEnum;
import com.yougou.wfx.enums.RefundWhenEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.member.model.MemberAccountEntity;
import com.yougou.wfx.member.service.IMemberAccountService;
import com.yougou.wfx.order.dao.OrderConsignDetailMapper;
import com.yougou.wfx.order.dao.OrderConsignMapper;
import com.yougou.wfx.order.dao.OrderDetailMapper;
import com.yougou.wfx.order.dao.OrderMapper;
import com.yougou.wfx.order.model.OrderConsignDetailEntity;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.model.OrderDetailEntity;
import com.yougou.wfx.order.model.OrderEntity;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderLogService;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.BizNoGen;

/**
 * IOrderRefundService实现
 * @author luoq
 * @Date 创建时间：2016-03-30 16:50:25
 */
@Service
public class OrderRefundServiceImpl extends BaseService implements IOrderRefundService {
	
	Logger logger = LoggerFactory.getLogger( OrderRefundServiceImpl.class );
	
	@Resource
	private OrderRefundMapper orderRefundMapper;
	@Resource
	private OrderMapper orderMapper;
	@Resource
	private SupplierAddressMapper supplierAddressMapper;
	@Resource
	IOrderLogService logService;
	@Resource
	IMemberAccountService memberAccountService;
	@Resource
	ICommodityProductService commodityProductService;
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource
	OrderDetailMapper orderDetailMapper;
	@Resource
	OrderConsignMapper orderConsignMapper;
	@Resource
	OrderConsignDetailMapper orderConsignDetailMapper;
	@Resource
	ISysConfigService sysConfigService;
	
	@Override
	public int findPageCount(OrderRefundEntity orderRefundEntity){
		return orderRefundMapper.findPageCount(orderRefundEntity);
	}

	@Override
	public List<OrderRefundEntity> findPage(OrderRefundEntity orderRefundEntity,RowBounds rowBounds){
		return orderRefundMapper.findPage(orderRefundEntity,rowBounds);
	}

	@Override
	public List<OrderRefundEntity> queryAfterSalePage(
			OrderRefundEntity orderRefundEntity, RowBounds rowBounds) {
		return orderRefundMapper.findPage(orderRefundEntity,rowBounds);
	}
	
	@Override
	public PageModel<OrderRefundEntity> getAfterSaleModel(OrderRefundEntity orderRefundEntity,PageModel pageModel){
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int toltalCount = orderRefundMapper.queryAfterSaleCount(orderRefundEntity);
		List<OrderRefundEntity> refundList = orderRefundMapper.queryAfterSalePage(orderRefundEntity, rowBounds);
		return new PageModel<OrderRefundEntity>(pageModel.getPage(),pageModel.getLimit(),toltalCount,refundList);
	}
	
	@Override
	@Transactional
	public String insert(OrderRefundEntity orderRefundEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		String refundNo = "";
		try {
			refundNo = BizNoGen.getRefundNo();
		} catch (Exception e) {
			logger.error("生成退款编号发生异常",e);
			return null;
		}
		orderRefundEntity.setId(strId);
		orderRefundEntity.setRefundNo(refundNo);
		orderRefundEntity.setSupplierName( ApiConstant.SUPPLIER_YOUGOU );
		orderRefundMapper.insertSelective(orderRefundEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(OrderRefundEntity orderRefundEntity){
		int result =  orderRefundMapper.update(orderRefundEntity);
		return result;
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return orderRefundMapper.remove(id);
	}
	
	@Override
	public OrderRefundEntity getById(String id){
		return orderRefundMapper.getById(id);
	}

	@Override
	public OrderRefundEntity getDetailByRefundNo(String refundNo) {
		return orderRefundMapper.getDetailByRefundNo(refundNo);
	}

	@Override
	public OrderRefundEntity getRefundItem(String orderId, String prodId) {
		return orderRefundMapper.getRefundItem(orderId, prodId);
	}

	@Override
	public RefundSatistics refundSatistics(RefundSatistics refundSatistics){
		return orderRefundMapper.refundSatistics(refundSatistics);
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public WFXResult<OrderRefundOutputDto> createRefundRecord( OrderRefundInputDto orderRefundInputDto ) {
		WFXResult<OrderRefundOutputDto> rs = new  WFXResult<OrderRefundOutputDto>();
		Boolean idAdminOperate = orderRefundInputDto.getIsAdminOperate();// 是否系统理员操作退款
		OrderRefundEntity orderRefundEntity =  (OrderRefundEntity) BeanUtil.convertBean(orderRefundInputDto,OrderRefundEntity.class);	
		orderRefundEntity.setUpdateTime( new Date() );
		orderRefundEntity.setCreateTime( new Date());
		// 查得orderDetailId
		String orderDetailId = orderRefundInputDto.getOrderDetailId();
		OrderDetailEntity detail =	orderDetailMapper.getById(orderDetailId );
		OrderEntity order = orderMapper.getByOrderDetailId(orderDetailId);
		StringBuffer msg = new StringBuffer();
		if( detail!=null && order!=null ){
			if(null == order.getPayTime()){
				rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
				msg.append("该订单未付款，不能申请退款。");
				rs.setResultMsg(msg.toString());
				msg.append(" 该子订单ID是").append(orderDetailId);
				logger.error(msg.toString());
				return rs;
			}
			
			// # 金额校验、可退数量校验 #
			Double refundFee = orderRefundEntity.getRefundFee();
			Integer proNum = orderRefundEntity.getProNum();
			if( null==proNum ){
				proNum = 0;
			}
			OrderRefundOutputDto tempDto = queryCanReturn(detail);
			if( refundFee==null || tempDto==null ||
					refundFee > tempDto.getCanReturnFee() || proNum>tempDto.getCanReturnNum() ){
				rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
				msg.append("该订单未通过可退金额、可退数量校验，申请退款失败。");
				if(tempDto!=null){
					msg.append("可退金额:").append(tempDto.getCanReturnFee() )
						.append(" 可退数量:").append(tempDto.getCanReturnNum() );
				}
				rs.setResultMsg(msg.toString());
				msg.append(" 该子订单ID是").append( orderDetailId );
				logger.error(msg.toString() );
				return rs;
			}
			
			// 进入方法主体
			orderRefundEntity.setOrderId(detail.getOrderId());
			orderRefundEntity.setProdId(detail.getProdId());
			orderRefundEntity.setProdName(detail.getProdName());
			orderRefundEntity.setShopId(detail.getShopId());
			orderRefundEntity.setShopName(detail.getShopName());
			orderRefundEntity.setShopCode(order.getShopCode());
			orderRefundEntity.setSellerId(detail.getSellerId());
			orderRefundEntity.setProdName(detail.getProdName());
			orderRefundEntity.setBuyNum(detail.getNum());
//			orderRefundEntity.setTotalFee( detail.getTotalFee() );
			orderRefundEntity.setBuyerLoginName( order.getBuyerAccount() );	//设置买家账号
			//售中、售后
			String orderStatus = order.getStatus();
			if(StringUtils.isEmpty(orderStatus) ){
				rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
				msg.append("创建退款单失败!获取订单状态异常，订单状态为空。");
				rs.setResultMsg(msg.toString());
				msg.append(" 该子订单ID是").append( orderDetailId );
				logger.error(msg.toString() );
				return rs;
			}else if( orderStatus.equals( OrderStatusEnum.TRADE_SUCCESS.getKey() )||orderStatus.equals( OrderStatusEnum.TRADE_CLOSED.getKey() )){
				orderRefundEntity.setIsAfterReceived( RefundWhenEnum.AFTER_DELIVERD_REFUND.getKey() );
			}else{
				orderRefundEntity.setIsAfterReceived( RefundWhenEnum.BEFORE_DELIVERD_REFUND.getKey() );
			}
			
		}else{
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			msg.append("创建退款单失败!请确认输入参数是否有效：orderDetailId=").append( orderDetailId );
			rs.setResultMsg(msg.toString());
			logger.error(msg.toString() );
			return rs;
		}
		// 初始化退款状态 // 是否返货
		String refundType = orderRefundEntity.getRefundType();
		if( StringUtils.isNoneEmpty(refundType) && !RefundTypeEnum.REJECTED_REFUND.getKey().equalsIgnoreCase( refundType )){
			orderRefundEntity.setStatus( RefundStatusEnum.APPLYING.getKey() );
			orderRefundEntity.setHasGoodReturn("false");
		}else if( StringUtils.isNoneEmpty(refundType) && RefundTypeEnum.REJECTED_REFUND.getKey().equalsIgnoreCase( refundType )){
			orderRefundEntity.setStatus( RefundStatusEnum.PENDING_DELIVERD.getKey() );
			orderRefundEntity.setHasGoodReturn("true");
		}else{
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			msg.append("创建退款单失败!退款类型无效，请确认：refundType =").append( refundType );
			rs.setResultMsg(msg.toString());
			logger.error(msg.toString());
			return rs;
		}
		
		String strId = insert(orderRefundEntity);
		logger.info("result is refundID ： "+strId);
		if( StringUtils.isNoneEmpty(strId) ){
			
			//记录售后日志
			OrderLogEntity orderLog = new OrderLogEntity();
			orderLog.setRejectedNo(orderRefundEntity.getRefundNo());
			orderLog.setLogType( OrderLogBelongTypeEnum.REFUND_LOG.getKey() );
			Date curDate =  new Date();
			orderLog.setUpdateTime( curDate );
			orderLog.setCreateTime( curDate );
			String operator = orderRefundInputDto.getBuyerLoginName();//约定的操作人
			orderLog.setOptUser( operator );
			StringBuilder logMessage = new StringBuilder();
			if( null!=idAdminOperate && idAdminOperate){
				logMessage.append("卖家(");
				orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey() );
			}else{
				logMessage.append("买家(");
				orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey() );
			}
			logMessage.append(operator).append(")于").append( DateUtil.formatDateByFormat(curDate, Constant.DATE_FORMAT_NORMAL) )
					  .append("创建了退款申请#退款类型：").append( RefundTypeEnum.getDescByKey( refundType ) )
					  .append("#退款金额：").append(orderRefundEntity.getRefundFee()).append("元#退款原因：").append(orderRefundEntity.getReason())
					  .append("#退款说明：").append( orderRefundEntity.getDescription() );
			orderLog.setLogInfo( logMessage.toString());
			
			logService.insert(orderLog);
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult( (OrderRefundOutputDto)BeanUtil.convertBean(orderRefundEntity,OrderRefundOutputDto.class) );	
		}else{
			msg .append("退款单数据插入数据库失败！子订单ID：").append( orderDetailId );
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			rs.setResultMsg(msg.toString());
			logger.error(msg.toString());
		}
		return rs;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public WFXResult<OrderRefundOutputDto> updateRefundRecord(OrderRefundInputDto orderRefundInputDto) {
		 WFXResult<OrderRefundOutputDto> rs = new  WFXResult<OrderRefundOutputDto>();
		 StringBuffer msg = new StringBuffer();
		// 初始化退款状态  
		String refundType = orderRefundInputDto.getRefundType();
		OrderRefundEntity entityBeforeUpdated = getById(orderRefundInputDto.getId());
		if( null==entityBeforeUpdated ){// 
			msg.append("退款单修改失败！数据库未找到该退款单，主键：").append(orderRefundInputDto.getId() );
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			rs.setResultMsg(msg.toString());
			logger.error(msg.toString());
			return rs;
		}
		
		OrderEntity order = orderMapper.getById( entityBeforeUpdated.getOrderId() );
		OrderDetailEntity detail = orderDetailMapper.getById( entityBeforeUpdated.getOrderDetailId() );
		
		OrderRefundEntity orderRefundEntity =  (OrderRefundEntity) BeanUtil.convertBean(orderRefundInputDto,OrderRefundEntity.class);
		
		if( StringUtils.isNotEmpty(refundType) && ( RefundTypeEnum.ONLY_REFUND.getKey().equalsIgnoreCase( refundType )
				|| RefundTypeEnum.DELIVERD_REFUND.getKey().equalsIgnoreCase( refundType ) ) ){
			orderRefundEntity.setStatus( RefundStatusEnum.APPLYING.getKey() );
		}else if( StringUtils.isNotEmpty(refundType) && RefundTypeEnum.REJECTED_REFUND.getKey().equalsIgnoreCase( refundType )){
			orderRefundEntity.setStatus( RefundStatusEnum.PENDING_DELIVERD.getKey() );
		}else{
			msg.append("退款单修改失败！退款类型无效，退款类型：").append(refundType );
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			rs.setResultMsg(msg.toString());
			logger.error(msg.toString());
			return rs;
		}

		// # 金额校验、可退数量校验 #
		Double refundFee = orderRefundEntity.getRefundFee();// not null
		Integer proNum = orderRefundEntity.getProNum();
		if( null==proNum ){
			proNum = 0;
		}
		String refundStatus = entityBeforeUpdated.getStatus();
		OrderRefundOutputDto tempDto = queryCanReturn(detail);
		//下面这四种状态的退款单已经计算过一次已退金额，所以在校验可退金额和可退数量时，退款金额及数量要减掉
		Double canRefundFee = tempDto.getCanReturnFee();
		Integer canRefundNum = tempDto.getCanReturnNum();
		if(null != refundStatus && (
				refundStatus.equals(RefundStatusEnum.APPLYING.getKey()) ||
				refundStatus.equals(RefundStatusEnum.PENDING_DELIVERD.getKey()) ||
				refundStatus.equals(RefundStatusEnum.SUCCESS_REFUND.getKey()) ||
				refundStatus.equals(RefundStatusEnum.UNDER_REFUND.getKey()))){
			Double oldRefundFee = entityBeforeUpdated.getRefundFee()==null?0d:entityBeforeUpdated.getRefundFee();
			Integer oldProNum = entityBeforeUpdated.getProNum()==null?0:entityBeforeUpdated.getProNum();
			canRefundFee += oldRefundFee;
			canRefundNum += oldProNum;
		}
		//修改时需要把原来的退款金额计算进去
		if( refundFee==null || tempDto==null ||
				refundFee>canRefundFee || proNum>canRefundNum){
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			msg.append("该订单未通过可退金额、可退数量校验，修改申请退款失败。");
			if(tempDto!=null){
				if(null == refundFee){
					refundFee = 0d;
				}
				msg.append("可退金额:").append(canRefundFee)
					.append(" 可退数量:").append(canRefundNum);
			}
			rs.setResultMsg(msg.toString());
			msg.append(" 该子订单ID是").append( entityBeforeUpdated.getOrderDetailId() );
			logger.error(msg.toString() );
			return rs;
		}
				
		// 售中/售后 更新
		orderRefundEntity.setUpdateTime( new Date() );
		if( order!=null ){
			String orderStatus = order.getStatus();
			if( orderStatus.equals( OrderStatusEnum.TRADE_SUCCESS.getKey() )){
				orderRefundEntity.setIsAfterReceived(RefundWhenEnum.AFTER_DELIVERD_REFUND.getKey());
			}else{
				orderRefundEntity.setIsAfterReceived(RefundWhenEnum.BEFORE_DELIVERD_REFUND.getKey());
			}
		}
	
		int rowNum = update(orderRefundEntity);
		
		if( rowNum>0 ){
			
			orderRefundEntity = getById(orderRefundEntity.getId());
			rs.setResult((OrderRefundOutputDto)BeanUtil.convertBean(orderRefundEntity,OrderRefundOutputDto.class));
			rs.setResultCode( ResultCodeEnum.SUCCESS.getKey());
			logger.info("退款单修改成功，修改后的退款单dto： "+orderRefundEntity.toString());
			
			//记录售后日志
			OrderLogEntity orderLog = new OrderLogEntity();
			orderLog.setRejectedNo(orderRefundEntity.getRefundNo());
			orderLog.setLogType( OrderLogBelongTypeEnum.REFUND_LOG.getKey() );
			Date curDate =  new Date();
			orderLog.setUpdateTime( curDate );
			orderLog.setCreateTime( curDate );
			String operator = orderRefundEntity.getBuyerLoginName();
			orderLog.setOptUser( operator );
			StringBuilder logMessage = new StringBuilder("买家(").append(operator).append(")于")
					.append( DateUtil.formatDateByFormat(curDate, Constant.DATE_FORMAT_NORMAL) ).append("退款申请修改#退款类型：").append( RefundTypeEnum.getDescByKey( refundType ) )
					.append("#退款金额：").append(orderRefundEntity.getRefundFee()).append("元#退款原因：").append(orderRefundEntity.getReason())
					.append("#退款说明：").append( orderRefundEntity.getDescription() );
			orderLog.setLogInfo( logMessage.toString());
			orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey() );
			logService.insert(orderLog);
		}else{
			msg.append("退款单修改失败！数据库未定位到退款记录，退款单ID：").append( orderRefundEntity.getId() );
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey());
			rs.setResultMsg( msg.toString() );
			logger.error( msg.toString() );
		}
		return rs;
	}

	@Override
	public WFXResult<OrderRefundOutputDto> getRefundRecordInfo(String orderDetailId) {
		 WFXResult<OrderRefundOutputDto> rs = new  WFXResult<OrderRefundOutputDto>();
		 // 为了获取多次退款中的最新退款记录，查所有售后记录按创建时间倒序排列
		 List<OrderRefundEntity> list = orderRefundMapper.getRefundRecordHistory(orderDetailId);
		 OrderRefundOutputDto refundDto = new OrderRefundOutputDto();
		 if( null!= list && 0<list.size()){
			 refundDto = (OrderRefundOutputDto)BeanUtil.convertBean(list.get(0),OrderRefundOutputDto.class);// 最新退款记录
			 rs.setResultCode( 1 );
			 rs.setResultMsg("找到该订单商品的退款记录DTO.");
		 }else{
			 rs.setResultCode( 2 );
			 rs.setResultMsg("未找到该订单商品的退款记录，返回可退金额和可退数量信息。");
		 }
		
		 // 查子订单详情
		 OrderDetailEntity detail = orderDetailMapper.getById(orderDetailId);
		 if( detail==null ){
			 rs.setResultCode( 3 );
			 rs.setResultMsg("未找到该子订单。");
			 return rs;
		 }
		 //查已退数量已退金额
		 OrderRefundOutputDto tempDto = queryCanReturn(detail);
		refundDto.setCanReturnNum( tempDto.getCanReturnNum() );
		refundDto.setCanReturnFee( tempDto.getCanReturnFee() );
		
		// 查子订单的真实发货状态
		OrderConsignEntity orderConsignEntity = new OrderConsignEntity();
		orderConsignEntity.setOrderId( detail.getOrderId() );
		List<OrderConsignEntity> orderConsignList = orderConsignMapper.queryList(orderConsignEntity);
		if( null==orderConsignList || orderConsignList.size()==0 ){
			refundDto.setDelivered(false);
		}else{
			boolean result = isOrderDetailDelivered(detail);
			refundDto.setDelivered(result);
		}
		
		rs.setResult(refundDto);
		logger.info("根据子订单ID:("+orderDetailId+")查得退款详情的结果：",refundDto.toString());
		return rs;
	}
	
	// 查子订单的真实发货状态
	@Override
	public boolean isOrderDetailDelivered(OrderDetailEntity detail){
		boolean result = false;
		OrderConsignDetailEntity orderConsignDetailEntity = new OrderConsignDetailEntity();
		orderConsignDetailEntity.setOrderDetailId( detail.getId() );
		List<OrderConsignDetailEntity> orderConsignDetailList = orderConsignDetailMapper.queryList(orderConsignDetailEntity);
		if( null==orderConsignDetailList || orderConsignDetailList.size()==0 ){
			result=false;
		}else{
			result=true;
		}
		return result;
	}
	
	@Override
	public WFXResult<Boolean> cancelRefund(
			OrderRefundInputDto orderRefundInputDto) {
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		OrderRefundEntity orderRefundEntity = new OrderRefundEntity();
		orderRefundEntity.setRefundNo( orderRefundInputDto.getRefundNo() );
		orderRefundEntity.setUpdateTime( new Date() );
		orderRefundEntity.setDenyReason("买家取消申请");
		orderRefundEntity.setStatus( RefundStatusEnum.CLOSE_REFUND.getKey()  );
		int rowNum = update(orderRefundEntity);
		logger.info("refect rowNum is "+rowNum);
		if( rowNum>0 ){
			
			//记录售后日志
			OrderLogEntity orderLog = new OrderLogEntity();
			orderLog.setRejectedNo(orderRefundInputDto.getRefundNo());
			orderLog.setLogType( OrderLogBelongTypeEnum.REFUND_LOG.getKey() );
			Date curDate =  new Date();
			orderLog.setUpdateTime( curDate );
			orderLog.setCreateTime( curDate );
			String operator = orderRefundInputDto.getBuyerLoginName();
			orderLog.setOptUser( operator );
			StringBuilder logMessage = new StringBuilder("买家(").append(operator).append(")于")
					.append( DateUtil.formatDateByFormat(curDate, Constant.DATE_FORMAT_NORMAL) ).append("取消退款");
			orderLog.setLogInfo( logMessage.toString());
			orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_BUYER.getKey() );
			logService.insert(orderLog);
			
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(true);
			rs.setResultMsg("取消退款成功！");
		}else{
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(false);
			rs.setResultMsg("取消退款失败：未定位到该退款记录！");
		}
		return rs;
	}

	@Override
	public PageModel<OrderAfterSaleDto> queryOrderAfterSalePage(String memberId,PageModel pageModel,OrderAfterSaleSearchDto search) {
		MemberAccountEntity accountEntity = memberAccountService.getById(memberId);
		if(null == accountEntity){
			logger.info("不存在memberId为：["+memberId+"]的会员");
			return null;
		}
		if(null == search){
			search = new OrderAfterSaleSearchDto();
		}
		OrderRefundEntity refundEntity = new OrderRefundEntity();
		refundEntity.setBuyerId(memberId);// updated on 20160701
		refundEntity.setStatus(search.getRefundStatus());
		PageModel<OrderRefundEntity> refundModel = this.getAfterSaleModel(refundEntity, pageModel);
		PageModel<OrderAfterSaleDto> afterSalePage = null;
		List<OrderRefundEntity> refundList = refundModel.getItems();
		if(null != refundList && refundList.size() > 0){
			afterSalePage = new PageModel<OrderAfterSaleDto>(pageModel.getPage(),pageModel.getLimit(),pageModel.getTotalCount());
			List<OrderAfterSaleDto> afterSaleList = new ArrayList<OrderAfterSaleDto>();
			OrderAfterSaleDto afterSaleDto = null;
			for(OrderRefundEntity tempRefund:refundList){
				afterSaleDto = new OrderAfterSaleDto();
				afterSaleDto.setShopId(tempRefund.getShopId());
				afterSaleDto.setShopName(tempRefund.getShopName());
				afterSaleDto.setShopCode(tempRefund.getShopCode());
				afterSaleDto.setStatus(tempRefund.getStatus());
				afterSaleDto.setProdId(tempRefund.getProdId());
				afterSaleDto.setOrderId(tempRefund.getOrderId());
				afterSaleDto.setOrderDetailId(tempRefund.getOrderDetailId());
				afterSaleDto.setRefundId(tempRefund.getId());
				afterSaleDto.setRefundNo(tempRefund.getRefundNo());
				//查询商品的小图
				CommodityProductEntity product = commodityProductService.getById(tempRefund.getProdId());
				if(null != product && null != product.getId()){
					CommodityStyleEntity style = commodityStyleService.getById(product.getCommodityId());
					if(null != style){
						afterSaleDto.setSmallPic(style.getPicSmall());
					}
				}
				afterSaleDto.setProdName(tempRefund.getProdName());
				OrderDetailEntity detailEntity = orderDetailMapper.getById(tempRefund.getOrderDetailId());
				if(null != detailEntity){
					afterSaleDto.setProdSpec(detailEntity.getProdSpec());
				}
				afterSaleDto.setTradeFee(tempRefund.getTotalFee());
				afterSaleDto.setRefundFee(tempRefund.getRefundFee());
				afterSaleList.add(afterSaleDto);
			}
			afterSalePage.setItems(afterSaleList);
		}
		return afterSalePage;
	}

	@Override
	public String timeOutCloseRefund() {
		String dayString = sysConfigService.getValueBykey(Constant.KEY_TIMEOUT_CLOSEREFUND_DAYS);
		Date curDate = new Date();
		int day = 7;
		if( StringUtils.isNotEmpty(dayString) ){
			day = Integer.parseInt(dayString.trim());
		}
		List<OrderRefundEntity> list = orderRefundMapper.getListForTimeOutCloseRefund(day, curDate);
		int rowNum = 0 ;
		if( list!=null && list.size()>0){
			int selectNum = list.size();
			
			rowNum = orderRefundMapper.timeOutCloseRefund( day,curDate);
			//
			if( selectNum!=rowNum ){
				logger.error("调度timeOutCloseRefund发生异常：记录日志的退款单数目("+selectNum+") 与 实际关闭的退款单数目("+rowNum+")不符！");
				return "PleaseCheckLog";
			}
			// 记录操作日志
			for(OrderRefundEntity orderRefundEntity:list){
				StringBuilder msg = new StringBuilder();
				msg.append("退款类型：").append( RefundTypeEnum.getDescByKey( orderRefundEntity.getRefundType() ) )
					.append("#退款单：").append(orderRefundEntity.getRefundNo()).append(" 超时未修改退款单，系统关闭退款");
				//记录售后日志
				OrderLogEntity orderLog = new OrderLogEntity();
				orderLog.setRejectedNo(orderRefundEntity.getRefundNo());
				orderLog.setLogType( OrderLogBelongTypeEnum.REFUND_LOG.getKey() );
				orderLog.setUpdateTime( curDate );
				orderLog.setCreateTime( curDate );
				orderLog.setOptUser( "system" );
				orderLog.setLogInfo( msg.toString());
				orderLog.setOptBelong( OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey() );
				logService.insert(orderLog);
			}
		}
		logger.info("调度执行完毕："+rowNum+"条记录符合退款拒绝超过"+day+"天的条件自动关闭！");
		
		return "success:"+rowNum ;
	}

	/* 查可退数量可退金额*/
	private OrderRefundOutputDto queryCanReturn(OrderDetailEntity detail ){
		OrderRefundOutputDto refundDto = new OrderRefundOutputDto();
		 //查已退数量已退金额
		 RefundSatistics sat =  new RefundSatistics();
		 sat.setOrderDetailId(detail.getId());
		 sat.initDefaultStatus();
		 RefundSatistics refundSatistics = refundSatistics(sat);
		 Integer returnNum = 0;
		 Double returnFee = 0.0;
		 if(null != refundSatistics){
			returnNum = refundSatistics.getReturnNum()==null?0:refundSatistics.getReturnNum();
			returnFee = refundSatistics.getReturnFee()==null?0:refundSatistics.getReturnFee();
		 }
		Integer num = detail.getNum()==null?0:detail.getNum();
		Double price = detail.getPrice()==null?0.0:detail.getPrice();
		refundDto.setCanReturnNum(num - returnNum);
		refundDto.setCanReturnFee(price*num - returnFee);
		return refundDto;
	}

	@Override
	public SupplierAddressOutputDto getBackAddress(String orderDetailId,
			String supplierCode) {
		
		SupplierAddressEntity  entity1 = supplierAddressMapper.queryByOrderDetailId(orderDetailId);
		if( entity1!=null ){
			return (SupplierAddressOutputDto) BeanUtil.convertBean( entity1, SupplierAddressOutputDto.class );
		}else{
			logger.info(" 根据子订单ID("+ orderDetailId+")未找到该商品的退货地址，接着会根据供应商编码来查退款地址。");
			SupplierAddressEntity  entity2 = supplierAddressMapper.queryBySupplierCode(supplierCode);
			if( entity2!=null ){
				return (SupplierAddressOutputDto) BeanUtil.convertBean( entity2, SupplierAddressOutputDto.class );
			}else{
				return null;
			}
		}
	}

	@Override
	public List<OrderRefundEntity> queryList(OrderRefundEntity orderRefundEntity) {
		return orderRefundMapper.queryList(orderRefundEntity);
	}

	@Override
	public List<OrderRefundEntity> queryOnlyRefundNotInConsign(
			String orderDetailId) {
		return orderRefundMapper.queryOnlyRefundNotInConsign(orderDetailId);
	}

	@Override
	public Double queryRefundSuccessFeeByOrderDetailId(String orderDetailId) {
		RefundSatistics refundSatistics = new RefundSatistics();
		refundSatistics.setOrderDetailId( orderDetailId );
		refundSatistics.initSuccessStatus();
		RefundSatistics result = refundSatistics(refundSatistics);
		if( null!=result && result.getReturnFee()>0){
			return result.getReturnFee();
		}else{
			return 0d;
		}
	}

}
