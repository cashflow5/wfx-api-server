/*
 * 版本信息

 * 日期 2016-03-24 16:58:55

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.basicset.service.ISysConfigService;
import com.yougou.wfx.commodity.dao.CommodityStyleMapper;
import com.yougou.wfx.commodity.dto.input.CommodityStyleInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStyleOrderInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityPropertyOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleFilterOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.model.CommodityInventoryEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.model.CommodityStyleFilterEntity;
import com.yougou.wfx.commodity.model.CommodityStyleOrderEntity;
import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.front.ICommissionPercentFrontApi;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;
import com.yougou.wfx.framework.annotation.cache.CacheCable;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.seller.dao.SellerInfoMapper;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.MathUtil;
import com.yougou.wfx.utils.PicPathUtil;

/**
 * ICommodityStyleService实现
 * 
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
@Service
public class CommodityStyleServiceImpl extends BaseService implements ICommodityStyleService {

	@Resource
	private CommodityStyleMapper commodityStyleMapper;
	@Resource
	private ICommissionPercentFrontApi commissionPercentFrontApi;
	@Resource
	private ISysConfigService sysConfigService ;
	@Resource
	private ISellerCommodityService sellerCommodityService;		
	@Resource
	private SellerInfoMapper sellerInfoMapper;
	
	@Override
	public int findPageCount(CommodityStyleEntity commodityStyleEntity) {
		return commodityStyleMapper.findPageCount(commodityStyleEntity);
	}

	@Override
	public List<CommodityStyleEntity> findPage(CommodityStyleEntity commodityStyleEntity, RowBounds rowBounds) {
		return commodityStyleMapper.findPage(commodityStyleEntity, rowBounds);
	}

	@Override
	public List<CommodityStyleEntity> queryList(CommodityStyleEntity commodityStyleEntity) {
		return commodityStyleMapper.queryList(commodityStyleEntity);
	}

	@Override
	@Transactional
	public String insert(CommodityStyleEntity commodityStyleEntity) {
		String strId = UUIDGenerator.get32LowCaseUUID();
		commodityStyleEntity.setId(strId);
		commodityStyleMapper.insert(commodityStyleEntity);
		return strId;
	}

	@Override
	@Transactional
	public int update(CommodityStyleEntity commodityStyleEntity) {
		return commodityStyleMapper.update(commodityStyleEntity);
	}

	@Override
	@Transactional
	public int remove(String id) {
		return commodityStyleMapper.remove(id);
	}

	@Override
	public CommodityStyleEntity getById(String id) {
		return commodityStyleMapper.getById(id);
	}

	@Override
	public void batchCommodityAddToWfx(List<CommodityStyleEntity> commodityStyleEntity) {
		if (commodityStyleEntity != null && commodityStyleEntity.size() > 0) {

			for (int i = 0; i < commodityStyleEntity.size(); i++) {
				commodityStyleMapper.batchCommodityAddToWfx(commodityStyleEntity.get(i));
			}
		}

	}

	@Override
	public void batchUpdateShelvesStatus(List<CommodityStyleEntity> commodityStyleEntity) {
		if (commodityStyleEntity != null && commodityStyleEntity.size() > 0) {
			for (int i = 0; i < commodityStyleEntity.size(); i++) {
				commodityStyleMapper.batchUpdateShelvesStatus(commodityStyleEntity.get(i));
			}
		}

	}
	
//	/* 给总经销商代理一系列商品 */
//	@Override
//	public void batchAddProxyToOrigianlSeller(List<CommodityStyleInputDto> list) {
//		String	originalSellerId = ApiConstant.ORIGINAL_SELLER_ID_DEFAULT;
//		SellerInfoEntity originalSeller = sellerInfoMapper.getById(originalSellerId);
//		if (list != null && list.size() > 0) {
//			for (CommodityStyleInputDto vo : list) {
//				// 查
//				CommodityStyleEntity commodityStyleEntity = commodityStyleMapper.getById( vo.getId() );
//				SellerCommodityEntity sellerCommodityEntity = new SellerCommodityEntity();
//				sellerCommodityEntity.setSellerId(originalSellerId);
//				sellerCommodityEntity.setCommodityStyleNo( commodityStyleEntity.getStyleNo() );
//				sellerCommodityEntity.setCommodityBrandNo( commodityStyleEntity.getBrandNo() );
//				sellerCommodityEntity.setCommodityCatNo( commodityStyleEntity.getCatNo() );
//				sellerCommodityEntity.setCommodityYears( commodityStyleEntity.getYears() );
//				List<SellerCommodityEntity> olds = sellerCommodityService.getSellerCommodity(sellerCommodityEntity);
//				if( olds!=null&& olds.size()>0 ){//update
//					for( int i=0;i<olds.size();i++){
//						SellerCommodityEntity old = olds.get(i);
//						if( i==0 ){
//							if( old.getStatus()!=ApiConstant.ON_SHOW ){
//								old.setStatus( ApiConstant.ON_SHOW );
//								old.setUpdateTime( new Date() );
//								sellerCommodityService.update(old);
//							}
//							// 上架状态，不做修改。
//						}else{
//							sellerCommodityService.remove(old.getId());
//						}
//					}
//				}else{// insert
//					sellerCommodityEntity.setCreateTime( new Date() );
//					sellerCommodityEntity.setStatus( ApiConstant.ON_SHOW );
//					sellerCommodityEntity.setUpdateTime( new Date() );
//					sellerCommodityEntity.setSellerName( originalSeller.getSellerName() );
//					sellerCommodityService.insert(sellerCommodityEntity);
//				}
//				
//				// TODO 增加代理日志
//			}
//		}
//
//	}

	@Override
	public List<CommodityStyleEntity> queryExportCommodityInfo(List<String> list) {
		return commodityStyleMapper.queryExportCommodityInfo(list);
	}

	@Override
	public void readjustPrices(List<CommodityStyleEntity> commodtyList) {
		commodityStyleMapper.readjustPrices(commodtyList);
	}

	@Override
	public void batchReadjustPrices(List<CommodityStyleEntity> commodtyList) {
		if (commodtyList != null && commodtyList.size() > 0) {
			for (int i = 0; i < commodtyList.size(); i++) {
				commodityStyleMapper.batchReadjustPrices(commodtyList.get(i));
			}
		}
	}

	@Override
	public int findInventoryPageCount(CommodityInventoryEntity commodityInventoryEntity) {
		return commodityStyleMapper.findInventoryPageCount(commodityInventoryEntity);
	}

	@Override
	public List<CommodityInventoryEntity> findInventoryPage(CommodityInventoryEntity commodityInventoryEntity, RowBounds rowBounds) {
		return commodityStyleMapper.findInventoryPage(commodityInventoryEntity, rowBounds);
	}

	@Override
	public int queryListCount(CommodityStyleEntity commodityStyleEntity) {
		return commodityStyleMapper.queryListCount(commodityStyleEntity);
	}

	@Override
	public List<CommodityStyleEntity> queryList(CommodityStyleEntity commodityStyleEntity, RowBounds rowBounds) {
		return commodityStyleMapper.queryList(commodityStyleEntity, rowBounds);
	}

	@Override
	public List<CommodityStyleEntity> queryCommodityListByIds(List<String> commodityIds) {
		return commodityStyleMapper.findPageByCommodityIds(commodityIds);
	}

	@Override
	public int getStock(@NotBlank String commodityId) {
		return commodityStyleMapper.getStock(commodityId);
	}

	@Override
	public List<CommodityStyleEntity> getSameSellerCommodity(@NotBlank String commodityId) throws Exception {
		return commodityStyleMapper.getSameSellerCommodity(commodityId);
	}

	@Override
	public List<CommodityStyleEntity> queryProxyCommodity(@NotBlank String sellerId, @NotNull RowBounds rowBounds) throws Exception {
		return commodityStyleMapper.queryProxyCommodity(sellerId, rowBounds);
	}

	@Override
	public int queryProxyCommodityCount(@NotBlank String sellerId) throws Exception {
		return commodityStyleMapper.queryProxyCommodityCount(sellerId);
	}

	@Override
	public void updateCommodityClassify(List<Map<String, String>> map) throws Exception {
		commodityStyleMapper.updateCommodityClassify(map);

	}

	@Override
	public void deleteDellerCommodityCatRela(List<String> commodityId) throws Exception {
		commodityStyleMapper.deleteDellerCommodityCatRela(commodityId);

	}

	@Override
	public PageModel<CommodityStyleOutputDto> queryHotCommodity(@NotBlank String shopId,PageModel pageModel) throws Exception {

		// 封装查询信息
		Map<String, Object> hotMap = new HashMap<String, Object>();
		hotMap.put("shopId", shopId);
		//hotMap.put("count", count);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		List<CommodityStyleEntity> styleEntity = commodityStyleMapper.queryHotCommodity(hotMap,rowBounds);
		
		int countPage = commodityStyleMapper.queryHotCommodityCount(hotMap);
		
	//	List<CommodityStyleOutputDto> styleDtoList = (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class);
		
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(), pageModel.getLimit(), countPage, (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class));
		
	}
	@Override
	public List<CommodityStyleOutputDto> queryHotCommodityList(@NotBlank String shopId, int count) throws Exception {

		// 封装查询信息
		Map<String, Object> hotMap = new HashMap<String, Object>();
		hotMap.put("shopId", shopId);
		hotMap.put("count", count);
		//RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		List<CommodityStyleEntity> styleEntity = commodityStyleMapper.queryHotCommodityList(hotMap);
		
	//	int countPage = commodityStyleMapper.queryHotCommodityCount(hotMap);
		
	    List<CommodityStyleOutputDto> styleDtoList = (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class);
		return styleDtoList;
		//return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(), pageModel.getLimit(), countPage, (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class));
		
	}

	@Override
	public PageModel<CommodityStyleOutputDto> getShopCategoryCommodity(@NotBlank String categoryId, @NotBlank String shopId, @NotNull PageModel pageModel) throws Exception {
		Map<String, Object> shopCategoryMap = new HashMap<String, Object>();
		shopCategoryMap.put("categoryId", categoryId);
		shopCategoryMap.put("shopId", shopId);

		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int count = commodityStyleMapper.getShopCategoryCommodityCount(shopCategoryMap);

		List<CommodityStyleEntity> styleEntity = commodityStyleMapper.getShopCategoryCommodity(shopCategoryMap, rowBounds);

		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(), pageModel.getLimit(), count, (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class));
	}

	@Override
	public List<CommodityStyleOutputDto> getCommodityByNos(@NotNull List<String> nos) throws Exception {
		List<CommodityStyleEntity> styleEntity = commodityStyleMapper.getCommodityByNos(nos);

		List<CommodityStyleOutputDto> styleDtoList = (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class);

		return styleDtoList;
	}

	@Override
	public List<CommodityStyleOutputDto> getCommodityByParameter(Map<String, Object> paramMap) {
		List<CommodityStyleEntity> styleEntityList = commodityStyleMapper.getCommodityByParameter(paramMap);

		List<CommodityStyleOutputDto> styleDtoList = (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntityList, CommodityStyleOutputDto.class);
		return styleDtoList;
	}

	@Override
	public CommodityStyleEntity getByNo(String no) {
		return commodityStyleMapper.getByNo(no);
	}

	@Override
	public int countCommodityStyleParams() {
		// 批量更新代理数量
//		int proxyQuantityNum = commodityStyleMapper.batchUpdateProxyQuantity();
		// 批量更新销售数量
		int saleQuantityNum = commodityStyleMapper.batchUpdateSaleQuantity();
		return saleQuantityNum;
	}
	@Override
	public List<String> getCatId(String commodityId) {
		return commodityStyleMapper.getCatId(commodityId);
	}

	@Override
	public PageModel<CommodityStyleOutputDto> getCommodityByBrandNo(
			@NotBlank String brandNo,String sllerId, @NotNull PageModel pageModel) {
	
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		ICommodityStyleService commodityStyleService = SpringContextHolder.getBean(ICommodityStyleService.class);
		int count=commodityStyleMapper.getCommodityByBrandNoCount(brandNo);
		Map<String,String>  brandInfoMap=new HashMap<String, String>();
		brandInfoMap.put("brandNo", brandNo);
		brandInfoMap.put("sllerId", sllerId);
		List<CommodityStyleEntity> styleEntity=commodityStyleMapper.getCommodityByBrandNo(brandInfoMap,rowBounds);
		//获取佣金
		if(styleEntity!=null && styleEntity.size()>0){
			for (int i = 0; i < styleEntity.size(); i++) {
				CommodityStyleEntity  style=styleEntity.get(i);
				String picBig= commodityStyleService.getPicBigName(style.getNo());
				 String path= PicPathUtil.getPicServicePath()+picBig;
				 style.setPicBig(path);
				//暂时不展示佣金 2016-06-07
//				List<String> catIdList=commodityStyleMapper.getCatId(style.getId());
//				List<CommissionPercentOutputDto> commissionPercents = commissionPercentFrontApi.getByBaseCatId(catIdList);
//				if(null == commissionPercents || commissionPercents.size() <= 0){
//					throw new RuntimeException("查询佣金比例出错");
//				}
//				CommissionPercentOutputDto commissionPercent = commissionPercents.get(0);
//				Double commissionLevel1Percent = commissionPercent.getCommissionLevel1Percent();
//				Double commissionLevel2Percent = commissionPercent.getCommissionLevel2Percent();
//				Double commissionLevel3Percent = commissionPercent.getCommissionLevel3Percent();
//				Double wfxPrice = style.getWfxPrice();
//				if(wfxPrice==null){
//					wfxPrice=new Double(0);
//				}
//				style.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1Percent*wfxPrice));
//				style.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2Percent*wfxPrice));
//				style.setCommissionLevel3(MathUtil.roundHalfUp(commissionLevel3Percent*wfxPrice));
				style.setCommissionLevel1(0d);
				style.setCommissionLevel2(0d);
				style.setCommissionLevel3(0d);
			}
		}
		
	
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), count,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity,CommodityStyleOutputDto.class));
	}

	@Override
	public PageModel<CommodityStyleOutputDto> getCommodityByCatId(@NotBlank String catId,@NotBlank String sllerId,
			@NotNull PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		ICommodityStyleService commodityStyleService = SpringContextHolder.getBean(ICommodityStyleService.class);
		int count=commodityStyleMapper.getCommodityByCatIdCount(catId);
		Map<String,String>  catInfoMap=new HashMap<String, String>();
		catInfoMap.put("catId", catId);
		catInfoMap.put("sllerId", sllerId);
		List<CommodityStyleEntity> styleEntity=commodityStyleMapper.getCommodityByCatId(catInfoMap,rowBounds);
		//获取佣金
		if(styleEntity!=null && styleEntity.size()>0){
			for (int i = 0; i < styleEntity.size(); i++) {
				CommodityStyleEntity  style=styleEntity.get(i);
				String picBig= commodityStyleService.getPicBigName(style.getNo());
				 String path= PicPathUtil.getPicServicePath()+picBig;
				 style.setPicBig(path);
				 //暂时不展示佣金 2016-06-07
//				List<String> catIdList=commodityStyleMapper.getCatId(style.getId());
//				List<CommissionPercentOutputDto> commissionPercents = commissionPercentFrontApi.getByBaseCatId(catIdList);
//				if(null == commissionPercents || commissionPercents.size() <= 0){
//					throw new RuntimeException("查询佣金比例出错");
//				}
//				CommissionPercentOutputDto commissionPercent = commissionPercents.get(0);
//				Double commissionLevel1Percent = commissionPercent.getCommissionLevel1Percent();
//				Double commissionLevel2Percent = commissionPercent.getCommissionLevel2Percent();
//				Double commissionLevel3Percent = commissionPercent.getCommissionLevel3Percent();
//				Double wfxPrice = style.getWfxPrice();
//				if(wfxPrice==null){
//					wfxPrice=new Double(0);
//				}
//				style.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1Percent*wfxPrice));
//				style.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2Percent*wfxPrice));
//				style.setCommissionLevel3(MathUtil.roundHalfUp(commissionLevel3Percent*wfxPrice));
				style.setCommissionLevel1(0d);
				style.setCommissionLevel2(0d);
				style.setCommissionLevel3(0d);
			}
		}
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), count,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity,CommodityStyleOutputDto.class));
	}

	@Override
	public PageModel<CommodityStyleOutputDto> queryCommodityListForSearch(@NotBlank String searchName,@NotBlank String sllerId,
			@NotNull PageModel pageModel) throws Exception{
		
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		
		int count=commodityStyleMapper.queryCommodityListForSearchCount(searchName);
		Map<String,String>  searchInfoMap=new HashMap<String, String>();
		searchInfoMap.put("searchName", searchName);
		searchInfoMap.put("sllerId", sllerId);
		List<CommodityStyleEntity> styleEntity=commodityStyleMapper.queryCommodityListForSearch(searchInfoMap,rowBounds);
		
		//获取佣金
		if(styleEntity!=null && styleEntity.size()>0){
			for (int i = 0; i < styleEntity.size(); i++) {
				CommodityStyleEntity  style=styleEntity.get(i);
				String path= PicPathUtil.getPicServicePath()+style.getPicBig();
				style.setPicBig(path);
//				List<String> catIdList=commodityStyleMapper.getCatId(style.getId());
//				List<CommissionPercentOutputDto> commissionPercents = commissionPercentFrontApi.getByBaseCatId(catIdList);
//				if(null == commissionPercents || commissionPercents.size() <= 0){
//					throw new RuntimeException("查询佣金比例出错");
//				}
//				CommissionPercentOutputDto commissionPercent = commissionPercents.get(0);
				
				List<String> baseCatIds = commodityStyleMapper.getCatId(style.getId());
				CommissionPercentOutputDto commissionPercent = commissionPercentFrontApi.getCommissionByCondition(style.getBrandNo(), baseCatIds != null ? baseCatIds.get(0) : "", style.getId());
				if(null == commissionPercent){
					throw new RuntimeException("查询佣金比例出错");
				}
				Double commissionLevel1Percent = commissionPercent.getCommissionLevel1Percent();
				Double commissionLevel2Percent = commissionPercent.getCommissionLevel2Percent();
				Double commissionLevel3Percent = commissionPercent.getCommissionLevel3Percent();
				Double wfxPrice = style.getWfxPrice();
				if(wfxPrice==null){
					wfxPrice=new Double(0);
				}
				style.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1Percent*wfxPrice));
				style.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2Percent*wfxPrice));
				style.setCommissionLevel3(MathUtil.roundHalfUp(commissionLevel3Percent*wfxPrice));
			}
		}
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), count,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity,CommodityStyleOutputDto.class));
	}

	@Override
	public int clearCommodityStylePicNotSPYG() {
		return commodityStyleMapper.clearPicNotSPYG();
	}

	@Override
	public Double computeDistributableMoney( Double wfxPrice, Double costPrice){
		/**
		 * 计算公式=（（除税微分销零售价-除税真实成本）-运费）/（1+个人所得税率）
		 */
		if( wfxPrice==null || wfxPrice<0 || costPrice==null || costPrice<0 ){
			logger.info("根据公式计算可分配佣金金额失败，公式中有参数未设置：wfxPrice -"+wfxPrice+"，costPrice-"+costPrice);
			return null;
		}
		String postFee = sysConfigService.getValueBykey( ApiConstant.WFX_POSTFEE );//运费
		String taxRate = sysConfigService.getValueBykey( ApiConstant.TAX_RATE);//个人所得税率
		String vatRate = sysConfigService.getValueBykey( ApiConstant.VAT_RATE );//增值税税率
		postFee = (postFee==null)?"0":postFee;
		taxRate = (taxRate==null)?"0":taxRate;
		vatRate = (vatRate==null)?"0":vatRate;
		Double result = MathUtil.formula1st( wfxPrice, costPrice, new BigDecimal(vatRate.trim()),
											new BigDecimal(taxRate.trim()), new BigDecimal(postFee.trim()) ) ;
		return result;
		
	}

	@Override
	public PageModel<CommodityStyleOutputDto> queryDiscoverStyleInfo(
			CommodityStyleEntity commodityStyleEntity, PageModel pageModel) {
		
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		
		int count=commodityStyleMapper.queryDiscoverStyleInfoCount(commodityStyleEntity);

	
		List<CommodityStyleEntity> styleEntity=commodityStyleMapper.queryDiscoverStyleInfo(commodityStyleEntity,rowBounds);
		
		//获取佣金
		if(styleEntity!=null && styleEntity.size()>0){
			for (int i = 0; i < styleEntity.size(); i++) {
				CommodityStyleEntity  style=styleEntity.get(i);
				String path= PicPathUtil.getPicServicePath()+style.getPicBig();
				style.setPicBig(path);
			}
		}
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), count,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity,CommodityStyleOutputDto.class));
	}

	@Override
	@CacheCable(key="'wfx:commodityPicS:'+#commodityNo" ,value="value",expiration=10*24*60*60)
	public String getPicBigName(String commodityNo) {
		return commodityStyleMapper.getPicBigName(commodityNo);
	}

	@Override
	public PageModel<CommodityStyleOutputDto> queryCommodityOrder(
			CommodityStyleOrderInputDto inputDto, PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
	
		CommodityStyleOrderEntity commodityStyleEntity = (CommodityStyleOrderEntity)BeanUtil.convertBean(inputDto, CommodityStyleOrderEntity.class);
		commodityStyleEntity.setCatNoList(inputDto.getCatNoList());
		commodityStyleEntity.setBrandNoList(inputDto.getBrandNoList());
		commodityStyleEntity.setPropValueNolist(inputDto.getPropValueNolist());
		commodityStyleEntity.setSizeNoList(inputDto.getSizeNoList());
		commodityStyleEntity.setSpecNameList(inputDto.getSpecNameList());
		List<CommodityStyleEntity> styleEntity = commodityStyleMapper.queryCommodityOrder(commodityStyleEntity,rowBounds);
		int countPage = commodityStyleMapper.queryCommodityOrderCount(commodityStyleEntity);
		
		
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(), pageModel.getLimit(), countPage, (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntity, CommodityStyleOutputDto.class));
	
	}

	@Override
	public List<CommodityStyleFilterOutputDto> getBrandInfo(
			CommodityStyleOrderInputDto inputDto) {
		CommodityStyleFilterEntity commodityStyleFilterEntity = (CommodityStyleFilterEntity)BeanUtil.convertBean(inputDto, CommodityStyleFilterEntity.class);
		List<CommodityStyleFilterEntity>  entityList=commodityStyleMapper.getBrandInfo(commodityStyleFilterEntity);
		List<CommodityStyleFilterOutputDto> commodityStyleFilterOutputDto = (List<CommodityStyleFilterOutputDto>)BeanUtil.convertBeanList(entityList, CommodityStyleFilterOutputDto.class);
		return commodityStyleFilterOutputDto;
	}

	@Override
	public List<CommodityPropertyOutputDto> getPropByCommodityId(String commodityNo) {
		
		 List<CommodityPropertyOutputDto>  outputDtoList=commodityStyleMapper.getPropByCommodityId(commodityNo);
		 List<CommodityPropertyOutputDto>  lst=new ArrayList<CommodityPropertyOutputDto>();
		//去除重复的三级分类
		if(outputDtoList!=null && outputDtoList.size()>0){
			for (int i = 0; i < outputDtoList.size(); i++) {
				CommodityPropertyOutputDto  propertyOutputDto=outputDtoList.get(i);
				String propItemName=propertyOutputDto.getPropItemName();         
				String newPropItemName=propItemName;
				String newPropValue=propertyOutputDto.getPropValue();
				for (int j = i+1; j < outputDtoList.size(); j++) {
					CommodityPropertyOutputDto  outputDto=outputDtoList.get(j);
					if(StringUtils.isNotBlank(propItemName) && propItemName.equals(outputDto.getPropItemName()) ){
						newPropValue=newPropValue+"/"+outputDto.getPropValue();
						outputDtoList.remove(j);
					}
				}
				CommodityPropertyOutputDto  commodityPropertyOutputDto=new CommodityPropertyOutputDto();
				commodityPropertyOutputDto.setPropItemName(newPropItemName);
				commodityPropertyOutputDto.setPropValue(newPropValue);
				lst.add(commodityPropertyOutputDto);
				
			}
			
		}
		return lst;
	}

	@Override
	public List<CommodityStyleFilterOutputDto> getCatInfo(
			CommodityStyleOrderInputDto inputDto) {
		CommodityStyleFilterEntity commodityStyleFilterEntity = (CommodityStyleFilterEntity)BeanUtil.convertBean(inputDto, CommodityStyleFilterEntity.class);
		List<CommodityStyleFilterEntity>  entityList=commodityStyleMapper.getCatInfo(commodityStyleFilterEntity);
		List<CommodityStyleFilterOutputDto> commodityStyleFilterList = (List<CommodityStyleFilterOutputDto>)BeanUtil.convertBeanList(entityList, CommodityStyleFilterOutputDto.class);
		List<CommodityStyleFilterOutputDto>  lst=new  ArrayList<CommodityStyleFilterOutputDto>();
		//去除重复的三级分类
		if(commodityStyleFilterList!=null && commodityStyleFilterList.size()>0){
			for (int i = 0; i < commodityStyleFilterList.size(); i++) {
				CommodityStyleFilterOutputDto  filterOutputDto=commodityStyleFilterList.get(i);
				String catName=filterOutputDto.getCatName();
				String newCatName=catName;
				String newCatNo=filterOutputDto.getCatNo();
				for (int j = i+1; j < commodityStyleFilterList.size(); j++) {
					CommodityStyleFilterOutputDto  outputDto=commodityStyleFilterList.get(j);
					if(StringUtils.isNotBlank(catName) && catName.equals(outputDto.getCatName()) ){
						newCatNo=newCatNo+"#"+outputDto.getCatNo();
						commodityStyleFilterList.remove(j);
					}
				}
				CommodityStyleFilterOutputDto  commodityStyleFilterOutputDto=new CommodityStyleFilterOutputDto();
				commodityStyleFilterOutputDto.setCatName(newCatName);
				commodityStyleFilterOutputDto.setCatNo(newCatNo);
				lst.add(commodityStyleFilterOutputDto);
				
			}
			
		}
		
		
		return lst;
	}

	@Override
	public List<String> getPriceInfo(CommodityStyleOrderInputDto inputDto) {
		CommodityStyleFilterEntity commodityStyleFilterEntity = (CommodityStyleFilterEntity)BeanUtil.convertBean(inputDto, CommodityStyleFilterEntity.class);
		List<CommodityStyleFilterEntity>  entityList=commodityStyleMapper.getPriceInfo(commodityStyleFilterEntity);
		//拼装价格区间数据
		List<String>  list=new ArrayList<String>();
 		if(entityList!=null && entityList.size()>0){
 			 Set<String> nameSet = new HashSet<String>();
 			 for (int i = 0; i < entityList.size(); i++) {
 				CommodityStyleFilterEntity   entity=entityList.get(i);
 				Integer minPrice=entity.getMinPrice();
 				
 				if(minPrice >=1 && minPrice<=50){
 					nameSet.add("1-50");
 				}if(minPrice >=51 && minPrice<=100){
 					nameSet.add("51-100");
 				}if(minPrice >=101 &&  minPrice<=200){
 					nameSet.add("101-200");
 				}if(minPrice >=201 &&  minPrice<=300){
 					nameSet.add("201-300");
 				}if(minPrice >=301 &&  minPrice<=500){
 					nameSet.add("301-500");
 				}if(minPrice >=501 &&  minPrice<=700){
 					nameSet.add("501-700");
 				}if(minPrice >=701 && minPrice<=1000){
 					nameSet.add("701-1000");
 				}if(minPrice>=1001){
 					nameSet.add("1000以上");
 				}
 			 }
 			 for (int i = 0; i < 10; i++) {
 				 list.add("");
			 }
 			 for(String price : nameSet){ 
 				if("1-50".equals(price)){
 					list.set(0, price);
 				}if("51-100".equals(price)){
 					list.set(1, price);
 				}if("101-200".equals(price)){
 					list.set(2, price);
 				}if("201-300".equals(price)){
 					list.set(3, price);
 				}if("301-500".equals(price)){
 					list.set(4, price);
 				}if("501-700".equals(price)){
 					list.set(5, price);
 				}if("701-1000".equals(price)){
 					list.set(6, price);
 				}if("1000以上".equals(price)){
 					list.set(7, price);
 				}
 				
 			 }  
		}
 		return list;
	}

	@Override
	public List<CommodityStyleFilterOutputDto> getSizeInfo(
			CommodityStyleOrderInputDto inputDto) {
		CommodityStyleFilterEntity commodityStyleFilterEntity = (CommodityStyleFilterEntity)BeanUtil.convertBean(inputDto, CommodityStyleFilterEntity.class);
		List<CommodityStyleFilterEntity>  entityList=commodityStyleMapper.getSizeInfo(commodityStyleFilterEntity);
		List<CommodityStyleFilterOutputDto> commodityStyleFilterOutputDto = (List<CommodityStyleFilterOutputDto>)BeanUtil.convertBeanList(entityList, CommodityStyleFilterOutputDto.class);
		return commodityStyleFilterOutputDto;
	}

	@Override
	public List<CommodityStyleFilterOutputDto> getSpecInfo(
			CommodityStyleOrderInputDto inputDto) {
		CommodityStyleFilterEntity commodityStyleFilterEntity = (CommodityStyleFilterEntity)BeanUtil.convertBean(inputDto, CommodityStyleFilterEntity.class);
		List<CommodityStyleFilterEntity>  entityList=commodityStyleMapper.getSpecInfo(commodityStyleFilterEntity);
		List<CommodityStyleFilterOutputDto> commodityStyleFilterOutputDto = (List<CommodityStyleFilterOutputDto>)BeanUtil.convertBeanList(entityList, CommodityStyleFilterOutputDto.class);
		return commodityStyleFilterOutputDto;
	}

	@Override
	public List<CommodityStyleFilterOutputDto> getPropInfo(
			CommodityStyleOrderInputDto inputDto) {
		CommodityStyleFilterEntity commodityStyleFilterEntity = (CommodityStyleFilterEntity)BeanUtil.convertBean(inputDto, CommodityStyleFilterEntity.class);
		List<CommodityStyleFilterEntity>  entityList=commodityStyleMapper.getPropInfo(commodityStyleFilterEntity);
		List<CommodityStyleFilterOutputDto> commodityStyleFilterOutputDto = (List<CommodityStyleFilterOutputDto>)BeanUtil.convertBeanList(entityList, CommodityStyleFilterOutputDto.class);
		return commodityStyleFilterOutputDto;
	}

	@Override
	public String getPictureUrl(String no) {
		
		return commodityStyleMapper.getPictureUrl(no);
	}

	@Override
	public List<CommodityStyleOutputDto> queryCommodityList(
			List<String> lstSupplierCode, List<String> lstCommodityId) {
		List<CommodityStyleEntity>  entityList=commodityStyleMapper.queryCommodityList(lstSupplierCode, lstCommodityId);
		for(CommodityStyleEntity style : entityList){
			style.setPicBig(PicPathUtil.getPicServicePath()+getPicBigName(style.getNo()));
		}
		List<CommodityStyleOutputDto> commodityStyleOutputDto = (List<CommodityStyleOutputDto>)BeanUtil.convertBeanList(entityList, CommodityStyleOutputDto.class);
		return commodityStyleOutputDto;
	}

}
