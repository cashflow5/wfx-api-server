 /*
 * 版本信息
 
 * 日期 2016-03-29 13:37:55
 
 * 版权声明Copyright (C) 2011- 2016 YouGou Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为优购科技开发研制，未经本公司正式书面同意，其他任何个人、团体不得
 * 使用、复制、修改或发布本软件。
 */
package com.yougou.wfx.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yougou.wfx.framework.logger.Logger;
import com.yougou.wfx.framework.logger.LoggerFactory;
import com.yougou.wfx.framework.sequence.SequenceHelper;
import com.yougou.wfx.framework.utils.WFXToolkit;

/**
 * 业务模块编码生成
 * @author wuyang
 * @Date 创建时间：2016-03-29 13:37:55
 */
public class BizNoGen {
	
	// 商品编码key
	public static final String SEQUENCE_KEY_COMMOIDTY_NO = "com.yougou.wfx.commodity.no";
	// 订单编码key
	public static final String SEQUENCE_KEY_ORDER_NO = "com.yougou.wfx.order.no";
	// 文章编码key
	public static final String SEQUENCE_KEY_ARTICLE_NO = "com.yougou.wfx.article.no";
	// 退款编号key
	public static final String SEQUENCE_KEY_REFUND_NO = "com.yougou.wfx.refund.no";
	// 账户余额提现申请单编号key
	public static final String SEQUENCE_KEY_ACCOUNT_WITHDRAWAL_NO = "com.yougou.wfx.account.withdrawal.no";
	// 店铺编码key
	public static final String SEQUENCE_KEY_SHOP_CODE = "com.yougou.wfx.shop.no";
	
	private static final Logger logger = LoggerFactory.getLogger(SequenceHelper.class);
	
	/**
	 * 生成商品编码，9位编码，首位是环境标识，1：正式环境，2：测试环境
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getCommodityNo() throws Exception {
		String sequence = "";
		try {
			sequence = SequenceHelper.getSeq(SEQUENCE_KEY_COMMOIDTY_NO, 8);
		} catch (Exception e) {
			logger.error("生成商品编码时发生异常",e);
			throw e;
		}
		if(WFXToolkit.isDev()){
			sequence = "2" + sequence;
		}else{
			sequence = "1" + sequence;
		}
		return sequence;
	}
	
	/**
	 * 生成退款编码，9位编码，首位是环境标识，1：正式环境，2：测试环境
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getRefundNo() throws Exception {
		String sequence = "";
		try {
			sequence = SequenceHelper.getSeq(SEQUENCE_KEY_REFUND_NO, 8);
		} catch (Exception e) {
			logger.error("生成商品编码时发生异常",e);
			throw e;
		}
		if(WFXToolkit.isDev()){
			sequence = "2" + sequence;
		}else{
			sequence = "1" + sequence;
		}
		return sequence;
	}
	
	/**
	 * 生成订单号：环境标识(1位)+年份最后一位(1位)+月(2位)+日(2位)+小时(2位)+分钟(2位)+增长序列(4位)
	 *	                     共14位，支持订单极限：每分钟9999个订单
	 *         首位是环境标识，1：正式环境，2：测试环境
	 * @return
	 * @throws Exception
	 */
	public static String getOrderNo() {
		String sequence = "";
		try {
			sequence = SequenceHelper.getSeq(SEQUENCE_KEY_ORDER_NO,4);
		} catch (Exception e) {
			logger.error("生成订单号时发生异常",e);
			throw new RuntimeException();
		}
		StringBuffer preFix = new StringBuffer(10);
		if(WFXToolkit.isDev()){
			preFix.append(2);
		}else{
			preFix.append(1);
		}
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		preFix.append(year%10);
		int month = calendar.get(Calendar.MONTH)+1;
		if(month <= 9){
			preFix.append("0" + month);
		}else{
			preFix.append(month);
		}
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		if(day <= 9){
			preFix.append("0" + day);
		}else{
			preFix.append(day);
		}
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(hour <= 9){
			preFix.append("0" + hour);
		}else{
			preFix.append(hour);
		}
		int minute = calendar.get(Calendar.MINUTE);
		if(minute <= 9){
			preFix.append("0" + minute);
		}else{
			preFix.append(minute);
		}
		sequence = preFix.toString() + sequence;
		logger.error("生成的编号为：" + sequence);
		return sequence;
	}
	
