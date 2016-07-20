package com.yougou.wfx.commodity.api.front.impl;
/*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.pc.model.commodityinfo.Commodity;
import com.yougou.pc.model.picture.Picture;
import com.yougou.tools.common.utils.StringUtil;
import com.yougou.wfx.commodity.api.front.ICommodityStyleFrontApi;
import com.yougou.wfx.commodity.dto.input.CheckProductNumberInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStyleOrderInputDto;
import com.yougou.wfx.commodity.dto.output.CheckProductNumberDetailsOutputDto;
import com.yougou.wfx.commodity.dto.output.CheckProductNumberOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityPicsOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityProductOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityPropertyOutputDto;
import com.yougou.wfx.commodity.dto.output.CommoditySimplifyStyleOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleFilterOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.dto.output.SellerCommodityCatRelaOutpuDto;
import com.yougou.wfx.commodity.model.CommodityProductEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.commodity.service.ICommodityProductService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.finance.api.front.ICommissionPercentFrontApi;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.proxy.commodity.CommodityProxyApi;
import com.yougou.wfx.seller.model.SellerCatEntity;
import com.yougou.wfx.seller.service.ISellerCatService;
import com.yougou.wfx.utils.PicPathUtil;
/**
 * ICommodityStyleFrontApi实现
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
@Service
public class CommodityStyleFrontApiImpl implements ICommodityStyleFrontApi{
	
	private Logger logger = LoggerFactory.getLogger(CommodityStyleFrontApiImpl.class);
	
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource
	ISellerCatService sellerCatService;
	@Resource
	ISellerCommodityService sellerCommodityService;
	@Resource
	ICommodityProductService commodityProductService;
	@Resource
	private ICommissionPercentFrontApi commissionPercentFrontApi;
	
	
	@Override
	@LoggerProfile(methodNote = "查询分销商指定数量的热卖商品列表分页")
	public PageModel<CommodityStyleOutputDto> queryHotCommodity(@NotBlank String shopId,@NotNull PageModel pageModel) {
		//logger.info("queryHotCommodity传入参数shopId："+shopId  +"count:"+count);
		try {
			return commodityStyleService.queryHotCommodity(shopId,pageModel);
		} catch (Exception e) {
			logger.error("查询分销商指定数量的热卖商品列表异常", e);
		}
	    return null;
	}
	@Override
	@LoggerProfile(methodNote = "查询分销商指定数量的热卖商品列表")
	public List<CommodityStyleOutputDto> queryHotCommodityList(@NotBlank String shopId,int count) {
		//logger.info("queryHotCommodity传入参数shopId："+shopId  +"count:"+count);
		try {
			return commodityStyleService.queryHotCommodityList(shopId, count);
		} catch (Exception e) {
			logger.error("查询分销商指定数量的热卖商品列表异常", e);
		}
	    return null;
	}
	@Override
	@LoggerProfile(methodNote = "根据选择分类获取店铺下的商品 ")
	public PageModel<CommodityStyleOutputDto> getShopCategoryCommodity(@NotBlank String categoryId,@NotBlank String shopId,@NotNull PageModel pageModel) {
		try {
			//logger.info("getShopCategoryCommodity传入参数categoryId："+categoryId  +"shopId:"+shopId);
			return commodityStyleService.getShopCategoryCommodity(categoryId, shopId, pageModel);
			
		} catch (Exception e) {
			logger.error("根据选择分类获取店铺下的商品", e);
		}
		 return null;
	}

	@Override
	@LoggerProfile(methodNote = "根据为分销商sellerId已代理商品列表接口 ")
	public PageModel<CommodityStyleOutputDto> queryProxyCommodity(
			@NotBlank String sellerId,@NotNull PageModel pageModel) {
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		try {
			int totalCount = commodityStyleService.queryProxyCommodityCount(sellerId);
			
			List<CommodityStyleEntity> lstCommodityStyles = commodityStyleService.queryProxyCommodity(sellerId, rowBounds);
			
			//获取佣金
			if(lstCommodityStyles!=null && lstCommodityStyles.size()>0){
				for (int i = 0; i < lstCommodityStyles.size(); i++) {
					CommodityStyleEntity  styleEntity=lstCommodityStyles.get(i);
					 String path= PicPathUtil.getPicServicePath()+styleEntity.getPicBig();
					 styleEntity.setPicBig(path);
//					List<String> catIdList=commodityStyleService.getCatId(styleEntity.getId());
//					List<CommissionPercentOutputDto> commissionPercents = commissionPercentFrontApi.getByBaseCatId(catIdList);
//					if(null == commissionPercents || commissionPercents.size() <= 0){
//						throw new RuntimeException("查询佣金比例出错");
//					}
//					CommissionPercentOutputDto commissionPercent = commissionPercents.get(0);
//					Double commissionLevel1Percent = commissionPercent.getCommissionLevel1Percent();
//					Double commissionLevel2Percent = commissionPercent.getCommissionLevel2Percent();
//					Double wfxPrice = styleEntity.getWfxPrice();
//					if(wfxPrice==null){
//						wfxPrice=new Double(0);
//					}
//					styleEntity.setCommissionLevel1(MathUtil.roundHalfUp(commissionLevel1Percent*wfxPrice));
//					styleEntity.setCommissionLevel2(MathUtil.roundHalfUp(commissionLevel2Percent*wfxPrice));
					styleEntity.setCommissionLevel1(0d);
					styleEntity.setCommissionLevel2(0d);
					styleEntity.setCommissionLevel3(0d);
				}
			}
			return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(lstCommodityStyles,CommodityStyleOutputDto.class));
			
		} catch (Exception e) {
			logger.error("根据为分销商sellerId已代理商品列表接口", e);
		}
		return null;
	}
	
	@Override
	@LoggerProfile(methodNote = "修改已代理商品上架、下架  状态")
	public WFXResult<Map<String, List<String>>> updateSellerCommodityShelvesStatus(
		 @NotNull	List<String> ids, int status) {
		// TODO Auto-generated method stub
		WFXResult<Map<String, List<String>>> rs = new WFXResult<Map<String, List<String>>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		//执行成功的商品id列表
		List<String> list1 = new ArrayList<String>();
		//未执行成功的商品id列表
		List<String> list2 = new ArrayList<String>();
		map.put("success", list1);
		map.put("error", list2);
		List<SellerCommodityEntity> lstSellerCommodity = new ArrayList<SellerCommodityEntity>();
		try{
			if(status==1){
				//可上架的商品集合
				List<SellerCommodityEntity> list = sellerCommodityService.pickUpShelvesCommodities(ids);
				if(list.size()<=0){
					rs.setResultCode(ResultCodeEnum.WARN.getKey());
					rs.setResult(null);
					rs.setResultMsg("所有商品在后台都不处于上架状态，无法执行上架操作！");
					return rs;
				}
				for(String id : ids){
					boolean b = true;
					for(SellerCommodityEntity item : list){
						if(id.equals(item.getId())){
							b = false;
							list1.add(id);
							SellerCommodityEntity sellerCommodityEntity = new SellerCommodityEntity();
							sellerCommodityEntity.setId(id);
							sellerCommodityEntity.setStatus(status);
							sellerCommodityEntity.setUpdateTime(new Date());
							lstSellerCommodity.add(sellerCommodityEntity);
							break;
						}
					}
					if(b){
						list2.add(id);
					}
				}
				int c = sellerCommodityService.batchUpdateSellerCommodity(lstSellerCommodity);
				if(c>0){
					rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
					rs.setResult(map);
					rs.setResultMsg("修改已代理商品上架状态成功！");
				}else{
					rs.setResultCode(ResultCodeEnum.WARN.getKey());
					rs.setResult(null);
					rs.setResultMsg("修改已代理商品上架状态失败！");
				}
				
			}else{
				for(String id : ids){
					list1.add(id);
					SellerCommodityEntity sellerCommodityEntity = new SellerCommodityEntity();
					sellerCommodityEntity.setId(id);
					sellerCommodityEntity.setStatus(status);
					sellerCommodityEntity.setUpdateTime(new Date());
					lstSellerCommodity.add(sellerCommodityEntity);
				}
				int c = sellerCommodityService.batchUpdateSellerCommodity(lstSellerCommodity);
				if(c>0){
					rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
					rs.setResult(map);
					rs.setResultMsg("修改已代理商品下架状态成功！");
				}else{
					rs.setResultCode(ResultCodeEnum.WARN.getKey());
					rs.setResult(null);
					rs.setResultMsg("修改已代理商品下架 状态失败！");
				}
			}
		}catch(Exception e){
			logger.error("修改已代理商品上架、下架  状态程序发生异常：" + e);
			
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(null);
			rs.setResultMsg("修改已代理商品上架、下架  状态失败："+e.getMessage());
			
		}
		return rs;
	}

	@Override
	public WFXResult<Boolean> updateCommodityClassify(@NotNull List<String> commodityId,
			@NotNull List<String> classifyId) {
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		//拼装保存的信息
		List<Map<String,String>>  listClassify=new ArrayList<Map<String,String>>();
		if(commodityId!=null && commodityId.size()>0 && classifyId!=null && classifyId.size()>0){
			for (int i = 0; i < commodityId.size(); i++) {
				for (int j = 0; j < classifyId.size(); j++) {
					Map<String,String> map=new HashMap<String, String>();
					String id=UUIDGenerator.get32LowCaseUUID();
					map.put("commodityId", commodityId.get(i));
					map.put("classifyId", classifyId.get(j));
					map.put("id", id);
					listClassify.add(map);
				}
			}
		}
		try {
			commodityStyleService.deleteDellerCommodityCatRela(commodityId);
			commodityStyleService.updateCommodityClassify(listClassify);
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(true);
			rs.setResultMsg("分类添加成功！");
		} catch (Exception e) {
			rs.setResultCode(ResultCodeEnum.WARN.getKey());
			rs.setResult(false);
			rs.setResultMsg("分类失败！");
		}
		
		
		return null;
	}
	
	@Override
	@LoggerProfile(methodNote = "删除代理分类")
	public WFXResult<Boolean> deleteClassify(@NotBlank String classifyId) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		try{
			sellerCatService.remove(classifyId);
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(true);
			rs.setResultMsg("删除分类成功！");
		}catch(Exception e){
			logger.error("删除代理分类程序发生异常：" + e);
			
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(false);
			rs.setResultMsg("删除分类失败："+e.getMessage());
		}
		return rs;
	}

	@Override
	@LoggerProfile(methodNote = "新增代理分类")
	public WFXResult<Boolean> addClassify(@NotBlank String classifyName,
			@NotBlank String parentNodeId,@NotBlank String sellerId) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		try{
			Date current = new Date();
			SellerCatEntity sellerCatEntity = new SellerCatEntity();
			if(StringUtil.isStrEmpty(parentNodeId) || parentNodeId.equals("0")){
				sellerCatEntity.setLevel(1);
				sellerCatEntity.setParentId("0");
			}else{
				sellerCatEntity.setLevel(2);
				sellerCatEntity.setParentId(parentNodeId);
			}
			sellerCatEntity.setCreateTime(current);
			sellerCatEntity.setUpdateTime(current);
			sellerCatEntity.setName(classifyName);
			sellerCatEntity.setSellerId(sellerId);
			String id = sellerCatService.insert(sellerCatEntity);
			if(!StringUtil.isStrEmpty(id)){
				rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				rs.setResult(true);
				rs.setResultMsg("新增分类成功！");
			}else{
				rs.setResultCode(ResultCodeEnum.WARN.getKey());
				rs.setResult(false);
				rs.setResultMsg("新增分类失败！");
			}
		}catch(Exception e){
			logger.error("新增代理分类程序发生异常：" + e);
			
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(false);
			rs.setResultMsg("新增分类失败："+e.getMessage());
		}
		return rs;
	}

	@Override
	@LoggerProfile(methodNote = "修改代理分类名称")
	public WFXResult<Boolean> updateClassify(@NotBlank String classifyName,
			@NotBlank String classifyId) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> rs = new WFXResult<Boolean>();
		try{
			Date current = new Date();
			SellerCatEntity sellerCatEntity = new SellerCatEntity();
			sellerCatEntity.setId(classifyId);
			sellerCatEntity.setName(classifyName);	
			sellerCatEntity.setUpdateTime(current);
			int i = sellerCatService.update(sellerCatEntity);
			if(i > 0){
				rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				rs.setResult(true);
				rs.setResultMsg("修改分类成功！");
			}else{
				rs.setResultCode(ResultCodeEnum.WARN.getKey());
				rs.setResult(false);
				rs.setResultMsg("修改分类失败！");
			}
			
		}catch(Exception e){
			logger.error("修改代理分类程序发生异常：" + e);
			
			rs.setResultCode(ResultCodeEnum.FAILURE.getKey());
			rs.setResult(false);
			rs.setResultMsg("修改分类失败："+e.getMessage());
		}
		return rs;
	}

	@SuppressWarnings("unchecked")
	@Override
	@LoggerProfile(methodNote = "根据分销商id查询分类信息")
	public List<SellerCommodityCatRelaOutpuDto> queryClassify(@NotBlank String sellerId) {
		// TODO Auto-generated method stub
		List<SellerCommodityCatRelaOutpuDto> outList = null;
		try{
			Date current = new Date();
			SellerCatEntity sellerCatEntity = new SellerCatEntity();
			sellerCatEntity.setSellerId(sellerId);
			List<SellerCatEntity> entityList = sellerCatService.queryList(sellerCatEntity);
			outList = (List<SellerCommodityCatRelaOutpuDto>) BeanUtil.convertBeanList(entityList, SellerCommodityCatRelaOutpuDto.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		return outList;
	}

	@Override
	@LoggerProfile(methodNote = "根据商品id 获取库存的状态 ,上下架状态，如果货品id不为空，已货品的维度去计算库存和上下架状态")
	public CommoditySimplifyStyleOutputDto queryShelvesStatusAndInventoryStatus(
			@NotBlank String commodityId,@NotBlank  String productId) {
		// TODO Auto-generated method stub
		CommoditySimplifyStyleOutputDto outDto = null;
		if(StringUtil.isStrEmpty(productId)){
			//根据商品维度去计算库存和上下架状态
			List<String> commodityIds = new ArrayList<String>();
			commodityIds.add(commodityId);
			List<CommodityStyleEntity> entityList = commodityStyleService.queryCommodityListByIds(commodityIds);
			if(entityList!=null && entityList.size()>0){
				outDto = new CommoditySimplifyStyleOutputDto();
				CommodityStyleEntity commodityStyleEntity = entityList.get(0);
				//库存状态设置
				if(commodityStyleEntity.getStock()>0){
					outDto.setInventoryStatus(1);
				}else{
					outDto.setInventoryStatus(2);
				}
				//上下架状态设置
				if(commodityStyleEntity.getIsOnsale()==1){
					outDto.setShelvesStatus(true);
				}else{
					outDto.setShelvesStatus(false);
				}
			}
		}else{
			//根据货品维度去计算库存和上下架状态
			CommodityProductEntity product = commodityProductService.getById(productId);
			if(product!=null){
				outDto = new CommoditySimplifyStyleOutputDto();
				//库存状态设置
				if((product.getInventoryNum()-product.getPrestoreInventoryNum())>0){
					outDto.setInventoryStatus(1);
				}else{
					outDto.setInventoryStatus(2);
				}
				//上下架状态设置
				if(product.getSellStatus()==1){
					outDto.setShelvesStatus(true);
				}else{
					outDto.setShelvesStatus(false);
				}
			}
		}
		
		return outDto;
	}

	@Override
	@LoggerProfile(methodNote = "根据货品跟下订单的数量校验库存")
	public CheckProductNumberOutputDto checkProductNumber(
			@NotNull List<CheckProductNumberInputDto> inDtoList) {
		// TODO Auto-generated method stub
		CheckProductNumberOutputDto outDto = new CheckProductNumberOutputDto();
		outDto.setRoductViaStatus("false");
		outDto.setDto(new ArrayList<CheckProductNumberDetailsOutputDto>());
		if(inDtoList!=null){
			boolean flag = true;
			for(CheckProductNumberInputDto inDto : inDtoList){
				String productId = inDto.getProductId();
				int orderNum = Integer.parseInt(inDto.getOrderNum());
				CommodityProductEntity product = commodityProductService.getById(productId);
				if(product != null){
					if((product.getInventoryNum()-product.getPrestoreInventoryNum()) <= 0 ){
						//没有库存
						CheckProductNumberDetailsOutputDto cpnd = new CheckProductNumberDetailsOutputDto();
						cpnd.setInventoryNum(String.valueOf(product.getInventoryNum()-product.getPrestoreInventoryNum()));
						cpnd.setProductId(productId);
						cpnd.setInventoryStatus(false);
						outDto.getDto().add(cpnd);
						if(flag){
							flag = false;
						}
					} else if((product.getInventoryNum()-product.getPrestoreInventoryNum()) < orderNum){
						//有库存但库存不足
						CheckProductNumberDetailsOutputDto cpnd = new CheckProductNumberDetailsOutputDto();
						cpnd.setInventoryNum(String.valueOf(product.getInventoryNum()-product.getPrestoreInventoryNum()));
						cpnd.setProductId(productId);
						cpnd.setInventoryStatus(true);
						outDto.getDto().add(cpnd);
						if(flag){
							flag = false;
						}
					}
				}
			}
			if(flag){
				outDto.setRoductViaStatus("true");
			}
		}
		return outDto;
	}

	@Override
	public List<CommodityStyleOutputDto> getCommodityListByIds(
			@NotNull	List<String> commodityIds, boolean includeProduct ,boolean includeStock) {
		// TODO Auto-generated method stub
		List<CommodityStyleOutputDto> dtoList = null;
		List<CommodityStyleEntity> entityList = commodityStyleService.queryCommodityListByIds(commodityIds);
		dtoList= (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(entityList, CommodityStyleOutputDto.class);
		if(dtoList!=null && includeProduct){
			//查询商品的货品
			for(CommodityStyleOutputDto dto : dtoList){
				RowBounds rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
				CommodityProductEntity commodityProductEntity = new CommodityProductEntity();
				commodityProductEntity.setCommodityId(dto.getId());
				List<CommodityProductEntity> products = commodityProductService.findPage(commodityProductEntity, rowBounds);
				List<CommodityProductOutputDto> productDtos = (List<CommodityProductOutputDto>) BeanUtil.convertBeanList(products, CommodityProductOutputDto.class);
				dto.setProductDto(productDtos);
			}
		}
		if(dtoList!=null && !includeStock){
			//屏蔽库存信息
			for(CommodityStyleOutputDto dto : dtoList){
				dto.setStock(null);
			}
		}
		return dtoList;
	}

	@Override
	@LoggerProfile(methodNote = "根据商品id获取该商品同款同分销商的商品列表。 ")
	public List<CommodityStyleOutputDto> getSameSellerCommodity(
			@NotBlank	String commodityId) {
		//logger.info("getSameSellerCommodity根据商品id获取该商品同款同分销商的商品列表。commodityId:"+commodityId);
		try {
			List<CommodityStyleEntity>   styleEntityList=commodityStyleService.getSameSellerCommodity(commodityId);
			List<CommodityStyleOutputDto> styleList = (List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(styleEntityList, CommodityStyleOutputDto.class);
			return styleList;
		} catch (Exception e) {
			logger.error("根据商品id获取该商品同款同分销商的商品列表异常",e);
		}
		return null;
	}

	@Override
	@LoggerProfile(methodNote = "提供给搜索：查询商品列表等信息。 ")
	public PageModel<CommodityStyleOutputDto> queryCommodityListForSearch(@NotBlank String nameSearch,@NotBlank String sllerId,
		 @NotNull 	PageModel pageModel) {
		try {
			return commodityStyleService.queryCommodityListForSearch(nameSearch,sllerId, pageModel);
		} catch (Exception e) {
			logger.error("提供给搜索：查询商品列表等信息。",e);
		}
		return null;
	}

	@Override
	@LoggerProfile(methodNote = "根据分销商品no获取商品详情。 ")
	public CommodityStyleOutputDto queryCommodityDetails(@NotBlank String commodityNo,
			boolean includeProduct, boolean includeStock) {
		//logger.info("queryCommodityDetails根据分销商品id获取商品详情。commodityNo:"+commodityNo+"includeProduct:"+includeProduct+"includeStock"+includeStock);
		try {																		
			//Commodity   commodity=commodityBaseApiService.getCommodityByNo(commodityNo, includeProduct, includeStock, false);
			Commodity   commodity=CommodityProxyApi.getCommodityDetails(commodityNo, includeProduct, includeStock);
					 
			CommodityStyleOutputDto  commodityStyle=(CommodityStyleOutputDto)BeanUtil.convertBean(commodity, CommodityStyleOutputDto.class);
			commodityStyle.setNo(commodity.getCommodityNo());
			commodityStyle.setSpecName(commodity.getColorName());
			commodityStyle.setSpecNo(commodity.getColorNo());
			commodityStyle.setPublicPrice(commodity.getMarkPrice());
			//添加微分销信息
			CommodityStyleOutputDto  wfxStyle=(CommodityStyleOutputDto) BeanUtil.convertBean(commodityStyleService.getByNo(commodityNo),CommodityStyleOutputDto.class);
			commodityStyle.setWfxPrice(wfxStyle.getWfxPrice());
			commodityStyle.setIsOnsale(wfxStyle.getIsOnsale());
			//商品描述
			commodityStyle.setProdDesc(CommodityProxyApi.getCommodityDescByNo(commodityNo));
			//获取货品信息
			Map<String,String> mapPropItem=new HashMap<String, String>();
			mapPropItem.put("id", commodityStyle.getId());
			mapPropItem.put("itemId", Constant.WFX_COMMODITY_PROP_ITEM_ID);
			List<CommodityProductOutputDto> productList = (List<CommodityProductOutputDto>) BeanUtil.convertBeanList(commodityProductService.getProductByCommodityId(mapPropItem), CommodityProductOutputDto.class);
			List<CommodityPicsOutputDto> commodityPics = (List<CommodityPicsOutputDto>) BeanUtil.convertBeanList(commodity.getPictures(), CommodityPicsOutputDto.class);
			commodityStyle.setProductDto(productList);
			commodityStyle.setPicsDto(commodityPics);
			List<String>  commodityNos=new ArrayList<String>();
			commodityNos.add(commodity.getCommodityNo());
			//获取图片
			Map<String, List<CommodityPicsOutputDto>> picsOutputDtoMap=batchGetCommodityPictures(commodityNos, "s");
			if(picsOutputDtoMap!=null){
				List<CommodityPicsOutputDto>   picsOutputDtoList=picsOutputDtoMap.get(commodity.getCommodityNo());
				if(picsOutputDtoList!=null && picsOutputDtoList.size()>0){
					CommodityPicsOutputDto  outputDto=picsOutputDtoList.get(0);
					String picName=outputDto.getPicPath()+outputDto.getPicName();
					commodityStyle.setPicBig(picName);
				}
				
			}
			if(includeStock==true){
				int  stock=commodityStyleService.getStock(commodityStyle.getId());
				commodityStyle.setStock(stock);
			}
			
			return commodityStyle;
			
		} catch (Exception e) {
			logger.error("根据分销商品id获取商品详情异常",e);
		}
		return null;
	}

	@Override
	@LoggerProfile(methodNote = "批量获取指定类型的商品图片。 ")
	public Map<String, List<CommodityPicsOutputDto>> batchGetCommodityPictures(
		@NotNull	List<String> commodityNos,@NotBlank String picType) {
		//logger.info("batchGetCommodityPictures批量获取指定类型的商品图片。commodityNos:"+commodityNos.toString()+"picType:"+picType);
		try {
			Map<String,List<CommodityPicsOutputDto>>   commodityPicsMap=new HashMap<String, List<CommodityPicsOutputDto>>();
			//Map<String, List<Picture>>    pictureMap=commodityBaseApiService.getPicturesByCommodityNos(commodityNos);
			Map<String, List<Picture>>    pictureMap=CommodityProxyApi.getPicturesByCommodityNos(commodityNos);
			
			if(pictureMap!=null){
				for (Map.Entry<String,  List<Picture>> entry : pictureMap.entrySet()) {
					List<Picture>   pictureList=entry.getValue();
					List<Picture>   newPictureList=new ArrayList<Picture>(); 
					if(StringUtils.isNotBlank(picType)){
						 if(pictureList!=null &&pictureList.size()>0){
							 for (int i = 0; i < pictureList.size(); i++) {
								 Picture  picture=pictureList.get(i);
								 if(picType.equals(picture.getPicType())){
									 newPictureList.add(pictureList.get(i));
								 }
							 }
						 }
						 List<CommodityPicsOutputDto> newCommodityPics = (List<CommodityPicsOutputDto>) BeanUtil.convertBeanList(newPictureList, CommodityPicsOutputDto.class); 
						 commodityPicsMap.put(entry.getKey(), newCommodityPics);
					
					}else{
						 List<CommodityPicsOutputDto> commodityPics = (List<CommodityPicsOutputDto>) BeanUtil.convertBeanList(pictureList, CommodityPicsOutputDto.class); 
						 commodityPicsMap.put(entry.getKey(), commodityPics);
					}
					
					
				}
			}
			//给图片路径加域名
			setPicPath(commodityPicsMap);
			return commodityPicsMap;
		} catch (Exception e) {
			logger.error("批量获取指定类型的商品图片。",e);
		}
		
		return null;
	}
    private void  setPicPath(Map<String,List<CommodityPicsOutputDto>>   commodityPicsMap){
    	if(commodityPicsMap!=null){
    		for (Map.Entry<String,  List<CommodityPicsOutputDto>> entry : commodityPicsMap.entrySet()) {
				List<CommodityPicsOutputDto>   pictureList=entry.getValue();
				 if(pictureList!=null &&pictureList.size()>0){
					 for (int i = 0; i < pictureList.size(); i++) {
						 CommodityPicsOutputDto  pics=pictureList.get(i);
						
						 String path= PicPathUtil.getPicServicePath()+pics.getPicPath();
						 pics.setPicPath(path);
					 }
				 }
    		}
    	}
    }
	@Override
	public List<CommodityStyleOutputDto> getCommodityByNos(@NotNull List<String> nos,
			boolean includeProduct, boolean includeStock) {
		
		try {
			 List<CommodityStyleOutputDto> dtoList = commodityStyleService.getCommodityByNos(nos);
				if(dtoList!=null && includeProduct){
					//查询商品的货品
					for(CommodityStyleOutputDto dto : dtoList){
						RowBounds rowBounds = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
						CommodityProductEntity commodityProductEntity = new CommodityProductEntity();
						commodityProductEntity.setCommodityId(dto.getId());
						List<CommodityProductEntity> products = commodityProductService.findPage(commodityProductEntity, rowBounds);
						List<CommodityProductOutputDto> productDtos = (List<CommodityProductOutputDto>) BeanUtil.convertBeanList(products, CommodityProductOutputDto.class);
						dto.setProductDto(productDtos);
					}
				}
				if(dtoList!=null && !includeStock){
					//屏蔽库存信息
					for(CommodityStyleOutputDto dto : dtoList){
						dto.setStock(null);
					}
				}
				return dtoList;
		} catch (Exception e) {
			logger.error("批量方法，根据商品no集合获取商品列表",e);
		}
		return null;
	}

	@Override
	@LoggerProfile(methodNote = "批量方法，根据货品编码集合获取货品列表。 ")
	public List<CommodityProductOutputDto> getProductByProductNos(
			@NotNull List<String> productNos) {
		try {
			List<CommodityProductEntity> productsList =commodityProductService.getProductByProductNos(productNos);
			if(productsList!=null && productsList.size()>0){
				for (int i = 0; i < productsList.size(); i++) {
					CommodityProductEntity    products=productsList.get(i);
					Integer  inventoryNum=products.getInventoryNum();
					Integer  prestoreInventoryNum=products.getPrestoreInventoryNum();
					products.setInventoryNum(inventoryNum-prestoreInventoryNum);
				}
			}
			List<CommodityProductOutputDto> productDtos = (List<CommodityProductOutputDto>) BeanUtil.convertBeanList(productsList, CommodityProductOutputDto.class);
			
			return productDtos;
		} catch (Exception e) {
			logger.error("批量方法，根据货品编码集合获取货品列表",e);
		}
		return null;	
	}
	
	@Override
	public CommodityProductOutputDto getProductById(String productID) {
		CommodityProductEntity entity = commodityProductService.getById(productID);
		CommodityProductOutputDto product = (CommodityProductOutputDto)BeanUtil.convertBean(entity, CommodityProductOutputDto.class);
		
		if(null != product){
			Integer inventoryNum = product.getInventoryNum()==null?0:product.getInventoryNum();
			Integer prestoreInventoryNum = product.getPrestoreInventoryNum()==null?0:product.getPrestoreInventoryNum();
			Integer realNum = inventoryNum - prestoreInventoryNum;
			if(realNum < 0){
				realNum = 0;
			}
			product.setInventoryNum(realNum);
		}
		return product;
	}
	@Override
	public List<CommodityStyleOutputDto> getCommodityByParameter(
			 @NotNull Map<String, Object> paramMap) {
		
		return commodityStyleService.getCommodityByParameter(paramMap);
	}

	@Override
	@LoggerProfile(methodNote = "通过品牌no查找商品信息。 ")
	public PageModel<CommodityStyleOutputDto> getCommodityByBrandNo(
			@NotBlank String brandNo,@NotBlank String sllerId,@NotNull PageModel pageModel) {
		return commodityStyleService.getCommodityByBrandNo(brandNo,sllerId, pageModel);
	}

	@Override
	@LoggerProfile(methodNote = "通过分类id查找商品信息。 ")
	public PageModel<CommodityStyleOutputDto> getCommodityByCatId(@NotBlank String catId,@NotBlank String sllerId,
			@NotNull PageModel pageModel) {
		
		return commodityStyleService.getCommodityByCatId(catId,sllerId, pageModel);
	}
	@Override
	@LoggerProfile(methodNote = "根据分销商品no获取商品详情。 ")
	public CommodityStyleOutputDto queryCommodityDetails(@NotBlank String commodityNo,@NotBlank String sllerId,
			boolean includeProduct, boolean includeStock) {
		try {																		
			Commodity   commodity=CommodityProxyApi.getCommodityDetails(commodityNo, includeProduct, includeStock);
					 
			CommodityStyleOutputDto  commodityStyle=(CommodityStyleOutputDto)BeanUtil.convertBean(commodity, CommodityStyleOutputDto.class);
			commodityStyle.setNo(commodity.getCommodityNo());
			commodityStyle.setSpecName(commodity.getColorName());
			commodityStyle.setSpecNo(commodity.getColorNo());
			commodityStyle.setPublicPrice(commodity.getMarkPrice());
			//添加微分销信息
			CommodityStyleOutputDto  wfxStyle=(CommodityStyleOutputDto) BeanUtil.convertBean(commodityStyleService.getByNo(commodityNo),CommodityStyleOutputDto.class);
			commodityStyle.setWfxPrice(wfxStyle.getWfxPrice());
			commodityStyle.setIsOnsale(wfxStyle.getIsOnsale());
			//判断商品是否已被代理
			SellerCommodityEntity  sellerCommodityEntity=new SellerCommodityEntity();
			sellerCommodityEntity.setCommodityBrandNo(commodityStyle.getBrandNo());
			sellerCommodityEntity.setCommodityCatNo(commodityStyle.getCatNo());
			sellerCommodityEntity.setCommodityYears(commodityStyle.getYears());
			sellerCommodityEntity.setCommodityStyleNo(commodityStyle.getStyleNo());
			sellerCommodityEntity.setSellerId(sllerId);
			String commodityId=sellerCommodityService.querySellerCommodity(sellerCommodityEntity);
			if(commodityId==null  || "".equals(commodityId)){
				commodityStyle.setSellerFlag("2");
			}else{
				commodityStyle.setSellerFlag("1");
			}
			//商品描述
			commodityStyle.setProdDesc(CommodityProxyApi.getCommodityDescByNo(commodityNo));
			//获取货品信息
			Map<String,String> mapPropItem=new HashMap<String, String>();
			mapPropItem.put("id", commodityStyle.getId());
			mapPropItem.put("itemId", Constant.WFX_COMMODITY_PROP_ITEM_ID);
			List<CommodityProductOutputDto> productList = (List<CommodityProductOutputDto>) BeanUtil.convertBeanList(commodityProductService.getProductByCommodityId(mapPropItem), CommodityProductOutputDto.class);
			if(commodity.getPictures()!=null){
				List<CommodityPicsOutputDto> commodityPics = (List<CommodityPicsOutputDto>) BeanUtil.convertBeanList(commodity.getPictures(), CommodityPicsOutputDto.class);
				commodityStyle.setPicsDto(commodityPics);
			}
			commodityStyle.setProductDto(productList);
			List<String>  commodityNos=new ArrayList<String>();
			commodityNos.add(commodity.getCommodityNo());
			//获取图片
			Map<String, List<CommodityPicsOutputDto>> picsOutputDtoMap=batchGetCommodityPictures(commodityNos, "s");
			if(picsOutputDtoMap!=null){
				List<CommodityPicsOutputDto>   picsOutputDtoList=picsOutputDtoMap.get(commodity.getCommodityNo());
				if(picsOutputDtoList!=null && picsOutputDtoList.size()>0){
					CommodityPicsOutputDto  outputDto=picsOutputDtoList.get(0);
					String picName=outputDto.getPicPath()+outputDto.getPicName();
					commodityStyle.setPicBig(picName);
				}
				
			}
			if(includeStock==true){
				int  stock=commodityStyleService.getStock(commodityStyle.getId());
				commodityStyle.setStock(stock);
			}
			
			return commodityStyle;
			
		} catch (Exception e) {
			logger.error("根据分销商品id获取商品详情异常",e);
		}
		return null;
	}
	@Override
	public PageModel<CommodityStyleOutputDto> queryCommodityOrder(
			CommodityStyleOrderInputDto inputDto, PageModel pageModel) {
		
		try {
			return commodityStyleService.queryCommodityOrder(inputDto,pageModel);
		} catch (Exception e) {
			logger.error("查询分销商指定数量的热卖商品列表异常", e);
		}
	    return null;
	}
	@Override
	public List<CommodityStyleFilterOutputDto> getBrandInfo(
			CommodityStyleOrderInputDto inputDto) {
		return commodityStyleService.getBrandInfo(inputDto);
	}
	
	@Override
	public List<CommodityPropertyOutputDto> getPropByCommodityId(String commodityNo) {
		return commodityStyleService.getPropByCommodityId(commodityNo);
	}
	
	@Override
	public List<CommodityStyleFilterOutputDto> getCatInfo(
			CommodityStyleOrderInputDto inputDto) {
		
		return commodityStyleService.getCatInfo(inputDto);
	}
	@Override
	public List<String> getPriceInfo(CommodityStyleOrderInputDto inputDto) {
	
		return commodityStyleService.getPriceInfo(inputDto);
	}
	@Override
	public List<CommodityStyleFilterOutputDto> getSizeInfo(
			CommodityStyleOrderInputDto inputDto) {
		// TODO Auto-generated method stub
		return commodityStyleService.getSizeInfo(inputDto);
	}
	@Override
	public List<CommodityStyleFilterOutputDto> getSpecInfo(
			CommodityStyleOrderInputDto inputDto) {
		// TODO Auto-generated method stub
		return commodityStyleService.getSpecInfo(inputDto);
	}
	@Override
	public List<CommodityStyleFilterOutputDto> getPropInfo(
			CommodityStyleOrderInputDto inputDto) {
		// TODO Auto-generated method stub
		return commodityStyleService.getPropInfo(inputDto);
	}
	@Override
	public CommodityStyleOutputDto getById(String id) {
		CommodityStyleEntity commodityStyleEntity = commodityStyleService.getById(id);
		return (CommodityStyleOutputDto) BeanUtil.convertBean(commodityStyleEntity,CommodityStyleOutputDto.class);
	}
	@Override
	public String getPictureUrl(String no) {
		return commodityStyleService.getPictureUrl(no);
	}
	
}
