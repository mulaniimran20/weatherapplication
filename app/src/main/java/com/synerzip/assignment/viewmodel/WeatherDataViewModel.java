package com.synerzip.assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.synerzip.assignment.data.local.LocalModule;
import com.synerzip.assignment.model.Coord;
import com.synerzip.assignment.model.LocalWeatherDataModel;
import com.synerzip.assignment.model.Main;
import com.synerzip.assignment.model.Sys;
import com.synerzip.assignment.model.Weather;
import com.synerzip.assignment.model.WeatherDataModel;
import com.synerzip.assignment.repository.WeatherDataRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class WeatherDataViewModel extends ViewModel {

    private WeatherDataRepository weatherDataRepository;


    private final CompositeDisposable disposable = new CompositeDisposable();
    private final MutableLiveData<WeatherDataModel> modelMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();


    private MutableLiveData<String>  mCityName = new MutableLiveData<>();


    @Inject
    public WeatherDataViewModel(WeatherDataRepository weatherDataRepository) {
        this.weatherDataRepository = weatherDataRepository;
    }

    public MutableLiveData<WeatherDataModel> getModelMutableLiveData(String cityName, LocalModule localModule) {
        loadData(cityName, localModule);
        return modelMutableLiveData;
    }

    private void loadData(String cityName, final LocalModule localModule) {
        isLoading.setValue(true);

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String todayDate = dateFormat.format(date);


        List<LocalWeatherDataModel> localWeatherDataModels = localModule.findByCityName(cityName, todayDate);

        LocalWeatherDataModel localWeatherDataModel = new LocalWeatherDataModel();
        WeatherDataModel weatherDataModel = new WeatherDataModel();
        if (localWeatherDataModels.size() > 0) {
            localWeatherDataModel = localWeatherDataModels.get(0);




            weatherDataModel.setName(localWeatherDataModel.getName());
            Coord coord = new Coord(localWeatherDataModel.getLon(), localWeatherDataModel.getLat());
            weatherDataModel.setCoord(coord);
            Main main = new Main(Double.parseDouble(localWeatherDataModel.getTemp()), Double.parseDouble(localWeatherDataModel.getTemp_min()), Double.parseDouble(localWeatherDataModel.getTemp_max()), localWeatherDataModel.getPressure(), Double.parseDouble(localWeatherDataModel.getHumidity()));
            weatherDataModel.setMain(main);
            String sunRiseT = localWeatherDataModel.getDt() + " " + localWeatherDataModel.getSunrise();
            String sunSetT = localWeatherDataModel.getDt() + " " + localWeatherDataModel.getSunset();
            String curDate = localWeatherDataModel.getDt() + " 06:00:00 am";
            SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
            try {
                Date sunRiseD = f.parse(sunRiseT);
                Date sunSetD = f.parse(sunSetT);
                Date curDateParse = f.parse(curDate);

                long sunriselong = (sunRiseD.getTime()) / 1000;
                long sunsetlong = (sunSetD.getTime()) / 1000;
                long datelong = (curDateParse.getTime()) / 1000;
                Sys sys = new Sys(localWeatherDataModel.getCountry(), sunriselong, sunsetlong);
                weatherDataModel.setSys(sys);


                weatherDataModel.setDt(datelong);

            } catch (ParseException e) {
                e.printStackTrace();
            }





            Weather weather = new Weather(localWeatherDataModel.getDescription(), localWeatherDataModel.getDescription(), localWeatherDataModel.getIcon());
            List<Weather> weatherList = new ArrayList<Weather>();
            weatherList.add(weather);
            weatherDataModel.setWeather(weatherList);
            weatherDataModel.setVisibility(localWeatherDataModel.getVisibility());



            isLoading.setValue(false);
            modelMutableLiveData.setValue(weatherDataModel);

        } else {

            disposable.add(weatherDataRepository.weatherDataModelSingle(cityName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<WeatherDataModel>() {

                        @Override
                        public void onSuccess(WeatherDataModel weatherDataModel) {
                            isLoading.setValue(false);
                            modelMutableLiveData.setValue(weatherDataModel);


                            LocalWeatherDataModel localWeatherDataModel1 = new LocalWeatherDataModel();
                            localWeatherDataModel1.setName(weatherDataModel.getName());
                            localWeatherDataModel1.setCountry(weatherDataModel.getSys().getCountry());
                            localWeatherDataModel1.setSunrise(weatherDataModel.getSys().getSunrise());
                            localWeatherDataModel1.setSunset(weatherDataModel.getSys().getSunset());

                            localWeatherDataModel1.setTemp(weatherDataModel.getMain().getTemp());
                            localWeatherDataModel1.setTemp_max(weatherDataModel.getMain().getTemp_max());
                            localWeatherDataModel1.setTemp_min(weatherDataModel.getMain().getTemp_min());
                            localWeatherDataModel1.setPressure(weatherDataModel.getMain().getPressure());
                            localWeatherDataModel1.setHumidity(weatherDataModel.getMain().getHumidity());

                            localWeatherDataModel1.setDescription(weatherDataModel.getWeather().get(0).getDescription());
                            localWeatherDataModel1.setIcon(weatherDataModel.getWeather().get(0).getIcon());

                            localWeatherDataModel1.setVisibility(weatherDataModel.getVisibility());
                            localWeatherDataModel1.setDt(weatherDataModel.getDt());
                            localWeatherDataModel1.setName(weatherDataModel.getName());

                            localModule.insertAll(localWeatherDataModel1);


                        }

                        @Override
                        public void onError(Throwable e) {
                            System.out.println(e.getMessage());
                            error.setValue(e.getMessage());
                            isLoading.setValue(false);
                        }
                    }));

        }
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public MutableLiveData<String> getError() {
        System.out.println("Error is = "+error.toString());
        return error;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();

    }

}
