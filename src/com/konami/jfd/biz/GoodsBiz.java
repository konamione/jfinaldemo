package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.MessageDto;

public class GoodsBiz {
	public List<Goods> loadAllGoods(){
		return Goods.dao.find("select * from t_goods");
	}
	
	public MessageDto addGoods(Goods g){
		MessageDto msg = new MessageDto();
		g.set("createtime", new Date());
		boolean b = g.save();
		if (b) {
			msg.setMsgContent("添加成功");
		} else {
			msg.setMsgContent("添加失败");
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
