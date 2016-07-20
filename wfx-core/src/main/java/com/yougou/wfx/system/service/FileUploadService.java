package com.yougou.wfx.system.service;

import java.io.InputStream;

import com.yougou.wfx.common.enums.FtpConf;
import com.yougou.wfx.dto.base.WFXResult;

public interface FileUploadService {
	/**
	 * 上传分销包图片
	 * @param in
	 * @param bagId
	 * @param fileName
	 * @param fileType
	 * @return
	 */
	public WFXResult<String> uploadBagImg(InputStream in, String bagId, String fileName, int fileType);

	/**
	 * 上传图片
	 * @param in
	 * @param fileName
	 * @param ftpConf
	 * @return
	 */
	public WFXResult<String> uploadImg(InputStream in, String fileName,FtpConf ftpConf);
	
	/**
	 * 上传销售分类图片，并保存图片上传后的路径
	 * @param in 文件输入流
	 * @param saleCatId 销售分类id
	 * @param fileName 文件名称
	 * @param ftpConf ftp配置
	 * @return
	 */
	public WFXResult<String> uploadSaleCatImg(InputStream in, String saleCatId,String fileName,String userName,FtpConf ftpConf);
	
	public WFXResult<String> uploadArticle(InputStream in,String fileName,int fileType);
	
	public WFXResult<String> uploadCarImg(InputStream in,String fileName,FtpConf ftpConf);
	
	/**
	 * 上传品牌图片
	 * @param in
	 * @param brandId
	 * @param fileName
	 * @param fileType
	 * @return
	 */
	public WFXResult<String> uploadBrandImg(InputStream in, String fileName, int fileType);
	/**
	 * 清理无效的分销包图片(分销包大图、分销包小图)
	 * @return
	 */
	public WFXResult<String> clearupInvalidBagImg();
	/**
	 * 清理无效的品牌图片(品牌微图、品牌中图、品牌手机图、品牌大图、品牌小图)
	 * @return
	 */
	public WFXResult<String> clearupInvalidBrandImg();
	/**
	 * 清理无效的销售分类图片
	 * @return
	 */
	public WFXResult<String> clearupInvalidSaleCatImg();
	/**
	 * 清理无效的店铺相关图片(店铺LOGO图片、店招图片、店铺二维码)
	 * @return
	 */
	public WFXResult<String> clearupInvalidShopImg();
	/**
	 * 清理无效的用户相关图片(用户头像)
	 * @return
	 */
	public WFXResult<String> clearupInvalidUserImg();
	/**
	 * 清理无效的轮播图
	 * @return
	 */
	public WFXResult<String> clearupInvalidCarouselImg();
	/**
	 * 清理无效的分销商身份证图片(身份证图片、委托书图片)
	 * @return
	 */
	public WFXResult<String> clearupInvalidSellerImg();

	/**
	 * 上传APP  apk
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	public WFXResult<String> appApkUpload(String fileName, InputStream inputStream);

	/**
	 * 上传皮质图片
	 * @param fileName
	 * @param inputStream
	 * @return
	 */
	public WFXResult<String> uploadCortexImg(String fileName, InputStream inputStream);

	/**
	 * 上传二维码图片
	 * @param originalFilename
	 * @param inputStream
	 * @return
	 */
	public WFXResult<String> uploadQrCodeImg(String originalFilename, InputStream inputStream);
}
