package com.xh189050131.weathercast;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;


public class WeatherListFragment extends Fragment {

    private RecyclerView mManageRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CityAdapter mAdapter;
    //private static int mManageIndex;
    private TextView mNullManageListTextView;
    private Button mAddManageButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    // UI
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather_list, container, false);

        mManageRecyclerView =  view.findViewById(R.id.city_recycler_view);
        mManageRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mNullManageListTextView = view.findViewById(R.id.null_city_list);

        mAddManageButton = view.findViewById(R.id.add_city);
        mAddManageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                City city = new City();
                CityLab.get(getActivity()).addCity(city);
                Intent intent = WeatherPagerActivity.newIntent(getActivity(), city.getCid());
                startActivity(intent);
            }
        });

        initswiprefresh(view);
        updateUI();
        return view;
    }

    private void initswiprefresh(View view) {
        swipeRefreshLayout = view.findViewById(R.id.swiprefresh);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
                updateUI();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // 创建菜单
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_manage_list, menu);
    }

    // 响应菜单点击
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_city:   // 创建新记录
                City city = new City();
                CityLab.get(getActivity()).addCity(city);
                Intent intent = WeatherPagerActivity.newIntent(getActivity(), city.getCid());
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    // 更新UI界面
    private void updateUI() {
        CityLab cityLab = CityLab.get(getActivity());
        List<City> cities = cityLab.getCities();

        if (mAdapter == null) {
            mAdapter = new CityAdapter(cities);
            mManageRecyclerView.setAdapter(mAdapter);
        } else {
            //重绘当前可见区域
            mAdapter.setCities(cities);
            mAdapter.notifyDataSetChanged();

            /*//部分重绘
            mAdapter.notifyItemChanged(mManageIndex);*/
        }

        if (cities.size() != 0) {
            mNullManageListTextView.setVisibility(View.INVISIBLE);
            mAddManageButton.setVisibility(View.INVISIBLE);
        } else {
            mNullManageListTextView.setVisibility(View.VISIBLE);
            mAddManageButton.setVisibility(View.VISIBLE);
        }

    }

    private class CityHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitleTextView;
        private Button deleteButton;
        private City mCity;

        public CityHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.list_item_weather, parent, false));
            itemView.setOnClickListener(this);

            mTitleTextView = itemView.findViewById(R.id.city_name);
            //mTitleTextView.setText("杭州");
            /*deleteButton = itemView.findViewById(R.id.delete_button1);
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CityLab.get(getActivity()).removeCity(mCity);
                    updateUI();
                }
            });*/
        }

        public void bind(City city) {
            mCity = city;
            //mTitleTextView.setText("杭州");
            mTitleTextView.setText(mCity.getCname());

        }

        @Override
        public void onClick(View view) {
            Intent intent = WeatherPagerActivity.newIntent(getActivity(), mCity.getCid());
            //mManageIndex = getAdapterPosition();     //返回数据在adapter中的位置
            startActivity(intent);
        }
    }


    private class CityAdapter extends RecyclerView.Adapter<CityHolder> {
        private List<City> mCities;

        public CityAdapter(List<City> cities) {
            mCities = cities;
        }

        public void setCities(List<City> cities) {
            mCities = cities;
        }
        //视图类别功能

        @NonNull
        @Override
        public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            //先建立LayoutInflater
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new CityHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(CityHolder holder, int position) {
            City city = mCities.get(position);
            (holder).bind(city);

        }

        @Override
        public int getItemCount() {
            return mCities.size();
        }


    }


}

