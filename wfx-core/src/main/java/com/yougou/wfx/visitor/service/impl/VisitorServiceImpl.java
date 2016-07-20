package com.yougou.wfx.visitor.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.framework.base.BaseService;
import com.yougou.wfx.framework.utils.UUIDGenerator;
import com.yougou.wfx.utils.PicPathUtil;
import com.yougou.wfx.visitor.dao.IVisitorMapper;
import com.yougou.wfx.visitor.dto.output.VisitorCountOutputDto;
import com.yougou.wfx.visitor.dto.output.VisitorOutputDto;
import com.yougou.wfx.visitor.model.VisitorEntity;
import com.yougou.wfx.visitor.service.IVisitorService;

@Service
public class VisitorServiceImpl extends BaseService implements IVisitorService {
	
	@Resource
	private IVisitorMapper visitorMapper;
	
	@Override
	public void insertVisitor(VisitorEntity visitorEntity) {
		// TODO Auto-generated method stub
		String shopId = visitorEntity.getShopId();
		String visitorId = visitorEntity.getVisitorId();
		//判断当前访客和店铺是否已经记录访问记录
		int visitCount = visitorMapper.queryVisitorCurDayVisitCount(shopId, visitorId);
		if(visitCount <=0){
			visitorEntity.setId(UUIDGenerator.get32LowCaseUUID());
			visitorEntity.setVisitTime(new Date());
			visitorMapper.insertVisitor(visitorEntity);
		}else {
			logger.info("用户：{}访问店铺：{}，已经记录访问记录，不在记录访客",visitorId, shopId );
		}
	}

	@Override
	public List<VisitorEntity> queryCurDayVisitorList(String shopId,Date date, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		List<VisitorEntity> dtoList = visitorMapper.queryCurDayVisitorList(shopId,date, rowBounds);
		//处理头像图片链接
		for(VisitorEntity dto : dtoList){
			String headShowImg = dto.getHeadShowImg();
			if(StringUtils.isEmpty(headShowImg)){
				dto.setHeadShowImg( PicPathUtil.getImageAbsUrl(Constant.WFX_MEMBER_LOGO_DEFAULT_PIC_URL) ); 
			}else if( headShowImg.startsWith( Constant.WX_IMG_PREFIX ) ){
				dto.setHeadShowImg( headShowImg.replace(Constant.WX_IMG_PREFIX, "") );
			}else{
				dto.setHeadShowImg(PicPathUtil.getImageAbsUrl(headShowImg));
			}
		}
		return dtoList;
	}

	@Override
	public int queryCurDayVisitCount(String shopId,Date date) {
		// TODO Auto-generated method stub
		return visitorMapper.queryCurDayVisitCount(shopId,date);
	}

	@Override
	public VisitorCountOutputDto queryVisitorSourceCount(String shopId,Date date) {
		// TODO Auto-generated method stub
		return visitorMapper.queryVisitorSourceCount(shopId,date);
	}

	@Override
	public Map<String, Object> query7DaysVisitorCount(String shopId) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		//获取最近7天店铺每天的访客统计
		List<Map<String,Object>> list = visitorMapper.query7DaysVisitorCount(shopId);
		//Date last7Days[]=new Date[7];
		String last7DaysStr[] = new String[7];
		Date now = new Date();
		// 封装数据以便C端曲线图展示[{name:06月12日,y:123},.....]
		for(int i=0;i<last7DaysStr.length;i++){
			//封装最近7天日期到日期数组
			Date date = DateUtil.addDate(now, -(last7DaysStr.length-i-1));
			String cnDateStr = DateUtil.format(date,"MM月dd日");
			//last7Days[i]=date;
			if(i==0 || i == last7DaysStr.length-1){
				last7DaysStr[i]=cnDateStr;
			}else{
				last7DaysStr[i]= "";
			}
			//定义曲线图数据封装map
			Map<String,Object> resultMap = new HashMap<String,Object>();			
			String dateStr = DateUtil.formatDate(date, "yyyy-MM-dd");
			resultMap.put("name", cnDateStr);
			resultMap.put("date",dateStr);
			boolean flag = false;
			//从查询的最近7天中匹配相应日期的访客数量封装至map 中
			for(Map<String,Object> map : list){
				String visitTime = (String) map.get("visitTime");//访问时间
				int visitorCount = (Integer) map.get("visitorCount"); //访客数量
				if(dateStr.equals(visitTime)){
					resultMap.put("y", visitorCount);
					flag =true;
					break;
				}
			}
			if(!flag){
				resultMap.put("y", 0);
			}
			resultList.add(resultMap);
		}
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("categories", last7DaysStr);
		result.put("data",resultList );
		return result;
	}
	
	public static void main(String[] args) {
		Date last7Days[]=new Date[7];
		Date now = new Date();
		for(int i=0;i<last7Days.length;i++){
			last7Days[i]=DateUtil.addDate(now, -(last7Days.length-i-1));
		}
		for(int i=0;i<last7Days.length;i++){
			Date date = last7Days[i];
			System.out.println(DateUtil.format(date,"MM月dd日"));
		}
		
	}

	@Override
	public int queryShopCurDayVisitCount(String shopId) {
		// TODO Auto-generated method stub
		return visitorMapper.queryShopCurDayVisitCount(shopId);
	}

}
