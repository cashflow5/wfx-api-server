package com.yougou.wfx.enums;

public enum WfxLogisticsCompany {
    WEITEPAI("weitepai","微特派"),
    SHENTONG("bjshentong","申通"),
    FEIHU("feihu","飞狐"),
    HAIWAIHUANQIU("haiwaihuanqiu","海外环球"),
    SHUNFENG("shunfeng","顺丰"),
    RUFENGDA("rufengda","如风达"),
    ZHAIJISONG("zhaijisong","宅急送"),
    QUANFENG("quanfengkuaidi","全峰"),
    YTO("yuantong","圆通"),
    ZTO("zhongtong","中通"),
    JD("jd","京东"),
    GDTL("feiyuanvipshop","广东通路"),
    SJFD("shunjiefengda","顺捷丰达"),
    STO("shentong","申通"),
    EMS("ems","EMS");
    
    private final String code;
    
    private final String name;
    
    WfxLogisticsCompany(String code,String name){
    	this.code = code;
    	this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    public String getCode() {
        return this.code;
    }
}
