
package com.xh189050131.weathercast;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

public class WeatherActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return new WeatherFragment();
    }

    public static Intent newIntent(Context packageContext) {
        Intent intent = new Intent(packageContext, WeatherActivity.class);
        return intent;
    }
}

