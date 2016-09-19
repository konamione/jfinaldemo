package com.konami.jfd.biz;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.konami.jfd.vo.Cash;
import com.konami.jfd.vo.Inventory;
import com.konami.jfd.vo.Sell;
import com.konami.jfd.vo.Storage;

public class ReportBiz {
	public List<Storage> loadAllStorage() {
		return Storage.dao
				.find("select s.id as id, s.num as num, s.createtime as ctime, g.id as gid, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rPrice from t_storage s left join t_goods g on s.goodsid = g.id");
	}

	public List<Storage> loadStorageById(Long id) {
		return Storage.dao
				.find("select s.id as id, s.num as num, s.createtime as ctime, g.id as gid, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rPrice from t_storage s left join t_goods g on s.goodsid = g.id where goodsid = ?",
						id);
	}

	public List<Inventory> loadAllInv() {
		return Inventory.dao
				.find("select inv.id as id, inv.goodsid as gid, g.goodsname as gname, g.goodsspec as spec, inv.goodsnum as gnum, case inv.inType when 'buy' then '进货' when 'back' then '销售退货' when 'proback' then '铺货退货' END as type, inv.remark as remark, inv.createid as cid, inv.createtime as ctime, u.realname as rname from t_inventory inv left join t_user u on inv.createid = u.id left join t_goods g on g.id = inv.goodsid");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Inventory> loadInvByPara(Inventory inv, Date ft, Date tt) {
		String sql = "select inv.id as id, inv.goodsid as gid, g.goodsname as gname, g.goodsspec as spec, inv.goodsnum as gnum, case inv.inType when 'buy' then '进货' when 'back' then '销售退货' when 'proback' then '铺货退货' END as type, inv.remark as remark, inv.createid as cid, inv.createtime as ctime, u.realname as rname from t_inventory inv left join t_user u on inv.createid = u.id left join t_goods g on g.id = inv.goodsid where 1=1";
		List paras = new ArrayList();
		if (inv.getLong("goodsid") != 0) {
			sql += " and inv.goodsid = ?";
			paras.add(inv.getLong("goodsid"));
		} else if (!inv.getStr("inType").equals("0")) {
			sql += " and inv.inType = ?";
			paras.add(inv.getStr("inType"));
		} else if (inv.getStr("remark") != null) {
			sql += " and inv.remark like ?";
			String pa = "%" + inv.getStr("remark") + "%";
			paras.add(pa);
		} else if (ft != null && tt == null) {
			sql += " and inv.createtime > ?";
			paras.add(ft);
		} else if (ft == null && tt != null) {
			sql += " and inv.createtime < ?";
			paras.add(tt);
		} else if (ft != null && tt != null) {
			sql += " and inv.createtime between ? and ?";
			paras.add(ft);
			paras.add(tt);
		} else if (inv.getLong("createid") != 0) {
			sql += " and inv.createid = ?";
			paras.add(inv.getLong("createid"));
		}
		return Inventory.dao.find(sql, paras.toArray());
	}

	public List<Sell> loadAllSell() {
		// return
		// Sell.dao.find("select s.id as id, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rprice, s.goodsnum as goodsnum, s.goodsPrice as goodsPrice, s.finalPrice as finalPrice, case s.outType when 'sell' then '销售' when 'back' then '退货' end as outType, s.remark as remark, s.createtime as createTime, u.realname as rname from t_sell s left join t_goods g on s.goodsid = g.id left join t_user u on s.createid = u.id where outType in (?,?)","sell","back");
		return Sell.dao
				.find("select s.id as id, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rprice, s.goodsnum as goodsnum, s.goodsPrice as goodsPrice, s.finalPrice as finalPrice, case s.outType when 'sell' then '销售' when 'back' then '退货' when 'pro' then '铺货' when 'proback' then '铺货退货' end as outType, s.custname as custname, s.remark as remark, s.createtime as createTime, u.realname as rname from t_sell s left join t_goods g on s.goodsid = g.id left join t_user u on s.createid = u.id");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Sell> loadSellByPara(Sell s, Date ft, Date tt) {
		String sql = "select s.id as id, g.goodsname as gname, g.goodsspec as gspec, g.CostPrice as cprice, g.retailPrice as rprice, s.goodsnum as goodsnum, s.goodsPrice as goodsPrice, s.finalPrice as finalPrice, case s.outType when 'sell' then '销售' when 'back' then '退货' when 'pro' then '铺货' when 'proback' then '铺货退货' end as outType, s.custname as custname, s.remark as remark, s.createtime as createTime, u.realname as rname from t_sell s left join t_goods g on s.goodsid = g.id left join t_user u on s.createid = u.id where 1=1";
		List paras = new ArrayList();
		if (s.getLong("goodsid") != 0) {
			sql += " and s.goodsid = ?";
			paras.add(s.getLong("goodsid"));
		} else if (!s.getStr("outType").equals("0")) {
			sql += " and s.outType = ?";
			paras.add(s.getStr("outType"));
		} else if (s.getStr("remark") != null) {
			sql += " and s.remark like ?";
			String pa = "%" + s.getStr("remark") + "%";
			paras.add(pa);
		} else if (ft != null && tt == null) {
			sql += " and s.createtime > ?";
			paras.add(ft);
		} else if (ft == null && tt != null) {
			sql += " and s.createtime < ?";
			paras.add(tt);
		} else if (ft != null && tt != null) {
			sql += " and s.createtime between ? and ?";
			paras.add(ft);
			paras.add(tt);
		} else if (s.getLong("createid") != 0) {
			sql += " and s.createid = ?";
			paras.add(s.getLong("createid"));
		}
		return Sell.dao.find(sql, paras.toArray());
	}

	public List<Cash> loadAllCash() {
		return Cash.dao.find("select c.id as id, c.res as res, c.money as money, c.remark as remark, c.createtime as createtime, u.realname as rname from t_cash c left join t_user u on c.createid = u.id");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Cash> loadCashByPara(Cash c, Date ft, Date tt) {
		String sql = "select c.id as id, c.res as res, c.money as money, c.remark as remark, c.createtime as createtime, u.realname as rname from t_cash c left join t_user u on c.createid = u.id where 1 = 1";
		List paras = new ArrayList();
		if (c.getStr("res") != null && !c.getStr("res").isEmpty()) {
			sql += " and c.res like ?";
			paras.add("%" + c.getStr("res") + "%");
		} else if(c.getStr("remark") != null && !c.getStr("remark").isEmpty()){
			sql += " and c.remark like ?";
			paras.add("%" + c.getStr("remark") + "%");
		} else if(c.getLong("createid") != 0){
			sql += " and c.createid = ?";
			paras.add(c.getLong("createid"));
		} else if(ft != null && tt == null){
			sql += " and c.createtime > ?";
			paras.add(ft);
		} else if(ft == null && tt != null){
			sql += " and c.createtime < ?";
			paras.add(tt);
		} else if(ft != null && tt != null){
			sql += " and c.createtime between ? and ?";
			paras.add(ft);
			paras.add(tt);
		}
		return Cash.dao.find(sql,paras.toArray());
	}
}
