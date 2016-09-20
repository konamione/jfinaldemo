package com.konami.jfd.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;

public class PassValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("oldPass", "oldMsg", "请填写旧密码");
		validateRequiredString("newPass", "newMsg", "请填写新密码");
		validateRequiredString("reNewPass", "reNewMsg", "请填写重复新密码");
		validateEqualField("newPass", "reNewPass", "reNewMsg", "两次输入的新密码不一致");
	}

	@Override
	protected void handleError(Controller c) {
		c.forwardAction("/user/toModifyPass");
	}

}
