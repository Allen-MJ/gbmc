package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A99Z1Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A99Z1;

public class A99Z1DaoImpl implements A99Z1Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A99Z1DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA99Z1(List<A99Z1> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA99Z1(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A99Z1 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000()
						};
				String sqlStr = "insert into A99Z1(B0111_key, A0000) values(?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA99Z1() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A99Z1");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA99Z1(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A99Z1 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
