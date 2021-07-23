package alen.mj.base.dao.impl;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import alen.mj.base.dao.M03Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.M03;

public class M03DaoImpl implements M03Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public M03DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addM03(List<M03> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletM03(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (M03 entry : list) {
				Object[] value = new Object[] {
						entry.getM0300(), entry.getM0111(), entry.getB0100(), entry.getA0000(),
						entry.getSortID()
						};
				String sqlStr = "insert into M03(M0300, M0111, B0100, A0000, SortID) values(?, ?, ?, ?, ?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	@Override
	public void deletM03() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from M03");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletM03(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from M03 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
