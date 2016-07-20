package com.yougou.wfx.outside.api;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.StringUtil;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;
import com.yougou.wfx.aftersale.service.IOrderRefundService;
import com.yougou.wfx.common.constant.MessageConstant;
import com.yougou.wfx.enums.OrderLogBelongTypeEnum;
import com.yougou.wfx.enums.OrderLogOptBelongEnum;
import com.yougou.wfx.enums.OrderLogOptEnum;
import com.yougou.wfx.enums.OrderStatusEnum;
import com.yougou.wfx.enums.RefundTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.order.model.OrderConsignDetailEntity;
import com.yougou.wfx.order.model.OrderConsignEntity;
import com.yougou.wfx.order.model.OrderLogEntity;
import com.yougou.wfx.order.service.IOrderConsignDetailService;
import com.yougou.wfx.order.service.IOrderConsignService;
import com.yougou.wfx.order.service.IOrderLogService;
import com.yougou.wfx.outside.dao.OrderForOutSideMapper;
import com.yougou.wfx.outside.dao.SupplierAddressForOutSideMapper;
import com.yougou.wfx.outside.domain.Order;
import com.yougou.wfx.outside.domain.OrderDetail;
import com.yougou.wfx.outside.request.LogisticsSendRequest;
import com.yougou.wfx.outside.request.OrderGetRequest;
import com.yougou.wfx.outside.request.OrderUpdateRequest;
import com.yougou.wfx.outside.request.SupplierAddressRequest;
import com.yougou.wfx.outside.response.LogisticsSendResponse;
import com.yougou.wfx.outside.response.OrderGetResponse;
import com.yougou.wfx.outside.response.OrderUpdateResponse;
import com.yougou.wfx.outside.response.SupplierAddressResponse;
import com.yougou.wfx.proxy.messenger.SmsProxyApi;
import com.yougou.wfx.user.model.SupplierEntity;
import com.yougou.wfx.user.service.ISupplierService;

/**
 * <p>
 * Title: LogisticsForOutSideApiImpl
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author: zheng.qq
 * @date: 2016年4月19日
 */
@Service
public class LogisticsForOutSideApiImpl extends BaseService implements ILogisticsForOutSideApi {

	@Resource
	private IOrderConsignService orderConsignService;
	@Resource
	private IOrderConsignDetailService orderConsignDetailService;
	@Resource
	private IOrderForOutSideApi orderForOutSideApi;
	@Resource
	private OrderForOutSideMapper orderForOutSideMapper;
	@Resource
	private SupplierAddressForOutSideMapper supplierAddressForOutSideMapper;
	@Resource
	private IOrderRefundService orderRefundService;
	@Resource
	private IOrderLogService orderLogService;
	@Resource
	private ISupplierService supplierService;

