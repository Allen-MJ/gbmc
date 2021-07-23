package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A14;

public interface A14Dao {

	// 数据添加
	public void addA14(List<A14> list, String B0111_key);
	// 数据清空
	public void deletA14();
	// 数据删除某名册
	public void deletA14(String B0111_key);
	
}
