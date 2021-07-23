package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.gwyinfo;

public interface GwyinfoDao {

	// 数据添加
	public void addGwyinfo(gwyinfo entry);
	// 数据清空
	public void deletGwyinfo();
	// 数据删除某名册
	public void deletGwyinfo(String B0111_key);
	
	public List<gwyinfo> getMcList();
	public List<gwyinfo> getMcList(String code);
	public void deleteAllData();
}
