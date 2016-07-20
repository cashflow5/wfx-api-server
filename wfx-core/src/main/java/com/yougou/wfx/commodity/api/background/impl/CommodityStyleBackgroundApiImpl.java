 /*
 * 版本信息
 
 * 日期 2016-03-24 16:58:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.outside.api.IThirdPartyCommodityService;
import com.yougou.tools.common.utils.StringUtil;
import com.yougou.wfx.commodity.api.background.ICommodityStyleBackgroundApi;
import com.yougou.wfx.commodity.dto.input.CommodityInventoryInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStyleInputDto;
import com.yougou.wfx.commodity.dto.input.CommodityStylePageInputDto;
import com.yougou.wfx.commodity.dto.output.CommodityInventoryOutputDto;
import com.yougou.wfx.commodity.dto.output.CommodityStyleOutputDto;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;
import com.yougou.wfx.commodity.model.CommodityInventoryEntity;
import com.yougou.wfx.commodity.model.CommodityStyleEntity;
import com.yougou.wfx.commodity.model.SellerCommodityEntity;
import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.commodity.service.ICommodityStyleService;
import com.yougou.wfx.commodity.service.ISellerCommodityService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.front.ICommissionPercentFrontApi;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.proxy.commodity.CommodityProxyApi;
import com.yougou.wfx.utils.BizNoGen;
import com.yougou.wfx.utils.MathUtil;

/**
 * ICommodityStyleBackgroundApi实现
 * @author zhang.wj
 * @Date 创建时间：2016-03-24 16:58:55
 */
@Service
public class CommodityStyleBackgroundApiImpl implements ICommodityStyleBackgroundApi{
	
	@Resource
	ICommodityStyleService commodityStyleService;
	@Resource 
	ICommodityCatb2cService commodityCatb2cService;
	
	@Resource 
	ISellerCommodityService  sellerCommodityService;
	@Resource
	private ICommissionPercentFrontApi commissionPercentFrontApi;
	@Override
	public int removeById(String id) {
		return commodityStyleService.remove(id);
	}
	
	@Override
	public String insert(CommodityStyleInputDto commodityStyleDto) {
		return commodityStyleService.insert(this.dtoToEntity(commodityStyleDto));
	}
	
