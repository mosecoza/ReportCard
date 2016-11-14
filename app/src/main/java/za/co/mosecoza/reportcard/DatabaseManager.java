package za.co.mosecoza.reportcard;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

public class DatabaseManager {
    private Integer mOpenCounter =0;

    private static DatabaseManager instance;
    private static SQLiteOpenHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    public static synchronized void initializeInstance(SQLiteOpenHelper helper){
        if (instance == null){
            instance = new DatabaseManager();
            mDBHelper = helper;
        }
    }

    public static synchronized DatabaseManager getInstance(){
        if(instance==null){
            throw new IllegalStateException(DatabaseManager.class.getSimpleName()
            +" is not initialized, call initializeInstance(..) method first.");
        }
        return instance;
    }
    public synchronized SQLiteDatabase openDatabase() {
        mOpenCounter+=1;
        if(mOpenCounter == 1) {
            // Opening new database
            mDatabase = mDBHelper.getWritableDatabase();
        }
        return mDatabase;
    }

    public synchronized void closeDatabase() {
        mOpenCounter-=1;
        if(mOpenCounter == 0) {
            // Closing database
            mDatabase.close();

        }
    }
}
