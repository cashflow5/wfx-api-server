package com.yougou.wfx.enums;
/**
 * 物流公司列表枚举类
 * @author luo.q1
 *
 */
public enum DistributionCompanyEnum {

	EMSJJ("EMSJJ","EMS经济快递"),
	STO("STO","申通"),
	EMS("EMS","EMS"),
	ZJS("ZJS","宅急送"),
	CCES("CCES","国通快递"),
	ZTO("ZTO","中通"),
	YUNDA("YUNDA","韵达"),
	FAST("FAST","快捷"),
	QFKD("QFKD","全峰快递"),
	SUER("SUER","速尔快递"),
	FEDEX("FEDEX","联邦"),
	LBEX("LBEX","龙邦快递"),
	YZGNXB("YZGNXB","邮政国内小包"),
	TTKDEX("TTKDEX","天天快递"),
	UC("UC","优速"),
	SF("SF","顺丰"),
	YTO("YTO","圆通"),
	HTKY("HTKY","汇通"),
	CHENGJI("CHENGJI","城际速递"),
	DB("DB","德邦快递"),
	JDKD("JDKD","京东快递"),
	CDFC("CDFC","成都丰程"),
	ZTOGJ("ZTOGJ","中通快递_国际"),
	UAPEX("UAPEX","全一快递"),
	ywl("ywl","云物流");
	
	private String key;
	private String desc;
	
	DistributionCompanyEnum(String key,String desc){
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
