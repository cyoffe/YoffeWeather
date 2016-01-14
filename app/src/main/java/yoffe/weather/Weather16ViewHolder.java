package yoffe.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;

/**
 * Created by CS on 1/10/2016.
 */
public class Weather16ViewHolder extends RecyclerView.ViewHolder{

    private final Context context;
    @Bind(R.id.weatherIcon)ImageView icon;
    @Bind(R.id.max) TextView high;
    @Bind(R.id.min) TextView low;
    @Bind(R.id.dayOfWeek) TextView dayOfWeek;



    public Weather16ViewHolder(View itemView, Context context) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
    }



    public void bind(Weather16 theWeather ){
        high.setText(String.valueOf((int) theWeather.getTemp().getMax() + "°"));
        low.setText(String.valueOf((int) theWeather.getTemp().getMin() + "°"));

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        dayOfWeek.setText(sdf.format(theWeather.getDt()));


        Picasso.with(context).load("http://openweathermap.org/img/w/" +
                theWeather.getWeather()[0].getIcon() + ".png").into(icon);


    }
}
