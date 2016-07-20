 /*
 * 版本信息
 
 * 日期 2016-03-25 10:43:03
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.front;

import java.util.List;
import java.util.Map;

import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.SellerLevelEnum;
import com.yougou.wfx.member.dto.output.MemberAddressOutputDto;
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
import com.yougou.wfx.order.dto.output.OrderDetailProfile;
import com.yougou.wfx.order.dto.output.OrderInfoDto;
import com.yougou.wfx.order.dto.output.OrderOutputDto;
import com.yougou.wfx.order.dto.output.OrderResultDto;

/**
 * IOrderFrontApi
 * @author wfx
 * @Date 创建时间：2016-03-25 10:43:03
 */
public interface IOrderFrontApi{
	/**
	 * 查询微分销平台支持的支付方式
	 * @return 支付方式列表。alipay:支付宝支付，wechatpay:微信支付
	 */
	 List<String> getPayInfo();
	
	/**
	 * 查询用户收货地址列表
	 * @param memberId 用户id(表tbl_wfx_member_account的id)
	 * @param defaultFlag 是否是默认收货地址。true：查询默认收货地址（如果没有收货地址或者没有设置默认收货地址，则返回空）
	 * false：查询所有收货地址
	 * null:如果传入的是空值，则默认是查询所有收货地址
	 * @return 收货地址列表
	 */
	List<MemberAddressOutputDto> getMemberAddressByMemberId(String memberId,Boolean defaultFlag);
	
	/**
	 * 根据地址id查询用户收货地址
	 * @param addressId 收货地址id
	 * @return 收货地址
	 */
	MemberAddressOutputDto getMemberAddressByAddressId(String addressId);
	
	/**
	 * 通过分销商id查询订单分页数据
	 * @param sellerId 分销商id
	 * @param pageMode 分页信息
	 * @return 订单列表
	 */
	PageModel<OrderInfoDto> queryPageOrders(String sellerId,PageModel pageMode);
	
	/**
	 * 通过主订单id查询订单详情
	 * @param orderId 主订单id
	 * @return 订单详情，包含子订单
	 */
	OrderInfoDto getOrderById(String orderId);
	
	/**
	 * 查询商品列表
	 * @param commodityList 商品编号list
	 * @return 商品基本信息list
	 */
	List<CommodityStyleOutputDto> getCommodity(List<String> commodityList);
	
	/**
	 * 预占库存接口
	 * @param commodityProductId 货品id
	 * @param num 预占库存的数量
	 * @return 预占库存的结果，true：预占库存成功,false：预占库存失败
	 */
	WFXResult<Boolean> preInventory(String commodityProductId,Integer num);
	
	/**
	 * 批量预占预占库存接口
	 * @param preList 商品id与预占库存数的list
	 * @return
	 */
	WFXResult<Boolean> batchPreInventory(Map<String,Integer> preMap);
	
	/**
	 * 创建订单接口
	 * @param info
	 */
	OrderResultDto createOrder(OrderCreateDto orderCreateDto);
	
	BatchOrderResultDto batchCreateOrder(List<OrderCreateDto> orderCreateList);
	
	/**
	 * 获取最大购买数量
	 * @return
	 */
	BuyLimitOutPutDto getBuyLimit();
	
	/**
	 * 申请退款/退货接口---------------------------------------------
	 */
	
	/**
	 * 根据用户id， 获取各订单状态订单总数
	 * @param memberId 用户ID
	 * @return OrderCountOutputDto  各订单状态订单数量（目前仅包含：代付款、退款/售后订单）
	 * @author zhang.f
	 */
	OrderCountOutputDto getMemberOrderCount(String memberId);
//	public string query
	
	/**
	 * 根据用户id获取订单列表
	 * @param memberId
	 * @param pageMode
	 * @return
	 */
	PageModel<OrderOutputDto> getOrders(String memberId,PageModel pageMode,OrderSearchDto orderSearchDto);
	
