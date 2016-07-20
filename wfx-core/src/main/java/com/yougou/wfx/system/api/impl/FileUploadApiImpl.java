package com.yougou.wfx.system.api.impl;

import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yougou.wfx.common.enums.FtpConf;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ImageFtpTypeEnum;
import com.yougou.wfx.framework.annotation.log.LoggerProfile;
import com.yougou.wfx.system.api.IFileUploadApi;
import com.yougou.wfx.system.service.FileUploadService;

@Service("fileUploadApi")
public class FileUploadApiImpl implements IFileUploadApi {
	@Resource
	private FileUploadService fileUploadService;
	
	@LoggerProfile(methodNote = "上传分销包图片接口")
	@Override
	public WFXResult<String> uploadBagImg(String bagId, String fileName, int fileType, InputStream in) {
		return fileUploadService.uploadBagImg(in, bagId, fileName, fileType);
	}
	
	@LoggerProfile(methodNote = "上传轮播图图片接口")
	@Override
	public WFXResult<String> uploadCarouselFigureImg(String carouselFigureId, String fileName, InputStream in) {
		// TODO Auto-generated method stub
		
		return fileUploadService.uploadImg(in, fileName, FtpConf.CAROUSEL_PIC);
		//return null;
	}
	
	@LoggerProfile(methodNote = "上传销售分类图片接口")
	@Override
	public WFXResult<String> uploadSaleCatImg(String saleCatId, String fileName,String userName, InputStream in) {
		return fileUploadService.uploadSaleCatImg(in, saleCatId, fileName,userName, FtpConf.SALECAT_PIC);
	}
	
	@LoggerProfile(methodNote = "上传文章封面图片接口")
	@Override
	public WFXResult<String> uploadArticle(String fileName,int fileType,InputStream in) {
		return fileUploadService.uploadArticle(in,fileName,fileType);
	}

	@LoggerProfile(methodNote = "上传发现功能轮播图图片接口")
	@Override
	public WFXResult<String> uploadCarImg(String fileName,InputStream in) {
		return fileUploadService.uploadCarImg(in,fileName,FtpConf.DISCOVER_CAR_PIC);
	}
	
	@LoggerProfile(methodNote = "上传品牌图片接口")
	@Override
	public WFXResult<String> uploadBrandImg(Integer imageType, String fileName, InputStream in) {
		return fileUploadService.uploadBrandImg(in, fileName, imageType);
	}
	
	@LoggerProfile(methodNote = "B/C端图片上传接口")
	@Override
	public WFXResult<String> frontImageUpload(String fileName, ImageFtpTypeEnum imageFtpType,InputStream inputStream) throws Exception {
		// TODO Auto-generated method stub
		FtpConf ftpConf = null;
		for(FtpConf ftp : FtpConf.values()){
			if(imageFtpType.getKey().equals(ftp.name())){
				ftpConf = ftp;
				break;
			}
		}
		
		return fileUploadService.uploadImg(inputStream, fileName,ftpConf);
	}
	
	@LoggerProfile(methodNote = "清理无效ftp文件")
	@Override
	public void clearupInvalidFtpFiles() {
		// TODO Auto-generated method stub
		fileUploadService.clearupInvalidBagImg();
		fileUploadService.clearupInvalidBrandImg();
		fileUploadService.clearupInvalidSaleCatImg();
		fileUploadService.clearupInvalidCarouselImg();
		fileUploadService.clearupInvalidSellerImg();
		fileUploadService.clearupInvalidShopImg();
		fileUploadService.clearupInvalidUserImg();
	}
	
	public static void main(String[] args) {
		
		/*String key = ImageFtpTypeEnum.SHOP_LOGO_IMG.getKey();
		String shoplogo = FtpConf.SHOP_LOGO.name();
		System.out.println(key+"-----"+shoplogo);*/
		
		FtpConf ftpConf = null;
		ImageFtpTypeEnum imageFtpType = ImageFtpTypeEnum.SHOP_SIGN_IMG;
		for(FtpConf ftp : FtpConf.values()){
			if(imageFtpType.getKey().equals(ftp.name())){
				ftpConf = ftp;
				break;
			}
		}
		System.out.println(ftpConf.getPath());
	}

	@Override
	public WFXResult<String> appApkUpload(String fileName, InputStream inputStream) throws Exception {
		return fileUploadService.appApkUpload(fileName,inputStream);
	}

	@Override
	public WFXResult<String> uploadCortexImg(String fileName, InputStream inputStream) {
		return fileUploadService.uploadCortexImg(fileName,inputStream);
	}

	@Override
	public WFXResult<String> uploadQrCodeImg(String originalFilename,
			InputStream inputStream) {
		return fileUploadService.uploadQrCodeImg(originalFilename, inputStream);
	}
}
