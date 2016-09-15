package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.MessageDto;

public class GoodsBiz {
	public List<Goods> loadAll(){
		return Goods.dao.find("select * from t_goods");
	}
	public List<Record> loadAllGoods(){
		return Db.find("select g.id as id,g.goodsname as name,g.goodsspec as spec,g.CostPrice as cost,g.retailPrice retail,g.createtime as ctime,g.modifytime as mtime,g.createid as cid,g.modifyid as mid,u.realname as rname from t_goods g left join t_user u on g.createid = u.id or g.modifyid = u.id");
	}
	
	public MessageDto addGoods(Goods g){
		MessageDto msg = new MessageDto();
		g.set("createtime", new Date());
		boolean b = false;
		Goods eg = g.findFirst("select * from t_goods where goodsname like '%" + g.getStr("goodsname") + "%'");
		//Goods eg = g.findFirst("select * from t_goods where goodsname like '%?%'",g.getStr("goodsname"));
		if (eg == null && g.getStr("goodsname") != null) {
			b = g.save();
			if (b) {
				msg.setMsgContent("添加成功");
			} else {
				msg.setMsgContent("添加失败");
			}
		} else {
			msg.setMsgContent("要添加的商品已存在，请核实后再操作！");
		}
		msg.setMsgFlag(b);
		return msg;
	}
	
	public Goods findById(int id){
		return Goods.dao.findById(id);
	}
	
	public MessageDto modifyGoods(Goods g){
		MessageDto msg = new MessageDto();
		g.set("createtime", new Date());
		boolean b = g.update();
		if (b) {
			msg.setMsgContent("修改成功");
		} else {
			msg.setMsgContent("修改失败");
		}
		msg.setMsgFlag(b);
		return msg;
	}
}
