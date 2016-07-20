package com.yougou.wfx.test.validate.oval;

import net.sf.oval.constraint.Assert;
import net.sf.oval.constraint.Length;
import net.sf.oval.constraint.NotEmpty;
import net.sf.oval.constraint.NotNull;
import net.sf.oval.constraint.Range;
import net.sf.oval.guard.Guarded;
import net.sf.oval.guard.Post;
import net.sf.oval.guard.Pre;

@Guarded
public class TestInputDto1 {
	
	@NotNull
	@NotEmpty
	@Length(min = 1,max = 3)
	private String attr1;
	
	@NotEmpty
	private String attr2;
	
	@Assert(expr = "_value ==_this.attr1 || _value == _this.attr2", lang = "javascript")
	public String attr3;
	
	@NotNull(when = "javascript:_this.attr1 == null")
	public String attr4;

	public String attr5;
	
	@Range(min=5, max=12)  
	private int month = 2;  
	
	@NotNull
	private TestInputDto2 dto;
	
	private int attr6 = 8;
	
	@Pre(expr = "_this.attr1 != null && count > _this.attr6", lang = "javascript")
	public void test1(int count){
		System.out.println("test1.....");
	}
	
	@Post(expr = "_this.attr1 != attr1 && count > _this.attr6", lang = "javascript")
	public void test2(int count){
		System.out.println("test2.....count="+count);
	}

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
