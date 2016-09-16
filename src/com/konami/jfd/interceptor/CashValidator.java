package com.konami.jfd.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.konami.jfd.vo.Cash;

public class CashValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("cash.res", "resMsg", "请填写事由");
		validateRequired("cash.money", "moneyMsg", "请填写金额");
	}

	@Override
	protected void handleError(Controller c) {
		c.keepModel(Cash.class);
		c.forwardAction("/cash");
		//c.render("/cash/index.html");
	}

}
