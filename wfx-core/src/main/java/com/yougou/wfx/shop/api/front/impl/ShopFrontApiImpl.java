package com.yougou.wfx.shop.api.front.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import net.sf.oval.constraint.NotBlank;
import net.sf.oval.constraint.NotNull;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.yougou.tools.common.utils.DateUtil;
import com.yougou.wfx.common.constant.Constant;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ImageFtpTypeEnum;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.framework.bean.BeanUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.ftp.service.FtpService;
import com.yougou.wfx.seller.model.SellerCatEntity;
import com.yougou.wfx.seller.service.ISellerCatService;
import com.yougou.wfx.shop.api.front.IShopFrontApi;
import com.yougou.wfx.shop.dto.input.ShopCatInputDto;
import com.yougou.wfx.shop.dto.input.ShopInputDto;
import com.yougou.wfx.shop.dto.output.ShopCatOutputDto;
import com.yougou.wfx.shop.dto.output.ShopOutputDto;
import com.yougou.wfx.shop.model.ShopEntity;
import com.yougou.wfx.shop.service.IShopService;
import com.yougou.wfx.system.api.IFileUploadApi;
import com.yougou.wfx.system.service.WFXSystemService;
import com.yougou.wfx.utils.MatrixToImageWriter;
import com.yougou.wfx.utils.PicPathUtil;

@Service
public class ShopFrontApiImpl implements IShopFrontApi {
	private Logger logger = LoggerFactory.getLogger(ShopFrontApiImpl.class);
	@Resource
	private FtpService ftpService;
	@Resource
	IShopService shopService;
	@Resource
	ISellerCatService sellerCatService;
	@Resource
	WFXSystemService wfxSystemService;
	@Resource
	IFileUploadApi fileUploadApi;
	
	
	private ShopEntity dtoToEntity(Object obj){
		return (ShopEntity) BeanUtil.convertBean(obj,ShopEntity.class);
	}
	
