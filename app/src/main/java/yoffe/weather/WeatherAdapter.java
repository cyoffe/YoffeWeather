package yoffe.weather;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
    private CurrentWeather currentWeather;
    private ArrayList<Object> list;

    // Provide a suitable constructor (depends on the kind of dataset)
    public WeatherAdapter(ArrayList<Object> list, Context context) {

        this.list = list;
        this.context = context;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return this.list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof CurrentWeather) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType) {
            case 0:
                View itemView = inflater.inflate(R.layout.current_weather_item, parent, false);
                return  new CurrentWeatherViewHolder(itemView, context);


            case 1:
                View itemView1 = inflater.inflate(R.layout.weather16_list, parent, false);
                return new Weather16ViewHolder(itemView1, context);

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 0:
                //CurrentWeatherViewHolder currentWeather = (CurrentWeatherViewHolder) viewHolder;
              //CurrentWeatherAsyncTask adapter = new CurrentWeatherAsyncTask(zip, currentWeather );
              //  adapter.execute();
             CurrentWeatherViewHolder currentWeather = (CurrentWeatherViewHolder) viewHolder;
              currentWeather.bind((CurrentWeather) list.get(position));
                break;
            case 1:
                Weather16ViewHolder weather16= (Weather16ViewHolder) viewHolder;
                weather16.bind((Weather16) list.get(position));
                break;

        }
    }
}

