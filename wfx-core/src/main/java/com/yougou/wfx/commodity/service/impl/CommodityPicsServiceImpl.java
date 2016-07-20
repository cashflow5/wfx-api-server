 /*
 * 版本信息
 
 * 日期 2016-05-09 17:09:12
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.dao.CommodityPicsMapper;
import com.yougou.wfx.commodity.model.CommodityPicsEntity;
import com.yougou.wfx.commodity.service.ICommodityPicsService;
import com.yougou.wfx.framework.base.BaseService;

/**
 * ICommodityPicsService实现
 * @author wzf
 * @Date 创建时间：2016-05-09 17:09:13
 */
@Service
public class CommodityPicsServiceImpl extends BaseService implements ICommodityPicsService {
	
	@Resource
	private CommodityPicsMapper commodityPicsMapper;

	@Override
	public int findPageCount(CommodityPicsEntity commodityPicsEntity){
		return commodityPicsMapper.findPageCount(commodityPicsEntity);
	}

	@Override
	public List<CommodityPicsEntity> findPage(CommodityPicsEntity commodityPicsEntity,RowBounds rowBounds){
		return commodityPicsMapper.findPage(commodityPicsEntity,rowBounds);
	}

	@Override
	public List<CommodityPicsEntity> queryList(CommodityPicsEntity commodityPicsEntity){
		return commodityPicsMapper.queryList(commodityPicsEntity);
	}
	
	@Override
	@Transactional
	public int update(CommodityPicsEntity commodityPicsEntity){
		return  commodityPicsMapper.update(commodityPicsEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return commodityPicsMapper.remove(id);
	}
	
	@Override
	public CommodityPicsEntity getById(String id){
		return commodityPicsMapper.getById(id);
	}
	
	@Override
	public String getUrlByCommNo(String commodityNo,String type){
		String url = "";
		if(StringUtils.isBlank(commodityNo) || StringUtils.isBlank(type)){
			return url;
		}
		CommodityPicsEntity pics = new CommodityPicsEntity();
		pics.setDelFlag(1);
		pics.setCommodityNo(commodityNo);
		pics.setPicType(type);
		List<CommodityPicsEntity> picsList = commodityPicsMapper.queryList(pics);
		if(null != picsList && picsList.size() > 0){
			CommodityPicsEntity picsRs = picsList.get(0);
			String picPath = picsRs.getPicPath()==null?"":picsRs.getPicPath();
			String picName = picsRs.getPicName()==null?"":picsRs.getPicName();
			url = picPath + picName;
		}
		return url;
	}
}