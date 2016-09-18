package com.konami.jfd.controller;

import java.util.Date;
import java.util.List;

import com.jfinal.core.Controller;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.biz.ReportBiz;
import com.konami.jfd.biz.UserBiz;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.Storage;
import com.konami.jfd.vo.User;

public class ReportController extends Controller {
	private GoodsBiz gb = new GoodsBiz();
	private ReportBiz rb = new ReportBiz();
	private UserBiz ub = new UserBiz();
	public void index(){
		
	}
	
	public void nav(){
		String para = getPara(0);
		if (para.equals("storage")) {
			forwardAction("/report/toStorage");
		} else if(para.equals("inventory")){
			forwardAction("/report/toInventory");
		} else if(para.equals("sell")){
			forwardAction("/report/toSell");
		} else if(para.equals("cash")){
			forwardAction("/report/toCash");
		}
	}
	
	public void toStorage(){
		Storage s = getModel(Storage.class);
		List<Storage> stoList = null;
		if (s == null || s.getLong("goodsid") == null || s.getLong("goodsid") == 0) {
			stoList = rb.loadAllStorage();
		} else {
			stoList = rb.loadStorageById(s.getLong("goodsid"));
		}
		setAttr("stoList", stoList);
		List<Goods> goodsList = gb.loadAll();
		setAttr("goodsList", goodsList);
		renderFreeMarker("/report/storage.html");
	}
	
	public void toInventory(){
		Inventory inv = getModel(Inventory.class);
		Date ft =  getParaToDate("createtimefrom");
		Date tt =  getParaToDate("createtimeto");
		List<Inventory> invList = null;
		if (inv.getStr("inType") == null) {
			invList = rb.loadAllInv();
		}else if (inv.getLong("goodsid") == 0 && inv.getStr("inType").equals("0") && inv.getStr("remark") == null && inv.getLong("createid") == 0 && ft == null && tt == null) {
			invList = rb.loadAllInv();
		} else {
			invList = rb.loadInvByPara(inv,ft,tt);
		}
		List<Goods> goodsList = gb.loadAll();
		List<User> userList = ub.loadAllUser();
		setAttr("goodsList", goodsList);
		setAttr("invList", invList);
		setAttr("userList", userList);
		renderFreeMarker("/report/inventory.html");
	}
	
	public void toSell(){
		Sell sell = getModel(Sell.class);
		Date ft =  getParaToDate("createtimefrom");
		Date tt =  getParaToDate("createtimeto");
		List<Sell> sellList = null;
		if (sell.getStr("outType") == null) {
			sellList = rb.loadAllSell();
		}else if (sell.getLong("goodsid") == 0 && sell.getStr("outType").equals("0") && sell.getStr("remark") == null && sell.getLong("createid") == 0 && ft == null && tt == null) {
			sellList = rb.loadAllSell();
		} else {
			sellList = rb.loadSellByPara(sell,ft,tt);
		}
		List<Goods> goodsList = gb.loadAll();
		List<User> userList = ub.loadAllUser();
		setAttr("goodsList", goodsList);
		setAttr("sellList", sellList);
		setAttr("userList", userList);
		renderFreeMarker("/report/sell.html");
	}
}
