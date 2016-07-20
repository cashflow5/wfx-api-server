package com.yougou.wfx.utils;

import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import com.yougou.tools.common.utils.SpringContextHolder;
import com.yougou.wfx.framework.utils.WFXToolkit;
import com.yougou.wfx.system.service.WFXSystemService;
/**
 * 获取图片域名
 * @author zhang.wj
 *
 */
public class PicPathUtil {
	
	public static String WFX_PICS_CDN_URL1 = "http://w1.ygimg.cn/";
	
	public static String WFX_PICS_CDN_URL2 = "http://w2.ygimg.cn/";
	
	/**
	 * 获取商品图片CDN域名
	 * @return
	 */
	 public static String getPicServicePath() {
	        Random random = new Random();
	        if (random.nextInt(2) == 0) {
	            return "http://i1.ygimg.cn/pics";
	        } else {
	            return "http://i2.ygimg.cn/pics";
	        }
	 }
	 
	 /**
	 * 获取微分销图片CDN域名
	 * @return
	 */
	 public static String getWFXPicServicePath() {
	        Random random = new Random();
	        if (random.nextInt(2) == 0) {
	            return WFX_PICS_CDN_URL1;
	        } else {
	            return WFX_PICS_CDN_URL2;
	        }
	 }
	 
	 /**
	  * 获取图片访问的绝对路径
	  * @param imgPath 图片相对路径
	  * @return 图片绝对路径
	  */
	 public static String getImageAbsUrl(String imgPath){
		 if(StringUtils.isBlank(imgPath)){
			 return null;
		 }
		 WFXSystemService wfxSystemService = SpringContextHolder.getBean(WFXSystemService.class);
		 String devImageBaseUrl = wfxSystemService.obtainImgBaseUrl();
		 String imgAbsPath = null;
		 // 传入的路径已经是全路径直接返回
		if(imgPath.contains(WFX_PICS_CDN_URL1) || imgPath.contains(WFX_PICS_CDN_URL2) || imgPath.contains(devImageBaseUrl)){
			return imgPath;
		} 
		// 开发/ 测试环境 图片路径：服务器ip+图片路径
		if(WFXToolkit.isDev()){
			imgAbsPath = devImageBaseUrl + imgPath;
		// 生产环境	图片路径： cdn域名+图片路径
		}else {
			imgAbsPath = getWFXPicServicePath() + imgPath;
		}
		 return (imgAbsPath== null ? imgPath : imgAbsPath);
	 }
	 
	 /**
	  * 获取图片访问的相对路径
	  * @param imgPath 图片绝对路径
	  * @return 图片绝对路径
	  */
	 public static String getImageRelativeUrl(String imgPath){
		 if(StringUtils.isBlank(imgPath)){
			 return null;
		 }
		 
		 WFXSystemService wfxSystemService = SpringContextHolder.getBean(WFXSystemService.class);
		 String devImageBaseUrl = wfxSystemService.obtainImgBaseUrl();
		 if(imgPath.contains(WFX_PICS_CDN_URL1) || imgPath.contains(WFX_PICS_CDN_URL2) ){
			  return imgPath.substring(WFX_PICS_CDN_URL1.length());
		 }else if(imgPath.contains(devImageBaseUrl)) {
			 return imgPath.substring(devImageBaseUrl.length());
		 }
		
		 return imgPath;
	 }
	 
	/* public static void main(String[] args) {
		System.out.println(getImageAbsUrl("xxx"));
	}*/

}
