package com.example.weather1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.TextView;

import com.example.weather1.Helpers.HelperImage;


public class MainActivity extends AppCompatActivity {
    HelperImage helperImage = new HelperImage();
    TextView date;
    TextView temperature;
    TextView wind;
    TextView rain;
    TextView pressure;
    TextView neige;
    ConstraintLayout layout;
    Bundle b = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        date = this.findViewById(R.id.date_tv);
        temperature =this.findViewById(R.id.temp_val_tv);
        wind =this.findViewById(R.id.wind_val_tv);
        rain =this.findViewById(R.id.rain_val_tv);
        pressure =this.findViewById(R.id.pressure_val_tv);
        neige = this.findViewById(R.id.neige_val_tv);
        layout = this.findViewById(R.id.layout_main);



        b = getIntent().getExtras();
        String stemperature = b.getString("wtemperature");
        final String swind = b.getString("wvent");
        String srain = b.getString("wpluie");
        String spressure = b.getString("wpression");
        String sdate = b.getString("wdate");
        String sneige = b.getString("wneige");
        int idPicture = 0;
        idPicture = b.getInt("widpicture");

        temperature.setText(stemperature + "°");
        wind.setText(swind + "km/h");
        rain.setText(srain +"mm");
        pressure.setText(spressure + "P");
        date.setText(sdate);
        if(sneige.equals("false")) {
            neige.setText("Non");
        } else {
            neige.setText("Oui");
        }
        if(idPicture != 0 && getBaseContext() != null && layout != null) {

            helperImage.setBackgroundActivity(idPicture, getBaseContext(), layout);
        }








    }


}
