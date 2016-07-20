 /*
 * 版本信息
 
 * 日期 2016-05-09 17:09:12
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.model.CommodityPicsEntity;

/**
 * ICommodityPicsService接口
 * @author wzf
 * @Date 创建时间：2016-05-09 17:09:13
 */
public interface ICommodityPicsService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityPicsEntity commodityPicsEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityPicsEntity> findPage(CommodityPicsEntity commodityPicsEntity,RowBounds rowBounds);
	
	/**
	 * 获取查询数据
	 */
	public List<CommodityPicsEntity> queryList(CommodityPicsEntity commodityPicsEntity);
	
	/**
	 * 更新记录
	 */
	public int update(CommodityPicsEntity commodityPicsEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public CommodityPicsEntity getById(String id); 
	
	/**
	 * 通过商品编号和图片类型获取图片url
	 * l最大图，角度图；b宝贝描述图，描述图;m大图，角度图；s中图，搜索列表图；t小图，角度图;
	 * c最小图，颜色图；u、U图,U图；
	 * mb手机手机客户端大图，手机图片；ms手机手机客户端大图，手机图片；目前只存放宝贝描述图片
	 * @param commodityNo
	 * @param type
	 * @return
	 */
	public String getUrlByCommNo(String commodityNo,String type);
}