package com.xh189050131.weathercast;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.xh189050131.weathercast.database.CityBaseHelper;
import com.xh189050131.weathercast.database.CityCursorWrapper;
import com.xh189050131.weathercast.database.CityDbSchema.CityTable;

public class CityLab {
    private static CityLab sCityLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    private CityLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CityBaseHelper(mContext).getWritableDatabase();
    }

    // 添加记录
    public void addCity(City c) {
        ContentValues values = getContentValues(c);
        mDatabase.insert(CityTable.NAME, null, values);
    }

    // 删除记录
    public void removeCity(City c) {
        String uuidString = c.getCid().toString();
        mDatabase.delete(CityTable.NAME, CityTable.Cols.id + " = ?", new String[]{uuidString});
    }

    // 更新记录
    public void updateCity(City city) {
        String uuidString = city.getCid().toString();
        ContentValues values = getContentValues(city);
        mDatabase.update(CityTable.NAME, values, CityTable.Cols.id + " = ?", new String[]{uuidString});
    }



    // 查找记录
    private CityCursorWrapper queryCities(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                CityTable.NAME,
                null,// null selects all columns
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new CityCursorWrapper(cursor);
    }

    // 得到Manages集合
    public List<City> getCities() {
        List<City> cities = new ArrayList<>();

        CityCursorWrapper cursor = queryCities(null,null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                cities.add(cursor.getCity());
                cursor.moveToNext();
            }
        }finally {
            cursor.close();
        }
        return cities;
    }

    // 根据ID获取Manage
    public City getCity(UUID id) {

        CityCursorWrapper cursor = queryCities(
                CityTable.Cols.id + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCity();
        } finally {
            cursor.close();
        }
    }

    // 获得数据库数据
    private static ContentValues getContentValues(City city) {
        ContentValues values = new ContentValues();
        values.put(CityTable.Cols.id, city.getCid().toString());
        values.put(CityTable.Cols.name, city.getCname());

        return values;
    }

    public static CityLab get(Context context) {
        if (sCityLab == null) {
            sCityLab = new CityLab(context);
        }
        return sCityLab;
    }

}
