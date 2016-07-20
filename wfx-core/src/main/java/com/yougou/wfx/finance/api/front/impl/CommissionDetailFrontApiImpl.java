 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotNull;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.front.ICommissionDetailFrontApi;
import com.yougou.wfx.finance.dto.input.CommissionDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionCollectOutputDto;
import com.yougou.wfx.finance.dto.output.CommissionDetailOutputDto;
import com.yougou.wfx.finance.model.CommissionDetailEntity;
import com.yougou.wfx.finance.service.ICommissionDetailService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.order.dto.output.OrderDetailCommissionOutPutDto;
import com.yougou.wfx.order.service.IOrderDetailService;

/**
 * ICommissionDetailFrontApi实现
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
@Service
public class CommissionDetailFrontApiImpl implements ICommissionDetailFrontApi{
	
	@Resource
	private ICommissionDetailService commissionDetailService;
	@Resource
    private IOrderDetailService orderDetailService;
	
	@Override
    @LoggerProfile(methodNote="手机客户端佣金明细列表分页查询")
    public PageModel<CommissionDetailOutputDto> findPage(@NotNull CommissionDetailPageInputDto pageInputDto,@NotNull PageModel<CommissionDetailOutputDto> pageModel) {
        CommissionDetailEntity commissionDetailEntity = (CommissionDetailEntity) BeanUtil.convertBean(pageInputDto,CommissionDetailEntity.class);
        RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
        if(null!=commissionDetailEntity.getStatus()&&"1".equals(commissionDetailEntity.getStatus())){//查询已经结算数据
            commissionDetailEntity.setDealType("1");//只查询已经结算的数据
        }
        int totalCount = commissionDetailService.findPageCount(commissionDetailEntity);
        List<CommissionDetailEntity> lstCommissionDetails = commissionDetailService.findPage(commissionDetailEntity, rowBounds);
        return new PageModel<CommissionDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommissionDetailOutputDto>) BeanUtil.convertBeanList(lstCommissionDetails,CommissionDetailOutputDto.class));
    }
	
	@Override
	@LoggerProfile(methodNote="H5佣金明细列表分页查询")
	public PageModel<CommissionDetailOutputDto> findPageH5(@NotNull Map<String,String> map,@NotNull PageModel<CommissionDetailOutputDto> pageModel) {
		if(StringUtils.isBlank(map.get("sellerId"))){
		    return null;
		}
	    RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		int totalCount = commissionDetailService.findPageCountGroup(map);
		List<CommissionDetailEntity> lstCommissionDetails = commissionDetailService.findPageGroup(map, rowBounds);
		return new PageModel<CommissionDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommissionDetailOutputDto>) BeanUtil.convertBeanList(lstCommissionDetails,CommissionDetailOutputDto.class));
	}

	@Override
	@LoggerProfile(methodNote="分销商佣金明细分类汇总")
	public CommissionCollectOutputDto getBySellerId( String sellerId){
	    if(StringUtils.isNotBlank(sellerId)){
	        CommissionDetailEntity commissionDetailEntity=new CommissionDetailEntity();
	        commissionDetailEntity.setSellerId(sellerId);
	        commissionDetailEntity.setStatus("0");
	        Map<String,Object> unSettlementDetailMap = commissionDetailService.findCommissionCollect(commissionDetailEntity);
	        commissionDetailEntity.setStatus("1");
	        Map<String,Object> settlementDetailMap = commissionDetailService.findCommissionCollect(commissionDetailEntity);
	       
	        CommissionCollectOutputDto dto=new CommissionCollectOutputDto();
	        dto.setSellerId(sellerId);
	        if(null!=unSettlementDetailMap&&unSettlementDetailMap.size()>0){
	            dto.setUnSettlementAmount(((BigDecimal)unSettlementDetailMap.get("commissionAmount")).doubleValue());//未结算
	        }
	        if(null!=settlementDetailMap&&settlementDetailMap.size()>0){
	            dto.setSettlementAmount(((BigDecimal)settlementDetailMap.get("commissionAmount")).doubleValue());//已结算
	        }
	        return dto;
        }else{
            return null;
        }
	}
	

	@Override
	@LoggerProfile(methodNote="手机客户端佣金明细-订单详情查看")
	public OrderDetailCommissionOutPutDto getCommissionDetailByNo(String commissionId){
	    if(StringUtils.isNotBlank(commissionId)){
	        return orderDetailService.getCommByCommId(commissionId);
	    }else{
	        return null;
	    }
	}
	
	@Override
    @LoggerProfile(methodNote="H5佣金明细-订单详情查看")
    public List<OrderDetailCommissionOutPutDto> getCommissionDetailByH5(Map<String,String> map){
        if(map!=null&&StringUtils.isNotBlank(map.get("sellerId"))&&StringUtils.isNotBlank(map.get("wfxOrderDetailNo"))){
            return null;
        }else{
            return null;
        }
    }
}
