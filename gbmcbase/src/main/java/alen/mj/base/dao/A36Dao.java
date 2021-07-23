package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A36;

public interface A36Dao {

	// 数据添加
	public void addA36(List<A36> list, String B0111_key);
	// 数据清空
	public void deletA36();
	// 数据删除某名册
	public void deletA36(String B0111_key);
	public List<A36> getPersonFamily(String id);
}
