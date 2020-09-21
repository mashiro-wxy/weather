
package com.xh189050131.weathercast;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;


public class WeatherFragment extends Fragment {

    private static final String ARG_MANAGE_ID = "weather_id";

    private List<WeatherItem> mItems = new ArrayList<>();
    private City mCity;
    private boolean flag = false;
    private EditText mEditText;
    private TextView mTextView2;
    private TextView mTextView3;
    private TextView mTextView4;
    private TextView mTextView5;
    private TextView mTextView6;
    private TextView mTextView7;

    private TextView h1a;
    private ImageView h1b;
    private TextView h1c;

    private TextView h2a;
    private ImageView h2b;
    private TextView h2c;

    private TextView h3a;
    private ImageView h3b;
    private TextView h3c;

    private TextView h4a;
    private ImageView h4b;
    private TextView h4c;

    private TextView h5a;
    private ImageView h5b;
    private TextView h5c;

    private TextView h6a;
    private ImageView h6b;
    private TextView h6c;

    private TextView h7a;
    private ImageView h7b;
    private TextView h7c;

    private TextView h8a;
    private ImageView h8b;
    private TextView h8c;

    private TextView w1a;
    private ImageView w1b;
    private TextView w1c;
    private TextView w1d;

    private TextView w2a;
    private ImageView w2b;
    private TextView w2c;
    private TextView w2d;

    private TextView w3a;
    private ImageView w3b;
    private TextView w3c;
    private TextView w3d;

    private TextView w4a;
    private ImageView w4b;
    private TextView w4c;
    private TextView w4d;

    private TextView w5a;
    private ImageView w5b;
    private TextView w5c;
    private TextView w5d;

    private TextView wind1;
    private TextView wind2;
    private TextView wind3;
    private TextView wind4;
    private TextView sd1;
    private TextView sd2;
    private TextView uv1;
    private TextView uv2;
    private TextView xc1;
    private TextView xc2;
    private TextView pm1;
    private TextView pm2;


    //private FragmentW binding;


    //根据ID创建实例
    public static WeatherFragment newInstance(UUID manageId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_MANAGE_ID, manageId);

        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // 保存部分数据
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        //new FetchItemsTask().execute();
        setHasOptionsMenu(true);

