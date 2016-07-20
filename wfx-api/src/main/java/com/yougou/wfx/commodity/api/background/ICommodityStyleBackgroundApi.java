/*
 * 版本信息

 * 日期 2016-03-24 16:58:55

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background;

import java.util.List;

import com.yougou.wfx.commodity.dto.input.CommodityInventoryInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStyleInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStylePageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityInventoryOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommodityStyleBackgroundApi
 * 
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public interface ICommodityStyleBackgroundApi {
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);

	/**
	 * 保存单条记录
	 */
	public String insert(CommodityStyleInputDto commodityStyleDto);

	/**
	 * 获取分页数据
	 */
	public PageModel<CommodityStyleOutputDto> findPage(CommodityStylePageInputDto pageInputDto, PageModel<CommodityStyleOutputDto> pageModel, int warehouseFlag);

	/**
	 * 更新记录
	 */
	public int update(CommodityStyleInputDto commodityStyleDto);

	/**
	 * 批量添加至微分销
	 * 
	 * @param dto
	 */
	public void batchCommodityAddToWfx(List<CommodityStyleInputDto> dto) throws Exception;

	/**
	 * 批量修改上下架
	 * 
	 * @param dto
	 */
	public void batchUpdateShelvesStatus(List<CommodityStyleInputDto> dto);

	/**
	 * 通过id查询数据
	 */
	public CommodityStyleOutputDto getById(String id);

	/**
	 * 查询满足条件的记录数量
	 * 
	 * @param commodityStyleDto
	 * @return
	 */
	int findPageCount(CommodityStyleInputDto commodityStyleDto);

	/**
	 * 判断某个分类下是否有在售的商品
	 * 
	 * @return
	 */
	boolean hasCommodityOnSaleByCat(String catNo);

	/**
	 * 查询导出商品信息
	 * 
	 * @param list
	 * @return
	 */
	public List<CommodityStyleOutputDto> queryExportCommodityInfo(List<String> list);

	/**
	 * 单个调价
	 * 
	 * @param dto
	 * @return
	 */
	public void readjustPrices(List<CommodityStyleInputDto> dto);

	/**
	 * 单个调价
	 * 
	 * @param dto
	 * @return
	 */
	public void batchReadjustPrices(List<CommodityStyleInputDto> dto);

	/**
	 * 根据品牌编号获取商品数量
	 * 
	 * @param brandNo
	 * @return
	 */
	int getCommodityCountByBrandNo(String brandNo);

	/**
	 * 查询商品库存列表
	 * 
	 * @param inputDto
	 * @param pageModel
	 * @return
	 */
	PageModel<CommodityInventoryOutputDto> queryCommodityInventoryPage(CommodityInventoryInputDto inputDto, PageModel pageModel);

	/**
	 * 根据条件查询商品
	 * 
	 * @return
	 */
	public List<CommodityStyleOutputDto> queryList(CommodityStyleInputDto inputDto);

	/**
	 * 统计销量，人气，代理数
	 * 
	 * @return
	 */
	public int countCommodityStyleParams();
	
	/**
	 * 根据商品id获取该商品同款同分销商的商品列表。
	 * @param commodityId   商品Id	
	 * @return
	 */
	List<CommodityStyleOutputDto>  getSameSellerCommodity(String commodityId);

	/**
	 * 清理非自营商品图片
	 * @return
	 */
	public int clearCommodityStylePicNotSPYG();
	
	
	/**
    * 批量方法，根据商品no集合获取商品列表
    *
    * @param  nos 商品编码
    * @return List<CommodityStyleOutputDto>
    */
	List<CommodityStyleOutputDto> getCommodityByNos(List<String>nos)throws Exception ;

	public Double computeDistributableMoney(Double wfxPrice, Double costPrice);

//	public void batchAddProxyToOrigianlSeller(
//			List<CommodityStyleInputDto> arrayList);
	

	public PageModel<CommodityStyleOutputDto> queryDiscoverStyleInfo(
			CommodityStylePageInputDto pageInputDto, PageModel pageModel);
	
	public void synchronizationCommodity(String channelNo, String SellerNo, List<String> commodityNoList)throws Exception ;

	List<CommodityStyleOutputDto> queryCommodityList(String supplierCodes,
			String commodityIds);
}
