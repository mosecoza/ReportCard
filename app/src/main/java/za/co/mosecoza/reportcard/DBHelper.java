package za.co.mosecoza.reportcard;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Bonginkosi Lukhele on 11-11-2016.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Students.db";
    private static final String TAG = DBHelper.class.getSimpleName();

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBHelper() {
        super(App.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StudentsRepo.createTable());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.d(TAG, String.format("SQLiteDatabase.onUpgrade(%d ->%d)", i,i1));

        db.execSQL("DROP TABLE IF EXISTS " + Students.TABLE);
        onCreate(db);
    }
}
