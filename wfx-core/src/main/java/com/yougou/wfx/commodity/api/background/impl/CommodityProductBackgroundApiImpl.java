 /*
 * 版本信息
 
 * 日期 2016-04-14 15:33:40
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.api.background.ICommodityProductBackgroundApi;
import com.yougou.wfx.commodity.dto.input.CommodityProductInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityProductPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityProductOutputDto;
import com.yougou.wfx.commodity.model.CommodityProductEntity;
import com.yougou.wfx.commodity.service.ICommodityProductService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommodityProductBackgroundApi实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-14 15:33:41
 */
@Service
public class CommodityProductBackgroundApiImpl implements ICommodityProductBackgroundApi{
	
	@Resource
	ICommodityProductService commodityProductService;
	
	@Override
	public int removeById(String id) {
		return commodityProductService.remove(id);
	}

	@Override
	public String insert(CommodityProductInputDto commodityProductDto) {
		return commodityProductService.insert(this.dtoToEntity(commodityProductDto));
	}

	@Override
	public PageModel<CommodityProductOutputDto> findPage(CommodityProductPageInputDto pageInputDto,PageModel pageModel) {
		CommodityProductEntity commodityProductEntity = (CommodityProductEntity) BeanUtil.convertBean(pageInputDto,CommodityProductEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityProductService.findPageCount(commodityProductEntity);
		List<CommodityProductEntity> lstCommodityProducts = commodityProductService.findPage(commodityProductEntity, rowBounds);

		return new PageModel<CommodityProductOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityProductOutputDto>) BeanUtil.convertBeanList(lstCommodityProducts,CommodityProductOutputDto.class));
	}

	@Override
	public int update(CommodityProductInputDto commodityProductDto) {
		return commodityProductService.update(this.dtoToEntity(commodityProductDto));
	}

	@Override
	public CommodityProductOutputDto getById(String id) {
		CommodityProductEntity commodityProductEntity = commodityProductService.getById(id);
		return this.entityToDto(commodityProductEntity);
	}

	@Override
	@Transactional
	public boolean batchPreInventory(Map<String, Integer> preMap) {
		boolean result = true;
		if(null != preMap && preMap.size() > 0){
			boolean preResult;
			for(String productId:preMap.keySet()){
				preResult = commodityProductService.preInventory(productId, preMap.get(productId));
				if(!preResult){
					result = false;
					return result;
				}
			}
		}
		return result;
	}

	@Override
	@Transactional
	public boolean batchFreePreInventory(Map<String, Integer> freeMap) {
		if(null != freeMap && freeMap.size() > 0){
			boolean preResult;
			for(String productId:freeMap.keySet()){
				preResult = commodityProductService.freePreInventory(productId, freeMap.get(productId));
				if(!preResult){
					return false;
				}
			}
		}
		return true;
	}
	
	private CommodityProductEntity dtoToEntity(Object obj){
		return (CommodityProductEntity) BeanUtil.convertBean(obj,CommodityProductEntity.class);
	}
	
	private CommodityProductOutputDto entityToDto(Object obj){
		return (CommodityProductOutputDto) BeanUtil.convertBean(obj,CommodityProductOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="商品数据清理：清理tbl_commodity_product表的非自营数据")
	public int clearNotSPYG() {
		return commodityProductService.clearNotSPYG();
	}
}
