package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A06;

public interface A06Dao {

	// 数据添加
	public void addA06(List<A06> list, String B0111_key);
	// 数据清空
	public void deletA06();
	// 数据删除某名册
	public void deletA06(String B0111_key);
	
}
