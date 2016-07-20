package com.yougou.wfx.framework.ftp.service.impl;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.MessageChannel;
import org.springframework.stereotype.Service;

import com.yougou.wfx.common.enums.FtpConf;
import com.yougou.wfx.dto.base.WFXResult;
import com.yougou.wfx.enums.ResultCodeEnum;
import com.yougou.wfx.framework.ftp.service.FtpService;
import com.yougou.wfx.framework.utils.WFXToolkit;
import com.yougou.wfx.utils.FTPUtil;
import com.yougou.wfx.utils.IdGen;
import com.yougou.wfx.utils.InputStreamCacher;

@Service
public class FtpServiceImpl implements FtpService {
	
	private Logger logger = LoggerFactory.getLogger(FtpServiceImpl.class);
	
	@Autowired
	private MessageChannel ftpUploadChannel;
	@Autowired
	private MessageChannel ftpRemoveChannel;
	
	public WFXResult<String> uploadFile(InputStream in, FtpConf ftpConf, String name) {
		WFXResult<String> result = new WFXResult<String>();
		try {
			if (in == null || StringUtils.isBlank(name)) {
				result.setResultCode(1);
				result.setResultMsg("请选择合适的图片");
				return result;
			}
			if (in.available() > ftpConf.getMaxSize()) {
				result.setResultCode(2);
				int sizeM = ftpConf.getMaxSize()/1024/1024;
				result.setResultMsg("图片不能超过"+sizeM+"M");
				return result;
			}
			if(ftpConf.getHeight() > 0 && ftpConf.getWidth() > 0){
				//缓存图片输入流
				InputStreamCacher inputStreamCacher = new InputStreamCacher(in);
				BufferedImage image = ImageIO.read(inputStreamCacher.getInputStream());
				if(ftpConf.name().equals(FtpConf.SELLER_AUTHORIZE.name()) 
						|| ftpConf.name().equals(FtpConf.SELLER_IDENTITY.name())){
					if(image.getWidth() < ftpConf.getWidth() || image.getHeight() < ftpConf.getHeight()){
						result.setResultCode(6);
						result.setResultMsg("图片尺寸必须:宽度>="+ftpConf.getWidth()+"像素,高度>="+ftpConf.getHeight()+"像素");
						return result;
					} 
				}else if(ftpConf.name().equals(FtpConf.ARTICLE_EDIT_PIC.name())){
					if(image.getWidth() > ftpConf.getWidth() || image.getHeight() > ftpConf.getHeight()){
						result.setResultCode(6);
						result.setResultMsg("图片的宽度不能超过"+ftpConf.getWidth()+"px，高度不能超过"+ftpConf.getHeight()+"px。");
						return result;
					} 
				}else{
					if(image.getWidth() != ftpConf.getWidth() || image.getHeight() != ftpConf.getHeight()){
						result.setResultCode(6);
						result.setResultMsg("图片尺寸必须是"+ftpConf.getWidth()+"*"+ftpConf.getHeight());
						return result;
					} 
				}
				//从缓存的图片输入流中复制流，关闭缓存流
				in = inputStreamCacher.getInputStream();
				inputStreamCacher.close();
			}
		} catch (IOException e) {
			result.setResultCode(3);
			result.setResultMsg("图片检验失败，请检查文件");
			logger.error("上传图片出错，错误代码3",e);
			return result;
		}
		
		String folderName = ftpConf.getPath();
		String path = folderName;
		String uuid = IdGen.uuid().toUpperCase();
		String extend = FilenameUtils.getExtension(name);// 文件扩展名
		String originalName = uuid + "." + extend;
		try {
			boolean uploadOriginal = FTPUtil.ftpUpload(ftpUploadChannel,in, originalName, path);
			if (!uploadOriginal) {
				result.setResultCode(4);
				result.setResultMsg("上传图片失败，ftp服务器错误");
				return result;
			}
			String picUrl = path + "/" + originalName;
			result.setResultCode(0);
			result.setResult(picUrl);
			return result;

		} catch (Exception e) {
			logger.error("上传图片出错",e);
		}
		result.setResultCode(5);
		result.setResultMsg("上传图片失败:系统发生未知错误，请联系支持组");
		return result;
	}
	
	@Override
	public WFXResult<String> deleteFile(String fileName, String ftpServerPath) {
		WFXResult<String> result = new WFXResult<String>();
		String picUrl = ftpServerPath + "/" + fileName;
		try {
			boolean flag = FTPUtil.ftpDeleteload(ftpRemoveChannel, fileName, ftpServerPath);
			if(flag){
				result.setResultCode(ResultCodeEnum.SUCCESS.getKey());
				result.setResult("success");
				result.setResultMsg("文件【"+picUrl+"】删除成功！");
			}else{
				result.setResultCode(ResultCodeEnum.WARN.getKey());
				result.setResult("warn");
				result.setResultMsg("文件【"+picUrl+"】删除失败！");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			result.setResultCode(ResultCodeEnum.FAILURE.getKey());
			result.setResult("error");
			result.setResultMsg("文件【"+picUrl+"】删除时,程序发生错误！");
			logger.error("删除ftp文件发生错误：",e);
		}
		return result;
	}

	@Override
	public WFXResult<String> obtainImgBaseUrl() {
		WFXResult<String> result = new WFXResult<String>();
		result.setResult(WFXToolkit.imgBaseUrl());
		result.setResultCode(1);
		return result;
	}
}
