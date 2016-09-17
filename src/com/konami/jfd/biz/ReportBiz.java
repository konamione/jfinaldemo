package com.konami.jfd.biz;

import java.util.List;

import com.konami.jfd.vo.Storage;

public class ReportBiz {
	public List<Storage> loadAllStorage(){
		return Storage.dao.find("select s.id as id, s.num as num, s.createtime as ctime, g.id as gid, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rPrice from t_storage s left join t_goods g on s.goodsid = g.id");
	}
	
	public List<Storage> loadStorageById(Long id){
		return Storage.dao.find("select s.id as id, s.num as num, s.createtime as ctime, g.id as gid, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rPrice from t_storage s left join t_goods g on s.goodsid = g.id where goodsid = ?", id);
	}
}
