package com.konami.jfd.biz;

import java.util.List;

import com.konami.jfd.vo.Inventory;

public class InventoryBiz {
	public List<Inventory> loadAllInv(){
		return Inventory.dao.find("select * from t_inventory");
	}
}
