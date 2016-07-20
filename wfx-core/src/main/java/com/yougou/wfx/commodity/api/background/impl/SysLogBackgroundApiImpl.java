 /*
 * 版本信息
 
 * 日期 2016-03-30 10:51:22
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.commodity.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.commodity.api.background.ISysLogBackgroundApi;
import com.yougou.wfx.commodity.dto.input.SysLogInputDto;
import com.yougou.wfx.commodity.dto.input.SysLogPageInputDto;
import com.yougou.wfx.commodity.dto.output.SysLogOutputDto;
import com.yougou.wfx.commodity.model.SysLogEntity;
import com.yougou.wfx.commodity.service.ISysLogService;
import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;

/**
 * ISysLogBackgroundApi实现
 * @author luoq
 * @Date 创建时间：2016-03-30 10:51:23
 */
@Service
public class SysLogBackgroundApiImpl implements ISysLogBackgroundApi{
	
	@Resource
	ISysLogService sysLogService;
	
	@Override
	public int removeById(String id) {
		return sysLogService.remove(id);
	}

	@Override
	public String insertSysLog(SysLogInputDto sysLogDto) {
		return sysLogService.insert(this.dtoToEntity(sysLogDto));
	}

	@Override
	public PageModel<SysLogOutputDto> findPage(SysLogPageInputDto pageInputDto,PageModel<SysLogOutputDto> pageModel) {
		SysLogEntity sysLogEntity = (SysLogEntity) BeanUtil.convertBean(pageInputDto,SysLogEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = sysLogService.findPageCount(sysLogEntity);
		List<SysLogEntity> lstSysLogs = sysLogService.findPage(sysLogEntity, rowBounds);

		return new PageModel<SysLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<SysLogOutputDto>) BeanUtil.convertBeanList(lstSysLogs,SysLogOutputDto.class));
	}

	@Override
	public int update(SysLogInputDto sysLogDto) {
		return sysLogService.update(this.dtoToEntity(sysLogDto));
	}

	@Override
	public SysLogOutputDto getSysLogById(String id) {
		SysLogEntity sysLogEntity = sysLogService.getById(id);
		return this.entityToDto(sysLogEntity);
	}
	
	private SysLogEntity dtoToEntity(Object obj){
		return (SysLogEntity) BeanUtil.convertBean(obj,SysLogEntity.class);
	}
	
	private SysLogOutputDto entityToDto(Object obj){
		return (SysLogOutputDto) BeanUtil.convertBean(obj,SysLogOutputDto.class);
	}
}
