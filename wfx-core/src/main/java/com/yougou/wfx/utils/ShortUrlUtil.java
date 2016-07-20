 /*
 * 版本信息
 
 * 日期 2016-04-06 14:37:20
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */

package com.yougou.wfx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.yougou.wfx.framework.cache.redis.WFXRedisUtil;
import com.yougou.wfx.framework.cache.redis.WFXRedisUtil.WFXModuleType;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.utils.UUIDGenerator;

/**
 * 短url工具类
 * @author wuyang
 * @Date 创建时间：2016-04-06 14:37:20
 */
public class ShortUrlUtil {

	protected final static Logger logger = LoggerFactory.getLogger(ShortUrlUtil.class);
	
	/**
	 * 根据业务id或编码生成短编码
	 * @param bizIdOrNo	原业务id或者no
	 * @param length	短编码长度(至少7位数)
	 * @return
	 * @throws Exception 
	 */
	public static String encode(String bizIdOrNo,int length) throws Exception {
		if(length < 6){
			throw new Exception("至少需要6位数!");
		}
		String shortNo = shortUrl(bizIdOrNo, length);
		WFXRedisUtil.set(WFXModuleType.SHORTURL, shortNo, bizIdOrNo);
		return shortNo;
	}
	
	/**
	 * 短编码解码
	 * @param shortNo 短编码
	 * @return 6f0703f7562b4a7cafa1f58f5240834b
	 * @throws Exception
	 */
	public static String decode(String shortNo) throws Exception {
		return WFXRedisUtil.getString(WFXModuleType.SHORTURL, shortNo);
	}

	private static String shortUrl(String url ,int length) throws NoSuchAlgorithmException {
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h",
				"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
				"u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
				"6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
				"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
				"U", "V", "W", "X", "Y", "Z"
		};
		String resUrl = "";
		// 对传入网址进行 MD5 加密
		String hex = md5ByHex(url);
		int maxLength = hex.length();
		int genNum;
		if(maxLength % length == 0){
			genNum = maxLength / length;
		}else{
			genNum = (maxLength / length) + 1;
		}
		int fixNum;
		if(length % genNum == 0){
			fixNum = length / genNum;
		}else{
			fixNum = (length / genNum) + 1;
		}
		int pronum = 0;
		for (int i = 1; i <= genNum ; i++) {
			if(i > length){
				break;
			}
			int start = (i-1)*length;
			int end = start + length;
			//当分组数大于每组取值个数时
			if(i == length || end > maxLength){ 
				end = maxLength;
			}
			//当分组数小于每组取值个数时
			if(i == genNum && pronum < length){
				fixNum = length - pronum;
			}
			String sTempSubString = hex.substring(start, end);
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			for (int j = 0; j < fixNum; j++) {
				long index = 0x0000003F & lHexLong;
				if(index >= chars.length){
					index = chars.length - 1;
				}
				resUrl += chars[(int) index];
				lHexLong = (lHexLong + 0x0000003F) >>> length;
				pronum++;
			}
		}
		return resUrl;
	}

	/**
	 * MD5加密(32位大写)
	 * @param src
	 * @return
	 * @throws NoSuchAlgorithmException 
	 */
	private static String md5ByHex(String src) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] b = src.getBytes();
		md.reset();
		md.update(b);
		byte[] hash = md.digest();
		String hs = "";
		String stmp = "";
		for (int i = 0; i < hash.length; i++) {
			stmp = Integer.toHexString(hash[i] & 0xFF);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	public static void main(String[] args) throws Exception {
		// 创建测试数据
		createIds();
		// 比对重复数据
		compareReplicatedData();
    }
	
	private static void createIds() throws Exception{
		for(int i = 1;i<=100;i++){
			FileWriter fw = new FileWriter("/Users/wuyang/Desktop/shorturl/ids_"+ i +".txt");
			for(int j = 0;j<100000;j++){
				String id = UUIDGenerator.get32LowCaseUUID();
				String url = shortUrl(id, 8);
				fw.write(url +"\n");
			}
			fw.close();
		}
	}
	
	private static void compareReplicatedData() throws Exception{
		for(int i = 1;i<=100;i++){
			String filePath = "/Users/wuyang/Desktop/shorturl/ids_"+ i +".txt";
			File file = new File(filePath);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String data = reader.readLine();
			boolean isExist = false;
	        while (data != null) {
		        String[] s = data.split(",");
	        	String id = s[0];
	        	String url = s[1];
	        	int count = 0;
	        	long startTime = System.currentTimeMillis();
	        	for(int j = 1;j<=100;j++){
					String filePath1 = "/Users/wuyang/Desktop/shorturl/ids_"+ j +".txt";
					List<String> ids = FileUtils.readLines(new File(filePath1));
					for(String line : ids){
						count ++;
			        	String[] s1 = line.split(",");
			        	String id1 = s1[0];
			        	String url1 = s1[1];
			        	if(!data.equals(line) && url.equals(url1)){
			        		isExist = true;
			        		//System.out.println("重复,id["+id+"],["+url+"!="+url1+"],file="+filePath1);
			        		break;
			        	}else{
			        		//System.out.println("不重复,id["+id+"],["+url+"=="+url1+"],file="+filePath1);
			        	}
					}
	        	}
	        	data = reader.readLine();
	        	System.out.println("已对比了"+ count +"条数据,result="+isExist+"，id["+id+"],["+url+"],file="+ filePath +",耗时：" + (System.currentTimeMillis() - startTime) +"ms");
	        }
	        reader.close();
		}
	}
}