	/**
	 * 确认收货
	 * @param orderId
	 * @return
	 */
	WFXResult<Boolean> conformOrder(String orderId);
	
	/**
	 * 根据订单id取消订单
	 * @param orderId
	 * @return
	 */
	WFXResult<Boolean> cancelOrder(String orderId);
	
	/**
	 * 根据分销商id获取订单列表
	 * @param id
	 * @param pageMode
	 * @return
	 */
	PageModel<OrderOutputDto> getSellerOrders(String sellerId,PageModel pageMode,SellerOrderSearchDto sellerOrderSearchDto);
	
	/**
	 * 分销商查看订单详情接口
	 * @param orderId
	 * @return
	 */
	OrderOutputDto getSellerOrder(String orderId);
	
	/**
	 * 单条保存订单/退款单 操作日志
	 * @param orderLogDto
	 * @return id
	 * @author zhang.f
	 */
	String insertOrderLog(OrderLogInputDto orderLogDto);
	
	/**
	 * 根据订单号查询物流追踪数据
	 * @param orderId
	 * @return
	 */
	List<ConsignInfosOutPutDto> getConsignInfosByOrderId(String orderId);
	
	/**
	 * 支付回调接口
	 * @param 支付方返回数据转换成的map对象
	 * @return 
	 */
	WFXResult<AfterPayedCallBackDto> callbackAfterPayed(Map<String, String> params, int callBackType);
	
	
	/**
	 * 根据分销商ID，获取最近7日（前6天+当天）订单数量
	 * @param sellerId 分销商ID
	 * @return 订单数量
	 */
	int getSellerSevenDayOrderCount(String sellerId);
	
	/**
	 * 查询
	 * @param memberId
	 * @return
	 */
	int getOrderDayNumberByMemberId(String memberId);
	
	/**
	 * 根据分销商id及分销商等级获取订单列表
	 * @param sellerId 分销商id
	 * @param level 分销商等级 传入分销商等级枚举类，如果为空，则查询所有等级的订单
	 * @param pageMode
	 * @param search 搜索条件dto
	 * @return
	 */
	PageModel<OrderOutputDto> getSellerOrdersByLevel(String sellerId,SellerLevelEnum level,PageModel pageMode,SellerOrderSearchDto search);
	
	/**
	 * 根据分销商id获取所有预估佣金
	 * @param sellerId
	 * @return
	 */
	Double getAllPreCommission(String sellerId);
	
	/**
	 * 根据分销商id获取今日预估佣金
	 * @param sellerId
	 * @return
	 */
	Double getTodayCommission(String sellerId);
	
	/**
	 * 查询会员订单是否为首单支付自动升级优粉的信息，若不是，返回为null，若是，返回（shopId、shopCode）
	 * @param 用户Id
	 * @param 订单号
	 * @return "shopId":shopId ， "shopCode":shopCode
	 */
	Map<String,String> queryIfAutoTransfer(String memberId,String wfxOrderNo);
	
	/**
	 * 根据订单的用户id和登录用户id查询登录用户的分销商等级
	 * @param orderSellerId
	 * @param loginSellerId
	 * @return
	 */
	int getSellerLevel(String orderSellerId,String loginSellerId);
	

	PageModel<CommissionOrderOutputDto> findCommissionOrderPage(
			String sellerId, PageModel pageMode);

	OrderOutputDto getOrderByWFXOrderNo(String wfxOrderNo);
	
	/**
	 * 提供给微信的接口-获取订单的佣金信息
	 */
	OrderOutputDto getCommiOrderById(String orderId);
	/**
	 * 设置子订单退款状态
	 * @param orderId
	 * @param orderDetailId
	 * @return OrderDetailProfile.RefudnShowStatus '3'-退款成功;'2'-退款中;"1"-有退款；(主订单交易关闭、确认收货的时间在7天前，不显示退款相关信息；)
	 */
	OrderDetailProfile setOrderDetails(String orderId, String orderDetailId);

	Double getRealCommission(String wfxOrderDetailNo, String level);
}

