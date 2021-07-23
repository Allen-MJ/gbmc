package alen.mj.base.db;

import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager {
	
	private static DatabaseManager manager;
	private static DatebaseSqlite mSqlite;
	private SQLiteDatabase mDatabase;
	private int point = 0;
	public static synchronized void init(DatebaseSqlite sqlite){
		if (manager == null) {
			manager = new DatabaseManager();
			mSqlite = sqlite;
        }
	}
	public static synchronized DatabaseManager getInstance(){
		if (manager == null) {
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() +
                    " is not initialized, call init(..) method first.");
        }
        return manager;
	}
	public synchronized SQLiteDatabase openDatabase() {
		point = point +1;
        if(point == 1) {
            // Opening new database
            mDatabase = mSqlite.getWritableDatabase();
        }
        return mDatabase;
    }
 
    public synchronized void closeDatabase() {
    	point = point - 1;
        if(point == 0) {
            // Closing database
            mDatabase.close();
        }
    }
    public synchronized boolean isClosed(){
    	if(point==0){
    		return true;
    	}
		return false;
    }
}
