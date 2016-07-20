package com.yougou.wfx.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 缓存InputStream，以便InputStream的重复利用
 * 
 * @author wzf
 * @version 2016-4-11
 */
public class InputStreamCacher {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory
			.getLogger(InputStreamCacher.class);

	/**
	 * 将InputStream中的字节保存到ByteArrayOutputStream中。
	 */
	private ByteArrayOutputStream byteArrayOutputStream = null;

	public InputStreamCacher(InputStream inputStream) {
		if (null == inputStream)
			return;
		byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len;
		try {
			while ((len = inputStream.read(buffer)) > -1) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
			byteArrayOutputStream.flush();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

	public InputStream getInputStream() {
		if (null == byteArrayOutputStream)
			return null;
		return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
	}
	
	public void close(){
		if(null != byteArrayOutputStream){
			try {
				byteArrayOutputStream.close();
			} catch (IOException e) {
				logger.error("关闭输入流缓存出错");
			}
		}
	}
}