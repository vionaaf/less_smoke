package com.example.andrei.smokingkills;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_DETAIL = "smokingDetails";
    private static final String DATABASE_NAME = "SmokingAndTheDayDB";
    private static final String KEY_NUMBEROFCIGARETTES ="numberOfCigarettes";
    private static final String KEY_DAY = "theDay";
    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_TABLE_DETAILS = "CREATE TABLE " + TABLE_DETAIL + "("
                + KEY_DAY + "TEXT" + KEY_NUMBEROFCIGARETTES + "TEXT" + ")";
        db.execSQL(CREATE_TABLE_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(TABLE_DETAIL);
        onCreate(db);
    }
}

