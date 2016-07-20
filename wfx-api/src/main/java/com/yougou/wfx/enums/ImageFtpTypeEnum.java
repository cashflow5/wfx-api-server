package com.yougou.wfx.enums;

/**
 * C/B 两端图片上传类型枚举
 * @author zhang.f1
 *
 */
public enum ImageFtpTypeEnum {
	
	SHOP_LOGO_IMG("SHOP_LOGO","店铺LOGO图片"),
	SHOP_SIGN_IMG("SHOP_SIGN","店招图片"),
	MEMBER_LOGO_IMG("MEMBER_LOGO","用户头像图片"),
	SELLER_AUTHORIZE_IMG("SELLER_AUTHORIZE","分销商委托书图片"),
	SELLER_IDENTITY_IMG("SELLER_IDENTITY","分销商身份证图片"),
	SHOP_QR_CODE_IMG("SHOP_QR_CODE","店铺二维码图片");
	
	private String key;
	private String desc;
	ImageFtpTypeEnum(String key,String desc){
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
			for(ImageFtpTypeEnum st : ImageFtpTypeEnum.values()){
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
