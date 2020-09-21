package com.xh189050131.weathercast.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.xh189050131.weathercast.database.CityDbSchema.CityTable;

public class CityBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "cityBase.db";

    public CityBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // 账号密码表
        db.execSQL("create table " + CityTable.NAME + "(" +
                "_id integer primary key autoincrement, " +
                CityTable.Cols.id + ", " +
                CityTable.Cols.name  + ")"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
