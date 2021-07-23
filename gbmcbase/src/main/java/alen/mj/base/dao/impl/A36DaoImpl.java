package alen.mj.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A36Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.db.SqlHelper;
import alen.mj.base.entry.A36;
import alen.mj.base.utils.Constants;

public class A36DaoImpl implements A36Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A36DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA36(List<A36> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA36(B0111_key);
					
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A36 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA3601(),entry.getA3604A(),entry.getA3607(),entry.getA3627(),entry.getA3611(),entry.getSORTID()
						};
				String sqlStr = "insert into A36(B0111_key, A0000, A3601, A3604A, A3607, A3627, A3611, SORTID) values(?,?,?,?,?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletA36() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A36");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA36(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A36 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public List<A36> getPersonFamily(String id) {
		List<A36> qt = new ArrayList<A36>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getPersonFamily(id), null);
		while (cursor.moveToNext()) {
			A36 m = new A36();
			String dw = cursor.getString(cursor.getColumnIndex("A3611"));
			m.setA3601(cursor.getString(cursor.getColumnIndex("A3601")));
			m.setA3604A(cursor.getString(cursor.getColumnIndex("A3604A")));
			m.setA3607(Constants.ageOfdate(cursor.getString(cursor.getColumnIndex("A3607")),dw));
			m.setA3627(cursor.getString(cursor.getColumnIndex("A3627")));
			m.setA3611(dw);
			qt.add(m);
		}
		cursor.close();
		sqlite.close();
		return qt;
	}

}
