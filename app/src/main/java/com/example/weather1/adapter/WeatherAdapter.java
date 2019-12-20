package com.example.weather1.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.weather1.ChooseWeatherActivity;
import com.example.weather1.Helpers.HelperImage;
import com.example.weather1.MainActivity;
import com.example.weather1.R;
import com.example.weather1.model.Weather;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
  private List<Weather> weathersList;
  private ItemClickListener itemClickListener;
  private HelperImage helperImage = new HelperImage();
  private ConstraintLayout layout;



  public void setPicturesList(List<Weather> weathersList) {
    this.weathersList = weathersList;
    notifyDataSetChanged();
  }

  public void setItemClickListener(ItemClickListener itemClickListener) {
    this.itemClickListener = itemClickListener;
  }

  @NonNull
  @Override
  public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
    View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_picture, viewGroup, false);

    return new WeatherViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull WeatherViewHolder weatherViewHolder, int i) {
    final Weather weather = weathersList.get(i);
    weatherViewHolder.nameTv.setText(weather.getDate());
    weatherViewHolder.temperaturetv.setText(Integer.toString(weather.getTemperature()) + " °");




    int idPicture;
    if(isSkyClear(weather) == 1){
      idPicture = ChooseClearPicture(weather.getDate());
      Glide.with(weatherViewHolder.itemView).load(R.drawable.iconsun).into(weatherViewHolder.weatherImv);
    } else {
      idPicture = ChooseRainPicture(weather.getDate());
      Glide.with(weatherViewHolder.itemView).load(R.drawable.iconrain).into(weatherViewHolder.weatherImv);
    }

    helperImage.setBackgroundActivity(idPicture, weatherViewHolder.itemView.getContext(), weatherViewHolder.layout);

    if (itemClickListener != null) {
      MainActivity activity = new MainActivity();
      weatherViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

          weather.setIdPicture(idPicture);
          itemClickListener.onClick(weather);

        }
      });
    }
  }

  @Override
  public int getItemCount() {
    return weathersList != null ? weathersList.size() : 0;
  }

  class WeatherViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_picture_imv)
    ImageView weatherImv;
    @BindView(R.id.item_picture_name_tv)
    TextView nameTv;
    @BindView(R.id.temperature_tv)
    TextView temperaturetv ;
    @BindView(R.id.item_layout)
    ConstraintLayout layout ;

    public WeatherViewHolder(@NonNull View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }

  public int ChooseRainPicture(String date){
    String time = date.substring(10,19);
    int image;

    switch(time) {

      case " 04:00:00":

        image = R.drawable.rain4h;
        break;
      case " 07:00:00":
        image = R.drawable.rain7h;
        // code block
        break;
      case " 10:00:00":
        image = R.drawable.rain10am;
        // code block
        break;
      case " 13:00:00":
        image = R.drawable.rain13h;
        // code block
        break;
      case " 16:00:00":
        image = R.drawable.rain16h;
        // code block
        break;
      case " 19:00:00":
        image = R.drawable.rain19h;
        // code block
        break;
      case " 22:00:00":
        image = R.drawable.rain22h;
        // code block
        break;
      case " 01:00:00":

        image = R.drawable.rain01h;
        break;
      default:
        // code block
        image = R.drawable.ic_launcher_background;
    }

    return image;
  }

  public int ChooseClearPicture(String date){
    String time = date.substring(10,19);
    int image;

    switch(time) {

      case " 04:00:00":

        image = R.drawable.clear4h;
        break;
      case " 07:00:00":
        image = R.drawable.sun7h;
        // code block
        break;
      case " 10:00:00":
        image = R.drawable.clear10h;
        // code block
        break;
      case " 13:00:00":
        image = R.drawable.clear13;
        // code block
        break;
      case " 16:00:00":
        image = R.drawable.clear17h;
        // code block
        break;
      case " 19:00:00":
        image = R.drawable.clear19h;
        // code block
        break;
      case " 22:00:00":
        image = R.drawable.clear22h;
        // code block
        break;
      case " 01:00:00":

        image = R.drawable.clear1h;
        break;
      default:
        // code block
        image = R.drawable.ic_launcher_background;
    }

    return image;
  }

  public int isSkyClear(Weather weather) {

    if(weather.getPluie() >= 1.0) {
      return 1;
    } else {
      return 0;
    }


  }
  public interface ItemClickListener {
    void onClick(Weather weather);
  }

}


