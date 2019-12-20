package com.example.weather1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather1.adapter.WeatherAdapter;
import com.example.weather1.model.Weather;
import com.example.weather1.services.NetworkProvider;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChooseWeatherActivity extends AppCompatActivity {
    @BindView(R.id.activity_choose_picture_rcv)
    RecyclerView pictureChoiceRcv;
    private WeatherAdapter weatherAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_weather);
        ButterKnife.bind(this);

        initRecyclerView();
        loadData();
    }

    private void initRecyclerView() {
        pictureChoiceRcv.setLayoutManager(new LinearLayoutManager(this));
        weatherAdapter = new WeatherAdapter();
        pictureChoiceRcv.setAdapter(weatherAdapter);

        weatherAdapter.setItemClickListener(new WeatherAdapter.ItemClickListener() {
            @Override
            public void onClick(Weather weather) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("wdate", weather.getDate());
                        intent.putExtra("wtemperature", Double.toString(weather.getHumidité()));
                        intent.putExtra("whumidite", Double.toString(weather.getTemperature()));
                        intent.putExtra("wizo-zero", Double.toString(weather.getIso_zero()));
                        intent.putExtra("wnebulosite", weather.getNebulosite());
                        intent.putExtra("wneige", Boolean.toString(weather.getNeige()));
                        intent.putExtra("wpluie", Double.toString(weather.getPluie()));
                        intent.putExtra("wdate", weather.getDate());
                        intent.putExtra("wpression", Double.toString(weather.getPression()));
                        intent.putExtra("wvent", Double.toString(weather.getVent()));
                        intent.putExtra("widpicture", weather.getIdPicture());
                        startActivity(intent);



            }
        });
    }

    private void loadData() {
        NetworkProvider.getInstance().getWeather(new NetworkProvider.Listner<ArrayList<Weather>>() {


            @Override
            public void onSuccess(ArrayList<Weather> data) {
                System.out.println("test" + data.toString());
                weatherAdapter.setPicturesList(data);
            }


            @Override public void onError(Throwable t) {
                Log.e("getWeatherMAIN","Error on getWeather : " + t.toString());

            }

        });


    }
}
