 /*
 * 版本信息
 
 * 日期 2016-06-20 14:03:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.notice.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.notice.model.NoticeEntity;

/**
 * NoticeMapper
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
public interface NoticeMapper{
	
	int findPageCount(NoticeEntity noticeEntity);

	List<NoticeEntity> findPage(NoticeEntity noticeEntity,RowBounds rowBounds);
	
	int insert(NoticeEntity noticeEntity);
	
	int update(NoticeEntity noticeEntity);
	
	int remove(String id);
	
	NoticeEntity getById(String id);

	List<NoticeEntity> queryList(NoticeEntity noticeEntity);
}
