package com.xh189050131.weathercast;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;
import java.util.UUID;

public class WeatherPagerActivity extends AppCompatActivity {

    private static final String EXTRA_MANAGE_ID = "xh189050131.weather.city_id";
    private static final String EXTRA_MANAGE_TITLE = "xh189050131.weather.city_title";

    private ViewPager mViewPager;
    private List<City> mCities;

    public static Intent newIntent(Context packageContext, UUID manageId) {
        Intent intent = new Intent(packageContext, WeatherPagerActivity.class);
        intent.putExtra(EXTRA_MANAGE_ID, manageId);
        return intent;
    }

    public static Intent newIntent(Context packageContext, String title) {
        Intent intent = new Intent(packageContext, WeatherPagerActivity.class);
        intent.putExtra(EXTRA_MANAGE_TITLE, title);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_pager);

        final UUID cityId = (UUID) getIntent().getSerializableExtra(EXTRA_MANAGE_ID);

        mViewPager = findViewById(R.id.weather_view_pager);

        mCities = CityLab.get(this).getCities();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                City city = mCities.get(position);
                return WeatherFragment.newInstance(city.getCid());
            }

            @Override
            public int getCount() {
                return mCities.size();
            }
        });

        // 给每条记录编号
        for (int i = 0; i < mCities.size(); i++) {
            if (mCities.get(i).getCid().equals(cityId)) {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}

