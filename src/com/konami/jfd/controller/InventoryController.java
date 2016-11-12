package com.konami.jfd.controller;

import java.util.List;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Record;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.biz.InventoryBiz;
import com.konami.jfd.biz.SysUtil;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.Inventory;

public class InventoryController extends Controller {
	private InventoryBiz ib = new InventoryBiz();
	private GoodsBiz gb = new GoodsBiz();
	public void index(){
		List<Record> invList = ib.loadAllInv();
		List<Goods> goodsList = gb.loadAll();
		setAttr("invList", invList);
		setAttr("goodsList", goodsList);
		renderFreeMarker("index.html");
	}
	
	@SuppressWarnings("unchecked")
	public void addInv(){
		Model<Inventory>[] invs = (Model<Inventory>[])SysUtil.packageModelArray(this, new Inventory());
		for (int i = 0; i < invs.length; i++) {
			ib.addInv((Inventory)invs[i]);
			//invs[i].save();
		}
		forwardAction("/inv");
	}
}
