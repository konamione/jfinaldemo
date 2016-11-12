package com.konami.jfd.biz;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.konami.jfd.vo.User;

public class SysUtil {
	
	@SuppressWarnings("rawtypes")
	public static Object[] packageModelArray(Controller c, Model m){
		Model<?>[] obj;
		String className = m.getClass().getSimpleName();
		className = className.toLowerCase();
		String fullName = m.getClass().getName();
		Map<String, String[]> paraMap = c.getParaMap();
		Set<String> paraKeySet = paraMap.keySet();
		Object[] paraKeyArray = paraKeySet.toArray();
		int maxLength = 0;
		for (int i = 0; i < paraKeyArray.length; i++) {
			if(paraMap.get(paraKeyArray[i].toString()).length > maxLength){
				maxLength = paraMap.get(paraKeyArray[i].toString()).length;
			}
		}
		obj = new Model<?>[maxLength];
		for(int i = 0; i < maxLength; i++){
			try {
				obj[i] = (Model<?>)Class.forName(fullName).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < paraKeySet.size(); i++) {
			if(paraKeyArray[i].toString().startsWith(className)){
				String[] values = paraMap.get(paraKeyArray[i].toString());
				if (paraKeyArray[i].toString().endsWith("createtime")) {
					for (int j = 0; j < values.length; j++) {
						if (values[j] == "") {
							obj[j].set(paraKeyArray[i].toString().split("\\.")[1], new Date());
						} else {
							obj[j].set(paraKeyArray[i].toString().split("\\.")[1], values[j]);
						}
					}
				} else {
					for (int j = 0; j < values.length; j++) {
						obj[j].set(paraKeyArray[i].toString().split("\\.")[1], values[j]);
					}
				}
			}
		}
		for (int i = 0; i < obj.length; i++) {
			obj[i].set("createid", ((User)(c.getSession().getAttribute("currentUser"))).get("id"));
		}
		return obj;
	}
}
