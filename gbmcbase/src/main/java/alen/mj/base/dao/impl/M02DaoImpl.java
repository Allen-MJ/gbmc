package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.M02Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.M02;

public class M02DaoImpl implements M02Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public M02DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addM02(List<M02> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletM02(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据   
			for (M02 entry : list) {
				Object[] value = new Object[] {
						entry.getM0200(), entry.getM0100(), entry.getB0100(), entry.getB0101(), 
						entry.getB0104(), entry.getSortID()
						};
				String sqlStr = "insert into M02(M0200, M0100, B0100, B0101, B0104, SortID) values(?, ?, ?, ?, ?, ?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	@Override
	public void deletM02() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from M02");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletM02(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from M02 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
