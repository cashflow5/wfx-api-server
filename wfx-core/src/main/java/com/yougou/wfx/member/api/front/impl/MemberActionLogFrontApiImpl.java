 /*
 * 版本信息
 
 * 日期 2016-04-18 16:48:04
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.member.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.api.front.IMemberActionLogFrontApi;
import com.yougou.wfx.member.dto.input.MemberActionLogInputDto;
import com.yougou.wfx.member.dto.input.MemberActionLogPageInputDto;
import com.yougou.wfx.member.dto.output.MemberActionLogOutputDto;
import com.yougou.wfx.member.model.MemberActionLogEntity;
import com.yougou.wfx.member.service.IMemberActionLogService;

/**
 * MemberActionLogFrontApiImpl实现
 * @author zheng.qq
 * @Date 创建时间：2016-04-18 16:48:05
 */
@Service
public class MemberActionLogFrontApiImpl implements IMemberActionLogFrontApi{
	
	@Resource
	IMemberActionLogService memberActionLogService;
	

	@Override
	@LoggerProfile(methodNote="新增用户操作日志")
	public String insert(@NotBlank.List(value={@NotBlank(target="loginaccountId")}) MemberActionLogInputDto memberActionLogDto) {
		return memberActionLogService.insert(this.dtoToEntity(memberActionLogDto));
	}

	@Override
	@LoggerProfile(methodNote="查询用户操作日志")
	public PageModel<MemberActionLogOutputDto> findPage(@NotNull MemberActionLogPageInputDto pageInputDto,@NotNull PageModel pageModel) {
		MemberActionLogEntity memberActionLogEntity = (MemberActionLogEntity) BeanUtil.convertBean(pageInputDto,MemberActionLogEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = memberActionLogService.findPageCount(memberActionLogEntity);
		List<MemberActionLogEntity> lstMemberActionLogs = memberActionLogService.findPage(memberActionLogEntity, rowBounds);

		return new PageModel<MemberActionLogOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<MemberActionLogOutputDto>) BeanUtil.convertBeanList(lstMemberActionLogs,MemberActionLogOutputDto.class));
	}

	
	private MemberActionLogEntity dtoToEntity(Object obj){
		return (MemberActionLogEntity) BeanUtil.convertBean(obj,MemberActionLogEntity.class);
	}
}
