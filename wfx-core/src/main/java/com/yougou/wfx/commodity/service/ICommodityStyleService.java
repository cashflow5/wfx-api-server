/*
 * 版本信息

 * 日期 2016-03-24 16:58:55

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.commodity.dto.input.CommodityStyleInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStyleOrderInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityPropertyOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleFilterOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.model.CommodityInventoryEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.dto.base.PageModel;

/**
 * ICommodityStyleService接口
 * 
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public interface ICommodityStyleService {

	/**
	 * 获取总条数
	 */
	public int findPageCount(CommodityStyleEntity commodityStyleEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityStyleEntity> findPage(CommodityStyleEntity commodityStyleEntity, RowBounds rowBounds);

	/**
	 * 保存单条记录
	 */
	public String insert(CommodityStyleEntity commodityStyleEntity);

	/**
	 * 更新记录
	 */
	public int update(CommodityStyleEntity commodityStyleEntity);

	/**
	 * 通过id删除记录
	 */
	public int remove(String id);

	/**
	 * 通过id查询数据
	 */
	public CommodityStyleEntity getById(String id);

	/**
	 * 批量添加至微分销
	 * 
	 * @param commodityStyleEntity
	 */
	public void batchCommodityAddToWfx(List<CommodityStyleEntity> commodityStyleEntity);

	/**
	 * 批量修改上下架
	 * 
	 * @param commodityStyleEntity
	 */
	public void batchUpdateShelvesStatus(List<CommodityStyleEntity> commodityStyleEntity);

	/**
	 * 查询导出商品信息
	 * 
	 * @param list
	 * @return
	 */
	public List<CommodityStyleEntity> queryExportCommodityInfo(List<String> list);

	/**
	 * 单个调价
	 * 
	 * @param commodtyList
	 * @return
	 */
	public void readjustPrices(List<CommodityStyleEntity> commodtyList);

	/**
	 * 批量调价
	 * 
	 * @param commodtyList
	 * @return
	 */
	public void batchReadjustPrices(List<CommodityStyleEntity> commodtyList);

	/**
	 * 获取总条数
	 */
	public int findInventoryPageCount(CommodityInventoryEntity commodityInventoryEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityInventoryEntity> findInventoryPage(CommodityInventoryEntity commodityInventoryEntity, RowBounds rowBounds);

	/**
	 * 获取总条数
	 */
	public int queryListCount(CommodityStyleEntity commodityStyleEntity);

	/**
	 * 获取分页数据
	 */
	public List<CommodityStyleEntity> queryList(CommodityStyleEntity commodityStyleEntity, RowBounds rowBounds);

	/**
	 * 根据指定条件获取商品信息
	 */
	List<CommodityStyleEntity> queryList(CommodityStyleEntity commodityStyleEntity);

	/**
	 * 根据商品ID获取商品信息
	 */
	List<CommodityStyleEntity> queryCommodityListByIds(List<String> commodityIds);

	/**
	 * 获取商品库存数量
	 * 
	 * @param commodityId
	 * @return
	 */
	int getStock(String commodityId);

	/**
	 * 根据商品id获取该商品同款同分销商的商品列表。
	 * 
	 * @param commodityId
	 *            商品Id
	 * @return
	 */
	List<CommodityStyleEntity> getSameSellerCommodity(String commodityId) throws Exception;

	/**
	 * 获取代理商的商品列表
	 * 
	 * @param sellerId
	 * @return
	 */
	List<CommodityStyleEntity> queryProxyCommodity(String sellerId, RowBounds rowBounds) throws Exception;

	/**
	 * 获取代理商的商品列表数
	 * 
	 * @param sellerId
	 * @return
	 */
	int queryProxyCommodityCount(String sellerId) throws Exception;

	/**
	 * 批量添加商品的分类属性/单个的
	 * 
	 * @param commodityId
	 *            代理商品的id
	 * @param classifyId
	 *            分类的id
	 * @return
	 */
	void updateCommodityClassify(List<Map<String, String>> map) throws Exception;

	/**
	 * 删除代理商品信息
	 * 
	 * @param commodityId
	 *            代理商品id
	 */
	void deleteDellerCommodityCatRela(List<String> commodityId) throws Exception;

	/**
	 * 查询分销商指定数量的热卖商品列表
	 *
	 * @param shopId
	 *            店铺id
	 * @param count
	 *            数量(0时返回所有热卖商品)
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> queryHotCommodity(String shopId, PageModel pageModel) throws Exception;
	
	
	
	/**
	 * 查询分销商指定数量的热卖商品列表
	 *
	 * @param shopId
	 *            店铺id
	 * @param count
	 *            数量(0时返回所有热卖商品)
	 * @return
	 */
	List<CommodityStyleOutputDto> queryHotCommodityList(String shopId, int count) throws Exception;
	
	

	/**
	 * 根据选择分类获取店铺下的商品
	 * 
	 * @param categoryCode
	 *            分类编码
	 * @param sellerId
	 *            微分销商
	 * @param pageModel
	 *            分页参数
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> getShopCategoryCommodity(String categoryId, String shopId, PageModel pageModel) throws Exception;

	/**
	 * 批量方法，根据商品no集合获取商品列表
	 *
	 * @param nos
	 *            款色ID
	 * @param includeProduct
	 *            是否包含货品信息
	 * @param includeStock
	 *            表示是否要查询库存
	 * @return List<CommodityStyleOutputDto>
	 */
	List<CommodityStyleOutputDto> getCommodityByNos(List<String> nos) throws Exception;

	/**
	 * 根据条件获取商品列表
	 * 
	 * @param paramMap
	 *            id,no,style_no,
	 * @return
	 */
	List<CommodityStyleOutputDto> getCommodityByParameter(Map<String, Object> paramMap);

	/**
	 * 通过款色ID获取
	 * 
	 * @param no
	 * @return
	 */
	CommodityStyleEntity getByNo(String no);

	/**
	 * 统计销量，人气，代理数
	 * 
	 * @return
	 */
	public int countCommodityStyleParams();

	/**
	 * 获取分类id
	 * 
	 * @param commodityId
	 * @return
	 */
	List<String> getCatId(String commodityId);

	/**
	 * 通过品牌id查找商品信息
	 * 
	 * @param BrandNo
	 *            品牌no
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> getCommodityByBrandNo(String brandNo,String sllerId, PageModel pageModel);

	/**
	 * 通过分类id查找商品信息
	 * 
	 * @param catId
	 *            分类id
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> getCommodityByCatId(String catId,String sllerId, PageModel pageModel);

	/**
	 * 搜索商品信息
	 * 
	 * @param searchName
	 * @param rowBounds
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> queryCommodityListForSearch(String searchName,String sllerId, PageModel pageModel) throws Exception;

	/**
	 * 清理非自营商品图片
	 * 
	 * @return
	 */
	int clearCommodityStylePicNotSPYG();
	/**
	 * 计算可分配佣金金额
	 * @param entity
	 * @return
	 */
	Double computeDistributableMoney( Double wfxPrice, Double costPrice);
//	/**
//	 * 批量加入总经销商代理
//	 */
//	void batchAddProxyToOrigianlSeller(List<CommodityStyleInputDto> commodityStyleEntity);

	
	PageModel<CommodityStyleOutputDto> queryDiscoverStyleInfo(CommodityStyleEntity commodityStyleEntity, PageModel pageModel);
	/**
	 * 获取图片
	 * @param commodityNo
	 * @return
	 */
	String getPicBigName(String commodityNo);
	
	/** 
	 * 获取商品信息
	 * @param inputDto
	 * @param pageModel
	 * @return
	 */
	PageModel<CommodityStyleOutputDto> queryCommodityOrder(
				CommodityStyleOrderInputDto inputDto, PageModel pageModel);
	
	/**
	 * 根据商品no查询属性
	 * @param commodityNo
	 * @return
	 */
	List<CommodityPropertyOutputDto> getPropByCommodityId(String commodityNo);
	   
	/**
     * 根据inputDto 获取品牌信息
     * @param inputDto  
     * @return
     */
    List<CommodityStyleFilterOutputDto> getBrandInfo(CommodityStyleOrderInputDto inputDto);
    
    /**
     * 根据inputDto 分类信息
     * @param inputDto  
     * @return
     */
    List<CommodityStyleFilterOutputDto> getCatInfo(CommodityStyleOrderInputDto inputDto);
    /**
     * 根据inputDto 分类信息
     * @param inputDto  
     * @return
     */
    List<String> getPriceInfo(CommodityStyleOrderInputDto inputDto);
    /**
     * 根据inputDto 分类信息
     * @param inputDto  
     * @return
     */
    List<CommodityStyleFilterOutputDto> getSizeInfo(CommodityStyleOrderInputDto inputDto);
    
    /**
     * 根据inputDto 分类信息
     * @param inputDto  
     * @return
     */
    List<CommodityStyleFilterOutputDto> getSpecInfo(CommodityStyleOrderInputDto inputDto);
    
    /**
     * 根据inputDto 分类信息
     * @param inputDto  
     * @return
     */
    List<CommodityStyleFilterOutputDto> getPropInfo(CommodityStyleOrderInputDto inputDto);
	
    /**
     * 获取商品尺码对照表
     * @param no
     * @return
     */
    String getPictureUrl(String no);

	List<CommodityStyleOutputDto> queryCommodityList(
			List<String> lstSupplierCode, List<String> lstCommodityId);
    
    
}
