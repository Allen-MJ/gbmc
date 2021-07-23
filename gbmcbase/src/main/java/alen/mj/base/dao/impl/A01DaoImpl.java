package alen.mj.base.dao.impl;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.format.Time;

import java.util.ArrayList;
import java.util.List;

import alen.mj.base.dao.A01Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.db.SqlHelper;
import alen.mj.base.entry.A01;
import alen.mj.base.entry.M01;
import alen.mj.base.utils.Constants;
import allen.frame.tools.Logger;
import allen.frame.tools.StringUtils;

public class A01DaoImpl implements A01Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public A01DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addA01(List<A01> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletA01(B0111_key);
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (A01 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getA0000(),entry.getA0101(),entry.getA0102(),entry.getA0104(),entry.getA0107(),entry.getA0111A(),entry.getA0114A(),entry.getA0115A(),entry.getA0117(),entry.getA0128(),entry.getA0134(),entry.getA0140(),entry.getA0141(),entry.getA0144(),
						entry.getA3921(),entry.getA3927(),entry.getA0160(),entry.getA0163(),entry.getA0165(),entry.getA0184(),entry.getA0187A(),entry.getA0192(),entry.getA0192A(),entry.getA0221(),entry.getA0288(),entry.getA0192E(),entry.getA0192C(),entry.getA0196(),entry.getA0197(),
						entry.getA0195(),entry.getA1701(),entry.getA14Z101(),entry.getA15Z101(),entry.getA0120(),entry.getA0121(),entry.getA0122(),entry.getA2949(),entry.getA0180(),entry.getQRZXL(),entry.getQRZXLXX(),entry.getQRZXW(),entry.getQRZXWXX(),entry.getZZXL(),
						entry.getZZXLXX(),entry.getZZXW(),entry.getZZXWXX(),entry.getZGXL(),entry.getZGXLXX(),entry.getZGXW(),entry.getZGXWXX()
						,entry.getZZZS(), entry.getQRZZS()};
//				System.out.println(entry.getA0101()+"---用户-->>" + entry.getA0000());
//				if(B0111_key == gwyinfo.TABMCFLAGLAB){ // mc 先删除，再说添加    B0111_key='"+B0111_key+"' and
					sqlite.execSQL("delete from A01 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A02 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A05 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A06 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A08 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A14 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A15 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A36 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A57 where A0000 = '"+entry.getA0000()+"'");
					sqlite.execSQL("delete from A99Z1 where A0000 ='"+entry.getA0000()+"'");
//				}
				String sqlStr = "insert into A01(B0111_key,A0000, A0101, A0102, A0104, A0107, A0111A, A0114A, A0115A, A0117, A0128, A0134, A0140, A0141, A0144,"
						+ "A3921, A3927, A0160, A0163, A0165, A0184, A0187A, A0192, A0192A, A0221, A0288, A0192E, A0192C, A0196, A0197,"
						+ "A0195, A1701, A14Z101, A15Z101, A0120, A0121, A0122, A2949, A0180, QRZXL, QRZXLXX, QRZXW, QRZXWXX, ZZXL,"
						+ "ZZXLXX, ZZXW, ZZXWXX, ZGXL, ZGXLXX, ZGXW, ZGXWXX, ZZZS,QRZZS) values(?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	@Override
	public void deletA01() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A01");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletA01(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from A01 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public List<M01> getPerMcList(String code, String id, String key, String type) {
		List<M01> qt = new ArrayList<M01>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getPerMcList(code, id, key, type), null);
		while (cursor.moveToNext()) {
			M01 m = new M01();
			m.setM0100(cursor.getString(cursor.getColumnIndex("A0000")));
			m.setM0101(cursor.getString(cursor.getColumnIndex("A0101")));
			if("23".equals(type)&& StringUtils.empty(key)){
				m.setM0102(cursor.getString(cursor.getColumnIndex("A0215A")));
			}else{
				m.setM0102(cursor.getString(cursor.getColumnIndex("A0192")));
			}
			m.setM0103(cursor.getString(cursor.getColumnIndex("A0215B")));//A0215B
			m.setM0104(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0243"))));
			m.setM0105(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0288"))));
			m.setM0106(Constants.getSex(cursor.getString(cursor.getColumnIndex("A0104"))));
			m.setM0107(Constants.getNative(cursor.getString(cursor.getColumnIndex("A0117"))));
			m.setM0108(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0107"))));
			m.setM0109(cursor.getString(cursor.getColumnIndex("A0111A")));
			m.setM0110(cursor.getString(cursor.getColumnIndex("QRZZS")));
			m.setM0111(cursor.getString(cursor.getColumnIndex("ZZZS")));
			m.setM0112(cursor.getString(cursor.getColumnIndex("A0196")));
			m.setM0113(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0134"))));
			m.setM0114(cursor.getString(cursor.getColumnIndex("A0140")));
			m.setM0115(cursor.getString(cursor.getColumnIndex("A0180")));
			m.setA5714(cursor.getString(cursor.getColumnIndex("A5714")));
			m.setA0144(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0144"))));
			m.setXl(cursor.getString(cursor.getColumnIndex("xl")));
			Logger.e("debug", "xl:"+m.getXl());
			qt.add(m);
		}
		cursor.close();
		sqlite.close();
		return qt;
	}

	@Override
	public A01 getPersonInfo(String id) {
		// TODO Auto-generated method stub
		Time time = new Time();
		time.setToNow();
		int y = time.year;
		int m = time.month+1;
		double a;
		if(m<10){
			a = Double.parseDouble(y+".0"+m);
		}else{
			a = Double.parseDouble(y+"."+m);
		}
		A01 qt = null;
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getPersonInfo(id), null);
		while (cursor.moveToNext()) {
			qt = new A01();
			qt.setA0000(id);
			qt.setA0184(cursor.getString(cursor.getColumnIndex("A0184")));
			qt.setA0101(cursor.getString(cursor.getColumnIndex("A0101")));
			qt.setA0104(Constants.getSex(cursor.getString(cursor.getColumnIndex("A0104"))));
			String date = cursor.getString(cursor.getColumnIndex("A0107"));
			if(StringUtils.empty(date)){
				qt.setA0107("");
			}else{
				if(date.length()>4){
					StringBuffer sb = new StringBuffer(date);
					if(date.contains(".")){
						String num = sb.toString();
						if(date.length()>7){
							num = num.substring(0, 7);
						}
						double b = Double.parseDouble(num);
						qt.setA0107(num+"<br  />("+(int)Math.floor(a-b)+"岁)");
					}else{
						String num = sb.insert(4, ".").toString();
						double b = Double.parseDouble(num);
						qt.setA0107(num+"<br  />("+(int)Math.floor(a-b)+"岁)");
					}
					sb=null;
				}else{
					qt.setA0107("");
				}
			}
			qt.setA0117(Constants.getNative(cursor.getString(cursor.getColumnIndex("A0117"))));
			qt.setA0111A(cursor.getString(cursor.getColumnIndex("A0111A")));
			qt.setA0114A(cursor.getString(cursor.getColumnIndex("A0114A")));
			qt.setA0140(cursor.getString(cursor.getColumnIndex("A0140")));
			String work = cursor.getString(cursor.getColumnIndex("A0134"));
			if(StringUtils.empty(work)){
				qt.setA0134("");
			}else{
				if(work.length()>4){
					StringBuffer sb = new StringBuffer(work);
					if(work.contains(".")){
						String num = sb.toString();
						if(work.length()>7){
							num = num.substring(0, 7);
						}
						qt.setA0134(num);
					}else{
						String num = sb.insert(4, ".").toString();
						qt.setA0134(num);
					}
					sb=null;
				}else{
					qt.setA0134("");
				}
			}
			qt.setA0128(cursor.getString(cursor.getColumnIndex("A0128")));
			qt.setA0196(cursor.getString(cursor.getColumnIndex("A0196")));
			qt.setA0187A(cursor.getString(cursor.getColumnIndex("A0187A")));
			qt.setQRZXL(cursor.getString(cursor.getColumnIndex("QRZXL")));
			qt.setQRZXLXX(cursor.getString(cursor.getColumnIndex("QRZXLXX")));
			qt.setQRZXW(cursor.getString(cursor.getColumnIndex("QRZXW")));
			qt.setQRZXWXX(cursor.getString(cursor.getColumnIndex("QRZXWXX")));
			qt.setZZXL(cursor.getString(cursor.getColumnIndex("ZZXL")));
			qt.setZZXLXX(cursor.getString(cursor.getColumnIndex("ZZXLXX")));
			qt.setZZXW(cursor.getString(cursor.getColumnIndex("ZZXW")));
			qt.setZZXWXX(cursor.getString(cursor.getColumnIndex("ZZXWXX")));
			qt.setA0192A(cursor.getString(cursor.getColumnIndex("A0192A")));
			qt.setA14Z101(cursor.getString(cursor.getColumnIndex("A14Z101")));
			qt.setA15Z101(cursor.getString(cursor.getColumnIndex("A15Z101")));
			qt.setA5714(cursor.getString(cursor.getColumnIndex("A5714")));
			qt.setA1701(cursor.getString(cursor.getColumnIndex("A1701")));//A1701
//			qt.setA1701(Constants.replaceJl(cursor.getString(cursor.getColumnIndex("A1701"))));//A1701
		}
		cursor.close();
		sqlite.close();
		return qt;
	}

	
	// 处理前多少条
	@Override
	public List<M01> getPerMcTopList(String code, int top, String type) {
		List<M01> qt = new ArrayList<M01>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery( SqlHelper.getPerMcTopList(code, top, type), null);
		while (cursor.moveToNext()) {
			M01 m = new M01();
			m.setM0100(cursor.getString(cursor.getColumnIndex("A0000")));
			m.setM0101(cursor.getString(cursor.getColumnIndex("A0101")));
			if("23".equals(type)){
				m.setM0102(cursor.getString(cursor.getColumnIndex("A0215A")));
			}else{
				m.setM0102(cursor.getString(cursor.getColumnIndex("A0192")));
			}
			m.setM0103("");
			m.setM0104(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0243"))));
			m.setM0105(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0288"))));
			m.setM0106(Constants.getSex(cursor.getString(cursor.getColumnIndex("A0104"))));
			m.setM0107(Constants.getNative(cursor.getString(cursor.getColumnIndex("A0117"))));
			m.setM0108(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0107"))));
			m.setM0109(cursor.getString(cursor.getColumnIndex("A0111A")));
			m.setM0110(cursor.getString(cursor.getColumnIndex("QRZZS")));
			m.setM0111(cursor.getString(cursor.getColumnIndex("ZZZS")));
			m.setM0112(cursor.getString(cursor.getColumnIndex("A0196")));
			m.setM0113(Constants.cldate(cursor.getString(cursor.getColumnIndex("A0134"))));
			m.setM0114(cursor.getString(cursor.getColumnIndex("A0140")));
			m.setM0115(cursor.getString(cursor.getColumnIndex("A0180")));
			qt.add(m);
		}
		cursor.close();
		sqlite.close();
		return qt;
	}
	
	
}
