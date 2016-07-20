 /*
 * 版本信息
 
 * 日期 2016-03-23 18:39:33
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.api.background.ICommodityBrandBackgroundApi;
import com.yougou.wfx.commodity.dto.input.CommodityBrandInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBrandPageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityBrandOutputDto;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.service.ICommodityBrandService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommodityBrandBackgroundApi实现
 * @author wuyang
 * @Date 创建时间：2016-03-23 18:39:34
 */
@Service
public class CommodityBrandBackgroundApiImpl implements ICommodityBrandBackgroundApi{
	
	@Resource
	ICommodityBrandService commodityBrandService;
	
	@Override
	@LoggerProfile(methodNote="根据ID删除商品品牌")
	public int removeById(String id) {
		return commodityBrandService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="新增商品品牌数据")
	public String insert(CommodityBrandInputDto commodityBrandDto) {
		return commodityBrandService.insert(this.dtoToEntity(commodityBrandDto));
	}
	
	@Override
	@LoggerProfile(methodNote="分页查询商品品牌列表")
	public PageModel<CommodityBrandOutputDto> findPage(CommodityBrandPageInputDto pageInputDto,PageModel<CommodityBrandOutputDto> pageModel) {
		CommodityBrandEntity commodityBrandEntity = (CommodityBrandEntity) BeanUtil.convertBean(pageInputDto,CommodityBrandEntity.class);
		commodityBrandEntity.setUseFlag(1);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commodityBrandService.findPageCount(commodityBrandEntity);
		List<CommodityBrandEntity> lstCommodityBrands = commodityBrandService.findPage(commodityBrandEntity, rowBounds);

		return new PageModel<CommodityBrandOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityBrandOutputDto>) BeanUtil.convertBeanList(lstCommodityBrands,CommodityBrandOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="更新商品品牌")
	public int update(CommodityBrandInputDto commodityBrandDto) {
		return commodityBrandService.update(this.dtoToEntity(commodityBrandDto));
	}

	@Override
	@LoggerProfile(methodNote="根据ID查询商品品牌")
	public CommodityBrandOutputDto getById(String id) {
		CommodityBrandEntity commodityBrandEntity = commodityBrandService.getById(id);
		return this.entityToDto(commodityBrandEntity);
	}
	
	private CommodityBrandEntity dtoToEntity(Object obj){
		return (CommodityBrandEntity) BeanUtil.convertBean(obj,CommodityBrandEntity.class);
	}
	
	private CommodityBrandOutputDto entityToDto(Object obj){
		return (CommodityBrandOutputDto) BeanUtil.convertBean(obj,CommodityBrandOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="批量更新热门商品品牌状态")
	public boolean batchUpdateHotBrand(String[] brandIds, String userName, int flag) {
		return commodityBrandService.batchUpdateHotBrand(brandIds, userName, flag);
	}
	
	@Override
	@LoggerProfile(methodNote="查询商品品牌数量")
	public int findPageCount(CommodityBrandPageInputDto pageInputDto){
		CommodityBrandEntity commodityBrandEntity = (CommodityBrandEntity) BeanUtil.convertBean(pageInputDto,CommodityBrandEntity.class);
		int totalCount = commodityBrandService.findPageCount(commodityBrandEntity);
		return totalCount;
	}
	
	@Override
	@LoggerProfile(methodNote="批量更新商品品牌使用状态")
	public void batchUpdateBrandUseFlag(){
		commodityBrandService.batchUpdateBrandUseFlag();
	}
}
