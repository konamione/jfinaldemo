package com.konami.jfd.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.User;

public class GoodsController extends Controller {
	private GoodsBiz gb = new GoodsBiz();
	public void index(){
		List<Goods> goodsList = gb.loadAllGoods();
		setAttr("goodsList", goodsList);
		setAttr("msg", null);
		renderFreeMarker("/goods/index.html");
	}
	
	public void addgoods(){
		Goods g = getModel(Goods.class);
		g.set("createid", ((User)getSession().getAttribute("currentUser")).get("id"));
		g.set("goodsspec", "斤");
		MessageDto msg = gb.addGoods(g);
		setAttr("msg", msg);
		forwardAction("/goods");
		//renderFreeMarker("/goods/index.html");
	}
	
	public void modifyGoods(){
		int id = getParaToInt(0);
		setAttr("goods", gb.findById(id));
		renderFreeMarker("/goods/modify.html");
	}
	
	public void doModifyGoods(){
		Goods g = getModel(Goods.class);
		g.set("modifytime", new Date());
		g.set("modifyid", ((User)getSession().getAttribute("currentUser")).get("id"));
		g.set("goodsspec", "斤");
		MessageDto msg = gb.modifyGoods(g);
		setAttr("msg", msg);
		forwardAction("/goods");
	}
}