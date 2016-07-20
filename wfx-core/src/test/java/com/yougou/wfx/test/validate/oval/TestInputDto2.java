package com.yougou.wfx.test.validate.oval;

import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;


public class TestInputDto2 {
	
	@NotNull
	@NotEmpty
	private String attr1;
	
	private String attr2;
	
	public String attr3;
	
	public String attr4;
	
	public String attr5;
	
	public TestInputDto3 dto3;

	public String getAttr1() {
		return attr1;
	}

	public void setAttr1(String attr1) {
		this.attr1 = attr1;
	}

	public String getAttr2() {
		return attr2;
	}

	public void setAttr2(String attr2) {
		this.attr2 = attr2;
	}

	public String getAttr3() {
		return attr3;
	}

	public void setAttr3(String attr3) {
		this.attr3 = attr3;
	}

	
}
