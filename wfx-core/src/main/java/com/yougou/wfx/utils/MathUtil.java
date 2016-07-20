package com.yougou.wfx.utils;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MathUtil {
	private Logger logger = LoggerFactory.getLogger(MathUtil.class);
	
	private static final Integer SCALE = 2;
	 private static final int DEF_DIV_SCALE = 10;
	
	//四舍五入并保留两位小数
	public static Double roundHalfUp(Double d){
		if(null == d){
			return 0d;
		}
		//用数字构造bigdecimal不精确，String精确
		BigDecimal bigdecimal = new BigDecimal(d+"");
		bigdecimal = bigdecimal.setScale(SCALE,BigDecimal.ROUND_HALF_UP);
		return bigdecimal.doubleValue();
	}
	
	 /**
	  * 提供精确的加法运算。
	  * 
	  * @param v1
	  *            被加数
	  * @param v2
	  *            加数
	  * @return 两个参数的和
	  */
	 public static double add(double v1, double v2) {
	  BigDecimal b1 = new BigDecimal(Double.toString(v1));
	  BigDecimal b2 = new BigDecimal(Double.toString(v2));
	  return b1.add(b2).doubleValue();
	 }
	 
	 public static double add(double v1, double v2 , double v3) {
		  BigDecimal b1 = new BigDecimal(Double.toString(v1));
		  BigDecimal b2 = new BigDecimal(Double.toString(v2));
		  BigDecimal b3 = new BigDecimal(Double.toString(v3));
		  return b1.add(b2).add(b3).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); 
	 }

	 /**
	  * 提供精确的减法运算。
	  * 
	  * @param v1
	  *            被减数
	  * @param v2
	  *            减数
	  * @return 两个参数的差
	  */
	 public static double sub(double v1, double v2) {
	  BigDecimal b1 = new BigDecimal(Double.toString(v1));
	  BigDecimal b2 = new BigDecimal(Double.toString(v2));
	  return b1.subtract(b2).doubleValue();
	 }

	 /**
	  * 提供精确的乘法运算。
	  * 
	  * @param v1
	  *            被乘数
	  * @param v2
	  *            乘数
	  * @return 两个参数的积
	  */
	 public static double mul(double v1, double v2) {
	  BigDecimal b1 = new BigDecimal(Double.toString(v1));
	  BigDecimal b2 = new BigDecimal(Double.toString(v2));
	  return b1.multiply(b2).doubleValue();
	 }

	 /**
	  * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。
	  * 
	  * @param v1
	  *            被除数
	  * @param v2
	  *            除数
	  * @return 两个参数的商
	  */
	 public static double div(double v1, double v2) {
	  return div(v1, v2, DEF_DIV_SCALE);
	 }

	 /**
	  * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。
	  * 
	  * @param v1
	  *            被除数
	  * @param v2
	  *            除数
	  * @param scale
	  *            表示表示需要精确到小数点以后几位。
	  * @return 两个参数的商
	  */

	 public static double div(double v1, double v2, int scale) {
	  if (scale < 0) {
	   throw new IllegalArgumentException(
	     "The scale must be a positive integer or zero");
	  }
	  BigDecimal b1 = new BigDecimal(Double.toString(v1));
	  BigDecimal b2 = new BigDecimal(Double.toString(v2));
	  return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	 }

	 /**
	  * 提供精确的小数位四舍五入处理。
	  * 
	  * @param v
	  *            需要四舍五入的数字
	  * @param scale
	  *            小数点后保留几位
	  * @return 四舍五入后的结果
	  */
	 public static double round(double v, int scale) {
	  if (scale < 0) {
	   throw new IllegalArgumentException(
	     "The scale must be a positive integer or zero");
	  }
	  BigDecimal b = new BigDecimal(Double.toString(v));
	  BigDecimal one = new BigDecimal("1");
	  return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	 }
	 
	 /* 根据公式计算：( wfxPrice/(1+vatRate) - costPrice/(1+vatRate) -postFee )/(1+taxRate) */
	 public static Double formula1st(Double wfxPriceD,Double costPriceD,BigDecimal vatRate,BigDecimal taxRate,BigDecimal postFee){
		if(null == wfxPriceD || costPriceD ==null || vatRate==null|| taxRate==null|| postFee==null ){
			return null;
		}
		BigDecimal result = null;
		//用数字构造bigdecimal不精确，String精确
		BigDecimal wfxPrice = new BigDecimal(Double.toString(wfxPriceD));
		BigDecimal costPrice = new BigDecimal(Double.toString(costPriceD));
		BigDecimal one = new BigDecimal("1") ;
		BigDecimal divisor1 = vatRate.add(one );
//		result = wfxPrice.divide( divisor1,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP )
//						 .subtract( costPrice.divide( divisor1,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP ) )
//						 .subtract( postFee )
//						 .divide(taxRate.add(one), DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
		result = wfxPrice.subtract( costPrice ).divide( divisor1,DEF_DIV_SCALE,BigDecimal.ROUND_HALF_UP )
				 .subtract( postFee )
				 .divide(taxRate.add(one), DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
		
		return result.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();  
	 }
	
//	 public static void main(String[] args) {
//		 MathUtil util = new MathUtil();
//		 System.out.println( util.formula1st(468d, 141.6d, new BigDecimal(0.17), new BigDecimal( 0.2 ), new BigDecimal(15) ) );
//	}
}
