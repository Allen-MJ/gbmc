package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A14Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A14;

public class A14DaoImpl implements A14Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A14DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA14(List<A14> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA14(B0111_key);
					
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A14 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA1404A(),entry.getA1404B(),entry.getA1407(),entry.getA1411A(),entry.getA1414(),entry.getA1415(),entry.getA1424(),entry.getA1428()
						};
				String sqlStr = "insert into A14(B0111_key, A0000, A1404A, A1404B, A1407, A1411A, A1414, A1415, A1424, A1428) values(?,?,?,?,?,?,?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA14() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A14");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA14(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A14 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
