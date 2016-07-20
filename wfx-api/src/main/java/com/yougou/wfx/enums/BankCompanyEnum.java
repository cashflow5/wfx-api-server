package com.yougou.wfx.enums;

public enum BankCompanyEnum {

	BOC(1,"中国银行"),
	CMB(2,"招商银行"),
	ICBC(3,"中国工商银行"),
	ABC(4,"中国农业银行"),
	CCB(5,"中国建设银行"),
	PSBC(6,"中国邮政储蓄"),
	CSSY(7,"城市商业银行"),
	GDB(8,"广东发展银行"),
	BCM(9,"交通银行"),
	PDFZ(10,"上海浦东发展银行"),
	SDB(11,"深圳发展银行"),
	CIB(12,"兴业银行"),
	CEB(13,"中国光大银行"),
	CMBC(14,"中国民生银行"),
	CITIC(15,"中信实业银行"),
	PINGAN(16,"平安银行"),
	BOB(17,"北京银行"),
	DGB(18,"东莞银行"),
	RCB(19,"农村商业银行"),
	HUAXIA(20,"华夏银行"),
	OTHER(21,"其他银行");

	private Integer key;
	private String desc;
	
	BankCompanyEnum(Integer key,String desc){
		this.key = key;
		this.desc = desc;
	}
	
	/**
	 * 传入key值，获取对应描述
	 * @param state
	 * @return
	 */
	public static String getDescByKey(Integer keyValue){
		
		if(null != keyValue){
			for(BankCompanyEnum st : BankCompanyEnum.values()){
				Integer key = st.getKey();
				if(key == keyValue ){
					return st.getDesc();
				}
			}
		}
		return null;
	}

	public Integer getKey() {
		return key;
	}

	public void setKey(Integer key) {
		this.key = key;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
