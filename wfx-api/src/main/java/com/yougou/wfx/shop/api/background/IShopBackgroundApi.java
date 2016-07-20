 /*
 * 版本信息
 
 * 日期 2016-03-31 10:45:08
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.shop.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.shop.dto.input.ShopInputDto;
import com.yougou.wfx.shop.dto.input.ShopPageInputDto;
import com.yougou.wfx.shop.dto.output.DiscoverShopOutputDto;
import com.yougou.wfx.shop.dto.output.ShopOutputDto;

/**
 * IShopBackgroundApi
 * @author zhangfeng
 * @Date 创建时间：2016-03-31 10:45:08
 */
public interface IShopBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(ShopInputDto shopDto);
	
	
	/**
	 * 更新记录
	 */
	public int update(ShopInputDto shopDto);
	
	/**
	 * 通过id查询数据
	 */
	public ShopOutputDto getById(String id);
	
	/**
	 * 店铺列表
	 * @param pageInputDto
	 * @param pageModel
	 * @return
	 */
	PageModel<ShopOutputDto> findPage(ShopPageInputDto pageInputDto, PageModel<ShopOutputDto> pageModel);
	
	PageModel<DiscoverShopOutputDto> findInfoPage(DiscoverShopOutputDto disShop,PageModel pageModel);
	
	/**
	 * 关闭店铺
	 * @param shopDto
	 * @return
	 */
	int closeShop(ShopInputDto shopDto);
}

