 /*
 * 版本信息
 
 * 日期 2016-04-15 16:09:24
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.seller.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.seller.api.background.ISellerBankBackgroundApi;
import com.yougou.wfx.seller.dto.output.SellerBankOutputDto;
import com.yougou.wfx.seller.model.SellerBankEntity;
import com.yougou.wfx.seller.service.ISellerBankService;

/**
 * ISellerBankBackgroundApi实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-15 16:09:26
 */
@Service
public class SellerBankBackgroundApiImpl implements ISellerBankBackgroundApi{
	@Resource
	private ISellerBankService sellerBankService;
	
	@Override
	@LoggerProfile(methodNote="通过分销商ID查询绑定银行账号")
	public List<SellerBankOutputDto> getSellerBanks(String sellerId) {
		// TODO Auto-generated method stub
		List<SellerBankEntity> entityList = sellerBankService.getSellerBanks(sellerId);
		return (List<SellerBankOutputDto>) BeanUtil.convertBeanList(entityList, SellerBankOutputDto.class);
	}
	
	
}