	// 组装店铺Logo,店招图片绝对路径
	private ShopOutputDto entityToDto(Object entity){
		if(entity!=null){
			ShopEntity obj = (ShopEntity)entity;
			String logoUrl = obj.getLogoUrl();
			String signUrl = obj.getSignUrl();
			String qrCodeUrl = obj.getQrCodeUrl();
			if(StringUtils.isEmpty(logoUrl)){
				obj.setLogoUrl( PicPathUtil.getImageAbsUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL) ); 
			}else{
				obj.setLogoUrl(PicPathUtil.getImageAbsUrl(logoUrl));
			}
			
			if( StringUtils.isBlank(signUrl) ){
				obj.setSignUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
			}else{
				obj.setSignUrl(PicPathUtil.getImageAbsUrl(signUrl));
			}
			if( StringUtils.isNotBlank(qrCodeUrl) ){
				obj.setQrCodeUrl(PicPathUtil.getImageAbsUrl(qrCodeUrl));
			}
			return (ShopOutputDto) BeanUtil.convertBean(obj,ShopOutputDto.class);
		}
		return null;
		
	}

	@Override
	@LoggerProfile(methodNote="根据店铺id查询店铺信息")
	public ShopOutputDto getShopById(@NotBlank String id, boolean isSeller) {
		// TODO Auto-generated method stub
		ShopEntity shopEntity = shopService.getById(id);
//		// 组装店铺Logo,店招图片绝对路径
//		if(null != shopEntity){
//			String absSignUrl = StringUtils.isNotBlank(shopEntity.getSignUrl()) ? 
//									PicPathUtil.getImageAbsUrl(shopEntity.getSignUrl()) : PicPathUtil.getImageAbsUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
//			String absLogoUrl =  StringUtils.isNotBlank(shopEntity.getLogoUrl()) ? 
//									PicPathUtil.getImageAbsUrl(shopEntity.getLogoUrl()) : PicPathUtil.getImageAbsUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL);
//			shopEntity.setSignUrl(absSignUrl );
//			shopEntity.setLogoUrl(absLogoUrl);
//		}
		return this.entityToDto(shopEntity);
	}
	
	@Override
	@LoggerProfile(methodNote="根据分销商id查询店铺信息")
	public ShopOutputDto getShopBySeller(@NotBlank String sellerId) {
		ShopEntity shopEntity = shopService.getShopBySeller( sellerId );
//		// 组装店铺Logo,店招图片绝对路径
//		if(null != shopEntity){
//			String absSignUrl = StringUtils.isNotBlank(shopEntity.getSignUrl()) ? 
//					PicPathUtil.getImageAbsUrl(shopEntity.getSignUrl()) : PicPathUtil.getImageAbsUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
//			String absLogoUrl =  StringUtils.isNotBlank(shopEntity.getLogoUrl()) ? 
//							PicPathUtil.getImageAbsUrl(shopEntity.getLogoUrl()) : PicPathUtil.getImageAbsUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL);
//			shopEntity.setSignUrl(absSignUrl );
//			shopEntity.setLogoUrl(absLogoUrl);
//		}
		return this.entityToDto(shopEntity);
	}

	@Override
	@LoggerProfile(methodNote="设置店铺公告")
	public WFXResult<Boolean> updateNoticeByShopId(@NotBlank String id, @NotBlank String notice) {
		 WFXResult<Boolean> rs = new  WFXResult<Boolean>();
		ShopEntity shopEntity = new ShopEntity();
		shopEntity.setId(id);
		shopEntity.setNotice(notice);
		shopEntity.setUpdateTime( new Date() );
		int rowNum = shopService.update(shopEntity);
		if(rowNum>0){
			rs.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			rs.setResult(Boolean.TRUE);
		}else{
			rs.setResultCode( ResultCodeEnum.FAILURE.getKey() );
			rs.setResult(Boolean.FALSE);
			rs.setResultMsg("未找到该店铺记录,店铺公告更新失败！");
		}
		return rs;
	}

	@Override
	@LoggerProfile(methodNote="根据店铺id查询分类列表")
	public List<ShopCatOutputDto> getShopCatByShopId(@NotBlank String shopId) {
		if(StringUtils.isBlank(shopId)){
			logger.error("调用getShopCatByShopId接口出错，原因：传入的" + shopId + "为空");
			throw new RuntimeException("shopId不能为空");
		}
		logger.error("参数shopId:" + shopId);
		ShopCatInputDto inputDto = new ShopCatInputDto();
		inputDto.setSellerId(shopId);
		inputDto.setLevel(1);
		List<ShopCatOutputDto> dtoList = null;
		//获取1级分类
		List<SellerCatEntity> entityList = sellerCatService.queryList((SellerCatEntity)BeanUtil.convertBean(inputDto, SellerCatEntity.class));
		//获取二级销售分类
		if(entityList != null && entityList.size() > 0){
			dtoList = this.catEntityToDto(entityList);
			for(int i=0;i<entityList.size();i++){
				List<SellerCatEntity> childrenList = sellerCatService.queryChildren(entityList.get(i).getId());
				List<ShopCatOutputDto> childrenOutList = this.catEntityToDto(childrenList);
				dtoList.get(i).setChilds(new HashSet<ShopCatOutputDto>(childrenOutList));
			}
		}
		return dtoList;
	}

	@Override
	@LoggerProfile(methodNote="根据店铺名称校验是否重复")
	public WFXResult<Boolean> checkShopName(@NotBlank String shopName) {
		// TODO Auto-generated method stub
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		result.setResult(true);
		int counts = shopService.getCountsByShopName(shopName);
		if(counts > 0){
			result.setResult(false);
		}
		return result;
	}

	@Override
	@LoggerProfile(methodNote="校验是否含有敏感词")
	public WFXResult<String> checkSensitiveWord(@NotBlank String separator, @NotBlank String content) {
		// TODO Auto-generated method stub
		WFXResult<String> result = new WFXResult<String>();
		String sensitiveWord = wfxSystemService.checkSensitiveWord(separator, content);
		result.setResult(sensitiveWord);
		return result;
	}

	@Override
	@LoggerProfile(methodNote="设置店招")
	public WFXResult<String> updateSign(@NotBlank String id, @NotBlank String fileName, @NotNull InputStream in) {
		WFXResult<String> ftpResult = null;
		try {
			ftpResult = fileUploadApi.frontImageUpload( fileName, ImageFtpTypeEnum.SHOP_SIGN_IMG ,in);
		} catch (Exception e) {
			logger.error("店招上传失败！异常信息：",e);
			ftpResult = new WFXResult<String>();
			ftpResult.setResultCode(ResultCodeEnum.FAILURE.getKey() );
			ftpResult.setResultMsg("店招图片上传发生异常！");
			return ftpResult;
		}
		if( ftpResult!=null && ftpResult.getResultCode() == 0 ){//上传成功
			String picURL = ftpResult.getResult();
			ShopEntity shopEntity = new ShopEntity();
			shopEntity.setId(id);
			// 图片上传后返回图片服务器相对路径
//			shopEntity.setSignUrl(wfxSystemService.obtainImgBaseUrl() + picURL);
			shopEntity.setSignUrl( picURL);
			shopEntity.setUpdateTime( new Date() );
			int rowNum = shopService.update(shopEntity);
			if(rowNum>0){
				ftpResult.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				return ftpResult;
			}else{
				ftpResult.setResultCode( ResultCodeEnum.FAILURE.getKey() );
				ftpResult.setResultMsg("店招图片上传成功后更新到数据库的店招URL失败！");
			}
		}
		return ftpResult;
	}
	
	@Override
	@LoggerProfile(methodNote="设置店铺LOGO")
	public WFXResult<String> updateLogo(@NotBlank String id, @NotBlank String fileName, @NotNull InputStream in) {
		WFXResult<String> ftpResult = null;
		try {
			ftpResult = fileUploadApi.frontImageUpload( fileName, ImageFtpTypeEnum.SHOP_LOGO_IMG ,in);
		} catch (Exception e) {
			logger.error("店铺LOGO上传失败！异常信息：",e);
			ftpResult = new WFXResult<String>();
			ftpResult.setResultCode(ResultCodeEnum.FAILURE.getKey() );
			ftpResult.setResultMsg("店铺LOGO图片上传发生异常！");
			return ftpResult;
		}
		if( ftpResult!=null && ftpResult.getResultCode() == 0 ){//上传成功
			String picURL = ftpResult.getResult();
			ShopEntity shopEntity = new ShopEntity();
			shopEntity.setId(id);
			// 图片上传后返回图片服务器绝对路径
			shopEntity.setLogoUrl(picURL);
//			shopEntity.setLogoUrl(wfxSystemService.obtainImgBaseUrl() +picURL);
			shopEntity.setUpdateTime( new Date() );
			int rowNum = shopService.update(shopEntity);
			if(rowNum>0){
				ftpResult.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				return ftpResult;
			}else{
				ftpResult.setResultCode( ResultCodeEnum.FAILURE.getKey() );
				ftpResult.setResultMsg("店铺LOGO上传成功后更新到数据库的店招URL失败！");
			}
		}
		return ftpResult;
	}

	@Override
	@LoggerProfile(methodNote="设置店铺信息")
	public WFXResult<Boolean> updateShop(@NotBlank.List(value = { @NotBlank(target="sellerId")})ShopInputDto shopInputDto) {
		WFXResult<Boolean> result = new WFXResult<Boolean>();
		shopInputDto.setUpdateTime( new Date() );
		ShopEntity shopEntity = (ShopEntity)BeanUtil.convertBean(shopInputDto,ShopEntity.class);
		// 设置默认店铺LOGO
		if(StringUtils.isBlank(shopEntity.getLogoUrl())){
			shopEntity.setLogoUrl(Constant.WFX_SHOP_LOGO_DEFAULT_PIC_URL);
		}
		//  设置默认店招
		if(StringUtils.isBlank(shopEntity.getSignUrl())){
			shopEntity.setSignUrl(Constant.WFX_SHOP_SIGN_DEFAULT_PIC_URL);
		}
		int rowNum = shopService.update(shopEntity);
		if(rowNum>0){
			result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			result.setResult(Boolean.TRUE);
		}else{
			result.setResultCode( ResultCodeEnum.FAILURE.getKey() );
			result.setResult(Boolean.FALSE);
			result.setResultMsg("更新店铺信息失败！");
		}
		return result;
	}
	
	private List<ShopCatOutputDto> catEntityToDto(List<SellerCatEntity> entityList){
		List<ShopCatOutputDto> dtoList = null;
		dtoList = (List<ShopCatOutputDto>)BeanUtil.convertBeanList(entityList, ShopCatOutputDto.class);
		return dtoList;
	}
	
	@LoggerProfile(methodNote="店铺访次递增接口")
	@Override
	public int addShopVisitCount(@NotBlank String shopId) {
		// TODO Auto-generated method stub
		String key = DateUtil.formatDate(new Date(), "yyyyMMdd");
		key = key + shopId;
		int visitCount = WFXRedisUtil.getInt(WFXModuleType.SHOPVISIT, key);
		visitCount ++;
		WFXRedisUtil.set(WFXModuleType.SHOPVISIT, key, visitCount, 2, TimeUnit.DAYS);
		return visitCount;
	}
	
	@LoggerProfile(methodNote="店铺访次查询接口")
	@Override
	public int getShopVisitCountById(@NotBlank String shopId) {
		// TODO Auto-generated method stub
		String key = DateUtil.formatDate(new Date(), "yyyyMMdd");
		key = key + shopId;
		int visitCount = WFXRedisUtil.getInt(WFXModuleType.SHOPVISIT, key);
		return visitCount;
	}

	@LoggerProfile(methodNote="查询店铺默认图片")
	@Override
	public Map<String, String> getShopDefaultImagesUrl(){
		Map<String, String> imageUrlMap = new HashMap<String, String>();
		String headPath = wfxSystemService.obtainImgBaseUrl();
		imageUrlMap.put("shop_sign", headPath+"SHOP_SIGN/default_sign.png");
		imageUrlMap.put("shop_logo", headPath+"SHOP_LOGO/default_logo.png");
		imageUrlMap.put("member_logo", headPath+"MEMBER_LOGO/default_logo.png");
		return imageUrlMap;
	}
	@LoggerProfile(methodNote="根据手机获取店铺")
	@Override
	public ShopOutputDto getShopByPhoneNumber(@NotBlank String loginName) {
		ShopOutputDto dto = this.entityToDto(shopService.getShopByPhoneNumber(loginName));
		return dto;
	}

	@Override
	public ShopOutputDto getShopByMemberId(String memberId) {
		ShopOutputDto dto = this.entityToDto(shopService.getShopByMemberId(memberId));
		return dto;
	}
	
	@Override
	@LoggerProfile(methodNote="生成优粉的二维码并上传到服务器")
	public WFXResult<String> generateQrCode(@NotBlank String wxCodeUrl, 
		@NotBlank.List(value = {@NotBlank(target="Id"),
								@NotBlank(target="logoUrl"), 
								@NotBlank(target="shopCode")})ShopOutputDto shopOutputDto) {
		// step1 生成图片
		String codeFilePath = wfxSystemService.getSystemConfig( Constant.WFX_PIC_TEMP_PATH );
//		if(StringUtils.isEmpty( codeFilePath )){
//			codeFilePath = "D:/data/tempPic/";
//		}
	    String fileName = System.currentTimeMillis()+".png";
		MatrixToImageWriter.drawWholeToFile(shopOutputDto, wxCodeUrl, 500, 810, codeFilePath+fileName);
		// step2 上传
		WFXResult<String> ftpResult = null;
		InputStream in = null;
		try {
			in = new FileInputStream( new File(codeFilePath+fileName));
		
			ftpResult = fileUploadApi.frontImageUpload( fileName, ImageFtpTypeEnum.SHOP_QR_CODE_IMG ,in);
		} catch (Exception e) {
			logger.error("二维码上传失败！异常信息：",e);
			ftpResult = new WFXResult<String>();
			ftpResult.setResultCode(ResultCodeEnum.FAILURE.getKey() );
			ftpResult.setResultMsg("二维码图片上传发生异常！");
			return ftpResult;
		}finally{
			try {
				in.close();
				in = null;
			} catch (IOException e) {
				logger.error("二维码上传关闭输入流错误：",e);
			}
		}
		// step3 保存到DB
		if( ftpResult!=null && ftpResult.getResultCode() == 0 ){//上传成功
			String picURL = ftpResult.getResult();
			ShopEntity shopEntity = new ShopEntity();
			shopEntity.setId(shopOutputDto.getId());
			// 图片上传后返回图片服务器??路径
			shopEntity.setQrCodeUrl(picURL);
			shopEntity.setUpdateTime( new Date() );
			int rowNum = shopService.update(shopEntity);
			if(rowNum==0){
				ftpResult.setResultMsg("二维码上传成功后更新到数据库失败！");
			}
			
			ftpResult.setResult( PicPathUtil.getImageAbsUrl(picURL) );
			ftpResult.setResultCode(ResultCodeEnum.SUCCESS.getKey());
			
		}
		return ftpResult;
	}

}
