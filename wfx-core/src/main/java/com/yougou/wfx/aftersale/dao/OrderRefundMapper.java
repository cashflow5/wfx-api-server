 /*
 * 版本信息
 
 * 日期 2016-04-01 11:08:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.aftersale.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.aftersale.dto.RefundSatistics;
import com.yougou.wfx.aftersale.model.OrderRefundEntity;

/**
 * OrderRefundMapper
 * @author luoq
 * @Date 创建时间：2016-04-01 11:08:55
 */
public interface OrderRefundMapper{
	
	int findPageCount(OrderRefundEntity orderRefundEntity);

	List<OrderRefundEntity> findPage(OrderRefundEntity orderRefundEntity,RowBounds rowBounds);
	
	List<OrderRefundEntity> queryList(OrderRefundEntity orderRefundEntity);
	
	int queryAfterSaleCount(OrderRefundEntity orderRefundEntity);

	List<OrderRefundEntity> queryAfterSalePage(OrderRefundEntity orderRefundEntity,RowBounds rowBounds);
	
	int insert(OrderRefundEntity orderRefundEntity);
	
	int insertSelective(OrderRefundEntity orderRefundEntity);
	
	int update(OrderRefundEntity orderRefundEntity);
	
	int remove(String id);
	
	OrderRefundEntity getById(@Param("id")String id);

	OrderRefundEntity getDetailByRefundNo(@Param("refundNo")String refundNo);
	/* orderId - - order表主键；  prodId - product表的主键 */
	OrderRefundEntity getRefundItem(@Param("orderId")String orderId, @Param("prodId")String prodId);

	/*OrderRefundEntity getRefundRecordInfo(String orderDetailId);*/
	/**
	 * 统计退款中或者已退款成功的商品数量及金额
	 * @param refundSatistics
	 * @return
	 */
	RefundSatistics refundSatistics(RefundSatistics refundSatistics);
	/**
	 * 查所有非完成、非关闭的售后记录
	 * @param orderDetailId
	 * @return
	 */
	OrderRefundEntity getRefundRecordHandling(String orderDetailId);
	/**
	 * 根据子订单号查所有售后记录
	 * @param orderDetailId
	 * @return
	 */
	List<OrderRefundEntity> getRefundRecordHistory(String orderDetailId);
	
	List<OrderRefundEntity> getListForTimeOutCloseRefund(@Param("day")int day,@Param("curDate")Date curDate);
			
	int timeOutCloseRefund(@Param("day")int day,@Param("curDate")Date curDate);
	
	Integer queryBackNumByOrderDetailId(@Param("orderDetailId")String orderDetailId);
	
	List<OrderRefundEntity> queryOnlyRefundNotInConsign(@Param("orderDetailId")String orderDetailId);
	
}
