package com.yougou.wfx.visitor.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.wfx.dto.base.PageModel;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.utils.RowBoundUtils;
import com.yougou.wfx.seller.dto.output.SellerInfoOutputDto;
import com.yougou.wfx.seller.model.SellerInfoEntity;
import com.yougou.wfx.visitor.api.front.IVisitorFrontApi;
import com.yougou.wfx.visitor.dto.input.VisitorInputDto;
import com.yougou.wfx.visitor.dto.input.VisitorPageInputDto;
import com.yougou.wfx.visitor.dto.output.VisitorCountOutputDto;
import com.yougou.wfx.visitor.dto.output.VisitorOutputDto;
import com.yougou.wfx.visitor.model.VisitorEntity;
import com.yougou.wfx.visitor.service.IVisitorService;

import net.sf.oval.constraint.NotBlank;

@Service
public class VisitorFrontApiImpl implements IVisitorFrontApi {
	
	@Resource
	private IVisitorService visitorService;
	
	private VisitorEntity dtoToEntity(Object obj){
		return (VisitorEntity) BeanUtil.convertBean(obj,VisitorEntity.class);
	}
	
	private VisitorOutputDto entityToDto(Object obj){
		return (VisitorOutputDto) BeanUtil.convertBean(obj,VisitorOutputDto.class);
	}
	
	@LoggerProfile(methodNote = "新增访客记录")
	@Override
	public void insertVisitor(@NotBlank.List(value = { 
			@NotBlank(target="shopId") ,
			@NotBlank(target="visitorId") ,
			@NotBlank(target="sourceType")
			}) VisitorInputDto visitorDto) {
		// TODO Auto-generated method stub
		visitorService.insertVisitor(dtoToEntity(visitorDto));
		
	}

	
	@LoggerProfile(methodNote = "查询店铺当日访客记录列表")
	@Override
	public PageModel<VisitorOutputDto> queryCurDayVisitorList(@NotBlank String shopId,Date date,PageModel pageModel) {
		// TODO Auto-generated method stub
		RowBounds rowBounds = RowBoundUtils.rowBounds(pageModel);
		
		int totalCount = visitorService.queryCurDayVisitCount(shopId,date);
		List<VisitorEntity> list = null;
		if(totalCount >0){
			list = visitorService.queryCurDayVisitorList(shopId, date,rowBounds);
			return new PageModel<VisitorOutputDto>(pageModel.getPage(),pageModel.getLimit(), totalCount,(List<VisitorOutputDto>) BeanUtil.convertBeanList(list,VisitorOutputDto.class));
			 
		}
		return null;
	}
	
	@LoggerProfile(methodNote = "查询访客各来源数量统计")
	@Override
	public VisitorCountOutputDto queryVisitorSourceCount(@NotBlank String shopId,Date date) {
		// TODO Auto-generated method stub
		return visitorService.queryVisitorSourceCount(shopId, date);
	}
	
	@LoggerProfile(methodNote = "查询店铺最近7天每天的访客数量")
	@Override
	public Map<String, Object> query7DaysVisitorCount(@NotBlank String shopId) {
		// TODO Auto-generated method stub
		return visitorService.query7DaysVisitorCount(shopId);
	}
	
	@LoggerProfile(methodNote = "查询店铺当天所有访客数量")
	@Override
	public int queryShopCurDayVisitCount(String shopId) {
		// TODO Auto-generated method stub
		return visitorService.queryShopCurDayVisitCount(shopId);
	}

}
