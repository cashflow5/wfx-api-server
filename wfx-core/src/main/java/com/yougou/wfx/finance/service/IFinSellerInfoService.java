 /*
 * 版本信息
 
 * 日期 2016-03-28 10:58:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.model.FinSellerInfoDetailEntity;
import com.yougou.wfx.finance.model.FinSellerInfoEntity;

/**
 * IFinSellerInfoService接口
 * @author wfx
 * @Date 创建时间：2016-03-28 10:58:34
 */
public interface IFinSellerInfoService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(FinSellerInfoEntity finSellerInfoEntity);

	/**
	 * 获取分页数据
	 */
	public List<FinSellerInfoEntity> findPage(FinSellerInfoEntity finSellerInfoEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public MessageOutputDto createSeller(FinSellerInfoEntity finSellerInfoEntity);
	
	/**
	 * 更新记录
	 */
	public int update(FinSellerInfoEntity finSellerInfoEntity);
	
	/**
	 * 更新分销商信息
	 * @date 2016年7月6日
	 * @param finSellerInfoEntity
	 * @return
	 */
	public int updateSeller(FinSellerInfoEntity finSellerInfoEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过直销商id查询当前直销商相应的数据(查询提现中，已提现，可提现，账户总余额收益接口)
	 */
	public FinSellerInfoEntity getById(String id); 
	
	/**
	 * 分销商佣金收益(接口).
	 * @param finSellerInfoDto
	 * @return
	 */
	public MessageOutputDto applyCommissionIncome(final FinSellerInfoDetailEntity obj) throws Exception;
	
	/**
	 * 查询下级分销商详情(包括下级分销商的收益情况)(接口:查询下级分销商带来收益接口)
	 * @param subSellers 封装下级分销商ID
	 * @return
	 */
	public List<FinSellerInfoEntity> querySubSellers(final List<String> subSellers);
	
	/**
	 * 累计收益金额：获取已结算（不包含未结算）的佣金收益总额
	 * @param sellerId 分销商ID
	 * @return
	 */
	public Double getTotalCommissionAmount(String sellerId);
	
	/**
	 * 今日收益：当天财务系统生成该分销商的佣金收益总额（正，负佣金之和）
	 * @param sellerId 分销商ID
	 * @return
	 */
	public Double getTodayCommissionAmount(String sellerId);
	
	/**
	 * 根据条件查询所有分销商的资金汇总（账户余额，佣金收益，已提现金额，提现中金额）
	 * @date 2016年7月12日
	 * @param finSellerInfoEntity
	 * @return
	 */
	public Map<String, Object> queryAccountSummary(FinSellerInfoEntity finSellerInfoEntity);
	
}