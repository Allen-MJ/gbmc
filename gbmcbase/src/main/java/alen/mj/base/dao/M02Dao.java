package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.M02;

public interface M02Dao {

	// 数据添加
	public void addM02(List<M02> list, String B0111_key);
	// 数据清空
	public void deletM02();
	// 数据删除某名册
	public void deletM02(String B0111_key);
	
}
