package com.konami.jfd.controller;

import java.util.List;
import com.jfinal.core.Controller;
import com.konami.jfd.biz.InventoryBiz;
import com.konami.jfd.vo.Inventory;

public class InventoryController extends Controller {
	private InventoryBiz ib = new InventoryBiz();
	
	public void index(){
		List<Inventory> invList = ib.loadAllInv();
		setAttr("invList", invList);
		renderFreeMarker("index.html");
	}
}
