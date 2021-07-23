package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A02;

public interface A02Dao {

	// 数据添加
	public void addA02(List<A02> list, String B0111_key);
	// 数据清空
	public void deletA02();
	// 数据删除某名册
	public void deletA02(String B0111_key);
	
}
