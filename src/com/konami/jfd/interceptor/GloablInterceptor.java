package com.konami.jfd.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.konami.jfd.vo.User;

public class GloablInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		User u = inv.getController().getSessionAttr("currentUser");
		String actionKey = inv.getActionKey();
		if(u != null || (!actionKey.isEmpty() && actionKey.equals("/user/login"))){
			inv.invoke();
		}else{
			inv.getController().setAttr("fMsg", "请先登录!");
			inv.getController().renderFreeMarker("/user/login.html");
		}
//		System.out.println("走了");
//		Logger logger = Logger.getLogger(GloablInterceptor.class);
//		logger.debug(inv.getViewPath());
//		inv.invoke();
	}

}
