
package com.xh189050131.weathercast;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class WeatherListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new WeatherListFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, WeatherListActivity.class);
        return intent;
    }
}

