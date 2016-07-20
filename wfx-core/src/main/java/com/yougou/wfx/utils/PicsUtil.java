package com.yougou.wfx.utils;

import java.util.Random;

public class PicsUtil {
	private static final String picUrl1 = "http://i1.ygimg.cn/pics";
	private static final String picUrl2 = "http://i2.ygimg.cn/pics";
	
	public static String getPicUrl(){
		Random random = new Random();
        if (random.nextInt(2) == 0) {
            return PicsUtil.picUrl1;
        } else {
            return PicsUtil.picUrl2;
        }
	}
}
