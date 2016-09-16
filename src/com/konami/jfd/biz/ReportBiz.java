package com.konami.jfd.biz;

import java.util.List;

import com.konami.jfd.vo.Storage;

public class ReportBiz {
	public List<Storage> loadAllStorage(){
		return Storage.dao.find("select * from t_storage");
	}
	
	public List<Storage> loadStorageById(Long id){
		return Storage.dao.find("select * from t_storage where goodsid = ?", id);
	}
}
