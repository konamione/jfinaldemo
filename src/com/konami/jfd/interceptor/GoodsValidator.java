package com.konami.jfd.interceptor;

import com.jfinal.core.Controller;
import com.jfinal.validate.Validator;
import com.konami.jfd.vo.Goods;

public class GoodsValidator extends Validator {

	@Override
	protected void validate(Controller c) {
		validateRequiredString("goods.goodsname", "nameMsg", "请填写商品名称");
		validateRequiredString("goods.CostPrice", "costMsg", "请填写成本价格");
		validateRequiredString("goods.retailPrice", "retailMsg", "请填写零售价格");
	}

	@Override
	protected void handleError(Controller c) {
		Goods g = c.getModel(Goods.class);
		c.setAttr("goods", g);
		c.forwardAction("/goods");
		//c.render("/goods/index.html");
	}

}
