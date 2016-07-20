/*
 * 版本信息

 * 日期 2016-03-20 13:16:54

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.commodity.api.background.IBagBackgroundApi;
import com.yougou.wfx.commodity.dto.input.BagInputDto;
import com.yougou.wfx.commodity.dto.input.BagPageInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBagRelaInputDto;
import com.yougou.wfx.commodity.dto.output.BagOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.model.BagEntity;
import com.yougou.wfx.commodity.model.CommodityBagRelaEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.service.IBagService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
/**
 * 分销包后台接口
 * 
 * @author wuyang
 * @Date 创建时间：2016-03-20 13:16:57
 */
@Service
public class BagBackgroundApiImpl implements IBagBackgroundApi {

	@Resource
	private IBagService bagService;

	@Override
	@Transactional
	@LoggerProfile(methodNote = "通过id删除分销包")
	public int removeById(String id) {
		bagService.remove(id);
		CommodityBagRelaEntity commodityBagRelaEntity = new CommodityBagRelaEntity();
		commodityBagRelaEntity.setBagId(id);
		bagService.removeCommodityBagRela(commodityBagRelaEntity);
		return bagService.remove(id);
	}

	@Override
	@Transactional
	@LoggerProfile(methodNote = "添加分销包")
	public String insert(BagInputDto bagDto) {
		String id = bagService.insert(this.dtoToEntity(bagDto));
		for(String item : bagDto.getCommodityIds()){
			CommodityBagRelaInputDto commodityBagRelaInputDto = new CommodityBagRelaInputDto();
			commodityBagRelaInputDto.setBagId(id);
			commodityBagRelaInputDto.setCommodityId(item);
			commodityBagRelaInputDto.setCreateTime(new Date());
			this.insertCommodityBagRela(commodityBagRelaInputDto);
		}
		return id;
	}

	@Override
	@LoggerProfile(methodNote = "获取分页分销包")
	public PageModel<BagOutputDto> findPage(BagPageInputDto pageInputDto,PageModel<BagOutputDto> pageModel) {
		BagEntity bagEntity = (BagEntity) BeanUtil.convertBean(pageInputDto,BagEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = bagService.findPageCount(bagEntity);
		List<BagEntity> lstBags = bagService.findPage(bagEntity, rowBounds);

		return new PageModel<BagOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<BagOutputDto>) BeanUtil.convertBeanList(lstBags,BagOutputDto.class));
	}

	@Override
	@Transactional
	@LoggerProfile(methodNote = "修改/编辑分销包")
	public int update(BagInputDto bagDto, boolean model) {
		int d = bagService.update(this.dtoToEntity(bagDto));
		CommodityBagRelaInputDto filter1 = new CommodityBagRelaInputDto();
		filter1.setBagId(bagDto.getId());
		if(model){
			this.deleteCommodityBagRela(filter1);
			for(String item : bagDto.getCommodityIds()){
				CommodityBagRelaInputDto filter2 = new CommodityBagRelaInputDto();
				filter2.setBagId(bagDto.getId());
				filter2.setCommodityId(item);
				filter2.setCreateTime(new Date());
				this.insertCommodityBagRela(filter2);
			}
		}
		return d;
	}

	@Override
	public BagOutputDto getById(String id) {
		BagEntity bagEntity = bagService.getById(id);
		return this.entityToDto(bagEntity);
	}
	
	@Override
	public List<CommodityStyleOutputDto> getRelaCommodityList(String id) {
		// TODO Auto-generated method stub
		List<CommodityStyleEntity> entityList = bagService.findRelaCommodityList(id);
		List<CommodityStyleOutputDto> outputDtoList = (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(entityList,CommodityStyleOutputDto.class);
		return outputDtoList;
	}

	@Override
	@Transactional
	@LoggerProfile(methodNote = "保存分销包的商品关联")
	public String insertCommodityBagRela(
			CommodityBagRelaInputDto commodityBagRelaInputDto) {
		// TODO Auto-generated method stub
		return bagService.insertCommodityBagRela((CommodityBagRelaEntity)BeanUtil.convertBean(commodityBagRelaInputDto, CommodityBagRelaEntity.class));
	}
	
	
	@Override
	@Transactional
	@LoggerProfile(methodNote = "批量保存分销包的商品关联")
	public void batchInsertCommodityBagRela(List<CommodityBagRelaInputDto> list) {
		// TODO Auto-generated method stub
		for(CommodityBagRelaInputDto commodityBagRelaInputDto:list){
			bagService.insertCommodityBagRela((CommodityBagRelaEntity)BeanUtil.convertBean(commodityBagRelaInputDto, CommodityBagRelaEntity.class));
		}
	}
	@Override
	@Transactional
	@LoggerProfile(methodNote = "删除分销包的商品关联")
	public int deleteCommodityBagRela(
			CommodityBagRelaInputDto commodityBagRelaInputDto) {
		// TODO Auto-generated method stub
		return bagService.removeCommodityBagRela((CommodityBagRelaEntity)BeanUtil.convertBean(commodityBagRelaInputDto, CommodityBagRelaEntity.class));
	}
	
	private BagEntity dtoToEntity(Object obj){
		return (BagEntity) BeanUtil.convertBean(obj,BagEntity.class);
	}
	
	private BagOutputDto entityToDto(Object obj){
		return (BagOutputDto) BeanUtil.convertBean(obj,BagOutputDto.class);
	}

	
}
