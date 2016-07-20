package com.yougou.wfx.common.constant;

/**
 * <p>Title: MessageConstant</p>
 * <p>Description: 短信格式常量类</p>
 * @author: zheng.qq
 * @date: 2016年4月27日
 */
public class MessageConstant {
	/*订单发货短信通知格式*/
	public static final String ORDER_DELIVERED_MESSAGE = "亲爱的优购家人，您的订单{0}已由{1}发出，"
			+ "快递单号：{2}，请注意先验货再签收。感谢您的支持！";
	
	/**
	 * 申请成为分销商，审核通过，短信提醒模板
	 */
	public static final String APPLY_TO_SELLER_MESSAGE="亲爱的优购家人，您已成为优粉，赶快关注“优购微零售官方服务号”分享商品吧！";

	/** 申请提现，提现成功短信通知模板 */
	public static final String SELLER_WITHDRAWAL_SUCCESS = "亲爱的优粉，您的提现申请：{0}，已成功提现：{1}元，将于3日内到您的指定账户，请您留意！";
	
}
