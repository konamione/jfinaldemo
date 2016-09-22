package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.Storage;

public class InventoryBiz {
//	public List<Inventory> loadAllInv(){
//		return Inventory.dao.find("select * from t_inventory");
//	}
	public List<Record> loadAllInv(){
		return Db.find("select inv.id as id, inv.goodsid as gid, g.goodsname as gname, g.goodsspec as spec, inv.goodsnum as gnum, case inv.inType when 'buy' then '进货' when 'back' then '销售退货' when 'proback' then '铺货退货' END as type, inv.remark as remark, inv.createid as cid, inv.createtime as ctime, u.realname as rname from t_inventory inv left join t_user u on inv.createid = u.id left join t_goods g on g.id = inv.goodsid");
	}
	
	@Before(Tx.class)
	public MessageDto addInv(Inventory inv){
		try {
			MessageDto msg = new MessageDto();
			if (inv.getDate("createtime") == null) {
				inv.set("createtime", new Date());
			}
			boolean b = inv.save();
			boolean b1 = false;
			Storage s = null;
			List<Storage> sList = Storage.dao.find("select * from t_storage where goodsid = ?",inv.getLong("goodsid"));
			if (sList == null || sList.size() == 0) {
				s = new Storage();
				s.set("goodsid", inv.getLong("goodsid"));
				s.set("num", inv.getDouble("goodsnum"));
				s.set("createtime", new Date());
				b1 = s.save();
			} else {
				s = sList.get(0);
				s.set("num", s.getDouble("num") + inv.getDouble("goodsnum"));
				s.set("createtime", new Date());
				b1 = s.update();
			}
			if (b && b1) {
				msg.setMsgContent("添加成功");
			} else {
				msg.setMsgContent("添加失败");
			}
			msg.setMsgFlag(b);
			return msg;
		} catch (Exception e) {
			throw new ActiveRecordException(e.getMessage());
		}
	}
}
