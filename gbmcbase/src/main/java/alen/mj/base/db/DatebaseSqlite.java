package alen.mj.base.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import alen.mj.base.utils.Constants;

public class DatebaseSqlite extends SQLiteOpenHelper {

	public DatebaseSqlite(Context context) {

		super(context, Constants.DB_NAME, null, Constants.VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// 名册表
		String mcDDL = "create table PB_MC(rid integer primary key autoincrement,"
				+ "type varchar(100),time Varchar(200),dataversion varchar(100),"
				+ "psncount varchar(100),B0101 varchar(100),B0111 varchar(100),B0114 varchar(100),"
				+ "B0194 varchar(100),linkpsn varchar(100),linktel varchar(100),remark varchar(100), "
				+ "isMCTag varchar(10), SortID integer, M0105 varchar(10))";
		db.execSQL(mcDDL);
		
		// A01
		String a01Table = "create table A01(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000 varchar(100),A0101 Varchar(100),A0102 varchar(100),"
				+ "A0104 varchar(100),A0107 varchar(100),A0111A varchar(100),A0114A varchar(100),"
				+ "A0115A varchar(100),A0117 varchar(100),A0128 varchar(100),"
				+ "A0134 varchar(100),A0140 varchar(100),A0141 varchar(100),A0144 varchar(100),"
				+ "A3921 varchar(100),A3927 varchar(100),A0160 varchar(100),A0163 varchar(100),"
				+ "A0165 varchar(100),A0184 varchar(100),A0187A varchar(100),A0192 varchar(100),"
				+ "A0192A varchar(100),A0221 varchar(100),A0288 varchar(100),A0192E varchar(100),"
				+ "A0192C varchar(100),A0196 varchar(100),A0197 varchar(100),A0195 varchar(100),"
				+ "A1701 varchar(100),A14Z101 varchar(100),A15Z101 varchar(100),A0120 varchar(100),"
				+ "A0121 varchar(100),A0122 varchar(100),A2949 varchar(100),A0180 varchar(100),"
				+ "QRZXL varchar(100),QRZXLXX varchar(100),QRZXW varchar(100),QRZXWXX varchar(100),"
				+ "ZZXL varchar(100),ZZXLXX varchar(100),ZZXW varchar(100),ZZXWXX varchar(100),"
				+ "ZGXL varchar(100),ZGXLXX varchar(100),ZGXW varchar(100),ZGXWXX varchar(100), ZZZS varchar(100), QRZZS varchar(100))";
		db.execSQL(a01Table);
		
		// A02   // A0223 A0225 Varchar(100)
		String a02Table = "create table A02(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A0201A  Varchar(100),A0201B  Varchar(100),A0201D  Varchar(100),"
				+ "A0201E  Varchar(100),A0215A  Varchar(100),A0219  Varchar(100),A0223  integer,"
				+ "A0225  integer,A0243  Varchar(100),A0245  Varchar(100),A0247  Varchar(100),"
				+ "A0251B Varchar(100),A0255  Varchar(100),A0265  Varchar(100),A0267  Varchar(100),"
				+ "A0272  Varchar(100),A0281  Varchar(100),A0279 Varchar(100),A0215B Varchar(100))";
		db.execSQL(a02Table);
		
		// A05
		String a05Table = "create table A05(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A0531  Varchar(100),A0501B  Varchar(100),A0504  Varchar(100),"
				+ "A0511  Varchar(100),A0517  Varchar(100),A0524 Varchar(100))";
		db.execSQL(a05Table);
		
		// A06
		String a06Table = "create table A06(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A0602  Varchar(100),A0601  Varchar(100),A0604  Varchar(100),"
				+ "A0607  Varchar(100),A0611  Varchar(100),A0699 Varchar(100))";
		db.execSQL(a06Table);
		
		// A08
		String a08Table = "create table A08(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A0801A  Varchar(100),A0801B  Varchar(100),A0901A  Varchar(100),"
				+ "A0901B  Varchar(100),A0804  Varchar(100),A0807  Varchar(100),A0904  Varchar(100),"
				+ "A0814  Varchar(100),A0824  Varchar(100),A0827  Varchar(100),A0834  Varchar(100),"
				+ "A0835  Varchar(100),A0837 Varchar(100),	A0811  Varchar(100),A0899 Varchar(100))";
		db.execSQL(a08Table);
		
		// A14
		String a14Table = "create table A14(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A1404A  Varchar(100),A1404B  Varchar(100),A1407  Varchar(100),"
				+ "A1411A  Varchar(100),A1414  Varchar(100),A1415  Varchar(100),A1424  Varchar(100),A1428 Varchar(100))";
		db.execSQL(a14Table);
		
		// A15
		String a15Table = "create table A15(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A1517  Varchar(100),A1521  Varchar(100))";
		db.execSQL(a15Table);
		
		// A36
		String a36Table = "create table A36(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A3601  Varchar(100),A3604A  Varchar(100),A3607  Varchar(100),"
				+ "A3627  Varchar(100), A3611 Varchar(100), SORTID integer)";
		db.execSQL(a36Table);
		
		// A57
		String a57Table = "create table A57(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100),A5714  Varchar(100))";
		db.execSQL(a57Table);
		
		// A99Z1
		String a99Z1Table = "create table A99Z1(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "A0000  Varchar(100))";
		db.execSQL(a99Z1Table);
		
		// B01
		String b01Table = "create table B01(aid integer primary key autoincrement,B0111_key varchar(100),"
				+ "B0101  Varchar(100),B0104  Varchar(100),B0107  Varchar(100),B0114  Varchar(100),"
				+ "B0111  Varchar(100),B0100  Varchar(100),B0117  Varchar(100),B0121  Varchar(100),"
				+ "B0124  Varchar(100),B0127  Varchar(100),B0131  Varchar(100),B0194  Varchar(100),"
				+ "B0227  Varchar(100),B0232  Varchar(100),B0233  Varchar(100),B0236  Varchar(100),"
				+ "B0234  Varchar(100),B0238  Varchar(100),B0239  Varchar(100),B0150  Varchar(100),"
				+ "B0183  Varchar(100),B0185  Varchar(100),SORTID integer,MCID Varchar(100))";
		db.execSQL(b01Table);
		
		// M02
		String m02Table = "create table M02(aid integer primary key autoincrement,"
				+ "M0200  Varchar(100), M0100  Varchar(100), B0100  Varchar(100), B0101  Varchar(100), B0104  Varchar(100), SortID  integer)";
		db.execSQL(m02Table);
		
		// M03
		String m03Table = "create table M03(aid integer primary key autoincrement,"
				+ "M0300  Varchar(100), M0111  Varchar(100), B0100  Varchar(100), A0000  Varchar(100), SortID  integer)";
		db.execSQL(m03Table);
		
		db.execSQL("create index t1 on M03 (A0000,M0111,B0100)");
		db.execSQL("create index t2 on M02 (M0100,B0100)");
		db.execSQL("create index t3 on A01 (A0000)");
		db.execSQL("create index t4 on A02 (A0000,A0201B)");
		db.execSQL("create index t5 on B01 (B0111,B0100)");

	}

	// 版本更新时
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*for(int i=oldVersion;i<=newVersion;i++){
			if(i==100){
				db.execSQL("ALTER TABLE A01 ADD COLUMN ZZZS varchar(100)");
				db.execSQL("ALTER TABLE A01 ADD COLUMN QRZZS varchar(100)");
			}
		}*/
	}

}
