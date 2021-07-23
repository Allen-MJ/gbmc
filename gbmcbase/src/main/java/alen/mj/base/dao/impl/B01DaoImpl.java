package alen.mj.base.dao.impl;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.B01Dao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.db.SqlHelper;
import alen.mj.base.entry.B01;
import alen.mj.base.entry.Tree;
import alen.mj.base.entry.UnitslevelEntry;
import allen.frame.tools.StringUtils;

public class B01DaoImpl implements B01Dao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public B01DaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addB01(List<B01> list, String B0111_key) {
//		// 清除上次的数据
//		this.deletB01(B0111_key);
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		int size = list==null?0:list.size();
		if (size>0) {
			// 添加新数据
			for (B01 entry : list) {
				Object[] value = new Object[] { B0111_key,
						entry.getB0101(),entry.getB0104(),entry.getB0107(),entry.getB0114(),entry.getB0111(),
						entry.getB0100(),entry.getB0117(),entry.getB0121(),entry.getB0124(),entry.getB0127(),
						entry.getB0131(),entry.getB0194(),entry.getB0227(),entry.getB0232(),entry.getB0233(),
						entry.getB0236(),entry.getB0234(),entry.getB0238(),entry.getB0239(),entry.getB0150(),
						entry.getB0183(),entry.getB0185(),entry.getSORTID()
						};
				// 先删除某行数据
				sqlite.execSQL("delete from B01 where B0111='"+entry.getB0111()+"'");
				
				String sqlStr = "insert into B01(B0111_key, B0101, B0104, B0107, B0114, B0111, B0100, B0117, B0121, B0124, B0127, B0131, B0194, B0227, B0232, B0233, B0236, B0234, B0238, B0239, B0150, B0183, B0185, SORTID) values(?,?,?,?,?,?,?,?,?,?, ?,?,?,?,?,?,?,?,?,?, ?,?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
		}
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	
	

	@Override
	public void deletB01() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from B01");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletB01(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from B01 where B0111_key='"+B0111_key+"'");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public List<Tree> getDwList(String code, String type) {
		int len = StringUtils.empty(code)?0:code.length();
		List<Tree> list = new ArrayList<Tree>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getDwList(code,type), null);
		while (cursor.moveToNext()) {
			Tree r = new Tree();
			
			r.setCode(cursor.getString(cursor
					.getColumnIndex("B0100")));
			r.setName(cursor.getString(cursor
					.getColumnIndex("B0104")));
			r.setItemNum(cursor.getInt(cursor
					.getColumnIndex("num")));
			if("23".equals(type)){
				String id = cursor.getString(cursor
						.getColumnIndex("B0111"));
				r.setId(id);
				r.setPid(cursor.getString(cursor
						.getColumnIndex("B0121")));
				r.setLevel((id.length()-len)/4);
				r.setOpen(cursor.getInt(cursor
						.getColumnIndex("isOpen"))==1?true:((id.length()-len)/4<2));
				r.setCheck((id.length()-len)/4<1);
			}else{
				r.setId("");
				r.setPid("");
				r.setLevel(0);
				r.setOpen(true);
				r.setCheck(false);
			}
//			r.setNodeMapKey2Value(gwyinfo.B0111, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.B0111)));
//			r.setNodeMapKey2Value(gwyinfo.B0114, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.B0114)));
//			r.setNodeMapKey2Value(gwyinfo.B0194, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.B0194)));
//			r.setNodeMapKey2Value(gwyinfo.DATAVERSION, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.DATAVERSION)));
			list.add(r);
		}
		cursor.close();
		sqlite.close();
		return list;
	}
	@Override
	public List<Tree> getDwList(String ccode, String code, String type) {
		int len = StringUtils.empty(code)?0:code.length();
		List<Tree> list = new ArrayList<Tree>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getDwList(ccode,code,type), null);
		while (cursor.moveToNext()) {
			Tree r = new Tree();
			
			r.setCode(cursor.getString(cursor
					.getColumnIndex("B0100")));
			r.setName(cursor.getString(cursor
					.getColumnIndex("B0104")));
			r.setItemNum(cursor.getInt(cursor
					.getColumnIndex("num")));
			if("23".equals(type)){
				String id = cursor.getString(cursor
						.getColumnIndex("B0111"));
				r.setId(id);
				r.setPid(cursor.getString(cursor
						.getColumnIndex("B0121")));
				r.setLevel((id.length()-len)/4);
				r.setOpen(cursor.getInt(cursor
						.getColumnIndex("isOpen"))==1?true:((id.length()-len)/4<2));
				r.setCheck((id.length()-len)/4<1);
				r.setAdd(cursor.getInt(cursor
						.getColumnIndex("isOpen"))==1);
			}else{
				r.setId("");
				r.setPid("");
				r.setLevel(0);
				r.setOpen(true);
				r.setCheck(false);
				r.setAdd(true);
			}
//			r.setNodeMapKey2Value(gwyinfo.B0111, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.B0111)));
//			r.setNodeMapKey2Value(gwyinfo.B0114, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.B0114)));
//			r.setNodeMapKey2Value(gwyinfo.B0194, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.B0194)));
//			r.setNodeMapKey2Value(gwyinfo.DATAVERSION, cursor.getString(cursor
//					.getColumnIndex(gwyinfo.DATAVERSION)));
			list.add(r);
		}
		cursor.close();
		sqlite.close();
		return list;
	}
	
	// 查询单个单位的下级单位
	@Override
	public List<UnitslevelEntry> getUnitsList(String code, String type, int levels) {
		List<UnitslevelEntry> list = new ArrayList<UnitslevelEntry>();
		sqlite = db.getReadableDatabase();
		String sql;
		if("23".equals(type)){ // 单位名册 
			sql = "select t2.counts as num, t1.B0101 as B0101,t1.B0104 as B0104,t1.B0100 as B0100,t1.B0121 as B0121,t1.SORTID,t1.b0107,t1.B0111 as B0111,t1.B0111_key from ( "
					+ "select b0101,b0104,B0100,B0121,SORTID,b0107,B0111,B0111_key from B01 WHERE B0111 like '" 
					+ code + "%' and B0121 = '" + code
					+ "' ) t1 left outer join (select count(b0121) as counts,B0121 from b01 group by b0121) t2"
					+ " on t2.b0121 = t1.B0111 order by SORTID";
		}else{ // 自定义名册
			sql = "select M0200,M0100,B0100,B0101,B0104,SortID,0 as num from M02 WHERE M0100='" 
					+ code + "' ORDER BY SortID";
		}
		Cursor cursor = sqlite.rawQuery(sql, null);
		while (cursor != null && cursor.moveToNext()) {
			UnitslevelEntry entry = new UnitslevelEntry();
			entry.setCode(cursor.getString(cursor.getColumnIndex("B0100")));
			
			entry.setName(cursor.getString(cursor.getColumnIndex("B0104")));
			entry.setFullName(cursor.getString(cursor.getColumnIndex("B0101")));
			
			entry.setLeaf(cursor.getInt(cursor.getColumnIndex("num")) <= 0);
			if("23".equals(type)){
				entry.setId(cursor.getString(cursor.getColumnIndex("B0111")));
				
				entry.setPid(cursor.getString(cursor.getColumnIndex("B0121")));
				entry.setLevel(levels + 1);
				entry.setOpen(false);
				entry.setCheck(false);
			}else{ // 自定义名册 单位树 就一级
				entry.setId(cursor.getString(cursor.getColumnIndex("B0100")));
				
				entry.setPid(cursor.getString(cursor.getColumnIndex("M0100")));
				entry.setLevel(1);
				entry.setOpen(false);
				entry.setCheck(false);
			}
			list.add(entry);
		}
		cursor.close();
		sqlite.close();
		try {
			if("23".equals(type) && levels == 0){
				UnitslevelEntry uperEntry = this.getOneUnits(code, levels);
				if(uperEntry!=null){
					List<UnitslevelEntry> subList = new ArrayList<UnitslevelEntry>();
					subList.addAll(list);
					uperEntry.setSubList(subList);
					list.add(0, uperEntry);
				}
			}
		} catch (Exception e) {
		}
		return list;
	}
	
	// 查询单个单位名册时 的主单位
	public UnitslevelEntry getOneUnits(String code, int levels) {
		sqlite = db.getReadableDatabase();
		String sql = "select t2.counts as num, t1.B0101 as B0101,t1.B0104 as B0104,t1.B0100 as B0100,t1.B0121 as B0121,t1.SORTID,t1.B0111 as B0111 from ( "
					+ "select b0101,b0104,B0100,B0121,SORTID,B0111 from B01 WHERE B0111 = '" 
					+ code + "' ) t1 left outer join (select count(b0121) as counts,B0121 from b01 group by b0121) t2"
					+ " on t2.b0121 = t1.B0111 order by SORTID";
		
		Cursor cursor = sqlite.rawQuery(sql, null);
		UnitslevelEntry entry = null;
		if (cursor != null && cursor.moveToNext()) {
			entry = new UnitslevelEntry();
			entry.setCode(cursor.getString(cursor.getColumnIndex("B0100")));
			entry.setName(cursor.getString(cursor.getColumnIndex("B0104")));
			entry.setFullName(cursor.getString(cursor.getColumnIndex("B0101")));
			entry.setLeaf(cursor.getInt(cursor.getColumnIndex("num")) <= 0);
			entry.setId(cursor.getString(cursor.getColumnIndex("B0111")));
			entry.setPid(cursor.getString(cursor.getColumnIndex("B0121")));
			
			entry.setLevel(levels);
			entry.setOpen(true);
			entry.setCheck(true);
		}
		cursor.close();
		sqlite.close();
		return entry;
	}

}
