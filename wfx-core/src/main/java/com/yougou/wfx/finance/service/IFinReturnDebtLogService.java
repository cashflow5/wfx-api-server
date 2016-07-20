 /*
 * 版本信息
 
 * 日期 2016-04-05 19:11:06
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.finance.model.FinReturnDebtLogEntity;

/**
 * IFinReturnDebtLogService接口
 * @author wfx
 * @Date 创建时间：2016-04-05 19:11:08
 */
public interface IFinReturnDebtLogService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(FinReturnDebtLogEntity finReturnDebtLogEntity);

	/**
	 * 获取分页数据
	 */
	public List<FinReturnDebtLogEntity> findPage(FinReturnDebtLogEntity finReturnDebtLogEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(FinReturnDebtLogEntity finReturnDebtLogEntity);
	
	/**
	 * 更新记录
	 */
	public int update(FinReturnDebtLogEntity finReturnDebtLogEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public FinReturnDebtLogEntity getById(String id); 
	/**
	 * 查询日志列表
	 */
	public List<FinReturnDebtLogEntity> queryDataList(FinReturnDebtLogEntity finReturnDebtLogEntity);

}