        mCity = new City();
        UUID cityId = (UUID) getArguments().getSerializable(ARG_MANAGE_ID);
        mCity = CityLab.get(getActivity()).getCity(cityId);
        if (mCity.getCname() == null){
            mCity.setCname("杭州");
        }

    }


    // UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        mEditText = v.findViewById(R.id.city);

        mTextView2 = v.findViewById(R.id.wtum1);
        mTextView3 = v.findViewById(R.id.tnow);
        mTextView4 = v.findViewById(R.id.weekNow);
        mTextView5 = v.findViewById(R.id.today);
        mTextView6 = v.findViewById(R.id.maxTtoday);
        mTextView7 = v.findViewById(R.id.minTtoday);

        h1a = v.findViewById(R.id.h1a);
        h1b = v.findViewById(R.id.h1b);
        h1c = v.findViewById(R.id.h1c);

        h2a = v.findViewById(R.id.h2a);
        h2b = v.findViewById(R.id.h2b);
        h2c = v.findViewById(R.id.h2c);

        h3a = v.findViewById(R.id.h3a);
        h3b = v.findViewById(R.id.h3b);
        h3c = v.findViewById(R.id.h3c);

        h4a = v.findViewById(R.id.h4a);
        h4b = v.findViewById(R.id.h4b);
        h4c = v.findViewById(R.id.h4c);

        h5a = v.findViewById(R.id.h5a);
        h5b = v.findViewById(R.id.h5b);
        h5c = v.findViewById(R.id.h5c);

        h6a = v.findViewById(R.id.h6a);
        h6b = v.findViewById(R.id.h6b);
        h6c = v.findViewById(R.id.h6c);

        h7a = v.findViewById(R.id.h7a);
        h7b = v.findViewById(R.id.h7b);
        h7c = v.findViewById(R.id.h7c);

        h8a = v.findViewById(R.id.h8a);
        h8b = v.findViewById(R.id.h8b);
        h8c = v.findViewById(R.id.h8c);


        w1a = v.findViewById(R.id.w1a);
        w1b = v.findViewById(R.id.w1b);
        w1c = v.findViewById(R.id.w1c);
        w1d = v.findViewById(R.id.w1d);

        w2a = v.findViewById(R.id.w2a);
        w2b = v.findViewById(R.id.w2b);
        w2c = v.findViewById(R.id.w2c);
        w2d = v.findViewById(R.id.w2d);

        w3a = v.findViewById(R.id.w3a);
        w3b = v.findViewById(R.id.w3b);
        w3c = v.findViewById(R.id.w3c);
        w3d = v.findViewById(R.id.w3d);

        w4a = v.findViewById(R.id.w4a);
        w4b = v.findViewById(R.id.w4b);
        w4c = v.findViewById(R.id.w4c);
        w4d = v.findViewById(R.id.w4d);

        w5a = v.findViewById(R.id.w5a);
        w5b = v.findViewById(R.id.w5b);
        w5c = v.findViewById(R.id.w5c);
        w5d = v.findViewById(R.id.w5d);

        //另外数据
        wind1 = v.findViewById(R.id.wind1);
        wind2 = v.findViewById(R.id.wind2);
        wind3 = v.findViewById(R.id.wind3);
        wind4 = v.findViewById(R.id.wind4);
        sd1 = v.findViewById(R.id.sd1);
        sd2 = v.findViewById(R.id.sd2);
        uv1 = v.findViewById(R.id.uv1);
        uv2 = v.findViewById(R.id.uv2);
        xc1 = v.findViewById(R.id.xc1);
        xc2 = v.findViewById(R.id.xc2);
        pm1 = v.findViewById(R.id.pm1);
        pm2 = v.findViewById(R.id.pm2);
        //mTextView1.setText(mItems.get(0).getWtNm());
        //updateView();
        if (mCity != null)
            mEditText.setText(mCity.getCname());
            mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCity.setCname(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                new FetchItemsTask().execute();
                CityLab.get(getActivity()).updateCity(mCity);
            }
        });
        new FetchItemsTask().execute();
        return v;
    }


    @Override
    public void onPause() {
        super.onPause();
        CityLab.get(getActivity()).updateCity(mCity);
    }

    // 根据需求更新视图
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
    }

    // 创建菜单
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_manage, menu);
    }

    // 根据选择响应
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_city:
                CityLab.get(getActivity()).removeCity(mCity);
                getActivity().finish();
                return true;
            case android.R.id.home:     //向上导航时保证子标题可见状态
                getActivity().finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateView(){
        if (flag){

            String i1 = "i" + mItems.get(2).getWtIcon();
            String i2 = "i" + mItems.get(3).getWtIcon();
            String i3 = "i" + mItems.get(4).getWtIcon();
            String i4 = "i" + mItems.get(5).getWtIcon();
            String i5 = "i" + mItems.get(6).getWtIcon();

            String h1 = "i" + mItems.get(7).getWtIcon();
            String h2 = "i" + mItems.get(8).getWtIcon();
            String h3 = "i" + mItems.get(9).getWtIcon();
            String h4 = "i" + mItems.get(10).getWtIcon();
            String h5 = "i" + mItems.get(11).getWtIcon();
            String h6 = "i" + mItems.get(12).getWtIcon();
            String h7 = "i" + mItems.get(13).getWtIcon();
            String h8 = "i" + mItems.get(14).getWtIcon();

            Class drawable = R.drawable.class;
            Field field = null;
            try {
                //未来5天图片
                field = drawable.getField(i1);
                int images = field.getInt(field.getName());
                w1b.setImageResource(images);

                field = drawable.getField(i2);
                images = field.getInt(field.getName());
                w2b.setImageResource(images);

                field = drawable.getField(i3);
                images = field.getInt(field.getName());
                w3b.setImageResource(images);

                field = drawable.getField(i4);
                images = field.getInt(field.getName());
                w4b.setImageResource(images);

                field = drawable.getField(i5);
                images = field.getInt(field.getName());
                w5b.setImageResource(images);

                //每三小时的图片
                field = drawable.getField(h1);
                images = field.getInt(field.getName());
                h1b.setImageResource(images);

                field = drawable.getField(h2);
                images = field.getInt(field.getName());
                h2b.setImageResource(images);

                field = drawable.getField(h3);
                images = field.getInt(field.getName());
                h3b.setImageResource(images);

                field = drawable.getField(h4);
                images = field.getInt(field.getName());
                h4b.setImageResource(images);

                field = drawable.getField(h5);
                images = field.getInt(field.getName());
                h5b.setImageResource(images);

                field = drawable.getField(h6);
                images = field.getInt(field.getName());
                h6b.setImageResource(images);

                field = drawable.getField(h7);
                images = field.getInt(field.getName());
                h7b.setImageResource(images);

                field = drawable.getField(h8);
                images = field.getInt(field.getName());
                h8b.setImageResource(images);



            }catch (Exception e) {
                e.printStackTrace();
            }

            //顶部
            mTextView2.setText(mItems.get(0).getWtNm());
            mTextView3.setText(mItems.get(0).getWtTemp() + "°");
            mTextView4.setText(mItems.get(0).getWeek());
            mTextView5.setText(" 今天");
            mTextView6.setText(mItems.get(1).getMaxT());
            mTextView7.setText(mItems.get(1).getMinT());

            //未来5天
            w1a.setText(mItems.get(2).getWeek());
            w1c.setText(mItems.get(2).getMaxT());
            w1d.setText(mItems.get(2).getMinT());
            w2a.setText(mItems.get(3).getWeek());
            w2c.setText(mItems.get(3).getMaxT());
            w2d.setText(mItems.get(3).getMinT());
            w3a.setText(mItems.get(4).getWeek());
            w3c.setText(mItems.get(4).getMaxT());
            w3d.setText(mItems.get(4).getMinT());
            w4a.setText(mItems.get(5).getWeek());
            w4c.setText(mItems.get(5).getMaxT());
            w4d.setText(mItems.get(5).getMinT());
            w5a.setText(mItems.get(6).getWeek());
            w5c.setText(mItems.get(6).getMaxT());
            w5d.setText(mItems.get(6).getMinT());

            //每三小时
            h1a.setText("现在");
            h1c.setText(mItems.get(7).getWtTemp());
            h2a.setText(mItems.get(8).getTime() + "时");
            h2c.setText(mItems.get(8).getWtTemp());
            h3a.setText(mItems.get(9).getTime() + "时");
            h3c.setText(mItems.get(9).getWtTemp());
            h4a.setText(mItems.get(10).getTime() + "时");
            h4c.setText(mItems.get(10).getWtTemp());
            h5a.setText(mItems.get(11).getTime() + "时");
            h5c.setText(mItems.get(11).getWtTemp());
            h6a.setText(mItems.get(12).getTime() + "时");
            h6c.setText(mItems.get(12).getWtTemp());
            h7a.setText(mItems.get(13).getTime() + "时");
            h7c.setText(mItems.get(13).getWtTemp());
            h8a.setText(mItems.get(14).getTime() + "时");
            h8c.setText(mItems.get(14).getWtTemp());


            //其他数据
            wind1.setText("风向");
            wind2.setText(mItems.get(0).getWindDirection());
            wind3.setText("风力");
            wind4.setText(mItems.get(1).getWind());
            sd1.setText("湿度");
            sd2.setText(mItems.get(0).getHumidity() + "%");
            uv1.setText("紫外线指数");
            uv2.setText(mItems.get(1).getUV());
            pm1.setText("PM2.5含量");
            pm2.setText(mItems.get(0).getPM2_5());
            xc1.setText("洗车指数");
            xc2.setText(mItems.get(1).getWashCar());

        }
    }

    private class FetchItemsTask extends AsyncTask<Void,Void,List<WeatherItem>> {

        @Override
        protected List<WeatherItem> doInBackground(Void... params) {
            if (mCity.getCname() != null)
                return new FlickrFetchr().fetchItems(mCity.getCname());
            else return null;

        }

        @Override
        protected void onPostExecute(List<WeatherItem> items){
            mItems = items;
            if (mItems == null || mItems.size() == 0){
                flag = false;
            }else{
                flag = true;
            }
            updateView();
        }
    }



}

