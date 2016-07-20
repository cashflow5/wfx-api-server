/*
 * 版本信息

 * 日期 2016-06-20 14:03:02

 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.notice.api.front.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.enums.NoticeStatusEnum;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.notice.api.front.INoticeFrontApi;
import com.yougou.wfx.notice.dto.output.NoticeOutputDto;
import com.yougou.wfx.notice.model.NoticeEntity;
import com.yougou.wfx.notice.service.INoticeService;

/**
 * INoticeBackgroundApi实现
 * 
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
@Service
public class NoticeFrontApiImpl implements INoticeFrontApi {

	@Resource
	INoticeService noticeService;

	@Override
	public List<NoticeOutputDto> getActiveNotice() {
		NoticeEntity noticeEntity=new NoticeEntity();
		noticeEntity.setStatus(NoticeStatusEnum.ACTIVE.getKey());
		List<NoticeEntity> lstNotices = noticeService.queryList(noticeEntity);
		return  (List<NoticeOutputDto>) BeanUtil.convertBeanList(lstNotices,NoticeOutputDto.class);
	}

	@Override
	public NoticeOutputDto getById(String id) {
		NoticeEntity noticeEntity = noticeService.getById(id);
		return this.entityToDto(noticeEntity);
	}

	private NoticeEntity dtoToEntity(Object obj) {
		return (NoticeEntity) BeanUtil.convertBean(obj, NoticeEntity.class);
	}

	private NoticeOutputDto entityToDto(Object obj) {
		return (NoticeOutputDto) BeanUtil.convertBean(obj, NoticeOutputDto.class);
	}

}
