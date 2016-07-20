 /*
 * 版本信息
 
 * 日期 2016-03-31 10:45:08
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.shop.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.shop.api.background.IShopBackgroundApi;
import com.yougou.wfx.shop.dto.input.ShopInputDto;
import com.yougou.wfx.shop.dto.input.ShopPageInputDto;
import com.yougou.wfx.shop.dto.output.DiscoverShopOutputDto;
import com.yougou.wfx.shop.dto.output.ShopOutputDto;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * IShopBackgroundApi实现
 * @author wuyang
 * @Date 创建时间：2016-03-31 10:45:08
 */
@Service
public class ShopBackgroundApiImpl implements IShopBackgroundApi{
	
	@Resource
	IShopService shopService;
	
	@Override
	@LoggerProfile(methodNote="删除店铺")
	public int removeById(String id) {
		return shopService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="新增店铺")
	public String insert(ShopInputDto shopDto) {
		return shopService.insert(this.dtoToEntity(shopDto));
	}

	@Override
	@LoggerProfile(methodNote="查询店铺列表")
	public PageModel<ShopOutputDto> findPage(ShopPageInputDto pageInputDto,PageModel<ShopOutputDto> pageModel) {
		ShopEntity shopEntity = (ShopEntity) BeanUtil.convertBean(pageInputDto,ShopEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = shopService.findPageCount(shopEntity);
		List<ShopEntity> lstShops = shopService.findPage(shopEntity, rowBounds);

		return new PageModel<ShopOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<ShopOutputDto>) BeanUtil.convertBeanList(lstShops,ShopOutputDto.class));
	}
	
	@Override
	public PageModel<DiscoverShopOutputDto> findInfoPage(DiscoverShopOutputDto disShop, PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = shopService.findInfoPageCount(disShop);
		List<DiscoverShopOutputDto> shopList = shopService.findInfoPage(disShop, rowBounds);
		return new PageModel<DiscoverShopOutputDto>(pageModel.getPage(),pageModel.getLimit(),totalCount,shopList);
	}

	@Override
	@LoggerProfile(methodNote="更新店铺")
	public int update(ShopInputDto shopDto) {
		return shopService.update(this.dtoToEntity(shopDto));
	}

	@Override
	@LoggerProfile(methodNote="查询店铺详情")
	public ShopOutputDto getById(String id) {
		ShopEntity shopEntity = shopService.getById(id);
		shopEntity.setLogoUrl( PicPathUtil.getImageAbsUrl( shopEntity.getLogoUrl() ) );
		shopEntity.setSignUrl( PicPathUtil.getImageAbsUrl( shopEntity.getSignUrl() ) );
		return this.entityToDto(shopEntity);
	}
	
	private ShopEntity dtoToEntity(Object obj){
		return (ShopEntity) BeanUtil.convertBean(obj,ShopEntity.class);
	}
	
	private ShopOutputDto entityToDto(Object obj){
		return (ShopOutputDto) BeanUtil.convertBean(obj,ShopOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="关闭店铺")
	public int closeShop(ShopInputDto shopDto) {
		return shopService.closeShop(this.dtoToEntity(shopDto));
	}
}
