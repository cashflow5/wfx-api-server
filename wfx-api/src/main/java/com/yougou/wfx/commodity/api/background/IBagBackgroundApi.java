 /*
 * 版本信息
 
 * 日期 2016-03-20 13:16:54
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import java.util.List;

import com.yougou.wfx.commodity.dto.input.BagInputDto;
import com.yougou.wfx.commodity.dto.input.BagPageInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityBagRelaInputDto;
import com.yougou.wfx.commodity.dto.output.BagOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.dto.base.PageModel;


/**
 * 分销包后台接口
 * @author zheng.qq
 * @Date 创建时间：2016-03-20 13:16:57
 */
public interface IBagBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(BagInputDto bagDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<BagOutputDto> findPage(BagPageInputDto pageInputDto,PageModel<BagOutputDto> pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(BagInputDto bagDto, boolean model);
	
	/**
	 * 通过id查询数据
	 */
	public BagOutputDto getById(String id);
	
	/**
	 * 通过id查询关联的商品数据
	 */
	public List<CommodityStyleOutputDto> getRelaCommodityList(String id);
	
	/**
	 * 保存分销包的商品关联
	 */
	public String insertCommodityBagRela(CommodityBagRelaInputDto commodityBagRelaInputDto);
	
	/**
	 * 批量保存分销包的商品关联
	 */
	public void batchInsertCommodityBagRela(List<CommodityBagRelaInputDto> list);
	
	/**
	 * 删除分销包的商品关联
	 */
	public int deleteCommodityBagRela(CommodityBagRelaInputDto commodityBagRelaInputDto);

}