	/**
	 * 生成账户提现申请单号（17位）
	 * 规则："TX"+环境标识（1位）+年份（2位）+月份（2位）+日期（2位）+时（2位）+分（2位）+流水号（4位）
	 * @return
	 */
	public static String getAccountWithdrawalNo() {
		String sequence = "";
		try {
			sequence = SequenceHelper.getSeq(SEQUENCE_KEY_ACCOUNT_WITHDRAWAL_NO, 4);
		} catch (Exception e) {
			logger.error("生成提现申请单号时发生异常",e);
			throw new RuntimeException();
		}
		StringBuffer preFix = new StringBuffer("TX");
		if(WFXToolkit.isDev()){
			preFix.append(2);
		}else{
			preFix.append(1);
		}
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        preFix.append(sdf.format(new Date()));
		sequence = preFix.toString() + sequence;
		logger.error("生成提现申请单编号为：" + sequence);
		return sequence;
	}
	
	/**
	 * 生成店铺编码
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getShopCode() throws Exception {
		String sequence = "";
		try {
			sequence = SequenceHelper.getSeq(SEQUENCE_KEY_SHOP_CODE);
		} catch (Exception e) {
			logger.error("生成商品编码时发生异常",e);
			throw e;
		}
		
		return sequence;
	}
	
	/**
	 * 生成文章编号：7位数字编码，1000001开始
	 * @return
	 * @throws Exception
	 */
	public static String getArticleNo() {
		String sequence = "";
		try {
			sequence = SequenceHelper.getSeq(SEQUENCE_KEY_ARTICLE_NO);
		} catch (Exception e) {
			logger.error("生成文章编号时发生异常",e);
			throw new RuntimeException();
		}
		logger.info("生成的编号为：" + sequence);
		return sequence;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(getShopCode());
	}
	
//	/**
//	 * 创建序列号 YYMMDDHHMM0001，后四位从0001开始，新增+1
//	 * @param createDate
//	 * @param set
//	 * @param redisKeyPrefix  rediskey的前缀 必须有值
//	 * @return
//	 */
//	private static String createSequenceNo(String redisKeyPrefix) {
//		Date createDate = new Date();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");
//		StringBuilder out = new StringBuilder(dateFormat.format(createDate));
//		try{
//			//固定字符串+当前时间（精确到秒）做Key 取退款单号
//			Long seq = WFXRedisUtil.increment(redisKeyPrefix,out);
//			if(seq <= 1){
//				WFXRedisUtil.expireForRefundNo(redisKeyPrefix,out); //设置过期时间为3分钟
//			}
//			String formatSeq = String.valueOf(seq);
//			if (seq < 1000) {
//				DecimalFormat decimalFormat = new DecimalFormat("0000");
//				formatSeq = decimalFormat.format(seq);
//			}
//			out.append(formatSeq);
//			logger.info("通过redis生成序列号正常，序列号为："+out.toString());
//		}catch(Exception e){
//			logger.error("通过redis生成序列号异常",e);
//		}
//		if(out.length()<14){
//			int size = buildRandom(3);
//			out.append(size);
//			out.append(new Random().nextInt(10));
//		}
//		return out.toString();
//	}
//	/**
//	 * 取出一个指定长度大小的随机正整数.
//	 * 
//	 * @param length
//	 *            int 设定所取出随机数的长度。length小于11
//	 * @return int 返回生成的随机数。
//	 */
//	private static  int buildRandom(int length) {
//		int num = 1;
//		double random = Math.random();
//		if (random < 0.1) {
//			random = random + 0.1;
//		}
//		for (int i = 0; i < length; i++) {
//			num = num * 10;
//		}
//		return (int) ((random * num));
//	}
//	
//	public static void main(String[] args) {
//		System.out.println(createSequenceNo(""));
//	}
}
