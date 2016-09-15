package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.Storage;

public class SellBiz {
	public List<Sell> loadAllSell(){
		return Sell.dao.find("select * from t_sell");
	}
	
	@Before(Tx.class)
	public MessageDto addSell(Sell s){
		MessageDto msg = new MessageDto();
		Storage sto = Storage.dao.find("select * from t_storage where goodsid = ?",s.getInt("goodsid")).get(0);
		if (sto == null) {
			msg.setMsgFlag(false);
			msg.setMsgContent("销售的商品不存在！请查证后再操作!");
		} else {
			if (s.getStr("outType").equals("sell")) {
				if(sto.getLong("num") <= 0 || sto.getLong("num") < s.getLong("goodsnum")){
					msg.setMsgFlag(false);
					msg.setMsgContent("商品库存不足！无法进行本次销售!");
				}else{
					s.set("createtime", new Date());
					boolean b = s.save();
					sto.set("num", sto.getLong("num") - s.getLong("goodsnum"));
					boolean b1 = sto.update();
					if(b && b1){
						msg.setMsgFlag(true);
						msg.setMsgContent("销售成功");
					}else{
						msg.setMsgFlag(false);
						msg.setMsgContent("销售失败");
					}
				}
			} else if(s.getStr("outType").equals("back")){
				s.set("createtime", new Date());
				boolean b = s.save();
				Inventory inv = new Inventory();
				inv.set("goodsid", s.getStr("goodsid"));
				inv.set("goodsnum", s.getLong("goodsnum"));
				inv.set("inType", "back");
				inv.set("remark", s.getStr("remark"));
				inv.set("createid", s.getLong("createid"));
				inv.set("createtime", new Date());
				boolean b2 = inv.save();
				sto.set("num", sto.getLong("num") + s.getLong("goodsnum"));
				boolean b1 = sto.update();
				if(b && b1 && b2){
					msg.setMsgFlag(true);
					msg.setMsgContent("退货成功");
				}else{
					msg.setMsgFlag(false);
					msg.setMsgContent("退货失败");
				}
			}
		}
		return msg;
	}
}
