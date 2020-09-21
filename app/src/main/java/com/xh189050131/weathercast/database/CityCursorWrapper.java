package com.xh189050131.weathercast.database;

import android.database.Cursor;
import android.database.CursorWrapper;



import com.xh189050131.weathercast.database.CityDbSchema.CityTable;
import com.xh189050131.weathercast.City;

import java.util.UUID;

public class CityCursorWrapper extends CursorWrapper {
    public CityCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public City getCity() {
        // 获得city信息
        String idString = getString(getColumnIndex(CityTable.Cols.id));
        String name = getString(getColumnIndex(CityTable.Cols.name));

        // 将数据写入数据库
        City city = new City(UUID.fromString(idString));
        city.setCname(name);

        return city;
    }



}
