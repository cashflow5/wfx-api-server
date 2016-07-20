package com.yougou.wfx.visitor.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.yougou.wfx.visitor.dto.output.VisitorCountOutputDto;
import com.yougou.wfx.visitor.dto.output.VisitorOutputDto;
import com.yougou.wfx.visitor.model.VisitorEntity;

/**
 * 用户访客DAO 持久层接口
 * @author zhang.f1
 *
 */
public interface IVisitorMapper {
	
	/**
	 * 新增用户访客记录
	 * @param visitorEntity
	 * @return
	 */
	int insertVisitor(VisitorEntity visitorEntity);
	
	
	
	/**
	 * 分页获取当日店铺访客列表
	 * @param pageInputDto
	 * @param shopId 店铺ID
	 * @return
	 */
	List<VisitorEntity> queryCurDayVisitorList(@Param("shopId")String shopId,@Param("date")Date date,RowBounds rowBounds);
	
	/**
	 * 获取当日店铺访客总数
	 * @param shopId
	 * @return
	 */
	int queryCurDayVisitCount(@Param("shopId")String shopId,@Param("date")Date date);
	
	/**
	 * 查询用户当天访问店铺记录
	 * @param shopId
	 * @param visitorId
	 * @return
	 */
	int queryVisitorCurDayVisitCount(@Param("shopId")String shopId,@Param("visitorId")String visitorId);
	
	/**
	 * 查询店铺当天各个来源的访客数量
	 * @param shopId
	 * @return
	 */
	VisitorCountOutputDto queryVisitorSourceCount(@Param("shopId")String shopId,@Param("date")Date date);
	
	/**
	 * 查询最近7天店铺每天的访客数量
	 * @param shopId
	 * @return
	 */
	List<Map<String,Object>> query7DaysVisitorCount(@Param("shopId")String shopId);
	
	/**
	 * 查询店铺当日所有访客数量
	 * @param shopId
	 * @return
	 */
	int queryShopCurDayVisitCount(@Param("shopId")String shopId);
}
