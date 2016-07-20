package com.yougou.wfx.system.api;

import java.io.InputStream;

import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ImageFtpTypeEnum;

/**
 * 文件上传API
 * @author zhang.hc
 *
 */
public interface IFileUploadApi {
	/**
	 * 分销包图片上传(后台使用)
	 * @param in 图片输入流
	 * @param bagId 分销包Id,如果分销包id为空的话，默认为新创建的分销包.不包含绑定数据表的功能
	 * @param fileName 文件名称
	 * @param fileType 1=分销包小图，2=分销包大图。else=不做任何处理
	 * @return
	 */
	public WFXResult<String> uploadBagImg(String bagId, String fileName, int fileType, InputStream in);
	
	/**
	 * 轮播图图片上传(后台使用)
	 * @param in 图片输入流
	 * @param carouselFigureId 轮播图Id,如果轮播图id为空的话，默认为新创建的轮播图.不包含绑定数据表的功能
	 * @param fileName 文件名称
	 * @return
	 */
	public WFXResult<String> uploadCarouselFigureImg(String carouselFigureId, String fileName,InputStream in);
	
	/**
	 * 销售分类图片上传（后台使用）
	 * @param saleCatId 销售分类id
	 * @param fileName 文件名称
	 * @param in 文件输入流
	 * @return 上传结果，包括上传的路径
	 */
	public WFXResult<String> uploadSaleCatImg(String saleCatId, String fileName,String userName, InputStream in);
	
	/**
	 * 上传文章封面接口
	 * @param in
	 * @return
	 */
	public WFXResult<String> uploadArticle(String fileNmae,int fileType,InputStream in);
	
	/**
	 * 上传发现功能轮播图图片接口
	 * @param in
	 * @return
	 */
	public WFXResult<String> uploadCarImg(String fileName,InputStream in);
	
	/**
	 * 上传品牌图片
	 * @param brandId 品牌ID
	 * @param imageType 图片类型
	 * @param fileName 文件名称
	 * @param in 文件输入流
	 * @return
	 */
	public WFXResult<String> uploadBrandImg(Integer imageType, String fileName, InputStream in);
	
	/**
	 * B/C 端图片上传
	 * @param inputStream 输入流
	 * @param fileName 文件名称
	 * @param imageFtpType 上传图片类型(头像、店招、店铺LOGO、店铺二维码)
	 * @return WFXResult{resultCode:0-6(0:图片上传成功，1-5图片上传失败),result:图片服务器地址，resultMsg:失败提示}
	 * @throws Exception
	 */
	WFXResult<String> frontImageUpload(String fileName, ImageFtpTypeEnum imageFtpType,InputStream inputStream)  throws Exception;
	
	
	/**
	 * 上传APP apk
	 * @param fileName 文件名称
	 * @param inputStream 输入流
	 * @return
	 * @throws Exception
	 */
	WFXResult<String> appApkUpload(String fileName, InputStream inputStream)  throws Exception;
	
	
	/**
	 * 清理无效ftp文件
	 * @return
	 */
	public void clearupInvalidFtpFiles();

	/**
	 * 上传皮质文章图片
	 * @param originalFilename
	 * @param inputStream
	 * @return
	 */
	public WFXResult<String> uploadCortexImg(String originalFilename, InputStream inputStream);
	/**
	 * 上传二维码图片
	 * @param originalFilename
	 * @param inputStream
	 * @return
	 */
	public WFXResult<String> uploadQrCodeImg(String originalFilename, InputStream inputStream);
}
