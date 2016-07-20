 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.background.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.api.background.IMemberActionLogBackgroundApi;
import com.yougou.wfx.member.dto.input.MemberActionLogInputDto;
import com.yougou.wfx.member.dto.input.MemberActionLogPageInputDto;
import com.yougou.wfx.member.dto.output.MemberActionLogOutputDto;
import com.yougou.wfx.member.model.MemberActionLogEntity;
import com.yougou.wfx.member.service.IMemberActionLogService;

/**
 * IMemberActionLogBackgroundApi实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
@Service
public class MemberActionLogBackgroundApiImpl implements IMemberActionLogBackgroundApi{
	
	@Resource
	IMemberActionLogService memberActionLogService;
	
	@Override
	public int removeById(String id) {
		return memberActionLogService.remove(id);
	}

	@Override
	public String insert(MemberActionLogInputDto memberActionLogDto) {
		return memberActionLogService.insert(this.dtoToEntity(memberActionLogDto));
	}

	@Override
	public PageModel<MemberActionLogOutputDto> findPage(MemberActionLogPageInputDto pageInputDto,PageModel pageModel) {
		MemberActionLogEntity memberActionLogEntity = (MemberActionLogEntity) BeanUtil.convertBean(pageInputDto,MemberActionLogEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = memberActionLogService.findPageCount(memberActionLogEntity);
		List<MemberActionLogEntity> lstMemberActionLogs = memberActionLogService.findPage(memberActionLogEntity, rowBounds);

		return new PageModel<MemberActionLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<MemberActionLogOutputDto>) BeanUtil.convertBeanList(lstMemberActionLogs,MemberActionLogOutputDto.class));
	}

	@Override
	public int update(MemberActionLogInputDto memberActionLogDto) {
		return memberActionLogService.update(this.dtoToEntity(memberActionLogDto));
	}

	@Override
	public MemberActionLogOutputDto getById(String id) {
		MemberActionLogEntity memberActionLogEntity = memberActionLogService.getById(id);
		return this.entityToDto(memberActionLogEntity);
	}
	
	private MemberActionLogEntity dtoToEntity(Object obj){
		return (MemberActionLogEntity) BeanUtil.convertBean(obj,MemberActionLogEntity.class);
	}
	
	private MemberActionLogOutputDto entityToDto(Object obj){
		return (MemberActionLogOutputDto) BeanUtil.convertBean(obj,MemberActionLogOutputDto.class);
	}
}
