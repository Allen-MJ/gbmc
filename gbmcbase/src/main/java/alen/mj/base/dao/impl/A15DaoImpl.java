package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A15Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A15;

public class A15DaoImpl implements A15Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A15DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA15(List<A15> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA15(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A15 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA1517(),entry.getA1521()
						};
				String sqlStr = "insert into A15(B0111_key, A0000, A1517, A1521) values(?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA15() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A15");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA15(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A15 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
