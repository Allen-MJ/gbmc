package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A08Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A08;

public class A08DaoImpl implements A08Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A08DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA08(List<A08> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA08(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A08 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA0801A(),entry.getA0801B(),entry.getA0901A(),entry.getA0901B(),entry.getA0804(),
						entry.getA0807(),entry.getA0904(),entry.getA0814(),entry.getA0824(),
						entry.getA0827(),entry.getA0834(),entry.getA0835(),entry.getA0837(),entry.getA0811(),entry.getA0899()
						};
				String sqlStr = "insert into A08(B0111_key, A0000, A0801A, A0801B, A0901A, A0901B, A0804, A0807, A0904, A0814, A0824, A0827, A0834, A0835, A0837, A0811, A0899) values(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA08() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A08");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA08(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A08 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
