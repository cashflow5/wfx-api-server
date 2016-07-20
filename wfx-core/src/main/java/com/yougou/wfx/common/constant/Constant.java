package com.yougou.wfx.common.constant;

/**
 * 接口实现静态常量类
 * @author zhang.f1
 *
 */
public class Constant {
	
	/**
	 * 申请成为分销商，自动审核开关
	 */
	public static final String APPLY_TO_SELLER_AUDIT_SWITCH = "wfx.seller.auto.audit";
	
	public static final String DATE_FORMAT_NORMAL = "yyyy-MM-dd HH:mm:ss";
	/*超时自动确认订单收货的超时天数*/
	public static final String KEY_AUTOCHANGE_ORDERSTATUS_DAYS ="wfx.order.auto.confirm.order.days";
	/*定时作废未付款订单的时间（分钟）*/
	public static final String KEY_CLOSEORDERS_TIME ="wfx.order.auto.close.order.minutes";
	/*拒绝退款之后自动取消退款的天数*/
	public static final String KEY_TIMEOUT_CLOSEREFUND_DAYS ="wfx.order.auto.close.refund.days";
	//微分销支付方式
	public static final String WFX_PAY_TYPE = "wfx.pay.type";
	//微分销单价商品最大购买数量
	public static final String WFX_ORDER_SINGLE_COMM_MAX_NUM = "wfx.order.single.comm.max.num";
	//微分销每天最大订单数量
	public static final String WFX_ORDER_ONEDAY_MAX_ORDER_NUM = "wfx.order.oneday.max.order.num";
	//微分销确认收货后超时时间
	public static final String WFX_ORDER_COMMISION_GENERATE_DAY = "wfx.order.commision.generate.day";
	//二维码临时保存路径
	public static final String WFX_PIC_TEMP_PATH = "wfx.pic.temp.path";
	
	/**
	 * 申请成为分销商自动审核开关开启，配置项值
	 */
	public static final String WFX_APPLY_TO_SELLER_SWITCH_OPEN="true";
	
	/**
	 * 后台分销商审核通过操作类型
	 */
	public static final String WFX_SELLER_AUDIT_TYPE="audit";
	
	/**
	 * 后台分销商开启合作操作类型
	 */
	public static final String WFX_SELLER_COOPERATE_TYPE="cooperate";
	
	/**
	 * 申请成为分销商，成功交易订单数量，系统配置项key
	 */
	public static final String WFX_APPLY_TO_SELLER_LIMIT_KEY = "wfx.apply.seller.order.count";
	
	/**
	 * 普通属性项，尺码ID
	 */
	public static final String WFX_COMMODITY_PROP_ITEM_ID="2c9481b130b516910130b553204204b6";
	
	/**
	 * 商品小图
	 */
	public static final String COMMODITY_PICS_SMALL = "s";
	
	/**
	 * 店招默认图片路径
	 */
	public static final String WFX_SHOP_SIGN_DEFAULT_PIC_URL="SHOP_SIGN/default_sign.png";
	
	/**
	 * 店铺logo默认图片路径
	 */
	public static final String WFX_SHOP_LOGO_DEFAULT_PIC_URL = "SHOP_LOGO/default_logo.png";
	
	/**
	 * 用户头像图片默认路径
	 */
	public static final String WFX_MEMBER_LOGO_DEFAULT_PIC_URL = "MEMBER_LOGO/default_logo.png";
	/**
	 * 轮播图-显示
	 */
	public static final Integer WFX_CAROUSEL_FIGURE_SHOW = 2;
	/**
	 * 文章-发布
	 */
	public static final Integer WFX_ARTICLE_IS_PUBLISH = 2;
	/**
	 * 文章-推荐
	 */
	public static final Integer WFX_ARTICLE_RECOMMEND = 2;
	/**
	 * 用户头像标识：从第三方抓取的全路径头像 。前缀 
	 */
	public static String WX_IMG_PREFIX = "WX:";
}
