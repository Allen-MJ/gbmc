package alen.mj.base.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import alen.mj.base.dao.GwyinfoDao;
import alen.mj.base.db.DatebaseSqlite;
import alen.mj.base.db.SqlHelper;
import alen.mj.base.entry.MCSubEntry;
import alen.mj.base.entry.gwyinfo;
import allen.frame.tools.StringUtils;

/**
 *  删除的设计方案一   添加gwyinfo 数据时删除重复数据，   添加a01 时删除其他部分表关联人员数据，添加b01时 删除重复的单位表数据
 *  缺点：非关联数据冗余 数据库中。  单位名册、自定义名册   人员信息相互覆盖
 * @author Administrator
 *  方案二 添加gwyinfo 数据时，将其关联数据全部删除。 在添加a01时  再次删除没有删除完成的数据（第一次加载的数据单位名册、自定义名册），b01 时同理。
 *  
 *  目前采用方案一
 */
public class GwyinfoDaoImpl implements GwyinfoDao {

	private DatebaseSqlite db;
	private SQLiteDatabase sqlite;

	public GwyinfoDaoImpl(Context context) {
		db = new DatebaseSqlite(context);
	}
	
	@Override
	public void addGwyinfo(gwyinfo entry) {
		HashMap<String, String> map = entry.getNodeMap();
		try {
			// 清除上次的数据
			deletGwyinfo(map.get(gwyinfo.B0111));
		} catch (Exception e) {
		}
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		// 添加新数据
		Object[] value = new Object[] { 
				map.get(gwyinfo.TYPE), map.get(gwyinfo.TIME), map.get(gwyinfo.DATAVERSION),
				map.get(gwyinfo.PSNCOUNT), map.get(gwyinfo.B0101), map.get(gwyinfo.B0111),
				map.get(gwyinfo.B0114), map.get(gwyinfo.B0194), map.get(gwyinfo.LINKPSN),
				map.get(gwyinfo.LINKTEL), map.get(gwyinfo.REMARK), gwyinfo.TABNORMALLAB
				
		};
		String sqlStr = "insert into PB_MC(type, time, dataversion, psncount, B0101, B0111, B0114, B0194, linkpsn, linktel, remark, isMCTag) values(?,?,?,?,?,?,?,?,?,?, ?, ?)";
		sqlite.execSQL(sqlStr, value);
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}
	
	// 添加名册的数据
	public void addM01Gwyinfo(gwyinfo entry, List<MCSubEntry> subMCList){
		HashMap<String, String> map = entry.getNodeMap();
		int size = subMCList.size();
		if(size > 0){
			sqlite = db.getWritableDatabase();
			sqlite.beginTransaction();
			for(MCSubEntry subEntry : subMCList){
				  // 添加 子名册
				Object[] value = new Object[] { 
					map.get(gwyinfo.TYPE), map.get(gwyinfo.TIME), map.get(gwyinfo.DATAVERSION),
					map.get(gwyinfo.PSNCOUNT), subEntry.getM0101(), subEntry.getM0111(),
					map.get(gwyinfo.B0114), map.get(gwyinfo.B0194), map.get(gwyinfo.LINKPSN),
					map.get(gwyinfo.LINKTEL), map.get(gwyinfo.REMARK),gwyinfo.TABMCFLAGLAB,
					subEntry.getSortID(), subEntry.getM0105()
				};
				// 清除上次的数据
				String M0111 = subEntry.getM0111();
				// 逐本名册删除
				sqlite.execSQL("delete from PB_MC where B0111='" + M0111 + "' and isMCTag='"+gwyinfo.TABMCFLAGLAB+"'");
				
//				sqlite.execSQL("delete from B01 where  B0100 in (select t1.B0100 from m02 t1,(select B0100,count(B0100)as total from m02 group by B0100)t2 where t1.B0100 = t2.B0100 and t2.total = 1 and t1.M0100 ='"+M0111+"')");
//	
//				sqlite.execSQL("delete from A02 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A05 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A06 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A08 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A14 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A15 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A36 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A57 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				sqlite.execSQL("delete from A99Z1 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
//				
//				sqlite.execSQL("delete from A01 where  A0000 in (select t1.a0000 from m03 t1,(select a0000,count(a0000)as total from m03 group by a0000)t2 where t1.a0000 = t2.a0000 and t2.total = 1 and t1.M0111 ='"+M0111+"')");
				
				sqlite.execSQL("delete from M02 where M0100='" + M0111 + "'");
				sqlite.execSQL("delete from M03 where M0111 ='" + M0111 + "'");
				
				String sqlStr = "insert into PB_MC(type, time, dataversion, psncount, B0101, B0111, B0114, B0194, linkpsn, linktel, remark, isMCTag, SortID, M0105) values(?,?,?,?,?,?,?,?,?,?, ?, ?,?,?)";
				sqlite.execSQL(sqlStr, value);
			}
			sqlite.setTransactionSuccessful();
			sqlite.endTransaction();
			sqlite.close();
		}
	}
	
