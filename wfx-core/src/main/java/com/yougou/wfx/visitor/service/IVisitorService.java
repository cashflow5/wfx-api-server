package com.yougou.wfx.visitor.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.visitor.dto.input.VisitorInputDto;
import com.yougou.wfx.visitor.dto.input.VisitorPageInputDto;
import com.yougou.wfx.visitor.dto.output.VisitorCountOutputDto;
import com.yougou.wfx.visitor.dto.output.VisitorOutputDto;
import com.yougou.wfx.visitor.model.VisitorEntity;

/**
 * 用户访客记录服务层接口
 * @author zhang.f1
 *
 */
public interface IVisitorService {
	
	/**
	 * 新增用户访客记录
	 * @param visitorEntity
	 * @return
	 */
	void insertVisitor(VisitorEntity visitorEntity);
	
	
	
	/**
	 * 分页获取当日店铺访客列表
	 * @param pageInputDto
	 * @param shopId 店铺ID
	 * @return
	 */
	List<VisitorEntity> queryCurDayVisitorList(String shopId,Date date,RowBounds rowBounds);
	
	/**
	 * 获取当日店铺访客总数
	 * @param shopId
	 * @return
	 */
	int queryCurDayVisitCount(String shopId,Date date);
	
	/**
	 * 查询店铺某天各个来源的访客数量
	 * @param shopId
	 * @param date 查询的日期 
	 * @return
	 */
	VisitorCountOutputDto queryVisitorSourceCount(String shopId,Date date);
	
	/**
	 * 查询最近7天店铺每天的访客数量
	 * @param shopId
	 * @return
	 */
	Map<String,Object> query7DaysVisitorCount(String shopId);
	
	/**
	 * 查询店铺当日所有访客数量
	 * @param shopId
	 * @return
	 */
	int queryShopCurDayVisitCount(String shopId);
	
}