	@Override
	@Transactional
	@LoggerProfile(methodNote="订单发货")
	public LogisticsSendResponse logisticsSend(@NotBlank.List(value={
			@NotBlank(target="wfxOrderNo"),@NotBlank(target="expressCode"),
			@NotBlank(target="expressName"),@NotBlank(target="expressNo"),
			@NotBlank(target="returnAddressNo"), @NotBlank(target="orderInfoList")})
			LogisticsSendRequest request)  throws Exception {
		// TODO Auto-generated method stub
		logger.info("外部平台订单发货输入参数："+request.toString());
		LogisticsSendResponse response = new LogisticsSendResponse();
		Date current = new Date();

		String expressCode = request.getExpressCode();
		String expressName = request.getExpressName();
		String expressNo = request.getExpressNo();
		String returnAddressNo = request.getReturnAddressNo();
		String wfxOrderNo = request.getWfxOrderNo();
		List<String> orderInfoList = request.getOrderInfoList();
		
		if(orderInfoList.size()<=0){
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResult(false);
			response.setResultMsg("发货关联的订单信息列表不能为空！");
			return response;
		}
		
		
		String[] receiverMobile = null;//收货人手机号
		String msgContent = "";//短信内容
		
		OrderGetRequest orderGetRequest = new OrderGetRequest();
		orderGetRequest.setWfxOrderNo(wfxOrderNo);
		OrderGetResponse orderGetResponse = orderForOutSideApi.getOrder(orderGetRequest);
		Order order = orderGetResponse.getOrder();
		if(order==null){
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResult(false);
			response.setResultMsg("找不到订单信息");
			return response;
		}
		if(order.getDetails()==null || order.getDetails().size()<1){
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResult(false);
			response.setResultMsg("找不到订单详情信息");
			return response;
		}
		//获取收货人手机号
		if(!StringUtil.isStrEmpty(order.getReceiverMobile())){
			receiverMobile = order.getReceiverMobile().split(",");
		}
		//发货信息保存
		try{
			for(String item : orderInfoList){
				boolean flag = false;
				if(StringUtil.isStrEmpty(item) || item.split(":").length != 2){
					logger.error("发货关联的订单信息格式不正确！");
					throw new RuntimeException("发货关联的订单信息格式不正确！");
				}
				String item_no = item.split(":")[0];
				String item_num = item.split(":")[1];
				int num = 0;
				if(!StringUtil.isStrEmpty(item_num)){
					try{
						int count = Integer.parseInt(item_num);
						if(count>0){
							num = count;
						}
					}catch(Exception ex){
						logger.error("发货关联的订单中的发货数量必须为数字！");
						throw new RuntimeException("发货关联的订单中的发货数量必须为数字！");
					}
					
				}
				for(OrderDetail detail : order.getDetails()){
					if(item_no.equals(detail.getWfxOrderDetailNo())){
						flag = true;
						//判断是否是“未发货且仅退款【退款申请中(APPLYING)/退款处理中(UNDER_REFUND)】”的订单,如果是则关闭相应的退款单。
						String orderDetailId = detail.getId();
						List<OrderRefundEntity> list1 = orderRefundService.queryOnlyRefundNotInConsign(orderDetailId);
						if(list1.size()>0){
							OrderRefundEntity orderRefundEntity = new OrderRefundEntity();
							orderRefundEntity.setId(list1.get(0).getId());
							orderRefundEntity.setStatus("CLOSE_REFUND");
							orderRefundEntity.setUpdateTime(current);
							orderRefundService.update(orderRefundEntity);
							//添加退款单状态修改日志
							String msg = "退款类型："+RefundTypeEnum.getDescByKey(list1.get(0).getRefundType())
									   + "退款单："+list1.get(0).getRefundNo()
									   + "因该订单已发货，系统关闭退款";
							OrderLogEntity orderLog = new OrderLogEntity();
							orderLog.setOrderNo(wfxOrderNo);
							orderLog.setOrderDetailNo(item_no);
							orderLog.setType(2);
							orderLog.setRejectedNo(list1.get(0).getRefundNo());
							orderLog.setLogType(OrderLogBelongTypeEnum.REFUND_LOG.getKey());
							orderLog.setOptType(OrderLogOptEnum.OPT_REFUND_CLOSE.getKey());
							orderLog.setUpdateTime(current);
							orderLog.setCreateTime(current);
							orderLog.setOptUser("system");
							orderLog.setLogInfo(msg);
							orderLog.setOptBelong(OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey());
							orderLog.setShowType(3);
							orderLog.setOptResult(1);
							orderLogService.insert(orderLog);
						}

						//保存物流信息
						String consignId = "";
						OrderConsignEntity orderConsignEntity = new OrderConsignEntity();
						orderConsignEntity.setExpressCode(expressCode);
						orderConsignEntity.setExpressName(expressName);
						orderConsignEntity.setExpressNo(expressNo);
						orderConsignEntity.setOrderId(order.getId());
						
						List<OrderConsignEntity> list2 = orderConsignService.queryList(orderConsignEntity);
						if(list2.size()>0){
							consignId = list2.get(0).getId();
						}else{
							orderConsignEntity.setCreateTime(current);
							orderConsignEntity.setUpdateTime(current);
							consignId = orderConsignService.insert(orderConsignEntity);
						}
						
						//若发货数量为0，则取订单中的数量；反之取发货数量。
						if(num == 0){
							num = detail.getNum();
						}
						
						//保存物流详细信息
						String prodId = detail.getProdId();
						String wfxOrderDetailNo = detail.getWfxOrderDetailNo();
						OrderConsignDetailEntity orderConsignDetailEntity = new OrderConsignDetailEntity();
						orderConsignDetailEntity.setConsignId(consignId);
						orderConsignDetailEntity.setNum(num);
						orderConsignDetailEntity.setOrderDetailId(orderDetailId);
						orderConsignDetailEntity.setProdId(prodId);
						orderConsignDetailEntity.setCreateTime(current);
						orderConsignDetailEntity.setUpdateTime(current);
						orderConsignDetailService.insert(orderConsignDetailEntity);
						
						//回写退货地址编码
						orderForOutSideMapper.updateOrderReturnAddressNo(wfxOrderDetailNo, returnAddressNo, current);
					
						break;
					}
				}
				if(!flag){
					logger.error("发货关联的订单号【"+item+"】不存在！");
					throw new RuntimeException("发货关联的订单号【"+item+"】不存在！");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("保存发货信息发生异常：" + e);
			throw new RuntimeException(e);
		}
		//订单状态修改
		try{
			//判断是否全部发货并修改订单状态-已发货/部分发货
			int c = orderConsignDetailService.queryConsignStatusByWfxOrderNo(wfxOrderNo);
			if(c == order.getDetails().size()){
				logger.error("判断订单的状态的逻辑有问题，需调整！");
				throw new RuntimeException("判断订单的状态的逻辑有问题，需调整！");
			}
			String status = "";
			if(c==0){
				status = OrderStatusEnum.DELIVERED.getKey();
			}else if(c>0 && c<order.getDetails().size()){
				status = OrderStatusEnum.PART_DELIVERED.getKey();
			}
			OrderUpdateRequest orderUpdateRequest = new OrderUpdateRequest();
			orderUpdateRequest.setWfxOrderNo(wfxOrderNo);
			orderUpdateRequest.setStatus(status);
			OrderUpdateResponse orderUpdateResponse = orderForOutSideApi.updateOrder(orderUpdateRequest);
			
			if(orderUpdateResponse.getResultCode()!=ResultCodeEnum.SUCCESS.getKey()){
				logger.error("订单状态修改失败!");
				throw new RuntimeException(orderUpdateResponse.getResultMsg());
			}else{
				String supplierName = "未知";
				if(StringUtil.isStrEmpty(order.getSupplierId())){
					supplierName = "未知";
				}else{
					SupplierEntity supplierEntity = new SupplierEntity();
					supplierEntity.setSupplierCode(order.getSupplierId());
					List<SupplierEntity> suppliers = supplierService.findPage(supplierEntity, new RowBounds());
					if(suppliers!=null && suppliers.size()>0){
						supplierName = suppliers.get(0).getSupplierName();
					}
				}
				
				//添加订单操作日志
				OrderLogEntity orderLog = new OrderLogEntity();
				orderLog.setOrderNo(wfxOrderNo);
				orderLog.setType(2);
				orderLog.setLogType(OrderLogBelongTypeEnum.ORDER_LOG.getKey());
				orderLog.setUpdateTime(current);
				orderLog.setCreateTime(current);
				orderLog.setOptUser(supplierName);
				orderLog.setOptBelong(OrderLogOptBelongEnum.OPT_BELONG_SELLER.getKey());
				orderLog.setShowType(3);
				orderLog.setOptResult(1);
				if(status.equals(OrderStatusEnum.DELIVERED.getKey())){
					orderLog.setOptType(OrderLogOptEnum.OPT_DELIVERED.getKey());
					orderLog.setLogInfo("订单已发货，物流公司【"+expressName+"】，快递单号【"+expressNo+"】");
				}else if(status.equals(OrderStatusEnum.PART_DELIVERED.getKey())){
					orderLog.setOptType(OrderLogOptEnum.OPT_PART_DELIVERED.getKey());
					orderLog.setLogInfo("订单部分发货，物流公司【"+expressName+"】，快递单号【"+expressNo+"】");
				}
				orderLogService.insert(orderLog);
	
				if(receiverMobile!=null && receiverMobile.length>0){
					//订单已发货通知
					try {
						msgContent = MessageFormat.format(MessageConstant.ORDER_DELIVERED_MESSAGE, 
								wfxOrderNo, expressName, expressNo);
						SmsProxyApi.sendDelay(receiverMobile, msgContent, "logisticsSend");
						response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
						response.setResult(true);
						response.setResultMsg("订单发货已完成，并短信通知客户！");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						logger.error("调用发货通知短信接口失败：" + e);
						response.setResultCode(ResultCodeEnum.WARN.getKey());
						response.setResult(false);
						response.setResultMsg("发货记录已保存，但调用发货通知短信接口失败！");
					}
				}else{
					logger.error("订单中没有收货人联系方式,不能发送发货通知短信！");
					response.setResultCode(ResultCodeEnum.WARN.getKey());
					response.setResultMsg("发货记录已保存，但订单中没有收货人联系方式,不能发送发货通知短信！");
				}
			}
		}catch(Exception e){
			logger.error("订单发货-订单状态修改异常：" + e);
			throw new RuntimeException(e);
		}

		return response;
	}

	@Override
	@LoggerProfile(methodNote = "查询供货商地址接口")
	public SupplierAddressResponse supplierAddressList(SupplierAddressRequest request)  throws Exception {
		SupplierAddressResponse response = new SupplierAddressResponse();
		try {
			logger.info("外部平台查询供货商地址输入参数："+request.toString());
			// 1.入参校验
			if (request.getSupplierCode() == null || request.getSupplierCode().equals("")) {
				response.setResultCode(ResultCodeEnum.WARN.getKey());
				response.setResultMsg("入参有误：供货商编号为空");
				return response;
			}

			// 2.查询数据并封装
			response.setSupplierAddressList(supplierAddressForOutSideMapper.queryList(request));
			response.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			response.setResultMsg("执行成功");
			return response;

			// 3. 异常处理
		} catch (Exception e) {
			response.setResultCode(ResultCodeEnum.FAILURE.getKey());
			response.setResultMsg("程序执行发生错误");
			logger.error("程序执行发生错误:", e);
			return response;
		}
	}

}
