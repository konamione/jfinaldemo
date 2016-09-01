package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.core.Controller;
import com.konami.jfd.biz.UserBiz;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.User;

public class UserController extends Controller {
	
	private UserBiz ub = new UserBiz();
	
	public void index(){
		List<User> userList = ub.loadAllUser();
		setAttr("userList", userList);
		renderFreeMarker("index.html");
	}
	
	public void goAddUser(){
		String id = getPara(0);
		if (id != null) {
			setAttr("user", ub.findById(Integer.parseInt(id)));
			renderFreeMarker("modifyuser.html");
		} else {
			renderFreeMarker("adduser.html");
		}
	}
	
	public void doAddUser(){
		User u = getModel(User.class);
		MessageDto msg = ub.addUser(u);
		setAttr("msg", msg);
		renderFreeMarker("adduser.html");
	}
	
	public void deleteById(){
		int id = getParaToInt(0);
		MessageDto msg = ub.deleteUser(id);
		setAttr("msg", msg);
		forwardAction("/user");
	}
	
	public void updateUser(){
		User u = getModel(User.class);
		MessageDto msg = ub.modifyUser(u);
		setAttr("msg", msg);
		forwardAction("/user");
	}
}
