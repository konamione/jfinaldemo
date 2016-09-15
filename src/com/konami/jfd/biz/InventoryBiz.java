package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.MessageDto;

public class InventoryBiz {
	public List<Inventory> loadAllInv(){
		return Inventory.dao.find("select * from t_inventory");
	}
	
	public MessageDto addInv(Inventory inv){
		MessageDto msg = new MessageDto();
		inv.set("createtime", new Date());
		boolean b = inv.save();
		if (b) {
			msg.setMsgContent("添加成功");
		} else {
			msg.setMsgContent("添加失败");
		}
		msg.setMsgFlag(b);
		return msg;
	}
}
