 /*
 * 版本信息
 
 * 日期 2016-06-21 17:57:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.dto.output.CommodityCortexOutputDto;
import com.yougou.wfx.commodity.model.CommodityCortexEntity;

/**
 * ICommodityCortexService接口
 * @author wfx
 * @Date 创建时间：2016-06-21 17:57:55
 */
public interface ICommodityCortexService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityCortexEntity commodityCortexEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityCortexEntity> findPage(CommodityCortexEntity commodityCortexEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(CommodityCortexEntity commodityCortexEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityCortexEntity commodityCortexEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityCortexEntity getById(String id);

	/**
	 * 通过皮质名查询数据
	 * @param cortexName
	 * @return
	 */
	public CommodityCortexOutputDto getByName(String cortexName);

	/**
	 * 通过皮质名编码数据
	 * @param no
	 * @return
	 */
	public CommodityCortexOutputDto getByNo(String no);

	/**
	 * 更新皮质数据
	 * @return
	 */
	public int updateCommodityCortex(); 
}