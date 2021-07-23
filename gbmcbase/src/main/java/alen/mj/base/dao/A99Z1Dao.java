package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A99Z1;

public interface A99Z1Dao {

	// 数据添加
	public void addA99Z1(List<A99Z1> list, String B0111_key);
	// 数据清空
	public void deletA99Z1();
	// 数据删除某名册
	public void deletA99Z1(String B0111_key);
	
}
