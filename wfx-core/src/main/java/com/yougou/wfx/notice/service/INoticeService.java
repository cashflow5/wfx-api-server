 /*
 * 版本信息
 
 * 日期 2016-06-20 14:03:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.notice.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.notice.model.NoticeEntity;

/**
 * INoticeService接口
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
public interface INoticeService {
	
	/**
	 * 获取总条数
	 */
	public int findPageCount(NoticeEntity noticeEntity);

	/**
	 * 获取分页数据
	 */
	public List<NoticeEntity> findPage(NoticeEntity noticeEntity,RowBounds rowBounds);
	
	/**
	 * 保存单条记录
	 */
	public String insert(NoticeEntity noticeEntity);
	
	/**
	 * 更新记录
	 */
	public int update(NoticeEntity noticeEntity);
	
	/**
	 * 通过id删除记录
	 */
	public int remove(String id);
	
	/**
	 * 通过id查询数据
	 */
	public NoticeEntity getById(String id);
	
	/**
	 * 参数条件查询
	 * @param noticeEntity
	 * @return
	 */
	public List<NoticeEntity> queryList(NoticeEntity noticeEntity); 
}