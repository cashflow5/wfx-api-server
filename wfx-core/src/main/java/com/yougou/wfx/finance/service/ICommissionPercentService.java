 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.CommissionPercentEntity;
import com.yougou.wfx.finance.model.CommissionPercentLogEntity;

/**
 * ICommissionPercentService接口
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
public interface ICommissionPercentService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommissionPercentEntity commissionPercentEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommissionPercentEntity> findPage(CommissionPercentEntity commissionPercentEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommissionPercentEntity commissionPercentEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommissionPercentEntity commissionPercentEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id,String updateUser);
	
	/**
	 * 通过id查询数据
	 */
	public CommissionPercentEntity getById(String id);

	/**
     * 通过分类idList查询相应佣金比例数据
     */
	public List<CommissionPercentEntity> getByBaseCatId(List<String> list); 
	/**
     * 通过品牌编码，品牌分类id，商品id获取佣金
     */
    public CommissionPercentEntity getCommissionByCondition(String brandNo, String baseCatId, String commodityid);
    
	/**
     * 佣金比例日志分页查询
     */
	public int findPageCountLog(CommissionPercentLogEntity logEntity);
	public List<CommissionPercentLogEntity> findPageLog(CommissionPercentLogEntity logEntity,RowBounds rowBounds);

    /**
     * 批量插入数据
     */
    void batchInsert(List<CommissionPercentEntity> entityList, List<CommissionPercentLogEntity> logEntities);
}