package com.konami.jfd.controller;

import com.jfinal.core.Controller;

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
			forwardAction("/pay");
		} else if(para.equals("report")){
			forwardAction("/report");
		} else if(para.equals("exit")){
			exitSys();
		} else if(para.equals("main")){
			renderFreeMarker("menus.html");
		}
	}
	
	private void exitSys(){
		removeSessionAttr("currentUser");
		renderFreeMarker("/user/login.html");
	}
}
