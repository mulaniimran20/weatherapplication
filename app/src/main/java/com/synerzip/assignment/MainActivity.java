package com.synerzip.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.synerzip.assignment.R;
import com.synerzip.assignment.data.AppDatabase;
import com.synerzip.assignment.data.dao.WheatherDataDao;
import com.synerzip.assignment.data.local.LocalModule;
import com.synerzip.assignment.data.local.WeatherDataRoomDatabase;
import com.synerzip.assignment.databinding.ActivityMainBinding;
import com.synerzip.assignment.model.Coord;
import com.synerzip.assignment.model.LocalWeatherDataModel;
import com.synerzip.assignment.model.Main;
import com.synerzip.assignment.model.SearchKeywordDataModel;
import com.synerzip.assignment.model.Sys;
import com.synerzip.assignment.model.Weather;
import com.synerzip.assignment.model.WeatherDataModel;
import com.synerzip.assignment.viewmodel.WeatherDataViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private WeatherDataViewModel weatherDataViewModel;
    private static final String TAG = "WeatherData";

    String cityName = "Pune";
    WeatherDataRoomDatabase database;
    LocalModule localModule;
    String todayDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        todayDate =dateFormat.format(date);


        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ((BaseApplication) getApplication()).getAppComponent().inject(this);

        database = WeatherDataRoomDatabase.getDatabase(this);
         localModule = new LocalModule(database);
         localModule.delete(todayDate);

        weatherDataViewModel = ViewModelProviders.of(this, viewModelFactory).get(WeatherDataViewModel.class);

        getData(cityName);


        activityMainBinding.searchCity.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    cityName = activityMainBinding.searchCity.getText().toString();
                    getData(cityName);
                    return true;
                }
                return false;
            }
        });;





    }

    public void getData(final String cityName)
    {
        weatherDataViewModel.getModelMutableLiveData(cityName, localModule).observe(MainActivity.this, new Observer<WeatherDataModel>() {
            @Override
            public void onChanged(WeatherDataModel weatherDataModel) {
                    activityMainBinding.setWeatherData(weatherDataModel);
            }
        });

        weatherDataViewModel.getIsLoading().observe(MainActivity.this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.d(TAG, aBoolean.toString());
            }
        });

        weatherDataViewModel.getError().observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.d(TAG, s);
            }
        });
    }


}
