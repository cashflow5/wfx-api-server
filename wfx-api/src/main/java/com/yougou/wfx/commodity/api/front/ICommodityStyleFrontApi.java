 /*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.front;
import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.commodity.dto.input.CheckProductNumberInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStyleOrderInputDto;
import com.yougou.wfx.commodity.dto.output.CheckProductNumberOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityPicsOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityProductOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityPropertyOutputDto;
import com.yougou.wfx.commodity.dto.output.CommoditySimplifyStyleOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleFilterOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.dto.output.SellerCommodityCatRelaOutpuDto;

/**
 * ICommodityStyleFrontApi
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
public interface ICommodityStyleFrontApi{
	
	/**
	* 查询分销商指定数量的热卖商品列表
	*
	* @param shopId 店铺id
	* @param count  数量(0时返回所有热卖商品)
	* @return
	*/
	public List<CommodityStyleOutputDto>  queryHotCommodityList(String shopId,int count);
	/**
	* 查询分销商指定数量的热卖商品列表
	*
	* @param shopId 店铺id
	* @param count  数量(0时返回所有热卖商品)
	* @return
	*/
	public PageModel<CommodityStyleOutputDto>  queryHotCommodity(String shopId,PageModel pageModel);
	
	/**
	* 商品列表展示：按销量排序、按新品排序、按价格排序
	*
	* @param inputDto  参数属性类
	* @return
	*/
	public PageModel<CommodityStyleOutputDto>  queryCommodityOrder(CommodityStyleOrderInputDto inputDto,PageModel pageModel);
	
	/**
	 * 根据选择分类获取店铺下的商品 
	 * @param categoryCode 分类编码
	 * @param sellerId 微分销商
	 * @param pageModel 分页参数
	 * @return
	 */
	public PageModel<CommodityStyleOutputDto>  getShopCategoryCommodity(String categoryId,String shopId,PageModel pageModel);
	
	/**
	 * 根据商品no查询属性
	 * @param commodityNo
	 * @return
	 */
	List<CommodityPropertyOutputDto> getPropByCommodityId(String commodityNo);
	  /**
     * 根据分销商品id获取商品详情
     *
     * @param no    分销商品no
     * @param includeProduct 是否包含货品
     * @param includeStock   是否包含库存
     * @return
     */
     CommodityStyleOutputDto queryCommodityDetails(String no, boolean includeProduct, boolean includeStock);
	
	

	/**
	 * 根据商品id获取该商品同款同分销商的商品列表。
	 * @param commodityId   商品Id	
	 * @return
	 */
	List<CommodityStyleOutputDto>  getSameSellerCommodity(String commodityId);

	 
	/**
	 * 根据为分销商sellerId已代理商品列表接口
	 * @param sellerId  分销商id	
	 * @param pageModel  分页信息
	 * @return
	 */
	public  PageModel<CommodityStyleOutputDto>   queryProxyCommodity(String sellerId,PageModel pageModel);
	
	/**
	 * 修改已代理商品上架、下架  状态
	 * @param id  代理商品的id
	 * @param status   上架/下架状态
	 * @return success:执行成功的商品id列表  error:未执行成功的商品id列表
	 * 
	 */
	public   WFXResult<Map<String, List<String>>>  updateSellerCommodityShelvesStatus(List<String> id , int status);
	
	
	/**
	 * 批量添加商品的分类属性/单个的 
	 * @param commodityId  代理商品的id
	 * @param classifyId   分类的id
	 * @return
	 */
	public  WFXResult<Boolean>    updateCommodityClassify(List<String> commodityId , List<String> classifyId);
	
	/**
	 * 删除分类
	 * @param classifyId  分类id
	 * @return
	 */
	public  WFXResult<Boolean>  deleteClassify(String  classifyId);
	
	/**
	 * 新增分类
	 * @param classifyName 分类名称
	 * @param parentNodeId  分类父节点Id,如果父节点为空，默认为一级节点
	 * @param sellerId  分销商id
	 * @return
	 */
	public  WFXResult<Boolean>  addClassify(String  classifyName, String parentNodeId, String sellerId);
	
	
	/**
	 * 修改分类
	 * @param classifyName 分类名称
	 * @param classifyId  分类id
	 * @return
	 */
	public  WFXResult<Boolean> updateClassify(String  classifyName,String classifyId);
	
	/**
	 * 根据分销商id查询分类信息
	 * @param sellerId 分销商id
	 * @return
	 */
	public List<SellerCommodityCatRelaOutpuDto>  queryClassify(String sellerId);
	
	/**
	 * 根据商品id 获取库存的状态 ,上下架状态，如果货品id不为空，已货品的维度去计算库存和上下架状态
	 * @param commodityId  商品id
	 * @param productId  货品id
	 * 
	 * @return
	 */
    public CommoditySimplifyStyleOutputDto  queryShelvesStatusAndInventoryStatus(String commodityId,String productId);
    
    /**
	 * 根据货品跟下订单的数量  校验库存
	 * @param dto  下单的货品信息
	 * @return
	 */
    public CheckProductNumberOutputDto  checkProductNumber(List<CheckProductNumberInputDto>  dto);
    
    
    /**
    * 批量方法，根据商品id集合获取商品列表
    *
    * @param commodityIds
    * @param includeProduct 是否包含货品信息
    * @param  includeStock 表示是否要查询库存
    * @return  CommodityStyleOutputDto对象请增加货品列表字段
    */
    List<CommodityStyleOutputDto> getCommodityListByIds(List<String> commodityIds, boolean includeProduct,boolean includeStock);
    
    /**
     * 提供给搜索：查询商品列表等信息
     * @param searchName 搜索关键字
     * @param pageModel
     * 
     * @return
     */
    PageModel<CommodityStyleOutputDto> queryCommodityListForSearch(String searchName,String sllerId,PageModel pageModel);
    
    /**
	* 批量获取指定类型的商品图片
	*
	* @param commodityIds 商品id集合
	* @param picType      图片类型,为空则获取所有类型图片
	* @return
	*/
	Map<String, List<CommodityPicsOutputDto>> batchGetCommodityPictures(List<String> commodityNos, String picType);
	
	
   /**
    * 批量方法，根据商品no集合获取商品列表
    *
    * @param  nos 商品编码
    * @param  includeProduct 是否包含货品信息
    * @param  includeStock 表示是否要查询库存
    * @return List<CommodityStyleOutputDto>
    */
	List<CommodityStyleOutputDto> getCommodityByNos(List<String>nos,boolean includeProduct,boolean includeStock);

    /**
    * 批量方法，根据货品编码集合获取货品列表
    *
    * @param productNos  
    * @return List<CommodityProductOutputDto>
    */
	List<CommodityProductOutputDto> getProductByProductNos(List<String> productNos);
	
	CommodityProductOutputDto getProductById(String productID);
	
	  /**
     * 根据条件获取商品列表
     * @param paramMap  id,no,style_no,
     * @return
     */
    List<CommodityStyleOutputDto> getCommodityByParameter(Map<String, Object> paramMap);
	
    /**
     * 通过品牌no查找商品信息
     * @param brandNo 品牌no
     * @return
     */
    PageModel<CommodityStyleOutputDto>  getCommodityByBrandNo(String brandNo,String sllerId,PageModel pageModel);
    
    
    /**
     * 通过分类id查找商品信息
     * @param catId  分类id
     * @param  sllerId  分销商id
     * @return
     */
    PageModel<CommodityStyleOutputDto>  getCommodityByCatId(String catId,String sllerId ,PageModel pageModel);
    
    
    
    /**
     * 根据分销商品id获取商品详情
     *
     * @param no    分销商品no
     * @param includeProduct 是否包含货品
     * @param includeStock   是否包含库存
     * @param sllerId 分销商id
     * @return
     */
     CommodityStyleOutputDto queryCommodityDetails(String no,String sllerId, boolean includeProduct, boolean includeStock);
    
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
      * 根据inputDto 获取价格区间新
      * @param inputDto  
      * @return
      */
     List<String> getPriceInfo(CommodityStyleOrderInputDto inputDto);
     /**
      * 根据inputDto 获取尺码信息
      * @param inputDto  
      * @return
      */
     List<CommodityStyleFilterOutputDto> getSizeInfo(CommodityStyleOrderInputDto inputDto);
     
     /**
      * 根据inputDto 获取颜色信息
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
 	 * 通过id查询数据
 	 */
 	CommodityStyleOutputDto getById(String id);
 	
 	 /**
     * 获取商品尺码对照表
     * @param no
     * @return
     */
    String getPictureUrl(String no);

}

