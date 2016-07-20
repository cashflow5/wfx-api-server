package com.yougou.wfx.framework.ftp.service;

import java.io.InputStream;

import com.yougou.wfx.common.enums.FtpConf;
import com.yougou.wfx.dto.base.WFXResult;

/**
 * Ftp上传Service
 * @author zhang.hc
 *
 */
public interface FtpService {
	/**
	 * ftp上传图片底层
	 * @param in
	 * @param uploadDir
	 * @param name
	 * @return
	 */
	public WFXResult<String> uploadFile(InputStream in, FtpConf ftpConf, String name);
	
	/**
	 * ftp删除资源
	 * @param fileName
	 * @param ftpServerPath
	 * @return
	 */
	public WFXResult<String> deleteFile(String fileName, String ftpServerPath);
	
	public WFXResult<String> obtainImgBaseUrl();
}
