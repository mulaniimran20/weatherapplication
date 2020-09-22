package com.synerzip.assignment.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.synerzip.assignment.R;
import com.synerzip.assignment.data.WheatherData;
import com.synerzip.assignment.database.DatabaseHelper;
import com.synerzip.assignment.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


import com.synerzip.assignment.data.MyApplication;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {


    EditText mEditTextSearchBox;
    String mCityName;
    private String mAppId = "8350ce00fd241edebaac8403c3e389d8";
    RequestQueue mRequestQueue, mRequestQueueWheatherForecast;
    String mSunRiseTime;
    String mSunSetTime;
    String mWheatherConditions;
    String mWheatherIconUrl;
    String mCurrentDate;
    double mTemprature, mMinimumTemprature, mMaximumTemprature;

    @Inject
    WheatherData mWheatherData;

    String mDateCurrent;
    ActivityMainBinding mActivityMainBinding;
    DatabaseHelper mDatabaseHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


         mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        mEditTextSearchBox = findViewById(R.id.searchCity);
        mRequestQueue = Volley.newRequestQueue(this);
        mRequestQueueWheatherForecast = Volley.newRequestQueue(this);

        mDatabaseHelper = new DatabaseHelper(this);

        mWheatherData = MyApplication.component.wheatherData();



        mEditTextSearchBox.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    MainActivity.this.searchCityWeather();     // you can do anything
                    InputMethodManager imm = (InputMethodManager)MainActivity.this.getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(mEditTextSearchBox.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

    }

    void searchCityWeather()
    {
        mCityName = mEditTextSearchBox.getText().toString();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        mDateCurrent = dateFormat.format(cal.getTime());

        mWheatherData = mDatabaseHelper.getCityWiseWeatherData(mCityName, mDateCurrent);
        System.out.println("City Name = "+mWheatherData.getCityName());

        if (mWheatherData.getCityName() == null) {
            String urlForCityData = "https://api.openweathermap.org/data/2.5/weather?q=" + mCityName + "&appid=" + mAppId + "&units=metric";
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlForCityData, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        JSONObject jsonObjectOfCordinates = response.getJSONObject("coord");
                        JSONObject jsonObjectSys = response.getJSONObject("sys");
                        JSONObject jsonObjectWeather = response.getJSONArray("weather").getJSONObject(0);
                        JSONObject jsonObjectTempratureDetails = response.getJSONObject("main");

                        double longitudeValue = jsonObjectOfCordinates.getDouble("lon");
                        double latitudeValue = jsonObjectOfCordinates.getDouble("lat");
                        String countryName = jsonObjectSys.getString("country");
                        int sunriseTimeStamp = jsonObjectSys.getInt("sunrise");
                        int sunsetTimeStamp = jsonObjectSys.getInt("sunset");
                        int cityid = response.getInt("id");

                        Date d = new Date(TimeUnit.SECONDS.toMillis(sunriseTimeStamp));
                        Date d1 = new Date(TimeUnit.SECONDS.toMillis(sunsetTimeStamp));

                        String patternDate = "dd/MM/yyyy";
                        String pattern = "hh:mm:ss a";
                        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(pattern);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patternDate);

                        mCurrentDate = simpleDateFormat.format(d);
                        mSunRiseTime = simpleTimeFormat.format(d);
                        mSunSetTime = simpleTimeFormat.format(d1);
                        mWheatherConditions = jsonObjectWeather.getString("main");
                        mWheatherIconUrl = "https://openweathermap.org/img/wn/" + jsonObjectWeather.getString("icon") + "@4x.png";
                        mTemprature = jsonObjectTempratureDetails.getDouble("temp");
                        mMaximumTemprature = jsonObjectTempratureDetails.getDouble("temp_min");
                        mMinimumTemprature = jsonObjectTempratureDetails.getDouble("temp_max");


                        mWheatherData.setCityName(mCityName);
                        mWheatherData.setDate(mCurrentDate);
                        mWheatherData.setSunriseTime(mSunRiseTime);
                        mWheatherData.setSunsetTime(mSunSetTime);
                        mWheatherData.setWheatherConditions(mWheatherConditions);
                        mWheatherData.setWheatherIconUrl(mWheatherIconUrl);
                        mWheatherData.setTemprature(mTemprature+ "\u2103");
                        mWheatherData.setMinimumTemprature(mMinimumTemprature+ "\u2103");
                        mWheatherData.setMaximumTemprature(mMaximumTemprature+ "\u2103");
                        mDatabaseHelper.insertData(mWheatherData);
                        mActivityMainBinding.setWheatherdata(mWheatherData);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    String body = null;
                    try {
                        body = new String(error.networkResponse.data, "UTF-8");
                        if (body != "") {
                            JSONObject jsonObject = new JSONObject(body);
                            Toast.makeText(MainActivity.this, "Please Enter Valid City Name ...", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Network Error, Please Try Again", Toast.LENGTH_SHORT).show();
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });

            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 2, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            mRequestQueue.add(jsonObjectRequest);



        }
        else{
            mActivityMainBinding.setWheatherdata(mWheatherData);
            System.out.println("Old Data Displayed");
        }
    }

}
