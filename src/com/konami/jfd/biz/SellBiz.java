package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.Storage;

public class SellBiz {
	public List<Sell> loadAllSell() {
		return Sell.dao.find("select s.id as id, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rprice, s.goodsnum as goodsnum, s.goodsPrice as goodsPrice, s.finalPrice as finalPrice, case s.outType when 'sell' then '销售' when 'back' then '退货' end as outType, s.remark as remark, s.createtime as createTime, u.realname as rname from t_sell s left join t_goods g on s.goodsid = g.id left join t_user u on s.createid = u.id where outType in (?,?)","sell","back");
	}

	@Before(Tx.class)
	public MessageDto addSell(Sell s) {
		try {
			MessageDto msg = new MessageDto();
			List<Storage> stoList = Storage.dao.find(
					"select * from t_storage where goodsid = ?",
					Long.parseLong(s.getStr("goodsid")));
			if (stoList == null || stoList.size() == 0) {
				msg.setMsgFlag(false);
				msg.setMsgContent("销售的商品没有库存！请查证后再操作!");
			} else {
				Storage sto = stoList.get(0);
				if (s.getStr("outType").equals("sell")) {
					if (sto.getDouble("num") <= 0
							|| sto.getDouble("num") < Double.parseDouble(s.getStr("goodsnum"))) {
						msg.setMsgFlag(false);
						msg.setMsgContent("商品库存不足！无法进行本次销售!");
					} else {
//						if (s.getDate("createtime") == null) {
//							s.set("createtime", new Date());
//						}
						boolean b = s.save();
						sto.set("num",
								sto.getDouble("num") - Double.parseDouble(s.getStr("goodsnum")));
						boolean b1 = sto.update();
						if (b && b1) {
							msg.setMsgFlag(true);
							msg.setMsgContent("销售成功");
						} else {
							msg.setMsgFlag(false);
							msg.setMsgContent("销售失败");
						}
					}
				} else if (s.getStr("outType").equals("back")) {
					s.set("createtime", new Date());
					boolean b = s.save();
					Inventory inv = new Inventory();
					inv.set("goodsid", Long.parseLong(s.getStr("goodsid")));
					inv.set("goodsnum", Math.abs(Double.parseDouble(s.getStr("goodsnum"))));
					inv.set("inType", "back");
					inv.set("remark", s.getStr("remark"));
					inv.set("createid", Long.parseLong(s.getStr("createid")));
					inv.set("createtime", new Date());
					boolean b2 = inv.save();
					sto.set("num", sto.getDouble("num") + Math.abs(Double.parseDouble(s.getStr("goodsnum"))));
					boolean b1 = sto.update();
					if (b && b1 && b2) {
						msg.setMsgFlag(true);
						msg.setMsgContent("退货成功");
					} else {
						msg.setMsgFlag(false);
						msg.setMsgContent("退货失败");
					}
				}
			}
			return msg;
		} catch (Exception e) {
			throw new ActiveRecordException(e.getMessage());
		}
	}
}
