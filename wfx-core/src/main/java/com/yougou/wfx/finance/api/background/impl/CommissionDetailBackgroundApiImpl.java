 /*
 * 版本信息
 
 * 日期 2016-03-29 10:46:57
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.background.ICommissionDetailBackgroundApi;
import com.yougou.wfx.finance.dto.input.CommissionDetailInputDto;
import com.yougou.wfx.finance.dto.input.CommissionDetailPageInputDto;
import com.yougou.wfx.finance.dto.output.CommissionDetailOutputDto;
import com.yougou.wfx.finance.model.CommissionDetailEntity;
import com.yougou.wfx.finance.service.ICommissionDetailService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ICommissionDetailBackgroundApi实现
 * @author langqiwei
 * @Date 创建时间：2016-03-29 10:46:58
 */
@Service
public class CommissionDetailBackgroundApiImpl implements ICommissionDetailBackgroundApi{
    private static final Logger log = LoggerFactory.getLogger(CommissionDetailBackgroundApiImpl.class);

	@Resource
	private ICommissionDetailService commissionDetailService;
	

	@Override
	public String insert(CommissionDetailInputDto commissionDetailDto) {
		return commissionDetailService.insert(this.dtoToEntity(commissionDetailDto));
	}

	@Override
	public PageModel<CommissionDetailOutputDto> findPage(CommissionDetailPageInputDto pageInputDto,PageModel<CommissionDetailOutputDto> pageModel) {
		CommissionDetailEntity commissionDetailEntity = (CommissionDetailEntity) BeanUtil.convertBean(pageInputDto,CommissionDetailEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = commissionDetailService.findPageCount(commissionDetailEntity);
		List<CommissionDetailEntity> lstCommissionDetails = commissionDetailService.findPage(commissionDetailEntity, rowBounds);

		return new PageModel<CommissionDetailOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<CommissionDetailOutputDto>) BeanUtil.convertBeanList(lstCommissionDetails,CommissionDetailOutputDto.class));
	}
	
	@Override
    public List<CommissionDetailOutputDto> exportCommissionDetail(CommissionDetailPageInputDto pageInputDto) {
        CommissionDetailEntity commissionDetailEntity = (CommissionDetailEntity) BeanUtil.convertBean(pageInputDto,CommissionDetailEntity.class);
        List<CommissionDetailEntity> lstCommissionDetails = commissionDetailService.findPage(commissionDetailEntity, new RowBounds());
        return (List<CommissionDetailOutputDto>) BeanUtil.convertBeanList(lstCommissionDetails,CommissionDetailOutputDto.class);
    }

	@Override
	public int update(CommissionDetailInputDto commissionDetailDto) {
		return commissionDetailService.update(this.dtoToEntity(commissionDetailDto));
	}

	@Override
	public CommissionDetailOutputDto getById(String id) {
		CommissionDetailEntity commissionDetailEntity = commissionDetailService.getById(id);
		return this.entityToDto(commissionDetailEntity);
	}
	
	private CommissionDetailEntity dtoToEntity(Object obj){
		return (CommissionDetailEntity) BeanUtil.convertBean(obj,CommissionDetailEntity.class);
	}
	
	private CommissionDetailOutputDto entityToDto(Object obj){
		return (CommissionDetailOutputDto) BeanUtil.convertBean(obj,CommissionDetailOutputDto.class);
	}

    @Override
    public synchronized void dispatchCreateAccountBalance(){
        Map<String,String> hasId=new HashMap<String, String>();//保存结算报错id
        for (int i = 0; i <=100;i++) {
           //按照500分页查询取数据结算,因为数据变化的原因，未结算数据越来越少，选择固定取值就可以
            List<CommissionDetailEntity> sellerList= commissionDetailService.queryUnSettleCommission();//1、根据状态为0未结算查询
            if(null!=sellerList&&sellerList.size()>0){
                for (CommissionDetailEntity commissionDetailEntity : sellerList) {
                    //2、逐步迭代佣金汇总计入相应分销商账号余额
                    try {
                        commissionDetailService.dispatchCreateAccountBalance(commissionDetailEntity);
                    } catch (Exception e) {
                        String id=commissionDetailEntity.getId();
                        if(StringUtils.isNotBlank(hasId.get(id))){//防止因为结算异常，出项重复死循环报错结算
                            return;
                        }else{
                            hasId.put(id, "1");
                            log.error("dispatchCreateAccountBalance结算报错,佣金明细ID:"+id, e);
                            String message=e.getLocalizedMessage();
                            //已经结算数据，没有更新结算状态，通过佣金明细id防止重复，抛出异常来重新更新为已结算状态
                            if(StringUtils.isNotBlank(message)&&message.contains("FinSellerInfoDetailMapper.insert")&&
                                message.contains("for key 'idx_commission_id'")){
                                CommissionDetailEntity tempEntiy=new CommissionDetailEntity();
                                tempEntiy.setId(id);
                                tempEntiy.setStatus("1");
                                tempEntiy.setRemark("结算时状态更新失败,重新更新为结算状态");
                                commissionDetailService.update(tempEntiy);
                            }
                        }
                    }
                }
            }else{
                return;
            }
        }
    }

}
