 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.service.ICommodityCatb2cService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.background.ICommissionPercentBackgroundApi;
import com.yougou.wfx.finance.dto.input.CommissionPercentInputDto;
import com.yougou.wfx.finance.dto.input.CommissionPercentLogPageInputDto;
import com.yougou.wfx.finance.dto.input.CommissionPercentPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionPercentLogOutputDto;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;
import com.yougou.wfx.finance.model.CommissionPercentEntity;
import com.yougou.wfx.finance.model.CommissionPercentLogEntity;
import com.yougou.wfx.finance.service.ICommissionPercentService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommissionPercentBackgroundApi实现
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
@Service
public class CommissionPercentBackgroundApiImpl implements ICommissionPercentBackgroundApi{
	
	@Resource
	private ICommissionPercentService commissionPercentService;
	@Resource
    private ICommodityCatb2cService commodityCatb2cService;
	
	@Override
	public int removeById(String id,String updateUser){
		return commissionPercentService.remove(id,updateUser);
	}

	@Override
	public String insert(CommissionPercentInputDto commissionPercentDto) {
		return commissionPercentService.insert(this.dtoToEntity(commissionPercentDto));
	}
	
    
	@Override
	public PageModel<CommissionPercentOutputDto> findPage(CommissionPercentPageInputDto pageInputDto,PageModel<CommissionPercentOutputDto> pageModel) {
		CommissionPercentEntity commissionPercentEntity = (CommissionPercentEntity) BeanUtil.convertBean(pageInputDto,CommissionPercentEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commissionPercentService.findPageCount(commissionPercentEntity);
		List<CommissionPercentEntity> lstCommissionPercents = commissionPercentService.findPage(commissionPercentEntity, rowBounds);

		return new PageModel<CommissionPercentOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommissionPercentOutputDto>) BeanUtil.convertBeanList(lstCommissionPercents,CommissionPercentOutputDto.class));
	}

	@Override
	public int update(CommissionPercentInputDto commissionPercentDto) {
		return commissionPercentService.update(this.dtoToEntity(commissionPercentDto));
	}

	@Override
	public CommissionPercentOutputDto getById(String id) {
		CommissionPercentEntity commissionPercentEntity = commissionPercentService.getById(id);
		return this.entityToDto(commissionPercentEntity);
	}
	
	private CommissionPercentEntity dtoToEntity(Object obj){
		return (CommissionPercentEntity) BeanUtil.convertBean(obj,CommissionPercentEntity.class);
	}
	
	private CommissionPercentOutputDto entityToDto(Object obj){
		return (CommissionPercentOutputDto) BeanUtil.convertBean(obj,CommissionPercentOutputDto.class);
	}

    @Override
    public PageModel<CommissionPercentLogOutputDto> findPageLog(CommissionPercentLogPageInputDto pageInputDto, PageModel<CommissionPercentLogOutputDto> pageModel) {
        CommissionPercentLogEntity logEntity = (CommissionPercentLogEntity) BeanUtil.convertBean(pageInputDto,CommissionPercentLogEntity.class);
        RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

        int totalCount = commissionPercentService.findPageCountLog(logEntity);
        List<CommissionPercentLogEntity> lstCommissionPercents = commissionPercentService.findPageLog(logEntity, rowBounds);

        return new PageModel<CommissionPercentLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommissionPercentLogOutputDto>) BeanUtil.convertBeanList(lstCommissionPercents,CommissionPercentLogOutputDto.class));
    }
    
    @Override
    public String saveCommission(CommissionPercentInputDto commissionPercent) {
        String messageString="";
        CommissionPercentEntity entity=this.dtoToEntity(commissionPercent);
        String supplierCode=entity.getSupplierCode();
        String commodityId=entity.getCommodityId();
        if(StringUtils.isNotBlank(supplierCode)&&StringUtils.isNotBlank(commodityId)){//单品佣金设置保存
            StringBuilder sBuilder=new StringBuilder();//返回已经生成佣金的信息
            List<CommissionPercentEntity> saveDtoList=new ArrayList<CommissionPercentEntity>();
            List<CommissionPercentLogEntity> logEntities=new ArrayList<CommissionPercentLogEntity>();
            
            StringBuilder commodityIdBuilder=new StringBuilder();
            String[] supplierCodesStrings=supplierCode.split(",");
            String[] commodityIdsStrings=commodityId.split(",");
            String[] commodityNamesStrings=entity.getCommodityName().split(",");
            for (int i = 0; i < commodityIdsStrings.length; i++) {//组装查询格式'1212','1212',
                commodityIdBuilder.append("'").append(commodityIdsStrings[i]).append("',");
            }
            entity.setSupplierCode(null);//因为商品名称id和款色编码是一对一的关系，所以有一个就可以
            entity.setCommodityId(commodityIdBuilder.substring(0, commodityIdBuilder.length()-1));
            entity.setCommodityName(null);
            List<CommissionPercentEntity> lstCommissionPercents = commissionPercentService.findPage(entity, new RowBounds(0,Integer.MAX_VALUE));
            
            for (int i = 0; i < commodityIdsStrings.length; i++) {//去掉已经设置好款色编码佣金
                String tempSupplierCode=supplierCodesStrings[i];
                String tempCommodityId=commodityIdsStrings[i];
                String tempCommodityName=commodityNamesStrings[i];
                boolean flag=true;
                if(null!=lstCommissionPercents){
                    Iterator<CommissionPercentEntity> its= lstCommissionPercents.iterator();
                    while (its.hasNext()) {
                        CommissionPercentEntity existEntity = its.next();
                        if(tempSupplierCode.equals(existEntity.getSupplierCode())&&tempCommodityId.equals(existEntity.getCommodityId())){
                           sBuilder.append("商品:"+tempCommodityName+"(").append(tempSupplierCode).append("),\n");//数据库已经有了的款色编码对应佣金设置数据
                           its.remove();
                           flag=false;
                           break;
                        }
                   }
                }
                if(flag){
                    CommissionPercentEntity saveDto=new CommissionPercentEntity();
                    saveDto.setSupplierCode(tempSupplierCode);
                    saveDto.setCommodityId(tempCommodityId);
                    saveDto.setType(3);
                    saveDto.setCommissionLevel1Percent(entity.getCommissionLevel1Percent());
                    saveDto.setCommissionLevel2Percent(entity.getCommissionLevel2Percent());
                    saveDto.setCommissionLevel3Percent(entity.getCommissionLevel3Percent());
                    saveDto.setCreateUser(entity.getCreateUser());
                    String strId = UUIDGenerator.get32LowCaseUUID();
                    saveDto.setId(strId);
                    saveDtoList.add(saveDto);
                    
                    CommissionPercentLogEntity logEntity=new CommissionPercentLogEntity();
                    StringBuilder remark=new StringBuilder("");
                    remark.append("款色编码:"+tempSupplierCode).append(",商品名称:"+tempCommodityName).append(",")
                    .append("一级佣金比例:设置为【").append(saveDto.getCommissionLevel1Percent())
                    .append("%】,二级佣金比例:设置为【").append(saveDto.getCommissionLevel2Percent())
                    .append("%】,三级佣金比例:设置为【").append(saveDto.getCommissionLevel3Percent()).append("%】");
                    logEntity.setId(strId);
                    logEntity.setCreateUser(saveDto.getCreateUser());
                    logEntity.setOperateType("1");
                    logEntity.setCommissionType("3");
                    logEntity.setRemark(remark.toString());
                    logEntity.setSupplierCode(tempSupplierCode);
                    logEntity.setCommodityId(tempCommodityId);
                    logEntities.add(logEntity);
                }
            }
            if(StringUtils.isNotBlank(sBuilder.toString())){
                messageString=sBuilder.toString()+"\n已有佣金比例，请不要重复设置.";
            }
            if(saveDtoList.size()>0){
                commissionPercentService.batchInsert(saveDtoList,logEntities);
            }
        }else{//品牌分类佣金设置保存
            if(StringUtils.isNotBlank(entity.getBrandNo())){
                CommissionPercentEntity tempEntity=new CommissionPercentEntity();
                tempEntity.setBrandNo(entity.getBrandNo());
                String baseCatId=entity.getBaseCatId();
                if(StringUtils.isNotBlank(baseCatId)){
                    tempEntity.setBaseCatId(baseCatId);//有传分类id，但是上面查询在相应品牌和分类id下面没有设置佣金，所以查询单品牌的时候，分类id查询应该为null
                }else{
                    tempEntity.setBaseCatId("has");//没有分类id的时候，单品牌查询
                }
                int count=commissionPercentService.findPageCount(tempEntity);
                if(count>0){
                    messageString="已有佣金比例，请不要重复设置！";
                }else{
                    entity.setType(2);
                    commissionPercentService.insert(entity);
                }
            }
        }
        return messageString;
    }
    
    @Override
    @LoggerProfile(methodNote="根据相关条件获取佣金比例")
    public CommissionPercentOutputDto getCommissionByCondition(@NotNull String brandNo,String baseCatId,@NotNull String commodityid) {
        CommissionPercentEntity entity=commissionPercentService.getCommissionByCondition(brandNo,baseCatId,commodityid);
        if(null!=entity){
            return (CommissionPercentOutputDto) BeanUtil.convertBean(entity,CommissionPercentOutputDto.class);
        }else{
            return null;
        }
    }
}
