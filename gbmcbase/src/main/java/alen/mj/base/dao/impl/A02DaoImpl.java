package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A02Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A02;

public class A02DaoImpl implements A02Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A02DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA02(List<A02> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA02(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A02 entry : list) {
				Object[] value = new Object[] {B0111_key,
						entry.getA0000(),entry.getA0201A(),entry.getA0201B(),entry.getA0201D(),entry.getA0201E(),
						entry.getA0215A(),entry.getA0219(),entry.getA0223(),entry.getA0225(),entry.getA0243(),
						entry.getA0245(),entry.getA0247(),entry.getA0251B(),entry.getA0255(),entry.getA0265(),
						entry.getA0267(),entry.getA0272(),entry.getA0281(),entry.getA0279(),entry.getA0215B()
						};
//				if("00E6A977-3737-4381-AAA9-BFC7E47D4826".equals(entry.getA0000())){
//					System.out.println("梁晓艳 ===>>" + entry.getA0215A());
//				}
				
				String sqlStr = "insert into A02(B0111_key, A0000, A0201A, A0201B, A0201D, A0201E, A0215A, A0219, A0223, A0225, A0243, A0245, A0247, A0251B,"
						+ "A0255, A0265, A0267, A0272, A0281, A0279, A0215B) values(?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA02() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A02");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA02(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A02 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
