 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.yougou.tools.common.utils.CalculateUtils;
import com.yougou.wfx.commodity.dao.CommodityCatb2cMapper;
import com.yougou.wfx.commodity.model.CommodityCatb2cEntity;
import com.yougou.wfx.finance.dao.CommissionPercentMapper;
import com.yougou.wfx.finance.model.CommissionPercentEntity;
import com.yougou.wfx.finance.model.CommissionPercentLogEntity;
import com.yougou.wfx.finance.service.ICommissionPercentService;
import com.yougou.wfx.finance.util.FinanaceConstant;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * ICommissionPercentService实现
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
@Service
public class CommissionPercentServiceImpl extends BaseService implements ICommissionPercentService {
	
	@Resource
	private CommissionPercentMapper commissionPercentMapper;
	@Resource
    private CommodityCatb2cMapper commodityCatb2cMapper;
	
	@Override
	public int findPageCount(CommissionPercentEntity commissionPercentEntity){
		return commissionPercentMapper.findPageCount(commissionPercentEntity);
	}

	@Override
	public List<CommissionPercentEntity> findPage(CommissionPercentEntity commissionPercentEntity,RowBounds rowBounds){
		return commissionPercentMapper.findPage(commissionPercentEntity,rowBounds);
	}
	 
	@Override
    @Transactional(rollbackFor=Exception.class)
    public void batchInsert(List<CommissionPercentEntity> entityList,List<CommissionPercentLogEntity> logEntities){
	    commissionPercentMapper.batchInsert(entityList);
	    commissionPercentMapper.batchInsertLog(logEntities);
	}
	@Override
	@Transactional(rollbackFor=Exception.class)
	public String insert(CommissionPercentEntity commissionPercentEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		commissionPercentEntity.setId(strId);
		commissionPercentMapper.insert(commissionPercentEntity);
		
		CommissionPercentLogEntity logEntity=new CommissionPercentLogEntity();
		StringBuilder remark=new StringBuilder("");
		remark.append("品牌名称:").append(commissionPercentEntity.getBrandName()+",");
		String baseCatIdString=commissionPercentEntity.getBaseCatId();
        String tempLevel=commissionPercentEntity.getBaseCatLevel();
        if(StringUtils.isNotBlank(baseCatIdString)){
            if("1".equals(tempLevel)){
                tempLevel="一";
            }else if("2".equals(tempLevel)){
                tempLevel="二";
            }
            remark.append(tempLevel).append("级分类:").append(commissionPercentEntity.getCatName()+",");
            logEntity.setBaseCatId(commissionPercentEntity.getBaseCatId());
        }
        remark.append("一级佣金比例:设置为【").append(commissionPercentEntity.getCommissionLevel1Percent())
       .append("%】,二级佣金比例:设置为【").append(commissionPercentEntity.getCommissionLevel2Percent())
       .append("%】,三级佣金比例:设置为【").append(commissionPercentEntity.getCommissionLevel3Percent()).append("%】");
        
        logEntity.setId(strId);
        logEntity.setCreateUser(commissionPercentEntity.getCreateUser());
        logEntity.setOperateType("1");
        logEntity.setCommissionType("2"); 
        logEntity.setRemark(remark.toString());
        commissionPercentMapper.insertLog(logEntity);
		return strId;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int update(CommissionPercentEntity commissionPercentEntity){
	    CommissionPercentEntity tempEntity=new CommissionPercentEntity();
	    tempEntity.setId(commissionPercentEntity.getId());
	    List<CommissionPercentEntity> originEntityList=commissionPercentMapper.findPage(tempEntity,new RowBounds(0,1));
	    
	    if(originEntityList!=null&&originEntityList.size()>0){
	        int temp=commissionPercentMapper.update(commissionPercentEntity);
	        if(temp>0){
    	        CommissionPercentEntity originEntity=originEntityList.get(0);
    	        CommissionPercentLogEntity logEntity=new CommissionPercentLogEntity();
    	        StringBuilder remark=new StringBuilder("");
    	        int commissionType=originEntity.getType();
    	        if (1==commissionType) {
    	            logEntity.setCommissionType("1"); 
                }else if(2==commissionType){//分类佣金比例
                    remark.append("品牌名称:").append(originEntity.getBrandName()).append(",");
    	            String tempLevel=originEntity.getBaseCatLevel();
                    if(StringUtils.isNotBlank(tempLevel)){
                        if("1".equals(tempLevel)){
                            tempLevel="一";
                        }else if("2".equals(tempLevel)){
                            tempLevel="二";
                        }
                        remark.append(tempLevel).append("级分类:").append(originEntity.getCatName()+",");
                    }
    	            logEntity.setBaseCatId(originEntity.getBaseCatId());	
    	            logEntity.setBrandNo(originEntity.getBrandNo());
    	            logEntity.setCommissionType("2"); 
    	        }else if(3==commissionType){
    	            remark.append("款色编码:").append(originEntity.getSupplierCode()).append(",")
    	            .append("商品名称:").append(originEntity.getCommodityName()+",");
    	            logEntity.setSupplierCode(originEntity.getSupplierCode());
    	            logEntity.setCommodityId(originEntity.getCommodityId());
    	            logEntity.setCommissionType("3"); 
    	        }
    	        remark.append("一级佣金比例:由【").append(originEntity.getCommissionLevel1Percent()).append("%】调整为【")
                .append(commissionPercentEntity.getCommissionLevel1Percent()).append("%】,二级佣金比例:由【")
                .append(originEntity.getCommissionLevel2Percent()).append("%】调整为【").append(commissionPercentEntity.getCommissionLevel2Percent()).append("%】,三级佣金比例:由【")
                .append(originEntity.getCommissionLevel3Percent()).append("%】调整为【").append(commissionPercentEntity.getCommissionLevel3Percent()).append("%】");
                 
    	        String strId = UUIDGenerator.get32LowCaseUUID();
    	        logEntity.setId(strId);
    	        logEntity.setCreateUser(commissionPercentEntity.getUpdateUser());
    	        logEntity.setOperateType("2");
    	        logEntity.setRemark(remark.toString());
    	        commissionPercentMapper.insertLog(logEntity);
	        }
	    }
		return  1;
	}
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public int remove(String id,String updateUser){
	    CommissionPercentEntity commissionPercentEntity=new CommissionPercentEntity();
	    commissionPercentEntity.setId(id);
        List<CommissionPercentEntity> originEntityList=commissionPercentMapper.findPage(commissionPercentEntity,new RowBounds(0,1));
        if(originEntityList!=null&&originEntityList.size()>0){
            int temp= commissionPercentMapper.remove(id);
            if(temp>0){
    	        CommissionPercentEntity originEntity=originEntityList.get(0);
                CommissionPercentLogEntity logEntity=new CommissionPercentLogEntity();
                StringBuilder remark=new StringBuilder("");
                int commissionType=originEntity.getType();
                if (1==commissionType) {
                    logEntity.setCommissionType("1"); 
                }else if(2==commissionType){//分类佣金比例
                    remark.append("品牌名称:").append(originEntity.getBrandName()).append(",");
                    String tempLevel=originEntity.getBaseCatLevel();
                    if(StringUtils.isNotBlank(tempLevel)){
                        if("1".equals(tempLevel)){
                            tempLevel="一";
                        }else if("2".equals(tempLevel)){
                            tempLevel="二";
                        }
                        remark.append(tempLevel).append("级分类:").append(originEntity.getCatName()+",");
                    }
                    logEntity.setBaseCatId(originEntity.getBaseCatId()); 
                    logEntity.setBrandNo(originEntity.getBrandNo());
                    logEntity.setCommissionType("2"); 
                }else if(3==commissionType){
                    remark.append("款色编码:").append(originEntity.getSupplierCode()).append(",")
                    .append("商品名称:").append(originEntity.getCommodityName()+",");
                    logEntity.setSupplierCode(originEntity.getSupplierCode());
                    logEntity.setCommodityId(originEntity.getCommodityId());
                    logEntity.setCommissionType("3"); 
                }
                remark.append("一级佣金比例:为【").append(originEntity.getCommissionLevel1Percent()).append("%】,二级佣金比例:为【")
               .append(originEntity.getCommissionLevel2Percent()).append("%】,三级佣金比例:为【")
               .append(originEntity.getCommissionLevel3Percent()+"%】");
    
                logEntity.setBaseCatId(originEntity.getBaseCatId());
                String strId = UUIDGenerator.get32LowCaseUUID();
                logEntity.setId(strId);
                logEntity.setCreateUser(updateUser);
                logEntity.setOperateType("3");
                logEntity.setRemark(remark.toString());
                commissionPercentMapper.insertLog(logEntity);
            }
        }
        return  1;
	}

	@Override
	public CommissionPercentEntity getById(String id){
		return commissionPercentMapper.getById(id);
	} 
	
	@Override
    public List<CommissionPercentEntity> getByBaseCatId(List<String> list) {
        if(null !=list&&list.size()>0){
            List<CommissionPercentEntity> entityList=new ArrayList<CommissionPercentEntity>();
            for (String baseCatId : list) {
                CommissionPercentEntity commissionEntity= commissionPercentMapper.getByBaseCatId(baseCatId);
                CommodityCatb2cEntity catEntity=commodityCatb2cMapper.getById(baseCatId);
                if(null==commissionEntity&&null!=catEntity&&2==catEntity.getLevel()){//二级商品分类没有设置佣金比例，往上级查询一级商品分类设置的佣金比例
                    commissionEntity= commissionPercentMapper.getByBaseCatId(catEntity.getParentId());
                    if(null==commissionEntity){//一级商品分类没有设置佣金比例，查询默认设置的佣金比例
                        commissionEntity= commissionPercentMapper.getById(FinanaceConstant.DEFAULT_COMMISSION_ID);
                    }
                }
                if(null!=commissionEntity){
                    CommissionPercentEntity tempEntity=new CommissionPercentEntity();
                    tempEntity.setBaseCatId(baseCatId);
                    tempEntity.setType(commissionEntity.getType());
                    tempEntity.setCommissionLevel1Percent(
                            CalculateUtils.multiply(commissionEntity.getCommissionLevel1Percent(), 0.01, 4));
                    tempEntity.setCommissionLevel2Percent(
                            CalculateUtils.multiply(commissionEntity.getCommissionLevel2Percent(), 0.01, 4));
                    tempEntity.setCommissionLevel3Percent(
                            CalculateUtils.multiply(commissionEntity.getCommissionLevel3Percent(), 0.01, 4));
                    entityList.add(tempEntity);
                    commissionEntity=null;//有事务时候，容易导致缓存，这里去缓存保持最新数据库数据
                }
            }
            return entityList;
        }else{
            return null;
        }
    }
	
	@Override
	public CommissionPercentEntity getCommissionByCondition(String brandNo, String baseCatId, String commodityid){
	    logger.info("佣金比例获取,品牌编码:"+brandNo+",分类id:"+baseCatId+"商品id:"+commodityid);
	    CommissionPercentEntity paramEntity=new CommissionPercentEntity();
	    //1、单品佣金比例
        paramEntity.setCommodityId(commodityid);
        CommissionPercentEntity commissionEntity=commissionPercentMapper.getCommissionByCondition(paramEntity);
        
	    //2、品牌+分类id
        if(null==commissionEntity&&StringUtils.isNotBlank(baseCatId)){
            //不管传参数是几级id，获得2级或者1级分类id
            CommodityCatb2cEntity catEntity=commodityCatb2cMapper.getById(baseCatId);
            if(null!=catEntity&&catEntity.getLevel()!=2&&catEntity.getLevel()!=1){
                for (int i = 0; i < 5; i++) {//
                    if(null!=catEntity&&(catEntity.getLevel()==2||catEntity.getLevel()==1)){
                        break;
                    }else if(null!=catEntity){
                        catEntity=commodityCatb2cMapper.getById(catEntity.getParentId());
                    }else{
                        break;
                    }
                }
            }
            if(catEntity!=null&&(catEntity.getLevel()==2||catEntity.getLevel()==1)){//得到分类id是2级或者1级
                paramEntity.setCommodityId(null);
                paramEntity.setBrandNo(brandNo);
                paramEntity.setBaseCatId(catEntity.getId());//品牌+分类id查询，这里的id可能是一级或者二级
                commissionEntity=commissionPercentMapper.getCommissionByCondition(paramEntity);
                if(null==commissionEntity&&catEntity.getLevel()==2){//当前分类id是二级，但是没有查询到，就采用父类一级id，继续往上查询
                    paramEntity.setBaseCatId(catEntity.getParentId());//1级分类id
                    commissionEntity=commissionPercentMapper.getCommissionByCondition(paramEntity);
                }
            }
        }
        //3、单品牌
        if(null==commissionEntity){
            paramEntity.setCommodityId(null);
            paramEntity.setBrandNo(brandNo);
            paramEntity.setBaseCatId("has");//单品牌查询只有一个品牌参数，分类id查询应该为null
            commissionEntity=commissionPercentMapper.getCommissionByCondition(paramEntity);
        }
        //4、默认佣金比例 
        if(null==commissionEntity){
            paramEntity.setCommodityId(null);
            paramEntity.setBrandNo(null);
            paramEntity.setBaseCatId(null);
            paramEntity.setId(FinanaceConstant.DEFAULT_COMMISSION_ID);
            commissionEntity=commissionPercentMapper.getCommissionByCondition(paramEntity);
        }
        if(null!=commissionEntity){
            CommissionPercentEntity tempEntity=new CommissionPercentEntity();
            tempEntity.setType(commissionEntity.getType());
            tempEntity.setCommissionLevel1Percent(
                    CalculateUtils.multiply(commissionEntity.getCommissionLevel1Percent(), 0.01, 4));
            tempEntity.setCommissionLevel2Percent(
                    CalculateUtils.multiply(commissionEntity.getCommissionLevel2Percent(), 0.01, 4));
            tempEntity.setCommissionLevel3Percent(
                    CalculateUtils.multiply(commissionEntity.getCommissionLevel3Percent(), 0.01, 4));
            return tempEntity;
	    }else{
	        return null;
	    }
    }

    @Override
    public int findPageCountLog(CommissionPercentLogEntity logEntity) {
        return commissionPercentMapper.findPageCountLog(logEntity);
    }
    @Override
    public List<CommissionPercentLogEntity> findPageLog(CommissionPercentLogEntity logEntity, RowBounds rowBounds) {
        return commissionPercentMapper.findPageLog(logEntity, rowBounds);
    }
}