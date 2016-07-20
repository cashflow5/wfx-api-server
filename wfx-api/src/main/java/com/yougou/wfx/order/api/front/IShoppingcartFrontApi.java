 /*
 * 版本信息
 
 * 日期 2016-04-21 16:43:38
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.order.api.front;

import java.util.List;

import com.yougou.wfx.order.dto.input.ShoppingcartInputDto;
import com.yougou.wfx.order.dto.output.ShoppingcartOutputDto;

/**
 * IShoppingcartBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-04-21 16:43:39
 */
public interface IShoppingcartFrontApi{
	/**
	 * 通过id删除记录
	 */
	int removeShoppingCartById(String id);
	
	/**
	 * 保存单条记录
	 */
	String insertShoppingCart(ShoppingcartInputDto shoppingcartDto);
	
	/**
	 * 获取分页数据
	 */
	List<ShoppingcartOutputDto> queryShopingCartList(String loginId);
	
	/**
	 * 更新记录
	 */
	int updateShoppingCart(ShoppingcartInputDto shoppingcartDto);
	
	/**
	 * 通过id查询数据
	 */
	ShoppingcartOutputDto getShoppingCartById(String id);
}

