package com.yougou.wfx.enums;

/**
 * 订单日志类型枚举
 * @author zhang.f1
 *
 */
public enum OrderLogTypeEnum {
	
		ORDER_OPT_LOG(1,"操作日志"),
		ORDER_TURN_LOG(2,"正常流转日志");

		private Integer key;
		private String desc;
		
		OrderLogTypeEnum(Integer key,String desc){
			this.key=key;
			this.desc = desc;
		}
		
		/**
		 * 传入key值，获取对应描述
		 * @param state
		 * @return
		 */
		public static String getDescByKey(Integer keyValue){
			
			if(null != keyValue){
				for(OrderLogOptEnum st : OrderLogOptEnum.values()){
					Integer key = st.getKey();
					if(key == keyValue){
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
