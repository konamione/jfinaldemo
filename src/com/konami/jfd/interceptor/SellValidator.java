package com.konami.jfd.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.konami.jfd.vo.Sell;

public class SellValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequired("sell.goodsnum", "gNumError", "数量不能为空");
		validateRequired("sell.goodsPrice", "gPriceError", "单价不能为空");
		validateRequired("sell.finalPrice", "fPriceError", "成交价不能为空");
		c.keepModel(Sell.class);
	}

	@Override
	protected void handleError(Controller c) {
		c.forwardAction("/sell");
	}

}
