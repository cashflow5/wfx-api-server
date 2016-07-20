package com.yougou.wfx.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 产生随机字符串、流水号等
 * @Author: 邓奇峰
 * Date: 13-11-22
 */
public class NumGenUtil {
    
    private static Random randGen = null;
    private static char[] numbersAndLetters = null;
    private static Object initLock = new Object();
    
    /**
     * 产生随机字符串
     * @param length
     * @return
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    numbersAndLetters = ("abcdefghijklmnopqrstuvwxyz0123456789"
                            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .toCharArray();
                }
            }
        }
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(61)];
        }
        return new String(randBuffer);
    }

    /**
     * 生成一定长度的流水号，不足位补0
     *
     * @param num
     * @param decimals 生成流水号的长度
     * @return
     */
    public static String toIntString(Integer num, int decimals) {
        String str = "";
        String s = num.toString();
        for (int i = s.length(); i < decimals; i++) {
            str += "0";
        }
        return str + num;
    }

    /**
     * 生成流水号
     *
     * @param t 流水号位数
     * @return 流水号
     */
    public static String getSequenceNumber(int t) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        String str = sdf.format(d);
        String haomiao = String.valueOf(System.nanoTime());
        str = str + haomiao.substring(haomiao.length() - 6, haomiao.length());
        return str.substring(str.length() - t, str.length());
    }

    /**
     * 根据结构名，获取当前分类从父到子的结构字符串数组
     *
     * @return
     */
    public static String[] getParamsByStructName(String structName) {
        if (StringUtils.isBlank(structName)) {
            return null;
        }
        if (structName.indexOf('-') < 0) {
            String[] str = new String[1];
            str[0] = structName;
        }
        String[] arr = structName.trim().split("-");
        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                result[i] = arr[i];
            } else {
                result[i] = result[i - 1] + "-" + arr[i];
            }
        }
        return result;
    }
}
