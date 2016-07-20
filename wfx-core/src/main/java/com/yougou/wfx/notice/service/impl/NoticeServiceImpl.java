 /*
 * 版本信息
 
 * 日期 2016-06-20 14:03:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.notice.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.notice.dao.NoticeMapper;
import com.yougou.wfx.notice.model.NoticeEntity;
import com.yougou.wfx.notice.service.INoticeService;

/**
 * INoticeService实现
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
@Service
public class NoticeServiceImpl extends BaseService implements INoticeService {
	
	@Resource
	private NoticeMapper noticeMapper;

	@Override
	public int findPageCount(NoticeEntity noticeEntity){
		return noticeMapper.findPageCount(noticeEntity);
	}

	@Override
	public List<NoticeEntity> findPage(NoticeEntity noticeEntity,RowBounds rowBounds){
		return noticeMapper.findPage(noticeEntity,rowBounds);
	}
	
	@Override
	@Transactional
	public String insert(NoticeEntity noticeEntity){
		String strId = UUIDGenerator.get32LowCaseUUID();
		noticeEntity.setId(strId);
		noticeMapper.insert(noticeEntity);
		return strId;
	}
	
	@Override
	@Transactional
	public int update(NoticeEntity noticeEntity){
		return  noticeMapper.update(noticeEntity);
	}
	
	@Override
	@Transactional
	public int remove(String id){
		return noticeMapper.remove(id);
	}
	
	@Override
	public NoticeEntity getById(String id){
		return noticeMapper.getById(id);
	}

	@Override
	public List<NoticeEntity> queryList(NoticeEntity noticeEntity) {
		return noticeMapper.queryList(noticeEntity);
	} 
}