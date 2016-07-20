 /*
 * 版本信息
 
 * 日期 2016-06-23 14:26:46
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.visitor.api.front;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.visitor.dto.input.VisitorInputDto;
import com.yougou.wfx.visitor.dto.input.VisitorPageInputDto;
import com.yougou.wfx.visitor.dto.output.VisitorCountOutputDto;
import com.yougou.wfx.visitor.dto.output.VisitorOutputDto;

/**
 * 用户访客API
 * @author zhang.f
 * @Date 创建时间：2016-06-23 14:26:48
 */
public interface IVisitorFrontApi{
	
	
	/**
	 * 新增用户访客记录
	 * @param visitorDto
	 * @return
	 */
	void insertVisitor(VisitorInputDto visitorDto);
	
	
	
	/**
	 * 分页获取当日店铺访客列表
	 * @param pageInputDto
	 * @param shopId 店铺ID
	 * @return
	 */
	PageModel<VisitorOutputDto> queryCurDayVisitorList(String shopId,Date date,PageModel pageModel);
	
	/**
	 * 查询店铺某天各个来源的访客数量(date 为null 默认查当天)
	 * @param shopId 店铺ID
	 * @param date 需要查询的日期 yyyy-MM-dd
	 * @return
	 */
	VisitorCountOutputDto queryVisitorSourceCount(String shopId,Date date);
	
	/**
	 * 查询最近7天店铺每天的访客数量
	 * @param shopId
	 * @return map{categories：'[06月18日,06月19日...]',data[{name:06月18日,y:123},{...}]}
	 */
	Map<String,Object> query7DaysVisitorCount(String shopId);
	
	/**
	 * 查询店铺当日所有访客数量
	 * @param shopId
	 * @return
	 */
	int queryShopCurDayVisitCount(String shopId);
	
	
}

