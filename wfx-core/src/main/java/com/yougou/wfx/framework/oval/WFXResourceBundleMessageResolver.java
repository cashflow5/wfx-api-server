package com.yougou.wfx.framework.oval;

import java.util.Locale;
import java.util.ResourceBundle;

import net.sf.oval.Validator;
import net.sf.oval.localization.message.ResourceBundleMessageResolver;

public class WFXResourceBundleMessageResolver {
	static ResourceBundleMessageResolver resolver;
	static ResourceBundle bundle;
	public WFXResourceBundleMessageResolver(){
	}
	
	public static void main(String[] args) {
		System.out.println(Locale.getDefault());
		if (resolver != null && bundle != null) {
			resolver.removeMessageBundle(bundle);
		}
		Locale.setDefault(new Locale("cn"));
		bundle = ResourceBundle.getBundle("oval_messages");
		resolver = (ResourceBundleMessageResolver) Validator.getMessageResolver();
		resolver.addMessageBundle(bundle);
		String msg =  resolver.getMessage("net.sf.oval.constraint.Assert.violated");
		System.out.println(msg);
	}
}
