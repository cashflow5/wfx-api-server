package com.yougou.wfx.cms.api.front.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.cms.api.front.IGoodsMarketFrontApi;
import com.yougou.wfx.cms.dto.output.CarouselFigureOutputDto;
import com.yougou.wfx.cms.dto.output.HotBrandOutputDto;
import com.yougou.wfx.cms.dto.output.HotSaleCatOutputDto;
import com.yougou.wfx.cms.model.CarouselFigureEntity;
import com.yougou.wfx.cms.model.CommoditySaleCatEntity;
import com.yougou.wfx.cms.service.ICarouselFigureService;
import com.yougou.wfx.cms.service.ICommoditySaleCatService;
import com.yougou.wfx.commodity.dto.output.BagOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.model.BagEntity;
import com.yougou.wfx.commodity.model.CommodityBrandEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.commodity.service.IBagService;
import com.yougou.wfx.commodity.service.ICommodityBrandService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.CarouseFigureTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.seller.service.ISellerInfoService;
import com.yougou.wfx.util.ApiConstant;
import com.yougou.wfx.utils.PicPathUtil;

@Service
public class GoodsMarketFrontApiImpl implements IGoodsMarketFrontApi {
	
	Logger logger = LoggerFactory.getLogger(GoodsMarketFrontApiImpl.class);
	
	@Resource
	ICarouselFigureService carouselFigureService;
	
	@Resource
	ICommodityBrandService commodityBrandService;
	
	@Resource
	ICommoditySaleCatService commoditySaleCatService;
	
	@Resource
	private IBagService bagService;
	
	@Resource
	private ISellerCommodityService sellerCommodityService;
	
	@Resource 
	private ICommodityStyleService commodityStyleService;
	
	@Resource
	private ISellerInfoService sellerInfoService;

