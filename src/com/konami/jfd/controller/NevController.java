package com.konami.jfd.controller;

import com.jfinal.core.Controller;
import com.konami.jfd.vo.User;

public class NevController extends Controller{
	public void index(){
		String para = getPara(0);
		if (para.equals("user")) {
			forwardAction("/user");
		} else if(para.equals("inventory")){
			forwardAction("/inv");
		} else if(para.equals("sell")){
			forwardAction("/sell");
		} else if(para.equals("pay")){
			forwardAction("/cash");
		} else if(para.equals("report")){
			forwardAction("/report");
		} else if(para.equals("exit")){
			exitSys();
		} else if(para.equals("main")){
			setAttr("currentUser", (User)getSession().getAttribute("currentUser"));
			renderFreeMarker("menus.html");
		} else if(para.equals("goods")){
			forwardAction("/goods");
		} else if(para.equals("pro")){
			forwardAction("/pro");
		}
	}
	
	private void exitSys(){
		removeSessionAttr("currentUser");
		renderFreeMarker("/user/login.html");
	}
}
