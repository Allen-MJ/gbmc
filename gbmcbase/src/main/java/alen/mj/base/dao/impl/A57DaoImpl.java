package alen.mj.base.dao.impl;

import java.util.List;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.A57Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.entry.A57;

public class A57DaoImpl implements A57Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A57DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA57(List<A57> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA57(B0111_key);
		
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A57 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA5714()
						};
				String sqlStr = "insert into A57(B0111_key, A0000, A5714) values(?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	@Override
	public void deletA57() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A57");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA57(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A57 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

}
