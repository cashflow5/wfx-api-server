package com.yougou.wfx.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.file.remote.session.Session;
import org.springframework.integration.support.MessageBuilder;

public class FTPUtil {

	private final static Logger logger = LoggerFactory.getLogger(FTPUtil.class);
	
	/**
	 * 图片上传封装类,上传图片名称使用imgfile名称
	 * @param ftpChannel
	 * @param imgfile
	 * @param ftpServerPath
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean ftpUpload(MessageChannel ftpChannel,File imgfile, String ftpServerPath) throws Exception{
		if (StringUtils.isNotBlank(ftpServerPath)
				&& ftpServerPath.startsWith("/")) {
			ftpServerPath = StringUtils.substringAfter(ftpServerPath, "/");
		}
		Message<File> message = MessageBuilder.withPayload(imgfile)
				.setHeader("remote_dir", new String(ftpServerPath.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
				.setHeader("remote_filename", new String(imgfile.getName().getBytes(Charset.forName("UTF-8")),"ISO-8859-1")).build();
		return ftpChannel.send(message);
	}
	
	/**
	 * 图片上传封装类,上传图片名称使用ftpfilename名称(重命名)
	 * @param ftpChannel
	 * @param imgfile
	 * @param ftpfilename
	 * @param ftpServerPath
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean ftpUpload(MessageChannel ftpChannel,File imgfile,String ftpfilename, String ftpServerPath) throws Exception {
		if (StringUtils.isNotBlank(ftpServerPath)
				&& ftpServerPath.startsWith("/")) {
			ftpServerPath = StringUtils.substringAfter(ftpServerPath, "/");
		}
		Message<File> message = MessageBuilder.withPayload(imgfile)
				.setHeader("remote_dir", new String(ftpServerPath.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
				.setHeader("remote_filename", new String(ftpfilename.getBytes(Charset.forName("UTF-8")),"ISO-8859-1")).build();
		return ftpChannel.send(message);
	}
	
	/**
	 * 图片上传封装类,上传图片名称使用ftpfilename名称(重命名)
	 * @param ftpChannel
	 * @param fileInputStream
	 * @param ftpfilename
	 * @param ftpServerPath
	 * @return
	 * @throws Exception
	 */
	public static boolean ftpUpload(MessageChannel ftpChannel,InputStream fileInputStream,String ftpfilename, String ftpServerPath) throws Exception {
		if (StringUtils.isNotBlank(ftpServerPath)
				&& ftpServerPath.startsWith("/")) {
			ftpServerPath = StringUtils.substringAfter(ftpServerPath, "/");
		}
		Message<byte[]> message=null;
		try {
			message = MessageBuilder.withPayload(IOUtils.toByteArray(fileInputStream))
					.setHeader("remote_dir", new String(ftpServerPath.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
					.setHeader("remote_filename", new String(ftpfilename.getBytes(Charset.forName("UTF-8")),"ISO-8859-1")).build();
		} catch (IOException e) {
			logger.error("ftp上传图片产生异常",e);
			throw new Exception(e);
		} finally{
			IOUtils.closeQuietly(fileInputStream);
		}
		return ftpChannel.send(message);
	}
	
	/**
	 * ftp 删除图片
	 * 
	 * @param ftpChannel
	 * @param imgfile
	 * @param ftpServerPath
	 * @return
	 * @throws Exception
	 */
	public static boolean ftpDeleteload(MessageChannel ftpChannel,String imgfile, String ftpServerPath) throws Exception{
		if (StringUtils.isNotBlank(ftpServerPath)
				&& ftpServerPath.startsWith("/")) {
			ftpServerPath = StringUtils.substringAfter(ftpServerPath, "/");
		}
		Message<String> message = null;
		try {
			message = MessageBuilder.withPayload(imgfile)
					.setHeader("file_remoteDirectory", new String(ftpServerPath.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
					.setHeader("file_remoteFile", new String(imgfile.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
					.build();
		} catch (Exception e) {
			logger.error("ftp删除图片产生异常",e);
			throw new Exception(e);
		}
		logger.info("删除ftp图片：getHeaders="+message.getHeaders());
		return ftpChannel.send(message);
	}
	
	/**
	 * ftp 下载图片
	 * 
	 * @param ftpChannel
	 * @param imgfile
	 * @param ftpServerPath
	 * @return
	 * @throws Exception
	 */
	public static boolean ftpGetload(MessageChannel ftpChannel,String imgfile ,String ftpServerPath) throws Exception{
		if (StringUtils.isNotBlank(ftpServerPath)
				&& ftpServerPath.startsWith("/")) {
			ftpServerPath = StringUtils.substringAfter(ftpServerPath, "/");
		}
		Message<String> message = null;
		try {
			message = MessageBuilder.withPayload(imgfile)
					.setHeader("file_remoteDirectory", new String(ftpServerPath.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
					.setHeader("file_remoteFile", new String(imgfile.getBytes(Charset.forName("UTF-8")),"ISO-8859-1"))
					.build();
		} catch (Exception e) {
			logger.error("ftp下载图片产生异常",e);
			throw new Exception(e);
		}
		logger.info("ftp下载图片：getHeaders="+message.getHeaders());
		return ftpChannel.send(message);
	}
	
	/**
	 * 指定路径获取FTP资源
	 * @throws Exception 
	 * 
	 */
	public static List<FTPFile> getResourcesByPath(Session session, String path) throws Exception{
		List<FTPFile> list = new ArrayList<FTPFile>();
		if(session==null){
			logger.error("ftp会话对象不能为null");
			return null;
		}
		if(!session.isOpen()){
			logger.error("ftp会话未连接");
			return null;
		}
		try {
			FTPFile[] arr = (FTPFile[]) session.list(path);
			Collections.addAll(list, arr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("获取ftp资源发生错误：" + e);
			throw new Exception(e);
		}
		return list;
	}
}
