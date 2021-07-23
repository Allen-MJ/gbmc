package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A08;

public interface A08Dao {

	// 数据添加
	public void addA08(List<A08> list, String B0111_key);
	// 数据清空
	public void deletA08();
	// 数据删除某名册
	public void deletA08(String B0111_key);
	
}
