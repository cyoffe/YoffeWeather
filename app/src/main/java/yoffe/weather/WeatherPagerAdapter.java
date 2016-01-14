package yoffe.weather;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherPagerAdapter extends PagerAdapter {
    private final Context context;
    private ArrayList<String> locations;
    private WeatherList weather;
    private CurrentWeather currentWeather;
    private WeatherService service;
    private WeatherAdapter weatherAdapter;
    @Bind(R.id.city) TextView city;
    @Bind(R.id.date) TextClock date;
    private EditText zipCode;
    @Bind(R.id.addLocationButton)Button locationButton;
    @Bind(R.id.background) ImageView background;
    CurrentWeatherService currentWeatherService;


    public WeatherPagerAdapter(ArrayList<String> locations, Context mainActivityContext) {
        this.context = mainActivityContext;
        this.locations = locations;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(WeatherService.class);
        currentWeatherService =  retrofit.create(CurrentWeatherService.class);

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {

        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        View view = inflater.inflate(R.layout.weather_pager_item, null);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setLayoutManager(layoutManager);
        ButterKnife.bind(this, view);


        Picasso.with(context)
                .load("http://lorempixel.com/600/820/nature")
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .resize(600,820)
                .into(background);


        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa");
        Date today = new Date();
        date.setText(sdf.format(today).toString());

        locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });


        Map<String, String> params = new HashMap<>();
        params.put("zip", locations.get(position));
        params.put("appid", "c08c6900d895082702317a1b4c3fae0d");
        params.put("units", "imperial");

        final ArrayList<Object> list = new ArrayList<>();

        Call<CurrentWeather> currentWeatherCall = currentWeatherService.listCurrentWeather(params);
        currentWeatherCall.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Response<CurrentWeather> response) {
                currentWeather = response.body();
                list.add(0, currentWeather);
                city.setText(currentWeather.getName());

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });

       params.put("cnt", "16");
        Call<WeatherList> call = service.listWeather(params);
        call.enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Response<WeatherList> response) {
                weather = response.body();


                for(Weather16 w : weather.getList()) {
                    list.add(w);
                }

            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }


        });
        weatherAdapter = new WeatherAdapter(list, context);
        recyclerView.setAdapter(weatherAdapter);

        container.addView(view);
        return view;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    private void showAlertDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View promptView = layoutInflater.inflate(R.layout.input_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setView(promptView);

        zipCode = (EditText) promptView.findViewById(R.id.zipCode);


        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String zip = String.valueOf(zipCode.getText());
                        if(!locations.contains(zip)){
                        locations.add(zip);
                        notifyDataSetChanged();
                        }
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });


        AlertDialog alert = alertDialogBuilder.create();
        alert.show();
    }
}

