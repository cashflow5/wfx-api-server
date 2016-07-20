/**
 * 
 */
package com.yougou.wfx.enums;

/**
 * 业务类型-商品模块 枚举类
 * @author luo.q1
 *
 */
public enum BusinessTypeEnum {
	
	COMMODITY_CREATE("COMMODITY_CREATE","添加商品"), 
	COMMODITY_ON_SHELVES("COMMODITY_ON_SHELVES","商品上下架"), 
	COMMODITY_PRICE_UP("COMMODITY_PRICE_UP","商品调价"), 
	COMMODITY_EXPORT("COMMODITY_EXPORT","商品导出"),
	SYSTEM_CONFIG("SYSTEM_CONFIG","系统配置日志"),
	COMMODITY_ON_SHELVES_UP("COMMODITY_ON_SHELVES_UP","商品上架"), 
	COMMODITY_ON_SHELVES_DOWN("COMMODITY_ON_SHELVES_DOWN","商品下架"), 
	SYSTEM_AREA("SYSTEM_AREA","地区管理"),
	SELLER_BANK("SELLER_BANK","分销商提现银行卡");
	private String key;
	private String desc;
	
	BusinessTypeEnum(String key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(String keyValue){
		
		if(null != keyValue){
			for(BusinessTypeEnum st : BusinessTypeEnum.values()){
				String key = st.getKey();
				if(key.equals(keyValue) ){
					return st.getDesc();
				}
			}
		}
		return null;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
