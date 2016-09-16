package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.biz.ProBiz;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.User;

public class ProController extends Controller {
	private ProBiz pb = new ProBiz();
	private GoodsBiz gb = new GoodsBiz();
	public void index(){
		List<Sell> sellList = pb.loadAllSell();
		List<Goods> goodsList = gb.loadAll();
		setAttr("sellList", sellList);
		setAttr("goodsList", goodsList);
		renderFreeMarker("/pro/index.html");
	}
	
	public void addPro(){
		Sell s = getModel(Sell.class);
		s.set("createid", ((User)getSession().getAttribute("currentUser")).get("id"));
		MessageDto msg = pb.addSell(s);
		setAttr("msg", msg);
		forwardAction("/pro");
	}
}
