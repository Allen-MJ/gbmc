package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A06Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A06;

public class A06DaoImpl implements A06Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A06DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA06(List<A06> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA06(B0111_key);
					
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A06 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA0602(),entry.getA0601(),entry.getA0604(),entry.getA0607(),entry.getA0611(),entry.getA0699()
						};
				String sqlStr = "insert into A06(B0111_key, A0000, A0602, A0601, A0604, A0607, A0611, A0699) values(?,?,?,?,?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA06() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A06");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA06(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A06 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
