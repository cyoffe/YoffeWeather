package yoffe.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by CS on 1/10/2016.
 */
public class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {


    private final Context context;
    @Bind(R.id.weather) TextView currentWeather;
    @Bind(R.id.high) TextView high;
    @Bind(R.id.low) TextView low;
    @Bind(R.id.condition) TextView condition;
    @Bind(R.id.icon) ImageView icon;
    @Bind(R.id.forecast)TextView forecast;
    @Bind(R.id.upArrow)TextView upArrow;

    public CurrentWeatherViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        ButterKnife.bind(this, itemView);
    }


    public void bind(CurrentWeather theWeather) {
      low.setText(String.valueOf((int)theWeather.getMain().getTempMin()+ "°"));
        high.setText(String.valueOf((int)theWeather.getMain().getTempMax()+ "°"));
        currentWeather.setText(String.valueOf((int)theWeather.getMain().getTemp()+ "°"));
        condition.setText(theWeather.getWeather()[0].getDescription());


        Picasso.with(context).load("http://openweathermap.org/img/w/" +
                theWeather.getWeather()[0].getIcon() + ".png").into(icon);

        forecast.setText(" Forecast");
        upArrow.setText("⇤");
    }
}