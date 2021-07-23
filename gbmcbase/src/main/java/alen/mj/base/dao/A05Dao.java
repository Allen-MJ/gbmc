package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A05;

public interface A05Dao {

	// 数据添加
	public void addA05(List<A05> list, String B0111_key);
	// 数据清空	
	public void deletA05();
	// 数据删除某名册
	public void deletA05(String B0111_key);
	
}