	@Override
	public void deletGwyinfo() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from PB_MC");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public void deletGwyinfo(String B0111_key) {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from PB_MC where B0111='"+B0111_key+"' and isMCTag='"+gwyinfo.TABNORMALLAB+"'");
//		sqlite.execSQL("delete from A01 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A02 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A05 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A06 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A08 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A14 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A15 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A36 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A57 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from A99Z1 where B0111_key='"+B0111_key+"'");
//		sqlite.execSQL("delete from B01 where B0111_key='"+B0111_key+"'");
		
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public List<gwyinfo> getMcList() {
		List<gwyinfo> list = new ArrayList<gwyinfo>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getMcList(), null);
		while (cursor.moveToNext()) {
			gwyinfo r = new gwyinfo();
			r.setNodeMapKey2Value(gwyinfo.B0101, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0101)));
			r.setNodeMapKey2Value(gwyinfo.B0111, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0111)));
			r.setNodeMapKey2Value(gwyinfo.B0114, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0114)));
			r.setNodeMapKey2Value(gwyinfo.B0194, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0194)));
			r.setNodeMapKey2Value(gwyinfo.DATAVERSION, getDATAVERSION(cursor.getString(cursor
					.getColumnIndex(gwyinfo.DATAVERSION))));
			r.setNodeMapKey2Value(gwyinfo.TYPE, cursor.getString(cursor.getColumnIndex("isMCTag")));
			r.setNodeMapKey2Value(gwyinfo.NUM, cursor.getString(cursor.getColumnIndex("num")));
			list.add(r);
		}
		cursor.close();
		sqlite.close();
		return list;
	}

	private String getDATAVERSION(String vision){
		if(StringUtils.notEmpty(vision)){
			if(vision.length()>8){
				return vision.substring(0, 8);
			}
		}
		return vision;
	}
	
	@Override
	public void deleteAllData() {
		sqlite = db.getWritableDatabase();
		sqlite.beginTransaction();
		sqlite.execSQL("delete from PB_MC");
		sqlite.execSQL("delete from M02");
		sqlite.execSQL("delete from M03");
		sqlite.execSQL("delete from A01");
		sqlite.execSQL("delete from A02");
		sqlite.execSQL("delete from A05");
		sqlite.execSQL("delete from A06");
		sqlite.execSQL("delete from A08");
		sqlite.execSQL("delete from A14");
		sqlite.execSQL("delete from A15");
		sqlite.execSQL("delete from A36");
		sqlite.execSQL("delete from A57");
		sqlite.execSQL("delete from A99Z1");
		sqlite.execSQL("delete from B01");
		sqlite.setTransactionSuccessful();
		sqlite.endTransaction();
		sqlite.close();
	}

	@Override
	public List<gwyinfo> getMcList(String code) {
		List<gwyinfo> list = new ArrayList<gwyinfo>();
		sqlite = db.getReadableDatabase();
		Cursor cursor = null;
		cursor = sqlite.rawQuery(SqlHelper.getMcList(code), null);
		while (cursor.moveToNext()) {
			gwyinfo r = new gwyinfo();
			r.setNodeMapKey2Value(gwyinfo.B0101, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0101)));
			r.setNodeMapKey2Value(gwyinfo.B0111, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0111)));
			r.setNodeMapKey2Value(gwyinfo.B0114, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0114)));
			r.setNodeMapKey2Value(gwyinfo.B0194, cursor.getString(cursor
					.getColumnIndex(gwyinfo.B0194)));
			r.setNodeMapKey2Value(gwyinfo.DATAVERSION, cursor.getString(cursor
					.getColumnIndex(gwyinfo.DATAVERSION)));
			r.setNodeMapKey2Value(gwyinfo.TYPE, cursor.getString(cursor.getColumnIndex("isMCTag")));
			r.setNodeMapKey2Value(gwyinfo.NUM, cursor.getString(cursor.getColumnIndex("num")));
			list.add(r);
		}
		cursor.close();
		sqlite.close();
		return list;
	}
	

}
