package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.M03;

public interface M03Dao {

	// 数据添加
	public void addM03(List<M03> list, String B0111_key);
	// 数据清空
	public void deletM03();
	// 数据删除某名册
	public void deletM03(String B0111_key);
	
}
