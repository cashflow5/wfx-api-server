 /*
 * 版本信息
 
 * 日期 2016-04-21 16:43:38
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.order.api.front.IShoppingcartFrontApi;
import com.yougou.wfx.order.dto.input.ShoppingcartInputDto;
import com.yougou.wfx.order.dto.output.ShoppingcartOutputDto;
import com.yougou.wfx.order.model.ShoppingcartEntity;
import com.yougou.wfx.order.service.IShoppingcartService;

/**
 * IShoppingcartBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-21 16:43:39
 */
@Service
public class ShoppingcartFrontApiImpl implements IShoppingcartFrontApi{
	
	@Resource
	IShoppingcartService shoppingcartService;
	
	@Override
	@LoggerProfile(methodNote="删除购物车数据")
	public int removeShoppingCartById(@NotBlank String id) {
		return shoppingcartService.remove(id);
	}

	@Override
	@LoggerProfile(methodNote="新增购物车数据")
	public String insertShoppingCart(@NotBlank.List(value={@NotBlank(target="id"),@NotBlank(target="loginId"),@NotBlank(target="shopId"),
			@NotBlank(target="commodityNo"),@NotBlank(target="productNo")}) ShoppingcartInputDto shoppingcartDto) {
		return shoppingcartService.insert(this.dtoToEntity(shoppingcartDto));
	}

	@Override
	@LoggerProfile(methodNote="查询购物车列表数据")
	public List<ShoppingcartOutputDto> queryShopingCartList(@NotBlank String loginId) {
		ShoppingcartEntity shoppingcartEntity = new ShoppingcartEntity();
		shoppingcartEntity.setLoginId(loginId);
		RowBounds rowBounds = new RowBounds();
		List<ShoppingcartEntity> lstShoppingcarts = shoppingcartService.findPage(shoppingcartEntity, rowBounds);

		return (List<ShoppingcartOutputDto>) BeanUtil.convertBeanList(lstShoppingcarts,ShoppingcartOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="修改购物车数据")
	public int updateShoppingCart(@NotBlank.List(value={@NotBlank(target="id"),@NotBlank(target="loginId"),@NotBlank(target="shopId"),
			@NotBlank(target="commodityNo"),@NotBlank(target="productNo")}) ShoppingcartInputDto shoppingcartDto) {
		return shoppingcartService.update(this.dtoToEntity(shoppingcartDto));
	}

	@Override
	@LoggerProfile(methodNote="查询购物车数据")
	public ShoppingcartOutputDto getShoppingCartById(@NotBlank String id) {
		ShoppingcartEntity shoppingcartEntity = shoppingcartService.getById(id);
		return this.entityToDto(shoppingcartEntity);
	}
	
	private ShoppingcartEntity dtoToEntity(Object obj){
		return (ShoppingcartEntity) BeanUtil.convertBean(obj,ShoppingcartEntity.class);
	}
	
	private ShoppingcartOutputDto entityToDto(Object obj){
		return (ShoppingcartOutputDto) BeanUtil.convertBean(obj,ShoppingcartOutputDto.class);
	}
}
