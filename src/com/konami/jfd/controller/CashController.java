package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.konami.jfd.biz.CashBiz;
import com.konami.jfd.interceptor.CashValidator;
import com.konami.jfd.vo.Cash;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.User;

public class CashController extends Controller {
	CashBiz cb = new CashBiz();
	public void index(){
		List<Cash> cashList = cb.loadAllCash();
		setAttr("cashList", cashList);
		renderFreeMarker("/cash/index.html");
	}
	
	@Before(CashValidator.class)
	public void addCash(){
		Cash c = getModel(Cash.class);
		c.set("createid", ((User)getSession().getAttribute("currentUser")).getLong("id"));
		MessageDto msg = cb.addCash(c);
		setAttr("msg", msg);
		forwardAction("/cash");
	}
}