	@Override
	@LoggerProfile(methodNote = "查询商品列表")
	public PageModel<CommodityStyleOutputDto> findPage(CommodityStylePageInputDto pageInputDto,PageModel<CommodityStyleOutputDto> pageModel,int warehouseFlag) {
		
		CommodityStyleEntity commodityStyleEntity = (CommodityStyleEntity) BeanUtil.convertBean(pageInputDto,CommodityStyleEntity.class);
		//获取分类信息
		List<String>   commodityCatList=this.getCommodityCatb2cList(pageInputDto, warehouseFlag);
		commodityStyleEntity.setCatList(commodityCatList);
		
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		
		commodityStyleEntity.setWarehouseFlag(warehouseFlag);
		
		int totalCount = commodityStyleService.findPageCount(commodityStyleEntity);
		
		List<CommodityStyleEntity> lstCommodityStyles = commodityStyleService.findPage(commodityStyleEntity, rowBounds);
		//获取佣金
		if(warehouseFlag==1  &&  lstCommodityStyles!=null && lstCommodityStyles.size()>0){
			for (int i = 0; i < lstCommodityStyles.size(); i++) {
				CommodityStyleEntity  style=lstCommodityStyles.get(i);
//				List<String> catIdList=commodityStyleService.getCatId(style.getId());
//				List<CommissionPercentOutputDto> commissionPercents = commissionPercentFrontApi.getByBaseCatId(catIdList);
				
				List<String> baseCatIds = commodityStyleService.getCatId(style.getId());
				CommissionPercentOutputDto commissionPercent = commissionPercentFrontApi.getCommissionByCondition(style.getBrandNo(), baseCatIds != null ? baseCatIds.get(0) : "", style.getId());
				if(null == commissionPercent){
					style.setTotalCommission(0d);
//					throw new RuntimeException("查询佣金比例出错");
				}else{
//				CommissionPercentOutputDto commissionPercent = commissionPercents.get(0);
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
					Double totalCommission =	MathUtil.add( commissionLevel1Percent*wfxPrice 
									,commissionLevel2Percent*wfxPrice
									,commissionLevel3Percent*wfxPrice
									);
					
					style.setTotalCommission(totalCommission);
				}
			}
		}
		return new PageModel<CommodityStyleOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(lstCommodityStyles,CommodityStyleOutputDto.class));
	}

	@Override
	public int update(CommodityStyleInputDto commodityStyleDto) {
		return commodityStyleService.update(this.dtoToEntity(commodityStyleDto));
	}

	@Override
	public CommodityStyleOutputDto getById(String id) {
		CommodityStyleEntity commodityStyleEntity = commodityStyleService.getById(id);
		return this.entityToDto(commodityStyleEntity);
	}
	
	@Override
	public boolean hasCommodityOnSaleByCat(String catNo) {
		if(StringUtils.isBlank(catNo)){
			return false;
		}
		boolean flag = false;
		CommodityStyleEntity entity = new CommodityStyleEntity();
		entity.setCatNo(catNo);
		entity.setCommodityStatus(2);
		entity.setDeleteFlag(1);
		int num = commodityStyleService.queryListCount(entity);
		if(num > 0){
			flag = true;
		}
		return flag;
	}
	
	@Override
	@LoggerProfile(methodNote = "查询商品列表数量")
	public int findPageCount(CommodityStyleInputDto inputDto){
		CommodityStyleEntity commodityStyleEntity = (CommodityStyleEntity)BeanUtil.convertBean(inputDto, CommodityStyleEntity.class);
		return commodityStyleService.findPageCount(commodityStyleEntity);
	}
	
	private CommodityStyleEntity dtoToEntity(Object obj){
		return (CommodityStyleEntity) BeanUtil.convertBean(obj,CommodityStyleEntity.class);
	}
	
	private CommodityStyleOutputDto entityToDto(Object obj){
		return (CommodityStyleOutputDto) BeanUtil.convertBean(obj,CommodityStyleOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote = "批量添加到微分销")
	public void batchCommodityAddToWfx(List<CommodityStyleInputDto> commodtyList) throws Exception {
		
		List<CommodityStyleEntity>  entityList=new ArrayList<CommodityStyleEntity>();
		
		if(commodtyList!=null && commodtyList.size()>0){
			for (int i = 0; i < commodtyList.size(); i++) {
				CommodityStyleEntity  entity=this.dtoToEntity(commodtyList.get(i));
				entity.setWfxCommodityNo(BizNoGen.getCommodityNo());
				entityList.add(entity);
				
			}
			commodityStyleService.batchCommodityAddToWfx(entityList);
		}
		
	}
 
	@Override
	@LoggerProfile(methodNote = "批量修改上下架")
	public void batchUpdateShelvesStatus(List<CommodityStyleInputDto> commodtyList) {
		
		List<CommodityStyleEntity>  entityList=new ArrayList<CommodityStyleEntity>();
		
		List<SellerCommodityEntity>  sellerList=new ArrayList<SellerCommodityEntity>();
		if(commodtyList!=null && commodtyList.size()>0){
			for (int i = 0; i < commodtyList.size(); i++) {
				CommodityStyleEntity  entity=this.dtoToEntity(commodtyList.get(i));
				entityList.add(entity);
				int isOnsale = entity.getIsOnsale();
				//更新代理商品下架
				if(isOnsale==2){
					SellerCommodityEntity  sellerCommodityEntity=new SellerCommodityEntity();
					sellerCommodityEntity.setStatus(entity.getIsOnsale());
					sellerCommodityEntity.setUpdateTime(new Date());
					sellerCommodityEntity.setId(entity.getId());
					sellerList.add(sellerCommodityEntity);
				}
			}
			commodityStyleService.batchUpdateShelvesStatus(entityList);
			updateSellerShelvesStatus(sellerList);
			
		}
		
	}
	@LoggerProfile(methodNote = "单个修改上下架")
	private void updateSellerShelvesStatus(List<SellerCommodityEntity>  entityList){
		List<SellerCommodityEntity> sellerArryList=new ArrayList<SellerCommodityEntity>();
		if(entityList==null || entityList.size()==0){
			return ;
		}
	
		for (int j = 0; j < entityList.size(); j++) {
			String id=entityList.get(j).getId();
			List<CommodityStyleOutputDto> outputDtoList=getSameSellerCommodity(id);
			int flog=0;
			if(outputDtoList!=null){
				for (int i = 0; i < outputDtoList.size(); i++) {
					CommodityStyleOutputDto  outputDto=outputDtoList.get(i);
					if(outputDto!=null){
						Integer  isOnsale=outputDto.getIsOnsale();
						if(isOnsale==2){
							flog=flog+1;
						}
					}
				}
				//修改代理商品上下架状态
				if(outputDtoList.size()==flog){
					SellerCommodityEntity  sellerCommodity=new SellerCommodityEntity();
					CommodityStyleOutputDto  commodityStyleOutputDto=outputDtoList.get(0);
					String styleNo=commodityStyleOutputDto.getStyleNo();
					sellerCommodity.setCommodityStyleNo(styleNo);
					sellerCommodity.setStatus(2);
					
					sellerArryList.add(sellerCommodity);
				}
			}
		}
		sellerCommodityService.updateSellerShelvesStatus(sellerArryList);
	}
	@LoggerProfile(methodNote = "查询导出商品信息")
	public List<CommodityStyleOutputDto>   queryExportCommodityInfo(List<String> list){
		List<CommodityStyleEntity>  lstCommodityStyles=commodityStyleService.queryExportCommodityInfo(list);
		
		List<CommodityStyleOutputDto>   lstStyleOutputDto=(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(lstCommodityStyles,CommodityStyleOutputDto.class);
		
		return lstStyleOutputDto;
	}

	@Override
	@LoggerProfile(methodNote = "单个调价")
	public void readjustPrices(List<CommodityStyleInputDto> commodtyList) {
		List<CommodityStyleEntity>  entityList=new ArrayList<CommodityStyleEntity>();
		if(commodtyList!=null && commodtyList.size()>0){
			for (int i = 0; i < commodtyList.size(); i++) {
				CommodityStyleEntity  entity=this.dtoToEntity(commodtyList.get(i));
				entityList.add(entity);
			}
			commodityStyleService.readjustPrices(entityList);
		}
		
	}
	
	@Override
	@LoggerProfile(methodNote = "根据品牌编号获取商品数量")
	public int getCommodityCountByBrandNo(String brandNo){
		CommodityStyleEntity commodityStyleEntity = new CommodityStyleEntity();
		commodityStyleEntity.setBrandNo(brandNo);
		int totalCount = commodityStyleService.queryListCount(commodityStyleEntity);
		return totalCount;
	}

	@Override
	@LoggerProfile(methodNote = "批量单个调价")
	public void batchReadjustPrices(List<CommodityStyleInputDto> commodtyList) {
		List<CommodityStyleEntity>  entityList=new ArrayList<CommodityStyleEntity>();
		if(commodtyList!=null && commodtyList.size()>0){
			for (int i = 0; i < commodtyList.size(); i++) {
				CommodityStyleEntity  entity=this.dtoToEntity(commodtyList.get(i));
				entityList.add(entity);
			}
			commodityStyleService.batchReadjustPrices(entityList);
		}
		
	}
	
	@Override
	@LoggerProfile(methodNote = "查询商品库存列表")
	public PageModel<CommodityInventoryOutputDto> queryCommodityInventoryPage(
			CommodityInventoryInputDto inputDto, PageModel pageModel) {
		CommodityInventoryEntity commodityInventoryEntity = (CommodityInventoryEntity) BeanUtil.convertBean(inputDto,CommodityInventoryEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		
		int totalCount = commodityStyleService.findInventoryPageCount(commodityInventoryEntity);
		
		List<CommodityInventoryEntity> lstCommodityInventorys = commodityStyleService.findInventoryPage(commodityInventoryEntity, rowBounds);

		return new PageModel<CommodityInventoryOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommodityInventoryOutputDto>) BeanUtil.convertBeanList(lstCommodityInventorys,CommodityInventoryOutputDto.class));
	}
	/**
	 * 获取分类信息编码
	 * @param pageInputDto
	 * @return
	 */
	private List<String>   getCommodityCatb2cList(CommodityStylePageInputDto pageInputDto,int warehouseFlag){
		//获取分类信息编码
		CommodityCatb2cEntity  commodityCatb2cEntity=null;
		//判断是微分销页面/优购页面  ,2为优购页面
		if(warehouseFlag==2){
			if(pageInputDto.getLevelthree()  !=null && !pageInputDto.getLevelthree().equals("")){
				commodityCatb2cEntity=new CommodityCatb2cEntity();
				commodityCatb2cEntity.setId(pageInputDto.getLevelthree());
				commodityCatb2cEntity.setLevel(3);
			}else if(pageInputDto.getLeveltwo()!=null && !pageInputDto.getLeveltwo().equals("")){
				commodityCatb2cEntity=new CommodityCatb2cEntity();
				commodityCatb2cEntity.setId(pageInputDto.getLeveltwo());
				commodityCatb2cEntity.setLevel(2);
			}else if(pageInputDto.getLevelone()!=null && !pageInputDto.getLevelone().equals("")){
				commodityCatb2cEntity=new CommodityCatb2cEntity();
				commodityCatb2cEntity.setId(pageInputDto.getLevelone());
				commodityCatb2cEntity.setLevel(1);
			}
		}else{
			if(pageInputDto.getWfxLevelThree() !=null && !pageInputDto.getWfxLevelThree().equals("")){
				commodityCatb2cEntity=new CommodityCatb2cEntity();
				commodityCatb2cEntity.setId(pageInputDto.getWfxLevelThree());
				commodityCatb2cEntity.setLevel(3);
			}else if(pageInputDto.getWfxLevelTwo()   !=null && !pageInputDto.getWfxLevelTwo().equals("")){
				commodityCatb2cEntity=new CommodityCatb2cEntity();
				commodityCatb2cEntity.setId(pageInputDto.getWfxLevelTwo());
				commodityCatb2cEntity.setLevel(2);
			}else if(pageInputDto.getWfxLevelOne() !=null && !pageInputDto.getWfxLevelOne().equals("")){
				commodityCatb2cEntity=new CommodityCatb2cEntity();
				commodityCatb2cEntity.setId(pageInputDto.getWfxLevelOne());
				commodityCatb2cEntity.setLevel(1);
			}
		}
		
		//存放商品编码
		List<String>  arrayList=new ArrayList<String>();
		if(commodityCatb2cEntity!=null){
			List<CommodityCatb2cEntity>  catb2cEntityList=null;
			if(commodityCatb2cEntity.getLevel()==3){
				CommodityCatb2cEntity catb2cEntity=commodityCatb2cService.getById(commodityCatb2cEntity.getId());
				catb2cEntityList=new ArrayList<CommodityCatb2cEntity>();
				catb2cEntityList.add(catb2cEntity);
			}else{
				catb2cEntityList=commodityCatb2cService.getThreeLevelCats(commodityCatb2cEntity);
			}
			if(catb2cEntityList!=null &&  catb2cEntityList.size()>0){
				for (int i = 0; i < catb2cEntityList.size(); i++) {
					CommodityCatb2cEntity  catb2cEntity=catb2cEntityList.get(i);
					if(StringUtils.isNotBlank(catb2cEntity.getNo())){
						arrayList.add(catb2cEntity.getNo());
					}
				}
			}
		}
				
		return arrayList;
	}


	@Override
	@LoggerProfile(methodNote = "根据条件查询商品")
	public List<CommodityStyleOutputDto> queryList(CommodityStyleInputDto inputDto) {
		CommodityStyleEntity commodityStyleEntity = (CommodityStyleEntity)BeanUtil.convertBean(inputDto, CommodityStyleEntity.class);
		List<CommodityStyleEntity> listEntity = commodityStyleService.queryList(commodityStyleEntity);
		List<CommodityStyleOutputDto> listOutputDto=(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(listEntity,CommodityStyleOutputDto.class);
		return listOutputDto;
	}

	@Override
	@LoggerProfile(methodNote = "统计销量，人气，代理数。")
	public int countCommodityStyleParams() {
		return commodityStyleService.countCommodityStyleParams();
	}

	@Override
	@LoggerProfile(methodNote = "根据商品id获取该商品同款同分销商的商品列表。")
	public List<CommodityStyleOutputDto> getSameSellerCommodity(
			String commodityId) {
		
		List<CommodityStyleEntity> listEntity=null;
		try {
			listEntity = commodityStyleService.getSameSellerCommodity(commodityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<CommodityStyleOutputDto> listOutputDto=(List<CommodityStyleOutputDto>) BeanUtil.convertBeanList(listEntity,CommodityStyleOutputDto.class);
		
		return listOutputDto;
	}

	@Override
	public int clearCommodityStylePicNotSPYG() {
		return commodityStyleService.clearCommodityStylePicNotSPYG();
	}

	@Override
	public List<CommodityStyleOutputDto> getCommodityByNos(List<String> nos) throws Exception {
		
		return commodityStyleService.getCommodityByNos(nos);
	}

	@Override
	public Double computeDistributableMoney(Double wfxPrice, Double costPrice) {
		return commodityStyleService.computeDistributableMoney(wfxPrice, costPrice);
	}

//	@Override
//	public void batchAddProxyToOrigianlSeller(
//			List<CommodityStyleInputDto> arrayList) {
//		 commodityStyleService.batchAddProxyToOrigianlSeller(arrayList);
//	}

	@Override
	public PageModel<CommodityStyleOutputDto> queryDiscoverStyleInfo(
			CommodityStylePageInputDto pageInputDto, PageModel pageModel) {
		CommodityStyleEntity commodityStyleEntity = (CommodityStyleEntity) BeanUtil.convertBean(pageInputDto,CommodityStyleEntity.class);
		return commodityStyleService.queryDiscoverStyleInfo(commodityStyleEntity, pageModel);
	}

	@Override
	public void synchronizationCommodity(String channelNo, String SellerNo,
			List<String> commodityNoList)throws Exception  {
		CommodityProxyApi.synchronizationCommodity(channelNo, SellerNo, commodityNoList);
	}
	
	@Override
	@LoggerProfile(methodNote = "根据商品id,款色编码查询商品列表。")
	public List<CommodityStyleOutputDto> queryCommodityList(String supplierCodes, String commodityIds){
		List<String> lstSupplierCode = null;
		if(StringUtils.isNotBlank(supplierCodes)){
			lstSupplierCode =  Arrays.asList(supplierCodes.split(","));
		}
		List<String> lstCommodityId = null;
		if(StringUtils.isNotBlank(commodityIds)){
			lstCommodityId =  Arrays.asList(commodityIds.split(","));
		}
		return commodityStyleService.queryCommodityList(lstSupplierCode, lstCommodityId);
	}
}
