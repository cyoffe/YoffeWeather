package yoffe.weather;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by CS on 1/12/2016.
 */
/*public class CurrentWeatherAsyncTask extends AsyncTask<String, String, CurrentWeather>{

    final RecyclerView.ViewHolder viewHolder;
    private String zipcode;
    private CurrentWeather currentWeather;

    public CurrentWeatherAsyncTask(String zipcode, RecyclerView.ViewHolder viewHolder) {
        this.zipcode = zipcode;
        this.viewHolder = viewHolder;
    }


    @Override
    protected CurrentWeather doInBackground(String... params) {
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();


        try {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?zip=" + zipcode +
                    "&appid=2de143494c0b295cca9337e1e96b00e0&units=imperial";
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            InputStream in = connection.getInputStream();

            currentWeather = gson.fromJson(new InputStreamReader(in), CurrentWeather.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentWeather;
    }

    @Override
    protected void onPostExecute(CurrentWeather currentWeather) {
        super.onPostExecute(currentWeather);
        ((CurrentWeatherViewHolder)viewHolder).bind(currentWeather);
    }
}
*/
