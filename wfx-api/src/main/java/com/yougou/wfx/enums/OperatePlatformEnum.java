/**
 * 
 */
package com.yougou.wfx.enums;

/**
 * 操作平台枚举类
 * @author zheng.qq
 *
 */
public enum OperatePlatformEnum {
		
	SHOPPINGMALL("0","商城端(C端)"), 
	DISTRIBUTORPLATFORM("1","分销商平台(B端)"), 
	PUBLICMALL("2","公众号商城"); 

	
	private String key;
	private String desc;
	
	
	OperatePlatformEnum(String key,String desc){
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
			for(OperatePlatformEnum st : OperatePlatformEnum.values()){
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
