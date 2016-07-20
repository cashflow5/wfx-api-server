 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.CommissionDetailEntity;

/**
 * ICommissionDetailService接口
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
public interface ICommissionDetailService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommissionDetailEntity commissionDetailEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommissionDetailEntity> findPage(CommissionDetailEntity commissionDetailEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommissionDetailEntity commissionDetailEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommissionDetailEntity commissionDetailEntity);
	
	/**
	 * 通过id查询数据
	 */
	public CommissionDetailEntity getById(String id);

	/**
     * 汇总数据查询
     */
	public Map<String, Object> findCommissionCollect(CommissionDetailEntity commissionDetailEntity);
    
	/**
     * 获取佣金明细列表
     */
	public List<CommissionDetailEntity> queryList(CommissionDetailEntity commissionDetailEntity);

	/**
     * 佣金明细结算-调度
	 * @param commissionDetailEntity 
	 * @throws Exception 
     */
	public void dispatchCreateAccountBalance(CommissionDetailEntity commissionDetailEntity) throws Exception;

	/**
     * 查询未结算佣金
     */
    public List<CommissionDetailEntity> queryUnSettleCommission();

    /**
     * H5展示佣金明细列表，需要根据订单维度展示，所以分组查询
     * @param map
     * @return
     */
    public int findPageCountGroup(Map<String, String> map);
    public List<CommissionDetailEntity> findPageGroup(Map<String, String> map, RowBounds rowBounds);

}