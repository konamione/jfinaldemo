package com.konami.jfd.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.konami.jfd.controller.CashController;
import com.konami.jfd.controller.GoodsController;
import com.konami.jfd.controller.InventoryController;
import com.konami.jfd.controller.NevController;
import com.konami.jfd.controller.ProController;
import com.konami.jfd.controller.SellController;
import com.konami.jfd.controller.UserController;
import com.konami.jfd.interceptor.GloablInterceptor;
import com.konami.jfd.vo.Cash;
import com.konami.jfd.vo.Goods;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.Storage;
import com.konami.jfd.vo.User;

/**
 * 配置类  继承自JFinalConfig
 * @author konami
 *
 */
public class Config extends JFinalConfig{

	/**
	 * 配置系统常量及参数
	 */
	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("log4j.properties");
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(true);					//设置是否开发模式
		me.setViewType(ViewType.FREE_MARKER);	//设置视图模式
	}

	/**
	 * 配置控制器（controller）访问路由
	 */
	@Override
	public void configRoute(Routes me) {
		me.add("/user", UserController.class);
		me.add("/nev",NevController.class);
		me.add("/inv",InventoryController.class);
		me.add("/goods",GoodsController.class);
		me.add("/sell",SellController.class);
		me.add("/pro",ProController.class);
		me.add("/cash",CashController.class);
	}

	/**
	 * 配置第三方插件
	 */
	@Override
	public void configPlugin(Plugins me) {
		C3p0Plugin cp = new C3p0Plugin(loadPropertyFile("a_little_config.txt"));
		me.add(cp);
		ActiveRecordPlugin arp = new ActiveRecordPlugin(cp);
		me.add(arp);
		arp.addMapping("t_user", User.class);
		arp.addMapping("t_inventory", Inventory.class);
		arp.addMapping("t_goods", Goods.class);
		arp.addMapping("t_sell", Sell.class);
		arp.addMapping("t_storage", Storage.class);
		arp.addMapping("t_cash", Cash.class);
	}

	/**
	 * 配置拦截器
	 */
	@Override
	public void configInterceptor(Interceptors me) {
		me.add(new GloablInterceptor());
		me.add(new Tx());
	}

	/**
	 * 配置Handler
	 */
	@Override
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));//设置上下文路径
	}

}
