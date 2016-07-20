 /*
 * 版本信息
 
 * 日期 2016-03-28 17:15:56
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinAlreadyIncomeEntity;

/**
 * IFinAlreadyIncomeService接口
 * @author he.xx
 * @Date 创建时间：2016-03-28 17:15:58
 */
public interface IFinAlreadyIncomeService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(FinAlreadyIncomeEntity finAlreadyIncomeEntity);

	/**
	 * 获取分页数据
	 */
	public List<FinAlreadyIncomeEntity> findPage(FinAlreadyIncomeEntity finAlreadyIncomeEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(FinAlreadyIncomeEntity finAlreadyIncomeEntity);
	
	/**
	 * 更新记录
	 */
	public int update(FinAlreadyIncomeEntity finAlreadyIncomeEntity);
	
	/**
	 * 通过id查询数据
	 */
	public FinAlreadyIncomeEntity getById(String id); 
	
	/**
	 * 查询收款数据列表
	 * @param finAlreadyIncome
	 * @return
	 */
	public List<FinAlreadyIncomeEntity> queryDataList(FinAlreadyIncomeEntity finAlreadyIncomeEntity);
	
	public int queryDataListCount(FinAlreadyIncomeEntity finAlreadyIncomeEntity);
	
	/**
	 * 查询收款信息
	 * @param finAlreadyIncomeEntity
	 * @return
	 */
	FinAlreadyIncomeEntity queryAlreadyIncome(FinAlreadyIncomeEntity finAlreadyIncomeEntity);
	
}