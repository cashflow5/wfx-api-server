package com.yougou.wfx.test.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.yougou.wfx.shop.api.front.IShopFrontApi;

public class TestFileUploadApi {
	 public static void main(String[] args) throws MalformedURLException, FileNotFoundException {
//		 String url = "http://10.0.40.62:8090/dubbo/com.yougou.wfx.shop.api.front.IShopFrontApi";
			String url = "http://localhost:8090/dubbo/com.yougou.wfx.shop.api.front.IShopFrontApi";
			HessianProxyFactory hp = new HessianProxyFactory();
			
			IShopFrontApi api = (IShopFrontApi)hp.create(IShopFrontApi.class, url);
			
//			String filePath = "/Users/wuyang/Desktop/goods.json";
//			InputStream in = new FileInputStream(filePath);
//			api.testFtpUpload(filePath, in);
			
			InputStream in = new FileInputStream("D:\\test.txt");
//			api.testFtpUpload("test", in, "test.txt");
		}
}
