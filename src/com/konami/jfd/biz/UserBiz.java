package com.konami.jfd.biz;

import java.util.Date;
import java.util.List;

import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.User;

public class UserBiz {

	public List<User> loadAllUser(){
		return User.dao.find("select * from t_user");
	}
	
	public MessageDto addUser(User u){
		MessageDto msg = new MessageDto();
		u.set("createtime", new Date());
		boolean b = u.save();
		if (b) {
			msg.setMsgContent("添加成功");
		} else {
			msg.setMsgContent("添加失败");
		}
		msg.setMsgFlag(b);
		return msg;
	}
	
	public MessageDto deleteUser(int id){
		MessageDto msg = new MessageDto();
		boolean b = User.dao.deleteById(id);
		if (b) {
			msg.setMsgContent("删除成功");
		} else {
			msg.setMsgContent("删除失败");
		}
		msg.setMsgFlag(b);
		return msg;
	}
	
	public User findById(int id){
		return User.dao.findById(id);
	}
	
	public MessageDto modifyUser(User u){
		MessageDto msg = new MessageDto();
		u.set("modifytime", new Date());
		boolean b = u.update();
		if (b) {
			msg.setMsgContent("修改成功");
		} else {
			msg.setMsgContent("修改失败");
		}
		msg.setMsgFlag(b);
		return msg;
	}
}
