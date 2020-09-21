package com.xh189050131.weathercast;

import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FlickrFetchr {

    private static final String TAG = "FlickrFetchr";
    private static final String HTTP = "http://api.k780.com/?app=weather.realtime&weaid=1&ag=today,futureDay,lifeIndex,futureHour&appkey=47223&sign=bcd6deee32d8f8b738b43e6fea3ec4aa&format=json";
    private static final String SIGN = "bcd6deee32d8f8b738b43e6fea3ec4aa";
    private static final String APP_KEY = "47223";
    //private static final String API_KEY = "de53d12508459f27b7b94515e32787d1";

    public byte[] getUrlBytes(String urlSpec) throws IOException{
        URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        try{
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new IOException(connection.getResponseMessage() + ": with " + urlSpec);
            }

            int bytesRead = 0;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0){
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }

    }

    public String getUrlString(String urlSpec) throws IOException{
        return new String(getUrlBytes(urlSpec));
    }

    public List<WeatherItem> fetchItems(String cityId) {

        List<WeatherItem> items = new ArrayList<>();

        try {
            String url = Uri.parse("https://sapi.k780.com/")
                    .buildUpon()
                    .appendQueryParameter("app", "weather.realtime")
                    .appendQueryParameter("weaid", cityId)
                    .appendQueryParameter("ag", "today,futureDay,lifeIndex,futureHour")
                    .appendQueryParameter("appkey", APP_KEY)
                    .appendQueryParameter("sign", SIGN)
                    .build().toString();
            String jsonString = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonString);
            JSONObject jsonBody = new JSONObject(jsonString);
            parseItems(items, jsonBody);

        } catch (IOException ioe){
            Log.e(TAG, "Failed to fetch items", ioe);
        } catch (JSONException je){
            Log.e(TAG, "Failed to parse JSON", je);
        }

        return items;
    }

    private void parseItems(List<WeatherItem> items, JSONObject jsonBody) throws IOException, JSONException{
        String flag = jsonBody.getString("success");
        if (flag.equals("1")) {
            JSONObject weathersJsonObject = jsonBody.getJSONObject("result");

            //现在
            JSONObject weatherJson = weathersJsonObject.getJSONObject("realTime");
            WeatherItem item = new WeatherItem();
            item.setWeek(weatherJson.getString("week"));
            item.setwId(weatherJson.getString("wtId"));
            item.setWtNm(weatherJson.getString("wtNm"));
            item.setWtIcon(weatherJson.getString("wtIcon"));
            item.setWtTemp(weatherJson.getString("wtTemp"));
            item.setHumidity(weatherJson.getString("wtHumi"));
            item.setWindDirection(weatherJson.getString("wtWindNm"));
            item.setPM2_5(weatherJson.getString("wtAqi"));
            items.add(item);

            //今天
            JSONObject j2 = weathersJsonObject.getJSONObject("today");
            WeatherItem item1 = new WeatherItem();
            item1.setMaxT(j2.getString("wtTemp1"));
            item1.setMinT(j2.getString("wtTemp2"));
            item1.setWind(j2.getString("wtWinpNm1"));
            item1.setUV(j2.getJSONObject("lifeIndex").getJSONObject("uv").getString("liDese"));
            item1.setWashCar(j2.getJSONObject("lifeIndex").getJSONObject("xc").getString("liDese"));
            items.add(item1);

            //未来五天
            JSONArray j3 = weathersJsonObject.getJSONArray("futureDay");
            for (int i = 0; i < j3.length() && i < 5; i++){
                JSONObject jsonObject = j3.getJSONObject(i);
                WeatherItem item2 = new WeatherItem();
                item2.setWeek(jsonObject.getString("week"));
                item2.setWtIcon(jsonObject.getString("wtIcon1"));
                item2.setMaxT(jsonObject.getString("wtTemp1"));
                item2.setMinT(jsonObject.getString("wtTemp2"));
                items.add(item2);
            }

            //未来24小时
            JSONArray j4 = weathersJsonObject.getJSONArray("futureHour");
            for (int i = 0; i < j4.length(); i+=3){
                JSONObject jsonObject = j4.getJSONObject(i);
                WeatherItem item3 = new WeatherItem();
                item3.setTime(jsonObject.getString("dateYmdh").substring(11,13));
                item3.setWtIcon(jsonObject.getString("wtIcon"));
                item3.setWtTemp(jsonObject.getString("wtTemp"));
                items.add(item3);
            }

        }


    }
}



