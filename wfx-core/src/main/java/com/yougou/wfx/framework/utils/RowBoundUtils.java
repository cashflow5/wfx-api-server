package com.yougou.wfx.framework.utils;

import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.dto.base.PageModel;

/**
 * 分页工具
 */
public class RowBoundUtils {
	
	@SuppressWarnings("rawtypes")
	public static RowBounds rowBounds(PageModel pageModel){
		if(pageModel == null){
			pageModel = new PageModel();
		}
		RowBounds rowBounds = new RowBounds((pageModel.getPage()-1)*pageModel.getLimit(),pageModel.getLimit());
		return rowBounds;
	}	

}
