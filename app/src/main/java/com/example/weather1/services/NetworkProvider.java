package com.example.weather1.services;

import android.util.Log;

import com.example.weather1.model.Weather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class NetworkProvider {
    WeatherServices weatherServices;

    //Singleton pas tres bien car tout le monde peut y acceder
    private static NetworkProvider instance;


    public static NetworkProvider getInstance() {
        if (instance == null) {
            instance = new NetworkProvider();
        }
        return instance;
    }


    public NetworkProvider() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.infoclimat.fr/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherServices = retrofit.create(WeatherServices.class);
    }




    ArrayList<Weather> weatherData = new ArrayList<>();

    public void getWeather (final Listner<ArrayList<Weather>> listner) {

        List<String> times = new ArrayList<>();
        times.add("01:00:00");
        times.add("04:00:00");
        times.add("07:00:00");
        times.add("10:00:00");
        times.add("13:00:00");
        times.add("16:00:00");
        times.add("19:00:00");
        times.add("22:00:00");

        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd ");


        weatherServices.getWeather().enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                String res = new Gson().toJson(response.body());
                JsonObject jsonObject = new JsonParser().parse(res).getAsJsonObject();

                jsonObject.remove("request_state");
                jsonObject.remove("request_key");
                jsonObject.remove("message");
                jsonObject.remove("model_run");
                jsonObject.remove("source");


                Calendar calendar = Calendar.getInstance();


                for(int i = 0; i < 3; i++) {

                    for(String time:times) {
                        String date = formatter.format(calendar.getTime()) + time;
                        System.out.println("Current Date = " + date);


                        if(jsonObject.get(date) != null) {

                            String sweather = jsonObject.get(date).toString();
                            JsonObject jweather = jsonObject.get(date).getAsJsonObject();
                            JsonObject temperature = jweather.get("temperature").getAsJsonObject();
                            JsonObject pression = jweather.get("pression").getAsJsonObject();
                            JsonObject humidite = jweather.get("humidite").getAsJsonObject();
                            JsonObject vent_moyen = jweather.get("vent_moyen").getAsJsonObject();
                            JsonObject nebulosite = jweather.get("nebulosite").getAsJsonObject();


                            double diso_zero = 0.0;
                            double dpluie = 0.0;
                            Boolean drisque_neige = false;
                            int dtemperature = 0;
                            double dpression = 0.0;
                            double dhumidite = 0.0;
                            double dvent_moyen = 0.0;
                            double dnebulosite = 0.0;

                            if (jweather.get("iso_zero") != null) {
                                diso_zero = jweather.get("iso_zero").getAsDouble();
                            }


                            if (jweather.get("pluie") != null) {
                                dpluie = jweather.get("pluie").getAsDouble();
                            }

                            if (jweather.get("risque_neige") != null) {
                                drisque_neige = jweather.get("risque_neige").getAsBoolean();
                            }


                            if (temperature.get("2m") != null) {
                                dtemperature = temperature.get("2m").getAsInt() - 273;
                            }

                            if (pression.get("niveau_de_la_mer") != null) {
                                dpression = pression.get("niveau_de_la_mer").getAsDouble();
                            }

                            if (humidite.get("2m") != null) {
                                dhumidite = humidite.get("2m").getAsDouble();
                            }

                            if (vent_moyen.get("10m") != null) {
                                dvent_moyen = vent_moyen.get("10m").getAsDouble();
                            }

                            if (nebulosite.get("moyenne") != null) {
                                dnebulosite = nebulosite.get("moyenne").getAsDouble();
                            }


                            Weather weather = new Weather(dtemperature, dpression, dpluie,dhumidite,dvent_moyen, drisque_neige,dnebulosite,diso_zero, date);
                            weatherData.add(weather);

                        }

                    }

                    calendar.add(Calendar.DATE, +1);

                }
                listner.onSuccess(weatherData);

            }


            @Override
            public void onFailure(Call<Object> call, Throwable t) {

                listner.onError(t);
                Log.e("getWeatherNP", "Error on getWeather : " + t.toString());
            }
        });


    }

public interface Listner<T> {
    void onSuccess(T data);
    void onError(Throwable t);
}
    }



