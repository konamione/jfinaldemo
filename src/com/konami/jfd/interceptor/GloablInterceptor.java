package com.konami.jfd.interceptor;

import org.apache.log4j.Logger;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class GloablInterceptor implements Interceptor{

	@Override
	public void intercept(Invocation inv) {
		Logger logger = Logger.getLogger(GloablInterceptor.class);
		logger.debug(inv.getViewPath());
		inv.invoke();
	}

}
