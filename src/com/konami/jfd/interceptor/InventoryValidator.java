package com.konami.jfd.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.konami.jfd.vo.Inventory;

public class InventoryValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequired("inventory.goodsnum", "numMsg", "请填写数量");
		c.keepModel(Inventory.class);
		//validateToken("blogToken", "repeatMsg", "请勿重复提交");
	}

	@Override
	protected void handleError(Controller c) {
		c.forwardAction("/inv");
	}

}
