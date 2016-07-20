package com.yougou.wfx.framework.utils;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.PBEKeySpec;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;

/**
 * DES 对称加密工具类
 *
 * @author 吴阳
 */
public class DESEncrypt {

	/** 密钥 */
	private static final String PASSWORD_CRYPT_KEY = "_@_j_belle_^wfx";

	/** 加密算法 */
	private final static String DES = "DES";
	
	/**
	 * PBKDF2 加密
	 */
	private final static String PBKDF="PBKDF2WithHmacSHA1";

	/** 日志器 **/
	private static final Logger logger = LoggerFactory.getLogger(DESEncrypt.class);

	/**
	 * 加密
	 *
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回加密后的数据
	 * @throws Exception
	 */
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(src);
	}

	/**
	 * 解密
	 *
	 * @param src
	 *            数据源
	 * @param key
	 *            密钥，长度必须是8的倍数
	 * @return 返回解密后的原始数据
	 * @throws Exception
	 */
	public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(DES);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(src);
	}

	/**
	 * 密码解密
	 *
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public final static String decrypt(String data) {
		try {
			return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			logger.error("解密失败", e);
		}
		return null;
	}

	/**
	 * 密码加密
	 *
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public final static String encrypt(String password) {
		try {
			return byte2hex(encrypt(password.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
		} catch (Exception e) {
			logger.error("解密失败", e);
		}
		return null;
	}

	/**
	 * 二行制转字符串
	 *
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0) {
			logger.error("长度不是偶数");
			throw new IllegalArgumentException("长度不是偶数");
		}
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}
	
	/**   
	* PBKDF2密码加密  
	* @param password 密码
	* @param memberId 用户ID
	* @return  
	*/  
	 public static String encryptPBKDF2(String password,String memberId) { 
		   try {       			
			   int iterations = 1000;   //迭代次数
			   char[] chars = password.toCharArray();    //加密明文
			   byte[] salt = memberId.getBytes();   //随机串              
		       PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations,16*8) ;		   
		       SecretKeyFactory skf = SecretKeyFactory.getInstance(PBKDF);   
		       byte[] hash = skf.generateSecret(spec).getEncoded();        
		       
		       return byte2hex(hash);
		   } catch (Exception e){  
			   logger.error("encryptPBKDF2加密失败", e);   
	       }    		
		  return null;  
	 }  
	 
	 
	 /**
	  * 获取随机串
	  * @return
	  */
	  private static String getSaltSHA1(){
	      	SecureRandom sr;   
		    byte[] salt = new byte[16];  
	        try {   
	        	sr = SecureRandom.getInstance("SHA1PRNG");  
	            sr.nextBytes(salt);   
		    } catch (NoSuchAlgorithmException e) {  
	           e.printStackTrace();   
		    }           
		    return salt.toString();       
	  }   
	       

	


	public static void main(String args[]){
		// 页面明文密码
		String pwd = "aaaaa888";
		// 用户ID
		String merberId = "6d0e3eaa8f6142d1aede20104cea4cef";
		// 前台MD5加密
		pwd = Hashing.md5().hashString(pwd, Charsets.UTF_8).toString().toUpperCase();
		// 后台PBKDF2 加密
		String jmh = encryptPBKDF2(pwd,merberId);
		System.out.println(";----加密后："+jmh);
		System.out.println(jmh.equals("00708AD988DB8D610B25AC08FAB4F04A"));
	/*	String loginaccountId = "xxx";  84018F1A8981374FE7DA2F27501EF6EA
		byte[] b=loginaccountId.getBytes();
		System.out.println(b.length);
		String singn = DESEncrypt.encrypt(loginaccountId);
		System.out.println(singn);
		System.out.println("-----------------------");
		System.out.println(DESEncrypt.decrypt(singn));
		
		int length = PASSWORD_CRYPT_KEY.getBytes().length;
		System.out.println(length);*/
	
	}
}
