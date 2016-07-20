package com.yougou.wfx.enums;

/**
 * 会员操作类型枚举：
 * @author zheng.qq
 *
 */
public enum MemberOptTypeEnum {
	LOGIN("LOGIN","会员登录"),
	MODIFY_PASSWORD("PASSWORD","密码设置"),
	VERIFY_AUTHORIZE("AUTHORIZE","资质审核"),
	MODIFY_BANK_CARD("BANK_CARD","银行卡绑定");
	
	private String key;
	private String desc;
	
	MemberOptTypeEnum(String key,String desc){
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
			for(MemberOptTypeEnum st : MemberOptTypeEnum.values()){
				String key = st.getKey();
				if(key .equals( keyValue) ){
					return st.getDesc();
				}
			}
		}
		return null;
	}

	public String getDesc() {
		return desc;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
