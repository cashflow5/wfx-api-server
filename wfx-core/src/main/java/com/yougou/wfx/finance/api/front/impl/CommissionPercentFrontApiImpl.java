 /*
 * 版本信息
 
 * 日期 2016-03-25 11:09:26
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.front.ICommissionPercentFrontApi;
import com.yougou.wfx.finance.dto.input.CommissionPercentPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionPercentOutputDto;
import com.yougou.wfx.finance.model.CommissionPercentEntity;
import com.yougou.wfx.finance.service.ICommissionPercentService;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommissionPercentFrontApi实现
 * @author langqiwei
 * @Date 创建时间：2016-03-25 11:09:26
 */
@Service
public class CommissionPercentFrontApiImpl implements ICommissionPercentFrontApi{
	
	@Resource
	ICommissionPercentService commissionPercentService;
	

	@Override
	@LoggerProfile(methodNote="佣金比例分页查询")
	public PageModel<CommissionPercentOutputDto> findPage(@NotNull CommissionPercentPageInputDto pageInputDto,@NotNull PageModel<CommissionPercentOutputDto> pageModel) {
		CommissionPercentEntity commissionPercentEntity = (CommissionPercentEntity) BeanUtil.convertBean(pageInputDto,CommissionPercentEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commissionPercentService.findPageCount(commissionPercentEntity);
		List<CommissionPercentEntity> lstCommissionPercents = commissionPercentService.findPage(commissionPercentEntity, rowBounds);

		return new PageModel<CommissionPercentOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommissionPercentOutputDto>) BeanUtil.convertBeanList(lstCommissionPercents,CommissionPercentOutputDto.class));
	}

	@Override
    @LoggerProfile(methodNote="根据分类ID获取佣金比例")
    public List<CommissionPercentOutputDto> getByBaseCatId(List<String> idList) {
	    List<CommissionPercentEntity> list=commissionPercentService.getByBaseCatId(idList);
	    if(null!=list&&list.size()>0){
	        return (List<CommissionPercentOutputDto>) BeanUtil.convertBeanList(list,CommissionPercentOutputDto.class);
	    }else{
	        return null;
	    }
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
