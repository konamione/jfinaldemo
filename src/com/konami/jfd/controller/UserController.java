package com.konami.jfd.controller;

import java.util.List;

import com.jfinal.aop.Before;
import com.jfinal.core.Controller;
import com.konami.jfd.biz.UserBiz;
import com.konami.jfd.interceptor.LoginValidator;
import com.konami.jfd.interceptor.PassValidator;
import com.konami.jfd.vo.MessageDto;
import com.konami.jfd.vo.User;

public class UserController extends Controller {
	
	private UserBiz ub = new UserBiz();
	
//	public void index(){
//		renderFreeMarker("login.html");
//	}
	
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
		u.set("createid", ((User)getSessionAttr("currentUser")).get("id"));
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
		u.set("modifyid", ((User)getSessionAttr("currentUser")).get("id"));
		MessageDto msg = ub.modifyUser(u);
		setAttr("msg", msg);
		forwardAction("/user");
	}
	
	@Before(LoginValidator.class)
	public void login(){
		User u = getModel(User.class);
		User currentUser = ub.login(u);
		if (currentUser != null) {
			setSessionAttr("currentUser", currentUser);
			String welcomeMsg = "欢迎您：" + currentUser.getStr("realname");
			setAttr("welcome", welcomeMsg);
			forwardAction("/nev/main");
			//renderFreeMarker("menus.html");
		} else {
			String fMsg = "用户名或密码错误!";
			setAttr("fMsg",fMsg);
			renderFreeMarker("login.html");
		}
	}
	
	public void toModifyPass(){
		renderFreeMarker("modifyPass.html");
	}
	
	@Before(PassValidator.class)
	public void doModifyPass(){
		String oldPass = getPara("oldPass");
		String newPass = getPara("newPass");
		String reNewPass = getPara("reNewPass");
		User u = (User)getSession().getAttribute("currentUser");
		if (u.getStr("userpass").equals(oldPass)) {
			MessageDto msg = ub.modifyPass(newPass, u);
			setAttr("msg", msg);
			forwardAction("/nev/main");
		} else {
			setAttr("oldMsg", "旧密码输入错误");
			renderFreeMarker("modifyPass.html");
		}
	}
}
