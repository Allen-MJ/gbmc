package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A15;

public interface A15Dao {

	// 数据添加
	public void addA15(List<A15> list, String B0111_key);
	// 数据清空
	public void deletA15();
	// 数据删除某名册
	public void deletA15(String B0111_key);
	
}
