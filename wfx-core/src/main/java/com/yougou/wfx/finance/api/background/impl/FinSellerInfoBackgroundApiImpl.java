 /*
 * 版本信息
 
 * 日期 2016-03-28 10:58:34
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.background.IFinSellerInfoBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinSellerInfoInputDto;
import com.yougou.wfx.finance.dto.input.FinSellerInfoPageInputDto;
import com.yougou.wfx.finance.dto.output.FinSellerInfoOutputDto;
import com.yougou.wfx.finance.dto.output.MessageOutputDto;
import com.yougou.wfx.finance.model.FinSellerInfoEntity;
import com.yougou.wfx.finance.service.IFinSellerInfoService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IFinSellerInfoBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-03-28 10:58:34
 */
@Service
public class FinSellerInfoBackgroundApiImpl implements IFinSellerInfoBackgroundApi{
	private static Logger LOG = LoggerFactory.getLogger(FinSellerInfoBackgroundApiImpl.class.getName());
	
	@Resource
	IFinSellerInfoService finSellerInfoService;
	
	@Override
	public MessageOutputDto createSeller(FinSellerInfoInputDto finSellerInfoDto) {
		LOG.info("FMSDubbo创建分销商，接收数据："+finSellerInfoDto);
		return finSellerInfoService.createSeller(this.dtoToEntity(finSellerInfoDto));
	}

	@Override
	public PageModel<FinSellerInfoOutputDto> findPage(FinSellerInfoPageInputDto pageInputDto,PageModel pageModel) {
		FinSellerInfoEntity finSellerInfoEntity = (FinSellerInfoEntity) BeanUtil.convertBean(pageInputDto,FinSellerInfoEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finSellerInfoService.findPageCount(finSellerInfoEntity);
		List<FinSellerInfoEntity> lstFinSellerInfos = finSellerInfoService.findPage(finSellerInfoEntity, rowBounds);

		return new PageModel<FinSellerInfoOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinSellerInfoOutputDto>) BeanUtil.convertBeanList(lstFinSellerInfos,FinSellerInfoOutputDto.class));
	}

	@Override
	public int update(FinSellerInfoInputDto finSellerInfoDto) {
		LOG.info("FMSDubbo修改分销商，接收数据："+finSellerInfoDto);
		return finSellerInfoService.update(this.dtoToEntity(finSellerInfoDto));
	}

	@Override
	public FinSellerInfoOutputDto getById(String id) {
		FinSellerInfoEntity finSellerInfoEntity = finSellerInfoService.getById(id);
		return this.entityToDto(finSellerInfoEntity);
	}
	
	private FinSellerInfoEntity dtoToEntity(Object obj){
		return (FinSellerInfoEntity) BeanUtil.convertBean(obj,FinSellerInfoEntity.class);
	}
	
	private FinSellerInfoOutputDto entityToDto(Object obj){
		return (FinSellerInfoOutputDto) BeanUtil.convertBean(obj,FinSellerInfoOutputDto.class);
	}

	@Override
	public Map<String, Object> queryAccountSummary(FinSellerInfoInputDto finSellerInfoInputDto) {
		return finSellerInfoService.queryAccountSummary(dtoToEntity(finSellerInfoInputDto));
	}
	
}
