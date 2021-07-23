package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.B01;
import alen.mj.base.entry.Tree;
import alen.mj.base.entry.UnitslevelEntry;

public interface B01Dao {

	// 数据添加
	public void addB01(List<B01> list, String B0111_key);
	// 数据清空
	public void deletB01();
	// 数据删除某名册
	public void deletB01(String B0111_key);
	
	public List<Tree> getDwList(String code, String type);
	public List<Tree> getDwList(String ccode, String code, String type);
	public List<UnitslevelEntry> getUnitsList(String code, String type, int levels);
}
