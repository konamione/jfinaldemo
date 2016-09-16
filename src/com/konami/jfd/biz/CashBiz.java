package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.konami.jfd.vo.Cash;
import com.konami.jfd.vo.MessageDto;

public class CashBiz {
	public List<Cash> loadAllCash(){
		return Cash.dao.find("select c.id as id, c.res as res, c.money as money, c.remark as remark, c.createtime as createtime, u.realname as cname from t_cash c left join t_user u on c.createid = u.id");
	}
	
	public MessageDto addCash(Cash c){
		c.set("createtime", new Date());
		MessageDto msg = new MessageDto();
		boolean b = c.save();
		if(b){
			msg.setMsgContent("添加成功");
		}else{
			msg.setMsgContent("添加成功");
		}
		msg.setMsgFlag(b);
		return msg;
	}
}
