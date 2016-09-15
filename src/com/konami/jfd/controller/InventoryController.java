package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Record;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.biz.InventoryBiz;
import com.konami.jfd.interceptor.InventoryValidator;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.User;

public class InventoryController extends Controller {
	private InventoryBiz ib = new InventoryBiz();
	private GoodsBiz gb = new GoodsBiz();
	public void index(){
//		List<Inventory> invList = ib.loadAllInv();
		List<Record> invList = ib.loadAllInv();
		List<Goods> goodsList = gb.loadAll();
		setAttr("invList", invList);
		setAttr("goodsList", goodsList);
		//createToken("blogToken", 30*60);
		renderFreeMarker("index.html");
	}
	
	@Before(InventoryValidator.class)
	public void addInv(){
		Inventory inv = getModel(Inventory.class);
		inv.set("createid", ((User)getSession().getAttribute("currentUser")).get("id"));
		setAttr("msg", ib.addInv(inv));
		forwardAction("/inv");
	}
}
