 /*
 * 版本信息
 
 * 日期 2016-06-20 14:03:02
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.notice.api.background;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.notice.dto.input.NoticeInputDto;
import com.yougou.wfx.notice.dto.input.NoticePageInputDto;
import com.yougou.wfx.notice.dto.output.NoticeOutputDto;

/**
 * INoticeBackgroundApi
 * @author wfx
 * @Date 创建时间：2016-06-20 14:03:02
 */
public interface INoticeBackgroundApi{
	/**
	 * 通过id删除记录
	 */
	public int removeById(String id);
	
	/**
	 * 保存单条记录
	 */
	public String insert(NoticeInputDto noticeDto);
	
	/**
	 * 获取分页数据
	 */
	public PageModel<NoticeOutputDto> findPage(NoticePageInputDto pageInputDto,PageModel pageModel);
	
	/**
	 * 更新记录
	 */
	public int update(NoticeInputDto noticeDto);
	
	/**
	 * 通过id查询数据
	 */
	public NoticeOutputDto getById(String id);
}