	@Override
	@LoggerProfile(methodNote="查询轮播图列表")
	public List<CarouselFigureOutputDto> queryCarouselFigureList() {
		CarouselFigureEntity  carouselFigureEntity = new CarouselFigureEntity();
		carouselFigureEntity.setStatus(2);
		carouselFigureEntity.setType(CarouseFigureTypeEnum.APP_SUPPLY_MARKET.getKey());
		
		RowBounds rowBounds = new RowBounds();		
		List<CarouselFigureEntity> lstCarouselFigures = carouselFigureService.findPage(carouselFigureEntity, rowBounds);
		for(CarouselFigureEntity entity : lstCarouselFigures){
			entity.setPicUrl(PicPathUtil.getImageAbsUrl(entity.getPicUrl()));
		}
		
		return (List<CarouselFigureOutputDto>) BeanUtil.convertBeanList(lstCarouselFigures, CarouselFigureOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="根据热门品牌列表")
	public List<HotBrandOutputDto> queryHotBrandList() {
		CommodityBrandEntity commodityBrandEntity = new CommodityBrandEntity();
		commodityBrandEntity.setHotFlag(1);
		commodityBrandEntity.setStatus(1);
		commodityBrandEntity.setUseFlag(1);
		
		RowBounds rowBounds = new RowBounds();	
		List<CommodityBrandEntity> lstCommodityBrand = commodityBrandService.findPage(commodityBrandEntity, rowBounds);
		for(CommodityBrandEntity entity : lstCommodityBrand){
			//兼容新老数据
			if(StringUtils.isNotBlank(entity.getMobilePic()) && StringUtils.indexOf(entity.getMobilePic(), "http") < 0){
				entity.setMobilePic(PicPathUtil.getImageAbsUrl(entity.getMobilePic()));
			}
		}
		return (List<HotBrandOutputDto>) BeanUtil.convertBeanList(lstCommodityBrand, HotBrandOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="查询热门销售分类列表")
	public List<HotSaleCatOutputDto> queryHotSaleCatList() {
		CommoditySaleCatEntity commoditySaleCatEntity = new CommoditySaleCatEntity();
		commoditySaleCatEntity.setHotFlag(1);
		commoditySaleCatEntity.setDeleteFlag(2);
		
		List<CommoditySaleCatEntity> lstCommoditySaleCats = commoditySaleCatService.queryHotCatList(commoditySaleCatEntity);
		for(CommoditySaleCatEntity entity : lstCommoditySaleCats){
			entity.setPicUrl(PicPathUtil.getImageAbsUrl(entity.getPicUrl()));
		}
		return (List<HotSaleCatOutputDto>) BeanUtil.convertBeanList(lstCommoditySaleCats,HotSaleCatOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="根据分销包列表")
	public List<BagOutputDto> queryBagList() {
		BagEntity bagEntity = new BagEntity();
		bagEntity.setStatus(3);
		RowBounds rowBounds = new RowBounds();

		List<BagEntity> lstBags = bagService.findPage(bagEntity, rowBounds);

		return (List<BagOutputDto>) BeanUtil.convertBeanList(lstBags,BagOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="根据分销包ID查询分销包")
	public BagOutputDto getBagById(@NotBlank String bagId) {
		BagEntity bagEntity = bagService.getById(bagId);
		return (BagOutputDto)BeanUtil.convertBean(bagEntity, BagOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="查询分销包商品列表")
	public PageModel<CommodityStyleOutputDto> queryBagCommodityList(@NotBlank String bagId, @NotNull PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		List<CommodityStyleEntity> lstCommodityStyle = bagService.findBagCommodityList(bagId, rowBounds);
		int totalCount = bagService.findBagCommodityCount(bagId);
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(lstCommodityStyle,CommodityStyleOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="代理分销包")
	public WFXResult<Integer> proxyBagById(@NotBlank String bagId, @NotBlank String sellerId) {
		WFXResult<Integer> result = new WFXResult<Integer>();
		try{
			//校验分销商是否停止合作，是否停用店铺，暂无
			SellerInfoEntity sellerInfo = sellerInfoService.getById(sellerId);
			if(null == sellerInfo){
				result.setResultCode(ResultCodeEnum.FAILURE.getKey());
				result.setResult(0);
				result.setResultMsg("分销商不存在！");
				return result;
			}
			RowBounds rowBounds = new RowBounds();
			List<CommodityStyleEntity> lstCommodityStyle = bagService.findBagCommodityList(bagId, rowBounds);
			int count = batchProxyCommodity(sellerInfo, lstCommodityStyle);
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult(count);
		}catch(Exception e){
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult(0);
			result.setResultMsg("代理分销包出现异常"+e);
		}
		return result;
	}

	@Override
	@LoggerProfile(methodNote="批量代理商品")
	public WFXResult<Integer> batchProxyCommodity(@NotBlank String sellerId, @NotNull List<String> style) {
		WFXResult<Integer> result = new WFXResult<Integer>();
		try{
			//校验分销商是否停止合作，是否停用店铺，暂无
			SellerInfoEntity sellerInfo = sellerInfoService.getById(sellerId);
			if(null == sellerInfo){
				result.setResultCode(ResultCodeEnum.FAILURE.getKey());
				result.setResult(0);
				result.setResultMsg("分销商不存在！");
				return result;
			}
			CommodityStyleEntity params = new CommodityStyleEntity();
			List<SellerCommodityEntity> lstSellerCommodity = new ArrayList<SellerCommodityEntity>();
			SellerCommodityEntity sellerCommodity = null;
			int proxyCount = 0;
			for(String styleNo : style){
				String[] styleArr = styleNo.split(",");
				if(styleArr.length<4){
					continue;
				}
				params = new CommodityStyleEntity();
				params.setStyleNo(styleArr[0]);
				params.setBrandNo(styleArr[1]);
				params.setCatNo(styleArr[2]);
				params.setYears(styleArr[3]);
				params.setIsOnsale(1);
				params.setIsWfxCommodity(1);
				int commodityCount = commodityStyleService.queryListCount(params);
				if(commodityCount == 0){
					continue;
				}
				//校验是否已经代理
				sellerCommodity = new SellerCommodityEntity();
				sellerCommodity.setCommodityStyleNo(params.getStyleNo());
				sellerCommodity.setCommodityBrandNo(params.getBrandNo());
				sellerCommodity.setCommodityCatNo(params.getCatNo());
				sellerCommodity.setCommodityYears(params.getYears());
				sellerCommodity.setSellerId(sellerInfo.getId());
				int existCount = sellerCommodityService.findPageCount(sellerCommodity);
				if(existCount>0){
					proxyCount++;
					continue;
				}
				sellerCommodity.setSellerName(sellerInfo.getSellerName());
				sellerCommodity.setStatus(1);
				sellerCommodity.setId(UUIDGenerator.get32LowCaseUUID());
				sellerCommodity.setCreateTime(new Date(System.currentTimeMillis()));
				sellerCommodity.setUpdateTime(new Date(System.currentTimeMillis()));
				lstSellerCommodity.add(sellerCommodity);
			}
			
			int count = 0; 
			if(lstSellerCommodity.size() > 0){
				count = sellerCommodityService.batchInsertSellerCommodity(lstSellerCommodity);
			}
			if(count > 0){
				result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			}else{
				result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			}
			if(proxyCount>0){
				result.setResultMsg(proxyCount+"款商品已经代理！");
			}
			result.setResult(count);
		}catch(Exception e){
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult(0);
			logger.error("批量代理商品出现异常",e);
			result.setResultMsg("批量代理商品出现异常,请重试！");
		}
		return result;
	}
	
	private int batchProxyCommodity(SellerInfoEntity sellerInfo, List<CommodityStyleEntity> lstCommodityStyle) {
		//批量新增代理商品关系表数据
		SellerCommodityEntity sellerCommodity = null;
		List<SellerCommodityEntity> lstSellerCommodity = new ArrayList<SellerCommodityEntity>();
		for(CommodityStyleEntity commodityStyle : lstCommodityStyle){
			//校验是否上架
			if(1 != commodityStyle.getIsOnsale()){
				continue;	
			}
			//校验是否已经代理
			sellerCommodity = new SellerCommodityEntity();
			sellerCommodity.setCommodityStyleNo(commodityStyle.getStyleNo());
			sellerCommodity.setCommodityBrandNo(commodityStyle.getBrandNo());
			sellerCommodity.setCommodityCatNo(commodityStyle.getCatNo());
			sellerCommodity.setCommodityYears(commodityStyle.getYears());
			sellerCommodity.setSellerId(sellerInfo.getId());
			int count = sellerCommodityService.findPageCount(sellerCommodity);
			if(count>0){
				continue;
			}
			sellerCommodity.setSellerName(sellerInfo.getSellerName());
			sellerCommodity.setStatus(1);
			sellerCommodity.setId(UUIDGenerator.get32LowCaseUUID());
			sellerCommodity.setCreateTime(new Date(System.currentTimeMillis()));
			sellerCommodity.setUpdateTime(new Date(System.currentTimeMillis()));
			lstSellerCommodity.add(sellerCommodity);
		}
		int count = 0; 
		if(lstSellerCommodity.size() > 0){
			count = sellerCommodityService.batchInsertSellerCommodity(lstSellerCommodity);
		}
		return count;
	}

	@Override
	public WFXResult<Integer> proxyAll(String sellerId) {
		WFXResult<Integer> result = new WFXResult<Integer>();
		try{
			//校验分销商是否停止合作，是否停用店铺，暂无
			SellerInfoEntity sellerInfo = sellerInfoService.getById(sellerId);
			if(null == sellerInfo){
				result.setResultCode(ResultCodeEnum.FAILURE.getKey());
				result.setResult(0);
				result.setResultMsg("分销商不存在！");
				return result;
			}
			String originalSellerId =  ApiConstant.ORIGINAL_SELLER_ID_DEFAULT;
//			int count = sellerCommodityService.proxyAll(sellerInfo,originalSellerId);
//			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
//			result.setResult(count);
		}catch(Exception e){
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult(0);
			result.setResultMsg("代理所有上架商品出现异常："+e);
		}
		return result;
	}

}
