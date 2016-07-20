 /*
 * 版本信息
 
 * 日期 2016-03-30 16:50:25
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.aftersale.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.dto.input.OrderRefundInputDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleDto;
import com.yougou.wfx.aftersale.dto.output.OrderAfterSaleSearchDto;
import com.yougou.wfx.aftersale.dto.output.OrderRefundOutputDto;
import com.yougou.wfx.aftersale.dto.output.SupplierAddressOutputDto;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.order.model.OrderDetailEntity;

/**
 * IOrderRefundService接口
 * @author luoq
 * @Date 创建时间：2016-03-30 16:50:25
 */
public interface IOrderRefundService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(OrderRefundEntity orderRefundEntity);

	/**
	 * 获取分页数据
	 */
	public List<OrderRefundEntity> findPage(OrderRefundEntity orderRefundEntity,RowBounds rowBounds);
	
	/**
	 * 分页查询售后退款
	 * @param orderRefundEntity
	 * @param rowBounds
	 * @return
	 */
	List<OrderRefundEntity> queryAfterSalePage(OrderRefundEntity orderRefundEntity,RowBounds rowBounds);
	
	/**
	 * 分页查询售后退款
	 * @param orderRefundEntity
	 * @param rowBounds
	 * @return
	 */
	PageModel<OrderRefundEntity> getAfterSaleModel(OrderRefundEntity orderRefundEntity,PageModel pageModel);
	/**
	 * 保存单条记录
	 */
	public String insert(OrderRefundEntity orderRefundEntity);

	List<OrderRefundEntity> queryList(OrderRefundEntity orderRefundEntity);
	/**
	 * 更新记录
	 */
	public int update(OrderRefundEntity orderRefundEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public OrderRefundEntity getById(String id); 
	
	/**
	 * 通过退款编号查询数据
	 */
	public OrderRefundEntity getDetailByRefundNo(String refundNo);
	
	/* orderId - - order表主键；  prodId - product表的主键 */
	public OrderRefundEntity getRefundItem(String orderId, String prodId);
	
//	public int existsRecord(String orderId, String prodId);
	
	/**
	 * 统计子订单退款进行中或已退款的商品数量及金额
	 * @param refundSatistics
	 * @return
	 */
	public RefundSatistics refundSatistics(RefundSatistics refundSatistics);

	WFXResult<OrderRefundOutputDto> createRefundRecord(
			OrderRefundInputDto orderRefundInputDto);

	WFXResult<OrderRefundOutputDto> updateRefundRecord(
			OrderRefundInputDto orderRefundInputDto);

	public WFXResult<OrderRefundOutputDto> getRefundRecordInfo(
			String orderDetailId);

	WFXResult<Boolean> cancelRefund(OrderRefundInputDto orderRefundInputDto);
	
	/**
	 * 分页查询会员的售后退款列表
	 * @param memberId 会员id
	 * @return
	 */
	public PageModel<OrderAfterSaleDto> queryOrderAfterSalePage(String memberId,PageModel pageModel,OrderAfterSaleSearchDto search);
	/**
	 * 拒绝退款之后N天超时处理(N=7)
	 * @return
	 */
	public String timeOutCloseRefund();

	public SupplierAddressOutputDto getBackAddress(String orderDetailId,
			String supplierCode);	
	/**
	 * 查询仅退款但无发货信息的退款单
	 * @param orderDetailId 
	 * @return
	 */
	List<OrderRefundEntity> queryOnlyRefundNotInConsign(String orderDetailId);
	// 查子订单的真实发货状态
	boolean isOrderDetailDelivered(OrderDetailEntity detail);

	//查询商品行在售后成功退款的总金额
	public Double queryRefundSuccessFeeByOrderDetailId(String orderDetailId);
}
