package com.yougou.wfx.util;

/**
 * 接口实现静态常量类
 * @author zhang.f1
 *
 */
public class ApiConstant {
	
	/**
	 *系统配置项key - 增值税税率
	 */
	public static final String VAT_RATE = "wfx.commodity.vat.rate";
	/**
	 * 系统配置项key -个人所得税税率
	 */
	public static final String TAX_RATE = "wfx.commodity.tax.rate";
	/**
	 * 系统配置项key -运费
	 */
	public static final String WFX_POSTFEE = "wfx.postFee";
	/**
	 * 系统配置项key -总经销商的账户
	 */	
	public static final String ORIGINAL_LOGIN_NAME = "wfx.commodity.original.login.name";
	/**
	 * 总经销商的sellerId
	 */	
	public static final String ORIGINAL_SELLER_ID_DEFAULT = "0735d892126211e6a3c761269a921097";
	/**
	 * 商品上架状态
	 * 
	 */
	public static final int ON_SHOW = 1;
	/**
	 * 商品下架状态
	 * 
	 */
	public static final int NOT_SHOW = 1;
	//微分销h5域名
	public static final String WFX_DOMAIN_H5 = "wfx.domain.h5";
	/**
	 * 供应商名称（暂时使用）
	 */
	public static final String SUPPLIER_YOUGOU = "优购科技有限公司";
}
