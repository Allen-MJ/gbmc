package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A05Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A05;

public class A05DaoImpl implements A05Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A05DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA05(List<A05> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA05(B0111_key);
					
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A05 entry : list) {
				Object[] value = new Object[] { 
						B0111_key,
						entry.getA0000(),entry.getA0531(),entry.getA0501B(),entry.getA0504(),entry.getA0511(),entry.getA0517(),entry.getA0524()
						};
				String sqlStr = "insert into A05(B0111_key, A0000, A0531, A0501B, A0504, A0511, A0517, A0524) values(?,?,?,?,?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA05() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A05");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA05(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A05 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
