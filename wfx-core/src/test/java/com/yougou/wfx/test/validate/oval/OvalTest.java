package com.yougou.wfx.test.validate.oval;

import java.util.List;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;

public class OvalTest {
	public static void main(String[] args) throws Exception {
		
		Validator validator = new Validator();
		TestInputDto1 dto1 = new TestInputDto1();
		dto1.setAttr1(null);
		dto1.setAttr2("abcde");
		dto1.setAttr3("abcde1");
		dto1.test1(1);
		dto1.test2(2);
		List<ConstraintViolation> violations = validator.validate(dto1);
		if (violations.size() > 0) {
			System.out.println("验证不通过!有如下问题：");
			for(ConstraintViolation v : violations){
				System.out.println(v.getMessage());
			}
		}else{
			System.out.println("验证通过!");
		}
	}
	
	public void createPerson(TestInputDto1 dto1){
	}
}
