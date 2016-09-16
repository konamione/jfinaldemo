package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.konami.jfd.biz.GoodsBiz;
import com.konami.jfd.biz.ReportBiz;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.Storage;

public class ReportController extends Controller {
	private GoodsBiz gb = new GoodsBiz();
	private ReportBiz rb = new ReportBiz();
	public void index(){
		
	}
	
	public void nav(){
		String para = getPara(0);
		if (para.equals("storage")) {
			forwardAction("/report/toStorage");
		} else if(para.equals("inventory")){
			renderFreeMarker("/report/inventory.html");
		} else if(para.equals("sell")){
			renderFreeMarker("/report/sell.html");
		} else if(para.equals("cash")){
			renderFreeMarker("/report/cash.html");
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
}
