package alen.mj.base.dao;

import java.util.List;

import alen.mj.base.entry.A01;
import alen.mj.base.entry.M01;

public interface A01Dao {

	// 数据添加
	public void addA01(List<A01> list, String B0111_key);
	// 数据清空
	public void deletA01();
	// 数据删除某名册
	public void deletA01(String B0111_key);
	/**
	 * 获取单位人员名册
	 * @param code
	 * @param id
	 * @param key
	 * @return
	 */
	public List<M01> getPerMcList(String code, String id, String key, String type);
	public A01 getPersonInfo(String id);
	
	// 选择前多少条
	public List<M01> getPerMcTopList(String code, int top, String type);
	
}
