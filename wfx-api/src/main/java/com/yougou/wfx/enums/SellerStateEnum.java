package com.yougou.wfx.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * 分销商状态枚举
 * @author zhang.f1
 *
 */
public enum SellerStateEnum {
	AUDITING("1","待审核"),
	AUDIT_NOT_PASS("2","审核不通过"),
	IN_COOPERATING("3","合作中"),
	CANCEL_COOPERATE("4","取消合作");
	
	private String state;
	private String stateDesc;
	
	SellerStateEnum(String state,String stateDesc){
		this.setState(state);
		this.setStateDesc(stateDesc);
	}
	
	/**
	 * 传入分销商状态key值，获取对应状态描述
	 * @param state
	 * @return
	 */
	public static String getStateDescByState(String state){
		
		if(StringUtils.isNotBlank(state)){
			for(SellerStateEnum st : SellerStateEnum.values()){
				String key = st.getState();
				if(key.equals(state)){
					return st.getStateDesc();
				}
			}
		}
		return null;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
	
	
	
	
	
}
