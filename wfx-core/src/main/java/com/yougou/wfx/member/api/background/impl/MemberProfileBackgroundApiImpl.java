 /*
 * 版本信息
 
 * 日期 2016-03-24 16:17:44
 
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
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.member.api.background.IMemberProfileBackgroundApi;
import com.yougou.wfx.member.dto.input.MemberProfileInputDto;
import com.yougou.wfx.member.dto.input.MemberProfilePageInputDto;
import com.yougou.wfx.member.dto.output.MemberProfileOutputDto;
import com.yougou.wfx.member.model.MemberProfileEntity;
import com.yougou.wfx.member.service.IMemberProfileService;

/**
 * IMemberProfileBackgroundApi实现
 * @author wuyang
 * @Date 创建时间：2016-03-24 16:17:44
 */
@Service
public class MemberProfileBackgroundApiImpl implements IMemberProfileBackgroundApi{
	
	@Resource
	IMemberProfileService memberProfileService;
	
	@Override
	public int removeById(String id) {
		return memberProfileService.remove(id);
	}

	@Override
	public String insert(MemberProfileInputDto memberProfileDto) {
		return memberProfileService.insert(this.dtoToEntity(memberProfileDto));
	}

	@Override
	public PageModel<MemberProfileOutputDto> findPage(MemberProfilePageInputDto pageInputDto,PageModel<MemberProfileOutputDto> pageModel) {
		MemberProfileEntity memberProfileEntity = (MemberProfileEntity) BeanUtil.convertBean(pageInputDto,MemberProfileEntity.class);
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);

		int totalCount = memberProfileService.findPageCount(memberProfileEntity);
		List<MemberProfileEntity> lstMemberProfiles = memberProfileService.findPage(memberProfileEntity, rowBounds);

		return new PageModel<MemberProfileOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<MemberProfileOutputDto>) BeanUtil.convertBeanList(lstMemberProfiles,MemberProfileOutputDto.class));
	}

	@Override
	public int update(MemberProfileInputDto memberProfileDto) {
		return memberProfileService.update(this.dtoToEntity(memberProfileDto));
	}

	@Override
	public MemberProfileOutputDto getById(String id) {
		MemberProfileEntity memberProfileEntity = memberProfileService.getById(id);
		return this.entityToDto(memberProfileEntity);
	}
	
	private MemberProfileEntity dtoToEntity(Object obj){
		return (MemberProfileEntity) BeanUtil.convertBean(obj,MemberProfileEntity.class);
	}
	
	private MemberProfileOutputDto entityToDto(Object obj){
		return (MemberProfileOutputDto) BeanUtil.convertBean(obj,MemberProfileOutputDto.class);
	}

	@Override
	@LoggerProfile(methodNote="会员信息表统计数据：tbl_wfx_member_profile统计订单数量，金额")
	public int batchUpdateOrderAmountAndCount() {
		return memberProfileService.batchUpdateOrderAmountAndCount();
	}
}
