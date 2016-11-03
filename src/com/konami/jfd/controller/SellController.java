package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.biz.SellBiz;
import com.konami.jfd.interceptor.SellValidator;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.User;

public class SellController extends Controller {
	private SellBiz sb = new SellBiz();
	private GoodsBiz gb = new GoodsBiz();
	public void index(){
		List<Sell> sellList = sb.loadAllSell();
		List<Goods> goodsList = gb.loadAll();
		setAttr("sellList", sellList);
		setAttr("goodsList", goodsList);
		renderFreeMarker("/sell/index.html");
	}
	
	@Before(SellValidator.class)
	public void addSell(){
		Sell s = getModel(Sell.class);
		s.set("createid", ((User)getSession().getAttribute("currentUser")).get("id"));
		MessageDto msg = sb.addSell(s);
		setAttr("msg", msg);
		forwardAction("/sell");
	}
}
