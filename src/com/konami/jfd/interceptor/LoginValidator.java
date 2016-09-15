package com.konami.jfd.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class LoginValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("user.username", "nameMsg", "请填写用户名");
		validateRequiredString("user.userpass", "passMsg", "请填写密码");
	}

	@Override
	protected void handleError(Controller c) {
		c.render("/user/login.html");
	}

}
