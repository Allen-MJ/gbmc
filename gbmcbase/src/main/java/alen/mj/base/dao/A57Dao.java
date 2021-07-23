package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A57;

public interface A57Dao {

	// 数据添加
	public void addA57(List<A57> list, String B0111_key);
	// 数据清空
	public void deletA57();
	// 数据删除某名册
	public void deletA57(String B0111_key);
	
}
