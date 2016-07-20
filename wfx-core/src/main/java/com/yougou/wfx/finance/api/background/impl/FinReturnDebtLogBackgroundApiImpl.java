 /*
 * 版本信息
 
 * 日期 2016-04-05 19:11:06
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.finance.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.finance.api.background.IFinReturnDebtLogBackgroundApi;
import com.yougou.wfx.finance.dto.input.FinReturnDebtLogInputDto;
import com.yougou.wfx.finance.dto.input.FinReturnDebtLogPageInputDto;
import com.yougou.wfx.finance.dto.output.FinReturnDebtLogOutputDto;
import com.yougou.wfx.finance.model.FinReturnDebtLogEntity;
import com.yougou.wfx.finance.service.IFinReturnDebtLogService;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * IFinReturnDebtLogBackgroundApi实现
 * @author wfx
 * @Date 创建时间：2016-04-05 19:11:08
 */
@Service
public class FinReturnDebtLogBackgroundApiImpl implements IFinReturnDebtLogBackgroundApi{
	
	@Resource
	IFinReturnDebtLogService finReturnDebtLogService;
	
	@Override
	public int removeById(String id) {
		return finReturnDebtLogService.remove(id);
	}

	@Override
	public String insert(FinReturnDebtLogInputDto finReturnDebtLogDto) {
		return finReturnDebtLogService.insert(this.dtoToEntity(finReturnDebtLogDto));
	}

	@Override
	public PageModel<FinReturnDebtLogOutputDto> findPage(FinReturnDebtLogPageInputDto pageInputDto,PageModel pageModel) {
		FinReturnDebtLogEntity finReturnDebtLogEntity = (FinReturnDebtLogEntity) BeanUtil.convertBean(pageInputDto,FinReturnDebtLogEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = finReturnDebtLogService.findPageCount(finReturnDebtLogEntity);
		List<FinReturnDebtLogEntity> lstFinReturnDebtLogs = finReturnDebtLogService.findPage(finReturnDebtLogEntity, rowBounds);

		return new PageModel<FinReturnDebtLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<FinReturnDebtLogOutputDto>) BeanUtil.convertBeanList(lstFinReturnDebtLogs,FinReturnDebtLogOutputDto.class));
	}

	@Override
	public int update(FinReturnDebtLogInputDto finReturnDebtLogDto) {
		return finReturnDebtLogService.update(this.dtoToEntity(finReturnDebtLogDto));
	}

	@Override
	public FinReturnDebtLogOutputDto getById(String id) {
		FinReturnDebtLogEntity finReturnDebtLogEntity = finReturnDebtLogService.getById(id);
		return this.entityToDto(finReturnDebtLogEntity);
	}
	
	private FinReturnDebtLogEntity dtoToEntity(Object obj){
		return (FinReturnDebtLogEntity) BeanUtil.convertBean(obj,FinReturnDebtLogEntity.class);
	}
	
	private FinReturnDebtLogOutputDto entityToDto(Object obj){
		return (FinReturnDebtLogOutputDto) BeanUtil.convertBean(obj,FinReturnDebtLogOutputDto.class);
	}

	@Override
	public List<FinReturnDebtLogOutputDto> queryDataList(FinReturnDebtLogOutputDto finReturnDebtLogOutputDto) {
		List<FinReturnDebtLogEntity> finReturnDebtLogEntitys = this.finReturnDebtLogService.queryDataList(this.dtoToEntity(finReturnDebtLogOutputDto));
		return (List<FinReturnDebtLogOutputDto>) BeanUtil.convertBeanList(finReturnDebtLogEntitys,FinReturnDebtLogOutputDto.class);
	}


}
