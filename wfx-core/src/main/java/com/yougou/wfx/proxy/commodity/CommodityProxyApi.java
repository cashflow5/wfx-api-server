package com.yougou.wfx.proxy.commodity;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.oval.constraint.NotBlank;

import com.yougou.component.logistics.api.IMemberLogisticsApi;
import com.yougou.component.logistics.constant.LogisticsCompany;
import com.yougou.component.logistics.vo.MemberLogisticsVo;
import com.yougou.outside.api.IThirdPartyCommodityService;
import com.yougou.pc.api.ICommodityBaseApiService;
import com.yougou.pc.model.commodityinfo.Commodity;
import com.yougou.pc.model.picture.Picture;
import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.utils.CheckSensitiveWordsUtil;
import com.yougou.wfx.utils.LogisticsUtil;
public class CommodityProxyApi {
	@LoggerProfile(methodNote="检测敏感词")
	public static String checkSensitiveWord(String separator, @NotBlank String content){
		/*ICommodityMerchantApiService commodityApi = SpringContextHolder.getBean(ICommodityMerchantApiService.class);
		String result = commodityApi.checkSensitiveWord(content);
		return "SUCCESS".equalsIgnoreCase(result) ? "" : 
			(StringUtils.isNotBlank(separator) ? (result.replaceAll(",", separator)) : result);*/
		String result = CheckSensitiveWordsUtil.checkSensitiveWord(separator, content);
		return result;
	}
	
	@LoggerProfile(methodNote="获取物流信息")
	public static MemberLogisticsVo getMemberLogistics(String expressOrderId,String expressCode){
		
		IMemberLogisticsApi memberLogisticsApi = SpringContextHolder.getBean(IMemberLogisticsApi.class);
		
		LogisticsCompany logisticsCompany = LogisticsUtil.getLogisticsByCode(expressCode);
		
		MemberLogisticsVo memberLogisticsVo = memberLogisticsApi.getLogistics4wfx(logisticsCompany, expressOrderId);
		
		return memberLogisticsVo;
	}
	@LoggerProfile(methodNote="获取商品图片信息")
	public static Map<String, List<Picture>> getPicturesByCommodityNos(List<String> commodityNos){
		
		ICommodityBaseApiService commodityBaseApiService = SpringContextHolder.getBean(ICommodityBaseApiService.class);
		
		Map<String, List<Picture>>    pictureMap=commodityBaseApiService.getPicturesByCommodityNos(commodityNos);
		
		return pictureMap;
	}
	@LoggerProfile(methodNote="获取商品详情信息")
	public static Commodity getCommodityDetails(String commodityNo,boolean includeProduct,boolean includeStock ){
		
		ICommodityBaseApiService commodityBaseApiService = SpringContextHolder.getBean(ICommodityBaseApiService.class);
		Commodity   commodity=commodityBaseApiService.getCommodityByNo(commodityNo, includeProduct, includeStock, false);
		commodityBaseApiService.getCommodityDescByNo(commodityNo);
		
		return commodity;
	}
	@LoggerProfile(methodNote="商品描述")
	public static String getCommodityDescByNo(String commodityNo){
		ICommodityBaseApiService commodityBaseApiService = SpringContextHolder.getBean(ICommodityBaseApiService.class);
		String commodityDesc=commodityBaseApiService.getCommodityDescByNo(commodityNo);
		return commodityDesc;
	}
	@LoggerProfile(methodNote="同步商品")
	public static void synchronizationCommodity(String channelNo, String SellerNo,
			List<String> commodityNoList) throws Exception{
		IThirdPartyCommodityService thirdPartyCommodityService = SpringContextHolder.getBean(IThirdPartyCommodityService.class);
		Map<String, String>  map=thirdPartyCommodityService.saveAndSyncCommodity(channelNo, SellerNo, commodityNoList);
		
	}
	
}
